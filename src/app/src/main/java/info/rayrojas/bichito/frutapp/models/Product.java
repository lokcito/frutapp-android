package info.rayrojas.bichito.frutapp.models;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import info.rayrojas.bichito.frutapp.helpers.QueueUtils.QueueObject;


public class Product {
    private int id;
    private String name;
    private String description;


    public Product(int i, String name) {
        this.id = i;
        this.name = name;
    }



    public int getId(){
        return this.id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void injectProductsFromCloud(final QueueObject o, final ArrayList<Product> products) {
        String url = "https://reqres.in/api/products?page=2";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("data")) {

                            try {
                                JSONArray list = response.getJSONArray("data");
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    products.add(new Product(o.getInt("id"), o.getString("first_name")));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        int b = 2;
                        b += 1;

                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }

    public static ArrayList<Product> getProductsAsString() {
        ArrayList<Product> o = new ArrayList<>();
        o.add(new Product(1,"Granolas"));
        o.add(new Product(2, "Galletas Integrales"));
        o.add(new Product(3, "Granolas con Miel"));
        o.add(new Product(4,"Granolas de Trigo"));
        o.add(new Product(4,"Salvado de Trigo"));
        o.add(new Product(5,"Quiwicha con miel"));
        o.add(new Product(6,"Miel de eucalipto"));
        o.add(new Product(7,"Pecanas"));
        o.add(new Product(8,"Avellanas"));
        o.add(new Product(9,"Mani"));
        o.add(new Product(10,"Frutos secos"));
        o.add(new Product(11,"Almendras"));
        o.add(new Product(12,"Leche de Almendras"));
        o.add(new Product(13,"Leche de soja"));
        o.add(new Product(14,"Colageno"));
        o.add(new Product(16,"Galle de algarrobina"));
        o.add(new Product(17,"Galleta integral"));
        return o;
    }

    public String toString() {
        return this.name;
    }
    public static Product getById(int _id) {
        for (Product product : Product.getProductsAsString()) {
            if (product.getId() == _id) {
                return product;
            }
        }
        return null;

    }

}
