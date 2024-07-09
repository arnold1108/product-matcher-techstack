from dataclasses import dataclass
from datetime import datetime
from enum import Enum


@dataclass
class AddStockRequest:
    product_name: str
    product_category: str
    product_description: str
    quantity: int
    price: float
    time_added: datetime


class StoreCategory(Enum):
    SPORTS = "Sports"
    FASHION = "Fashion"
    ELECTRONICS = "Electronics"
    APPLIANCES = "Appliances"
    HOME_AND_OFFICE = "Home & Office"
    COMPUTING = "Computing"
    HEALTH_AND_BEAUTY = "Health & Beauty"
    GROCERIES = "Groceries"


@dataclass
class CreateStoreRequest:
    store_name: str
    category: StoreCategory
