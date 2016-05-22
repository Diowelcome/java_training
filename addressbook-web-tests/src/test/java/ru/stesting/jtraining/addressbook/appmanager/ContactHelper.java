package ru.stesting.jtraining.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stesting.jtraining.addressbook.model.Contacts;
import ru.stesting.jtraining.addressbook.model.ShortContactData;

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

//  public void selectContact(int index) {
//    wd.findElements(By.name("selected[]")).get(index).click();
//  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initContactModification(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void updateContactInfo() {
    click(By.name("update"));
  }

  public void create(ShortContactData shortContact) {
    app.goTo().gotoEditAddContactPage();
    fillContactForm(shortContact, true);
    submitContactInfo();
    app.goTo().homePage();

  }

  public void modify(ShortContactData contact) {
    app.contact().initContactModification(contact.getId());
    app.contact().fillContactForm(contact, false);
    app.contact().updateContactInfo();
    app.goTo().homePage();
  }

  public void delete(ShortContactData contact) {
    selectContactById(contact.getId());
    click(By.cssSelector("input[value='Delete']"));
    wd.switchTo().alert().accept();
    app.goTo().homePage();
  }

//  public boolean isThereAContact() {
//    return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
//  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("input[name='selected[]']"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.getAttribute("value"));
      String title = element.getAttribute("title").substring(7);
      String firstname = title.substring(1, title.indexOf(' '));
      String lastname = title.substring(title.indexOf(' ') + 1, title.length() - 1);
      String email = element.getAttribute("accept");

      ShortContactData contact = new ShortContactData().withId(id).withFirstname(firstname).withLastname(lastname).withEmail(email);
      contacts.add(contact);
    }
    return contacts;
  }

}
