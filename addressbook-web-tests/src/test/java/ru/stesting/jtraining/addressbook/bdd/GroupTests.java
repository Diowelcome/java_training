package ru.stesting.jtraining.addressbook.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by Dborisov on 17.06.2016.
 */

@CucumberOptions(features = "classpath:bdd", plugin = {"pretty", "html:build/cucumber-report"})
public class GroupTests extends AbstractTestNGCucumberTests {
}
