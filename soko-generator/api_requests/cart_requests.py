import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
import requests
import uuid
import logging

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)
base_url = "http://localhost:8080"


def add_product_to_cart(product_id: str, quantity: int):
    url = f"{base_url}/carts/add"
    headers = {
        "Content-Type": "application/json"
    }
    params = {
        "product_id": product_id,
        "quantity": quantity
    }

    add_product_to_cart_response = requests.post(url=url, headers=headers, params=params)

    if add_product_to_cart_response.status_code == 200:
        logger.info(add_product_to_cart_response.text)
    else:
        logger.info(add_product_to_cart_response.status_code, add_product_to_cart_response.text)


def view_cart(shopper_id: str):
    url = f"{base_url}/carts"
    headers = {
        "Content-Type": "application/json"
    }
    params = {
        "shopper_id": shopper_id
    }
    view_cart_response = requests.get(url=url, headers=headers, params=params)

    if view_cart_response.status_code == 200:
        logger.info(view_cart_response.text)
    else:
        logger.info(view_cart_response.status_code, view_cart_response.text)


def remove_product_from_cart(product_id: str):
    url = f"{base_url}/carts/remove"
    headers = {
        "Content-Type": "application/json"
    }
    params = {
        "product_id": product_id
    }
    remove_product_from_cart_response = requests.post(url=url, headers=headers, params=params)

    if remove_product_from_cart_response.status_code == 200:
        logger.info(remove_product_from_cart_response.text)
    else:
        logger.info(remove_product_from_cart_response.status_code, remove_product_from_cart_response.text)


def checkout(shopper_id: str):
    url = f"{base_url}/carts/checkout"
    headers = {
        "Content-Type": "application/json"
    }
    params = {
        "shopper_id": shopper_id
    }
    checkout_response = requests.post(url=url, headers=headers, params=params)

    if checkout_response.status_code == 200:
        logger.info(checkout_response.text)
    else:
        logger.info(checkout_response.status_code, checkout_response.text)


def main():
    store_id = str(uuid.uuid4())
    logger.info(f"Using store_id :: {store_id}")

    add_product_to_cart(product_id=store_id, quantity=3)
    view_cart(shopper_id=store_id)
    remove_product_from_cart(product_id=store_id)
    checkout(shopper_id=store_id)


if __name__ == "__main__":
    main()
