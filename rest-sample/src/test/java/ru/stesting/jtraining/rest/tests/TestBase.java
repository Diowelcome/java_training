package ru.stesting.jtraining.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stesting.jtraining.rest.appmanager.ApplicationManager;

import java.io.IOException;

//import javax.xml.rpc.ServiceException;

/**
 * Created by DBorisov on 14.06.2016.
 */
public class TestBase {

  protected static final ApplicationManager app
        = new ApplicationManager();

  @BeforeSuite
  public void setUp() {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  public  boolean isIssueOpen(int issueId) throws IOException {
    return app.rest().isIssueOpen(issueId);
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
