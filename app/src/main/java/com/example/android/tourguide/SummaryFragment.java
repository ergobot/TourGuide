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

    public void loadDesMoinesData(){
        models = new ArrayList<Model>();
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
        models.add(capitalBuilding);
        models.add(iowaStateFair);
        models.add(farmersMarket);
    }
    public void loadAmesData(){
        models = new ArrayList<Model>();
        Model isu = new Model();
        isu.setId("1");
        isu.setName("Iowa State University");
        isu.setAddress("Ames, IA 50011");
        isu.setPhone("515-294-4111");
        isu.setWebsite("http://www.iastate.edu/");
        isu.setDetails("Iowa State University of Science and Technology is a public flagship land-grant and space-grant research university located in Ames, Iowa, United States. Iowa State is classified as a Research University with very high research activity (RU/VH) by the Carnegie Foundation for the Advancement of Teaching. Iowa State is also a member of the prestigious American Association of Universities (AAU).");
        isu.setImageResourceId(R.drawable.isulogo);

        Model gardens = new Model();
        gardens.setId("2");
        gardens.setName("Reiman Gardens");
        gardens.setAddress("1407 University Blvd, Ames, IA 50011");
        gardens.setPhone("515-294-2710");
        gardens.setWebsite("https://www.reimangardens.com/");
        gardens.setDetails("Reiman Gardens consists of a dozen distinct garden areas, an indoor conservatory and an indoor butterfly \"wing\", butterfly emergence cases, a gift shop, and several supporting greenhouses.");
        gardens.setImageResourceId(R.drawable.reimangardens);

        Model artMuseum = new Model();
        artMuseum.setId("3");
        artMuseum.setName("Brunnier Art Museum");
        artMuseum.setAddress("290 Scheman Bldg, Ames, IA");
        artMuseum.setPhone("515-294-4442");
        artMuseum.setWebsite("http://www.museums.iastate.edu/");
        artMuseum.setDetails("The Brunnier Art Museum, Iowa’s only accredited museum emphasizing a decorative arts collection, is one of the nation's few museums located within a performing arts and conference complex, the Iowa State Center. Founded in 1975, the museum is named after its benefactors, Iowa State alumnus Henry J. Brunnier and his wife Ann. The decorative arts collection they donated, called the Brunnier Collection, is extensive, consisting of ceramics, glass, dolls, ivory, jade, and enameled metals.");
        artMuseum.setImageResourceId(R.drawable.artmuseum);

        models.add(isu);
        models.add(gardens);
        models.add(artMuseum);
    }

    public void loadIowaCityData() {
        models = new ArrayList<Model>();

        Model uofi = new Model();
        uofi.setId("1");
        uofi.setName("University of Iowa");
        uofi.setAddress("Iowa City, IA 52242");
        uofi.setPhone("319-335-3500");
        uofi.setWebsite("http://www.uiowa.edu/");
        uofi.setDetails("The Iowa campus spans 1,700 acres centered along the banks of the Iowa River and includes the University of Iowa Hospitals and Clinics, named one of \\\"America’s Best Hospitals\\\" for the 25th year in a row. The university was the original developer of the Master of Fine Arts degree and it operates the world-renowned Iowa Writers’ Workshop.");
        uofi.setImageResourceId(R.drawable.uofi);

        Model fossils = new Model();
        fossils.setId("2");
        fossils.setName("Devonian Fossil Gorge");
        fossils.setAddress("Iowa City, IA");
        fossils.setPhone("515-867-5309");
        fossils.setWebsite("http://www.mvr.usace.army.mil/Missions/Recreation/Coralville-Lake/Recreation/Devonian-Fossil-Gorge/");
        fossils.setDetails("Imagine walking on a 375 million year old ocean floor! This glimpse into Iowa's geologic past was first exposed during the Floods of 1993 at Coralville Lake, then again more were exposed during the Floods of 2008. The fossils and limestone bedrock of the Devonian Fossil Gorge date back almost 200 million years before the dinosaurs!");
        fossils.setImageResourceId(R.drawable.fossils);

        Model orchard = new Model();
        orchard.setId("3");
        orchard.setName("Wilson Orchard");
        orchard.setAddress("2924 Orchard Lane NE, Iowa City, IA 52240");
        orchard.setPhone("319-354-5651");
        orchard.setWebsite("http://www.wilsonsorchard.com/");
        orchard.setDetails("Wilson’s is part nature park, part orchard. Surrounded by old oak forest, the farm occupies both sides of a valley with Rapid Creek winding through the land. Joyce and Robert ‘Chug’ Wilson first fell in love with this ground in 1980 and set to work building an orchard of apples that tasted like apples should. They planted over 120 varieties over the years, all chosen based on one simple criterion: great taste.");
        orchard.setImageResourceId(R.drawable.orchard);

        models.add(uofi);
        models.add(fossils);
        models.add(orchard);
    }

    public void loadDubuqueData(){
        models = new ArrayList<Model>();

        Model aquarium = new Model();
        aquarium.setId("1");
        aquarium.setName("National Mississippi River Museum & Aquarium");
        aquarium.setAddress("350 East Third Street, Port of Dubuque, Dubuque, IA 52001");
        aquarium.setPhone("800-226-3369");
        aquarium.setWebsite("http://www.rivermuseum.com/");
        aquarium.setDetails("The National Mississippi River Museum & Aquarium is home to museum exhibits on the culture and history of America's rivers. The campus also includes over a dozen aquariums featuring wildlife representative of that found in the Mississippi River and the Gulf of Mexico and other river systems and deltas, including giant catfish, sturgeon, ducks, frogs, turtles, rays, octopus, river otters, and more. There are also outdoor exhibits, featuring river otters, a marsh, and large artifacts, such as steam boilers, boats, a blacksmith shop, a stream, and raptor aviaries including bald eagles.");
        aquarium.setImageResourceId(R.drawable.aquarium);

        Model elevator = new Model();
        elevator.setId("2");
        elevator.setName("Fenelon Place Elevator Co.");
        elevator.setAddress("512 Fenelon Place, Cable Car Square, Dubuque, IA 52001");
        elevator.setPhone("563-582-6496");
        elevator.setWebsite("http://www.fenelonplaceelevator.com/");
        elevator.setDetails("The Fenelon Place Elevator is a 3 ft (914 mm) narrow gauge funicular railway located in Dubuque, Iowa. Also known as the Fourth Street Elevator, it is claimed to be the shortest and steepest railroad in the world (although several other funiculars also make this claim). It is listed in the National Register of Historic Places.");
        elevator.setImageResourceId(R.drawable.elevator);

        Model park = new Model();
        park.setId("3");
        park.setName("Eagle Point Park");
        park.setAddress("Shiras Avenue, Dubuque, IA 52004");
        park.setPhone("563-589-4263");
        park.setWebsite("http://www.cityofdubuque.org/573/Eagle-Point-Park");
        park.setDetails("The Riverwalk, situated along the edge of the bluff, has views of the Mississippi, the Lock and Dam, the city of Dubuque, and Grant County, Wisconsin. The park offers tennis courts, horseshoe pits, playground equipment, a band shell with free music concerts, and a small wading pool for young children.");
        park.setImageResourceId(R.drawable.park);

        models.add(aquarium);
        models.add(elevator);
        models.add(park);
    }

}
