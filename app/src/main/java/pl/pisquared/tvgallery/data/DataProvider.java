package pl.pisquared.tvgallery.data;

import java.util.ArrayList;
import java.util.List;

import pl.pisquared.tvgallery.ImageRow;

public class DataProvider
{
    private static final int HEADERS_NUM = 5;
    public static final String [] HEADERS_TITLES = {"Berlin", "London", "New York", "Los Angeles", "Paris"};
    public static final String [] BERLIN_URLS = {
            "http://www.sindbadslask.pl/wp-content/uploads/2015/12/Wycieczka-Berlin-1.jpg",
            "https://www.tapeciarnia.pl/tapety/normalne/182293_katedra_berlin.jpg",
            "https://www.tapetus.pl/obrazki/n/248853_noc-berlin-niemcy.jpg",
            "https://awscloudfront.kempinski.com/1111/bild_exterior-2.jpg",
            "http://mmk.travel.pl/mmk2017/wp-content/uploads/2017/12/MMK-Travel-Gliwice-Berlin-wycieczka-autokarowem-do-Berlina-i-Tropikalna-wyspa.jpg"
    };
    public static final String [] LONDON_URLS = {
            "https://london.newplacestobe.com/wp-content/uploads/sites/5/2017/02/london.jpg",
            "http://www.royalfashionist.com/wp-content/uploads/2017/09/london.jpg",
            "https://a19c53f45b440f049d21-3f2703e71e5f5fed646a243f2d21abcb.ssl.cf3.rackcdn.com/x/1920cm1080/images/inner_pages/london-at-night-01.jpg",
            "https://i.ytimg.com/vi/Mzu3Je59wwo/maxresdefault.jpg",
            "https://timandjulieharris.com/wp-content/uploads/2018/03/london-home-prices-drop-img-0322218-1.jpg"
    };
    public static final String [] NEW_YORK_URLS = {
            "https://objects.airfrance.com/FR/common/common/img/tbaf/destinations/NYC/slideshow/NYC-4-16_9-1920x1080.jpg",
            "http://media.equityapartments.com/images/c_crop,x_0,y_0,w_1920,h_1080/c_fill,w_1920,h_1080/q_80/4039-96/beatrice-apartments.jpg",
            "http://www.hdnicewallpapers.com/Walls/Big/Wonders/Statue_of_Liberty_Wonders_in_New_York_City_US_HD_Wallpapers.jpg",
            "https://www.bucketlist127.com/uploads/images/50345ef186bf69f87d6cf3bb2f716ddc.jpg",
            "https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/WUS5VgH/new-york-city-manhattan-nyc-east-river-daytime_n1kyrram__F0000.png"
    };
    public static final String [] LOS_ANGELES_URLS = {
            "https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/acZhNbr/videoblocks-downtown-los-angeles-1st-street-bridge-and-gold-line-night-timelapse_rnritrsqg_thumbnail-full01.png",
            "https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/GbbtDTW/pan-across-downtown-los-angeles-skyline-through-row-of-palm-trees-aerial-view_nnsjnlrce__F0005.png",
            "http://getwallpapers.com/wallpaper/full/a/3/6/539403.jpg",
            "https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/HKxta86in0ls1we/videoblocks-griffith-observatory-and-downtown-los-angeles-day-to-night-transition-timelapse_hsjbqawnl_thumbnail-full01.png",
            "https://wallpaperstudio10.com/static/wpdb/wallpapers/1920x1080/168041.jpg"
    };
    public static final String [] PARIS_URLS = {
            "https://objects.airfrance.com/FR/common/common/img/tbaf/destinations/PAR/slideshow/PAR-1-16_9-1920x1080.jpg",
            "https://www.mypremiumeurope.com/img/image_db/romance_paris_special_offers_main-1920.jpg",
            "https://archaeology-travel.com/wp-content/uploads/2013/04/paris-archaeology-guide.jpg",
            "http://miriadna.com/desctopwalls/images/max/Les-Champs-(Paris).jpg",
            "https://theculturetrip.com/wp-content/uploads/2017/04/notre-dame-de-paris--nkoks-pixabay.jpg"
    };

    private static DataProvider instance = null;

    private List<ImageRow> imageRowList;

    public static DataProvider getInstance()
    {
        if(instance == null)
            instance = new DataProvider();
        return instance;
    }

    private DataProvider()
    {
        imageRowList = new ArrayList<>(HEADERS_NUM);
        ImageRow imageRow = null;
        for(int i = 0; i < HEADERS_NUM; ++i)
        {
            imageRow = new ImageRow(i + 1, HEADERS_TITLES[i]);
            imageRowList.add(imageRow);
        }
    }

    public List<ImageRow> getImageRowList()
    {
        return imageRowList;
    }
}
