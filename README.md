# 🏦 Nexus Banking System (v2.0 - SQL Integrated)

A professional-grade backend application built to demonstrate **Relational Database Management (RDBMS)**, **Dependency Management**, and **Clean Backend Architecture**.

## 🚀 Recent Upgrades
- **Database Integration:** Migrated from `.txt` files to a **MySQL** database for reliable data persistence.
- **Cryptographic Security:** Added SHA-256 hashing for all stored user passwords.
- **Maven Migration:** Project now uses **Maven** (`pom.xml`) for automated dependency and build management.
- **Java 21 Support:** Optimized for the latest Long-Term Support (LTS) Java version.
- **Prepared Statements:** Implemented strict SQL logic to prevent SQL Injection attacks.

## 🛠️ Technical Stack
- **Language:** Java 21
- **Database:** MySQL 8.x
- **Connector:** MySQL Connector/J (JDBC)
- **Build Tool:** Maven

## 🖥️ Database Schema
This microservice relies on a multi-table relational schema:

```sql
CREATE TABLE accounts (
    account_number VARCHAR(10) PRIMARY KEY,
    account_holder VARCHAR(100) NOT NULL UNIQUE,
    balance DECIMAL(15, 2) DEFAULT 0.0,
    password VARCHAR(255) NOT NULL,
    is_frozen BOOLEAN DEFAULT FALSE
);

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender VARCHAR(100) NOT NULL,
    recipient VARCHAR(100) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    transfer_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE offline_messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender VARCHAR(100) NOT NULL,
    recipient VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## 🔗 The Nexus Ecosystem
This banking system is no longer just a standalone application—it acts as the foundational data layer for a microservice architecture. 

It is integrated with the **[Nexus Chat Server](https://github.com/M4H33N-dev/Nexus-Chat-Server)**, allowing users to securely query their database balances (`/balance`) and execute live, multi-threaded SQL transactions (`/transfer`) directly through an encrypted JavaFX chat interface.

## ⚙️ Setup & Installation
1. Clone the repository: `git clone https://github.com/M4H33N-dev/Nexus-Banking-System.git`
2. Create the MySQL database: `CREATE DATABASE bank_db;`
3. Execute the schema provided above to build the tables.
4. Update the `URL`, `USER`, and `PASSWORD` in `DatabaseManager.java`.
5. Build and Run using Maven: 
   `mvn compile exec:java -Dexec.mainClass="BankingApp"`