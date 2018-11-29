package info.rayrojas.bichito.frutapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import info.rayrojas.bichito.frutapp.R;
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

        private ViewHolder() {
        }
    }

    public ProductAdapter(Context context, int resourceId, List<Product> items, ImageLoader _queue) {
        super(context, resourceId, items);
        this.context = context;
        this.queue = _queue;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Product rowItem = (Product) getItem(position);
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


//        holder.image.setImageBitmap(rowItem.getSmallBitMap());
        return convertView;
    }
}