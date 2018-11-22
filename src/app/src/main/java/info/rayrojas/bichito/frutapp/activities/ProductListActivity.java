package info.rayrojas.bichito.frutapp.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import info.rayrojas.bichito.frutapp.R;
import info.rayrojas.bichito.frutapp.generals.Settings;
import info.rayrojas.bichito.frutapp.models.Product;

public class ProductListActivity extends AppCompatActivity {
    ListView listViewProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        listViewProducts = (ListView) findViewById(R.id.listViewProducts);

        ArrayList<Product> items = Product.getProductsAsString();

        ArrayAdapter<Product> itemsAdapter =
                new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, items);

        listViewProducts.setAdapter(itemsAdapter);

        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Product selItem = (Product) adapter.getItemAtPosition(position);
                if ( selItem != null ) {
                    Intent o = new Intent(ProductListActivity.this, ProductActivity.class);
                    o.putExtra("productId", selItem.getId());

                    startActivity(o);
                    Log.d(Settings.DEBUG, "La aplicacion dijo: " + selItem.getName());
                }
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        Intent myIntent = getIntent();
        String statusRequest = myIntent.getStringExtra("status");
        if (statusRequest != null && !statusRequest.isEmpty() ) {
            Toast.makeText(this,"Oops", Toast.LENGTH_SHORT).show();
        }
        // put your code here...

    }
}
