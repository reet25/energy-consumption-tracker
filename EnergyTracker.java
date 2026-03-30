// Smart Energy Consumption Tracker
// This program helps users track appliance-wise energy consumption,
// estimate electricity bills, and suggests energy-saving tips.
// Developed as part of BYOP project.
import java.util.*;
import java.io.*;
// Appliance class 
class Appliance {
    String name;
    double power; // in watts
    double hours; // per day
    // initializes appliances
    Appliance(String name, double power, double hours) {
        this.name = name;
        this.power = power;
        this.hours = hours;
    }
    // calculates energy consumption in units
    double calculateUnits(int days) {
        return (power * hours * days) / 1000.0;
    }
    //converts to string to store in the file
    @Override
    public String toString() {
        return name + "," + power + "," + hours;
    }
}
// Energy tracker class
public class EnergyTracker {
    static ArrayList<Appliance> appliances = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    // takes user input and adds appliances
    static void addAppliance() {
        System.out.print("Enter appliance name ");
        String name = sc.nextLine();
        System.out.print("Enter power in watts ");
        double power = sc.nextDouble();
        System.out.print("Enter hours used per day ");
        double hours = sc.nextDouble();
        sc.nextLine();
        appliances.add(new Appliance(name, power, hours));
        System.out.println("Appliance added");
    }
    // shows consumption for given number of days
    static void viewConsumption() {
        if (appliances.isEmpty()) {
            System.out.println("No data available\n");
            return;
        }
        System.out.print("Enter number of days ");
        int days = sc.nextInt();
        double totalUnits = 0;
        System.out.println("Consumption Details");
        for (Appliance a : appliances) {
            double units = a.calculateUnits(days);
            totalUnits += units;
            System.out.println(a.name + " - " + units + " units");
        }
        System.out.println("Total units- " + totalUnits + "\n");
    }
    // Calculate bill based on given data
    static void calculateBill() {
        if (appliances.isEmpty()) {
            System.out.println("No data available\n");
            return;
        }
        System.out.print("Enter number of days - ");
        int days = sc.nextInt();
        System.out.print("Enter cost per unit in rupees - ");
        double cost = sc.nextDouble();
        double totalUnits = 0;
        for (Appliance a : appliances) {
            totalUnits += a.calculateUnits(days);
        }
        double bill = totalUnits * cost;
        System.out.println("\nTotal Units- " + totalUnits);
        System.out.println("Estimated Bill- " + bill + "\n");
    }
    // Highest consuming appliance for given number of days
    static void highestConsumer() {
        if (appliances.isEmpty()) {
            System.out.println("No data available\n");
            return;
        }
        System.out.print("Enter number of days - ");
        int days = sc.nextInt();
        Appliance maxAppliance = appliances.get(0);
        double maxUnits = maxAppliance.calculateUnits(days);
        for (Appliance a : appliances) {
            double units = a.calculateUnits(days);
            if (units > maxUnits) {
                maxUnits = units;
                maxAppliance = a;
            }
        }
        System.out.println("\nHighest Consumption is done by " + maxAppliance.name +
                " (" + maxUnits + " units). Consider reducing its usage.\n");
    }
    // Save data provided by user
    static void saveData() {
        try {
            FileWriter fw = new FileWriter("data.txt");
            for (Appliance a : appliances) {
                fw.write(a.toString() + "\n");
            }
            fw.close();
            System.out.println("Data saved\n");
        } catch (IOException e) {
            System.out.println("Error saving data");
        }
    }
    //loads data stored in the file
    static void loadData() {
        try {
            File file = new File("data.txt");
            if (!file.exists()) {
                System.out.println("No data found\n");
                return;
            }
            Scanner fileScanner = new Scanner(file);
            appliances.clear();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0];
                double power = Double.parseDouble(parts[1]);
                double hours = Double.parseDouble(parts[2]);
                appliances.add(new Appliance(name, power, hours));
            }
            fileScanner.close();
            System.out.println("Data loaded\n");

        } catch (Exception e) {
            System.out.println("Error loading data");
        }
    }
    //suggests savings based on consumption appliance-wise
    static void suggestSavings() {
        if (appliances.isEmpty()) {
            System.out.println("No data available\n");
            return;
        }
        System.out.print("Enter number of days - ");
        int days = sc.nextInt();
        double totalUnits = 0;
        for (Appliance a : appliances) {
            totalUnits += a.calculateUnits(days);
        }
        System.out.println("\nEnergy Saving Suggestions -\n");
        for (Appliance a : appliances) {
            double units = a.calculateUnits(days);
            double percentage = (units / totalUnits) * 100;
            if (percentage > 30) {
                System.out.println(a.name + " consumes " + String.format("%.2f", percentage)
                        + "% of total energy - Consider reducing usage significantly.");
            } else if (percentage > 15) {
                System.out.println(a.name + " consumes moderate energy ("
                        + String.format("%.2f", percentage)
                        + "%). Try optimizing usage.");
            }
        }
        if (totalUnits < 50) {
            System.out.println("\nOverall consumption is low. Good management!");
        }
        System.out.println();
    }
    // Main method to interact with user
    public static void main(String[] args) {
        while (true) {
            System.out.println("Energy Consumption Tracker");
            System.out.println("1.Add appliance");
            System.out.println("2.View consumption");
            System.out.println("3.Calculate bill");
            System.out.println("4.Highest consuming appliance");
            System.out.println("5.Save data");
            System.out.println("6.Load data");
            System.out.println("7.Suggest energy savings");
            System.out.println("8.Exit");
            System.out.print("Enter choice ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addAppliance();
                    break;
                case 2:
                    viewConsumption();
                    break;
                case 3:
                    calculateBill();
                    break;
                case 4:
                    highestConsumer();
                    break;
                case 5:
                    saveData();
                    break;
                case 6:
                    loadData();
                    break;
                case 7:
                    suggestSavings();
                    break;
                case 8:
                    System.out.println("Exited");
                    return;
                default:
                    System.out.println("Invalid choice\n");
            }
        }
    }
}