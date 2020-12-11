import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class FrontendBank {
    private final Queue<Client> clientQueue = new ArrayDeque<>(2);
    private Semaphore sem;

    FrontendBank(Semaphore sem) {
        this.sem = sem;
    }

    public void put(Client client) {
        try {
            sem.acquire();
            clientQueue.add(client);
            sem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Client get() {
        try {
            sem.acquire();
            Client client = clientQueue.poll();
            sem.release();
            return client;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
