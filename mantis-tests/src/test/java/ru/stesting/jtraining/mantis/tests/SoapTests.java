package ru.stesting.jtraining.mantis.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;
import ru.stesting.jtraining.mantis.model.Issue;
import ru.stesting.jtraining.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by DBorisov on 13.06.2016.
 */
public class SoapTests extends TestBase {

  @Test(enabled = false)
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    //             НЕ РАБОТАЕТ!!!
    // вызов mc_projects_get_user_accessible приводит к ошибке
    //  faultString: Error Type: SYSTEM NOTICE,
    //  Error Description: Array to string conversion

    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    // Заменяем нерабочий вызов указанием конкретного проекта
    //Set<Project> projects = app.soap().getProjects();
    Project testProject = new Project().withId(5).withName("test");
    Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test issue description")
            //.withProject(projects.iterator().next());
            .withProject(testProject);
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test
  public void testCreateProject() throws RemoteException, ServiceException, MalformedURLException {
    int projectId = app.soap().addProject("test");
    System.out.println("Создан проект с идентификатором " + String.valueOf(projectId));
  }

  @Test
  public void testAddNote() throws RemoteException, ServiceException, MalformedURLException {
    // На данный тест заведен Issue
    int assosiatedBug = 11;
    try {
      skipIfNotFixed(assosiatedBug);
    //
    // Код теста
    //
    System.out.println("Тест, проверяющий добавление ноты");
    //
    //
    } catch (SkipException e) {
      e.printStackTrace();
    }
  }



}


