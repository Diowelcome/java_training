package ru.stesting.jtraining.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by DBorisov on 17.04.2016.
 */
public class SquareTests {

  @Test
  public void testArea() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 20.0);
  }

}
