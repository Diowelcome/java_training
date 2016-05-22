package ru.stesting.jtraining.addressbook.model;

public class ShortContactData {
  private int id;
  private static String group;
  private String firstname;
  private String lastname;
  private String email;

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
