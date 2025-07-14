# SkillBarter - Skill Exchange Platform

SkillBarter is a full-stack web application built with Angular and Spring Boot that allows users to **share, learn, and exchange skills**. Users can create profiles, post skills to teach or learn, and request sessions with others.

---

## 🧱 Project Structure

![image](https://github.com/user-attachments/assets/886cc23b-7701-4404-a336-b42353245a91)

---

## 🚀 How to Run the Project Locally

### ⚙️ Backend (Spring Boot)

> Prerequisites:  
> - Java 17+  
> - Maven  
> - MySQL (configured in `application.properties`)

```bash
cd backend
```

### 1. Configure your application.properties file (usually found in src/main/resources/) with your DB credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/skillbarter
spring.datasource.username=root
spring.datasource.password=yourpassword

### 2. Build and run the Spring Boot application:
```bash
mvn clean install
mvn spring-boot:run
```
By default, the backend runs on http://localhost:8080/skill-barter.

Frontend (Angular 16+ with SSR)
Prerequisites:

Node.js 18+

Angular CLI v20.0+
```bash
cd frontend
```

### 1. Install dependencies:
```bash
npm install
```

### 2. Run Angular in development mode:
```bash
npm run start
```
Frontend will be available at http://localhost:4200.

## API Endpoints Overview (Spring Boot)
| Method | Endpoint                         | Description                      |
| ------ | -------------------------------- | -------------------------------- |
| GET    | `/connect`                       | Health check                     |
| POST   | `/user/signup`                   | Register new user                |
| POST   | `/user/login`                    | Login with email/password        |
| GET    | `/user/profile/{userId}`         | Fetch user profile               |
| PUT    | `/user/updateProfile/{userId}`   | Update profile (verify password) |
| GET    | `/user/{userId}/skills`          | Get all posted skills by user    |
| POST   | `/post/{userId}/skill`           | Post a new skill                 |
| DELETE | `/user/{userId}/skill/{skillId}` | Delete a skill                   |
| PUT    | `/user/{userId}/skill/{skillId}` | Update skill by ID               |
| GET    | `/skills/search`                 | Search/filter/sort public skills |

## Features

Full login/signup flow
Edit profile with password verification
Skill posting (teach/learn intent)
Skill filtering, search, sort
Session request support
Responsive UI using Bootstrap 5

## Tech Stack

Frontend: Angular 16+, Angular Standalone Components, Bootstrap Icons
Backend: Spring Boot 3, REST APIs, JPA/Hibernate
Database: MySQL 8
ORM: Spring Data JPA
Build Tools: Maven, Angular CLI

## Notes

This project supports Server Side Rendering (SSR) using Angular Universal.
Bootstrap is used for styling; Bootstrap JS is conditionally imported to avoid SSR errors.
Make sure userId is stored after login to access protected APIs.
You can set up your own Cloudinary/Avatar services for profile images.

## ScreenShots
|Home page|
|---------|
![image](https://github.com/user-attachments/assets/9ca3ea8c-64ef-40fa-a6c3-207eb281914d)

|Profile page|
|---------|
![image](https://github.com/user-attachments/assets/b8efeaad-8ee8-440c-a4dd-970ffd6aab5d)

|Post Skill page|
|---------|
![image](https://github.com/user-attachments/assets/f6bb9f7b-3bf9-42ae-9cad-00a95c6c554c)

|Matches page|
|---------|
![image](https://github.com/user-attachments/assets/6bfb81b7-c859-4b7e-bccc-ab5f1af2b193)

|Home page|
|---------|
![image](https://github.com/user-attachments/assets/2b8e05b7-2c71-4016-bde0-19baf9f1d7a2)

## Developer
Name: Nitish
Goal: Full-stack Developer | Passionate about Java, Angular, and clean architecture

License
This project is licensed under the MIT License.


















