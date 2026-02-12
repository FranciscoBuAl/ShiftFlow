# horarios-generator-monorepo

Estructura base de monorepo para un generador de horarios con backend en Quarkus y frontend en React.

## Estructura

- `backend/`: API Quarkus (Java 21, Maven, Panache, Flyway, OpenAPI, Timefold).
- `frontend/`: Vite + React 19 + TypeScript 5.
- `.docker/`: PostgreSQL 17 con docker-compose y volumen persistente.
- `pom.xml`: padre agregador Maven.

## Requisitos

- Java 21
- Maven 3.9+
- Node.js 20+
- Docker + Docker Compose

## Levantar base de datos

```bash
docker compose -f .docker/docker-compose.yml up -d
```

## Backend (localhost:8080)

```bash
mvn -pl backend quarkus:dev
```

Health check de ejemplo:

```bash
curl http://localhost:8080/health
```

## Frontend (localhost:5173)

```bash
cd frontend
npm install
npm run dev
```

## Build de verificación

```bash
mvn -pl backend clean verify
cd frontend && npm run build
```
