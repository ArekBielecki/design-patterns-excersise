package pl.sda.designpatterns.movieticket;

import java.util.List;

public class ChocolateBarSnack implements MovieTicket {
    private String snackName;
    private double snackPrice;
    private MovieTicket movieTicket;
    private List<MovieTicket> ticketComponents;

    public ChocolateBarSnack(MovieTicket movieTicket) {
        this.movieTicket = movieTicket;
        snackName = "Chocolate bar";
        snackPrice = 2.7;
        ticketComponents = movieTicket.getTicketComponents();
        ticketComponents.add(this);
    }

    @Override
    public void buyTicket() {
        movieTicket.buyTicket();
    }

    @Override
    public String getComponentName() {
        return snackName;
    }

    @Override
    public double getPrice() {
        return snackPrice;
    }

    @Override
    public List<MovieTicket> getTicketComponents() {
        return ticketComponents;
    }
}
