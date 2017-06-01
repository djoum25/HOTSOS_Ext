package com.teamtreehouselearningjulienlaurent.hotsos_ext.DialogFragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamtreehouselearningjulienlaurent.hotsos_ext.OrderToComplete;
import com.teamtreehouselearningjulienlaurent.hotsos_ext.R;

import java.util.ArrayList;


public class AddItemsDialogFragment extends DialogFragment implements AdapterView.OnItemClickListener {
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference;
    MultiAutoCompleteTextView itemsRequested;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ArrayAdapter<String> listViewAdapter;
    private String roomNumber;
    private onAddItemsInteractionListener listener;
    private ListView multiselectList;
    private ArrayList<String> listOfSelectedItems;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new ArrayList<>();
        listOfSelectedItems = new ArrayList<>();
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
        itemsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item, items);
        listViewAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, items);
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
        itemsRequested.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        multiselectList = (ListView) view.findViewById(R.id.my_list_of_item);
        multiselectList.setOnItemClickListener(this);
        multiselectList.setAdapter(listViewAdapter);

        builder.setView(view).setTitle(getRoomNumber());

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (String s : listOfSelectedItems) {
                    Log.d("LIST OF SELECTED ITEM: ", s);
                }
                /*
                review before next run
                 */
                if (listener != null) {
                    //OrderToComplete orderToComplete = new OrderToComplete(roomNumber);
                    //orderToComplete.convertItemsToList(itemsRequested.getText().toString());
                    //listener.onAddItemsInteraction(orderToComplete);
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        return builder.create();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selected = (String) multiselectList.getItemAtPosition(position);
        Toast.makeText(getContext(), selected, Toast.LENGTH_SHORT).show();
        itemsRequested.append(selected + "\n");
        listOfSelectedItems.add(selected);
    }

    public interface onAddItemsInteractionListener {
        void onAddItemsInteraction(OrderToComplete order);
    }


}
