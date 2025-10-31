created employeementDatabaseManagement APP using java and my sql
Java JDBC app to perform CRUD operations on MySQL.
## Features
- Add, View, Update, Delete employees
- Uses JDBC with PreparedStatement
- Handles SQL exceptions gracefully

## Setup
1. Create MySQL DB `employeedb`
2. Create table:
```sql
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(100),
    salary DOUBLE
);
