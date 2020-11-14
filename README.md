[![Build Status](https://dev.azure.com/aquality-automation/aquality-automation/_apis/build/status/aquality-automation.aquality-selenium-java-template?branchName=master)](https://dev.azure.com/aquality-automation/aquality-automation/_build/latest?definitionId=9&branchName=master)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=aquality-automation_aquality-selenium-java-template&metric=alert_status)](https://sonarcloud.io/dashboard?id=aquality-automation_aquality-selenium-java-template)

# Aquality Selenium Template Project
Template for [aquality-selenium-java](https://github.com/aquality-automation/aquality-selenium-java) library.

### Project structure
- **aquality-selenium-template** - project related part with PageObjects, models and utilities
  - **configuration/**: classes that used to fetch project config from [src/main/resources/environment](https://github.com/aquality-automation/aquality-selenium-java-template/blob/master/aquality-selenium-template/src/main/resources/environment) folder
  - **forms/**: Page Objects
  - **models/**: classes that represent data models of the application under the test (POJO classes) 
  - **utilities/**: util classes
  - **src/main/resources/**: resource files such as configurations and test data
- **aquality-selenium-template-cucumber** - Cucumber implementation of the tests
  - **features/**: Gherkin feature files with test scenarios
  - **hooks/**: Cucumber [hooks](https://cucumber.io/docs/cucumber/api/#hooks)
  - **runners/**: Cucumber test runners
  - **stepdefinitions/**: step definition classes
  - **transformations/**: Cucumber [data transformations](https://cucumber.io/docs/cucumber/configuration/)

### Configuration
[settings.json](https://github.com/aquality-automation/aquality-selenium-java-template/blob/master/aquality-selenium-template/src/main/resources/settings.json) file contains settings of Aquality Selenium library. Additional information you can find [here](https://github.com/aquality-automation/aquality-selenium-java/wiki/Overview-(English)).

[allure.properties](https://github.com/aquality-automation/aquality-selenium-java-template/blob/master/aquality-selenium-template/src/main/resources/allure.properties) is a part of Allure Report configuration. See details [here](https://docs.qameta.io/allure/).   

### Tests execution
Scenarios from feature files can be executed with TestNG plugin for IDE (Intellij Idea, Eclipse)
or with Maven command ```mvn clean test``` where you can specify all necessary arguments.

If executed with Maven, tests will be run in ```4``` threads. To change the amount of threads add ```-Ddata.provider.thread.count``` property to the command.
E.g. ```mvn clean test -Ddata.provider.thread.count=10```.

### Reporting 
Allure Framework is used as a reporting tool. Report data will be placed in ```target/allure-results/``` folder (you can change it in ```allure.properties``` file).

Run maven command ```mvn allure:serve``` to build and open report in web browser. To generate report in CI use corresponding plugin for your system.

### License
Library's source code is made available under the [Apache 2.0 license](https://github.com/aquality-automation/aquality-selenium-java-template/blob/master/LICENSE).
