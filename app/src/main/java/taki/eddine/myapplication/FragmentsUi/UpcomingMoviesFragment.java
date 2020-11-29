package taki.eddine.myapplication.FragmentsUi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;

import taki.eddine.myapplication.MainActivity;
import taki.eddine.myapplication.MoviesDetails.FavDetailsActivity;
import taki.eddine.myapplication.PaginationUi.PaginationClass.filterpagination.FilterViewModel;
import taki.eddine.myapplication.datamodels.MoviesDataModel;
import taki.eddine.myapplication.MoviesDetails.MoviesDetails;
import taki.eddine.myapplication.PaginationUi.PaginationClass.upcomingmoviessource.UpcomingViewModel;
import taki.eddine.myapplication.R;
import taki.eddine.myapplication.RecyclerviewUi.TopRatedMoviesAdapter;
import taki.eddine.myapplication.RecyclerviewUi.UpcomingMoviesAdapter;
import taki.eddine.myapplication.databinding.UpcomingmovieslayoutBinding;

public class UpcomingMoviesFragment extends Fragment {

    private UpcomingViewModel upcomingViewModel;
    private UpcomingMoviesAdapter upcomingMoviesAdapter;
    private FilterViewModel filterViewModel;
    private UpcomingmovieslayoutBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater,R.layout.upcomingmovieslayout,container,false);

       binding.upcomingmoviesrecycler.setLayoutManager(new GridLayoutManager(requireContext(),2));
       binding.upcomingmoviesrecycler.setHasFixedSize(true);


        upcomingMoviesAdapter = new UpcomingMoviesAdapter(getContext());
        upcomingViewModel = new ViewModelProvider(this).get(UpcomingViewModel.class);
        filterViewModel = new ViewModelProvider(this).get(FilterViewModel.class);

        //Observing changes on pagedlist
        upcomingViewModel.liveData.observe(getViewLifecycleOwner(), new Observer<PagedList<MoviesDataModel>>() {
            @Override
            public void onChanged(PagedList<MoviesDataModel> moviesDataClasses) {
                upcomingMoviesAdapter.submitList(moviesDataClasses);
                binding.upcomingmoviesrecycler.setAdapter(upcomingMoviesAdapter);
                upcomingMoviesAdapter.getDetails(new TopRatedMoviesAdapter.OnMovieDetails() {
                    @Override
                    public void data(int position) {
                        Intent intent = new Intent(getContext(), MoviesDetails.class);
                        intent.putExtra("movieid", moviesDataClasses.get(position).getMovie_id());
                        startActivity(intent);
                    }
                });
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.favmenu,menu);
        MenuItem menuItem  = menu.findItem(R.id.filtermovie);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterViewModel.filterTextAll.setValue(query.toLowerCase());
                filterViewModel.getData().observe(getViewLifecycleOwner(), new Observer<PagedList<MoviesDataModel>>() {
                    @Override
                    public void onChanged(PagedList<MoviesDataModel> moviesDataClasses) {
                        upcomingMoviesAdapter.submitList(moviesDataClasses);
                        binding.upcomingmoviesrecycler.setAdapter(upcomingMoviesAdapter);
                        upcomingMoviesAdapter.getDetails(new TopRatedMoviesAdapter.OnMovieDetails() {
                            @Override
                            public void data(int position) {
                                Intent intent = new Intent(getContext(), MoviesDetails.class);
                                intent.putExtra("movieid", moviesDataClasses.get(position).getMovie_id());
                                startActivity(intent);
                            }
                        });
                    }
                });

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.favsection:
                startActivity(new Intent(requireContext(), FavDetailsActivity.class));
                break;
            case R.id.refreshdata:
                Intent intent = new Intent(requireContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                Toast.makeText(requireContext(),"Successfully Updated..",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

}
