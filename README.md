# ByteMe

ByteMe is a CLI-based food ordering system designed specifically for our college canteen. It helps students browse the canteen menu, place orders, and track their delivery without leaving their hostel rooms. It also enables canteen staff to manage menu items and process orders efficiently.

## Project Structure

src/
├── main/
│   └── java/
│       └── com/
│           └── byteme/
│               ├── models/
│               │   ├── User.java
│               │   ├── Customer.java
│               │   ├── Admin.java
│               │   ├── MenuItem.java
│               │   ├── Order.java
│               │   ├── Cart.java
│               │   ├── Review.java
│               │   └── VIPMembership.java
│               ├── services/
│               │   ├── MenuService.java
│               │   ├── MenuServiceImpl.java
│               │   ├── OrderService.java
│               │   ├── OrderServiceImpl.java
│               │   ├── UserService.java
│               │   ├── UserServiceImpl.java
│               │   ├── CartService.java
│               │   ├── CartServiceImpl.java
│               │   ├── ReviewService.java
│               │   └── ReviewServiceImpl.java
│               ├── utils/
│               │   ├── InputValidator.java
│               │   └── Logger.java
│               ├── interfaces/
│               │   └── Observable.java
│               ├── exceptions/
│               │   ├── InvalidInputException.java
│               │   └── NotFoundException.java
│               └── ByteMeApplication.java
└── test/
    └── java/
        └── com/
            └── byteme/
                ├── services/
                │   ├── MenuServiceTest.java
                │   ├── OrderServiceTest.java
                │   └── UserServiceTest.java
                └── utils/
                    └── InputValidatorTest.java



## Features

### Admin Interface
- **Menu Management**
  - Add new items
  - Update existing items
  - Remove items
- **Order Management**
  - View pending orders
  - Update order status
  - Process refunds
  - Handle special requests
  - Order priority for VIP customers
- **Report Generation**
  - Daily sales report

### Customer Interface
- **Customer Types**
  - VIP
  - Regular
- **Browse Menu**
  - View all items
  - Search functionality
  - Filter by category
  - Sort by price
- **Cart Operations**
  - Add items
  - Modify quantities
  - Remove items
  - View total
  - Checkout process
- **Order Tracking**
  - View order status
  - Cancel order
  - Order history
- **Item Reviews**
  - Provide review
  - View reviews

## Getting Started

1. Clone the repository.
2. Navigate to the project directory.
3. Compile the project using your preferred Java IDE or command line.
4. Run the `ByteMeApplication` class to start the application.

## Running Tests

To run the tests, use your preferred Java testing framework. The tests are located in the `src/test/java/com/byteme` directory.

