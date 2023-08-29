import socket
from confluent_kafka import Producer
from faker import Faker
import json

kafka_config = {
    "bootstrap.servers": "localhost:9092",
    "client.id": socket.gethostname(),
}
kafka_topic = "my-topic"
# Create a Faker instance
fake = Faker()


def delivery_report(err, msg):
    if err is not None:
        print("Message delivery failed: {}".format(err))
    else:
        print("Message delivered to {} [{}]".format(msg.topic(), msg.partition()))


# Create a Kafka producer instance
producer = Producer(kafka_config)

# Generate and publish fake events to the Kafka topic
for _ in range(100):  # Generate 100 fake events
    event = {
        "user_id": fake.random_int(min=1, max=10),  # Replace with your user ID logic
        "event_type": fake.random_element(elements=("click", "purchase", "view")),
        "timestamp": fake.date_time_this_month().isoformat(),  # Convert datetime to ISO format string
        "product_id": fake.random_int(
            min=1, max=1000
        ),  # Replace with your product ID logic
    }

    # Convert event to JSON and publish it to the Kafka topic
    producer.produce(
        kafka_topic, json.dumps(event).encode("utf-8"), callback=delivery_report
    )

# Wait for any outstanding messages to be delivered and delivery reports to be received
producer.flush()
