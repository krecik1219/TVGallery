package pl.pisquared.tvgallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.app.BackgroundManager;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import jp.wasabeef.blurry.internal.Blur;
import jp.wasabeef.blurry.internal.BlurFactor;

public class PicassoBackgroundManagerTarget implements Target
{
    private BackgroundManager backgroundManager;
    private Context context;

    public PicassoBackgroundManagerTarget(BackgroundManager backgroundManager, Context context)
    {
        this.backgroundManager = backgroundManager;
        this.context = context;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from)
    {
        BlurFactor factor = new BlurFactor();
        factor.width = bitmap.getWidth();
        factor.height = bitmap.getHeight();
        Bitmap blurred = Blur.of(context, bitmap, factor);
        backgroundManager.setBitmap(blurred);
    }

    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable)
    {
        backgroundManager.setDrawable(errorDrawable);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable)
    {
        backgroundManager.setDrawable(placeHolderDrawable);
    }
}
