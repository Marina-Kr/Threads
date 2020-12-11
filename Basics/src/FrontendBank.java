import java.util.ArrayDeque;
import java.util.Queue;

public class FrontendBank {
    private final Queue<Client> clientQueue = new ArrayDeque<>(2);

    public synchronized void put(Client client) {
        while (clientQueue.size() > 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        clientQueue.add(client);
    }

    public synchronized Client get() {
        Client client = clientQueue.poll();
        this.notifyAll();
        return client;
    }

}
