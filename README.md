# mockhttp

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Java: 17+](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Live API](https://img.shields.io/badge/Live%20API-Online-success.svg)](https://mockhttp.onrender.com/status/200)
[![Playground](https://img.shields.io/badge/Playground-Swagger%20UI-brightgreen.svg)](https://nitinkumar-gove.github.io/mockhttp/)

A lightweight API to mock real-world HTTP response codes, custom messages, and latency delays over a live public endpoint.

**Live Base URL**: `https://mockhttp.onrender.com`  
**Interactive Playground**: [https://nitinkumar-gove.github.io/mockhttp/](https://nitinkumar-gove.github.io/mockhttp/)

## API Reference

### `GET /status/{statusCode}`

Returns an HTTP response matching `{statusCode}` with optional custom messages and response delays.

#### Parameters

| Parameter | In | Type | Required | Description |
| :--- | :--- | :--- | :--- | :--- |
| `statusCode` | path | integer | Yes | HTTP status code (`100`–`599`) |
| `message` | query | string | No | Custom message or error text in payload |
| `delayMs` | query | long | No | Delay in milliseconds before returning response |

#### Examples

**Basic Status Code**
```bash
curl -i https://mockhttp.onrender.com/status/200
```
```json
{
  "message": "Mocked response for status 200"
}
```

**Custom Message & Error Status**
```bash
curl -i "https://mockhttp.onrender.com/status/404?message=User+not+found"
```
```json
{
  "error": "User not found"
}
```

**Simulate Latency Delay**
```bash
curl -i "https://mockhttp.onrender.com/status/503?delayMs=1000"
```
```json
{
  "error": "Mocked response for status 503"
}
```

**Validation Error**
```bash
curl -i https://mockhttp.onrender.com/status/999
```
```json
{
  "error": "Invalid HTTP status code: 999"
}
```

## Local Development

### Run with Maven
```bash
./mvnw spring-boot:run
```
Available locally at `http://localhost:8080/status/{statusCode}`.

### Run Tests
```bash
./mvnw test
```

### Run with Docker
```bash
docker build -t mockhttp .
docker run -p 8080:8080 mockhttp
```

## License

[MIT](LICENSE)
