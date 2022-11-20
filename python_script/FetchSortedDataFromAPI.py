# importing the requests library
import requests

# api-endpoint
URL = "https://datausa.io/api/data?drilldowns=Nation&measures=Population"

# sending the get request and saving the response as response object
resp = requests.get(url=URL)

# get the source name for census in json format
source_name = resp.json()["source"][0]["annotations"]["source_name"]

# get the data block in json format
data = resp.json()["data"]

# sort the data by Year
sorted_data = sorted(data, key=lambda x: x["Year"])

# get total number of years
total_years = len(sorted_data)

# get the min year
min_year = sorted_data[0]["Year"]

# get the max year
max_year = sorted_data[total_years - 1]["Year"]

# initialize the peak values for growth and corresponding year
peak_growth_percentage = 0
peak_growth_year = 0

# initialize the lowest values for growth and corresponding year
low_growth_percentage = 100
low_growth_year = 9999
i = 0

for item in sorted_data:
    # i > 0 is to skip the lowest year i.e. min_year
    if i > 0:
        # since data is sorted in ascending order, current year is (i) and previous year is (i-1)
        prev_year_item = sorted_data[i - 1]
        # calculate the percentage increase
        percentage = (
            (item["Population"] - prev_year_item["Population"])
            / prev_year_item["Population"]
        ) * 100
        # check for the peak growth
        if percentage > peak_growth_percentage:
            peak_growth_percentage = percentage
            peak_growth_year = item["Year"]
        # check for the lowest growth
        if percentage < low_growth_percentage:
            low_growth_percentage = percentage
            low_growth_year = item["Year"]
    i += 1

# generate the final output
final_str = f"According to {source_name}, in {total_years} years from {min_year} to {max_year}, peak population growth was {round(peak_growth_percentage,2)}% in {peak_growth_year} and lowest population increase was {round(low_growth_percentage,2)}% in {low_growth_year}."
# print("------------final output------------")
print(final_str)
