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

  public void selectContact() {
    // Пока пытаемся выбрать первую ("гарантированную") запись
    // Ошибка отсутствия записи пока не обрабатывается
    if (!isSelected(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"))) {
      click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }
  }

  public void deleteContact() {
    // Аналогично: пытаемся удалить первую запись
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
}
