package info.rayrojas.bichito.frutapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import info.rayrojas.bichito.frutapp.R;
import info.rayrojas.bichito.frutapp.models.Product;

public class ProductActivity extends AppCompatActivity {

    Product productObject;
    TextView productName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent myIntent = getIntent(); // gets the previously created intent
        int _productId = myIntent.getIntExtra("productId", 0);

        try {
//            int _productId = Integer.parseInt(productId);
            productObject = Product.getById(_productId);
        } catch (Exception e) {

        }

        if ( productObject == null ) {
            Intent o = new Intent(ProductActivity.this, ProductListActivity.class);
            o.putExtra("status", "error");
            startActivity(o);
            finish();
            return;
        }

        productName = (TextView)findViewById(R.id.textViewName);
        productName.setText(productObject.getName());
    }
}
