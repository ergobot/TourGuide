package com.example.android.tourguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tourguide.model.Model;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Model model;

    ImageView imageView;
    TextView title;

    // middle buttons
    Button leftButton;
    Button middleButton;
    Button rightButton;

    TextView address;
    TextView phone;
    TextView website;
    FrameLayout detailsHeader;
    TextView details;

    public DetailFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // no args
    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.headerimage);
        title = (TextView) rootView.findViewById(R.id.detail_title);

        // middle buttons
        leftButton = (Button)rootView.findViewById(R.id.mainbuttonone);
        middleButton = (Button)rootView.findViewById(R.id.mainbuttontwo);
        rightButton = (Button)rootView.findViewById(R.id.mainbuttonthree);

        address = (TextView) rootView.findViewById(R.id.address_row);
        phone = (TextView) rootView.findViewById(R.id.phone_row);
        website = (TextView) rootView.findViewById(R.id.website_row);
        detailsHeader = (FrameLayout) rootView.findViewById(R.id.detail_header_container);
        details = (TextView) rootView.findViewById(R.id.detail_content);

        model = ((NavigationActivity)getActivity()).getSelectedModel();
        imageView.setImageResource(model.getImageResourceId());
        title.setText(model.getName());

        Drawable[] drawables = leftButton.getCompoundDrawables();
        Drawable wrapDrawable = DrawableCompat.wrap(drawables[1]);
        DrawableCompat.setTint(wrapDrawable, getResources().getColor(R.color.colorPrimary));
        drawables = middleButton.getCompoundDrawables();
        wrapDrawable = DrawableCompat.wrap(drawables[1]);
        DrawableCompat.setTint(wrapDrawable, getResources().getColor(R.color.colorPrimary));
        drawables = rightButton.getCompoundDrawables();
        wrapDrawable = DrawableCompat.wrap(drawables[1]);
        DrawableCompat.setTint(wrapDrawable, getResources().getColor(R.color.colorPrimary));



        middleButton.setEnabled(false);




        if(model.getAddress() != null && !model.getAddress().isEmpty()){
            address.setVisibility(View.VISIBLE);
            address.setText(model.getAddress());
        }else{
            address.setVisibility(View.GONE);
        }
        if(model.getPhone() != null && !model.getPhone().isEmpty()){
            leftButton.setEnabled(true);
            leftButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + model.getPhone()));
                    startActivity(intent);
                }
            });
            phone.setVisibility(View.VISIBLE);
            phone.setText(model.getPhone());
        }else{
            leftButton.setEnabled(false);
            phone.setVisibility(View.GONE);
        }
        if(model.getWebsite() != null && !model.getWebsite().isEmpty()){
            rightButton.setEnabled(true);
            rightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = model.getWebsite();
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(url));
                    startActivity(i);
                }
            });
            website.setVisibility(View.VISIBLE);
            website.setText(model.getWebsite());
        }else{
            rightButton.setEnabled(false);
            website.setVisibility(View.GONE);
        }
        if(model.getDetails() != null && !model.getDetails().isEmpty()){
            detailsHeader.setVisibility(View.VISIBLE);
            details.setVisibility(View.VISIBLE);
            details.setText(model.getDetails());
        }else{
            detailsHeader.setVisibility(View.GONE);
            details.setVisibility(View.GONE);
        }



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
}
