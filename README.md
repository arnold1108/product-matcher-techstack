# ProductMatcher
## Project Background
The e-commerce industry is continuously evolving, with a vast amount of user activity data being generated in real-time. To provide a personalized and engaging shopping experience, it is crucial for e-commerce businesses to efficiently process and analyze this data. Moreover, recommending relevant products to users based on their preferences and behavior has become a critical aspect of increasing customer satisfaction and driving sales.

This project aims to address these challenges by developing a comprehensive end-to-end data pipeline and recommender engine specifically tailored for e-commerce. The pipeline enables seamless ingestion, processing, and storage of streaming user activity events, while the recommender engine utilizes this data to generate personalized recommendations.

By leveraging cutting-edge technologies such as streaming platforms, distributed computing frameworks, and machine learning algorithms, we can extract valuable insights from the user activity data in real-time. The data pipeline ensures the efficient processing and transformation of these events, enabling us to persist the enriched data in a highly scalable and reliable database.

The heart of the project lies in the recommender engine, which utilizes state-of-the-art recommendation algorithms and machine learning techniques. By analyzing user behavior, preferences, and product information, the engine generates highly accurate and personalized product recommendations. This empowers e-commerce businesses to deliver tailored shopping experiences, enhance customer satisfaction, and increase conversion rates.

https://www.startdataengineering.com/post/data-engineering-project-for-beginners-stream-edition/#2-sample-project
https://github.com/josephmachado/beginner_de_project_stream/tree/main
https://github.com/mdipietro09/DataScience_ArtificialIntelligence_Utils/blob/master/machine_learning/example_recommendation.ipynb

Benefits
Recommend people products based on collaborative filtering.
Sellers can create stalls and upload their stock. The recommender engine will match their products to their potential buyers.

Roles
There are two major roles
The Seller - They should access the recommended image through the marketplace
The buyer - They should be able to create stalls and upload their stock

Buyer Functionality
Create account -> Explore -> Select items -> Add to Cart -> Checkout.

Seller Functionality
Create account -> Create Store -> Add Stock -> Receive Notification when their product gets selected.

Microservices
Accounts Service
This will handle the account creation transaction for both the seller and the buyer.
Explore
This will house the recommender engine. It will also be responsible for selecting the items they have liked.
Cart
This will be responsible for selecting items and adding them to the cart, and checking out
Store
This will be responsible for creating and managing the store, including the stocks inside.
Notification
This will be responsible for sending notifications to the seller when the buyer gets their item.
API Gateway
This will be responsible for routing the apis


![High Level Architecture](data/soko.png)