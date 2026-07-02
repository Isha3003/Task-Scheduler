# CPU Scheduling Simulator

A web-based CPU Scheduling Simulator built using **Java, Spring Boot, HTML, CSS, and JavaScript**. The application simulates various CPU scheduling algorithms and displays process execution details, performance metrics, and a Gantt Chart for visualization.

---

## Features

- FCFS (First Come First Serve)
- SJF (Shortest Job First - Non Preemptive)
- Round Robin Scheduling
- Priority Scheduling (Non Preemptive)
- Dynamic Process Creation
- Input Validation
- Gantt Chart Visualization
- CPU Utilization Calculation
- Throughput Calculation
- Average Waiting Time
- Average Turnaround Time
- Response Time Calculation

---

## Technologies Used

### Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Maven

### Frontend
- HTML
- CSS
- JavaScript

### Database
- MySQL

---

## Project Structure

```
cpu-scheduler
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── algorithm
│   │   │   ├── controller
│   │   │   ├── dto
│   │   │   ├── entity
│   │   │   ├── repository
│   │   │   └── service
│   │   │
│   │   └── resources
│   │       ├── static
│   │       │   ├── index.html
│   │       │   ├── style.css
│   │       │   └── script.js
│   │       └── application.properties
│
└── pom.xml
```

---

## Algorithms Implemented

### 1. First Come First Serve (FCFS)

- Non-preemptive scheduling
- Executes processes in order of arrival.

---

### 2. Shortest Job First (SJF)

- Non-preemptive scheduling
- Executes the process having the smallest burst time.

---

### 3. Round Robin

- Preemptive scheduling
- User-defined Time Quantum
- Fair CPU allocation among all processes.

---

### 4. Priority Scheduling

- Non-preemptive scheduling
- Executes the highest priority process among the available processes.
- Supports Idle CPU time.

---

## Performance Metrics

The simulator calculates:

- Completion Time
- Waiting Time
- Turnaround Time
- Response Time
- Average Waiting Time
- Average Turnaround Time
- CPU Utilization
- Throughput

---

## Gantt Chart

The simulator generates a Gantt Chart showing the execution order of processes, including CPU Idle time whenever applicable.

---

## How to Run

### Clone the repository

```bash
git clone https://github.com/Isha3003/Task-Scheduler.git
```

---

### Navigate to the project

```bash
cd Task-Scheduler
```

---

### Configure Database

Update `application.properties` with your MySQL credentials.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cpu_scheduler_db
spring.datasource.username=root
spring.datasource.password=your_password
```

---

### Run the application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

### Open in Browser

```
http://localhost:8080
```

---

## Sample Features

- Add or Delete Processes
- Select Scheduling Algorithm
- Enter Arrival Time
- Enter Burst Time
- Enter Priority (Priority Scheduling)
- Enter Time Quantum (Round Robin)
- View Performance Metrics
- View Gantt Chart

---

## Future Enhancements

- Preemptive SJF (SRTF)
- Preemptive Priority Scheduling
- Dark/Light Theme
- Export Results as PDF
- Save Simulation History
- Authentication and User Accounts
- Interactive Gantt Chart

---

## Author

**Isha Pandey**

B.Tech Computer Science and Engineering

Galgotias University

GitHub: https://github.com/Isha3003

---

## License

This project is developed for educational purposes.