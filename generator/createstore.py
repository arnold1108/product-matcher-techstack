import requests
from faker import Faker

fake = Faker()

# Generate fake data
sellerId = fake.uuid4()
category = fake.word()
storeName = fake.company()

# Create the payload
payload = {"sellerId": str(sellerId), "category": category, "storeName": storeName}

# Define the headers for the request
headers = {"Content-Type": "application/json"}

# Make the POST request
response = requests.post(
    "http://localhost:8081/api/endpoint", json=payload, headers=headers
)

# Print the response
print(response.status_code)
# print(response.json()
