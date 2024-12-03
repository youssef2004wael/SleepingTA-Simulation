package sleepingta;
import java.util.concurrent.Semaphore;
/// Shared Resources Definition 
public class SleepingTA {
    private final Semaphore taSemaphore; // To signal TA availability
    private final Semaphore chairsSemaphore; // To manage chairs
    private int waitingStudents = 0; // Students currently waiting
    private final int totalChairs; // Total number of chairs

    public SleepingTA(int totalChairs) {
        this.taSemaphore = new Semaphore(0); // Initially, TA is sleeping
        this.chairsSemaphore = new Semaphore(totalChairs); // Chairs available
        this.totalChairs = totalChairs;
    }

    public synchronized boolean sitOnChair() {
        if (chairsSemaphore.tryAcquire()) { // Try to acquire a chair
            waitingStudents++;
            System.out.println("Student sits on a chair. Waiting students: " + waitingStudents);
            return true;
        } else {
            System.out.println("No chairs available. Student will try later.");
            return false;
        }
    }

    public synchronized void leaveChair() {
        chairsSemaphore.release();
        waitingStudents--;
    }

    public void signalTA() {
        System.out.println("Student wakes up the TA.");
        taSemaphore.release(); // Signal the TA to wake up
    }

    public void helpStudent() throws InterruptedException {
        taSemaphore.acquire(); // Wait until a student signals
        System.out.println("TA is helping a student.");
    }
}
