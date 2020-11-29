package taki.eddine.myapplication.RecyclerviewUi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import taki.eddine.myapplication.movieslisteners.MovieListener;
import taki.eddine.myapplication.MoviesDetails.MoviesDetails;
import taki.eddine.myapplication.databinding.MovieslayoutBinding;
import taki.eddine.myapplication.datamodels.MoviesDataModel;
import taki.eddine.myapplication.R;

public class UpcomingMoviesAdapter extends PagedListAdapter<MoviesDataModel, UpcomingMoviesAdapter.viewholder> {

    private Context context;
    List<MoviesDataModel> list;


    private TopRatedMoviesAdapter.OnMovieDetails onMovieDetails;
    public interface OnMovieDetails
    {
        void data(int position);
    }
    public void getDetails(TopRatedMoviesAdapter.OnMovieDetails onMovieDetails)
    {
        this.onMovieDetails = onMovieDetails;
    }


    public UpcomingMoviesAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    public void setData(List<MoviesDataModel> list)
    {
        this.list = list;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieslayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.movieslayout,parent,false);
        return new viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        MoviesDataModel moviesModel = getItem(position);
        holder.binding.setModel(moviesModel);
        holder.binding.setListener(new MovieListener() {
            @Override
            public void SendMovieID(MoviesDataModel moviesDataClass) {
                Intent intent = new Intent(context, MoviesDetails.class);
                intent.putExtra("movieid",moviesDataClass.getMovie_id());
                context.startActivity(intent);
            }
        });
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private MovieslayoutBinding binding;
        public viewholder(MovieslayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Nullable
    @Override
    protected MoviesDataModel getItem(int position) {
        return super.getItem(position);
    }

    public static DiffUtil.ItemCallback<MoviesDataModel> diffCallback = new DiffUtil.ItemCallback<MoviesDataModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull MoviesDataModel oldItem, @NonNull MoviesDataModel newItem) {
            return oldItem.getMovie_id() == newItem.getMovie_id();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull MoviesDataModel oldItem, @NonNull MoviesDataModel newItem) {
            return oldItem.equals(newItem);
        }
    };


}
