package ru.stesting.jtraining.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by DBorisov on 11.06.2016.
 */
public class RegistrationHelper {

  private final ApplicationManager app;
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    wd = app.getDriver();
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
  }
}
