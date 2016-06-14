package ru.stesting.jtraining.rest.tests;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stesting.jtraining.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by DBorisov on 14.06.2016.
 */
public class RestAssuredTests extends TestBase {

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = app.restA().getIssues();
    Issue newIssue = new Issue().withSubject("Test subject").withDescription("New test issue");
    int issueId = app.restA().createIssue(newIssue);
    Set<Issue> newIssues = app.restA().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }


}
