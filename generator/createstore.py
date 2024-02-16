import requests
from faker import Faker
import json
import uuid

fake = Faker()


def generate_fake_store_request():
    seller_id = str(uuid.uuid4())
    category = fake.random_element(
        elements=("Electronics", "Clothing", "Books", "Toys")
    )
    store_name = fake.company()

    request_data = {"category": category, "storeName": store_name}

    return seller_id, request_data


def send_fake_store_request(seller_id, request_data):
    url = f"http://localhost:8081/{seller_id}/seller/store/create"
    print(f"Sending request to: {url}")
    headers = {"Content-Type": "application/json"}

    response = requests.post(url, data=json.dumps(request_data), headers=headers)

    return response


if __name__ == "__main__":
    for _ in range(100):  # Adjust the number of fake requests you want to generate
        seller_id, request_data = generate_fake_store_request()
        response = send_fake_store_request(seller_id, request_data)

        print(f"Generated request for Seller ID: {seller_id}")
        print(f"Response: {response.status_code} - {response.text}")
        print("\n")
