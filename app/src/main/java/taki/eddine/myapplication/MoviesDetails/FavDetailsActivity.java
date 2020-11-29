package taki.eddine.myapplication.MoviesDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.concurrent.Executors;

import taki.eddine.myapplication.Adapter;
import taki.eddine.myapplication.datamodels.DataModel;
import taki.eddine.myapplication.R;
import taki.eddine.myapplication.RoomDB.MoviesDatabase;
import taki.eddine.myapplication.databinding.ActivityFavdetailsBinding;

public class FavDetailsActivity extends AppCompatActivity {


    private Adapter adapter;
    private List<DataModel> list;
    private ActivityFavdetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_favdetails);

        setSupportActionBar(binding.favtoolbar);
        binding.favtoolbar.setTitle("Favourite Movies");
        binding.favtoolbar.setTitleTextColor(Color.WHITE);
        binding.favrecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.favrecycler.setHasFixedSize(true);

        ///Delete All MOVIES
        deleteOneMovie();

        ///Swipe delete a movie
        deleteMovieOnSwipe();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.roommenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteitem:

                Executors.newFixedThreadPool(1).execute(new Runnable() {
                    @Override
                    public void run() {
                        MoviesDatabase.getInstance(FavDetailsActivity.this).moviesDao().deleteAllMovies();
                    }
                });
                list.clear();
                adapter.notifyDataSetChanged();
                break;
        }
        return false;
    }

    private void deleteOneMovie() {
        Executors.newFixedThreadPool(1).execute(new Runnable() {
            @Override
            public void run() {
                MoviesDatabase.getInstance(FavDetailsActivity.this).moviesDao().deleteDuplicate();
                list = MoviesDatabase.getInstance(FavDetailsActivity.this).moviesDao().getMovies();
                adapter = new Adapter(list, FavDetailsActivity.this);

                //-----------------------------------------//
                adapter.Delete(new Adapter.DeleteMovie() {
                    @Override
                    public void movieDeleted(int position) {

                    }
                });
                binding.favrecycler.setAdapter(adapter);
            }
        });

    }

    private void deleteMovieOnSwipe() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Executors.newFixedThreadPool(1).execute(new Runnable() {
                    @Override
                    public void run() {
                        MoviesDatabase.getInstance(FavDetailsActivity.this).moviesDao().deleteMovie(list.get(viewHolder.getAdapterPosition()));
                        adapter.deletePosition(viewHolder.getAdapterPosition());
                    }
                });
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.favrecycler);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
