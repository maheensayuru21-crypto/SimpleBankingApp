# Simple Banking Application (Java)

A console-based banking system built with core Java, focusing on **Object-Oriented Programming (OOP)** and **Data Persistence**.

## 🚀 Features
- **User Registration:** Dynamically generate unique 4-digit account numbers.
- **Secure Login:** Session-based access to specific account data.
- **Stateful Transactions:** Deposit and withdraw funds with real-time balance updates.
- **Data Persistence:** All account information is saved to a local flat-file database (`accounts.txt`).
- **Error Handling:** Robust validation for numeric inputs and insufficient funds.

## 🛠️ Technical Stack
- **Language:** Java 17+
- **Data Structure:** `HashMap` for O(1) account lookups.
- **Storage:** File I/O (CSV-style parsing).
- **Version Control:** Git/GitHub with Feature-Branch workflow.

## 📖 What I Learned (Backend Focus)
- **Separation of Concerns:** Split logic into Model (`Account`), Controller (`BankingApp`), and Data Access (`FileHandler`).
- **Encapsulation:** Used private fields and public getters to protect sensitive financial data.
- **Defensive Programming:** Implemented `try-catch` blocks to handle malformed user input.
