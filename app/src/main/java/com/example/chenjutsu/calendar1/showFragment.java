package com.example.chenjutsu.calendar1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
   interface
 * to handle interaction events.
 * Use the {@link showFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class showFragment extends Fragment {
    public List<EVENT> eventListFromDB;
    ListView listView;
    ArrayAdapter adapter;

    private View view;
    public Button delete;

    public Button back;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    public showFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment showFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static showFragment newInstance(String param1, String param2) {
        showFragment fragment = new showFragment();
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
        calenderActivity.eventLists = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_show, container, false);
        delete = (Button)view.findViewById(R.id.button5);
        back = (Button)view.findViewById(R.id.backButton);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        eventListFromDB = calenderActivity.eventDao.queryBuilder().where(EVENTDao.Properties.Year.eq(calenderActivity.yr),
                EVENTDao.Properties.Month.eq(calenderActivity.mon),
                EVENTDao.Properties.Day.eq(calenderActivity.day)
                ).list();

        if(eventListFromDB != null){
            calenderActivity.idList = new ArrayList<>();
            for(EVENT event : eventListFromDB){
                Long id = event.getId();
                calenderActivity.idList.add(id);
                String time = event.getYear().toString() + " " + event.getMonth().toString() + " " + event.getDay().toString() + " " + event.getDate().toString();
                calenderActivity.eventLists.add( time + " " + event.getEvent().toString());
            }
        }
        if(eventListFromDB.isEmpty()){

            Toast.makeText(getActivity(), "No Event", Toast.LENGTH_LONG).show();
        }

        Log.d("CreatedActivity", "LaunchpadFragment");
        listView = (ListView)view.findViewById(R.id.list_view);


        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, calenderActivity.eventLists);

        listView.setAdapter(adapter);
        calenderActivity.adapt = adapter;
        //click on the item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                calenderActivity.ID = calenderActivity.idList.get(position);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container,DeleteFragment.newInstance(null,null))
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getFragmentManager().popBackStack();

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container,calenderFragment.newInstance(null,null))
                        .addToBackStack(null)
                        .commit();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
