package sleepingta;
import sleepingta.SleepingTA;
class Student implements Runnable {
    private final SleepingTA taRoom;

    public Student(SleepingTA taRoom) {
        this.taRoom = taRoom;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (taRoom.sitOnChair()) {
                    taRoom.signalTA();
                    break; // Student gets help and leaves
                } else {
                    Thread.sleep(1000); // Wait before trying again
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
