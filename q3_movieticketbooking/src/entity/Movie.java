package q3_movieticketbooking.src.entity;

import java.util.List;

public class Movie {
    private long id;
    private String name;
    private int rating;
    private List<Show> showList;

    public Movie(){}

    public Movie(long id, String name, int rating, List<Show> showList) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.showList = showList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Show> getShowList() {
        return showList;
    }

    public void setShowList(List<Show> showList) {
        this.showList = showList;
    }
}
