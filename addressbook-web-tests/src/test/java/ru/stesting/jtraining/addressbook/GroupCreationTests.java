package ru.stesting.jtraining.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Test group", "Group header", "Group header"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
