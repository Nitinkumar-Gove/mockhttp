# mockhttp

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Java: 17+](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Live API](https://img.shields.io/badge/Live%20API-Online-success.svg)](https://mockhttp.onrender.com/status/200)
[![Playground](https://img.shields.io/badge/Playground-Swagger%20UI-brightgreen.svg)](https://nitinkumar-gove.github.io/mockhttp/)

A lightweight API to mock real-world HTTP response codes, custom messages, and latency delays over a live public endpoint.

## 🚀 Try the Live API

Test all supported endpoints, parameters, and simulated delays interactively directly in your browser:

👉 **[https://nitinkumar-gove.github.io/mockhttp/](https://nitinkumar-gove.github.io/mockhttp/)**

The interactive playground is powered by Swagger UI and connects directly to the live service at `https://mockhttp.onrender.com`.

### Quick cURL

```bash
# Basic status code
curl -i https://mockhttp.onrender.com/status/200

# Custom message
curl -i "https://mockhttp.onrender.com/status/404?message=User+not+found"

# Latency delay (ms)
curl -i "https://mockhttp.onrender.com/status/503?delayMs=1000"
```
