/*
 * Copyright (C) 2017 The Android Open Source Project
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


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v4.content.ContextCompat;

import java.util.Iterator;
import java.util.List;

import pl.pisquared.tvgallery.data.DataProvider;


public class MainFragment extends BrowseFragment
{
    private static final String TAG = "MainFragment";

    private OnItemViewClickedListener onItemViewClickedListener;
    private OnItemViewSelectedListener onItemViewSelectedListener;
    private List<ImageRow> imageRows;  // TODO load that somewhre

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            onItemViewClickedListener = (OnItemViewClickedListener) context;
            onItemViewSelectedListener = (OnItemViewSelectedListener) context;
        }catch(ClassCastException e)
        {
            throw new ClassCastException("Parent activity must implement OnItemViewClickedListener and OnItemViewSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setBrandColor(ContextCompat.getColor(getActivity(), R.color.headers_background));
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
        setBadgeDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.pisquared));

        createDataRows();
        createRows();
        setOnItemViewClickedListener(onItemViewClickedListener);
        setOnItemViewSelectedListener(onItemViewSelectedListener);
        //prepareEntranceTransition();
        fetchImages();
    }

    private void createDataRows()
    {
        ImagePresenter presenter = new ImagePresenter();
        DataProvider provider = DataProvider.getInstance();
        imageRows = provider.getImageRowList();
        for(Iterator<ImageRow> it = imageRows.iterator(); it.hasNext() ; )
        {
            it.next().setAdapter(new ArrayObjectAdapter(presenter));
        }
    }

    private void createRows()
    {
        ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        ImageRow imageRow = null;
        HeaderItem headerItem = null;
        ListRow listRow = null;
        for(Iterator<ImageRow> it = imageRows.iterator(); it.hasNext() ; )
        {
            imageRow = it.next();
            headerItem = new HeaderItem(imageRow.getId(), imageRow.getTitle());
            listRow = new ListRow(headerItem, imageRow.getAdapter());
            rowsAdapter.add(listRow);
        }
        setAdapter(rowsAdapter);
    }

    private void fetchImages()
    {
        // TODO
        ArrayObjectAdapter adapter = null;
        int counter = 1;
        for(Iterator<ImageRow> it = imageRows.iterator(); it.hasNext() ; )
        {
            adapter = it.next().getAdapter();
            switch(counter)
            {
                case 1:
                    fetchBerlin(adapter);
                    break;
                case 2:
                    fetchLondon(adapter);
                    break;
                case 3:
                    fetchNewYork(adapter);
                    break;
                case 4:
                    fetchLosAngeles(adapter);
                    break;
                case 5:
                    fetchParis(adapter);
                    break;
            }
            counter++;
        }
    }

    private void fetchBerlin(ArrayObjectAdapter adapter)
    {
        for(String url : DataProvider.BERLIN_URLS)
        {
            adapter.add(new ImageObject(url));
        }
    }

    private void fetchLondon(ArrayObjectAdapter adapter)
    {
        for(String url : DataProvider.LONDON_URLS)
        {
            adapter.add(new ImageObject(url));
        }
    }

    private void fetchNewYork(ArrayObjectAdapter adapter)
    {
        for(String url : DataProvider.NEW_YORK_URLS)
        {
            adapter.add(new ImageObject(url));
        }
    }

    private void fetchLosAngeles(ArrayObjectAdapter adapter)
    {
        for(String url : DataProvider.LOS_ANGELES_URLS)
        {
            adapter.add(new ImageObject(url));
        }
    }

    private void fetchParis(ArrayObjectAdapter adapter)
    {
        for(String url : DataProvider.PARIS_URLS)
        {
            adapter.add(new ImageObject(url));
        }
    }

}
