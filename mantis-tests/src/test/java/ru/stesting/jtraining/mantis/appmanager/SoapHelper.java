package ru.stesting.jtraining.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stesting.jtraining.mantis.model.Issue;
import ru.stesting.jtraining.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by DBorisov on 13.06.2016.
 */
public class SoapHelper {

  private ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");

    return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
              .getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
    IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                      .withName(createdIssueData.getProject().getName()));
  }

  public int addProject(String name) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String username = app.getProperty("web.adminLogin");
    String password = app.getProperty("web.adminPassword");
    ProjectData project = new ProjectData();
    project.setName(name);
    int id = mc.mc_project_add(username, password, project).intValue();
    return id;
  }

  public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String username = app.getProperty("web.adminLogin");
    String password = app.getProperty("web.adminPassword");
    // Получаем Issue
    if (mc.mc_issue_exists(username, password, BigInteger.valueOf(issueId))) {
        IssueData createdIssueData = mc.mc_issue_get(username, password, BigInteger.valueOf(issueId));
        String bugStatus = createdIssueData.getStatus().getName();
        if ( ! bugStatus.equals("closed")) {
          return true;
        }
     }
    return false;
  }
}
