/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package pl.pisquared.tvgallery;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

import jp.wasabeef.blurry.Blurry;

/*
 * LeanbackDetailsFragment extends DetailsFragment, a Wrapper fragment for leanback details screens.
 * It shows a detailed view of video and its meta plus related videos.
 */
public class ImageFragment extends Fragment
{
    private static final String TAG = "ImageFragment";
    private PicassoImageContainerTarget picassoImageContainerTarget;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.details_fragmet, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        String url = args.getString(Constants.EXTRA_URL_KEY);
        ImageView imageContainer = getView().findViewById(R.id.iv_image_container);
        ImageView imageBackground = getView().findViewById(R.id.iv_image_background);
        picassoImageContainerTarget = new PicassoImageContainerTarget(imageBackground, imageContainer, getActivity());
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.loading_large)
                .error(R.drawable.loading_error_large)
                .into(picassoImageContainerTarget);

    }

    private static class PicassoImageContainerTarget implements Target
    {
        private ImageView imageBackground;
        private ImageView imageContainer;
        private Context context;

        private PicassoImageContainerTarget(ImageView imageBackground, ImageView imageContainer, Context context)
        {
            this.imageBackground = imageBackground;
            this.imageContainer = imageContainer;
            this.context = context;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from)
        {
            Blurry.with(context).from(bitmap).into(imageBackground);
            imageContainer.setImageBitmap(bitmap);
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable)
        {
            imageBackground.setImageResource(R.drawable.default_background);
            imageContainer.setImageDrawable(errorDrawable);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable)
        {
            imageBackground.setImageResource(R.drawable.default_background);
            imageContainer.setImageDrawable(placeHolderDrawable);
        }
    }
}
