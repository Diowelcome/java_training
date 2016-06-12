package ru.stesting.jtraining.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by DBorisov on 12.06.2016.
 */
public class SessionHelper extends HelperBase {

  public SessionHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }
}
