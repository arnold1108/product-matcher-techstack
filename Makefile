# Spinning up the resources 
up:
	docker compose up --build -d 
down:
	docker compose down
install:
	pip install --upgrade pip && \
		-r requirements.txt 
