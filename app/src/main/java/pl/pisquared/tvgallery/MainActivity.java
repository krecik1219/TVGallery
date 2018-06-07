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

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.util.Log;

import com.squareup.picasso.Picasso;



/*
 * MainActivity class that loads {@link MainFragment}.
 */
public class MainActivity extends Activity implements OnItemViewClickedListener, OnItemViewSelectedListener
{
    private static final String TAG = "MainActivity";

    private Drawable defaultBackground;
    private BackgroundManager backgroundManager;
    private PicassoBackgroundManagerTarget picassoBackgroundManagerTarget;
    private String currentlySelectedURL;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "On create");
        defaultBackground = getDrawable(R.drawable.default_background);
        backgroundManager = BackgroundManager.getInstance(this);
        backgroundManager.attach(getWindow());
        backgroundManager.setDrawable(defaultBackground);
        picassoBackgroundManagerTarget = new PicassoBackgroundManagerTarget(backgroundManager, this);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, new MainFragment());
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG, "on resume");
        Picasso.get()
                .load(currentlySelectedURL)
                .error(defaultBackground)
                .placeholder(defaultBackground)
                .into(picassoBackgroundManagerTarget);
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row)
    {
        if(item instanceof ImageObject)
        {
            ImageObject image = (ImageObject) item;
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(Constants.EXTRA_URL_KEY, image.getUrl());
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row)
    {
        if(item instanceof ImageObject)
        {
            ImageObject image = (ImageObject) item;
            currentlySelectedURL = image.getUrl();
            Picasso.get()
                    .load(image.getUrl())
                    .error(defaultBackground)
                    .placeholder(defaultBackground)
                    .into(picassoBackgroundManagerTarget);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "On save instance state, url: "+currentlySelectedURL);
        outState.putString(Constants.BACKGROUND_IMAGE_URL_KEY, currentlySelectedURL);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        String url = savedInstanceState.getString(Constants.BACKGROUND_IMAGE_URL_KEY);
        Log.d(TAG, "On restore instance state, url: "+url);
        currentlySelectedURL = url;
        Picasso.get()
                .load(url)
                .error(defaultBackground)
                .placeholder(defaultBackground)
                .into(picassoBackgroundManagerTarget);
    }
}
