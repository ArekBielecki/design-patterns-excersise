package pl.sda.designpatterns.movieticket;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static pl.sda.designpatterns.movieticket.TicketGenerator.*;

public class AppFacade {
    private static AppFacade instance;
    private Scanner input = new Scanner(System.in);

    private AppFacade(){};

    public static AppFacade getInstance() {
        if (instance == null) {
            instance = new AppFacade();
        }
        return instance;
    }

    public void runCustomerServiceInterface() {

        printInitialMenu();
        MovieFactory movieFactory = new MovieFactory();
        MovieTicket ticket = movieFactory.getMovieTicket(input.nextInt());
        String movieTitle = ticket.getComponentName();
        addSnacksToOrder(ticket);

        TicketGenerator ticketGenerator = TicketGeneratorBuilder
                .builder()
                .movieTitle(movieTitle)
                .buyerName("Name")
                .buyerSurname("Surname")
                .seatNumber(10)
                .dateOfPurchase(LocalDate.now())
                .priceCalculator(new PriceCalculator())
                .discountedAndNonDiscountedItemsSorter(new DiscountedAndNonDiscountedItemsSorter(ticket.getTicketComponents()))
                .build();

        ticketGenerator.printTicket();
    }

    private void addSnacksToOrder(MovieTicket ticket) {
        printSnacksOffer();
        int choiceNumber = input.nextInt();
        while (choiceNumber != 4) {
            switch (choiceNumber) {
                case 1:
                    ticket = new CrispsSnack(ticket);
                    break;
                case 2:
                    ticket = new ColaDrink(ticket);
                    break;
                case 3:
                    ticket = new ChocolateBarSnack(ticket);
                    break;
                default:
                    System.out.println("Wrong number chosen");
                    break;
            }
            printSnacksOffer();
            choiceNumber = input.nextInt();
        }

    }

    private void printInitialMenu(){
        System.out.println("Welcome to our Movie Ticket selling servie");
        System.out.println("Currently we offer tickets for following movies - choose from following numbers:");
        System.out.println("1. Pulp Fiction");
        System.out.println("2. The GodFather");
    }

    private void printSnacksOffer() {
        System.out.println("Add snacks to ticket: ");
        System.out.println("1. Crisps");
        System.out.println("2. Cola");
        System.out.println("3. Chocolate bar");
        System.out.println("4. Skip");
    }

    public static void main(String[] args) {
        MovieFactory movieFactory = new MovieFactory();
        MovieTicket ticket = movieFactory.getMovieTicket(1);
        ticket = new CrispsSnack(ticket);
        ticket = new CrispsSnack(ticket);
        ticket = new ColaDrink(ticket);
        ticket = new ColaDrink(ticket);
        ticket = new ColaDrink(ticket);
        ticket = new ChocolateBarSnack(ticket);
        PriceCalculator priceCalculator = new PriceCalculator();
        double price = priceCalculator.getPriceOfDiscountedComponents(new DiscountedAndNonDiscountedItemsSorter(ticket.getTicketComponents()).getItemsWhichQualifyForDiscount());
        System.out.println(price);
        List<MovieTicket> itemsWithoutDiscount = new DiscountedAndNonDiscountedItemsSorter(ticket.getTicketComponents()).getItemsWhichDoNotQualifyForDiscount();
        TicketGenerator ticketGenerator = TicketGeneratorBuilder
                .builder()
                .movieTitle(ticket.getComponentName())
                .buyerName("Arek")
                .buyerSurname("Bielecki")
                .seatNumber(10)
                .dateOfPurchase(LocalDate.now())
                .priceCalculator(priceCalculator)
                .discountedAndNonDiscountedItemsSorter(new DiscountedAndNonDiscountedItemsSorter(ticket.getTicketComponents()))
                .build();

        ticketGenerator.printTicket();

    }
}
