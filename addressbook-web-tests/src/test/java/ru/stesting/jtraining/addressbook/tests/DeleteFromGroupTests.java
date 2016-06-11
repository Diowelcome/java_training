package ru.stesting.jtraining.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stesting.jtraining.addressbook.model.ContactData;
import ru.stesting.jtraining.addressbook.model.Contacts;
import ru.stesting.jtraining.addressbook.model.GroupData;
import ru.stesting.jtraining.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stesting.jtraining.addressbook.tests.TestBase.app;

/**
 * Created by DBorisov on 10.06.2016.
 */
public class DeleteFromGroupTests extends TestBase {


  @BeforeMethod
  public  void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Alexei").withEmail("barancev@gmail.com"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(app.db().uniqueGroupName()));
    }
  }

  @Test
  public void testDeleteFromGroup() {
    // Выбираем контакт
    Contacts contacts = app.db().contacts();
    ContactData selectedContact = contacts.iterator().next();
    // Выбираем все группы контакта
    Groups before = selectedContact.getGroups();
    GroupData deletedGroup;
    // Если у контакта групп нет - включаем его в группу
    if (before.size() == 0) {
      Groups group = app.db().groups();
      deletedGroup = group.iterator().next();
      app.contact().addToGroup(selectedContact, deletedGroup);
    } else {
      deletedGroup = before.iterator().next();
    }
    // Удаляем контакт из группы
    app.contact().deleteFromGroup(selectedContact, deletedGroup);
    // Вновь выбираем все группы контакта из базы
    // Цинично... с помощью SQL  :)
    Groups after = app.db().getContactGroups(selectedContact);
    // Момент истины
    assertThat(after, equalTo(before.without(deletedGroup)));
  }


}
