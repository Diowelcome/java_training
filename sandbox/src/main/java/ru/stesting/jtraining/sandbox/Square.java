package ru.stesting.jtraining.sandbox;

/**
 * Created by Dborisov on 12.04.2016.
 */
public class Square {

  public double l;

  public Square(double len) {
    this.l = len;
  }

  public double area() {
    return this.l * this.l;
  }

}
