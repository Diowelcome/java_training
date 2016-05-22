package ru.stesting.jtraining.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stesting.jtraining.addressbook.model.Contacts;
import ru.stesting.jtraining.addressbook.model.ShortContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Dborisov on 26.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ShortContactData().withFirstname("Alexei").withEmail("barancev@gmail.com"));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ShortContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
