package info.rayrojas.bichito.frutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import info.rayrojas.bichito.frutapp.activities.LoginActivity;
import info.rayrojas.bichito.frutapp.activities.ProductListActivity;
import info.rayrojas.bichito.frutapp.models.User;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User userObject = new User();
        userObject.getOne(this);
        if ( userObject.localSynced ) {
            Intent o = new Intent(this, MenuActivity.class);
            startActivity(o);
            finish();
        } else {
            Intent o = new Intent(this, LoginActivity.class);
            startActivity(o);
            finish();
        }

    }
}
