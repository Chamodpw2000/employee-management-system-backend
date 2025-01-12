I completed a project on an Employee Management System to familiarize myself with Springboot Microservices architecture and CRUD Operations by integrating Springboot with MySQL. I developed a full-stack application by using Springboot as the backend framework, MySQL as the Database and React along with MUI for the frontend.

Here are the features and technologies I used. 🚀

💥Three microservices as Employee, Department and Organization with WebClient for seamless cross-microservice communication.

💥An API Gateway handles routing by directing incoming requests to the appropriate microservices or backend services, ensuring efficient and centralized management of API traffic.

💥Config Server to externalize configurations in a GitHub repository and ensure consistent and dynamic updates without needing to redeploy the services.

💥Distributed Tracing with Zipkin and Sleuth helps track requests as they flow through various microservices, providing insights into performance bottlenecks and service interactions

💥Using Resilience4J as a circuit breaker to prevent cascading failures by managing requests to failing services and temporarily blocking calls to them until they recover, ensuring system stability and resilience.

Demostration - https://www.youtube.com/watch?v=hs8EqQweL_I&t=3s
