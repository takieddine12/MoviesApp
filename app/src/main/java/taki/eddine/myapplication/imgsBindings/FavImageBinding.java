package taki.eddine.myapplication.imgsBindings;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

import kotlin.jvm.JvmStatic;

public class FavImageBinding {

    @BindingAdapter("FavImg")
    public static void setFavImage(ImageView view , String url){
        Picasso.with(view.getContext()).load(url).fit().into(view);
    }
}
