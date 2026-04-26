# Nexus Banking System (v2.0 - SQL Integrated)

A professional-grade console application built to demonstrate **Relational Database Management (RDBMS)**, **Dependency Management**, and **Clean Backend Architecture**.

## 🚀 Recent Upgrades
- **Database Integration:** Migrated from `.txt` files to a **MySQL** database for reliable data persistence.
- **Maven Migration:** Project now uses **Maven** (`pom.xml`) for automated dependency and build management.
- **Java 21 support:** Optimized for the latest Long-Term Support (LTS) Java version.
- **Prepared Statements:** Implemented SQL logic to prevent SQL Injection attacks.

## 🛠️ Technical Stack
- **Language:** Java 21
- **Database:** MySQL 8.x
- **Connector:** MySQL Connector/J (JDBC)
- **Build Tool:** Maven
- **Architecture:** Controller-DAO Pattern

## 📖 Key Learnings
- **Data Integrity:** Ensuring transactions (Deposit/Withdraw) are reflected accurately in a database.
- **Environment Parity:** Aligning local JDK versions with Maven compiler settings.
- **Standard Directory Layout:** Organizing code into `src/main/java` as per industry standards.

## 🖥️ Database Schema
```sql
CREATE TABLE accounts (
    account_number VARCHAR(10) PRIMARY KEY,
    account_holder VARCHAR(100) NOT NULL,
    balance DECIMAL(15, 2) DEFAULT 0.0
);



## ⚙️ Setup & Installation
1. Clone the repository: `git clone [Your-Repo-Link]`
2. Create the MySQL database using the schema provided above.
3. Update the `DB_URL`, `USER`, and `PASS` in `DatabaseHandler.java`.
4. Build and Run:
   - Using VS Code: Click the **Run** icon on `BankingApp.java`.
   - Using Maven: `mvn compile exec:java -Dexec.mainClass="BankingApp"`

   