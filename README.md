# 🐍 Cobra Banking System

A CORBA-based distributed banking system built with Java. Supports balance inquiry, deposit, and withdrawal via client-server architecture using IDL interface definitions.

---

## 📌 Overview

This project demonstrates **distributed computing** using Java CORBA (Common Object Request Broker Architecture). A client (ATM) communicates with a remote banking server through a CORBA ORB and NameService — without needing to know the server's physical location.

---

## 🏗️ Architecture

```
ATMClient ──→ NameService ("Bank") ──→ BankServer ──→ AccountImpl
                  (CORBA ORB)               (POA)        (In-memory DB)
```

| Component | Description |
|---|---|
| `Account.idl` | Interface Definition Language file — defines the banking contract |
| `BankServer.java` | CORBA server — registers the service with NameService |
| `AccountImpl.java` | Banking logic — handles balance, deposit, withdrawal |
| `ATMClient.java` | Client — connects via NameService and performs operations |

---

## ✨ Features

- ✅ Check account balance
- ✅ Deposit funds
- ✅ Withdraw funds (with `InsufficientBalance` exception handling)
- ✅ CORBA ORB-based client-server communication
- ✅ IDL-defined `Account` interface
- ✅ POA (Portable Object Adapter) server-side implementation

---

## 🚀 Getting Started

### Prerequisites

- Java 8 (CORBA is deprecated in Java 9+)
- `idlj` compiler (included in JDK 8)

### 1. Compile the IDL file

```bash
idlj -fall Account.idl
```

### 2. Compile all Java files

```bash
javac -d . *.java BankingApp/*.java
```

### 3. Start the CORBA Name Service

```bash
orbd -ORBInitialPort 1050 &
```

### 4. Start the Bank Server

```bash
java com.hirusha.bcd.server.BankServer -ORBInitialPort 1050 -ORBInitialHost localhost
```

### 5. Run the ATM Client

```bash
java com.hirusha.bcd.client.ATMClient -ORBInitialPort 1050 -ORBInitialHost localhost
```

---

## 💳 Demo Accounts

| Account Number | Initial Balance |
|---|---|
| `ACC001` | Rs. 10,000.00 |
| `ACC002` | Rs. 20,000.00 |
| `ACC003` | Rs. 30,000.00 |

---

## 📁 Project Structure

```
cobra-banking-system/
├── src/
│   ├── com/hirusha/bcd/
│   │   ├── client/
│   │   │   └── ATMClient.java
│   │   └── server/
│   │       ├── BankServer.java
│   │       └── AccountImpl.java
│   └── Account.idl
└── README.md
```

---

## 🛠️ Built With

- **Java 8** — Core language
- **Java CORBA / ORB** — Distributed object communication
- **IDL (Interface Definition Language)** — Interface contract
- **POA (Portable Object Adapter)** — Server-side object management
- **CosNaming / NameService** — Service discovery

---

## ⚠️ Note

Java CORBA has been **deprecated since Java 9** and **removed in Java 11**. This project is intended for **educational purposes** to understand distributed systems and middleware concepts. Modern alternatives include **gRPC** and **REST APIs**.

---

## 👨‍💻 Author

**Hirusha** — [GitHub](https://github.com/hirusha)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
