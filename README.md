# data-ingestion-service
Data Ingestion Service (DIS)

High-level Functional Requirements:

* Simulate customer onboarding. Mimic customer sign-up by pulling random datasets from Kaggle.com at a random instance once per day.
* Oauth 2.0 for customers signing up

Non-Functional Requirements:

* Highly available and reliable
* Performant
* Eventually consistent
* Scalable - for different datasets and customers
* Maintainable


Run:
    docker-compose -f devops/docker-compose/docker-compose.yml up --build
