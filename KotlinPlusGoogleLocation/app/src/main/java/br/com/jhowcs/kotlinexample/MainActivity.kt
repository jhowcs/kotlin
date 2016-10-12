package br.com.jhowcs.kotlinexample

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    val TAG: String = "MainActivity"

    var mGoogleApiClient : GoogleApiClient? = null
    var mLastLocation : Location? = null

    var txtLatitude : TextView? = null
    var txtLongitude: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtLatitude = findViewById(R.id.txtLatitude) as TextView
        txtLongitude= findViewById(R.id.txtLongitude) as TextView

        buildGoogleApiClient()
    }

    fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build()
    }

    override fun onStart() {
        super.onStart()

        mGoogleApiClient?.connect()

    }

    override fun onStop() {
        mGoogleApiClient?.disconnect()

        super.onStop()
    }

    override fun onConnected(bundle: Bundle?) {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        showLastLocation(mLastLocation);
    }

    fun showLastLocation(mLastLocation: Location?) {
        val latitude : Double? = mLastLocation?.latitude
        val longitude: Double? = mLastLocation?.longitude

        //Toast.makeText(this, "Latitude: $latitude Longitude: $longitude", Toast.LENGTH_SHORT).show()
        txtLatitude?.setText(latitude.toString())
        txtLongitude?.setText(longitude.toString())
    }

    override fun onConnectionSuspended(p0: Int) {
        Log.i(TAG, "Connection Suspended");
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.i(TAG, "Connection Failed");
    }

    fun String.aspas() : String {
       return "'" + this.toString() + "'"
    }
}
