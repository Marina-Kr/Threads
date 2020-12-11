import java.util.Objects;

public class RequestHandler implements Runnable {

    private final String requestHandlerName;
    private final FrontendBank frontendBank;
    private final BackendBank backendBank;

    RequestHandler(String requestHandlerName, FrontendBank frontendBank, BackendBank backendBank) {
        this.requestHandlerName = requestHandlerName;
        this.frontendBank = frontendBank;
        this.backendBank = backendBank;
    }

    @Override
    public void run() {
        while (true) {
            Client client = frontendBank.get();
            if (Objects.nonNull(client)) {
                System.out.println(requestHandlerName + ": Получена заявка на обработку по клиенту - " + client.getClientThreadName());
                backendBank.processing(client);
            }
        }
    }

}
