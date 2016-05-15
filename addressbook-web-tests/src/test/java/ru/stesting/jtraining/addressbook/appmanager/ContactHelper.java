package ru.stesting.jtraining.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stesting.jtraining.addressbook.model.ShortContactData;

import java.util.ArrayList;
import java.util.List;

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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteContact() {
    click(By.cssSelector("input[value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    List<WebElement> elements = wd.findElements(By.cssSelector("img[title='Edit']"));
        elements.get(index).click();
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

  public List<ShortContactData> getShortContactList() {
    List<ShortContactData> contacts = new ArrayList<ShortContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("input[name='selected[]']"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.getAttribute("value"));
      String title = element.getAttribute("title").substring(7);
      String firstname = title.substring(1, title.indexOf(' '));
      String lastname = title.substring(title.indexOf(' ') + 1, title.length() - 1);
      String email = element.getAttribute("accept");

      ShortContactData contact = new ShortContactData(id, firstname, lastname, email, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
