package ru.stesting.jtraining.mantis.model;

/**
 * Created by DBorisov on 12.06.2016.
 */
public class MailMessage {

  public String to;
  public String text;

  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;
  }
}
