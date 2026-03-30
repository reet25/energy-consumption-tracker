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
