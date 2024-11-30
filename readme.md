# Sleeping TA Simulation

### Overview
##### This project simulates the "Sleeping TA" problem using Java. It demonstrates concurrency control using threads, mutex locks, and semaphores, modeling the interaction between Teaching Assistants (TAs) and students seeking help in a computer science department.

In this simulation:
- Students are represented as threads.
- TAs are represented as resources controlled by mutex locks and semaphores.
- Chairs for waiting students are limited, creating resource contention.

The simulation ensures correct coordination between TAs and students:
1. If a TA is free, the student is immediately helped.
2. If all TAs are busy, students wait on one of the limited chairs.
3. If no chairs are available, students leave and come back later.

## Features
- **Concurrency**: Models students and TAs as threads for real-time interaction.
- **Semaphore Usage**: Ensures controlled access to TAs and chairs.
- **Dynamic Input**: Configurable number of TAs, chairs, and students.
- **Real-Time Updates**: Outputs the current status of TAs, waiting students, and those who leave.

## Problem Description
A university computer science department has:
- A small office with `N` TAs and `M` chairs for students waiting in line.
- Students arrive randomly to seek help. If a TA is free, the student is assisted. Otherwise:
  - If there are empty chairs, the student waits.
  - If no chairs are free, the student leaves and tries again later.
- TAs take a nap if no students are waiting.

### Input
- **Number of TAs**: The total number of teaching assistants available to help students.
- **Number of Chairs**: The total number of chairs for students waiting in line.
- **Number of Students**: The total number of students seeking help.

### Output
- **Real-Time Status**:
  - Number of TAs working.
  - Number of TAs sleeping.
  - Number of students waiting on chairs.
  - Number of students leaving to return later.
