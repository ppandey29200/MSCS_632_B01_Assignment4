# Employee Scheduler in Python

import random
from collections import defaultdict

# Constants
DAYS = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
SHIFTS = ['Morning', 'Afternoon', 'Evening']

# Data Structure to store schedule
schedule = {day: {shift: [] for shift in SHIFTS} for day in DAYS}
employee_days = defaultdict(int)

# Input Employee Data
num_employees = int(input('Enter number of employees: '))
employees = {}

for _ in range(num_employees):
    name = input('Enter employee name: ')
    preferences = []
    for day in DAYS:
        pref = input(f'Preferred shift for {day} (Morning/Afternoon/Evening): ')
        preferences.append((day, pref))
    employees[name] = preferences

# Scheduling Logic
for day in DAYS:
    for shift in SHIFTS:
        # Filter employees who prefer this shift
        candidates = [name for name, prefs in employees.items() if (day, shift) in prefs and employee_days[name] < 5]
        random.shuffle(candidates)

        while len(schedule[day][shift]) < 2:
            if candidates:
                employee = candidates.pop()
                if employee_days[employee] < 5:
                    schedule[day][shift].append(employee)
                    employee_days[employee] += 1
            else:
                # Randomly assign if fewer than 2 employees
                available = [name for name in employees if employee_days[name] < 5]
                if available:
                    employee = random.choice(available)
                    schedule[day][shift].append(employee)
                    employee_days[employee] += 1
                else:
                    break

# Display Final Schedule
print('\nFinal Schedule:')
for day in DAYS:
    print(f'\n{day}:')
    for shift, names in schedule[day].items():
        print(f'  {shift}: {', '.join(names) if names else 'No employees assigned'}')
