package taki.eddine.myapplication.datamodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MoviesDataModel {

    @SerializedName("original_title") @Expose private String title;
    @SerializedName("poster_path") @Expose private String movie_poster;
    @SerializedName("id") @Expose private int movie_id;

    @SerializedName("results")
    private List<MoviesDataModel> list;

    public String getTitle() {
        return title;
    }

    public List<MoviesDataModel> getList() {
        return list;
    }

    public void setList(List<MoviesDataModel> list) {
        this.list = list;
    }

    public MoviesDataModel(String title, String movie_poster, int movie_id) {
        this.title = title;
        this.movie_poster = movie_poster;
        this.movie_id = movie_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovie_poster() {
        return movie_poster;
    }

    public void setMovie_poster(String movie_poster) {
        this.movie_poster = movie_poster;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }
}
