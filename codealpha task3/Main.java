import java.util.*;

class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean isBooked;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookRoom() {
        isBooked = true;
    }

    public void releaseRoom() {
        isBooked = false;
    }
}

class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer: " + name + ", Email: " + email;
    }
}

class Reservation {
    private static int idCounter = 1001;
    private int bookingId;
    private Customer customer;
    private Room room;
    private Date bookingDate;
    private boolean isPaid;

    public Reservation(Customer customer, Room room, Date bookingDate) {
        this.bookingId = idCounter++;
        this.customer = customer;
        this.room = room;
        this.bookingDate = bookingDate;
        this.isPaid = false;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void processPayment() {
        this.isPaid = true;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ",\n " + customer + ",\n Room: " + room.getRoomNumber() + 
               " (" + room.getCategory() + "), \n Date: " + bookingDate + ",\n  Paid: " + (isPaid ? "Yes" : "No");
    }
}

class HotelReservationSystem {
    private ArrayList<Room> rooms;
    private HashMap<Integer, Reservation> reservations;

    public HotelReservationSystem() {
        rooms = new ArrayList<>();
        reservations = new HashMap<>();
    }

    public void addRoom(int roomNumber, String category, double price) {
        rooms.add(new Room(roomNumber, category, price));
    }

    public void displayAvailableRooms(String category) {
        System.out.println("Available Rooms in " + category + " category:");
        boolean available = false;
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && !room.isBooked()) {
                System.out.println("Room Number: " + room.getRoomNumber() + ", Price: RS " + room.getPrice());
                available = true;
            }
        }
        if (!available) {
            System.out.println("No available rooms in this category.");
        }
    }

    public int makeReservation(String customerName, String email, String category) {
        Customer customer = new Customer(customerName, email);
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && !room.isBooked()) {
                room.bookRoom();
                Reservation reservation = new Reservation(customer, room, new Date());
                reservations.put(reservation.getBookingId(), reservation);
                System.out.println("Reservation successful with Booking ID: " + reservation.getBookingId());
                return reservation.getBookingId();
            }
        }
        System.out.println("No available rooms in " + category + " category.");
        return -1;
    }

    public void viewReservation(int bookingId) {
        Reservation reservation = reservations.get(bookingId);
        if (reservation != null) {
            System.out.println(reservation);
        } else {
            System.out.println("Reservation not found.");
        }
    }

    public void cancelReservation(int bookingId) {
        Reservation reservation = reservations.get(bookingId);
        if (reservation != null) {
            reservation.getRoom().releaseRoom();
            reservations.remove(bookingId);
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Reservation not found.");
        }
    }

    public void processPayment(int bookingId) {
        Reservation reservation = reservations.get(bookingId);
        if (reservation != null && !reservation.isPaid()) {
            reservation.processPayment();
            System.out.println("Payment processed for Booking ID: " + bookingId);
        } else if (reservation != null && reservation.isPaid()) {
            System.out.println("Booking ID " + bookingId + " is already paid.");
        } else {
            System.out.println("Reservation not found.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelReservationSystem hotelSystem = new HotelReservationSystem();

        // Adding some rooms
        hotelSystem.addRoom(101, "Standard", 1200.0);
        hotelSystem.addRoom(102, "Standard", 1200.0);
        hotelSystem.addRoom(103, "Standard", 1200.0);
        hotelSystem.addRoom(104, "Standard", 1200.0);
        hotelSystem.addRoom(201, "Deluxe", 2000.0);
        hotelSystem.addRoom(202, "Deluxe", 2000.0);
        hotelSystem.addRoom(203, "Deluxe", 2000.0);
        hotelSystem.addRoom(204, "Deluxe", 2000.0);  // Fixed duplicate issue here

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Reservation Details");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. Process Payment");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Standard/Deluxe): ");
                    String category = scanner.nextLine();
                    hotelSystem.displayAvailableRooms(category);
                    break;

                case 2:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter customer email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter room category to reserve (Standard/Deluxe): ");
                    category = scanner.nextLine();
                    hotelSystem.makeReservation(name, email, category);
                    break;

                case 3:
                    System.out.print("Enter booking ID to view details: ");
                    int bookingId = 0;
                    try {
                        bookingId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid booking ID.");
                        break;
                    }
                    hotelSystem.viewReservation(bookingId);
                    break;

                case 4:
                    System.out.print("Enter booking ID to cancel: ");
                    bookingId = 0;
                    try {
                        bookingId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid booking ID.");
                        break;
                    }
                    hotelSystem.cancelReservation(bookingId);
                    break;

                case 5:
                    System.out.print("Enter booking ID to process payment: ");
                    bookingId = 0;
                    try {
                        bookingId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid booking ID.");
                        break;
                    }
                    hotelSystem.processPayment(bookingId);
                    break;

                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please choose a number between 1 and 6.");
            }
        }
    }
}
