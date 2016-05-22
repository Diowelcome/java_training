package ru.stesting.jtraining.addressbook.tests;

import org.testng.annotations.Test;
import ru.stesting.jtraining.addressbook.model.Contacts;
import ru.stesting.jtraining.addressbook.model.ShortContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ShortContactData contact = new ShortContactData().withFirstname("Alexei").withLastname("Barancev").withEmail("barancev@gmail.com");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
