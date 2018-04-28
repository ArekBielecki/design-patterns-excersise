package pl.sda.designpatterns.movieticket;

public class Main {
    public static void main(String[] args) {
        AppFacade appFacade = AppFacade.getInstance();
        appFacade.runCustomerServiceInterface();
    }
}
