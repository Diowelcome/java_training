package ru.stesting.jtraining.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by DBorisov on 11.06.2016.
 */
public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() {
    app.registration().start("user1", "user1@localhost.localdomain");
  }
}
