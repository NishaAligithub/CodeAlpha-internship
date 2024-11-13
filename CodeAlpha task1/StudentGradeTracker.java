import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the number of grades to input
        System.out.print("Enter the number of grades to input for number of students: ");
        int numGrades = scanner.nextInt();

        // Array to store grades
        double[] grades = new double[numGrades];

        // Input grades into the array
        System.out.println("Enter each student's grade:");
        for (int i = 0; i < numGrades; i++) {
            System.out.print("Grade " + (i + 1) + ": ");
            grades[i] = scanner.nextDouble();
        }

        // Initialize variables for calculations
        double total = 0;
        double highest = grades[0];
        double lowest = grades[0];

        // Calculate total, highest, and lowest in a single loop
        for (int i = 0; i < numGrades; i++) {
            double grade = grades[i];
            total += grade;

            // Update highest and lowest grades using Math functions
            highest = Math.max(highest, grade);
            lowest = Math.min(lowest, grade);
        }

        // Calculate the average
        double average = total / numGrades;

        // Display results
        System.out.println("\nGrades Summary:");
        System.out.println("Total number of grades: " + numGrades);
        System.out.printf("Average grade: %.2f\n", average);  // Use printf for formatting
        System.out.println("Highest grade: " + highest);
        System.out.println("Lowest grade: " + lowest);

        scanner.close();
    }
}
