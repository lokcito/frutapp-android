package info.rayrojas.bichito.frutapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
//import android.provider.Settings;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import info.rayrojas.bichito.frutapp.fragments.AccountFragment;
import info.rayrojas.bichito.frutapp.generals.Settings;
import info.rayrojas.bichito.frutapp.helpers.QueueUtils;
import info.rayrojas.bichito.frutapp.helpers.QueueUtils.QueueObject;

public class User {
    public int id;
    public String token;
    public String username;
    public String first_name;
    public String last_name;
    public boolean localSynced = false;
    public static UserDbHelper dbInstance;
    public User() {

    }

    /* LocalStorage */
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + UserTable.TABLE_NAME + " (" +
                    UserTable._ID + " INTEGER PRIMARY KEY," +
                    UserTable.COLUMN_NAME_FIRST_NAME + " TEXT," +
                    UserTable.COLUMN_NAME_TOKEN + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserTable.TABLE_NAME;

    public static class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "frutapp_user";
        public static final String COLUMN_NAME_FIRST_NAME = "first_name";
        public static final String COLUMN_NAME_TOKEN = "token";
    }

    public class UserDbHelper extends SQLiteOpenHelper {

        public UserDbHelper(Context context) {
            super(context, Settings.DATABASE_NAME, null, Settings.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

    /* End LocalStorage */

    public User(String _username, String _first_name, String _last_name) {
        this.username = _username;
        this.first_name = _first_name;
        this.last_name = _last_name;
    }

    public UserDbHelper getDbInstance(Context _context) {
        if ( User.dbInstance == null ) {
            User.dbInstance = new UserDbHelper(_context);
            return User.dbInstance;
        }
        return User.dbInstance;
    }

    public void getOne(Context _context) {
        SQLiteDatabase db = this.getDbInstance(_context).getReadableDatabase();

        String[] fields = new String[] {UserTable.COLUMN_NAME_FIRST_NAME};
        String[] args = new String[] {this.id + ""};

        this.localSynced = false;

        Cursor c = db.query(UserTable.TABLE_NAME, fields, null, null,  null, null, null);

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            do {
                this.first_name = c.getString(0);
                this.localSynced = true;
            } while(c.moveToNext());
        }
    }

    public void syncLocal(Context _context) {
        SQLiteDatabase db = this.getDbInstance(_context).getReadableDatabase();

        String[] fields = new String[] {UserTable.COLUMN_NAME_FIRST_NAME};
        String[] args = new String[] {this.id + ""};

        this.localSynced = false;

        Cursor c = db.query(UserTable.TABLE_NAME, fields, "_id=?", args,  null, null, null);

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            do {
                this.first_name = c.getString(0);
                this.localSynced = true;
            } while(c.moveToNext());
        }
    }

    public void setLocal(Context _context) {
        SQLiteDatabase db = this.getDbInstance(_context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserTable._ID, this.id);
        values.put(UserTable.COLUMN_NAME_FIRST_NAME, this.first_name);
        values.put(UserTable.COLUMN_NAME_TOKEN, this.token);

        String[] args = new String[] {this.id + ""};

        this.syncLocal(_context);

        if ( this.localSynced ) {
            db.update(UserTable.TABLE_NAME, values, "_id = ?", args);
        } else {
            long newRowId = db.insert(UserTable.TABLE_NAME, null, values);
        }

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
