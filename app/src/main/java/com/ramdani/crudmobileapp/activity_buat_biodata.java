package com.ramdani.crudmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

public class activity_buat_biodata extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;
    Button btnGetLocation;
    //TextView showLocation;
    LocationManager locationManager;
    String latitude, longitude;

protected Cursor cursor;
        DataHelper dbHelper;
        Button ton1, ton2;
        EditText text1, text2, text3, text4, text5;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_biodata);
        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        text5 = (EditText) findViewById(R.id.editText5);
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);
        ActivityCompat.requestPermissions( this,
        new String[]
        {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
// showLocation = findViewById(R.id.showLocation);
        btnGetLocation = findViewById(R.id.btnGetLocation);
        btnGetLocation.setOnClickListener(new
        View.OnClickListener() {
@Override
public void onClick(View v) {
        locationManager = (LocationManager)
        getSystemService(Context.LOCATION_SERVICE);
        if
        (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER
        )) {
        OnGPS();
        } else {
        getLocation();
        }
        }
        });
        ton1.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View arg0) {
// TODO Auto-generated method stub
        SQLiteDatabase db =
        dbHelper.getWritableDatabase();
        db.execSQL("insert into biodata(no, nama, nohp, jk, alamat) values('" +
        text1.getText().toString() + "','" +
        text2.getText().toString() + "','" +
        text3.getText().toString() + "','" +
        text4.getText().toString() + "','" +
        text5.getText().toString() + "')");
        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
        MainActivity.ma.RefreshList();
        finish();
        }
        });
        ton2.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View arg0) {
// TODO Auto-generated method stub
        finish();
        }
        });
        }
private void OnGPS() {
final AlertDialog.Builder builder = new
        AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new
        DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int
        which) {
        startActivity(new
        Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
        }).setNegativeButton("No", new
        DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int
        which) {
        dialog.cancel();
        }
        });
final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        }
private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
        this,Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION) !=
        PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, new
        String[]{Manifest.permission.ACCESS_FINE_LOCATION},
        REQUEST_LOCATION);
        } else {
        Location locationGPS =
        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (locationGPS != null) {
        double lat = locationGPS.getLatitude();
        double longi = locationGPS.getLongitude();
        latitude = String.valueOf(lat);
        longitude = String.valueOf(longi);
        text5.setText(latitude + " , " + longitude);
        } else {
        Toast.makeText(this, "Unable to find location.",
        Toast.LENGTH_SHORT).show();
        }
        }
        }
        }