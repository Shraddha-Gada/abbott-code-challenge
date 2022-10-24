Software Development Engineer in Testing Code Challenge

# Problem statement
Write test cases to cover below system requirement and automate the test cases. Automation
requirement are on the next page.
System Requirement Specification
Here is an SRS (System Requirement Specification) for the Header of LibreView Support & Marketing
website https://pat.libreview.io/.

• The header displays the following links:
o Patients (hidden for France)
o Professionals (hidden for France)

Test Case Requirements
• Only need to cover below country + language combinations
o United States + English
o France + French

## Solution:
Selecting the Option-2, ie:

Web Automation + Python:
1) Successfully complete UI automation with any test framework or programing language.

2) Write a small Python script to fetch data from API and output the data
a) Get data from URL:
https://datausa.io/api/data?drilldowns=Nation&measures=Population
b) Process response and output in below format:
According to _____, in _ years from 20__ to 20__, peak population growth was __% in
20__ and lowest population increase was __% in 20__.

# Part-1:

WorkFlow implemented to achieve Part-1:
UI Automation using Selenium-Java on TestNG, POM framework
##### Step-1: 
Create a Maven Structured framework.

##### Step-2:
Configure the following dependencies using pom.xml(Maven):

- *Selenium-Java*
- *TestNG*
- *WebDriverManager*
- *ExtentReports*
- *JSON-simple*

Maven automatically downloads the necessary files from the repository while building the project.

##### Step-3:
Create a [globalVar.properties](https://github.com/Shraddha-Gada/abbott-code-challenge/blob/main/sdet-code-challenge/src/main/java/abbott/sdet_code_challenge/resources/globalVar.properties) file for storing browser config details and other properties which can be used for keyword-driven testing.

##### Step-4:

Create a utility class [DriverInitializer.java](https://github.com/Shraddha-Gada/abbott-code-challenge/blob/main/sdet-code-challenge/src/main/java/abbott/sdet_code_challenge/utilities/DriverInitializer.java) where driver is initialized and the browser is launched.


##### Step-5:

Create an Object Repository for web UI element by implementing Page Object Model design pattern

Under this model, for each web page in the application, there should be a corresponding Page Class. This Page class will identify the WebElements of that web page and also contains Page methods which perform operations on those WebElements. 

- [LandingPage.java](https://github.com/Shraddha-Gada/abbott-code-challenge/blob/main/sdet-code-challenge/src/main/java/abbott/sdet_code_challenge/pages/LandingPage.java) is the page class where webelements are created
- [LandingPageTest.java](https://github.com/Shraddha-Gada/abbott-code-challenge/blob/main/sdet-code-challenge/src/test/java/abbott/sdet_code_challenge/tests/LandingPageTest.java) is correspoding test class where actual test cases are written

##### Step-6:
Create a [testng.xml](https://github.com/Shraddha-Gada/abbott-code-challenge/blob/main/sdet-code-challenge/testng.xml) runner file to trigger the tests with single point of execution control.

##### Step-7:
Implement TestNG Listeners and integrate ExtentReports for logging and customizing report.
- [Listeners.java](https://github.com/Shraddha-Gada/abbott-code-challenge/blob/main/sdet-code-challenge/src/main/java/abbott/sdet_code_challenge/utilities/Listeners.java) is the class where TestNG listeners have been implemented
- [ExtentReporter.java](https://github.com/Shraddha-Gada/abbott-code-challenge/blob/main/sdet-code-challenge/src/main/java/abbott/sdet_code_challenge/utilities/ExtentReporter.java) is the class where Extent Report wrapper is implemented


# Misc
Additionally, with more time, I would have implemented code for capturing screenshots on test failures and a logging framework.

# Part-2:
Script to fetch data from API and output the data
As confirmed with Arpan in my last conversation, language was not a constraint, thus I have coded this in Java.
I could have coded in python as well, just it would have take a bit more time

### Pseudo Code:

##### Step-1: 
Make HTTP GET request to given URL https://datausa.io/api/data?drilldowns=Nation&measures=Population
##### Step-2:
Response is received as a string
##### Step-3: 
Parse string to JSON 
##### Step-4:
Understand Required DATA
Based on required output, we want below fields
  - **According to _______** means source name of census
  - **in __ years** means total number of years
  - **from 20__** means the first year of data
  - **to 20__,** means the last year of data
  - **peak population growth was \_\_% in 20\_\_** means we need to find percentage increase of population for every year comparing with previous year. We need to track percentage and corresponding year.
  - **and lowest population increase was \_\_% in 20\_\_.** means same as above but lowest growth.
 
##### Step-5:
Extract Basic fields
- `source_name`
- `total_years`

##### Step-6: 
- Initialize variables for min, max year
- Initialize variables for peak, low growth and year
- Max is initialized to 0, so we find maximum values
- Min is initialized to 100 or 9999 so we find minimum possible values.

##### Step-7:
Loop through the 'data' array of response.
For each item on required output, we check below things

  - Compare with max_year. If greater than max_year, assign that value to max_year
  - Compare with min_year. If less than min_year, assign that value to min_year
  - Calculate percentage increase with previous year
  - Since data is present in descending order (2020,2019,etc.) we have to compare "i" th element with "i+1" th element.
  - Always check (i+1) value with size of array to avoid array out of bounds issue.
  - Calculate percentage increase of current year as (current year population - previous year population) / previous year population * 100.
  - if current percentage increase > peak_growth_percentage
    - Assign current year values to peak_growth_percentage, peak_growth_year
  - if current percentage increase < low_growth_percentage
    - Assign current year values to low_growth_percentage, low_growth_year
##### Step-8:
Create final string using variables:

- `source_name`
- `total_years`
- `min_year`
- `max_year`
- `peak_growth_percentage`
- `peak_growth_year`
- `low_growth_percentage`
- `low_growth_year`

##### Final code:
[FetchDataFromAPI.java](https://github.com/Shraddha-Gada/abbott-code-challenge/blob/main/sdet-code-challenge/src/test/java/abbott/sdet_code_challenge/tests/FetchDataFromAPI.java)

