# TeamFit

## Description
TeamFit is a Java-based application designed to help users manage their fitness routines. The application allows users to track their exercises and maintain a cart of selected exercises.

## Features
- User management
- Exercise tracking
- Exercise cart functionality

## Project Structure

teamfit/
├── .classpath
├── .project
├── cart.txt
├── exercise.txt
├── users.txt
├── bin/
│ └── teamfit/
│ ├── db.properties
│ ├── Exercise.class
│ ├── ExerciseCart.class
│ ├── TeamFit.class
│ └── User.class
├── src/
│ └── teamfit/
│ ├── db.properties
│ ├── Exercise.java
│ ├── ExerciseCart.java
│ ├── TeamFit.java
│ └── User.java
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
![Membership registration and login](https://github.com/gksqls9735/TeamFit/raw/master/images/Membership_registration.png)

### Lecture Registration
![Lecture Registration](https://github.com/gksqls9735/TeamFit/raw/master/images/lecture_registration1.png)
![Lecture Registration](https://github.com/gksqls9735/TeamFit/raw/master/images/lecture_registration2.png)

### Instructor Matching
![Instructor Matching](https://github.com/gksqls9735/TeamFit/raw/master/images/instructor_matching.png)

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
Your Name - [your-email@example.com](mailto:your-email@example.com)

Project Link: [https://github.com/yourusername/teamfit](https://github.com/yourusername/teamfit)
