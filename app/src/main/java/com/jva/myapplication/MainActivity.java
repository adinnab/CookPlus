package com.jva.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {



    ActionBarDrawerToggle toggle;

    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawerLayout);


        /// Adaugam un toggle pe drawer layout
        toggle = new ActionBarDrawerToggle(this,
                drawerLayout, R.string.open , R.string.close);

        // Un listener pe drawerLayout (cand dam click )
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        //Salvam actionbar-ul in variabila actionbar
        ActionBar actionbar = getSupportActionBar();

        if( actionbar != null ) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }else
        {
            Toast.makeText(this,"EROR",Toast.LENGTH_LONG).show();
        }

        NavigationView navView = findViewById(R.id.navView);

        /// Prima data cand se deschide aplicatia,  facem inflate la fragmentul Home
        replaceFragment(new HomeFragment());

        // Cand dam click pe un item din drawer
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                      @Override
                                                      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                          item.isChecked();
                                                          if(item.getItemId() == R.id.nav_home)
                                                          {
                                                              replaceFragment(new HomeFragment());
                                                          }else if(item.getItemId() == R.id.nav_food)
                                                          {
                                                              replaceFragment(new FoodFragment());
                                                          }else if(item.getItemId() == R.id.nav_message)
                                                          {
                                                              replaceFragment(new MessageFragment());
                                                          }
                                                          else if(item.getItemId() == R.id.nav_settings)
                                                          {
                                                              replaceFragment(new SettingsFragment());
                                                          }

                                                          return true;
                                                      }
                                                  }

        );

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment)
    {
        // Initializam un fragmentmanager
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTraction = fragmentManager.beginTransaction();

        fragmentTraction.replace(R.id.frameLayout,fragment);
        fragmentTraction.commit();
        //Inchidem drawer-ul dupa ce am randat noul fragment
        drawerLayout.closeDrawers();

    }

}