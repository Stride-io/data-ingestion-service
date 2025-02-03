# data-ingestion-service
Data Ingestion Service (DIS)

High-level Functional Requirements:

* Simulate customer onboarding. Mimic customer sign-up by pulling random datasets from Kaggle.com at a random instance once per day.
* Set up K8s and helm to read secrets
* ETL data using Apache spark
* Data normalization and transformation
* Stream data as events to DSS using Flink

Non-Functional Requirements:

* Highly available and reliable
* Performant
* Eventually consistent
* Scalable - for different datasets and customers
* Maintainable


Run:
    docker-compose -f devops/docker-compose/docker-compose.yml up --build
