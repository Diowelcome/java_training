package ru.stesting.jtraining.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stesting.jtraining.addressbook.model.ShortContactData;

import java.util.List;

/**
 * Created by Dborisov on 26.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test(enabled = false)
  public void testContactDeletion() {
    app.goTo().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ShortContactData("Alexei", null, "barancev@gmail.com", null));
    }
    List<ShortContactData> before = app.getContactHelper().getShortContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContact();
    app.goTo().gotoHomePage();
    List<ShortContactData> after = app.getContactHelper().getShortContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
