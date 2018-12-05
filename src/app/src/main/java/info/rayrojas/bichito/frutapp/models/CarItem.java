package info.rayrojas.bichito.frutapp.models;

import android.content.Context;

import java.util.ArrayList;

public class CarItem {
    private int id;
    private String text;
    private int quantity;
    private float price;
    private Context contexto;

    public CarItem(int i, String text, int quantity, float price) {
        this.setId(i);
        this.setText(text);
        this.setQuantity(quantity);
        this.setPrice(price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public static ArrayList<CarItem> get() {
        ArrayList<CarItem> o = new ArrayList<>();
        o.add(new CarItem(1, "Granola", 12, 34));
        o.add(new CarItem(1, "Galleta", 2, 15));
        return o;
    }
}
