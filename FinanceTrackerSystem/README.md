# Finance Tracker System

## 📋 Overview

The **Finance Tracker System** is a comprehensive Java-based CLI application designed to help users manage their personal finances. It supports tracking accounts, budgets, categories, transactions, and user profiles, providing a robust platform for financial organization and analysis.

---

## 🚀 Features & Functionality

### 1. **User Management**

- **Registration & Login:** Users can register and securely log in.
- **Profile Management:** Edit personal details, view profile, and manage user types (admin/user).
- **Data Storage:** User details are stored in `Files/UsersDetails.txt`.

### 2. **Account Management**

- **Create/Edit/Delete Accounts:** Manage multiple financial accounts (e.g., savings, checking).
- **View Account Details:** See balances, account types, and transaction history.

### 3. **Budget Management**

- **Set Budgets:** Define budgets for categories or overall spending.
- **Edit/Delete Budgets:** Update or remove budgets as needed.
- **Budget Tracking:** Monitor spending against set budgets.

### 4. **Category Management**

- **Custom Categories:** Create, edit, and delete categories for transactions (e.g., Food, Rent).
- **Category Types:** Supports category types via `Models/enums/CategoryType.java`.

### 5. **Transaction Management**

- **Add/Edit/Delete Transactions:** Record income and expenses, assign to accounts and categories.
- **Transaction History:** View, filter, and search transaction records.
- **Reporting:** Summarize transactions by date, category, or account.

---

## 📂 Project Structure

```
FinanceTrackerSystem/
│
├── Main.java
├── Controllers/
│   ├── AccountController.java
│   ├── BudgetController.java
│   ├── CategoryController.java
│   ├── TransactionController.java
│   ├── UserController.java
│   └── UserProfileController.java
├── Menus/
│   ├── AccountMenu.java
│   ├── BudgetMenu.java
│   ├── CategoryMenu.java
│   ├── ProfileMenu.java
│   └── TransactionMenu.java
├── Models/
│   ├── Account.java
│   ├── Budget.java
│   ├── Category.java
│   ├── Transaction.java
│   ├── User.java
│   ├── UserProfile.java
│   └── enums/
│       ├── CategoryType.java
│       └── UserType.java
├── Files/
│   └── UsersDetails.txt
├── docs/
│   └── [Javadoc HTML documentation]
├── resources/
│   └── [Images and assets]
├── legal/
│   └── [Licenses and attributions]
├── .dist/
│   └── [Distribution files]
├── sources.txt
├── FINANCE TRACKER SYSTEM.pdf
├── Finance Tracking Discussion sketch.jpg
├── Finance Tracking Discussion.docx
└── Improvements in Finance Tracking System.docx
```

---

## 📖 Documentation (`docs/`)

The `docs/` folder contains **auto-generated Javadoc HTML documentation** for all packages, classes, and methods in the system.

- **index.html:** Entry point for documentation.
- **overview-summary.html:** High-level summary of the system.
- **FinanceTrackerSystem/**: Package-level documentation for Controllers, Menus, Models, and their classes.
- **allclasses-index.html:** Alphabetical list of all classes.
- **constant-values.html:** List of constants used in the system.
- **serialized-form.html:** Serialization details for classes.
- **package-summary.html / package-tree.html:** Structure and hierarchy of packages.
- **element-list, member-search-index.js, type-search-index.js, etc.:** Search and navigation aids.

**How to Use:**

- Open `docs/index.html` in your browser.
- Navigate through packages, classes, and methods for detailed API references.
- Use search features to quickly find classes or methods.

---

## 📑 Additional Resources

- **FINANCE TRACKER SYSTEM.pdf:** Full system documentation.
- **Finance Tracking Discussion sketch.jpg:** Visual sketch of system workflow.
- **Finance Tracking Discussion.docx:** Meeting notes and requirements.
- **Improvements in Finance Tracking System.docx:** Suggestions and planned enhancements.
- **sources.txt:** Additional setup/configuration notes.

---

## ⚙️ Setup & Usage

### 1. **Pre-requisites**

- **Java 18** or compatible JDK installed.
- Ensure CLI access to compile and run Java files.

### 2. **Configuration**

- **File Paths:** Update file paths in source files (e.g., `UserController.java`, `TransactionController.java`) to match your environment if necessary.
  - Especially check references to `Files/UsersDetails.txt` and other data files.
- **Permissions:** Ensure read/write access to the `Files/` directory.

### 3. **Compilation**

- Compile all Java files in `Controllers/`, `Menus/`, and `Models/`:
  ```sh
  javac Main.java Controllers/*.java Menus/*.java Models/*.java Models/enums/*.java
  ```
- Run the application:
  ```sh
  java Main
  ```

### 4. **Data Storage**

- All user and transaction data is stored locally in `Files/UsersDetails.txt`.
- **Backup regularly** to prevent data loss.

---

## 🛠️ Development Notes

- **Modular Design:** Each functionality is separated into Controllers, Menus, and Models for maintainability.
- **Extensible:** Easily add new features or categories via the respective controllers and models.
- **CLI-Based:** All interactions are via command-line interface for simplicity and portability.

---

## 📝 TODO Before Running

- [ ] Update file paths in source code for your environment.
- [ ] Ensure Java is installed and configured.
- [ ] Compile all source files.
- [ ] Check and set file permissions for `Files/` directory.
- [ ] Review `sources.txt` for any additional setup steps.
- [ ] Backup `Files/UsersDetails.txt` regularly.

---

## ⚠️ Important Points

- **Local Data Storage:** All data is stored locally; there is no cloud backup.
- **Documentation:** Refer to `docs/` for API/class/method details.
- **Licensing:** See `legal/` for licenses and attributions.
- **Resources:** All images and assets are in `resources/`.

---

## 📬 Support & Contributions

For issues, suggestions, or contributions:

- Refer to the documentation and discussion notes.
- Review the improvement document for planned features.
- Submit issues or pull requests as per project guidelines.

---

## 📚 References

- [Javadoc Documentation](docs/index.html)
- [System Documentation PDF](FINANCE TRACKER SYSTEM.pdf)
- [Discussion Notes](Finance Tracking Discussion.docx)
- [Improvements Document](Improvements in Finance Tracking System.docx)

---

\*\*© Guru Charan
