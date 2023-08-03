package com.example.challenges;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.challenges.adapter.NotificationAdapter;
import com.example.challenges.fragment.DisplayQRContentFragment;
import com.example.challenges.fragment.LibraryFragment;
import com.example.challenges.fragment.NotifiFragment;
import com.example.challenges.fragment.ScheduleFragment;
import com.example.challenges.fragment.ScoreFragment;
import com.example.challenges.model.Notification;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    List<Notification> dataList;
    NotificationAdapter dataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                if (item.getItemId() == R.id.notificationBottom){
                    fragment = new NotifiFragment();
                } else if (item.getItemId() == R.id.scheduleBottom) {
                    fragment = new ScheduleFragment();
                } else if (item.getItemId() == R.id.qrScanBottom) {
                    fragment = new DisplayQRContentFragment();
                } else if (item.getItemId() == R.id.scoreBottom){
                    fragment = new ScoreFragment();
                }else {
                    fragment = new LibraryFragment();

                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout,fragment).commit();
                return true;
            }
        });

        Toolbar toolBar = findViewById(R.id.toolBar);
//        edtSearch = findViewById(R.id.edtSearch);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout,toolBar,
                R.string.open,
                R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        Intent intent = getIntent();
        String coso = intent.getStringExtra("coso");
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("name");
        String pictureUrl = intent.getStringExtra("picture");

        //Ánh xạ các phần tử trong header
        View headerView = navigationView.getHeaderView(0);
        ImageView imgProfileNav = headerView.findViewById(R.id.imgProfileNav);
        Glide.with(this)
                .load(pictureUrl)
                .circleCrop()
                .into(imgProfileNav);

        TextView tvNameProfileNav = headerView.findViewById(R.id.tvNameProfileNav);
        tvNameProfileNav.setText("Name: " + name);

        TextView tvEmailProfileNav = headerView.findViewById(R.id.tvEmailProfileNav);
        tvEmailProfileNav.setText("Email: " + email);

        TextView tvCoSoProfileNav = headerView.findViewById(R.id.tvCoSoProfileNav);
        tvCoSoProfileNav.setText("Cơ sở: " + coso);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.icLogOut){
                    mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            SharedPreferences.Editor editor = getSharedPreferences("userdata",MODE_PRIVATE).edit();
                            editor.clear();
                            editor.apply();

                            Intent signOutIntent = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(signOutIntent);
                            finish();
                        }
                    });
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu to add items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        //Set the query listener for handling search
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                filterData(query);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (dataList != null){
//                    filterData(newText);
//                }
//                return true;
//            }
//        });
        return true;
    }

//    private void filterData(String query) {
//        if (dataList != null){
//            List<Notification> filteredList = new ArrayList<>();
//            for (Notification item : dataList){
//                if (item.getTitle().toLowerCase().contains(query.toLowerCase())){
//                    filteredList.add(item);
//                }
//            }
//            dataAdapter.updateData(filteredList);
//            dataAdapter.notifyDataSetChanged();
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        // Handle other action bar item clicks here if needed
        return super.onOptionsItemSelected(item);
    }
}