import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

import requests
import uuid
import logging

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)
base_url = "http://localhost:8080"


def explore_products(buyer_id: str):
    """Recommender engine not ready yet"""
    raise NotImplementedError("This function is not yet implemented")


def like_product(product_id: str):
    url = f"{base_url}/products/like"
    headers = {
        "Content-Type": "application/json"
    }
    params = {
        "product_id": product_id
    }
    like_product_response = requests.post(url=url, headers=headers, params=params)

    if like_product_response.status_code == 200:
        logger.info(like_product_response.text)
    else:
        logger.error(f"Failed to like product: {like_product_response.status_code} - {like_product_response.text}")


def main():
    store_id = str(uuid.uuid4())
    logger.info(f"Using store_id :: {store_id}")

    like_product(product_id=store_id)


if __name__ == "__main__":
    main()
