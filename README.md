# Smart Energy Consumption Tracker

* Name: Reet Dubey
* Registration Number: 24BAI10232

---

## Overview

This project is a Java-based application that helps users track appliance-wise energy consumption and estimate electricity bills.

---

## Problem Statement

Users often do not know how much energy individual appliances consume, leading to higher electricity bills and wastage. This project provides a simple way to monitor and analyze usage.

---

## Features

* Add appliances with power and usage details
* Calculate total energy consumption (kWh)
* Estimate electricity bill
* Identify highest energy-consuming appliance
* Suggest energy-saving tips
* Save and load data using file handling

---

## Methodology

Energy consumption is calculated using:

Units = (Power × Hours × Days) / 1000

Data is stored using `ArrayList` and persisted using file handling.

---
## Usage Flow

1) Run the program using: java EnergyTracker
2) The system displays a menu with options:
   - Add appliance
   - View consumption
   - Calculate bill
   - Highest consuming appliance
   - Save/Load data
   - Suggest energy savings
3) Start by selecting “Add appliance” and enter:
Appliance name
Power rating (in watts)
Daily usage (in hours)
Add multiple appliances as needed.
4) Choose “View consumption” to see energy usage for a given number of days.
5) Select “Calculate bill” to estimate electricity cost by entering:
Number of days
Cost per unit
6) Use “Highest consuming appliance” to identify which appliance uses the most energy.
7) Use “Suggest energy savings” to get recommendations based on usage.
8) Save your data using “Save data” and reload it anytime using “Load data”.
9) Exit the program when done.

## How to Run

1. Compile:

   ```
   javac EnergyTracker.java
   ```
2. Run:

   ```
   java EnergyTracker
   ```

---

## Technologies Used

* Java
* ArrayList
* File Handling

---

## Conclusion

The project helps users understand and manage their electricity consumption effectively, promoting energy-efficient behavior.
