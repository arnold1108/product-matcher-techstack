from faker import Faker
from confluent_kafka import Producer
import json

# Kafka broeker configuration
kafka_config = {
    "bootstrap.servers": "localhost:9092",
    "client.id": "fake_event_producer",
}
