package ru.stesting.jtraining.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Dborisov on 26.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    // По аналогии с тестом удаления группы
    // отсутствие записей в тесте пока не обрабатывается
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    // Понятно, что не надо, но... для порядка
    app.getNavigationHelper().gotoHomePage();
  }
}
