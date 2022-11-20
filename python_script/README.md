# Problem statement

Write a small Python script to fetch data from API and output the data

a) Get data from URL:
https://datausa.io/api/data?drilldowns=Nation&measures=Population

b) Process response and output in below format:
According to _____, in _ years from 20__ to 20__, peak population growth was __% in
20__ and lowest population increase was __% in 20__.


### Pseudo Code:

##### Step-1: 
Understand the required data.

Based on the required output, we want the below fields
  - **According to _______** means source name of census
  - **in __ years** means total number of years
  - **from 20__** means the first year of data
  - **to 20__,** means the last year of data
  - **peak population growth was \_\_% in 20\_\_** means we need to find percentage increase of population for every year comparing with previous year. We need to track percentage and corresponding year.
  - **and lowest population increase was \_\_% in 20\_\_.** means same as above but lowest growth.
 
##### Step-2:  
Make HTTP GET request to given URL https://datausa.io/api/data?drilldowns=Nation&measures=Population

##### Step-3:
Response is received as a string

##### Step-4: 
Parse string to JSON 

##### Step-5:
Sort the data block by Year
 
##### Step-6:
Extract Basic fields
- `source_name`
- `total_years`
- `min_year`
- `max_year`

##### Step-7: 
- Initialize variables for peak_growth_percentage and peak_growth_year
- Initialize variables for low_growth_percentage and low_growth_year
- peak variables are initialized to 0, so we find maximum values
- low variales are initialized to 100 or 9999 so we find minimum possible values.

##### Step-8:
Loop through the sorted 'data' block of response.
For each item on required output, we check below things

  - Calculate percentage increase with previous year
  - Since data is present in ascending order (2013,2014,etc.) we have to compare "i" th element with "i-1" th element.
  - Calculate percentage increase of current year as (current year population - previous year population) / previous year population * 100.
  - if current percentage increase > peak_growth_percentage
    - Assign current year values to peak_growth_percentage, peak_growth_year
  - if current percentage increase < low_growth_percentage
    - Assign current year values to low_growth_percentage, low_growth_year
	
##### Step-9:
Create final string using variables:

- `source_name`
- `total_years`
- `min_year`
- `max_year`
- `peak_growth_percentage`
- `peak_growth_year`
- `low_growth_percentage`
- `low_growth_year`

##### Step-10:
Print the final string



