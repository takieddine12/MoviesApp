package taki.eddine.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import taki.eddine.myapplication.MoviesDetails.MoviesDetails;
import taki.eddine.myapplication.databinding.FavlayoutBinding;
import taki.eddine.myapplication.datamodels.DataModel;
import taki.eddine.myapplication.movieslisteners.FavMealListener;
import timber.log.Timber;

public  class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {

    private List<DataModel> list;
    private Context context;
    private DeleteMovie deleteMovie;

    public interface DeleteMovie {
        void movieDeleted(int position);
    }

    public void Delete(DeleteMovie deleteMovie) {
        this.deleteMovie = deleteMovie;
    }

    public Adapter(List<DataModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavlayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.favlayout,parent,false);
       return new viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        DataModel dBdata = list.get(position);
        holder.binding.setModel(dBdata);
        holder.binding.setListener(new FavMealListener() {
            @Override
            public void SendFavID(DataModel dBdata) {
                Intent intent = new Intent(context, MoviesDetails.class);
                intent.putExtra("movieid",dBdata.getMoviesid());
                context.startActivity(intent);
                Timber.d("Movie id " + dBdata.getUrl());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private FavlayoutBinding binding;
        public viewholder(FavlayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void deletePosition(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void deleteAll(List<DataModel> dBdata)
    {
        list.remove(dBdata);
    }
}
