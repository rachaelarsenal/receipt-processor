# 🧾 Receipt Processor

A simple web service that processes receipts and calculates points based on predefined rules.

---

## 🚀 Features

- Submit a receipt (`/receipts/process`) and receive a receipt ID.
- Retrieve points for a receipt via (`/receipts/{id}/points`).
- Simple HTML UI to test receipts interactively.
- Dockerized and built with Java 21 + Spring Boot.

---

## 🧪 Run Locally (Using Docker)

### 1. 🏗️ Build Docker Image

```
docker build -t receipt-processor .
```

### 2. ▶️ Run the App

```
docker run -p 8080:8080 receipt-processor
```
To run it with large language model set to true (default is false)
```
docker run -e LARGE_LANGUAGE_MODEL=true -p 8080:8080 receipt-processor
```
App will be available at: http://localhost:8080

---

## 💻 Web UI

Once running, visit: http://localhost:8080

- Paste your receipt JSON
- Click Submit
- See the awarded points!

![image](https://github.com/user-attachments/assets/e49a2f9a-de5c-4a98-a5bd-4179155ff086)
