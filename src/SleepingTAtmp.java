import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Scanner;

public class SleepingTAtmp {
    private static int numTAs;
    private static final int NUM_CHAIRS = 3;
    private static final int NUM_STUDENTS = 10;

    private static Lock[] taMutex;
    private static Semaphore chairs = new Semaphore(NUM_CHAIRS);
    private static boolean[] taSleeping;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of TAs: ");
        numTAs = scanner.nextInt();

        taMutex = new ReentrantLock[numTAs];
        taSleeping = new boolean[numTAs];

        for (int i = 0; i < numTAs; i++) {
            taMutex[i] = new ReentrantLock();
            taSleeping[i] = false;
        }

        Thread[] students = new Thread[NUM_STUDENTS];

        for (int i = 0; i < NUM_STUDENTS; i++) {
            students[i] = new Thread(() -> {
                try {
                    // Acquire a chair
                    chairs.acquire();

                    // Find an available TA
                    int availableTA = findAvailableTA();
                    if (availableTA != -1) {
                        // Enter the TA's office
                        if (taMutex[availableTA].tryLock()) {
                            try {
                                if (taSleeping[availableTA]) {
                                    wakeUpTA(availableTA);
                                }
                                getHelpFromTA(availableTA);
                            } finally {
                                taMutex[availableTA].unlock();
                            }
                        } else {
                            // No TA available, student will leave and come back later
                            System.out.println(Thread.currentThread().getName() + " is leaving and will come back later.");
                        }
                    } else {
                        // No TA available, student will leave and come back later
                        System.out.println(Thread.currentThread().getName() + " is leaving and will come back later.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Release the chair
                    chairs.release();
                }
            }, "Student " + i);
            students[i].start();
        }

        // TA threads
        for (int i = 0; i < numTAs; i++) {
            final int index = i;
            new Thread(() -> {
                while (true) {
                    // Wait for a student
                    if (taMutex[index].tryLock()) {
                        try {
                            if (!taSleeping[index]) {
                                helpStudent(index);
                                taSleeping[index] = true;
                                takeNap(index);
                            }
                        } finally {
                            taMutex[index].unlock();
                        }
                    }
                }
            }, "TA " + i).start();
        }
    }

    private static int findAvailableTA() {
        for (int i = 0; i < numTAs; i++) {
            if (taMutex[i].tryLock()) {
                taMutex[i].unlock();
                return i;
            }
        }
        return -1;
    }

    private static void getHelpFromTA(int taIndex) {
        System.out.println(Thread.currentThread().getName() + " is getting help from TA " + taIndex);
    }

    private static void wakeUpTA(int taIndex) {
        System.out.println(Thread.currentThread().getName() + " is waking up TA " + taIndex);
        taSleeping[taIndex] = false;
    }

    private static void helpStudent(int taIndex) {
        System.out.println("TA " + taIndex + " is helping a student.");
    }

    private static void takeNap(int taIndex) {
        System.out.println("TA " + taIndex + " is taking a nap.");
    }
}