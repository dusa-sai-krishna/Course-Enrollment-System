# 🎓 Course Management System

A robust and secure API-based Course Management System built with Spring Boot, featuring JWT authentication, role-based access control, and comprehensive API documentation.

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot 3.4.2**
- **Spring Security** with JWT Authentication
- **Spring Data JPA** for data persistence
- **PostgreSQL** for database
- **OpenAPI/Swagger** for API documentation
- **Lombok** for reducing boilerplate code
- **Maven** for dependency management

## ✨ Key Features

### Authentication & Security
- JWT-based authentication system
- Secure endpoints with role-based access control
- Password encryption and secure token handling

### Course Management
- Course creation and management (Admin access)
- Student enrollment system
- Course scheduling functionality
- Detailed course information tracking

### API Features
- RESTful API design
- Comprehensive error handling
- Request validation
- Pagination support for data retrieval

### Documentation & Development
- Interactive API documentation with Swagger UI
- Detailed API specifications and examples
- Development-friendly features with Spring Boot DevTools

## 🔒 API Security

The API uses Bearer token authentication. All protected endpoints require a valid JWT token in the Authorization header:
```
Authorization: Bearer <your_jwt_token>
```

## 📚 API Documentation

The API documentation is available through Swagger UI at:
```
http://<your-server>/swagger-ui.html
```

## 🛠️ Getting Started

1. Clone the repository
2. Configure PostgreSQL database settings in `application.properties`
3. Run `mvn clean install` to build the project
4. Start the application using `mvn spring-boot:run`
5. Access Swagger UI to explore and test the APIs

## 💡 Key Endpoints

### Authentication
- `POST /auth/register` - Register a new user
- `POST /auth/login` - Authenticate and get JWT token

### Course Management (Admin)
- `POST /api/admin/course/add` - Create a new course
- `GET /api/admin/course/id/{courseId}` - Get course details
- Additional endpoints for course management and student operations

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📝 License

This project is licensed under the [MIT License](LICENSE).