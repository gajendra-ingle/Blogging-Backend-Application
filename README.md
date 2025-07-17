# 📚 Blog Application

<div align="center">
  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/gajendra-ingle/Blogging-Backend-Application/blob/main/LICENSE)
![Lines of Code](https://tokei.rs/b1/github/gajendra-ingle/Blogging-Backend-Application?category=code)
![Visitor Badge](https://visitor-badge.laobi.icu/badge?page_id=gajendra-ingle.Blogging-Backend-Application) 
[![Last Commit](https://img.shields.io/github/last-commit/gajendra-ingle/Blogging-Backend-Application)](https://github.com/gajendra-ingle/Blogging-Backend-Application/commits/main)

</div>


### 📖 About the Project
This is a RESTful backend service built with Spring Boot to manage a blogging platform. It supports user registration, login, role-based access, managing posts, categories, comments, and image uploads. The project includes JWT-based security and provides pagination, sorting, and search capabilities.


### 🎯 Objective
To build a secure, scalable, and well-structured backend API for a blog system that handles authentication, authorization, content management, and multimedia handling efficiently.

## 🛠 Technology Stack

- **Java**: JDK 17  
- **Backend Framework**: Spring Boot, Spring Security (JWT)  
- **Database Framework**: Spring Data JPA (Hibernate)  
- **Database**: MySQL  
- **Object Mapping**: ModelMapper (DTO mapping)  
- **Build Tool**: Maven  
- **API Documentation**: Swagger (OpenAPI)  
- **Development Environment**: Eclipse  
- **API Testing Tool**: Postman  

## 🧩 Modules in the Project

- **Authentication**: Handles user registration, login, and JWT-based authentication  
- **User**: Provides CRUD operations and role assignment for users  
- **Post**: Supports creating, updating, deleting, listing, searching posts, and uploading images  
- **Category**: Organizes and categorizes blog posts  
- **Comment**: Enables adding and removing comments on posts  
- **Security**: Implements role-based access control and authorization using JWT  
- **API Documentation**: Offers interactive API exploration via Swagger UI   


## 🚀 API Endpoints Overview

### 🔐 Authentication

| Method | Endpoint                  | Description                                |
|--------|---------------------------|--------------------------------------------|
| POST   | /api/v1/auth/register     | Register a new user                        |
| POST   | /api/v1/auth/login        | Authenticate user and get JWT token        |
| GET    | /api/v1/auth/current-user | Get the currently logged-in user's details |

### 👤 User

| Method | Endpoint              | Description                    |
|--------|-----------------------|--------------------------------|
| POST   | /api/user/create      | Create a new user              |
| GET    | /api/user/all         | Get all users                  |
| GET    | /api/user/{userId}    | Get user by ID                 |
| PUT    | /api/user/{userId}    | Update user by ID              |
| DELETE | /api/user/{userId}    | Delete user by ID (Admin only) |

### 📝 Post

| Method | Endpoint                                                    | Description                             |
|--------|-------------------------------------------------------------|-----------------------------------------|
| POST   | /api/post/user/{userId}/category/{categoryId}/posts         | Create a post for user and category     |
| GET    | /api/post/all                                               | Get all posts (pagination & sorting)    |
| GET    | /api/post/{postId}                                          | Get post by ID                          |
| GET    | /api/post/user/{userId}/posts                               | Get all posts by a user                 |
| GET    | /api/post/category/{categoryId}/posts                       | Get all posts by category               |
| GET    | /api/post/search/{keyword}                                  | Search posts by title keyword           |
| POST   | /api/post/image/upload/{postId}                             | Upload image for a post                 |
| GET    | /api/post/image/{imageName}                                 | Serve post image                        |
| PUT    | /api/post/{postId}                                          | Update post                             |
| DELETE | /api/post/{postId}                                          | Delete post                             |

### 🗂️ Category

| Method | Endpoint                 | Description           |
|--------|--------------------------|-----------------------|
| POST   | /api/category/           | Create a new category |
| GET    | /api/category/all        | Get all categories    |
| GET    | /api/category/{catId}    | Get category by ID    |
| PUT    | /api/category/{catId}    | Update category       |
| DELETE | /api/category/{catId}    | Delete category       |

### 💬 Comment

| Method | Endpoint                          | Description           |
|--------|-----------------------------------|-----------------------|
| POST   | /api/post/{postId}/comments       | Add comment to a post |
| DELETE | /api/comments/{commentId}         | Delete comment by ID  |

### 🔔 Notification  (Future Update)

| Method | Endpoint                    | Description                         |
|--------|-----------------------------|-------------------------------------|
| GET    | /api/notifications          | Get all notifications for the user  |
| POST   | /api/notifications          | Create a new notification           |
| PUT    | /api/notifications/{id}     | Update a notification by ID         |
| DELETE | /api/notifications/{id}     | Delete a notification by ID         |


## 💾 Database

The project uses a MySQL database with the following key entities:

- **User**: Stores user details and roles  
- **Role**: User roles like ADMIN and USER  
- **Post**: Blog posts linked to users and categories  
- **Category**: Categories grouping posts  
- **Comment**: Comments linked to posts  

### 📊 Entity-Relationship (ER) Diagram

![ER Diagram](/ER-Diagram.png)


## 🛠 Future Enhancements

- **Notification Module**: Implement real-time user notifications and alerts via email or in-app messages.  
- **Advanced Image Handling**: Support for image resizing, compression, and CDN integration for faster delivery.  
- **Enhanced Search**: Add filtering, sorting, and tagging capabilities to improve content discoverability.  
- **User Activity Analytics**: Track user engagement, post views, and behavior for actionable insights.  
- **Role & Permission Management**: Introduce granular permission levels beyond basic roles.  
- **Social Features**: Enable features like post sharing, likes, and follower systems.  
- **API Rate Limiting and Throttling**: Protect the API from abuse and ensure fair usage.  
- **Automated Testing**: Add unit and integration tests for improved code quality and reliability.  
- **Deployment Automation**: Implement CI/CD pipelines for seamless application updates.

## 🙏 Thank You

Thank you for taking the time to explore this Blog Application.  
Your feedback and contributions are always welcome to help improve this project.  

Feel free to reach out for any questions, suggestions, or collaboration opportunities!

Happy coding! 🚀


