# Enterprise Analytics Platform

A full-stack, enterprise-grade **Master-Data Driven Analytics & Operations Platform** built using **Spring Boot, Redis, PostgreSQL, and React**.

This project demonstrates how real-world enterprise systems are designed, secured, cached, and visualized with modern dashboards.

-------------------------------------------------------------------------------------------
🧠 Project Overview

This platform combines:

- 🔐 Secure authentication (JWT + Redis)
- 🗂️ Master data management (countries, cities, products, statuses)
- 📊 Backend-driven analytics dashboards
- ⚡ Redis-powered caching for performance
- 🎯 Role-based access control
- 🖥️ Modern React frontend with charts & drill-downs

Designed to be **industry-agnostic** and adaptable to:
- FinTech
- Travel
- E-commerce
- Logistics
- Business Intelligence systems

-----------------------------------------------------------------------------------------

## 🏗️ Architecture


frontend-react (React)
        |
        |  REST APIs (JSON)
        |
auth-service (Spring Boot)
        |
        |  JWT + Redis
        |
master-data-service (Spring Boot)
        |
        |  PostgreSQL + Redis
        |
analytics-engine

-----------------------------------------------------------------------------------------

🧩 Modules
🔐 Auth Service

JWT authentication

Refresh tokens stored in Redis

Role-based authorization (ADMIN, USER)

Secure /api/user/me endpoint
-----------------------------------------------------------------------------------------

🗂️ Master Data Service

Startup-loaded master data:

Countries

Cities

Products

Statuses

Redis cache for instant UI performance

Admin-triggered cache refresh APIs

-----------------------------------------------------------------------------------------

📊 Analytics Engine

Revenue & booking analytics

Aggregated SQL queries

Redis-cached analytics responses

Backend-driven chart contracts (title, type, colors, axis labels)
-----------------------------------------------------------------------------------------
🖥️ Frontend (React)

Login & protected routes

Master Data Explorer (dropdowns)

Analytics Dashboard

Chart rendering using backend-driven config

Axios interceptors for auth handling
-----------------------------------------------------------------------------------------

🔐 Security Design

Stateless JWT authentication

Redis-backed token validation

Role-based API protection using @PreAuthorize

CORS configured for frontend-backend communication
-----------------------------------------------------------------------------------------

⚡ Caching Strategy

Redis is used for:

Master data caching

Analytics aggregation caching

Authentication tokens

Cache keys follow structured naming:

master:countries
analytics:revenue-by-product
analytics:count-by-status
-----------------------------------------------------------------------------------------

📊 Analytics Contract Example

Backend sends chart-ready JSON:

{
  "title": "Revenue by Product",
  "chartType": "BAR",
  "series": [
    {
      "name": "Revenue",
      "color": "#4CAF50",
      "data": {
        "FLIGHT": 12.0,
        "HOTEL": 18.0
      }
    }
  ],
  "numberFormat": "K",
  "xAxisLabel": "Product",
  "yAxisLabel": "Revenue (K)"
}


Frontend renders charts dynamically without hardcoding logic.
-----------------------------------------------------------------------------------------
🛠️ Tech Stack
Backend

Java 21

Spring Boot 3

Spring Security

Spring Data JPA

Redis

PostgreSQL

Maven

Frontend

React

React Router

Axios

Chart.js / Recharts

CSS
-----------------------------------------------------------------------------------------
🚀 How to Run Locally
Backend
# Auth Service
cd auth-service
mvn spring-boot:run

# Master Data Service
cd master-data-service
mvn spring-boot:run

Frontend
cd frontend-react
npm install
npm start
-----------------------------------------------------------------------------------------

**Enterprise Analytics Platform**
Built a secure, Redis-cached analytics system with startup-loaded master data, JWT authentication, role-based access control, backend-driven chart APIs, and interactive React dashboards using Spring Boot, Redis, PostgreSQL, and React.

🧠 What This Project Demonstrates

Enterprise system design

Performance optimization with caching

Secure API development

Clean separation of concerns

Real-world analytics patterns

Backend + frontend ownership
