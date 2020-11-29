package taki.eddine.myapplication.datamodels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DataModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "url")
    private String url;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "releasedate")
    private String releasedata;
    @ColumnInfo(name = "movieid")
    private int moviesid;

    public DataModel(String url, String title, String releasedata, int moviesid) {
        this.url = url;
        this.title = title;
        this.releasedata = releasedata;
        this.moviesid = moviesid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoviesid() {
        return moviesid;
    }

    public void setMoviesid(int moviesid) {
        this.moviesid = moviesid;
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

    public String getReleasedata() {
        return releasedata;
    }

    public void setReleasedata(String releasedata) {
        this.releasedata = releasedata;
    }

}
