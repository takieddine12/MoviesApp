package taki.eddine.myapplication.imgsBindings;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

import kotlin.jvm.JvmStatic;

public class playNowImgBinding {

    @BindingAdapter("PlayNowImg")
    @JvmStatic
     public static void setPlayNowMovieImage(ImageView view,String url){
        Picasso.with(view.getContext()).load("https://image.tmdb.org/t/p/w185//"+ url).fit().into(view);
    }
}
