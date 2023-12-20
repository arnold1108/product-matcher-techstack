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

print("Connected to the PostgreSQL database!")

# creating a cusor
cur = conn.cursor()

# Generate and insert fake user data
for _ in range(20):  # Generate 20 fake users
    first_name = fake.first_name()
    last_name = fake.last_name()
    email = fake.email()
    date_of_birth = fake.date_of_birth(minimum_age=18, maximum_age=80)
    region = fake.country()

    cur.execute(
        "INSERT INTO users (first_name, last_name, email, data_of_birth, region)"
        "VALUES (%s, %s, %s, %s, %s)",
        (first_name, last_name, email, date_of_birth, region),
    )

# Confirming the inserted user data
cur.execute("SELECT * FROM users")
users = cur.fetchall()

for user in users:
    print(user)

# Closing the connection
conn.commit()
cur.close()
conn.close()
