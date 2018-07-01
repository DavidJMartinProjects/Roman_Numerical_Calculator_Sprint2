package com.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="feature/Calculator.feature",glue={"com.cucumber.stepDefinition"})		


public class TestRunner {
}
