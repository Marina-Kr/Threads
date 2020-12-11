import java.util.concurrent.Semaphore;

public class ToyBank {

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(2);
        FrontendBank frontendBank = new FrontendBank(sem);
        BackendBank backendBank = new BackendBank();

        new Thread(new Client("Клиент_1", 10000, RequestType.REPAYMENT, frontendBank)).start();
        new Thread(new Client("Клиент_2", 15000, RequestType.REPAYMENT, frontendBank)).start();
        new Thread(new Client("Клиент_3", 20000, RequestType.REPAYMENT, frontendBank)).start();
        new Thread(new Client("Клиент_4", 5000, RequestType.CREDIT, frontendBank)).start();
        new Thread(new Client("Клиент_5", 150000, RequestType.CREDIT, frontendBank)).start();

        new Thread(new RequestHandler("Обработчик заявок_1", frontendBank, backendBank)).start();
        new Thread(new RequestHandler("Обработчик заявок_2", frontendBank, backendBank)).start();

    }

}
