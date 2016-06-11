package ru.stesting.jtraining.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stesting.jtraining.addressbook.model.ContactData;
import ru.stesting.jtraining.addressbook.model.Contacts;
import ru.stesting.jtraining.addressbook.model.GroupData;
import ru.stesting.jtraining.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Dborisov on 10.06.2016.
 */
public class AddToGroupTests extends TestBase {

  @BeforeMethod
  public  void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Alexei").withEmail("barancev@gmail.com"));
    }

  }

  @Test
  public void testAddToGroup() {
  // Выбираем контакт
    Contacts contacts = app.db().contacts();
    ContactData selectedContact = contacts.iterator().next();
  // Выбираем все группы контакта
    Groups before = selectedContact.getGroups();
  // Выбираем свободную группу, в которую абонент еще не входит
    Groups freeGroups = app.db().freeGroups(before.allIdAsString());
  // Если свободных групп нет - создаем ее
    if (freeGroups.size() == 0) {
      String testGroupName = app.db().uniqueGroupName();
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(testGroupName));
      freeGroups = app.db().freeGroups(before.allIdAsString());
    }
    GroupData selectedGroup = freeGroups.iterator().next();
  // Добавляем контакт в группу
    app.contact().addToGroup(selectedContact, selectedGroup);
  // Вновь выбираем все группы контакта из базы
  // Цинично... с помощью SQL  :)
    Groups after = app.db().getContactGroups(selectedContact);
  // Момент истины
    assertThat(after, equalTo(before.withAdded(selectedGroup)));
  }


}
