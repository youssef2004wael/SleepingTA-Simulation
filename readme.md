# Sleeping TA Simulation

### Table Of Content 
  - [Documentation](/docs/)
  - [Problem Explantation](/src/pseudocode.md)
  - [Src](/src/)
  - [LICENSE](/LICENSE)
---
## Overview
> **This project simulates the "Sleeping TA" problem using Java. It demonstrates concurrency control using threads, mutex locks, and semaphores, modeling the interaction between Teaching Assistants (TAs) and students seeking help in a computer science department.**

In this simulation:
- Students are represented as threads.
- TAs are represented as resources controlled by mutex locks and semaphores.
- Chairs for waiting students are limited, creating resource contention.

The simulation ensures correct coordination between TAs and students:
1. If a TA is free, the student is immediately helped.
2. If all TAs are busy, students wait on one of the limited chairs.
3. If no chairs are available, students leave and come back later.
---

## Features
- **Concurrency**: Models students and TAs as threads for real-time interaction.
- **Semaphore Usage**: Ensures controlled access to TAs and chairs.
- **Dynamic Input**: Configurable number of TAs, chairs, and students.
- **Real-Time Updates**: Outputs the current status of TAs, waiting students, and those who leave.
---
## Possible Scenarios
1. **Scenario One**
There will be zero students coming to visit the TA and the TA will check the hallway outside his office to see if there are any students seated and waiting for him. If there are none, the TA will sleep in his office.

2. **Scenario Two**
When a student arrives at the TA’s office and finds the TA sleeping. Then the student will awaken the TA and ask for help. When the TA assists the student, the student's semaphore changes from 0 to 1 and waits for the TA's semaphore. When the TA finishes helping one student, he will check if there is any other student waiting in the hallway. If yes, he will help the next student and if not, TA goes back to sleeping and TA's semaphore becomes 1 and awaits student's semaphore.

3. **Scenario Three**
When a student arrives while the TA is busy with another student. Then the student who arrived will have to check if the TA is busy. If the TA is busy, the student will have to wait seated outside in the hallway until the TA is done with his session. When the TA completes his session, the student seated outside will be called in by the TA for a review session. Once all students have finished their sessions and left the TA’s office, the TA will go back to sleep after making sure no students are waiting.

4. **Scenario Four**
When a student arrives while the TA is busy in a review session, and all the seats in the hallway are occupied. Then, student will have to leave the hallway and come back later. When the student comes back, eventually, and there is a seat available, he will take a seat and wait for his turn with the TA.
---
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
