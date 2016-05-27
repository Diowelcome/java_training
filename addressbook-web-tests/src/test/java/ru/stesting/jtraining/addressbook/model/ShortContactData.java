package ru.stesting.jtraining.addressbook.model;

public class ShortContactData {
  private int id;
  private static String group;
  private String firstname;
  private String lastname;

  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String allPhones;

  private String address;

  private String email;
  private String email2;
  private String email3;
  private String allEmails;


  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ShortContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ShortContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public ShortContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
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

  public ShortContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public ShortContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public ShortContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ShortContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ShortContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ShortContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ShortContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ShortContactData withEmail2(String email) {
    this.email2 = email;
    return this;
  }

  public ShortContactData withEmail3(String email) {
    this.email3 = email;
    return this;
  }

  public static String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ShortContactData that = (ShortContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    return email != null ? email.equals(that.email) : that.email == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }

}
