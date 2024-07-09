import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from models.store import CreateStoreRequest, AddStockRequest
import requests
import uuid
import logging

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)
base_url = "http://localhost:8080"


class StoreManager:
    def __init__(self, base_url: str):
        self.base_url = base_url
        self.headers = {
            "Content-Type": "application/json"
        }

    def _send_post_request(self, endpoint: str, params: dict = None):
        url = f"{self.base_url}{endpoint}"
        response = requests.post(url=url, headers=self.headers, params=params)
        self._log_response(response)

    def _send_get_request(self, endpoint: str, params: dict = None):
        url = f"{self.base_url}{endpoint}"
        response = requests.get(url=url, headers=self.headers, params=params)
        self._log_response(response)

    def _log_response(self, response):
        if response.status_code == 200:
            logger.info(response.text)
        else:
            logger.error(f"Error {response.status_code}: {response.text}")

    def create_store(self, create_store_request: CreateStoreRequest):
        endpoint = "/stores/create"
        self._send_post_request(endpoint)

    def add_stock(self, add_stock_request: AddStockRequest, store_id: str):
        endpoint = "/stores/stock/add"
        params = {
            "store_id": store_id
        }
        self._send_post_request(endpoint, params=params)

    def close_store(self, store_id: str):
        endpoint = "/stores/close"
        params = {
            "store_id": store_id
        }
        self._send_post_request(endpoint, params=params)

    def reopen_store(self, store_id: str):
        endpoint = "/stores/reOpen"
        params = {
            "store_id": store_id
        }
        self._send_post_request(endpoint, params=params)

    def manage_store(self, store_id: str):
        endpoint = "/stores/manage"
        params = {
            "store_id": store_id
        }
        self._send_get_request(endpoint, params=params)


def main():
    store_manager = StoreManager(base_url=base_url)
    store_id = str(uuid.uuid4())
    logger.info(f"Using store_id :: {store_id}")

    store_manager.manage_store(store_id=store_id)
    store_manager.reopen_store(store_id=store_id)
    store_manager.close_store(store_id=store_id)


if __name__ == "__main__":
    main()
