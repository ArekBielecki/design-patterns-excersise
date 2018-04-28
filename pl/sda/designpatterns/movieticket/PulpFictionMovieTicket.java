package pl.sda.designpatterns.movieticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PulpFictionMovieTicket implements MovieTicket {
    private String movieTitle;
    private double ticketPrice;
    private List<MovieTicket> ticketComponents;

    public PulpFictionMovieTicket() {
        movieTitle = "Pulp Fiction";
        ticketPrice = 20;
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
