from faker import Faker
import psycopg2

fake = Faker()

# Connecting to the PostgreSQL database
conn = psycopg2.connect(
    host="localhost",
    port=5432,
    dbname="product_matcher",
    user="adventure",
    password="opiyo123",
)

# Generate and insert fake user data
