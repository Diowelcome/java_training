package ru.stesting.jtraining.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by DBorisov on 21.05.2016.
 */
public class Groups extends ForwardingSet<GroupData> {

  private Set<GroupData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupData>(groups.delegate());
  }

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  public Groups(Collection<GroupData> groups) {
    this.delegate = new HashSet<GroupData>(groups);
  }

  @Override
  protected Set delegate() {
    return delegate;
  }

  public Groups withAdded(GroupData group) {
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups without(GroupData group) {
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }

  public String allIdAsString() {
    // Получает строку идентификаторов групп через запятую
    Groups groups = new Groups(this);
    String resultIds = "";
    for (GroupData group: groups) {
      resultIds = resultIds + ", " + group.getId();
    }
    if (resultIds.length() > 0) {
      resultIds = resultIds.substring(2);
    }
    return resultIds;
  }
}

