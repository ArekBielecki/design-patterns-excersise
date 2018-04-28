package pl.sda.designpatterns.movieticket;

import java.time.LocalDate;
import java.util.*;

public class TicketGenerator {

    private String movieTitle;

    private String buyerName;

    private String buyerSurname;

    private LocalDate dateOfPurchase;

    private int seatNumber;

    private PriceCalculator priceCalculator;

    private DiscountedAndNonDiscountedItemsSorter sortedItems;

    public TicketGenerator(String movieTitle, String buyerName, String buyerSurname, LocalDate dateOfPurchase, int seatNumber,
                           PriceCalculator priceCalculator, DiscountedAndNonDiscountedItemsSorter sortedItems) {

        this.movieTitle = movieTitle;
        this.buyerName = buyerName;
        this.buyerSurname = buyerSurname;
        this.dateOfPurchase = dateOfPurchase;
        this.seatNumber = seatNumber;
        this.priceCalculator = priceCalculator;
        this.sortedItems = sortedItems;
    }

    public void printTicket() {
        System.out.println(dateOfPurchase);
        System.out.println("Movie Title: " + movieTitle);
        System.out.println("Seat Number: " + seatNumber);
        System.out.print("Name: " + buyerName + "\t");
        System.out.println("Surname: " + buyerSurname);
        System.out.println("------------------");
        printBill();


    }

    private void printBill(){
        System.out.println(new NoDiscountPolicy().discountDescription());
        System.out.println(generateDetailedPriceInformation(sortedItems.getItemsWhichDoNotQualifyForDiscount()));

        double priceOfNonDiscountedComponents = priceCalculator.getPriceOfNonDiscountedComponents(sortedItems.getItemsWhichDoNotQualifyForDiscount());

        System.out.println("Price: " + priceOfNonDiscountedComponents + "\n");

        double priceAfterDiscount = 0;
        if(sortedItems.getItemsWhichQualifyForDiscount() != null){
            System.out.println(new TenPercentDiscountPolicy().discountDescription());
            System.out.println(generateDetailedPriceInformation(sortedItems.getItemsWhichQualifyForDiscount()));

            double priceBeforeDiscount = priceCalculator.getPriceOfNonDiscountedComponents(sortedItems.getItemsWhichQualifyForDiscount());
            priceAfterDiscount = priceCalculator.getPriceOfDiscountedComponents(sortedItems.getItemsWhichQualifyForDiscount());

            System.out.println("Normal price: " + priceBeforeDiscount);
            System.out.println("Discount amount: -" + (double)Math.round((priceBeforeDiscount - priceAfterDiscount)*100)/100);
            System.out.println("Discount price: " + priceAfterDiscount);
        }

        System.out.println("---------");
        System.out.println("Total price: " + (priceOfNonDiscountedComponents + priceAfterDiscount));

    }

    private String generateDetailedPriceInformation(List<MovieTicket> listOfItems){
        Map<String, Integer> map = new LinkedHashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Item:\tQuantity:\tPrice:\n");
        if(listOfItems == null){
            return "";
        }
        for (MovieTicket item : listOfItems) {
            if (!map.containsKey(item.getComponentName())) {
                map.put(item.getComponentName(), 1);
            } else {
                map.put(item.getComponentName(), map.get(item.getComponentName()) + 1);
            }
        }
        for (String item : map.keySet()) {
            for (MovieTicket movieTicket : listOfItems) {
                if (item.equals(movieTicket.getComponentName())) {
                    stringBuilder.append(item + "\t" + map.get(item) + "\t" + movieTicket.getPrice() + "\n");
                    break;
                }
            }
        }
        return stringBuilder.toString();
    }

    public static class TicketGeneratorBuilder {
        private String movieTitle;

        private String buyerName;

        private String buyerSurname;

        private LocalDate dateOfPurchase;

        private int seatNumber;

        private PriceCalculator priceCalculator;

        private DiscountedAndNonDiscountedItemsSorter sortedItems;

        public static TicketGeneratorBuilder builder() {
            return new TicketGeneratorBuilder();
        }

        public TicketGeneratorBuilder movieTitle(String movieTitle) {
            this.movieTitle = movieTitle;
            return this;
        }

        public TicketGeneratorBuilder buyerName(String buyerName) {
            this.buyerName = buyerName;
            return this;
        }

        public TicketGeneratorBuilder buyerSurname(String buyerSurname) {
            this.buyerSurname = buyerSurname;
            return this;
        }

        public TicketGeneratorBuilder dateOfPurchase(LocalDate dateOfPurchase) {
            this.dateOfPurchase = dateOfPurchase;
            return this;
        }

        public TicketGeneratorBuilder seatNumber(int seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }

        public TicketGeneratorBuilder priceCalculator(PriceCalculator priceCalculator) {
            this.priceCalculator = priceCalculator;
            return this;
        }

        public TicketGeneratorBuilder discountedAndNonDiscountedItemsSorter(DiscountedAndNonDiscountedItemsSorter sortedItems) {
            this.sortedItems = sortedItems;
            return this;
        }

        public TicketGenerator build() {
            return new TicketGenerator(movieTitle, buyerName, buyerSurname, dateOfPurchase, seatNumber, priceCalculator, sortedItems);
        }
    }
}
