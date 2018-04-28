package pl.sda.designpatterns.movieticket;

import java.util.List;

public class ColaDrink implements MovieTicket {
    private String drinkName;
    private double drinkPrice;
    private MovieTicket movieTicket;
    private List<MovieTicket> ticketComponents;

    public ColaDrink(MovieTicket movieTicket) {
        drinkName = "Coca-cola";
        drinkPrice = 3.2;
        this.movieTicket = movieTicket;
        ticketComponents = movieTicket.getTicketComponents();
        ticketComponents.add(this);
    }

    @Override
    public void buyTicket() {
        movieTicket.buyTicket();
    }

    @Override
    public String getComponentName() {
        return drinkName;
    }

    @Override
    public double getPrice() {
        return drinkPrice;
    }

    @Override
    public List<MovieTicket> getTicketComponents() {
        return ticketComponents;
    }
}
