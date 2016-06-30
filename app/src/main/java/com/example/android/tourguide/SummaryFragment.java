package com.example.android.tourguide;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tourguide.model.Model;
import com.example.android.tourguide.model.ModelAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SummaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SummaryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String TAG = "SummaryFragment";
    public static final String DESMOINES = "Des Moines";
    public static final String AMES = "Ames";
    public static final String IOWACITY = "Iowa City";
    public static final String DUBUQUE = "Dubuque";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<Model> models = new ArrayList<Model>();

    TextView header;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public SummaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SummaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SummaryFragment newInstance(String param1, String param2) {
        SummaryFragment fragment = new SummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static SummaryFragment newInstance(String param1) {
        SummaryFragment fragment = new SummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public static SummaryFragment newInstance() {
        SummaryFragment fragment = new SummaryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mParam1 = getArguments().getString(ARG_PARAM1);
            if(mParam1.equals(SummaryFragment.DESMOINES)){
                loadDesMoinesData();
            }else if(mParam1.equals(SummaryFragment.AMES)){
                loadAmesData();
            }else if(mParam1.equals(SummaryFragment.IOWACITY)){
                loadIowaCityData();
            }else if(mParam1.equals(SummaryFragment.DUBUQUE)){
                loadDubuqueData();
            }

        }else{
            loadDesMoinesData();
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_summary, container, false);

        header = (TextView)rootView.findViewById(R.id.header);
        if(mParam1.equals(SummaryFragment.DESMOINES)){
            header.setText(SummaryFragment.DESMOINES);
        }else if(mParam1.equals(SummaryFragment.AMES)){
            header.setText(SummaryFragment.AMES);
        }else if(mParam1.equals(SummaryFragment.IOWACITY)){
            header.setText(IOWACITY);
        }else if(mParam1.equals(SummaryFragment.DUBUQUE)){
            header.setText(SummaryFragment.DUBUQUE);
        }

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        mAdapter = new ModelAdapter(models, new ModelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Model item, int position) {

                ((NavigationActivity)getActivity()).openDetails(item);

            }
        });
        mRecyclerView.setAdapter(mAdapter);


        return rootView;


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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

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

    public void loadDesMoines(){
        loadDesMoinesData();
        mAdapter.notifyDataSetChanged();
    }
    public void loadAmes(){
        loadAmesData();
        mAdapter.notifyDataSetChanged();
    }
    public void loadIowaCity(){
        loadIowaCityData();
        mAdapter.notifyDataSetChanged();
    }
    public void loadDubuque(){
        loadDubuqueData();
        mAdapter.notifyDataSetChanged();
    }

    public int getImageResourceFromString(String drawableName){
        return getResources().getIdentifier(drawableName, "drawable", getActivity().getApplicationContext().getPackageName());
    }

    public void loadDesMoinesData(){
        models = new ArrayList<Model>();
        Model capitalBuilding = new Model();
        capitalBuilding.setId(getResources().getString(R.string.capitolbuilding_id));
        capitalBuilding.setName(getResources().getString(R.string.capitolbuilding_name));
        capitalBuilding.setAddress(getResources().getString(R.string.capitolbuilding_address));
        capitalBuilding.setPhone(getResources().getString(R.string.capitolbuilding_phone));
        capitalBuilding.setWebsite(getResources().getString(R.string.capitolbuilding_website));
        capitalBuilding.setDetails(getResources().getString(R.string.capitolbuilding_details));
        capitalBuilding.setImageResourceId(getImageResourceFromString(getResources().getString(R.string.capitolbuilding_imageresourceid)));
        Model iowaStateFair = new Model();
        iowaStateFair.setId(getResources().getString(R.string.iowastatefair_id));
        iowaStateFair.setName(getResources().getString(R.string.iowastatefair_name));
        iowaStateFair.setAddress(getResources().getString(R.string.iowastatefair_address));
        iowaStateFair.setPhone(getResources().getString(R.string.iowastatefair_phone));
        iowaStateFair.setWebsite(getResources().getString(R.string.iowastatefair_website));
        iowaStateFair.setDetails(getResources().getString(R.string.iowastatefair_details));
        iowaStateFair.setImageResourceId(getImageResourceFromString(getResources().getString(R.string.iowastatefair_imageresourceid)));
        Model farmersMarket = new Model();
        farmersMarket.setId(getResources().getString(R.string.farmersmarket_id));
        farmersMarket.setName(getResources().getString(R.string.farmersmarket_name));
        farmersMarket.setAddress(getResources().getString(R.string.farmersmarket_address));
        farmersMarket.setWebsite(getResources().getString(R.string.farmersmarket_website));
        farmersMarket.setDetails(getResources().getString(R.string.farmersmarket_details));
        farmersMarket.setImageResourceId(getImageResourceFromString(getResources().getString(R.string.farmersmarket_imageresourceid)));
        models.add(capitalBuilding);
        models.add(iowaStateFair);
        models.add(farmersMarket);
    }
    public void loadAmesData(){
        models = new ArrayList<Model>();
        Model isu = new Model();
        isu.setId(getResources().getString(R.string.isu_id));
        isu.setName(getResources().getString(R.string.isu_name));
        isu.setAddress(getResources().getString(R.string.isu_address));
        isu.setPhone(getResources().getString(R.string.isu_phone));
        isu.setWebsite(getResources().getString(R.string.isu_website));
        isu.setDetails(getResources().getString(R.string.isu_details));
        isu.setImageResourceId(getImageResourceFromString(getResources().getString(R.string.isu_imageresourceid)));

        Model gardens = new Model();
        gardens.setId(getResources().getString(R.string.gardens_id));
        gardens.setName(getResources().getString(R.string.gardens_name));
        gardens.setAddress(getResources().getString(R.string.gardens_address));
        gardens.setPhone(getResources().getString(R.string.gardens_phone));
        gardens.setWebsite(getResources().getString(R.string.gardens_website));
        gardens.setDetails(getResources().getString(R.string.gardens_details));
        gardens.setImageResourceId(getImageResourceFromString(getResources().getString(R.string.gardens_imageresourceid)));

        Model artMuseum = new Model();
        artMuseum.setId(getResources().getString(R.string.artmuseum_id));
        artMuseum.setName(getResources().getString(R.string.artmuseum_name));
        artMuseum.setAddress(getResources().getString(R.string.artmuseum_address));
        artMuseum.setPhone(getResources().getString(R.string.artmuseum_phone));
        artMuseum.setWebsite(getResources().getString(R.string.artmuseum_website));
        artMuseum.setDetails(getResources().getString(R.string.artmuseum_details));
        artMuseum.setImageResourceId(getImageResourceFromString(getResources().getString(R.string.artmuseum_imageresourceid)));

        models.add(isu);
        models.add(gardens);
        models.add(artMuseum);
    }

    public void loadIowaCityData() {
        models = new ArrayList<Model>();

        Model uofi = new Model();
        uofi.setId(getResources().getString(R.string.uofi_id));
        uofi.setName(getResources().getString(R.string.uofi_name));
        uofi.setAddress(getResources().getString(R.string.uofi_address));
        uofi.setPhone(getResources().getString(R.string.uofi_phone));
        uofi.setWebsite(getResources().getString(R.string.uofi_website));
        uofi.setDetails(getResources().getString(R.string.uofi_details));
        uofi.setImageResourceId(getImageResourceFromString(getResources().getString(R.string.uofi_imageresourceid)));

        Model fossils = new Model();
        fossils.setId(getResources().getString(R.string.fossils_id));
        fossils.setName(getResources().getString(R.string.fossils_name));
        fossils.setAddress(getResources().getString(R.string.fossils_address));
        fossils.setPhone(getResources().getString(R.string.fossils_phone));
        fossils.setWebsite(getResources().getString(R.string.fossils_website));
        fossils.setDetails(getResources().getString(R.string.fossils_details));
        fossils.setImageResourceId(getImageResourceFromString(getResources().getString(R.string.fossils_imageresourceid)));

        Model orchard = new Model();
        orchard.setId(getResources().getString(R.string.orchard_id));
        orchard.setName(getResources().getString(R.string.orchard_name));
        orchard.setAddress(getResources().getString(R.string.orchard_address));
        orchard.setPhone(getResources().getString(R.string.orchard_phone));
        orchard.setWebsite(getResources().getString(R.string.orchard_website));
        orchard.setDetails(getResources().getString(R.string.orchard_details));
        orchard.setImageResourceId(getImageResourceFromString(getResources().getString(R.string.orchard_imageresourceid)));

        models.add(uofi);
        models.add(fossils);
        models.add(orchard);
    }

    public void loadDubuqueData(){
        models = new ArrayList<Model>();

        Model aquarium = new Model();
        aquarium.setId(getResources().getString(R.string.aquarium_id));
        aquarium.setName(getResources().getString(R.string.aquarium_name));
        aquarium.setAddress(getResources().getString(R.string.aquarium_address));
        aquarium.setPhone(getResources().getString(R.string.aquarium_phone));
        aquarium.setWebsite(getResources().getString(R.string.aquarium_website));
        aquarium.setDetails(getResources().getString(R.string.aquarium_details));
        aquarium.setImageResourceId(getImageResourceFromString(getResources().getString(R.string.aquarium_imageresourceid)));

        Model elevator = new Model();
        elevator.setId(getResources().getString(R.string.elevator_id));
        elevator.setName(getResources().getString(R.string.elevator_name));
        elevator.setAddress(getResources().getString(R.string.elevator_address));
        elevator.setPhone(getResources().getString(R.string.elevator_phone));
        elevator.setWebsite(getResources().getString(R.string.elevator_website));
        elevator.setDetails(getResources().getString(R.string.elevator_details));
        elevator.setImageResourceId(getImageResourceFromString(getResources().getString(R.string.elevator_imageresourceid)));

        Model park = new Model();
        park.setId(getResources().getString(R.string.park_id));
        park.setName(getResources().getString(R.string.park_name));
        park.setAddress(getResources().getString(R.string.park_address));
        park.setPhone(getResources().getString(R.string.park_phone));
        park.setWebsite(getResources().getString(R.string.park_website));
        park.setDetails(getResources().getString(R.string.park_details));
        park.setImageResourceId(getImageResourceFromString(getResources().getString(R.string.park_imageresourceid)));

        models.add(aquarium);
        models.add(elevator);
        models.add(park);
    }

}
