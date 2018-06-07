package pl.pisquared.tvgallery;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

public class ImagePresenter extends Presenter
{
    private static final String TAG = "ImagePresenter";
    
    private static final int CARD_WIDTH = 400;
    private static final int CARD_HEIGHT = 225;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent)
    {
        ImageCardView cardView = new ImageCardView(parent.getContext());
        cardView.setCardType(BaseCardView.CARD_TYPE_MAIN_ONLY);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        return new ViewHolder(cardView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item)
    {
        ImageObject image = (ImageObject) item;
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
        holder.updateCardViewImage(image.getUrl());
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder)
    {

    }

    private static class ViewHolder extends Presenter.ViewHolder
    {
        private ImageCardView cardView;
        private Drawable defaultCardViewImage;
        private PicassoImageCardViewTarget imageCardViewTarget;


        public ViewHolder(View view, Context context)
        {
            super(view);
            cardView = (ImageCardView) view;
            imageCardViewTarget = new PicassoImageCardViewTarget(cardView);
            defaultCardViewImage = context.getDrawable(R.drawable.loading_error);
        }

        public ImageCardView getCardView()
        {
            return cardView;
        }

        private void updateCardViewImage(String url)
        {
            Log.d(TAG, "URL: " + url);
            Picasso instance = Picasso.get();
            instance.load(url)
                    .resize(CARD_WIDTH, CARD_HEIGHT)
                    .centerCrop()
                    .placeholder(R.drawable.loading)
                    .error(defaultCardViewImage)
                    .into(imageCardViewTarget);

        }
    }
}
