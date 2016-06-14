package ru.stesting.jtraining.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;
import ru.stesting.jtraining.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by DBorisov on 13.06.2016.
 */
public class RestTests extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = app.rest().getIssues();
    Issue newIssue = new Issue().withSubject("Temporary subject").withDescription("Description for temporary subject");
    int issueId = app.rest().createIssue(newIssue);
    Set<Issue> newIssues = app.rest().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testBugifyIntegration() throws IOException {
    // На данный тест заведен Issue
    int testIssueId = 1;
    try {
      skipIfNotFixed(testIssueId);
      //
      // Код теста
      //
      System.out.println("Тест: интеграция с Bugify");
      //
      //
    } catch (SkipException e) {
      e.printStackTrace();
    }
  }

}
