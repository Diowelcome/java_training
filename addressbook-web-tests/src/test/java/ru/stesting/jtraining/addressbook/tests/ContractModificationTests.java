package ru.stesting.jtraining.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stesting.jtraining.addressbook.model.Contacts;
import ru.stesting.jtraining.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Dborisov on 26.04.2016.
 */
public class ContractModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Alexei").withEmail("barancev@gmail.com"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Alexei_1").withLastname("Barancev_1").withEmail("barancev_1@gmail.com");
    app.contact().modify(contact);
    Contacts  after = app.db().contacts();
    // Берем контактные данные из формы, где инициализируются все переменные перед записью в базу (кроме фото :) )
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertEquals(after.size(), before.size());
    // Можно было бы добавить фото .withPhoto(contact.getPhoto())
    // Но фото хранится в базе в виде картинки, а не в виде пути, который задается на входе
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactInfoFromEditForm)));
  }

}
