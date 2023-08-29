import socket
from confluent_kafka import Consumer, KafkaError

# Kafka configuration
kafka_config = {
    "bootstrap.servers": "localhost:9092",  # Change to your Kafka broker address
    "group.id": socket.gethostname(),
    "auto.offset.reset": "earliest",
}

# Create a Kafka consumer instance
consumer = Consumer(kafka_config)

# Subscribe to the topic
consumer.subscribe(["my-topic"])  # Replace with your topic name

try:
    while True:
        # Poll for new messages
        msg = consumer.poll(1.0)

        if msg is None:
            continue
        if msg.error():
            if msg.error().code() == KafkaError._PARTITION_EOF:
                # End of partition event
                print("Reached end of partition")
            else:
                print(f"Error: {msg.error().str()}")
        else:
            # Print the received message value
            print(f'Received message: {msg.value().decode("utf-8")}')

except KeyboardInterrupt:
    pass
finally:
    # Close the consumer
    consumer.close()
