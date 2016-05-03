package ru.stesting.jtraining.addressbook.tests;

import org.testng.annotations.Test;
import ru.stesting.jtraining.addressbook.model.ShortContactData;

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
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ShortContactData("Alexei_1", "Barancev_1", "barancev_1@gmail.com", null), false);
    app.getContactHelper().updateContactInfo();
    app.getNavigationHelper().gotoHomePage();
  }
}
