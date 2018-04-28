package pl.sda.designpatterns.movieticket;

import java.util.NoSuchElementException;

public class MovieFactory {

    public MovieTicket getMovieTicket(int choiceNumber) {
        switch (choiceNumber) {
            case 1:
                return new PulpFictionMovieTicket();
            case 2:
                return new GodFatherMovieTicket();
            default:
                throw new NoSuchElementException();
        }
    }
}
