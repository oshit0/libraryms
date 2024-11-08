Library Management System (LibraryMS)

This is a Library Management System (LibraryMS) developed as part of my TESDA Institutional Assessment 1 project. It is a command-line interface (CLI) based application built using Java and MySQL. The system follows the Model-View-Controller (MVC) architectural pattern, ensuring a clean separation of concerns for managing books, users, and transactions in the library.
Features

    Book Management: Add, update, and delete books in the library.
    User Management: Register and manage library users.
    Transaction Management: Track borrowed books.
    Search Functionality: Search books by title, Id.
    CLI Interface: Interact with the system using simple command-line interface.

Technologies Used

    Programming Language: Java
    Database: MySQL
    Architectural Pattern: Model-View-Controller (MVC)
    Persistence: JDBC for database connectivity

Prerequisites

Before running the Library Management System, ensure you have the following installed:

    Java (JDK 8 or higher)
    MySQL (or any compatible relational database)
    A MySQL database and user credentials for connecting the application

Installation

    Clone the repository: git clone https://github.com/oshit0/libraryms

Set up the MySQL database:

    Create a new MySQL database, e.g., libraryms.
    Create the necessary tables for storing book, user, and transaction data. You can find the SQL schema in *message me.

Configure database connection:

    Open the config/DbConnection.java file and update the MySQL connection details (username, password, database name).

Compile and run the application:

    Open a terminal/command prompt and navigate to the project directory.
    Compile the Java files:

javac -d bin src/*.java

Run the application:

        java -cp bin Main
        
Acknowledgments

    Thanks to TESDA for the opportunity to work on this project as part of the Institutional Assessment 1.
    Special thanks to the Java and MySQL documentation for the helpful resources.

Notes:

    Be sure to update the database configuration details and any specific paths that might differ from my example.
    If you have any specific classes, methods, or files that you want to highlight, you can add a section for them under Usage or Structure.

Let me know if you'd like any other adjustments!
