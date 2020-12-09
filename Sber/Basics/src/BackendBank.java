public class BackendBank {
    private volatile int capital = 0;

    public synchronized void processing(Client client) {
        if (RequestType.CREDIT == client.getRequestType()) {
            while (capital < client.getAmount()) {
                System.out.println("БЭК-СИСТЕМА: Заявка {clientThreadName='" + client.getClientThreadName() + "', amount='" + client.getAmount() +
                        "', requestType='" + client.getRequestType() + "'} НЕ ВЫПОЛНЕНА. Сумма больше баланса банка. Баланс банка: " + capital);
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            capital = capital - client.getAmount();
        } else {
            capital = capital + client.getAmount();
        }
        System.out.println("БЭК-СИСТЕМА: Заявка {clientThreadName='" + client.getClientThreadName() + "', amount='"
                + client.getAmount() + "', requestType='" + client.getRequestType() + "'} УСПЕШНО ВЫПОЛНЕНА. Баланс банка: " + capital);
        notifyAll();
    }
}