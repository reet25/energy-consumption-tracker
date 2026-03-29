import java.util.*;
import java.io.*;
// Appliance class
class Appliance {
    String name;
    double power;//in watts
    double hours;//per day
    Appliance(String name, double power, double hours) {
        this.name = name;
        this.power = power;
        this.hours = hours;
    }
    double calculateUnits(int days) {
        return (power * hours * days) / 1000.0; 
    }
    @Override
    public String toString() {
        return name + "," + power + "," + hours;
    }
}
// Energy tracker class
public class EnergyTracker {
    static ArrayList<Appliance> appliances = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    // Add appliances
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
    // View consumption
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
    // Calculate bill
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
    // check highest consuming appliance
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
    // saving data
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
    // loading data
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
    // suggest savings
    static void suggestSavings() {
        if (appliances.isEmpty()) {
            System.out.println("No data available\n");
            return;
        }
        System.out.print("Enter number of days - ");
        int days = sc.nextInt();
        for (Appliance a : appliances) {
            double units = a.calculateUnits(days);
            if (units > 50) {
                System.out.println(a.name + " is consuming high energy. Try reducing usage.");
            }
        }
        System.out.println();
    }
    // Main class
    public static void main(String[] args) {
        while (true) {
            System.out.println("Energy Consumption Tracker");
            System.out.println("1.Add appliance");
            System.out.println("2.View consumption");
            System.out.println("3.Calculate bill");
            System.out.println("4.Highest consuming appliance");
            System.out.println("5.Save data");
            System.out.println("6.load data");
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