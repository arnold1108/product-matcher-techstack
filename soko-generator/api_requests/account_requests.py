import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from models.account import CompleteSignupRequest, Gender
import json
import logging
import requests
from dataclasses import asdict
from faker import Faker
from datetime import datetime

fake = Faker()

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)

def create_account(complete_signup_request: CompleteSignupRequest):
    url = "http://localhost:8080/accounts/completeSignup"
    headers = {
        "Content-Type": "application/json"
    }
    payload = asdict(data)
    payload["gender"] = payload["gender"].value

    response = requests.post(url=url, data=json.dumps(payload), headers=headers)

    if response.status_code == 200:
        logger.info(response.text)
    else:
        logger.info(response.status_code, response.text)


def generate_fake_signup_request() -> CompleteSignupRequest:
    """Generating fake data for signup"""
    return CompleteSignupRequest(
        first_name=fake.first_name(),
        last_name=fake.last_name(),
        date_of_birth=datetime.now().isoformat(),
        gender=Gender.MALE,
        country=fake.country()

    )


if __name__ == "__main__":
    data = generate_fake_signup_request()
    create_account(complete_signup_request=data)
