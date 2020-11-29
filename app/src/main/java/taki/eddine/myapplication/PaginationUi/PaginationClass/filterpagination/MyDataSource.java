package taki.eddine.myapplication.PaginationUi.PaginationClass.filterpagination;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.concurrent.Executor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import taki.eddine.myapplication.datamodels.MoviesDataModel;
import taki.eddine.myapplication.NetworkUi.ApiInterface;

public class MyDataSource extends PageKeyedDataSource<Integer, MoviesDataModel> {
    private String input;
    private Executor executor;
    int page = 1;

    public MyDataSource(String input, Executor executors) {
        this.input = input;
        executor = executors;
    }
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, MoviesDataModel> callback) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder ok = new OkHttpClient.Builder();
        ok.addInterceptor(httpLoggingInterceptor);

        new Retrofit.Builder().baseUrl(Utils.Popular_Movies_Url)
                .client(ok.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiInterface.class)
                .getSearchMovie(Utils.api_key,"en-US",input,page,false)
                .enqueue(new Callback<MoviesDataModel>() {
                    @Override
                    public void onResponse(Call<MoviesDataModel> call, Response<MoviesDataModel> response) {
                        if(response.body() != null)
                        {
                            callback.onResult(response.body().getList(),null,page);
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
                .build().create(ApiInterface.class).getSearchMovie(Utils.api_key,"en-US",input,page,false)
                .enqueue(new Callback<MoviesDataModel>() {
                    @Override
                    public void onResponse(Call<MoviesDataModel> call, Response<MoviesDataModel> response) {
                         Integer adjacentKey = (params.key < 1) ? params.key - 1 : null;
                         if(response.body() != null)
                         {
                             callback.onResult(response.body().getList(),adjacentKey);
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
                .build().create(ApiInterface.class).getSearchMovie(Utils.api_key,"en-US",input,page,false)
                .enqueue(new Callback<MoviesDataModel>() {
                    @Override
                    public void onResponse(Call<MoviesDataModel> call, Response<MoviesDataModel> response) {
                        if(response.body() != null)
                        {
                            callback.onResult(response.body().getList(),page);
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesDataModel> call, Throwable t) {

                    }
                });
    }
}
