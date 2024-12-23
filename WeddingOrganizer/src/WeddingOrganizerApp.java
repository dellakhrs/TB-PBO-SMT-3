import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.DriverManager;
import java.util.Date;

//Class untuk membantu menjalankan database
class DatabaseHelper {
    private static final String URL = "jdbc:postgresql://localhost:5432/wedding_db"; // URL database
    private static final String USER = "postgres"; // Username PostgreSQL
    private static final String PASSWORD = "dellakhrs"; // Password PostgreSQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void checkConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Koneksi berhasil ke database.");
            } else {
                System.out.println("Koneksi gagal.");
            }
        } catch (SQLException e) {
            System.out.println("Koneksi ke database gagal: " + e.getMessage());
        }
    }
}

public class WeddingOrganizerApp {
    public static void main(String[] args) {
        try (Connection conn = DatabaseHelper.getConnection()) {
            ClientService clientService = new ClientService(conn);  // Pastikan ClientService ada
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to Wedding Organizer Management System");
            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Create Client");
                System.out.println("2. View Clients");
                System.out.println("3. Update Client");
                System.out.println("4. Delete Client");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter contact: ");
                        String contact = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter event location: ");
                        String eventLocation = scanner.nextLine();
                        System.out.print("Enter status: ");
                        String status = scanner.nextLine();
                        System.out.print("Enter event time (yyyy-mm-dd): ");
                        String eventTimeStr = scanner.nextLine();

                        // Convert event time from String to Date
                        Date eventTime = java.sql.Date.valueOf(eventTimeStr);

                        System.out.print("Enter cost: ");
                        double cost = scanner.nextDouble();

                        Client client = new Client(0, name, contact, email, eventLocation, status, eventTime, cost);
                        clientService.create(client);  // Call the method from ClientService
                    }
                    case 2 -> clientService.read().forEach(System.out::println);
                    case 3 -> {
                        System.out.print("Enter Client ID to update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter new contact: ");
                        String contact = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter new event location: ");
                        String eventLocation = scanner.nextLine();
                        System.out.print("Enter new status: ");
                        String status = scanner.nextLine();
                        System.out.print("Enter new event time (yyyy-mm-dd): ");
                        String eventTimeStr = scanner.nextLine();

                        // Convert event time from String to Date
                        Date eventTime = java.sql.Date.valueOf(eventTimeStr);

                        System.out.print("Enter new cost: ");
                        double cost = scanner.nextDouble();

                        Client updatedClient = new Client(id, name, contact, email, eventLocation, status, eventTime, cost);
                        clientService.update(id, updatedClient);
                    }
                    case 4 -> {
                        System.out.print("Enter Client ID to delete: ");
                        int id = scanner.nextInt();
                        clientService.delete(id);
                    }
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
