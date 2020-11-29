package taki.eddine.myapplication.NetworkUi;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import taki.eddine.myapplication.MoviesDetails.LatestMealsModel;
import taki.eddine.myapplication.datamodels.MoviesDetailsModel;
import taki.eddine.myapplication.datamodels.MoviesDataModel;

public interface ApiInterface {


    @GET("movie/popular?")
    Call<MoviesDataModel> getPopularMovies(@Query("api_key")String apikey,
                                           @Query("language")String lanaguage,
                                           @Query("page")int page,
                                           @Query("pagesize")int pagesize);


    @GET("movie/upcoming?")
    Call<MoviesDataModel> getUpcomingMovies(@Query("api_key")String apikey,
                                            @Query("language")String language,
                                            @Query("page") int page,
                                            @Query("pagesize")int pagesize);

    @GET("movie/top_rated?")
    Call<MoviesDataModel> getUTopRatedMovies(@Query("api_key")String apikey,
                                             @Query("language")String language,
                                             @Query("page") int page,
                                             @Query("pagesize")int pagesize);

    @GET("movie/now_playing?")
    Call<MoviesDataModel> getPlayingNowMovies(@Query("api_key")String apikey,
                                              @Query("language")String language,
                                              @Query("page") int page,
                                              @Query("pagesize")int pagesize);


    @GET("movie/{id}")
    Flowable<MoviesDetailsModel> getDetails(@Path("id") int movieid,
                                            @Query("api_key")String apikey);

    @GET("search/movie?")
    Call<MoviesDataModel> getSearchMovie(@Query("api_key")String apikey,
                                         @Query("language") String language,
                                         @Query("query")String query,
                                         @Query("page") int page,
                                         @Query("include_adult") boolean adult);


    @GET("movie/latest?")
    Flowable<LatestMealsModel> getLatestMeals(@Query("api_key") String apikey);

}
