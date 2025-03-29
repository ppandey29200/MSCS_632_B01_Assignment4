# Employee Shift Scheduling Application

This project is a Python-based as well as Java-based application designed to manage employee schedules at a company operating 7 days a week. The program allows employees to choose shifts (morning, afternoon, or evening) and generates a weekly schedule while respecting specific constraints.

## Features

* **Input and Storage:**
    * Collects employee names and preferred shifts for each day of the week.
    * Stores the data using dictionaries for efficient access.
* **Scheduling Logic:**
    * Ensures that no employee works more than one shift per day.
    * Restricts employees to a maximum of 5 shifts per week.
    * Guarantees at least 2 employees per shift per day.
    * Randomly assigns additional employees to shifts when needed.
* **Conflict Resolution:**
    * Detects when an employee's preferred shift is full and reassigns them to another available shift.
* **Output:**
    * Displays the final schedule in a readable format.

## Technologies Used

* Python
* Java

## Installation

1.  Clone the repository.
2.  Run the script

## Usage

1.  Enter the number of employees.
2.  For each employee, enter their name and preferred shifts for each day.
3.  The application will automatically assign shifts while respecting constraints.
4.  The final schedule will be displayed on the console.

## Example Output

```yaml
Final Schedule:
Monday:
  Morning: Alice, Bob
  Afternoon: Carol, Dave
  Evening: Eve, Frank
Tuesday:
  Morning: Alice, Carol
  Afternoon: Bob, Frank
  Evening: Dave, Eve
...
