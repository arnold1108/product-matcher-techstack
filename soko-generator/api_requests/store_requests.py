import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

import requests
from models.store import CreateStoreRequest, AddStockRequest
import uuid
import logging

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)
base_url = "http://localhost:8080"


def create_store(create_store_request: CreateStoreRequest):
    url = f"{base_url}/stores/create"
    headers = {
        "Content-Type": "application/json"
    }

    create_store_response = requests.post(url=url, headers=headers)

    if create_store_response.status_code == 200:
        logger.info(create_store_response.text)
    else:
        logger.info(create_store_response.status_code, create_store_response.text)


def add_stock(add_stock_request: AddStockRequest, store_id: str):
    url = f"{base_url}/stores/stock/add"
    headers = {
        "Content-Type": "application/json"
    }
    params = {
        "store_id": store_id
    }
    add_stock_response = requests.post(url=url, headers=headers, params=params)

    if add_stock_response.status_code == 200:
        logger.info(add_stock_response.text)
    else:
        logger.info(add_stock_response.status_code, add_stock_response.text)


def close_store(store_id: str):
    url = f"{base_url}/stores/close"
    headers = {
        "Content-Type": "application/json"
    }
    params = {
        "store_id": store_id
    }
    close_store_response = requests.post(url=url, headers=headers, params=params)
    if close_store_response.status_code == 200:
        logger.info(close_store_response.text)
    else:
        logger.info(close_store_response.status_code, close_store_response.text)


def reopen_store(store_id: str):
    url = f"{base_url}/stores/reOpen"
    headers = {
        "Content-Type": "application/json"
    }
    params = {
        "store_id": store_id
    }
    reopen_store_response = requests.post(url=url, headers=headers, params=params)
    if reopen_store_response.status_code == 200:
        logger.info(reopen_store_response.text)
    else:
        logger.info(reopen_store_response.status_code, reopen_store_response.text)


def manage_store(store_id: str):
    url = f"{base_url}/stores/manage"
    headers = {
        "Content-Type": "application/json"
    }
    params = {
        "store_id": store_id
    }
    manage_store_response = requests.get(url, headers=headers, params=params)

    if manage_store_response.status_code == 200:
        logger.info(manage_store_response.json())
    else:
        logger.info(manage_store_response.status_code, manage_store_response.text)


def main():
    store_id = str(uuid.uuid4())
    logger.info(f"Using store_id :: {store_id}")

    manage_store(store_id=store_id)
    reopen_store(store_id=store_id)
    close_store(store_id=store_id)


if __name__ == "__main__":
    main()
