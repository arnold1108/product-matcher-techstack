import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

import requests
import uuid
import logging


logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)
base_url = "http://localhost:8080"


class ShoppingCart:
    def __init__(self, base_url: str):
        self.base_url = base_url
        self.headers = {
            "Content-Type": "application/json"
        }

    def _send_post_request(self, endpoint: str, params: dict):
        url = f"{self.base_url}{endpoint}"
        response = requests.post(url=url, headers=self.headers, params=params)
        self._log_response(response)

    def _send_get_request(self, endpoint: str, params: dict):
        url = f"{self.base_url}{endpoint}"
        response = requests.get(url=url, headers=self.headers, params=params)
        self._log_response(response)

    def _log_response(self, response):
        if response.status_code == 200:
            logger.info(response.text)
        else:
            logger.error(f"Error {response.status_code}: {response.text}")

    def add_product_to_cart(self, product_id: str, quantity: int):
        params = {
            "product_id": product_id,
            "quantity": quantity
        }
        self._send_post_request("/carts/add", params)

    def view_cart(self, shopper_id: str):
        params = {
            "shopper_id": shopper_id
        }
        self._send_get_request("/carts", params)

    def remove_product_from_cart(self, product_id: str):
        params = {
            "product_id": product_id
        }
        self._send_post_request("/carts/remove", params)

    def checkout(self, shopper_id: str):
        params = {
            "shopper_id": shopper_id
        }
        self._send_post_request("/carts/checkout", params)


def main():
    cart = ShoppingCart(base_url=base_url)
    store_id = str(uuid.uuid4())
    logger.info(f"Using store_id :: {store_id}")

    cart.add_product_to_cart(product_id=store_id, quantity=3)
    cart.view_cart(shopper_id=store_id)
    cart.remove_product_from_cart(product_id=store_id)
    cart.checkout(shopper_id=store_id)


if __name__ == "__main__":
    main()
