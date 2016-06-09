package ru.stesting.jtraining.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id;

  @XStreamOmitField
  @Transient
  private static String group;

  @Expose
  @Column(name = "firstname")
  private String firstname;
  @XStreamOmitField
  @Column(name = "middlename")
  private String middlename;
  @Expose
  @Column(name = "lastname")
  private String lastname;
  @XStreamOmitField
  @Column(name = "nickname")
  private String nickname;

  @XStreamOmitField
  @Column(name = "title")
  private String title;

  @XStreamOmitField
  @Column(name = "company")
  private String company;

  @XStreamOmitField
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @XStreamOmitField
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;
  @XStreamOmitField
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;
  @XStreamOmitField
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @XStreamOmitField
  @Transient
  private String allPhones;

  @XStreamOmitField
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @XStreamOmitField
  @Column(name = "fax")
  @Type(type = "text")
  private String fax;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;
  @XStreamOmitField
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;
  @XStreamOmitField
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @XStreamOmitField
  @Transient
  private String allEmails;

  @XStreamOmitField
  @Column(name = "homePage")
  @Type(type = "text")
  private String homePage;

  @XStreamOmitField
  @Column(name = "bday")
  private Byte bday;
  @XStreamOmitField
  @Column(name = "bmonth")
  private String bmonth;
  @XStreamOmitField
  @Column(name = "byear")
  private String byear;

  @XStreamOmitField
  @Column(name = "aday")
  private byte aday;
  @XStreamOmitField
  @Column(name = "amonth")
  private String amonth;
  @XStreamOmitField
  @Column(name = "ayear")
  private String ayear;

  @XStreamOmitField
  @Column(name = "address2")
  @Type(type = "text")
  private String address2;
  @XStreamOmitField
  @Column(name = "phone2")
  @Type(type = "text")
  private String phone2;
  @XStreamOmitField
  @Column(name = "notes")
  @Type(type = "text")
  private String notes;


  public String formatEmail(String vEmail) {
    if (!vEmail.equals("")) {
      String host = vEmail.substring(vEmail.indexOf("@") + 1);
      if (!host.equals("")) {
        return vEmail + " (www." + host + ")";
      }
      return vEmail;
    }
    return "";
  }

  private String formatMemo(String memoString) {
    return memoString.replaceAll("\\s\\s+", " ").replaceAll("\\n$", "");
  }

  private String formatDate(String title, String day, String month, String year) {
    if (!(day.equals("0") && month.equals("-") && year.equals(""))) {
      return String.format("%s %s %s %s %s", title, formatDay(day), formatMonth(month), year, getYearDiff(year)).replaceAll("\\s\\s+", " ");
    }
    return "";
  }

  private String formatMonth(String month) {
    if (!month.equals("-")) {
      return month.substring(0, 1).toUpperCase() + month.substring(1);
    }
    return "";
  }

  private String formatDay(String day) {
    if (!day.equals("0")) {
      return day + ".";
    }
    return "";
  }

  private String formatPhone(String prefix, String phone) {
    if (!phone.equals("")) {
      return String.format("%s: %s", prefix, phone);
    }
    return "";
  }

  private String formatHomePage(String webPage) {
    if (!webPage.equals("")) {
      // Все остальные варианты не отсекаются - даже большие буквы HTTP
      if (webPage.indexOf("http://") > -1) {
        return "Homepage:\n" + webPage.substring(7);
      }
      if (webPage.indexOf("https://") > -1) {
        return "Homepage:\n" + webPage.substring(8);
      }
      return "Homepage:\n" + webPage;
    }
    return "";
  }

  private String checkSkipLine(String s1, String s2, String s3, String s4) {
    if (!(s1.equals("") && s2.equals("") && s3.equals("") && s4.equals(""))) {
      return "$skipLine$";
    }
    return "";
  }

  public String getYearDiff(String year) {
    if (!year.equals("")) {
      Calendar c = new GregorianCalendar();
      return "(" + String.valueOf(c.get(Calendar.YEAR) - Integer.valueOf(year)) + ")";
    }
    return "";
  }

  public String asString() {

    String allNames = Arrays.asList(firstname, middlename, lastname).stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining(" "));

    String detailInfoAsString = Arrays.asList(allNames,
            nickname,
            // Вопрос с пустой строкой для фото решается в методе для DetailsForm, поскольку при редактировании
            // отсутствует информация о загруженном файле
            title,
            company,
            formatMemo(address),
            // Пустая строка
            checkSkipLine(homePhone, mobilePhone, workPhone, fax),
            formatPhone("H", homePhone),
            formatPhone("M", mobilePhone),
            formatPhone("W", workPhone),
            formatPhone("F", fax),
            // Пустая строка
            checkSkipLine(email, email2, email3, homePage),
            formatEmail(email),
            formatEmail(email2),
            formatEmail(email3),
            formatHomePage(homePage),
            // Пустая строка
            checkSkipLine(formatDate("Birthday", getBday(), bmonth, byear), formatDate("Anniversary", getAday(), amonth, ayear), "", ""),
            formatDate("Birthday", getBday(), bmonth, byear),
            formatDate("Anniversary", getAday(), amonth, ayear),
            // Пустая строка
            checkSkipLine(address2, "", "", ""),
            formatMemo(address2),
            // Пустая строка
            checkSkipLine(phone2, "", "", ""),
            formatPhone("P", phone2),
            // Пустая строка
            checkSkipLine(notes, "", "", ""),
            formatMemo(notes)
    ).stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n")
    ).replace("$skipLine$", "");

    return detailInfoAsString;

  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email) {
    this.email2 = email;
    return this;
  }

  public ContactData withEmail3(String email) {
    this.email3 = email;
    return this;
  }

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getTitle() {
    return title;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public String getNickname() {
    return nickname;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public String getCompany() {
    return company;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public String getFax() {
    return fax;
  }

  public ContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public String getHomePage() {
    return homePage;
  }

  public ContactData withHomePage(String homePage) {
    this.homePage = homePage;
    return this;
  }

  public String getBday() {
    return Integer.toString(bday);
  }

  public ContactData withBday(String bday) {
    this.bday = Byte.parseByte(bday);
    return this;
  }

  public String getBmonth() {
    return bmonth;
  }

  public ContactData withBmonth(String bmonth) {
    this.bmonth = bmonth;
    return this;
  }

  public String getByear() {
    return byear;
  }

  public ContactData withByear(String byear) {
    this.byear = byear;
    return this;
  }

  public String getAday() {
    return Integer.toString(aday);
  }

  public ContactData withAday(String aday) {
    this.aday = Byte.parseByte(aday);
    return this;
  }

  public String getAmonth() {
    return amonth;
  }

  public ContactData withAmonth(String amonth) {
    this.amonth = amonth;
    return this;
  }

  public String getAyear() {
    return ayear;
  }

  public ContactData withAyear(String ayear) {
    this.ayear = ayear;
    return this;
  }

  public String getAddress2() {
    return address2;
  }

  public ContactData withAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  public String getPhone2() {
    return phone2;
  }

  public ContactData withPhone2(String phone2) {
    this.phone2 = phone2;
    return this;
  }

  public String getNotes() {
    return notes;
  }

  public ContactData withNotes(String notes) {
    this.notes = notes;
    return this;
  }

  public static String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    return allEmails != null ? allEmails.equals(that.allEmails) : that.allEmails == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (allEmails != null ? allEmails.hashCode() : 0);
    return result;
  }
}
