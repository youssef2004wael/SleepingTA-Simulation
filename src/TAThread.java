package sleepingta;
import sleepingta.SleepingTA;
class TA implements Runnable {
    
    private final SleepingTA taRoom;

    public TA(SleepingTA taRoom) {
        this.taRoom = taRoom;
    }

    @Override
    public void run() {
        while (true) {
            try {
                taRoom.helpStudent();
                Thread.sleep(2000); // Simulate helping a student
                taRoom.leaveChair();
                System.out.println("TA finished helping a student.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
