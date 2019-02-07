package info.rayrojas.bichito.frutapp;


import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import info.rayrojas.bichito.frutapp.fragments.AboutUsFragment;
import info.rayrojas.bichito.frutapp.fragments.AccountFragment;
import info.rayrojas.bichito.frutapp.fragments.CarFragment;
import info.rayrojas.bichito.frutapp.fragments.MapFragment;
import info.rayrojas.bichito.frutapp.fragments.ProductFragment;
import info.rayrojas.bichito.frutapp.fragments.ProductListFragment;
import info.rayrojas.bichito.frutapp.generals.Settings;
import info.rayrojas.bichito.frutapp.models.Product;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase databaseFireBase;
    DatabaseReference productsReference;
    //FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        databaseFireBase = FirebaseDatabase.getInstance();
        productsReference = databaseFireBase.getReference("products");

        /* Si queremos escuchar cambios */
        productsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if ( dataSnapshot != null ) {
                    Product o = dataSnapshot.getValue(Product.class);
                    if ( o != null ) {
                        Toast.makeText(MenuActivity.this,
                            String.format("Producto agregado es: %s", o.getName()),
                            Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentChange("product-list");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            fragmentChange("product-list");
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            fragmentChange("cart");
        } else if (id == R.id.nav_slideshow) {
            fragmentChange("about");
        } else if ( id == R.id.send_message ) {
            /* Enviamos datos a firebase */
            ArrayList<Product> products = new ArrayList<>();

            Product myNewProduct = new Product(1, "Galletas integrales");
            products.add(myNewProduct);

            productsReference.setValue(products);
            /* Enviamos datos a firebase */
        } else if (id == R.id.nav_send) {
            fragmentChange("maps");
        } else if ( id == R.id.show_my_account ) {
            fragmentChange("my-account");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void fragmentChange(String _fragment) {
        if ( _fragment.equals("product-list") ) {
            getFragmentManager().beginTransaction().
                    replace(R.id.mainFragment, new ProductListFragment()).addToBackStack(null).commit();
        } else if ( _fragment.equals("cart") ) {
            getFragmentManager().beginTransaction().
                    replace(R.id.mainFragment, new CarFragment()).addToBackStack(null).commit();
        } else if ( _fragment.equals("about-us") ) {
            getFragmentManager().beginTransaction().
                    replace(R.id.mainFragment, new AboutUsFragment()).addToBackStack(null).commit();
        } else if ( _fragment.equals("product") ) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.mainFragment, new ProductFragment()).addToBackStack(null).commit();
        } else if ( _fragment.equals("my-account") ) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.mainFragment, new AccountFragment()).addToBackStack(null).commit();
        } else if ( _fragment.equals("maps") ) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.mainFragment, new MapFragment()).addToBackStack(null).commit();
        }
    }

    public void productListFragmentSelectProduct(int position) {
        fragmentChange("product");
    }

}
