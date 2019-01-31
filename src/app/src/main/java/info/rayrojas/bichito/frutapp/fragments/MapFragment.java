package info.rayrojas.bichito.frutapp.fragments;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import info.rayrojas.bichito.frutapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MapView mMapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private TextView textViewCurrentLocation;
    private ZoomControls zoomControls;
    private GoogleMap googleMapInstance;
    private OnFragmentInteractionListener mListener;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        textViewCurrentLocation = (TextView)v.findViewById(R.id.txtCurrentLocation);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView = (MapView) v.findViewById(R.id.mapView);
//        mMapView.setb
        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);

        mMapView.setClickable(true);
        zoomControls = (ZoomControls) v.findViewById(R.id.zoomcontrols);
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( googleMapInstance == null ) {
                    return;
                }
                googleMapInstance.animateCamera(CameraUpdateFactory.zoomTo( 17.0f ));
//                mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );

            }
        });
        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( googleMapInstance == null ) {
                    return;
                }
                googleMapInstance.animateCamera(CameraUpdateFactory.zoomTo( 10.0f ));
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMapInstance = googleMap;
        LatLng coordinates = new LatLng(-12.0611391,-75.2127232);
        googleMap.addMarker(new MarkerOptions().position(coordinates).title("Marker"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15));
        mMapView.onResume();

        googleMap.setOnMapClickListener(this);
//        mMap.setOnMapLongClickListener(this);
//        mMap.setOnCameraIdleListener(this);
//
    }

    @Override
    public void onMapClick(LatLng location) {


//        if (location != null) {
//            textViewCurrentLocation.setText(location.toString());
//            System.out.println("in onlocationchanged");
//            String locationString = location. .convert(location.getLatitude(), 1);
//            Toast.makeText(this, "locationString==" + locationString, Toast.LENGTH_LONG).show();
//            double lat = location.getLatitude();
//            double lng = location.getLongitude();
//            String currentLocation = "The location is changed to Lat: " + lat + " Lng: " + lng;
//            Toast.makeText(this, currentLocation, Toast.LENGTH_LONG).show();
//        }
    }

//    @Override
//    public void onLocationChanged(Location location) {
//        textViewCurrentLocation.setText(location.toString());
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
