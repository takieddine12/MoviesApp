package taki.eddine.myapplication.datamodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SlideModel {

    @SerializedName("poster_path")
    @Expose
    private String moviesurl;

    private List<SlideModel> list;

    public List<SlideModel> getList() {
        return list;
    }

    public void setList(List<SlideModel> list) {
        this.list = list;
    }

    public String getMoviesurl() {
        return moviesurl;
    }

    public void setMoviesurl(String moviesurl) {
        this.moviesurl = moviesurl;
    }
}
