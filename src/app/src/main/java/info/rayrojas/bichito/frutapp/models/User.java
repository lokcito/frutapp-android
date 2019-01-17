package info.rayrojas.bichito.frutapp.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import info.rayrojas.bichito.frutapp.fragments.AccountFragment;
import info.rayrojas.bichito.frutapp.helpers.QueueUtils;
import info.rayrojas.bichito.frutapp.helpers.QueueUtils.QueueObject;

public class User {
    public int id;
    public String token;
    public String username;
    public String first_name;
    public String last_name;
    public User() {

    }
    public User(String _username, String _first_name, String _last_name) {
        this.username = _username;
        this.first_name = _first_name;
        this.last_name = _last_name;
    }

    public static void sync(QueueObject o, String _username, final AccountFragment _interface) {
        String url = String.format("http://rrojasen.alwaysdata.net/users/get/by/username.json?username=%s", _username);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Cuando todo va bien
                        if ( response.has("data") ) {
                            JSONObject object_response = null;
                            try {
                                object_response = response.getJSONObject("data");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if ( object_response != null ) {
                                try {
                                    User userCloud = new User(object_response.getString("username"),
                                            object_response.getString("first_name"),
                                            object_response.getString("last_name"));
                                    // Se llamara a: _interface
                                    _interface.setUser(userCloud);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Cuando hay un error
                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }

}
