package pl.sda.designpatterns.movieticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GodFatherMovieTicket implements MovieTicket {

    private String movieTitle;
    private double ticketPrice;
    private List<MovieTicket> ticketComponents;

    public GodFatherMovieTicket() {
        movieTitle = "The Godfather";
        ticketPrice = 15;
        ticketComponents = new ArrayList<>();
        ticketComponents.add(this);
    }

    @Override
    public void buyTicket() {

    }

    @Override
    public String getComponentName() {
        return movieTitle;
    }

    @Override
    public double getPrice() {
        return ticketPrice;
    }

    @Override
    public List<MovieTicket> getTicketComponents() {
        return ticketComponents;
    }
}
