package com.example.chenjutsu.calendar1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
  interface
 * to handle interaction events.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public Button back;
    public Button save;
    public EditText text;
    public List<EVENT> eventListFromDB;

    //private OnFragmentInteractionListener mListener;

    public CreateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateFragment newInstance(String param1, String param2) {
        CreateFragment fragment = new CreateFragment();
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
        View view;
        view  = inflater.inflate(R.layout.fragment_create, container, false);
        back = (Button)view.findViewById(R.id.button3);
        save = (Button)view.findViewById(R.id.button4);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        //save button
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                text = (EditText)getActivity().findViewById(R.id.input);


                saveEvent();
            }
        });

        //back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getFragmentManager().popBackStack();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, calenderFragment.newInstance(null, null))
                        .addToBackStack(null)
                        .commit();


            }
        });






    }
    public void saveEvent(){
        Random rand = new Random();
        calenderActivity.time = DateFormat.getDateTimeInstance().format(new Date());
        EVENT newEvent = new EVENT(rand.nextLong(),calenderActivity.yr,calenderActivity.mon,calenderActivity.day,text.getText().toString(),
                calenderActivity.time, true);
        calenderActivity.eventDao.insert(newEvent);
        Long date = calenderActivity.calendarView.getDate();

        closeReopenDatabase();
        eventListFromDB = calenderActivity.eventDao.queryBuilder().where(EVENTDao.Properties.Year.eq(calenderActivity.yr),
                EVENTDao.Properties.Month.eq(calenderActivity.mon),EVENTDao.Properties.Day.eq(calenderActivity.day),
                EVENTDao.Properties.Date.eq(calenderActivity.time)).list();

        if(eventListFromDB != null) {

            Toast.makeText(getActivity(),"save", Toast.LENGTH_LONG).show();
        }


    }

    public void closeDatabase(){
        calenderActivity.daoSession.clear();
        calenderActivity.eventCalendarDB.close();
        calenderActivity.eventCalendarDBHelper.close();
    }


    public void closeReopenDatabase(){
        closeDatabase();
        calenderActivity.eventCalendarDBHelper = new DaoMaster.DevOpenHelper(getActivity(),"Calendar1.sqlite",null);
        calenderActivity.eventCalendarDB = calenderActivity.eventCalendarDBHelper.getWritableDatabase();
        calenderActivity.daoMaster = new DaoMaster(calenderActivity.eventCalendarDB);
        calenderActivity.daoMaster.createAllTables(calenderActivity.eventCalendarDB, true);
        calenderActivity.daoSession = calenderActivity.daoMaster.newSession();
        calenderActivity.eventDao = calenderActivity.daoSession.getEVENTDao();
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
