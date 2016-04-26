package ru.stesting.jtraining.addressbook.tests;

import org.testng.annotations.Test;
import ru.stesting.jtraining.addressbook.model.ShortContactData;

/**
 * Created by Dborisov on 26.04.2016.
 */
public class ContractModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    // Ошибка отсутствия редактируемых записей
    // пока не обрабатывается
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ShortContactData("Alexei_1", "Barancev_1", "barancev_1@gmail.com"));
    app.getContactHelper().updateContactInfo();
    app.getNavigationHelper().gotoHomePage();
  }
}