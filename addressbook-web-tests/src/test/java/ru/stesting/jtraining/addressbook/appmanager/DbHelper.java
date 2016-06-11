package ru.stesting.jtraining.addressbook.appmanager;

import com.thoughtworks.xstream.converters.ConversionException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stesting.jtraining.addressbook.model.ContactData;
import ru.stesting.jtraining.addressbook.model.Contacts;
import ru.stesting.jtraining.addressbook.model.GroupData;
import ru.stesting.jtraining.addressbook.model.Groups;

import java.sql.*;
import java.util.List;

/**
 * Created by Dborisov on 09.06.2016.
 */
public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();

    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData" ).list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }


  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public Groups freeGroups(String occupiedGroups) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    String selectStatement = "from GroupData where deprecated = '0000-00-00' ";
    if (! occupiedGroups.equals("")) {
      selectStatement = selectStatement + " and group_id not in ( " + occupiedGroups + ")";
    }
    List<GroupData> result = session.createQuery(selectStatement).list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public String uniqueGroupName() {
    // Генерирует уникальное тестовое имя в формате 'Test \d'
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> groups = session.createQuery("from GroupData where deprecated = '0000-00-00' and group_name like 'Test %'").list();
    session.getTransaction().commit();
    session.close();
    int uniqueIndex = 0;
    if (groups.size() > 0) {
      for (GroupData group : groups) {
        String groupName = group.getName().substring(5);
        if (groupName.length() > 0) {
          try {
            if (Integer.parseInt(groupName) > uniqueIndex) {
              uniqueIndex = Integer.parseInt(groupName);
            }
          } catch (NumberFormatException ex) {
            // Если встречается текстовое название или название вида 'Test group 10'- просто ничего не делаем
          }
        }
      }
    }
    return "Test " + String.valueOf(uniqueIndex + 1);
  }

  public Groups getAllGroupsByName(String groupName) {
    // Выбирает все группы с одинаковым названием
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData where deprecated = '0000-00-00' and group_name = '" + groupName + "'").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Groups getContactGroups(ContactData selectedContact) {
    // Выбирает все группы, в которые входит контакт
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs =
              st.executeQuery("SELECT `group_list`.`group_id`, `group_list`.`group_name`, `group_list`.`group_header`, `group_list`.`group_footer` " +
                      "FROM `group_list` JOIN `address_in_groups` ON `group_list`.`group_id` = `address_in_groups`.`group_id` " +
                      "WHERE `address_in_groups`.`deprecated` = '0000-00-00' AND `address_in_groups`.`id` = " + selectedContact.getId());
      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }
      rs.close();
      st.close();
      conn.close();

      return new Groups(groups);
      // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return null;
  }
}
