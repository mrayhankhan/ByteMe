# ByteMe

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
