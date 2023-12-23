import requests
from faker import Faker
import json
import uuid

fake = Faker()

endpoint_url = "http://localhost:8080/seller/account/create"

for _ in range(1000):
    # Generate fake data
    first_name = fake.first_name()
    last_name = fake.last_name()
    email = fake.email()
    dob = fake.date_of_birth(minimum_age=18, maximum_age=80).year
    gender = fake.random_element(elements=("MALE", "FEMALE"))

    # Create the request payload
    payload = {
        "firstName": first_name,
        "lastName": last_name,
        "emailAddress": email,
        "email": email,
        "dob": str(dob),
        "gender": gender,
    }

    # Make the HTTP request
    response = requests.post(endpoint_url, json=payload)

    # Print the response
    print(f"{response.text} {first_name}")
