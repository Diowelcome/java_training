package ru.stesting.jtraining.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import ru.stesting.jtraining.addressbook.model.ShortContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoEditAddContactPage();
    app.getContactHelper().fillContactForm(new ShortContactData("Alexei", "Barancev", "barancev@gmail.com"));
    app.getContactHelper().submitContactInfo();
    app.getNavigationHelper().returnToHomePage();
  }

}
