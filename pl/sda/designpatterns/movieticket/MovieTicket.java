package pl.sda.designpatterns.movieticket;

import java.util.List;

public interface MovieTicket {

    public void buyTicket();
    public String getComponentName();
    public double getPrice();
    public List<MovieTicket> getTicketComponents();

}
