package ru.stesting.jtraining.mantis.tests;

import org.testng.annotations.Test;
import ru.stesting.jtraining.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.*;

/**
 * Created by DBorisov on 11.06.2016.
 */
public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
