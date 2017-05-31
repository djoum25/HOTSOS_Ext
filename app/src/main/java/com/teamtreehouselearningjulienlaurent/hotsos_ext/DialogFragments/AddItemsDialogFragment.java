package com.teamtreehouselearningjulienlaurent.hotsos_ext.DialogFragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamtreehouselearningjulienlaurent.hotsos_ext.OrderToComplete;
import com.teamtreehouselearningjulienlaurent.hotsos_ext.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by djoum on 5/28/17.
 */

public class AddItemsDialogFragment extends DialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference;
    MultiAutoCompleteTextView itemsRequested;
    Button cancelItemRequested, addItemRequested;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ArrayAdapter<String> spinnerAdapter;
    StringBuilder mStringBuilder = new StringBuilder();
    private String roomNumber;
    private onAddItemsInteractionListener listener;
    private Spinner itemsRequestedSpinner, itemRequestedQuanttiy;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference("Hotsos");
        Log.d("TEST: ", "ON CREATE IS CALLED");
        mDatabaseReference.child(getString(R.string.listOfAvaillableItems)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    items.add(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, items);
        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, items);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.add_items_dialog, null);
        itemsRequested = (MultiAutoCompleteTextView) view.findViewById(R.id.itemsRequested);
        itemsRequested.setAdapter(itemsAdapter);
        itemsRequested.setThreshold(1);
        itemsRequestedSpinner = (Spinner) view.findViewById(R.id.itemsRequestedSpinner);
        itemRequestedQuanttiy = (Spinner) view.findViewById(R.id.itemsRequestedQuantitySpinner);
        itemRequestedQuanttiy.setOnItemSelectedListener(this);
        itemsRequestedSpinner.setOnItemSelectedListener(this);
        itemsRequested.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        cancelItemRequested = (Button) view.findViewById(R.id.cancelItemRequestButton);
        addItemRequested = (Button) view.findViewById(R.id.addItemRequestedButton);
        cancelItemRequested.setOnClickListener(this);
        addItemRequested.setOnClickListener(this);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view).setTitle(getRoomNumber());
        return builder.create();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addItemRequestedButton:
                ArrayList<String> translate = new ArrayList<>
                    (Arrays.asList(itemsRequested.getText().toString().split(",")));

                for (String s : translate) {
                    Log.d("this is translate", s);
                }
                /*
                review before next run
                 */
                if (listener != null) {
                    //OrderToComplete orderToComplete = new OrderToComplete(roomNumber);
                    //orderToComplete.convertItemsToList(itemsRequested.getText().toString());
                    //listener.onAddItemsInteraction(orderToComplete);
                }
                Toast.makeText(getActivity(), "Item requested button press", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.cancelItemRequestButton:
                dismiss();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_SHORT).show();
        mStringBuilder.append(parent.getItemAtPosition(position)).append(" ");
        itemsRequested.setText(mStringBuilder);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onAddItemsInteractionListener) {
            listener = (onAddItemsInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface onAddItemsInteractionListener {
        void onAddItemsInteraction(OrderToComplete order);
    }


}
