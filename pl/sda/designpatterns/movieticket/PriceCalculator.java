package pl.sda.designpatterns.movieticket;


import java.util.List;

public class PriceCalculator {
    private DiscountPolicy discountPolicy;

    public double getPriceOfDiscountedComponents(List<MovieTicket>itemsWhichQualifyForDiscount) {
        discountPolicy = new TenPercentDiscountPolicy();
        return discountPolicy.calculatePrice(itemsWhichQualifyForDiscount);
    }

    public double getPriceOfNonDiscountedComponents(List<MovieTicket> itemsWhichDoNotQualifyForDiscount) {
        discountPolicy = new NoDiscountPolicy();
        return discountPolicy.calculatePrice(itemsWhichDoNotQualifyForDiscount);
    }

}
