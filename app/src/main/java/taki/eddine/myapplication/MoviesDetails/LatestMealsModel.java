package taki.eddine.myapplication.MoviesDetails;

public class LatestMealsModel {

    private String poster_path;
    private String original_title;
    private int id;


    public LatestMealsModel(String poster_path, String original_title, int id) {
        this.poster_path = poster_path;
        this.original_title = original_title;
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
