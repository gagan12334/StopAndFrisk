# StopAndFrisk
This project is an assignment that involves analyzing real CSV data from the New York Police Department related to the Stop and Frisk cases over the years 2013, 2014, 2021, and 2022. The assignment focuses on using Java and object-oriented programming (OOP) principles to read and analyze the data.

# Overview
The goal of this assignment is to gain insights into social bias using data from the Stop and Frisk cases in New York. The project delves into the analysis of the Stop and Frisk logistics from 2013 and 2014 to more recent years (2021 and 2022), allowing for a comparison and examination of any differences.

# Project Structure
The project consists of the following files:

StopAndFrisk.java: This file contains the main class with methods for reading CSV files, analyzing data, and implementing object-oriented programming principles.

SFRecord.java: Represents a singular Stop and Frisk record's information, providing getter methods to access various parameters of a record.

SFYear.java: Represents one particular year, containing an ArrayList of all the Stop and Frisk records for that year. It includes methods to retrieve the year number and all the records for the year.

Driver.java: A tool to test the StopAndFrisk implementation interactively.

StdIn and StdOut: Libraries to handle input and output.

CSV files (2013.csv, 2014.csv, 2021.csv, 2022.csv): Files containing year’s record information to be tested by the driver.

# Usage
To use this project, follow these steps:

Download the CSV files from the provided link and store them inside the Assignment9 folder.

Open the project in an IDE (e.g., VSCode).

Run the Driver class to interactively test the implemented methods in StopAndFrisk.java.

Ensure that the methods produce the expected outputs as described in the assignment.

# Implementation Details
The core methods implemented include:

readFile(): Reads stop and frisk records from an input CSV file, creating SFRecord objects and inserting them into the corresponding year in the database.

populationStopped(year, race): Returns an ArrayList of records of people of a specific race stopped in a specific year.

friskedVSArrested(year): Calculates the percentage of records where the person was frisked and the percentage where the person was arrested in a given year.

genderBias(year): Creates a 2D array containing the percentage of Black females, White females, Black males, and White males who were stopped in a given year.

crimeIncrease(description, year1, year2): Returns a double value representing the percentage of crime increase or decrease between any two years for a specific crime description.

mostCommonBorough(year): Outputs the NYC borough where the most stops occurred in a given year.

# References
For more information on the Stop and Frisk policy in New York, refer to NYCLU - A Closer Look at Stop and Frisk in NYC.
