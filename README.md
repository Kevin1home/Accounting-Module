# Financial Reporting System

**Module: Accounting and Financial Analysis**  
*A console-based Java application for loading, validating, and analyzing monthly and yearly financial reports.*

---

## Overview

This tool is designed for businesses to manage and analyze financial reports efficiently.  
It allows users to:

- Load monthly and yearly reports from CSV files  
- Reconcile monthly and yearly data  
- Identify maximum profits and expenses  
- Calculate monthly profits and average yearly statistics

---

## Features

- Load all monthly financial reports (CSV)  
- Load yearly financial report (CSV)  
- Reconcile monthly and yearly reports to detect discrepancies  
- Display monthly report summaries:
  - Highest profit service
  - Highest expense item
- Display yearly report summaries:
  - Monthly profits
  - Average monthly profit and expenses

---

## Architecture

- **`ReportingApp`** – Main application entry point (console UI)  
- **`FileReader`** – Reads CSV files from the resources directory  
- **`Transaction`** – Represents a single financial transaction (profit or expense)  
- **`MonthlyReport`** – Stores and analyzes transactions for a specific month  
- **`YearlyReport`** – Stores and analyzes financial results for the entire year  
- **`MonthTotalPerYear`** – Collection of monthly reports  
- **`ReportEngine`** – Performs reconciliation and validation between monthly and yearly reports  

---

## Usage

Compile and run:

```bash
javac -d out src/app/ReportingApp.java src/io/FileReader.java src/model/*.java src/service/ReportEngine.java
java -cp out app.ReportingApp
```

Menu options:

```
1 - Load all monthly reports
2 - Load yearly report
3 - Reconcile monthly and yearly reports
4 - Print monthly reports summary
5 - Print yearly report summary
6 - Exit
```

---

## Example Output

```
Welcome to Financial Reporting System!

Select an option:
1. Load all monthly reports
2. Load yearly report
3. Reconcile monthly and yearly reports
4. Print monthly reports summary
5. Print yearly report summary
6. Exit
> 1
Monthly reports loaded successfully

Select an option:
> 2
Yearly report loaded successfully

Select an option:
> 3
Monthly and yearly reports match in expenses.
Monthly and yearly reports match in profit.

Select an option:
> 4
Year 2021. Month 1.
Highest profit service: [Profit_2]
Amount: [1050000]
Highest expense: [Expense_3]
Amount: [150000]

Year 2021. Month 2.
Highest profit service: [Profit_2]
Amount: [450000]
Highest expense: [Expense_2]
Amount: [10000]

Year 2021. Month 3.
Highest profit service: [Profit_3]
Amount: [1500000]
Highest expense: [Expense_3]
Amount: [60000]

Select an option:
> 5
Year 2021.
Profit in month #1 is: 1243150
Profit in month #2 is: 796000
Profit in month #3 is: 2340000
Average expenses per year: 151333
Average profit per year: 1611050

Select an option:
> 6
Type EXIT to confirm quitting:
> EXIT
Exiting program...
```

---

## Requirements

- Java 17+  
- No external dependencies (standard Java library only)  
- CSV files should be placed in the `resources` directory with names like `m.202101.csv` (monthly) and `y.2021.csv` (yearly)

---

## License

Released under the **MIT License**.
