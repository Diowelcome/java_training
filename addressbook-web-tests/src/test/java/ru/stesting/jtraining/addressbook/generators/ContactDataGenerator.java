package ru.stesting.jtraining.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stesting.jtraining.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by DBorisov on 05.06.2016.
 */
public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  final Random random = new Random();

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unknown format " + format);
    }

  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<ContactData> groups, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(groups);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> groups = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      String firstname = generateFirstName();
      String lastname = generateLastname();
      groups.add(new ContactData().withFirstname(firstname).withLastname(lastname)
              .withEmail(generateEmail(firstname, lastname)));
    }
    return groups;

  }

  private String generateEmail(String firstname, String lastname) {
    String[] host = {"email.ru", "gmail.com", "yandex.ru"};
    return String.format("%s.%s@%s", firstname, lastname, host[random.nextInt(host.length)]);
  }

  private String generateLastname() {
    String[] lastnames = {"Ivanov", "Petrov", "Sidorov", "Pupkin", "Sokolov", "Vasiliev"};
    return lastnames[random.nextInt(lastnames.length)];
  }

  private String generateFirstName() {
    String[] firstnames = {"Ivan", "Petr", "Alexei", "Foma", "Alexander", "Dmitry", "Konstantin", "Victor", "Andrey"};
    return firstnames[random.nextInt(firstnames.length)];
  }
}
