package pl.pisquared.tvgallery;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PicassoImageCardViewTarget implements Target
{
    private ImageCardView imageCardView;

    public PicassoImageCardViewTarget(ImageCardView imageCardView)
    {
        this.imageCardView = imageCardView;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
        Drawable bitmapDrawable = new BitmapDrawable(imageCardView.getContext().getResources(), bitmap);
        imageCardView.setMainImage(bitmapDrawable);
    }

    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable)
    {
        imageCardView.setMainImage(errorDrawable);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable)
    {
        imageCardView.setMainImage(placeHolderDrawable);
    }
}
