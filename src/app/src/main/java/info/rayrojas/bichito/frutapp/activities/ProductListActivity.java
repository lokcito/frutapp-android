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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

import info.rayrojas.bichito.frutapp.R;
import info.rayrojas.bichito.frutapp.adapters.ProductAdapter;
import info.rayrojas.bichito.frutapp.generals.Settings;
import info.rayrojas.bichito.frutapp.helpers.QueueUtils;
import info.rayrojas.bichito.frutapp.helpers.QueueUtils.QueueObject;
import info.rayrojas.bichito.frutapp.models.Product;

public class ProductListActivity extends AppCompatActivity {
    ListView listViewProducts;
    QueueObject queue = null;
    ProductAdapter itemsAdapter;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        queue = QueueUtils.getInstance(this.getApplicationContext());

        listViewProducts = (ListView) findViewById(R.id.listViewProducts);

        ArrayList<Product> items = new ArrayList<>();

        Product.injectProductsFromCloud(queue, items, this);

        itemsAdapter =
                new ProductAdapter(this, android.R.layout.simple_list_item_1, items, queue.getImageLoader());

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
    public void refreshList(){
        if ( itemsAdapter!= null ) {
            itemsAdapter.notifyDataSetChanged();
        }
    }
}
