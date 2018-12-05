package info.rayrojas.bichito.frutapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import info.rayrojas.bichito.frutapp.R;
import info.rayrojas.bichito.frutapp.adapters.CarItemAdapter;
import info.rayrojas.bichito.frutapp.models.CarItem;
import info.rayrojas.bichito.frutapp.models.Product;

public class CarActivity extends AppCompatActivity {
    ListView listViewCarItems;
    CarItemAdapter itemsAdapter;
    TextView txtTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        listViewCarItems = (ListView) findViewById(R.id.listViewCarItems);

        ArrayList<CarItem> items = CarItem.get();

        itemsAdapter =
                new CarItemAdapter(this, R.layout.adapter_car_item, items);

        listViewCarItems.setAdapter(itemsAdapter);

        listViewCarItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Product selItem = (Product) adapter.getItemAtPosition(position);
                if ( selItem != null ) {
//                    Intent o = new Intent(CarActivity.this, ProductActivity.class);
//                    o.putExtra("productId", selItem.getId());
//
//                    startActivity(o);
//                    Log.d(Settings.DEBUG, "La aplicacion dijo: " + selItem.getName());
                }
            }
        });

    }
    @Override
    public void onResume(){
        super.onResume();
        txtTotal = (TextView)findViewById(R.id.txtTotal);
        txtTotal.setText("12.00");

    }
}
