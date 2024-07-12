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


def generate_fake_signup_request() -> CompleteSignupRequest:
    """Generating fake data for signup"""
    return CompleteSignupRequest(
        first_name=fake.first_name(),
        last_name=fake.last_name(),
        # date_of_birth=datetime.now().isoformat(),
        gender=Gender.MALE,
        country=fake.country()
    )


class AccountManager:
    def __init__(self):
        self.base_url = "http://localhost:8080"
        self.headers = {"Content-Type": "application/json"}

    def create_account(self, complete_signup_request: CompleteSignupRequest):
        url = f"{self.base_url}/accounts/completeSignup"
        payload = asdict(complete_signup_request)
        payload["gender"] = payload["gender"].value

        logger.info(f"Sending request :: {payload}")
        try:
            response = requests.post(url=url, data=json.dumps(payload), headers=self.headers)
            if response.status_code == 200:
                logger.info(response.text)
            else:
                logger.error(f"Failed to create account: {response.status_code} - {response.text}")
        except requests.exceptions.RequestException as e:
            logger.error(f"Request error: {e}")


def main():
    account_manager = AccountManager()
    data = generate_fake_signup_request()
    account_manager.create_account(complete_signup_request=data)


if __name__ == "__main__":
    main()
