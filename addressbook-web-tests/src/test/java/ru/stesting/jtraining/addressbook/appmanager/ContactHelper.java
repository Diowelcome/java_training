package ru.stesting.jtraining.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stesting.jtraining.addressbook.model.ShortContactData;

/**
 * Created by Dborisov on 26.04.2016.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(ApplicationManager app) {
    super(app);
  }

  public void submitContactInfo() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ShortContactData shortContactData, boolean creation) {
    type(By.name("firstname"), shortContactData.getFirstname());
    type(By.name("lastname"), shortContactData.getLastname());
    type(By.name("email"), shortContactData.getEmail());
    if (ShortContactData.getGroup() != null) {
      if (creation) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(ShortContactData.getGroup());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }
  }

  public void selectContact() {
    if (!isSelected(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"))) {
      click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }
  }

  public void deleteContact() {
    if (isSelected(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"))) {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
      wd.switchTo().alert().accept();
    }
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void updateContactInfo() {
    click(By.name("update"));
  }

  public void createContact(ShortContactData shortContact) {
    app.getNavigationHelper().gotoEditAddContactPage();
    fillContactForm(shortContact, true);
    submitContactInfo();
    app.getNavigationHelper().gotoHomePage();

  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
  }
}
