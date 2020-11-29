package taki.eddine.myapplication.imgsBindings;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class DetailsImg  {

    @BindingAdapter("DetailsImg")
    public static void SetDetailsImg(ImageView view,String Url){
        Picasso.with(view.getContext()).load("https://image.tmdb.org/t/p/w185//" + Url).fit().into(view);
    }
}
