import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

import requests
import uuid
import logging

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)
base_url = "http://localhost:8080"


class ProductActions:
    def __init__(self):
        self.base_url = base_url
        self.headers = {"Content-Type": "application/json"}

    def _make_post_request(self, endpoint, params=None):
        url = f"{self.base_url}/{endpoint}"
        try:
            response = requests.post(url=url, headers=self.headers, params=params)
            return response
        except requests.exceptions.RequestException as e:
            logger.error(f"Request error: {e}")
            return None

    def like_product(self, product_id: str):
        endpoint = "products/like"
        params = {"product_id": product_id}
        response = self._make_post_request(endpoint, params)
        if response and response.status_code == 200:
            logger.info(response.text)
        else:
            logger.error(f"Failed to like product: {response.status_code} - {response.text if response else ''}")

    # Add more methods for other product actions as needed

def main():
    product_actions = ProductActions()

    store_id = str(uuid.uuid4())
    logger.info(f"Using store_id :: {store_id}")

    product_actions.like_product(product_id=store_id)


if __name__ == "__main__":
    main()
