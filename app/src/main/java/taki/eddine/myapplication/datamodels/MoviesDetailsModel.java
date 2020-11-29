package taki.eddine.myapplication.datamodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesDetailsModel {

    @SerializedName("poster_path") @Expose private String url;
    @SerializedName("original_title") @Expose private String title;
    @SerializedName("original_language") @Expose private String language;
    @SerializedName("popularity") @Expose private String popularity;
    @SerializedName("overview") @Expose private String overview;
    @SerializedName("release_date") @Expose private String releasedate;
    @SerializedName("budget") @Expose private String budget;
    @SerializedName("vote_average") @Expose  private String voteaverage;

    public MoviesDetailsModel(String url, String title, String language, String popularity, String overview, String releasedate, String budget, String voteaverage) {
        this.url = url;
        this.title = title;
        this.language = language;
        this.popularity = popularity;
        this.overview = overview;
        this.releasedate = releasedate;
        this.budget = budget;
        this.voteaverage = voteaverage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getVoteaverage() {
        return voteaverage;
    }

    public void setVoteaverage(String voteaverage) {
        this.voteaverage = voteaverage;
    }
}
