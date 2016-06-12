package ru.stesting.jtraining.mantis.model;

/**
 * Created by DBorisov on 12.06.2016.
 */
import javax.persistence.*;

@Entity
@Table(name = "mantis_user_table")
public class UserData {
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "username")
  private String username;
  @Column(name = "email")
  private String email;

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }
}
