package taki.eddine.myapplication.RecyclerviewUi;


///for pagination we need PagedlistAdapter

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

import taki.eddine.myapplication.movieslisteners.MovieListener;
import taki.eddine.myapplication.MoviesDetails.MoviesDetails;
import taki.eddine.myapplication.databinding.MovieslayoutBinding;
import taki.eddine.myapplication.datamodels.MoviesDataModel;
import taki.eddine.myapplication.R;

public class PopularMoviesAdapter extends PagedListAdapter<MoviesDataModel, PopularMoviesAdapter.viewholder> {

    private Context mCtx;
    private TopRatedMoviesAdapter.OnMovieDetails onMovieDetails;

    public interface OnMovieDetails {
        void data(int position);
    }
    public void getDetails(TopRatedMoviesAdapter.OnMovieDetails onMovieDetails) {
        this.onMovieDetails = onMovieDetails;
    }

    public PopularMoviesAdapter(Context mCtx) {
        super(callback);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieslayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mCtx),
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
             Intent intent = new Intent(mCtx, MoviesDetails.class);
             intent.putExtra("movieid",moviesDataClass.getMovie_id());
             mCtx.startActivity(intent);
         }
     });

    }

    public static DiffUtil.ItemCallback<MoviesDataModel> callback = new DiffUtil.ItemCallback<MoviesDataModel>() {
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

    @Nullable
    @Override
    protected MoviesDataModel getItem(int position) {
        return super.getItem(position);
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private MovieslayoutBinding binding;
        public viewholder(MovieslayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
