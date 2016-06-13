package ru.stesting.jtraining.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by DBorisov on 13.06.2016.
 */
public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("127.0.0.1");
    assertEquals(geoIP.getCountryCode(), "ZZZ");
  }
}
