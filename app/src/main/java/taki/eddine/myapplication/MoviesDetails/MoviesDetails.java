package taki.eddine.myapplication.MoviesDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import taki.eddine.myapplication.MainActivity;
import taki.eddine.myapplication.databinding.ActivityFavdetailsBinding;
import taki.eddine.myapplication.databinding.ActivityMoviesDetails2Binding;
import taki.eddine.myapplication.datamodels.DataModel;
import taki.eddine.myapplication.datamodels.MoviesDetailsModel;
import taki.eddine.myapplication.NetworkUi.ApiInterface;
import taki.eddine.myapplication.R;
import taki.eddine.myapplication.RoomDB.MoviesDatabase;
import timber.log.Timber;

public class MoviesDetails extends AppCompatActivity {


    private ActivityMoviesDetails2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movies_details2);

        setSupportActionBar(binding.detailstoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ///getting movies id to search for details
        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("movieid", 0);
            Timber.d("movie id 2 " + id);
            getDetails(id);
        }

    }
    ///getting details of movies from api
    private void getDetails(int id) {
        new Retrofit.Builder().baseUrl(Utils.Popular_Movies_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiInterface.class).getDetails(id,Utils.api_key)
                .toObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesDetailsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoviesDetailsModel moviesDetailsModel) {
                        String movie_url = moviesDetailsModel.getUrl();
                        String movie_title = moviesDetailsModel.getTitle();
                        String language = moviesDetailsModel.getLanguage();
                        String popularity = moviesDetailsModel.getPopularity();
                        String overview = moviesDetailsModel.getOverview();
                        String releasedate = moviesDetailsModel.getReleasedate();
                        String budget = moviesDetailsModel.getBudget();
                        String voteaverage = moviesDetailsModel.getVoteaverage();

                        binding.setModel(new MoviesDetailsModel(movie_url,movie_title,language,popularity,overview,releasedate,budget,voteaverage));

                        DataModel dBdata = new DataModel("https://image.tmdb.org/t/p/w185//"+movie_url, movie_title, releasedate,id);

                        binding.addfav.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Executors.newFixedThreadPool(1).execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        Long result =   MoviesDatabase.getInstance(MoviesDetails.this)
                                                .moviesDao().insertMovies(dBdata);
                                        if(result != -1)
                                        {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(MoviesDetails.this,"Movie was added",Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                     Timber.d("Exception " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(MoviesDetails.this, MainActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
