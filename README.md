# Product Management App

A simple Android application to manage products, with functionality for adding products, categorizing them by status (Scheduled, Delivered, New Orders), and displaying them in tabs with badges showing the number of products in each category.

## Features
- **Tab Navigation**: The app uses `TabLayout` with three categories:
  - **Scheduled**
  - **Delivered**
  - **New Orders**
  
- **Product Management**: 
  - Add new products with details (title, date, price, status) via a floating action button (FAB).
  - Products can be categorized based on their status (Scheduled, Delivered, New Orders).
  
- **Badge Count**: A badge on the `Scheduled` tab displays the number of products that are scheduled.

- **Dynamic Data Updates**: 
  - The app reads product data from a local SQLite database.
  - Changes such as adding a new product or updating the product's status are dynamically reflected in the UI.

## Tech Stack

- **Programming Language**: Java
- **Framework**: Android SDK
- **Database**: SQLite
- **UI Components**:
  - `TabLayout` and `ViewPager2`
  - `FloatingActionButton` (FAB)
  - Custom `AlertDialog` for adding products
  - `ListView` for displaying product lists in each fragment
