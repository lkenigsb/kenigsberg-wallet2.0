package com.mintedtech.wallet_20.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mintedtech.wallet_20.R;
import com.mintedtech.wallet_20.classes.CardItemAdapter;

import static com.mintedtech.wallet_20.lib.Utils.showInfoDialog;

public class MainActivity extends AppCompatActivity {
    private Snackbar mSnackBar;

    private final int[] cardImages = {R.drawable.american_express_image, R.drawable.mastercard_image, R.drawable.visa_image};

    private final String[] cardNames = {"American Express", "Master Card", "Visa"};

    private final String[] url = {"https://www.americanexpress.com/en-us/account/login",
            "https://customerportal.mastercard.com/login",
            "https://usa.visa.com/en_us/account/login?returnurl=%2Fen_us%2Faccount%2Fprofile"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setupToolbar();
        setupFAB();
        mSnackBar = Snackbar.make(findViewById(android.R.id.content).getRootView(), "Welcome", Snackbar.LENGTH_LONG);

        // Create a reference to the RecyclerView in activity_main.xml
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // set number of columns to 1 or 2 for portrait or landscape respectively
        // Please note the use of an xml integer here: portrait will be 1x9 and landscape 2x5; neat!
        final int COLUMNS = getResources().getInteger(R.integer.rv_columns);

        // create and set a Grid Layout Manager to use as the Layout Manager for this RV
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, COLUMNS);
        recyclerView.setLayoutManager(gridLayoutManager);

        // create and set an adapter to use as the Layout Manager for this RV
        CardItemAdapter cardItemAdapter = new CardItemAdapter(MainActivity.this, cardImages, cardNames, url);
        recyclerView.setAdapter(cardItemAdapter);

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> handleFabClick(view));
    }

    private void handleFabClick(View view) {
        Snackbar.make(view, "Adding another card?", Snackbar.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                showSettings();
                return true;
            }
            case R.id.action_about: {
                showAbout();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSettings() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    private void showAbout() {
        dismissSnackBarIfShown();

        showInfoDialog(MainActivity.this, "About Wallet 2.0",
                "An Easy accessible way to access your major cc\n" +
                        "\nApp by Libby Kenigsberg\nlibbykenigsberg@gmail.com");
    }

    private void dismissSnackBarIfShown() {
        if (mSnackBar.isShown()) {
            mSnackBar.dismiss();
        }
    }
}