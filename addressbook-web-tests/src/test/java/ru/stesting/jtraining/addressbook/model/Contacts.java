package ru.stesting.jtraining.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by DBorisov on 21.05.2016.
 */
public class Contacts extends ForwardingSet<ShortContactData> {

  private Set<ShortContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ShortContactData>(contacts.delegate());
  }

  public Contacts() {
    this.delegate = new HashSet<ShortContactData>();
  }

  public Contacts withAdded(ShortContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ShortContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
  @Override
  protected Set<ShortContactData> delegate() {
    return delegate;
  }
}

