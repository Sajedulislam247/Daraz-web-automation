# Daraz Automation Project - Playwright Java, TestNG and Allure

This repository contains an automated test suite for a web application, developed using **Playwright with Java** and the **TestNG** framework. It follows the **Page Object Model (POM)** design pattern for scalability and uses **Allure Reports** for comprehensive, interactive reporting.

---

### 1. Technology Stack

| Technology | Purpose |
| :--- | :--- |
| **Java** | Primary programming language |
| **Playwright** | Core automation library for browser interaction |
| **TestNG** | Testing framework for execution and structure |
| **Allure Reports** | Interactive reporting tool for detailed test analysis |
| **Gradle** | Build automation and dependency management |
| **Test Data File** | Stores dynamic test data (`testdata.properties`) |

### 2. Prerequisites

Before running the tests, ensure you have the following installed:

* **Java Development Kit (JDK):** Version 11 or higher.
* **Allure Command Line Tool:** Necessary to generate and view the reports. Install it via a package manager (like Scoop or Homebrew) or download the binary from the [Allure Framework website](https://docs.qameta.io/allure/latest/manual/install/).

### 3. Setup and Installation

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/BrainStation23HR/QA_Sajedul-Islam/tree/main
    cd src
    ```

2.  **Download Dependencies:**
    Use the included Gradle wrapper to download all required libraries, including Playwright and Allure dependencies:
    ```bash
    # On Linux/macOS
    ./gradlew build

    # On Windows
    gradlew.bat build
    ```

3.  **Configure Test Data:**
    All dynamic test parameters (like credentials, URLs, and search queries) are managed in the properties file. Update the values in `src/main/resources/testdata.properties` before execution.

### 4. Project Structure (Page Object Model)

The project adheres to the POM pattern, separating test logic from page element interaction:

| Folder/File | Description |
| :--- | :--- |
| `src/main/java/.../page` | Contains **Page Object** classes (`HomePage.java`, `LoginPage.java`, etc.) that hold elements and methods specific to a web page. |
| `src/test/java/.../web` | Contains the actual **Test Classes** that use TestNG annotations to define the workflows. |
| `src/main/resources` | Stores configuration (`log4j2.xml`) and external data (`testdata.properties`). |
| `build.gradle` | Configuration file that manages dependencies and Allure integration. |

---

## Test Execution and Reporting

### 5. Test Scenarios Covered

This suite executes **5 critical test cases**, each including specific validations:

| # | Test Case Name | Workflow Validated | Key Validation/Expected Result |
| :--- | :--- | :--- | :--- |
| 1 | **Login with Valid Credentials** | User authentication flow. | Successful redirection and presence of a specific element (e.g., User Profile Icon). |
| 2 | **Search for Product** | Core site search functionality. | Search results page loads with all returned items matching the query from the data file. |
| 3 | **Add First Item to Cart** | Adding a product from the result page. | Cart counter update and item confirmation in the cart view. |
| 4 | **Add Second Item to Cart** | Adding multiple items to the cart. | Cart quantity increments correctly, and both items are correctly listed. |
| 5 | **Logout** | Secure session termination. | Redirection to the public home/login page and removal of user session artifacts. |

### 6. Running the Tests

Execute the tests and generate the raw Allure results using the Gradle wrapper:

```bash
# Runs tests and generates raw JSON/TXT files in the build/allure-results directory.
./gradlew clean test
```

### 7. Generating and Viewing Allure Reports
After the tests are complete, use the Allure command line tool to process the results and open the interactive HTML report in your browser:

Bash
```
# Generates the report and serves it on a local web server (e.g., [http://0.0.0.0](http://0.0.0.0):port)
allure serve build/allure-results
```

### 8. Test Results Screenshot
The screenshot below illustrates the dashboard of the Allure Report, summarizing the execution status of the 5 implemented test cases:
<img width="1364" height="605" alt="image" src="https://github.com/user-attachments/assets/8bacf8cc-3e66-470d-b2a0-2d315c0b1864" />

<img width="1366" height="606" alt="image" src="https://github.com/user-attachments/assets/adf0bbc1-ea86-4fa2-99a4-587691833acc" />

<img width="1361" height="610" alt="image" src="https://github.com/user-attachments/assets/5b6287c9-0c46-4c2b-8fd7-a095cabe2341" />



