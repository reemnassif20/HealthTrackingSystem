# Health Tracking System User Manual

## Introduction

Welcome to the Health Tracking System! This user manual provides comprehensive guidance on using the application to monitor and manage your health effectively. Whether you're tracking meals, exercises, setting reminders, or reviewing your health history, this manual will help you navigate the features seamlessly.

## Table of Contents

1. [Getting Started](#1-getting-started)
   - [Cloning the Repository](#cloning-the-repository)
   - [Setting Up Dependencies](#setting-up-dependencies)
   - [Running the Application](#running-the-application)
   
2. [User Registration and Login](#2-user-registration-and-login)
   - [Creating an Account](#creating-an-account)
   - [Logging In](#logging-in)
   
3. [Home](#3-home)
   - [User Information Display](#user-information-display)
   - [Total Calories Tracking](#total-calories-tracking)
   - [Date-Specific Calculations](#date-specific-calculations)
   
4. [Meal Tracking](#4-meal-tracking)
   - [Logging Meals](#logging-meals)
   - [Customizing Meal Entries](#customizing-meal-entries)
   - [Date Selection](#date-selection)
   
5. [Exercise Tracking](#5-exercise-tracking)
   - [Recording Activities](#recording-activities)
   - [Adding Workout Details](#adding-workout-details)
   - [Date Selection](#date-selection)
   
6. [Reminder Management](#6-reminder-management)
   - [Setting Custom Reminders](#setting-custom-reminders)
   - [Editing and Deleting Reminders](#editing-and-deleting-reminders)
   - [Notification Preferences](#notification-preferences)
   
7. [History Tracking](#7-history-tracking)
   - [Reviewing Comprehensive History](#reviewing-comprehensive-history)
   - [Filtering and Sorting](#filtering-and-sorting)
   - [Data Visualization](#data-visualization)
   
8. [Calorie Counting](#8-calorie-counting)
   - [Automatic Calculation](#automatic-calculation)
   - [Daily and Weekly Totals](#daily-and-weekly-totals)
   
9. [Edit and Delete Functionality](#9-edit-and-delete-functionality)
   
10. [User Profile Management](#10-user-profile-management)
    - [Managing Personal Information](#managing-personal-information)
    - [Uploading Profile Pictures](#uploading-profile-pictures)

# 1.Getting Started

## Cloning the Repository

```bash
git clone https://github.com/reemnassif20/HealthTrackingSystem 
```
(#setting-up-dependencies) ## Setting Up Dependencies
- Ensure you have Java, JavaFX, and XAMPP (for running the local database) installed.
- Set up the project in your preferred IDE (Eclipse, IntelliJ, etc.).
(#running-the-application) ## Running the Application
- Open XAMPP and start running Apache and MySQL.
- Open the localhost and import the database queries found in HealthTrackingSystem\database\db_queries2.sql.
- Locate the main application file and run it.
The application GUI should appear, allowing you to explore its features.
# 2. User Registration and Login
## Creating an Account
- Launch the application and navigate to the registration page.
- Fill in the required information (username, email, password, etc.).
- Click the "Sign Up" button to create your account.
## Logging In
- Enter your registered username and password.
- Click the "Log In" button to access your account.
 # 3. Home
## User Information Display
- Username: Displays the username of the currently signed-in user.
- Current BMI: Shows the Body Mass Index (BMI) calculated based on the user's weight and height.
- Current Weight: Displays the user's latest recorded weight.
- Change Recommendation: Provides recommendations on whether the user should lose, gain, or maintain their current weight.
- Weight Change: Indicates the amount of weight the user needs to lose or gain to reach their target.
- Calories Per Day: Displays the estimated daily calorie intake for the user.
- Weight Status: Describes the user's weight status (e.g., underweight, normal, overweight).
- Optimal Weight: Presents the optimal weight for the user based on their height and other factors.
- Total Calories Tracking
- Total Eaten Calories: Calculates and displays the total calories consumed by the user through recorded meals.
- Total Burned Calories: Calculates and displays the total calories burned by the user through recorded exercises.
T- otal Entered Calories: Shows the net calories entered, taking into account both consumed and burned calories.
## Date-Specific Calculations
- Calculates and displays total eaten and burned calories based on the current date.
- Enables real-time tracking of daily food intake and exercise activities.
# 4. Meal Tracking
 ## Logging Meals
- Access the "Meals" section of the application.
- Enter details about the food items consumed, including quantity and type.
- Save the entry.
## Customizing Meal Entries
- Edit or delete previously entered meal data for accuracy.
## Date Selection
- Choose the date for entering meal information, allowing retrospective entries and planning.
# 5. Exercise Tracking
## Recording Activities
- Navigate to the "Exercises" section.
- Provide details about the workout, including type, and duration.
- Save the entry.
## Adding Workout Details
- Edit or delete previously recorded exercise data for accuracy.
## Date Selection
- Choose the date for entering exercise information, allowing retrospective entries and planning.
# 6. Reminder Management
## Setting Custom Reminders
- Access the "Reminders" section.
- Click the "Add Reminder" button.
- Enter details such as Reminder message, and duration.
- Save the reminder.
## Deleting Reminders
- Delete reminders that are no longer needed.
# 7. History Tracking
## Reviewing Comprehensive History
- Explore the "History" section to view a comprehensive log of your health-related activities.
- Filter history by date.
- Sort history entries based on different criteria for better analysis.
# 8. Calorie Counting
## Automatic Calculation
- Log meals and exercises to allow the application to automatically calculate added calories.
## Daily Totals
- Monitor your daily and weekly calorie intake through the application's summary feature.
# 9. Edit and Delete Functionality
-  Enjoy the flexibility of editing or deleting previously entered data to maintain accuracy.
# 10. User Profile Management
## Managing Personal Information
- Navigate to the "Profile" section.
- Update your weight and other personal details.
- Save the changes.
# Conclusion
- Congratulations! You're now well-versed in using the Health Tracking System. For any additional queries, reach out to the development team. Remember, your health journey is just a click away!
