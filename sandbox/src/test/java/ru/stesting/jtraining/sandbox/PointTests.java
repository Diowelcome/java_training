package ru.stesting.jtraining.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by DBorisov on 17.04.2016.
 */
public class PointTests {

  @Test
  public void testPoint() {
    Point p1 = new Point(-4, -3);
    Point p2 = new Point( 4,  3);
    Assert.assertEquals(p1.distance(p2), 10.0);
    Assert.assertEquals(p2.distance(p1), 10.0);
    Assert.assertEquals(p1.distance(p2), p2.distance(p1));
  }
}
