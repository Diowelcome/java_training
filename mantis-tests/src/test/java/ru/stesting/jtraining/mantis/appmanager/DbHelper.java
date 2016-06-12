package ru.stesting.jtraining.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stesting.jtraining.mantis.model.UserData;
import ru.stesting.jtraining.mantis.model.Users;


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

  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData where username <> 'administrator' and enabled = 1" ).list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

}
