package com.company;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;

import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.company.hub.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.transition.ChangeBounds;
import androidx.transition.ChangeImageTransform;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;

public class FeedPostActivity extends AppCompatActivity {
    private static final String DEFAULT_LOCAL = "Select Type";
    private String[] array_truckType, array_truckCapacity, array_goodsType;
    //    public class PostLoadActivity extends Activity implements View.OnClickListener {
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 101;
    private int PLACE_AUTOCOMPLETE_REQUEST_DEST = 201;
    private String TAG = "CreateTrip";
    private ImageView truckPhotos;
    private Bitmap bp;
    private byte[] photo;
    private final int RESULT_LOAD_IMG = 2;
    private final int requestCode = 1;
    private boolean mColorsInverted = true;
    private boolean isColorsInverted = true;
    private boolean expandeding;
    private String goodsType, truckType, truckCapacity, goodsPhoto, fromAddress, toAddress, frightCharges, dLocationChecked, dChargesChecked;
    private CheckBox dLocation, dCharges;
    private EditText edtfrom_address, edtto_address, edtfright_charge;
    public static String sourceLatLong, destLatLong, sourceName, destName;
    private ProgressDialog progressDialog;

    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private Button btnSubmit;
    TextView tvProgressLabel;
    private int progress = 5;
    boolean doubleBackToExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_feed_activity);


        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final Spinner spTruckType = (Spinner) findViewById(R.id.foodType);
        array_truckType = getResources().getStringArray(R.array.ent_name);
        ArrayAdapter adapterTruckType = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_truckType);
        spTruckType.setAdapter(adapterTruckType);
        spTruckType.setSelection(adapterTruckType.getPosition(DEFAULT_LOCAL));

        spTruckType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                truckType = spTruckType.getSelectedItem().toString();
                if (truckType.equals("Select Any Music Type")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FeedPostActivity.this); // Change "this" to `getActivity()` if you're using this on a fragment
                    builder.setMessage("Contact Hubb Company")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {

                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                } else {
                    Log.i("Selected item : ", truckType);
                    Toast.makeText(FeedPostActivity.this, truckType, Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        final Spinner spTruckCapacity = (Spinner) findViewById(R.id.restName);
        array_truckCapacity = getResources().getStringArray(R.array.music_type);
        ArrayAdapter adapterTruckCapacity = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_truckCapacity);
        spTruckCapacity.setAdapter(adapterTruckCapacity);
        spTruckCapacity.setSelection(adapterTruckCapacity.getPosition(DEFAULT_LOCAL));

        spTruckCapacity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                truckCapacity = spTruckCapacity.getSelectedItem().toString();

                if (truckCapacity.equals("Select Entertainment Name")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FeedPostActivity.this); // Change "this" to `getActivity()` if you're using this on a fragment
                    builder.setMessage("Contact Hubb company")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {

                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                } else {
                    Log.i("Selected item : ", truckCapacity);
                    Toast.makeText(FeedPostActivity.this, truckCapacity, Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(FeedPostActivity.this, new String[]{Manifest.permission.CAMERA}, requestCode);

        if (Build.VERSION.SDK_INT >= 23) {
// Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(FeedPostActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(FeedPostActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(FeedPostActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            requestCode);


                    // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
        }


        truckPhotos = (ImageView) findViewById(R.id.goods_image);
        truckPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout transitionsContainer = (LinearLayout) findViewById(R.id.trnasView);
                TransitionManager.beginDelayedTransition(transitionsContainer, new TransitionSet()
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeImageTransform()));

//                ViewGroup.LayoutParams params = truckPhotos.getLayoutParams();
//                params.height = expandeding ? ViewGroup.LayoutParams.MATCH_PARENT :
//                        ViewGroup.LayoutParams.WRAP_CONTENT;
//                truckPhotos.setLayoutParams(params);
//
//                truckPhotos.setScaleType(expandeding ? ImageView.ScaleType.CENTER_CROP :
//                        ImageView.ScaleType.FIT_CENTER);
                AlertDialog.Builder builder = new AlertDialog.Builder(FeedPostActivity.this);
                builder.setTitle("Publish Event");
                builder.setMessage("select Event pictures from below method");
                builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(photoCaptureIntent, requestCode);
                    }
                });

                builder.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

                    }
                });
                builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });

                AlertDialog diag = builder.create();
                diag.show();
            }
        });






            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
            Menu menu = bottomNavigationView.getMenu();
            MenuItem menuItem = menu.getItem(2);
            menuItem.setChecked(true);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.home:
                            Intent home = new Intent(FeedPostActivity.this, BottomMainActivity.class);
                            startActivity(home);
                            finish();
                            break;

                        case R.id.foody:
//
                            Intent intent2 = new Intent(FeedPostActivity.this, EventsActivity.class);
                            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent2);

                            break;

                        case R.id.review:

                            Intent notif = new Intent(FeedPostActivity.this, FeedPostActivity.class);
                            notif.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(notif);

                            break;

                        case R.id.search:

                            Intent intent1 = new Intent(FeedPostActivity.this, MainActivity.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent1);

                            break;

                        case R.id.profile:
                            Intent intent4 = new Intent(FeedPostActivity.this, BottomProfileActivity.class);
                            intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent4);

                            break;
                    }
                    return true;
                }
            });

        }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExit) {
            Intent setIntent = new Intent(Intent.ACTION_MAIN);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);

        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            doubleBackToExit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExit = false;
                }
            }, 3 * 1000);
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (this.requestCode == requestCode && resultCode == RESULT_OK) {
            bp = (Bitmap) data.getExtras().get("data");
            truckPhotos.setImageBitmap(bp);
        }

        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {

            Uri choosenImage = data.getData();

            if (choosenImage != null) {

                bp = decodeUri(choosenImage, 400);
                truckPhotos.setImageBitmap(bp);
            }

        }


    }


    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {

        try {

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(FeedPostActivity.this.getContentResolver().openInputStream(selectedImage), null, o);

            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(FeedPostActivity.this.getContentResolver().openInputStream(selectedImage), null, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}