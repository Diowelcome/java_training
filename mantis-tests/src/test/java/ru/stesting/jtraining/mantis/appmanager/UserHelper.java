package ru.stesting.jtraining.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stesting.jtraining.mantis.model.UserData;

/**
 * Created by DBorisov on 12.06.2016.
 */
public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void changePassword(UserData selectedUser) {
    click(By.linkText(selectedUser.getUsername()));
    click(By.cssSelector("input[value='Reset Password']"));
  }
}
