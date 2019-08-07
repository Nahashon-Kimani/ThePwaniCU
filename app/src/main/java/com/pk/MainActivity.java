package com.pk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pk.adapter.BottomSheetDialog;
import com.pk.drawerFragment.AboutCU;
import com.pk.drawerFragment.BsRegistration;
import com.pk.drawerFragment.CuSocialMedia;
import com.pk.drawerFragment.Help;
import com.pk.drawerFragment.Home;
import com.pk.drawerFragment.ThemeVerse;

public class MainActivity extends AppCompatActivity {
    public static long backPressed;
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    TextView specialNotice;
    String notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        specialNotice = findViewById(R.id.special_notice);

        navigationView.setItemIconTintList(null);
        setSupportActionBar(toolbar);
        specialNotice.setSelected(true);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("New").child("Special Notice");
        /*mRef.setValue("This is a special Notice");*/

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.drawer_open, R.string.drawer_close);

        onSetOnNavigationListener();//This method is used to enable click listener to navigation view
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
            navigationView.setCheckedItem(R.id.drawer_home);
        }

        openSpecialNoticeBottomSheet();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (backPressed + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Snackbar snackbar = Snackbar.make(drawer, "Press back again to exit", Snackbar.LENGTH_SHORT);
                snackbar.show();
                backPressed = System.currentTimeMillis();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_setting:
                Toast.makeText(MainActivity.this, "Under Development", Toast.LENGTH_SHORT).show();
                break;
            case R.id.today_program:
                //This should show a bottom sheet with today program
                openTodayProgramBottomSheet();
                break;
            case R.id.help:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Help()).commit();
                break;
            case R.id.dark_mode:
                Toast.makeText(MainActivity.this, "Under Development", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void onSetOnNavigationListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.drawer_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
                        break;
                    case R.id.drawer_about_cu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutCU()).commit();
                        break;
                    case R.id.drawer_theme_verse:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ThemeVerse()).commit();
                        break;
                    case R.id.drawer_bs_registration:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BsRegistration()).commit();
                        break;
                    case R.id.drawer_forum:
                        //startActivity(new Intent(MainActivity.this, Forum.class));
                        break;
                    case R.id.drawer_night_mode:
                        Toast.makeText(MainActivity.this, "Under Development", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_setting:
                        Toast.makeText(MainActivity.this, "Under development", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_social_media:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CuSocialMedia()).commit();
                        break;
                    case R.id.drawer_help:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Help()).commit();
                        break;
                    case R.id.drawer_feed_back:
                        shareFeedback();
                        break;
                    case R.id.drawer_share_app:
                        shareApp();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    //share app method
    public void shareApp() {
        Intent shareAppIntent = new Intent();
        shareAppIntent.setAction(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.app_name));//replace this with the link at play store
        shareAppIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareAppIntent, "Share " + getResources().getString(R.string.app_name)));
    }

    //method to mail feedback to developers.
    public void shareFeedback() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                "nahashonmbuci@gmail.com", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pwani University CU App Review");
        intent.putExtra(Intent.EXTRA_TEXT, "Please talk to us...");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Send Email"));
        }
    }

    public void openTodayProgramBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
        bottomSheetDialog.show(getSupportFragmentManager(), "OPEN BOTTOM SHEET");
    }

    public void openSpecialNoticeBottomSheet() {
        /*SpecialNoticeBottomsheet bottomsheet = new SpecialNoticeBottomsheet();
        bottomsheet.show(getSupportFragmentManager(), "OPEN BOTTOM SHEET");*/


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                notice = dataSnapshot.getValue().toString().trim();

                if (!notice.isEmpty()) {

                    specialNotice.setVisibility(View.VISIBLE);
                    specialNotice.setText(notice);

                    specialNotice.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            specialNotice.setVisibility(View.GONE);
                        }
                    }, 20000);

                } else {
                    specialNotice.setVisibility(View.GONE);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}