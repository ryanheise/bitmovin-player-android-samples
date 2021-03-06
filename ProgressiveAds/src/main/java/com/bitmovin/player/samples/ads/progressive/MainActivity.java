package com.bitmovin.player.samples.ads.progressive;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.bitmovin.player.BitmovinPlayerView;
import com.bitmovin.player.config.PlayerConfiguration;
import com.bitmovin.player.config.advertising.AdItem;
import com.bitmovin.player.config.advertising.AdSource;
import com.bitmovin.player.config.advertising.AdSourceType;
import com.bitmovin.player.config.advertising.AdvertisingConfiguration;
import com.bitmovin.player.config.media.SourceItem;

public class MainActivity extends AppCompatActivity
{
    private static final String AD_SOURCE_1 = "https://bitmovin-a.akamaihd.net/content/testing/ads/testad2s.mp4";
    private static final String AD_SOURCE_2 = "file:///android_asset/testad2s.mp4";

    private BitmovinPlayerView bitmovinPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create AdSources
        AdSource firstAdSource = new AdSource(AdSourceType.PROGRESSIVE, AD_SOURCE_1);
        AdSource secondAdSource = new AdSource(AdSourceType.PROGRESSIVE, AD_SOURCE_2);

        // Setup a pre-roll ad
        AdItem preRoll = new AdItem("pre", firstAdSource);
        AdItem midRoll = new AdItem("mid", secondAdSource);

        // Add the AdItems to the AdvertisingConfiguration
        AdvertisingConfiguration advertisingConfiguration = new AdvertisingConfiguration(preRoll, midRoll);

        // Creating a new PlayerConfiguration
        PlayerConfiguration playerConfiguration = new PlayerConfiguration();
        // Assign the AdvertisingConfiguration to the PlayerConfiguration
        // All ads in the AdvertisingConfiguration will be scheduled automatically
        playerConfiguration.setAdvertisingConfiguration(advertisingConfiguration);

        // Create new BitmovinPlayerView with our PlayerConfiguration
        this.bitmovinPlayerView = new BitmovinPlayerView(this, playerConfiguration);
        this.bitmovinPlayerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        // Load the SourceItem
        this.bitmovinPlayerView.getPlayer().load(new SourceItem("https://bitmovin-a.akamaihd.net/content/sintel/sintel.mpd"));

        LinearLayout rootView = (LinearLayout) this.findViewById(R.id.activity_main);

        // Add BitmovinPlayerView to the layout
        rootView.addView(this.bitmovinPlayerView, 0);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        this.bitmovinPlayerView.onStart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        this.bitmovinPlayerView.onResume();
    }

    @Override
    protected void onPause()
    {
        this.bitmovinPlayerView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        this.bitmovinPlayerView.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        this.bitmovinPlayerView.onDestroy();
        super.onDestroy();
    }
}
