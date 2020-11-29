package taki.eddine.myapplication.PaginationUi.PaginationClass.upcomingmoviessource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import taki.eddine.myapplication.datamodels.MoviesDataModel;
import taki.eddine.myapplication.NetworkUi.ApiInterface;

public class UpcomingSource extends PageKeyedDataSource<Integer, MoviesDataModel> {
    private int page  = 1;
    private int pagesize = 20;
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, MoviesDataModel> callback) {
        new Retrofit.Builder().baseUrl(Utils.Popular_Movies_Url).addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiInterface.class).getUpcomingMovies(Utils.api_key,"en-US",page,pagesize)
                .enqueue(new Callback<MoviesDataModel>() {
                    @Override
                    public void onResponse(Call<MoviesDataModel> call, Response<MoviesDataModel> response) {
                        if(response.isSuccessful())
                        {
                            callback.onResult(response.body().getList(),null,page+1);
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesDataModel> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MoviesDataModel> callback) {
        new Retrofit.Builder().baseUrl(Utils.Popular_Movies_Url).addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiInterface.class).getUpcomingMovies(Utils.api_key,"en-US",page--,pagesize)
                .enqueue(new Callback<MoviesDataModel>() {
                    @Override
                    public void onResponse(Call<MoviesDataModel> call, Response<MoviesDataModel> response) {

                        //Integer adjectKey = (params.key > 1) ? params.key - 1 : null;
                        if(response.isSuccessful())
                        {
                            callback.onResult(response.body().getList(),params.key - 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesDataModel> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MoviesDataModel> callback) {
        new Retrofit.Builder().baseUrl(Utils.Popular_Movies_Url).addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiInterface.class).getUpcomingMovies(Utils.api_key,"en-US",page++,pagesize)
                .enqueue(new Callback<MoviesDataModel>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesDataModel> call, @NonNull Response<MoviesDataModel> response) {

                        if(response.isSuccessful())
                        {
                            callback.onResult(response.body().getList(),params.key + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesDataModel> call, Throwable t) {

                    }
                });
    }
}
