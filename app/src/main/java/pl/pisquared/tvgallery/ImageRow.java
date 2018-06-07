package pl.pisquared.tvgallery;

import android.support.v17.leanback.widget.ArrayObjectAdapter;

public class ImageRow
{
    private long id;
    private String title;
    private ArrayObjectAdapter adapter;

    public ImageRow(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public long getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public ArrayObjectAdapter getAdapter()
    {
        return adapter;
    }

    public void setAdapter(ArrayObjectAdapter adapter)
    {
        this.adapter = adapter;
    }
}
