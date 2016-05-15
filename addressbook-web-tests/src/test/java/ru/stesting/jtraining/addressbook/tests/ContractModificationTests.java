package ru.stesting.jtraining.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stesting.jtraining.addressbook.model.ShortContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Dborisov on 26.04.2016.
 */
public class ContractModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ShortContactData("Alexei", null, "barancev@gmail.com", null));
    }
    List<ShortContactData> before = app.getContactHelper().getShortContactList();
    app.getContactHelper().initContactModification(before.size() - 1);
    ShortContactData contact = new ShortContactData(before.get(before.size() - 1).getId(), "Alexei_1", "Barancev_1", "barancev_1@gmail.com", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().updateContactInfo();
    app.getNavigationHelper().gotoHomePage();
    List<ShortContactData> after = app.getContactHelper().getShortContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ShortContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
