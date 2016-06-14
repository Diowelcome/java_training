package ru.stesting.jtraining.rest.appmanager;

/**
 * Created by DBorisov on 14.06.2016.
 */
public class ApplicationManager {

  public void init() {
    System.out.println("Test started");
  }

  public void stop() {
    System.out.println("Test finished");
  }

  public RestHelper rest() {
    return new RestHelper(this);
  }

  public RestAssuredHelper restA() {
    return new RestAssuredHelper(this);
  }

}
