package ru.stesting.jtraining.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stesting.jtraining.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by DBorisov on 28.05.2016.
 */
public class ContactDetalisTests extends TestBase {

  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().createContactWithTestData();
    }
  }

  @Test
  public void testContactDetails() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String contactInfoFromDetailsFormAsString = app.contact().infoFromDetailsFormAsString(contact);

    assertThat(contactInfoFromEditForm.asString(), equalTo(truncateGroups(contactInfoFromDetailsFormAsString)));
  }

  private String truncateGroups(String contactInfoAsString) {
    if (contactInfoAsString.indexOf("\n\nMember of:") > 0)  {
      return contactInfoAsString.substring(0, contactInfoAsString.indexOf("\n\nMember of:"));
    }
   return contactInfoAsString;
  }
}
