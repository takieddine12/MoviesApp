package taki.eddine.myapplication;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import taki.eddine.myapplication.FragmentsUi.NowPlayingMoviesFragment;
import taki.eddine.myapplication.FragmentsUi.PopularMoviesFragment;
import taki.eddine.myapplication.FragmentsUi.TopRatedMoviesFragmet;
import taki.eddine.myapplication.FragmentsUi.UpcomingMoviesFragment;
import taki.eddine.myapplication.MoviesDetails.LatestMealsModel;
import taki.eddine.myapplication.MoviesDetails.MoviesDetails;
import taki.eddine.myapplication.NetworkUi.ApiInterface;
import taki.eddine.myapplication.databinding.ActivityMainBinding;
import timber.log.Timber;


public class MainActivity extends AppCompatActivity {
    private CompositeDisposable compositeDisposable;
    private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding  = DataBindingUtil.setContentView(this,R.layout.activity_main);
        compositeDisposable = new CompositeDisposable();
        setSupportActionBar(activityMainBinding.toolbar);


        InitViewPager();
        getVolleyApi();


    }


    //--Set up the viewpager and fragments
    private void InitViewPager() {

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Popular",PopularMoviesFragment.class)
                .add("Upcoming", UpcomingMoviesFragment.class)
                .add("Top Rated",TopRatedMoviesFragmet.class)
                .add("Now Playing",NowPlayingMoviesFragment.class)
                .create());

        activityMainBinding.viewpager.setAdapter(adapter);
        activityMainBinding.tabs.setViewPager(activityMainBinding.viewpager);
    }

    //--setting up the last movies with slider
    private void getVolleyApi() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okhttp = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor);
        new Retrofit.Builder().baseUrl(Utils.Popular_Movies_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okhttp.build())
                .build().create(ApiInterface.class)
                .getLatestMeals(Utils.api_key).toObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LatestMealsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                     compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(LatestMealsModel latestMealsModel) {

                        Timber.d("Details Title" + latestMealsModel.getOriginal_title());
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put(latestMealsModel.getOriginal_title(),"https://image.tmdb.org/t/p/w185/" + latestMealsModel.getPoster_path());

                        for (String name : hashMap.keySet()) {
                            TextSliderView textSliderView = new TextSliderView(MainActivity.this);
                            // initialize a SliderLayout
                            textSliderView
                                    .description(name)
                                    .image(hashMap.get(name))
                                    .setScaleType(BaseSliderView.ScaleType.Fit);

                            //add your extra information
                            textSliderView.bundle(new Bundle());
                            textSliderView.getBundle()
                                    .putString("extra", name);

                            activityMainBinding.slider.addSlider(textSliderView);

                            int MOVIEID = latestMealsModel.getId();
                            if(String.valueOf(MOVIEID).equals("null")){

                            }else{
                                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                    @Override
                                    public void onSliderClick(BaseSliderView slider) {
                                        Intent intent = new Intent(MainActivity.this,MoviesDetails.class);
                                        intent.putExtra("movieid",latestMealsModel.getId());
                                        startActivity(intent);
                                    }
                                });
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                      Timber.d("Exception " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                      Timber.d("Response Finished..");
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}


