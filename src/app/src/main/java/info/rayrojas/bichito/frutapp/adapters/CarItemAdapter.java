package info.rayrojas.bichito.frutapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import info.rayrojas.bichito.frutapp.R;
import info.rayrojas.bichito.frutapp.activities.CarActivity;
import info.rayrojas.bichito.frutapp.models.CarItem;

public class CarItemAdapter extends ArrayAdapter<CarItem> {
    Context context;
    ImageLoader queue;
    CarActivity activity;

    private class ViewHolder {
        NetworkImageView image;
        TextView text;
        TextView price;
        EditText qty;
        Button btnUpdate;
        Button btnRemove;

//        TextView category;

        private ViewHolder() {
        }
    }

    public CarItemAdapter(Context context, int resourceId, List<CarItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final CarItemAdapter.ViewHolder holder;
        CarItem rowItem = (CarItem) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_car_item, null);
            holder = new CarItemAdapter.ViewHolder();
//            holder.image = (ImageView) convertView.findViewById(R.id.image);
//            holder.image = (NetworkImageView)convertView.findViewById(R.id.image);
            holder.text = (TextView) convertView.findViewById(R.id.txtText);
            holder.qty = (EditText) convertView.findViewById(R.id.editTextQty);
            holder.btnUpdate = (Button) convertView.findViewById(R.id.btnUpdate);

            convertView.setTag(holder);
        } else {
            holder = (CarItemAdapter.ViewHolder) convertView.getTag();
        }

        holder.text.setText(rowItem.getText());
        holder.qty.setText(rowItem.getQuantity() + "");
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                EditText _qty = (EditText)v;
//                rowItem.setQuantity(_qty.getText());
                CarActivity activity = (CarActivity) context;
                activity.updateCartItem(position, holder.qty.getText().toString());
                //.refreshTotals();
            }
        });
//        holder.price.setText(rowItem.getPriceText());
//        holder.description.setText(rowItem.getDescription());
//        holder.category.setText(rowItem.getCategory());

//        if ( rowItem.getSmallImage() != null ) {
//            holder.image.setImageUrl(
//                    rowItem.getSmallImage(), queue);
//        }


//        holder.image.setImageBitmap(rowItem.getSmallBitMap());
        return convertView;
    }
}