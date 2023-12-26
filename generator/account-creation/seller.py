import requests
from faker import Faker
import json
import uuid
from datetime import datetime

fake = Faker()

endpoint_url = "http://localhost:8080/seller/account/create"

for _ in range(12):
    # Generate fake data
    first_name = fake.first_name()
    last_name = fake.last_name()
    email = fake.email()

    # Generate a random date of birth as a datetime object
    dob_datetime = fake.date_time_between(start_date="-80y", end_date="-18y")

    # Convert the datetime object to a string in the desired format
    dob = dob_datetime.strftime("%Y-%m-%dT%H:%M:%S")

    gender = fake.random_element(elements=("MALE", "FEMALE"))
    country_code = fake.country()

    # Create the request payload
    payload = {
        "firstName": first_name,
        "lastName": last_name,
        "emailAddress": email,
        "email": email,
        "dob": dob,
        "gender": gender,
        "country": country_code,
    }

    # Make the HTTP request
    response = requests.post(endpoint_url, json=payload)

    # Print the response
    print(f"{response.text}")
