[![Build Status](https://dev.azure.com/aquality-automation/aquality-automation/_apis/build/status/aquality-automation.aquality-selenium-java-template?branchName=master)](https://dev.azure.com/aquality-automation/aquality-automation/_build/latest?definitionId=9&branchName=master)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=aquality-automation_aquality-selenium-java-template&metric=alert_status)](https://sonarcloud.io/dashboard?id=aquality-automation_aquality-selenium-java-template)

# Aquality Selenium Template Project

Template for [aquality-selenium-java](https://github.com/aquality-automation/aquality-selenium-java) library.

### Project structure

- **src/test/java/aquality/selenium/template/**
  - **configuration/**: classes that used to fetch project config from [src/test/resources/environment](./src/test/resources/environment) folder
  - **models/**: classes that represent data models of the application under the test (POJO classes) 
  - **forms/**: Page Objects
  - **glue/**
    - **hooks/**: Cucumber [hooks](https://cucumber.io/docs/cucumber/api/#hooks)
    - **stepdefinitions/**: step definition classes
    - **transformations/**: Cucumber [data transformations](https://cucumber.io/docs/cucumber/configuration/)
  - **features/**: Cucumber feature files with test scenarios
  - **runners/**: Cucumber test runners
- **src/test/resources/**: resource files such as configurations and test data

### Configuration

[settings.json](./src/test/resources/settings.json) file contains settings of Aquality Selenium library. Additional information you can find [here](https://github.com/aquality-automation/aquality-selenium-java/blob/master/Documentation.en.md).

[allure.properties](./src/test/resources/allure.properties) is a part of Allure Report configuration. See details [here](https://docs.qameta.io/allure/).   

### Tests execution

Scenarios from feature files can be executed with TestNG plugin for IDE (Intellij Idea, Eclipse)
or with Maven command ```mvn clean test``` where you can specify all necessary arguments.

### Reporting 

Allure Framework is used as a reporting tool. Report data will be places in ```target/allure-results/``` folder (you can change it in [allure.properties](./src/test/resources/allure.properties) file).

Run maven command ```mvn allure:serve``` to build and open report in web browser.