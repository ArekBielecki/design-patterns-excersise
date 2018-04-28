package pl.sda.designpatterns.movieticket;

import java.util.List;

public class TenPercentDiscountPolicy implements DiscountPolicy {

    @Override
    public double calculatePrice(List<MovieTicket> listOfComponents) {
        if(listOfComponents ==null){
            return 0;
        }
        return (listOfComponents.stream().mapToDouble(x -> x.getPrice()).sum())*0.9;
    }

    @Override
    public String discountDescription() {
        return "*** 10% DISCOUNT ***";
    }
}
