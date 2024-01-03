## Soko
### Project Background
The e-commerce industry is continuously evolving, with a vast amount of user activity data being generated in real-time. To provide a personalized and engaging shopping experience, it is crucial for e-commerce businesses to efficiently process and analyze this data. Moreover, recommending relevant products to users based on their preferences and behavior has become a critical aspect of increasing customer satisfaction and driving sales. 

This project aims to address these challenges by developing a comprehensive end-to-end data pipeline and recommender engine specifically tailored for e-commerce. The pipeline enables seamless ingestion, processing, and storage of streaming user activity events, while the recommender engine utilizes this data to generate personalized recommendations. By leveraging cutting-edge technologies such as streaming platforms, distributed computing frameworks, and machine learning algorithms, we can extract valuable insights from the user activity data in real-time. The data pipeline ensures the efficient processing and transformation of these events, enabling us to persist the enriched data in a highly scalable and reliable database.

The heart of the project lies in the recommender engine, which utilizes state-of-the-art recommendation algorithms and machine learning techniques. By analyzing user behavior, preferences, and product information, the engine generates highly accurate and personalized product recommendations. This empowers e-commerce businesses to deliver tailored shopping experiences, enhance customer satisfaction, and increase conversion rates.

### Project Overview
#### Benefits
* Collaborative Filtering Recommendations: The system leverages collaborative filtering to recommend products, ensuring buyers receive personalized suggestions based on their preferences.
* Seller Empowerment: Sellers can create stalls and upload their stock, benefiting from the recommender engine that intelligently matches their products to potential buyers.
* Transaction Notifications: Sellers receive timely notifications when their products are purchased by buyers, keeping them informed about successful transactions.

#### Roles
* Buyer: They play a crucial role in the marketplace and can access recommended insights through the marketplace platform.
* Seller: Sellers have the ability to create stalls and upload their stock, enhancing their engagement and participation in the platform.

#### Buyer Functionality
* Create Account: Buyers initiate their journey by creating an account, establishing a personalized profile.
* Explore: Buyers can seamlessly explore a diverse range of products available in the marketplace.
* Select Items: Upon exploration, buyers have the flexibility to select items that align with their preferences and needs.
* Add to Cart: The Cart functionality allows buyers to conveniently add selected items for consideration.
* Checkout: Buyers finalize their transactions by checking out, ensuring a smooth and secure shopping experience.

#### Seller Functionality
* Create Account: Sellers begin by creating an account, establishing their identity on the platform.
* Create Store: Sellers have the capability to create personalized stores, providing a dedicated space for showcasing their products.
* Add Stock: Sellers can effortlessly add and manage their stock within their created store, enhancing their online presence.
* Receive Notifications: Sellers receive timely notifications when their products are selected by buyers, enabling them to stay informed about their sales.

#### Microservices
* API Gateway: The API Gateway serves as the central entry point for routing API requests to the relevant microservices.
* Accounts Service: The Accounts Service manages account creation transactions for both sellers and buyers.
* Marketplace Service: The Marketplace Service facilitates product exploration for buyers, incorporating a recommender engine. It also handles the selection of liked items.
* Cart Service: The Cart Service is responsible for managing the selection, addition, and checkout of items in the shopping cart.
* Store Service: The Store Service is dedicated to creating and managing stores, overseeing stock levels, and related operations.
* Notification Service: The Notification Service handles the delivery of notifications to sellers when buyers complete their transactions.


[//]: # (![High Level Architecture]&#40;data/soko.png&#41;)
