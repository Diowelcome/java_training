package ru.stesting.jtraining.addressbook.model;

public class ShortContactData {
  private static String group;
  private final String firstname;
  private final String lastname;
  private final String email;

  public ShortContactData(String firstname, String lastname, String email, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.group = group;
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

  public static String getGroup() {
    return group;
  }
}
