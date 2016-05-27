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
    type(By.name("address"), shortContactData.getAddress());
    type(By.name("email"), shortContactData.getEmail());
    type(By.name("email2"), shortContactData.getEmail2());
    type(By.name("email3"), shortContactData.getEmail3());
    type(By.name("home"), shortContactData.getHomePhone());
    type(By.name("mobile"), shortContactData.getMobilePhone());
    type(By.name("work"), shortContactData.getWorkPhone());
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

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void updateContactInfo() {
    click(By.name("update"));
  }

  public void create(ShortContactData shortContact) {
    app.goTo().gotoEditAddContactPage();
    fillContactForm(shortContact, true);
    submitContactInfo();
    contactCache = null;
    app.goTo().homePage();

  }

  public void modify(ShortContactData contact) {
    app.contact().initContactModificationById(contact.getId());
    app.contact().fillContactForm(contact, false);
    app.contact().updateContactInfo();
    contactCache = null;
    app.goTo().homePage();
  }

  public void delete(ShortContactData contact) {
    selectContactById(contact.getId());
    click(By.cssSelector("input[value='Delete']"));
    wd.switchTo().alert().accept();
    contactCache = null;
    app.goTo().homePage();
  }

  public ShortContactData infoFromEditForm(ShortContactData contact) {
    app.contact().initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ShortContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withAddress(address)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

//  public boolean isThereAContact() {
//    return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
//  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      ShortContactData contact = new ShortContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address)
              .withAllEmails(allEmails).withAllPhones(allPhones);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public void createContactWithTestData() {
    app.contact().create(new ShortContactData().withFirstname("Александр").withLastname("Соколов")
            .withAddress("г. Тверь ул. Желябова 36 кв. 12")
            .withEmail("email@mail.ru").withEmail2("email2@mail.ru").withEmail3("email3@mail.ru")
            .withHomePhone("+7(4822)261299").withMobilePhone("+7(920)9213355").withWorkPhone("+7(4822)835554"));
  }

}
