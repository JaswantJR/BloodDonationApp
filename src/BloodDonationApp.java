import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class User {
    private String name;
    private String contactNumber;
    private String bloodType;
    private String location;
    private String cnicOrID; // Added CNIC or ID number

    public User(String name, String contactNumber, String bloodType, String location, String cnicOrID) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.bloodType = bloodType;
        this.location = location;
        this.cnicOrID = cnicOrID;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getLocation() {
        return location;
    }

    public String getCnicOrID() {
        return cnicOrID;
    }
}

public class BloodDonationApp {
    private static Map<String, User> donors = new HashMap<>();
    private static List<User> bloodRequests = new ArrayList<>(); // Blood request submissions

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nBlood Donation App");
            System.out.println("1. Register as Donor");
            System.out.println("2. View Donors");
            System.out.println("3. Submit Donation Request");
            System.out.println("4. View Blood Requests"); // Added option
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    registerDonor(scanner);
                    break;
                case 2:
                    viewDonors();
                    break;
                case 3:
                    submitDonationRequest(scanner);
                    break;
                case 4:
                    viewBloodRequests();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerDonor(Scanner scanner) {
        System.out.println("\nRegister as Donor");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your contact number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter your CNIC or ID number: "); // Moved CNIC or ID input here
        String cnicOrID = scanner.nextLine();
        System.out.print("Enter your blood type: ");
        String bloodType = scanner.nextLine();
        System.out.print("Enter your location: ");
        String location = scanner.nextLine();

        User donor = new User(name, contactNumber, bloodType, location, cnicOrID);
        donors.put(contactNumber, donor);

        System.out.println("Thank you for registering as a donor!");
    }

    private static void viewDonors() {
        System.out.println("\nRegistered Donors");
        for (User donor : donors.values()) {
            System.out.println("\nName: " + donor.getName() + ", \nBlood Type: " + donor.getBloodType() + ", \nLocation: " + donor.getLocation() + ", \nCNIC/ID: " + donor.getCnicOrID());
        }
    }

    private static void submitDonationRequest(Scanner scanner) {
        System.out.println("\nSubmit Donation Request");
        System.out.print("Enter Patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter  contact number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter required blood type: ");
        String requiredBloodType = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        // Match donor based on blood type and location
        List<User> matchedDonors = new ArrayList<>();
        for (User donor : donors.values()) {
            if (donor.getBloodType().equalsIgnoreCase(requiredBloodType) && donor.getLocation().equalsIgnoreCase(location)) {
                matchedDonors.add(donor);
            }
        }

        if (matchedDonors.isEmpty()) {
            System.out.println("No donors found matching the criteria.");
        } else {
            System.out.println("Donors matching the criteria:");
            for (User donor : matchedDonors) {
                System.out.println("Name: " + donor.getName() + ", Contact: " + donor.getContactNumber());
            }
            System.out.println("Please contact them for donation.");
            // Add to blood requests
            bloodRequests.add(new User(name, contactNumber, requiredBloodType, location, null));
        }
    }

    private static void viewBloodRequests() {
        System.out.println("\nBlood Requests");
        for (User request : bloodRequests) {
            System.out.println("Name: " + request.getName() + ", Blood Type: " + request.getBloodType() + ", Location: " + request.getLocation());
        }
    }
}