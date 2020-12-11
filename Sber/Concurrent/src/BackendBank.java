import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BackendBank {
    private volatile int capital = 0;

    ReentrantLock lock;
    Condition cond;

    BackendBank() {
        lock = new ReentrantLock();
        cond = lock.newCondition();
    }

    public void processing(Client client) {
        if (RequestType.CREDIT == client.getRequestType()) {
            lock.lock();
            try {
                while (capital < client.getAmount()) {
                    System.out.println("БЭК-СИСТЕМА: Заявка {clientThreadName='" + client.getClientThreadName() + "', amount='" + client.getAmount() +
                            "', requestType='" + client.getRequestType() + "'} НЕ ВЫПОЛНЕНА. Сумма больше баланса банка. Баланс банка: " + capital);
                    cond.await();
                }
                capital = capital - client.getAmount();
                cond.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            lock.lock();
            try {
                while (client.getAmount() == 0) {
                    cond.await();
                }
                capital = capital + client.getAmount();
                System.out.println("БЭК-СИСТЕМА: Заявка {clientThreadName='" + client.getClientThreadName() + "', amount='"
                        + client.getAmount() + "', requestType='" + client.getRequestType() + "'} УСПЕШНО ВЫПОЛНЕНА. Баланс банка: " + capital);
                cond.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
