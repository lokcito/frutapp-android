package info.rayrojas.bichito.frutapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import info.rayrojas.bichito.frutapp.MenuActivity;
import info.rayrojas.bichito.frutapp.R;
import info.rayrojas.bichito.frutapp.activities.CarActivity;
import info.rayrojas.bichito.frutapp.activities.ProductActivity;
import info.rayrojas.bichito.frutapp.activities.ProductListActivity;
import info.rayrojas.bichito.frutapp.generals.Settings;
import info.rayrojas.bichito.frutapp.helpers.QueueUtils;
import info.rayrojas.bichito.frutapp.models.Product;


public class ProductAdapter extends ArrayAdapter<Product> {
    Context context;
    ImageLoader queue;

    private class ViewHolder {
        NetworkImageView image;
        TextView name;
        TextView price;
        TextView description;
        TextView category;
        ImageButton btnAddToBag;

        private ViewHolder() {
        }
    }

    public ProductAdapter(Context context, List<Product> items, ImageLoader _queue) {
        super(context, 0, items);
        this.context = context;
        this.queue = _queue;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Product rowItem = (Product) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_product_item, null);
            holder = new ViewHolder();
//            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.image = (NetworkImageView)convertView.findViewById(R.id.image);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.category = (TextView) convertView.findViewById(R.id.category);
            holder.btnAddToBag = (ImageButton) convertView.findViewById(R.id.btnAddToBag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(rowItem.getName());
        holder.price.setText(rowItem.getPriceText());
        holder.description.setText(rowItem.getDescription());
        holder.category.setText(rowItem.getCategory());

        if ( rowItem.getSmallImage() != null ) {
            holder.image.setImageUrl(
                    rowItem.getSmallImage(), queue);
        }

        holder.btnAddToBag.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MenuActivity activity = (MenuActivity) context;
                activity.productListFragmentSelectProduct(position);

            }
        });


//        holder.image.setImageBitmap(rowItem.getSmallBitMap());
        return convertView;
    }
}