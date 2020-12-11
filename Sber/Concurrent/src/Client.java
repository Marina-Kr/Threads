import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

class Client extends Thread {

    private String clientThreadName;
    private int amount;
    private RequestType requestType;
    private FrontendBank bank;


    Client(String clientThreadName, int amount, RequestType requestType, FrontendBank bank) {
        this.amount = amount;
        this.clientThreadName = clientThreadName;
        this.requestType = requestType;
        this.bank = bank;


    }

    @Override
    public void run() {
        System.out.println(clientThreadName + ": Заявка {clientThreadName='" + clientThreadName + "', amount='"
                + amount + "', requestType='" + requestType + "'} отправлена в Банк");
        bank.put(this);
    }

    public String getClientThreadName() {
        return clientThreadName;
    }

    public void setClientThreadName(String clientThreadName) {
        this.clientThreadName = clientThreadName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public FrontendBank getBank() {
        return bank;
    }

    public void setBank(FrontendBank bank) {
        this.bank = bank;
    }
}
