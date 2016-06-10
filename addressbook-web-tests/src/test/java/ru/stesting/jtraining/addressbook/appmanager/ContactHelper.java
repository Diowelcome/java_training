package ru.stesting.jtraining.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stesting.jtraining.addressbook.model.ContactData;
import ru.stesting.jtraining.addressbook.model.Contacts;

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

  public void fillContactForm(ContactData ContactData, boolean creation) {
    type(By.name("firstname"), ContactData.getFirstname());
    type(By.name("middlename"), ContactData.getMiddlename());
    type(By.name("lastname"), ContactData.getLastname());
    type(By.name("nickname"), ContactData.getNickname());
    type(By.name("title"), ContactData.getTitle());
    type(By.name("company"), ContactData.getCompany());
    type(By.name("address"), ContactData.getAddress());
    type(By.name("home"), ContactData.getHomePhone());
    type(By.name("mobile"), ContactData.getMobilePhone());
    type(By.name("work"), ContactData.getWorkPhone());
    type(By.name("fax"), ContactData.getFax());
    type(By.name("email"), ContactData.getEmail());
    type(By.name("email2"), ContactData.getEmail2());
    type(By.name("email3"), ContactData.getEmail3());
    type(By.name("homepage"), ContactData.getHomePhone());
    if (ContactData.getBday() != null && ! ContactData.getBday().equals("0")) {
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(ContactData.getBday());
    }
    if (ContactData.getBmonth() != null) {
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(ContactData.getBmonth());
    }
    type(By.name("byear"), ContactData.getByear());
    if (ContactData.getAday() != null && ! ContactData.getAday().equals("0")) {
      new Select(wd.findElement(By.name("aday"))).selectByVisibleText(ContactData.getAday());
    }
    if (ContactData.getAmonth() != null) {
      new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(ContactData.getAmonth());
    }
    type(By.name("ayear"), ContactData.getAyear());
    type(By.name("address2"), ContactData.getAddress2());
    type(By.name("phone2"), ContactData.getPhone2());
    type(By.name("notes"), ContactData.getNotes());

    attach(By.name("photo"), ContactData.getPhoto());
    if (ContactData.getGroup() != null) {
      if (creation) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(ContactData.getGroup());
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

  public void openDetailsFormById(int id) {
    wd.findElement(By.cssSelector("a[href='view.php?id=" + id + "']")).click();
  }

  public void updateContactInfo() {
    click(By.name("update"));
  }

  public void create(ContactData shortContact) {
    app.goTo().gotoEditAddContactPage();
    fillContactForm(shortContact, true);
    submitContactInfo();
    contactCache = null;
    app.goTo().homePage();

  }

  public void modify(ContactData contact) {
    app.contact().initContactModificationById(contact.getId());
    app.contact().fillContactForm(contact, false);
    app.contact().updateContactInfo();
    contactCache = null;
    app.goTo().homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    click(By.cssSelector("input[value='Delete']"));
    wd.switchTo().alert().accept();
    contactCache = null;
    app.goTo().homePage();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    app.contact().initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String nickname = wd.findElement(By.name("nickname")).getAttribute("value");
    String title = wd.findElement(By.name("title")).getAttribute("value");
    String company = wd.findElement(By.name("company")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String fax = wd.findElement(By.name("fax")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String homepage = wd.findElement(By.name("homepage")).getAttribute("value");
    String bday = wd.findElement(By.name("bday")).getAttribute("value");
    String bmonth = wd.findElement(By.name("bmonth")).getAttribute("value");
    String byear = wd.findElement(By.name("byear")).getAttribute("value");
    String aday = wd.findElement(By.name("aday")).getAttribute("value");
    String amonth = wd.findElement(By.name("amonth")).getAttribute("value");
    String ayear = wd.findElement(By.name("ayear")).getAttribute("value");
    String address2 = wd.findElement(By.name("address2")).getAttribute("value");
    String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
    String notes = wd.findElement(By.name("notes")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withMiddlename(middlename).withLastname(lastname)
            .withNickname(nickname).withTitle(title).withCompany(company)
            .withAddress(address)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withFax(fax)
            .withEmail(email).withEmail2(email2).withEmail3(email3).withHomePage(homepage)
            .withBday(bday).withBmonth(bmonth).withByear(byear)
            .withAday(aday).withAmonth(amonth).withAyear(ayear)
            .withAddress2(address2).withPhone2(phone2).withNotes(notes);
  }

  public String infoFromDetailsFormAsString(ContactData contact) {
    app.contact().openDetailsFormById(contact.getId());
    String text = wd.findElement(By.cssSelector("div[id='content']")).getText();
    // Убираем лишнее \n от фото, поскольку в EditForm данная информация отсутствует
    if (isElementPresent(By.xpath("//div[@id='content']/img"))) {
      String text1 = text.substring(text.indexOf("\n") + 1);
      String text2 = text1.substring(text1.indexOf("\n") + 1);
      text = text.substring(0, text.indexOf(text2) - 1) + text2;
    }
    return text;
  }

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
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address)
              .withAllEmails(allEmails).withAllPhones(allPhones);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public void createContactWithTestData() {
    app.contact().create(new ContactData().withFirstname("Александр").withLastname("Соколов")
            .withAddress("г. Тверь ул. Желябова 36 кв. 12")
            .withEmail("email@mail.ru").withEmail2("email2@mail.ru").withEmail3("email3@mail.ru")
            .withHomePhone("+7(4822)261299").withMobilePhone("+7(920)9213355").withWorkPhone("+7(4822)835554"));
  }

}
