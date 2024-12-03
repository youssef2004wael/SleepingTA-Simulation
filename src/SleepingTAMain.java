import sleepingta.SleepingTA;
import sleepingta.Student;
import sleepingta.TA;
public class SleepingTAMain {

    public static void main(String[] args) {
        // after the Gui we'll take inputs values from the text boxes

        int numberOfTAs = 1; // Number of TAs
        int numberOfChairs = 3; // Number of chairs
        int numberOfStudents = 20; // Number of students

        SleepingTA taRoom = new SleepingTA(numberOfChairs);

        // Create TA threads
        for (int i = 0; i < numberOfTAs; i++) {
            new Thread(new TA(taRoom)).start();
        }

        // Create Student threads
        for (int i = 0; i < numberOfStudents; i++) {
            new Thread(new Student(taRoom)).start();
            try {
                Thread.sleep(500); // Stagger student arrivals
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
