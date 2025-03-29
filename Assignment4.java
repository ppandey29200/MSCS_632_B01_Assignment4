
import java.util.*;

public class Assignment4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] SHIFTS = {"Morning", "Afternoon", "Evening"};

        // Data Structure to store schedule
        Map<String, Map<String, List<String>>> schedule = new HashMap<>();
        for (String day : DAYS) {
            schedule.put(day, new HashMap<>());
            for (String shift : SHIFTS) {
                schedule.get(day).put(shift, new ArrayList<>());
            }
        }

        Map<String, Integer> employeeDays = new HashMap<>();

        // Input Employee Data
        System.out.print("Enter number of employees: ");
        int numEmployees = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Map<String, List<Pair<String, String>>> employees = new HashMap<>();

        for (int i = 0; i < numEmployees; i++) {
            System.out.print("Enter employee name: ");
            String name = scanner.nextLine();
            List<Pair<String, String>> preferences = new ArrayList<>();
            for (String day : DAYS) {
                System.out.printf("Preferred shift for %s (Morning/Afternoon/Evening): ", day);
                String pref = scanner.nextLine();
                preferences.add(new Pair<>(day, pref));
            }
            employees.put(name, preferences);
            employeeDays.put(name, 0); // Initialize shift count to 0
        }

        // Scheduling Logic
        for (String day : DAYS) {
            for (String shift : SHIFTS) {
                // Filter employees who prefer this shift
                List<String> candidates = new ArrayList<>();
                for (Map.Entry<String, List<Pair<String, String>>> entry : employees.entrySet()) {
                    String name = entry.getKey();
                    List<Pair<String, String>> prefs = entry.getValue();
                    if (prefs.contains(new Pair<>(day, shift)) && employeeDays.get(name) < 5) {
                        candidates.add(name);
                    }
                }
                Collections.shuffle(candidates);

                while (schedule.get(day).get(shift).size() < 2) {
                    if (!candidates.isEmpty()) {
                        String employee = candidates.remove(0);
                        if (employeeDays.get(employee) < 5) {
                            schedule.get(day).get(shift).add(employee);
                            employeeDays.put(employee, employeeDays.get(employee) + 1);
                        }
                    } else {
                        // Randomly assign if fewer than 2 employees
                        List<String> available = new ArrayList<>();
                        for (Map.Entry<String, Integer> entry : employeeDays.entrySet()) {
                            if (entry.getValue() < 5) {
                                available.add(entry.getKey());
                            }
                        }
                        if (!available.isEmpty()) {
                            String employee = available.get(random.nextInt(available.size()));
                            schedule.get(day).get(shift).add(employee);
                            employeeDays.put(employee, employeeDays.get(employee) + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        // Display Final Schedule
        System.out.println("\nFinal Schedule:");
        for (String day : DAYS) {
            System.out.println("\n" + day + ":");
            for (String shift : SHIFTS) {
                List<String> names = schedule.get(day).get(shift);
                String result = names.isEmpty() ? "No employees assigned" : String.join(", ", names);
                System.out.printf("  %s: %s%n", shift, result);
            }
        }

        scanner.close();
    }

    // Helper class to represent a pair of day and shift
    static class Pair<T, U> {
        T first;
        U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) &&
                    Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}