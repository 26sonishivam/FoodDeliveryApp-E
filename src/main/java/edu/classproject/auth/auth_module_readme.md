# 🔐 Authentication Module (Auth + User Service)

## 📌 Overview
This module implements a simple **authentication system** with:
- User signup
- Login validation
- Session management (login/logout)

It is designed as part of a modular backend architecture where:
- **User Service** handles user data (credentials)
- **Auth Service** handles authentication and sessions

---

## 🧱 Architecture

```
AuthDemo (Client)
      ↓
AuthService (Interface)
      ↓
InMemoryAuthService
      ↓
UserService (Interface)
      ↓
InMemoryUserService
```

---

## 📁 Project Structure

```
edu.classproject
│
├── user
│   ├── UserService.java
│   └── InMemoryUserService.java
│
└── auth
    ├── AuthService.java
    ├── AuthSession.java
    ├── InMemoryAuthService.java
    ├── AuthDemo.java
    └── AuthTest.java
```

---

## 🔑 Features

### 1. Signup
- Stores user email and password in memory
- Prevents duplicate users

### 2. Login
- Validates credentials using `UserService`
- Creates a session on success

### 3. Session Management
- Generates unique session IDs
- Tracks session expiry (2 hours)
- Supports logout

---

## 🧠 How It Works

### 🔐 Signup Flow
```
signup(email, password)
    → stored in users HashMap
    → auto-login (creates session)
```

### 🔑 Login Flow
```
login(email, password)
    → validate via UserService
    → create session (UUID)
    → store in sessions map
```

### 🚪 Logout Flow
```
logout(sessionId)
    → remove session from map
```

### 🔍 Session Check
```
isSessionActive(sessionId)
    → check if exists
    → check expiry time
```

---

## 📦 Data Storage

### User Repository (Permanent)
```
Map<String, String> users
email → password
```

### Session Repository (Temporary)
```
Map<String, AuthSession> sessions
sessionId → AuthSession
```

---

## ⚠️ Limitations

- Passwords stored in plain text ❌
- Data lost on restart (in-memory storage) ❌
- No real user validation (no DB) ❌
- No security (no hashing/JWT) ❌

---

## 🚀 Future Improvements

- 🔐 Add password hashing (BCrypt)
- 🗄 Replace HashMap with database (PostgreSQL)
- ⚡ Use Redis for session storage
- 🔑 Implement JWT-based authentication
- 👤 Add User model (ID, roles, etc.)

---

## ▶️ How to Run

1. Compile all Java files
2. Run:
```
AuthDemo.java
```

Expected output:
- User signs up
- Session created
- Login works
- Logout invalidates session

---

## 🧾 Summary

- Users are stored in **UserService (HashMap)**
- Sessions are stored in **AuthService (HashMap)**
- Login validation is handled via **UserService**
- AuthService manages session lifecycle

---

## 👥 Authors

- Shivam Soni
- Shreesha Nallur
- Rose Singh Bisen
- Saanvi Manjunath

---

## 📚 Notes

This is a **basic educational implementation** of authentication, suitable for understanding:
- Modular design
- Separation of concerns
- Session-based authentication

