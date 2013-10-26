package com.kodujdlapolski.openjajo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kodujdlapolski.openjajo.adapters.AdapterItem;
import com.kodujdlapolski.openjajo.adapters.SimpleAdapter;
import com.kodujdlapolski.openjajo.sejmometr.GminaSet;
import com.kodujdlapolski.openjajo.sejmometr.gmina.DetailedGminaData;
import com.kodujdlapolski.openjajo.sejmometr.voivodeship.DetailedVoiovodeshipData;

public class MainActivity extends Activity {

	private GoogleMap map;
	private LocationListener locationListener;
	LocationManager locationManager;

	private int volodoshipId = 0;
	private int gminaId = 0;
	private SimpleAdapter adapter;
	ListView lv;

	private AdapterItem[] itemArray;
	private ArrayList<AdapterItem> arrayListAdapterItem = new ArrayList<AdapterItem>();

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (locationManager != null && locationListener != null)
			locationManager.removeUpdates(locationListener);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setLocationListening();

		if (savedInstanceState == null) {
			setContentView(R.layout.splash_screen);
			getActionBar().hide();
			Handler h = new Handler();
			h.postDelayed(new Runnable() {
				@Override
				public void run() {
					showMappingUI();

				}
			}, 2000);
		} else {
			showMappingUI();
		}

	}

	private void showMappingUI() {
		setContentView(R.layout.activity_main);
		getActionBar().show();
		// Get a handle to the Map Fragment
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		// objects = new AdapterItem[4];
		// objects[0] = new AdapterItem("Average Salary", "2973 pln");
		// objects[1] = new AdapterItem("Hackatron events", "1");
		// objects[2] = new AdapterItem("Drug Dealers", "200");
		// objects[3] = new AdapterItem("Hobos per street", "7");
		// adapter = new SimpleAdapter(this, R.layout.data_row, null);
		//
		lv = (ListView) findViewById(R.id.myList);
		// lv.setAdapter(adapter);

	}

	private void applyMapChange(Location location) {
		double lat, lon;
		lat = location.getLatitude();
		lon = location.getLongitude();
		LatLng me = new LatLng(lat, lon);
		if (map != null) {
			arrayListAdapterItem.clear();
			new GminaAsyncTask().execute(location);
			new EggAsyncTask().execute(location);
			new SalaryAsyncTask().execute(location);
			new FuelAsyncTask().execute(location);
			new UnemploymentAsyncTask().execute(location);
			map.setMyLocationEnabled(true);
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(me, 13));

			map.addMarker(new MarkerOptions().title("You")
					.snippet("Eat the eggs, they are so cheap").position(me));
		}
	}

	private void setLocationListening() {
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location
				// provider.
				applyMapChange(location);
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};
		// Register the listener with the Location Manager to receive location
		// updates
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 60000, 100, locationListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * ASYNC TASKS
	 * 
	 * @author u238
	 * 
	 */

	private class GminaAsyncTask extends AsyncTask<Location, Void, String> {

		@Override
		protected String doInBackground(Location... params) {
			RestClient client = new RestClient("http://sejmometr.pl/");
			GminaSet localization = client.getLocalization(
					String.valueOf(params[0].getLatitude()),
					String.valueOf(params[0].getLongitude()));
			// GminaSet localization =
			// client.getLocalization("52.2297","21.0126",
			// String.valueOf(params[0].getLongitude()));

			volodoshipId = localization.getGminy()[0].getWojewodzwtwoId();
			gminaId = localization.getGminy()[0].getGminaId();

			return localization.getGminy()[0].getGminaNazwa();
		}

		@Override
		protected void onPostExecute(String result) {

			((TextView) findViewById(R.id.gminaName)).setText(result);
			findViewById(R.id.gminaName).setVisibility(View.VISIBLE);
			findViewById(R.id.gminaLoader).setVisibility(View.GONE);

			super.onPostExecute(result);
		}

	}

	private class EggAsyncTask extends AsyncTask<Location, Void, String> {

		@Override
		protected String doInBackground(Location... params) {
			Client client = new RestClient("http://sejmometr.pl/");
			DetailedVoiovodeshipData[] indicator = client
					.getVoivodeshipIndicator(3, 61, 6);
			return indicator[0].getValue().toString();
		}

		@Override
		protected void onPostExecute(String result) {
			((TextView) findViewById(R.id.eggsCounter))
					.setText(result + "/pln");
			findViewById(R.id.eggsCounter).setVisibility(View.VISIBLE);
			findViewById(R.id.eggsLoader).setVisibility(View.GONE);
			super.onPostExecute(result);
		}

	}

	private class SalaryAsyncTask extends AsyncTask<Location, Void, String> {

		@Override
		protected String doInBackground(Location... params) {
			Client client = new RestClient("http://sejmometr.pl/");
			gminaId = (gminaId != 0) ? gminaId : 903;
			DetailedGminaData[] indicator = client.getGminaIndicator(20, 288,
					gminaId);
			return indicator[0].getValue().toString() + "pln";
		}

		@Override
		protected void onPostExecute(String result) {
			arrayListAdapterItem.add(new AdapterItem("Avarege Salary", result));
			itemArray = new AdapterItem[arrayListAdapterItem.size()];
			itemArray = arrayListAdapterItem.toArray(itemArray);
			adapter = new SimpleAdapter(MainActivity.this, R.layout.data_row,
					itemArray);
			lv.setAdapter(adapter);
			super.onPostExecute(result);
		}

	}

	private class FuelAsyncTask extends AsyncTask<Location, Void, String> {

		@Override
		protected String doInBackground(Location... params) {
			Client client = new RestClient("http://sejmometr.pl/");
			gminaId = (gminaId != 0) ? gminaId : 903;
			DetailedGminaData[] indicator = client.getGminaIndicator(171, 1820,
					gminaId);
			return indicator[0].getValue().intValue() + "";
		}

		@Override
		protected void onPostExecute(String result) {
			arrayListAdapterItem.add(new AdapterItem("Gas Stations", result));
			itemArray = new AdapterItem[arrayListAdapterItem.size()];
			itemArray = arrayListAdapterItem.toArray(itemArray);
			adapter = new SimpleAdapter(MainActivity.this, R.layout.data_row,
					itemArray);
			lv.setAdapter(adapter);
			super.onPostExecute(result);
		}

	}

	private class UnemploymentAsyncTask extends
			AsyncTask<Location, Void, String> {

		@Override
		protected String doInBackground(Location... params) {
			Client client = new RestClient("http://sejmometr.pl/");
			gminaId = (gminaId != 0) ? gminaId : 903;
			DetailedGminaData[] indicator = client.getGminaIndicator(779,
					13932, gminaId);
			return indicator[0].getValue().toString() + "%";
		}

		@Override
		protected void onPostExecute(String result) {
			arrayListAdapterItem.add(new AdapterItem("Unemplyment", result));
			itemArray = new AdapterItem[arrayListAdapterItem.size()];
			itemArray = arrayListAdapterItem.toArray(itemArray);
			adapter = new SimpleAdapter(MainActivity.this, R.layout.data_row,
					itemArray);
			lv.setAdapter(adapter);
			super.onPostExecute(result);
		}

	}

}
