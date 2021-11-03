package com.ReVibe.stepDefinition;

import org.junit.runner.RunWith;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/Features/LogInFeature.feature", glue = "com.ReVibe.stepDefinition")
public class CucmberTestRunner {
	
}
