# 🏥 Hospital Management System (HMS)

A full-stack backend application built using **Java Spring Boot** to manage hospital operations like patients, doctors, and appointments efficiently.

---

## 🚀 Tech Stack

* **Backend:** Java, Spring Boot
* **Database:** MySQL
* **ORM:** Spring Data JPA (Hibernate)
* **Build Tool:** Maven
* **API Testing:** Postman
* **Version Control:** Git & GitHub

---

## 📌 Features

### 👨‍⚕️ Doctor Management

* Add, update, delete doctors
* Manage specialization and availability

### 🧑‍🤝‍🧑 Patient Management

* Register new patients
* Store medical history and personal details

### 📅 Appointment Management

* Book appointments with doctors
* Track appointment status

### 🔄 REST APIs

* Clean and structured API endpoints
* Follows MVC architecture (Controller → Service → Repository)

---

## 📁 Project Structure

```
com.hms
 ┣ controller
 ┣ service
 ┣ repository
 ┣ entity
 ┣ dto
 ┗ config
```

---

## ⚙️ How to Run the Project

### 1️⃣ Clone Repository

```
git clone https://github.com/your-username/hospital-management-system.git
cd hospital-management-system
```

### 2️⃣ Configure Database

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/hms_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️⃣ Run Application

```
mvn clean install
mvn spring-boot:run
```

---

## 🌐 API Endpoints (Sample)

| Method | Endpoint          | Description      |
| ------ | ----------------- | ---------------- |
| GET    | /api/doctors      | Get all doctors  |
| POST   | /api/doctors      | Add new doctor   |
| GET    | /api/patients     | Get all patients |
| POST   | /api/patients     | Add new patient  |
| POST   | /api/appointments | Book appointment |

---

## 🧠 Key Learnings

* Built REST APIs using Spring Boot
* Implemented layered architecture
* Integrated MySQL with JPA/Hibernate
* Handled real-world entities and relationships

---

## 🚀 Future Enhancements

* 🔐 JWT Authentication & Role-Based Access
* 🧾 Billing & Payment Module
* 📊 Admin Dashboard
* 🌍 Deployment on cloud (AWS / Render)
* ⚛️ React Frontend Integration

---

## 🤝 Contributing

Contributions are welcome! Feel free to fork the repo and submit pull requests.

---

## 📧 Contact

**Mohammed Kaif Dalvi**
📩 Feel free to connect for collaboration or opportunities

---

⭐ If you like this project, give it a star on GitHub!
