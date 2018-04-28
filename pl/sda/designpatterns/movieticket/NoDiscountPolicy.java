package pl.sda.designpatterns.movieticket;

import java.util.List;

public class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public double calculatePrice(List<MovieTicket> listOfComponents) {
        if(listOfComponents == null){
            return 0;
        }
        return listOfComponents.stream().mapToDouble(x -> x.getPrice()).sum();
    }

    @Override
    public String discountDescription() {
        return "Normal price: ";
    }
}
