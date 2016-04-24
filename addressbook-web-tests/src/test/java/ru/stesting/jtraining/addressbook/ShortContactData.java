package ru.stesting.jtraining.addressbook;

public class ShortContactData {
  private final String firstname;
  private final String lastname;
  private final String email;

  public ShortContactData(String firstname, String lastname, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
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
}
