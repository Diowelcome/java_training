package ru.stesting.jtraining.addressbook.tests;

import org.testng.annotations.Test;
import ru.stesting.jtraining.addressbook.model.GroupData;
import ru.stesting.jtraining.addressbook.model.ShortContactData;

/**
 * Created by Dborisov on 26.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(app.getNavigationHelper(), new ShortContactData("Alexei", null, "barancev@gmail.com", null));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    // Понятно, что не надо, но... для порядка
    app.getNavigationHelper().gotoHomePage();
  }
}
