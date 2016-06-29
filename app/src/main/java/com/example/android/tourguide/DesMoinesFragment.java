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

import com.example.android.tourguide.model.Model;
import com.example.android.tourguide.model.ModelAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DesMoinesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DesMoinesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DesMoinesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<Model> desMoinesModels = new ArrayList<Model>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public DesMoinesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DesMoinesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DesMoinesFragment newInstance(String param1, String param2) {
        DesMoinesFragment fragment = new DesMoinesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static DesMoinesFragment newInstance() {
        DesMoinesFragment fragment = new DesMoinesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        loadDesMoinesData();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_des_moines, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        mAdapter = new ModelAdapter(desMoinesModels, new ModelAdapter.OnItemClickListener() {
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

    public void loadDesMoinesData(){
        desMoinesModels = new ArrayList<Model>();
        Model capitalBuilding = new Model();
        capitalBuilding.setId("1");
        capitalBuilding.setName("Capitol Building");
        capitalBuilding.setAddress("1007 E Grand Ave, Des Moines, IA 50319");
        capitalBuilding.setPhone("515-281-5591");
        capitalBuilding.setWebsite("https://www.legis.iowa.gov/docs/LIO/Info/IowaCapitolTours.pdf");
        capitalBuilding.setDetails("Located at East 9th Street and Grand Avenue, the capitol is set atop a hill and offers a panoramic view of the city's downtown and the West capitol Terrace. Various monuments and memorials are to its sides and front, including the Soldiers and Sailors' Monument and the Lincoln and Tad statue.");
        capitalBuilding.setImageResourceId(R.drawable.capitol);
        Model iowaStateFair = new Model();
        iowaStateFair.setId("2");
        iowaStateFair.setName("Iowa State Fair");
        iowaStateFair.setAddress("E. 30th St. and University Ave., Des Moines, IA 50319");
        iowaStateFair.setPhone("515-262-3111");
        iowaStateFair.setWebsite("http://www.iowastatefair.org/");
        iowaStateFair.setDetails("The Iowa State Fair is an annual state fair held in Des Moines, Iowa over 11 days in August and is one of the largest state fairs in the country.");
        iowaStateFair.setImageResourceId(R.drawable.iowastatefair);
        Model farmersMarket = new Model();
        farmersMarket.setId("3");
        farmersMarket.setName("Farmers Market");
        farmersMarket.setAddress("4th Street and Court Avenue, Des Moines, IA 50314");
        farmersMarket.setWebsite("www.desmoinesfarmersmarket.com/");
        farmersMarket.setDetails("Throughout the summer, the Court Avenue District plays home to the nationally recognized Downtown Des Moines Farmers Market. The Market boast over 300 vendors and is free and open to the public. Additionally, the Des Moines Bicycle Collective offers free valet parking for bikes, which is a unique feature of the Des Moines Farmers Market.");
        farmersMarket.setImageResourceId(R.drawable.farmersmarket);
        desMoinesModels.add(capitalBuilding);
        desMoinesModels.add(iowaStateFair);
        desMoinesModels.add(farmersMarket);

    }
}
