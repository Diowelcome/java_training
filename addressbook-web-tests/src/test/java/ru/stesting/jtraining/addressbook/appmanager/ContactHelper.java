package ru.stesting.jtraining.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stesting.jtraining.addressbook.model.ShortContactData;

/**
 * Created by Dborisov on 26.04.2016.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactInfo() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ShortContactData shortContactData) {
    type(By.name("firstname"), shortContactData.getFirstname());
    type(By.name("lastname"), shortContactData.getLastname());
    type(By.name("email"), shortContactData.getEmail());
  }

}
