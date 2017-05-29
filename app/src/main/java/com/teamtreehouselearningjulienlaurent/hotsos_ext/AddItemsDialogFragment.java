package com.teamtreehouselearningjulienlaurent.hotsos_ext;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by djoum on 5/28/17.
 */

public class AddItemsDialogFragment extends DialogFragment {
    EditText itemsRequested;
    Button cancelItemRequested, addItemRequested;
    private String roomNumber;

    public AddItemsDialogFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.add_items_dialog, null);
        itemsRequested = (EditText) view.findViewById(R.id.itemsRequested);
        cancelItemRequested = (Button) view.findViewById(R.id.cancelItemRequestButton);
        addItemRequested = (Button) view.findViewById(R.id.addItemRequestedButton);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        return builder.create();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
