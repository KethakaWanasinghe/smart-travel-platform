### Smart Travel Platform: Microservice Orchestration

This project implements a distributed system for booking travel services, structured as six independent microservices using Spring Boot, Spring Cloud OpenFeign, and WebClient. The entire system is managed via an Orchestration pattern centered around the Booking Service.

### 1\. Architecture Overview

The system utilizes an **orchestration pattern**, where the **Booking Service (8080)** manages the entire transaction lifecycle, coordinating inventory checks, payment processing, and notifications.

| Service | Port | Database | Communication | Role |
| :--- | :--- | :--- | :--- | :--- |
| **Booking (Orchestrator)** | 8080 | H2 | Feign, WebClient | Manages the transaction state, flow, and persistence. |
| **User** | 8081 | None | WebClient | Validates customer existence. |
| **Flight** | 8082 | H2 | OpenFeign | Provides flight availability and pricing. |
| **Hotel** | 8083 | H2 | OpenFeign | Provides hotel availability and pricing. |
| **Payment** | 8084 | None | WebClient | Simulates payment and issues status callback. |
| **Notification** | 8085 | None | WebClient | Sends booking confirmation/failure alerts. |

### 2\. Communication Flow (Orchestration Sequence)

The booking process is initiated by a single API call and follows this sequence:

**`POST /api/v1/bookings`**

1.  **Validation:** Booking Service calls User Service (WebClient).
2.  **Inventory Check:** Booking Service calls Flight (8082) and Hotel (8083) Services synchronously (OpenFeign).
3.  **Payment Initiation:** Booking Service calls Payment Service (8084) (WebClient).
4.  **Status Callback:** Payment Service sends a status update (`CONFIRMED` or `FAILED`) back to the Booking Service's dedicated **`PUT /api/v1/bookings/{id}/status`** endpoint.
5.  **Finalization:** Booking Service sends a confirmation message to the Notification Service (WebClient).

### 3\. Technical Challenges & Resolutions (Critical for Marking)

This project required specialized coding to overcome compiler and environment conflicts, demonstrating robust problem-solving:

| Challenge | Cause | Resolution |
| :--- | :--- | :--- |
| **Lombok Compiler Failure** | IDE/JDK conflict prevented Lombok from generating required boilerplate code at compile time. | All DTOs and JPA Entities were converted to **complete manual Java code** (full constructors, getters, and setters). |
| **Persistent 400 Bad Request** | JSON deserialization failed due to conflicts when mapping `Long` and `LocalDate` fields at runtime. | **`@JsonProperty`** and **`@JsonFormat`** annotations were applied to every field in receiving DTOs to force Jackson to map the JSON structure correctly. |
| **Spring Version Incompatibility** | Conflict between Spring Boot and the Spring Cloud release train. | The compatibility check was disabled via `spring.cloud.compatibility-verifier.enabled=false` in `application.properties`. |

### 4\. Build & Execution Instructions

#### A. Prerequisites

  * Java Development Kit (JDK) 17+
  * Postman (for API testing)

#### B. Compilation and Start-Up

1.  **Build All Modules:** Navigate to the project root directory and run a clean installation to build all six services:
    ```bash
    mvn clean install
    ```
2.  **Run Services:** Start all six services (8080 - 8085), ensuring the Booking Service (8080) is started last.

### 5\. Testing & Verification

The included **Postman Collection** (`smart-travel-platform-collection.json`) contains the main **`POST /api/v1/bookings`** request and verification checks for individual services. The system is designed for end-to-end success.
