# mockhttp

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Java: 17+](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Framework-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Live API](https://img.shields.io/badge/Live%20API-Online-success.svg)](https://mockhttp.onrender.com/status/200)

A lightweight, high-performance mock API service to emulate real-life HTTP traffic scenarios, status codes, custom messages, and latency delays over a live public API.

---

## 🌐 Live API Base URL

The live API is hosted and freely accessible at:

```text
https://mockhttp.onrender.com
```

You can immediately start sending requests using `curl`, Postman, or your application's HTTP client.

---

## 📖 API Reference

### `GET /status/{statusCode}`

Simulates an HTTP response matching the specified status code with optional message customization and response delay.

#### Path Parameters

| Parameter | Type | Required | Description | Example |
| :--- | :--- | :--- | :--- | :--- |
| `statusCode` | `integer` | **Yes** | Any standard HTTP status code (100–599) | `200`, `404`, `500` |

#### Query Parameters

| Parameter | Type | Required | Description | Example |
| :--- | :--- | :--- | :--- | :--- |
| `message` | `string` | No | Overrides the default response text | `User created successfully` |
| `delayMs` | `long` | No | Introduces a sleep delay in milliseconds before returning the response | `500` |

---

## 📦 Response Formats

All responses return standard `Content-Type: application/json`.

### 1. Success Responses (1xx, 2xx, 3xx)
Successful status codes return a JSON object containing the `message` field:

```json
{
  "message": "Mocked response for status 200"
}
```

If a custom `?message=...` is provided:
```json
{
  "message": "Payment processed successfully"
}
```

### 2. Error Responses (4xx, 5xx)
Client and server error status codes return a JSON object containing the `error` field:

```json
{
  "error": "Mocked response for status 404"
}
```

If a custom `?message=...` is provided:
```json
{
  "error": "Resource not found"
}
```

### 3. Client Validation Errors (`400 Bad Request`)
If an invalid status code or a non-numeric parameter is supplied, a `400 Bad Request` is returned:

* **Unrecognized HTTP Status Code**:
  ```json
  {
    "error": "Invalid HTTP status code: 999"
  }
  ```

* **Type Mismatch (e.g. non-numeric code)**:
  ```json
  {
    "error": "Invalid value for parameter 'statusCode': expected a number"
  }
  ```

---

## 🚀 Quickstart & cURL Recipes

### Basic Status Code Check
```bash
curl -i https://mockhttp.onrender.com/status/200
```

### Simulating a 404 with Custom Error Message
```bash
curl -i "https://mockhttp.onrender.com/status/404?message=User+account+not+found"
```

### Simulating a 503 Service Unavailable with a Latency Delay
```bash
# Simulates a slow downstream service failing after 1000ms
curl -i "https://mockhttp.onrender.com/status/503?delayMs=1000"
```

### Simulating a 201 Created
```bash
curl -i "https://mockhttp.onrender.com/status/201?message=Record+created"
```

---

## 💻 Running Locally

### Prerequisites
* **Java 17+**
* **Maven 3.9+** (or use the included Maven wrapper)

### Build & Run
```bash
# Clone the repository
git clone https://github.com/Nitinkumar-Gove/mockhttp.git
cd mockhttp

# Run using Maven
./mvnw spring-boot:run
```

Once started, the local API will be available at:
```text
http://localhost:8080/status/{statusCode}
```

### Running Tests
Execute the unit and integration test suite:
```bash
./mvnw test
```

### Running with Docker
```bash
# Build the Docker image
docker build -t mockhttp .

# Run the container
docker run -p 8080:8080 mockhttp
```

---

## 🏗️ Architecture

`mockhttp` is built on Spring Boot using an extensible **Strategy Pattern**:

```
Client Request ──► MockhttpController ──► MockStrategy Resolution
                                                 │
                      ┌──────────────────────────┴──────────────────────────┐
                      ▼                                                     ▼
             DelayedMockStrategy                                   SimpleMockStrategy
        (Handles ?delayMs requests)                                (Handles standard requests)
                      │                                                     │
                      └──────────────────────────┬──────────────────────────┘
                                                 ▼
                                        MockResponseService
                                    (Constructs JSON response)
```

* **`MockStrategy`**: Interface defining strategy support and execution.
* **`SimpleMockStrategy`**: Standard response generation for immediate status code mocking.
* **`DelayedMockStrategy`**: Handles simulated network latency delays.
* **`GlobalExceptionHandler`**: Translates validation exceptions (`InvalidStatusCodeException`, `MethodArgumentTypeMismatchException`) into clean JSON responses.

---

## 🗺️ Roadmap & Upcoming Features

Active development is underway for future capabilities tracked on our [GitHub Issues](https://github.com/Nitinkumar-Gove/mockhttp/issues):
- [ ] Support for all HTTP methods (`POST`, `PUT`, `PATCH`, `DELETE`, `HEAD`, `OPTIONS`) ([#13](https://github.com/Nitinkumar-Gove/mockhttp/issues/13))
- [ ] Custom response headers and cookies ([#14](https://github.com/Nitinkumar-Gove/mockhttp/issues/14))
- [ ] Custom response body payloads and content types ([#15](https://github.com/Nitinkumar-Gove/mockhttp/issues/15))
- [ ] Random latency jitter and chaos failure simulation ([#16](https://github.com/Nitinkumar-Gove/mockhttp/issues/16))
- [ ] Request echo and inspection endpoint (`/inspect`) ([#17](https://github.com/Nitinkumar-Gove/mockhttp/issues/17))
- [ ] Redirect chain simulations (`/redirect/{n}`) ([#18](https://github.com/Nitinkumar-Gove/mockhttp/issues/18))
- [ ] Interactive Swagger UI / OpenAPI documentation ([#26](https://github.com/Nitinkumar-Gove/mockhttp/issues/26))

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
