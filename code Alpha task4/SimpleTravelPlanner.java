import java.util.ArrayList;
import java.util.Scanner;

class ItineraryEntry {
    private String destination;
    private String date;
    private double budget;

    public ItineraryEntry(String destination, String date, double budget) {
        this.destination = destination;
        this.date = date;
        this.budget = budget;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public double getBudget() {
        return budget;
    }

    @Override
    public String toString() {
        return "Destination: " + destination + ", Date: " + date + ", Budget: $" + budget;
    }
}

public class SimpleTravelPlanner {
    private static ArrayList<ItineraryEntry> itinerary = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the Simple Travel Planner!");
        displayMenu();

        while (true) {
            System.out.print("Enter a command: ");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "add":
                    addEntry(scanner);
                    break;
                case "view":
                    viewItinerary();
                    break;
                case "exit":
                    System.out.println("Exiting the planner. Safe travels!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
                    displayMenu();
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Available commands:");
        System.out.println(" - add: Add a new destination to your itinerary");
        System.out.println(" - view: View your current travel itinerary");
        System.out.println(" - exit: Exit the travel planner");
    }

    private static void addEntry(Scanner scanner) {
        System.out.print("Enter a destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter the date of visit (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        double budget = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter your estimated budget for this destination: ");
            try {
                budget = Double.parseDouble(scanner.nextLine());
                if (budget < 0) {
                    System.out.println("Budget can't be negative. Please enter a valid amount.");
                } else {
                    validInput = true; // Break out of the loop if input is valid
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the budget.");
            }
        }

        // Add entry to the itinerary
        ItineraryEntry entry = new ItineraryEntry(destination, date, budget);
        itinerary.add(entry);
        System.out.println("Destination added successfully!");
    }

    private static void viewItinerary() {
        if (itinerary.isEmpty()) {
            System.out.println("Your itinerary is currently empty.");
        } else {
            System.out.println("\nYour Travel Itinerary:");
            double totalBudget = 0;
            for (ItineraryEntry entry : itinerary) {
                System.out.println(entry);
                totalBudget += entry.getBudget();
            }
            System.out.printf("Total Estimated Budget: $%.2f%n", totalBudget);
        }
    }
}
