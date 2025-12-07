# 🧠 Knowledge Base Management System (Java + MongoDB) — Mac Version

## 📘 Project Overview

The **Knowledge Base Management System** is a Java-based console application integrated with **MongoDB** to create, store, organize, and retrieve informational articles. It demonstrates full **CRUD operations** (Create, Read, Update, Delete) with robust data management, validation, and MongoDB integration using the official Java driver.

This project was developed as part of the **GUVI HCL Technical Training Program** (Batch: 2022–2026) under the  **Department of Information Technology, SVCET College** , and serves as an educational demonstration of real-world database application development.

---

## 🎯 Aim

To design and develop a **comprehensive Knowledge Base Management System** that efficiently stores, organizes, and retrieves informational articles using **Java** and  **MongoDB** , implementing complete CRUD operations through a user-friendly console interface.

---

## ⚙️ Features

✅ Java–MongoDB Integration
✅ CRUD Operations for Knowledge Articles
✅ Search Functionality (Pattern Matching)
✅ Duplicate ID Prevention
✅ Input Validation & Error Handling
✅ Modular, Well-Commented Java Code
✅ Clear Console Menu Navigation

---

## 📂 Project Structure

```
KnowledgeBaseSystem/
├── .vscode/
├── lib/
│   ├── bson-4.10.2.jar
│   ├── mongodb-driver-core-4.10.2.jar
│   └── mongodb-driver-sync-4.10.2.jar
├── KnowledgeBaseApp.java
├── KnowledgeBaseApp.class
└── README.md
```

---

## 🖥️ System Requirements

### Software Requirements

* **Operating System:** macOS 10.14+ (Recommended: macOS 12+)
* **Java Development Kit:** JDK 8 or higher (Recommended: JDK 11/17)
* **Database:** MongoDB 4.4+ (Recommended: 6.0+)
* **IDE:** Visual Studio Code, IntelliJ IDEA, Eclipse, or NetBeans
* **JAR Dependencies:**
  * mongodb-driver-sync-4.10.2.jar
  * mongodb-driver-core-4.10.2.jar
  * bson-4.10.2.jar

### Hardware Requirements

* **Processor:** Dual Core or above (Recommended: Intel i5 or Ryzen 5)
* **RAM:** Minimum 4 GB (Recommended 8 GB)
* **Storage:** 2 GB free space
* **Network:** Localhost access for MongoDB

---

## 🧰 Installation & Setup (macOS)

### Step 1: Install Java

Verify installation:

```bash
java -version
javac -version
```

Expected Output:

```
java version "11.0.15" 2022-04-19 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.15+8-LTS-149)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.15+8-LTS-149, mixed mode)
```

---

### Step 2: Install MongoDB

Install MongoDB using  **Homebrew** :

```bash
brew tap mongodb/brew
brew install mongodb-community
```

#### 🧾 Run MongoDB on macOS

Once installed, you need to manually **start the MongoDB server** before running your Java program.

##### 1️⃣ Open the **first terminal** and run:

```bash
sudo mongod --dbpath=/Users/adenasivareddy/data/db
```

> Replace `/Users/adenasivareddy` with your mac username, for example:
>
> ```bash
> sudo mongod --dbpath=/Users/yourUserName/data/db
> ```

If the directory doesn’t exist, create it:

```bash
mkdir -p /Users/yourUserName/data/db
```

##### 2️⃣ Open a **second terminal** and run:

```bash
mongosh
```

If `mongod` is running properly, this will connect and you’ll see a prompt like:

```bash
test>
```

Congrats, that means it’s working ✅



---

### Step 3: Project Compilation & Execution

```bash
# Navigate to project directory
cd KnowledgeBaseSystem

# Compile the Java program
javac -cp "lib/*" KnowledgeBaseApp.java

# Run the application
java -cp ".:lib/*" KnowledgeBaseApp
```

---

## 🧮 Functional Modules

| Module         | Description                                                   |
| -------------- | ------------------------------------------------------------- |
| Create Article | Add a new article with ID, title, content, category, and tags |
| View Articles  | Display all existing articles                                 |
| Find by ID     | Retrieve a specific article using its unique ID               |
| Search         | Search articles by keyword in title or content                |
| Update         | Modify an article’s details                                  |
| Delete         | Remove an article from the database                           |

---

## 🧾 Example Output

```
Connected to MongoDB successfully!

=== Knowledge Base System ===
1. Create new article
2. View all articles
3. Find article by ID
4. Search articles
5. Update article
6. Delete article
7. Exit
Choose an option: 1
=== Create New Article ===
Enter article ID: 101
Enter title: MongoDB CRUD Operations
Enter content: Introduction to insert, find, update, delete
Enter category: Database
Enter tags (comma separated): mongodb,crud,database
Article created successfully with ID: 101
```

---

## 🧪 Testing

* **Method:** Manual and Integration testing
* **Test Cases:** CRUD operations, input validation, error handling
* **Status:** All modules tested successfully

---

## 🧩 Conclusion

The **Knowledge Base Management System** successfully demonstrates seamless integration of **Java** and  **MongoDB** , fulfilling all project objectives:

* ✅ Full CRUD operations
* ✅ Robust data handling
* ✅ Error-free execution
* ✅ Educational value for database-driven Java development

This project can be further extended with a  **web-based frontend** ,  **user authentication** , and  **advanced search algorithms** .

---

## 🔗 GitHub Repository

**Repository Link:** [https://github.com/harireddy0106/knowledge-base-system](https://github.com/harireddy0106/knowledge-base-system)

---

## 👨‍💻 Author

**Name:** ADENA HARI PRASAD REDDY
**Regd. No.:** 22781A1201
**Department:** Information Technology
**Institution:** Sri Venkateswara College of Engineering and Technology (Autonomous), Chittoor
**Under the Guidance of:** GUVI HCL Technical Trainer *P. Ragavan*
