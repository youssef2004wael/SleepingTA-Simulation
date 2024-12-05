package src;

public class Main {
    public static void main(String[] args) {
        Simulation.takeUserInput(); // Console-based input
        Simulation.createStudentThreads();

        while (Simulation.isStudentThreadRunning()) {
            Simulation.getInfo(true); // Display status
            try {
                Thread.sleep(1000); // Pause before refreshing info
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Simulation.terminateStudentThreads();
        System.out.println("Simulation complete.");
    }
}
