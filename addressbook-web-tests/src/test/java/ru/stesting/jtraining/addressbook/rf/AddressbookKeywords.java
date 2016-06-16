package ru.stesting.jtraining.addressbook.rf;

import org.openqa.selenium.remote.BrowserType;
import ru.stesting.jtraining.addressbook.appmanager.ApplicationManager;
import ru.stesting.jtraining.addressbook.model.GroupData;

import java.io.IOException;

/**
 * Created by DBorisov on 16.06.2016.
 */
public class AddressbookKeywords {

  public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

  private ApplicationManager app;

  public void initApplicationManager() throws IOException {
    app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    app.init();
  }

  public void stopApplicationManager() {
    app.stop();
    app = null;
  }

  public int getGroupCount() {
    app.goTo().groupPage();
    return app.group().count();
  }

  public void createGroup(String name, String header, String footer) {
    app.goTo().groupPage();
    app.group().create(new GroupData().withName(name).withHeader(header).withFooter(footer));
  }
}
