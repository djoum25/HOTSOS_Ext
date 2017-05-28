package com.teamtreehouselearningjulienlaurent.hotsos_ext;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCompletedOrdersFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CompletedOrders extends Fragment {

    private OnCompletedOrdersFragmentInteractionListener mListener;

    public CompletedOrders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_completed_orders, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCompletedOrderFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCompletedOrdersFragmentInteractionListener) {
            mListener = (OnCompletedOrdersFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                + " must implement OnCompletedOrdersFragmentInteractionListener");
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
    public interface OnCompletedOrdersFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCompletedOrderFragmentInteraction(Uri uri);
    }
}
