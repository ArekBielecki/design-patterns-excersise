package pl.sda.designpatterns.movieticket;

import java.util.List;

public interface DiscountPolicy {

    double calculatePrice(List<MovieTicket> listOfComponents);

    String discountDescription();


}
