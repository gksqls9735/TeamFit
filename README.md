# TeamFit

## Description
TeamFit is a Java-based application designed to help users manage their fitness routines. The application allows users to track their exercises and maintain a cart of selected exercises.

## Features
- User management
- Exercise tracking
- Exercise cart functionality

## Project Structure
```plaintext
teamfit/
├── .classpath
├── .project
├── cart.txt
├── exercise.txt
├── users.txt
├── bin/
│   └── teamfit/
│       ├── db.properties
│       ├── Exercise.class
│       ├── ExerciseCart.class
│       ├── TeamFit.class
│       └── User.class
├── src/
│   └── teamfit/
│       ├── db.properties
│       ├── Exercise.java
│       ├── ExerciseCart.java
│       ├── TeamFit.java
│       └── User.java
└── images/
    ├── instructor_matching.png
    ├── lecture_registration1.png
    ├── lecture_registration2.png
    └── Membership_registration.png

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Git

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/teamfit.git
    ```
2. Navigate to the project directory:
    ```sh
    cd teamfit
    ```

### Running the Application
1. Compile the Java source files:
    ```sh
    javac -d bin src/teamfit/*.java
    ```
2. Run the application:
    ```sh
    java -cp bin teamfit.TeamFit
    ```

## Files
- `src/teamfit/`: Contains the source code for the application.
- `bin/teamfit/`: Contains the compiled class files.
- `cart.txt`: A text file that might be used to store cart data.
- `exercise.txt`: A text file that might be used to store exercise data.
- `users.txt`: A text file that might be used to store user data.
- `db.properties`: A properties file for database configuration.

## Screenshots
Here are some examples of the application running in the console:

### Membership registration and login
![Membership_registration](https://github.com/gksqls9735/TeamFit/assets/166088728/0ca402d2-f0ea-4727-9ef6-199a55dd10f9)

### Lecture Registration
![lecture_registration1](https://github.com/gksqls9735/TeamFit/assets/166088728/83a5c15d-949a-4fef-a08d-d72cc174163f)
![lecture_registration2](https://github.com/gksqls9735/TeamFit/assets/166088728/b5a729ff-de5a-4197-855d-bf59eb54f864)

### Instructor Matching
![instructor_matching](https://github.com/gksqls9735/TeamFit/assets/166088728/2703ee31-a082-4a83-a041-e1b73a45cff2)

## Contributing
1. Fork the repository.
2. Create your feature branch:
    ```sh
    git checkout -b feature/YourFeature
    ```
3. Commit your changes:
    ```sh
    git commit -m 'Add some feature'
    ```
4. Push to the branch:
    ```sh
    git push origin feature/YourFeature
    ```
5. Open a pull request.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact
Email - [your-email@example.com](mailto:gksqls9735@gmail.com)

Project Link: [https://github.com/gksqls9735/teamfit](https://github.com/gksqls9735/teamfit)
