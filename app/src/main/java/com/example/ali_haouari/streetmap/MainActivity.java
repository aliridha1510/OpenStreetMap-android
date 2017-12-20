package com.example.ali_haouari.streetmap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Context ctx = getApplicationContext();
    //important! set your user agent to prevent getting banned from the osm servers
    Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
    setContentView(R.layout.activity_main);

    MapView map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(9);
        GeoPoint startPoint = new GeoPoint(52.410868, 12.535764);
        mapController.setCenter(startPoint);
}

    public void onResume(){
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration.getInstance().save(this, prefs);
      Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

    }
}
