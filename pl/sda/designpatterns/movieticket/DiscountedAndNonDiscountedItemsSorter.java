package pl.sda.designpatterns.movieticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountedAndNonDiscountedItemsSorter {

    private List<MovieTicket> listOfComponents;
    private List<MovieTicket> itemsWhichQualifyForDiscount;
    private List<MovieTicket> itemsWhichDoNotQualifyForDiscount;

    public DiscountedAndNonDiscountedItemsSorter(List<MovieTicket> listOfComponents){
        this.listOfComponents = listOfComponents;
        sortItemsWhichQualifyForDiscount(numberOfEntitledDiscounts());
    }

    public List<MovieTicket> getItemsWhichQualifyForDiscount() {
        return itemsWhichQualifyForDiscount;
    }

    public List<MovieTicket> getItemsWhichDoNotQualifyForDiscount() {
        return itemsWhichDoNotQualifyForDiscount;
    }


    private int numberOfEntitledDiscounts() {
        int crispsSnacksNumber = (int) listOfComponents.stream().filter(x -> x instanceof CrispsSnack).count();
        int colaDrinksNumber = (int) listOfComponents.stream().filter(x -> x instanceof ColaDrink).count();
        int chocolateBarsSnacksNumber = (int) listOfComponents.stream().filter(x -> x instanceof ChocolateBarSnack).count();

        return Arrays.asList(crispsSnacksNumber, colaDrinksNumber, chocolateBarsSnacksNumber)
                .stream()
                .reduce(Integer.MAX_VALUE, (a, b) -> Integer.min(a, b));

    }

    private void sortItemsWhichQualifyForDiscount(int numberOfEntitledDiscounts) {
        if (numberOfEntitledDiscounts == 0) {
            itemsWhichDoNotQualifyForDiscount = listOfComponents;
        } else {
            int numberOfCrispsSnacksEntitledForDiscount = 0;
            int numberOfColaDrinksEntitledForDiscount = 0;
            int numberOfChocolateBarsEntitledForDiscount = 0;

            itemsWhichQualifyForDiscount = new ArrayList<>();
            itemsWhichDoNotQualifyForDiscount = new ArrayList<>();

            for (MovieTicket component : listOfComponents) {
                if (component instanceof CrispsSnack &&
                        numberOfCrispsSnacksEntitledForDiscount < numberOfEntitledDiscounts) {

                    itemsWhichQualifyForDiscount.add(component);
                    numberOfCrispsSnacksEntitledForDiscount++;
                } else if (component instanceof ColaDrink &&
                        numberOfColaDrinksEntitledForDiscount < numberOfEntitledDiscounts) {

                    itemsWhichQualifyForDiscount.add(component);
                    numberOfColaDrinksEntitledForDiscount++;
                } else if (component instanceof ChocolateBarSnack &&
                        numberOfChocolateBarsEntitledForDiscount < numberOfEntitledDiscounts) {

                    itemsWhichQualifyForDiscount.add(component);
                    numberOfChocolateBarsEntitledForDiscount++;
                } else {
                    itemsWhichDoNotQualifyForDiscount.add(component);
                }
            }
        }
    }
}
