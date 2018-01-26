package com.example.konstantin.vktestapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.konstantin.vktestapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Konstantin on 10.01.2018.
 */

public class WorkActivity extends AppCompatActivity {

    public static final String TAG = "VKTestApp";
    private FragmentManager fm;
    private Fragment fragment;
    private Intent openLoginActivity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        // Настройка toolbar
        Toolbar toolbar = findViewById(R.id.toolbarWorkActivity);
        setSupportActionBar(toolbar);

        // переход на активити с логином
        openLoginActivity = new Intent(this, LoginActivity.class);
        // убираем активити из бэкстэка после разлогина
        openLoginActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_container);

        // если фрагмент еще не существует - создаем новый
        // иначе - загружаем уже подготовленный
        if (fragment == null) {
            fragment = createFragment();

            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_logout:
                // Logout menu item
                logoutVK();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void logoutVK() {
        openLoginActivity.putExtra("shouldLogout", true);
        startActivity(openLoginActivity);
    }

    protected Fragment createFragment() {
        return new FriendsListFragment();
    }
}
