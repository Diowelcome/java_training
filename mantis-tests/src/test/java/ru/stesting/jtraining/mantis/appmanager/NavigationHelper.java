package ru.stesting.jtraining.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by DBorisov on 24.04.2016.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void manageUsers() {
    click(By.linkText("Manage Users"));
  }

}
