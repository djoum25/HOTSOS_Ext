package com.teamtreehouselearningjulienlaurent.hotsos_ext;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class RoomDialog extends DialogFragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    String inputRoomValue;
    AutoCompleteTextView inputAutocomplete;
    private RadioGroup buildingRadioGroup;
    private String whichBuilding;
    private Button addRoom, cancelRoom;
    private onRoomDialogInteractionListener listener;
    private ArrayList<String> rooms;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        rooms = new ArrayList<>();
        //setRoomNumberForAutocomple(rooms);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
            android.R.layout.simple_dropdown_item_1line, rooms);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.room_number_dialog, null);
        inputAutocomplete = (AutoCompleteTextView) view.findViewById(R.id.room);
        inputAutocomplete.setImeOptions(EditorInfo.IME_ACTION_DONE);
        inputAutocomplete.setThreshold(2);
        inputAutocomplete.setAdapter(adapter);
        buildingRadioGroup = (RadioGroup) view.findViewById(R.id.buildings);
        buildingRadioGroup.setOnCheckedChangeListener(this);
        addRoom = (Button) view.findViewById(R.id.button_add_room_add);
        cancelRoom = (Button) view.findViewById(R.id.button_add_room_cancel);
        cancelRoom.setOnClickListener(this);
        addRoom.setOnClickListener(this);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.sorento:
                whichBuilding = "Sorento";
                break;
            case R.id.tresor:
                whichBuilding = "Tresor";
                break;
            case R.id.chateau:
                whichBuilding = "Chateau";
                break;
            case R.id.versaille:
                whichBuilding = "Versailles";
                break;
            default:
                whichBuilding = null;
        }
        setRoomNumberForAutocomple(rooms);
        inputAutocomplete.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_room_add:
                inputRoomValue = inputAutocomplete.getText().toString();
                if (inputRoomValue.length() > 0 && whichBuilding != null) {
                    if (listener != null) {
                        try {
                            listener.onRoomDialogInteraction(roomForOrder(whichBuilding, inputRoomValue));
                            dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "Enter room and building", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_add_room_cancel:
                Toast.makeText(getActivity(), "You press cancel", Toast.LENGTH_SHORT).show();
                getDialog().cancel();
                break;
        }
    }

    /**
     * @param room     to check the value of the room depend on
     * @param building that user chooses and
     * @return the right room number to send to the other fragment
     * @throws Exception in case room do not match
     */
    public String roomForOrder(String building, String room) throws Exception {
        //Toast.makeText(getActivity(), room.substring(1, 4), Toast.LENGTH_SHORT).show();
        int roomNumberInteger = Integer.parseInt(room);
        StringBuilder correctRoom = new StringBuilder();


        if ((building.equalsIgnoreCase("tresor") &&
            ((roomNumberInteger >= 701 && roomNumberInteger <= 717) ||
                (roomNumberInteger >= 801 && roomNumberInteger < 817) ||
                (roomNumberInteger >= 901 && roomNumberInteger < 917)))) {
            correctRoom.append("20-").append(room);
        } else if ((building.equalsIgnoreCase("tresor") &&
            ((roomNumberInteger >= 1001 && roomNumberInteger <= 1017) ||
                (roomNumberInteger >= 1101 && roomNumberInteger <= 1117) ||
                (roomNumberInteger >= 1201 && roomNumberInteger <= 1217) ||
                (roomNumberInteger >= 1401 && roomNumberInteger <= 1417) ||
                (roomNumberInteger >= 1501 && roomNumberInteger <= 1517) ||
                (roomNumberInteger >= 1601 && roomNumberInteger <= 1617) ||
                (roomNumberInteger >= 1701 && roomNumberInteger <= 1717) ||
                (roomNumberInteger >= 1801 && roomNumberInteger <= 1817) ||
                (roomNumberInteger >= 1901 && roomNumberInteger <= 1917) ||
                (roomNumberInteger >= 2001 && roomNumberInteger <= 2017) ||
                (roomNumberInteger >= 2101 && roomNumberInteger <= 2117) ||
                (roomNumberInteger >= 2201 && roomNumberInteger <= 2217) ||
                (roomNumberInteger >= 2301 && roomNumberInteger <= 2317) ||
                (roomNumberInteger >= 2401 && roomNumberInteger <= 2417) ||
                (roomNumberInteger >= 2501 && roomNumberInteger <= 2517) ||
                (roomNumberInteger >= 2401 && roomNumberInteger <= 2417) ||
                (roomNumberInteger >= 2601 && roomNumberInteger <= 2617) ||
                (roomNumberInteger >= 2701 && roomNumberInteger <= 2717) ||
                (roomNumberInteger >= 2801 && roomNumberInteger <= 2817) ||
                (roomNumberInteger >= 2901 && roomNumberInteger <= 2917) ||
                (roomNumberInteger >= 3001 && roomNumberInteger <= 3017) ||
                (roomNumberInteger >= 3101 && roomNumberInteger <= 3117) ||
                (roomNumberInteger >= 3201 && roomNumberInteger <= 3217) ||
                (roomNumberInteger >= 3301 && roomNumberInteger <= 3317) ||
                (roomNumberInteger >= 3401 && roomNumberInteger <= 3417) ||
                (roomNumberInteger >= 3501 && roomNumberInteger <= 3517) ||
                (roomNumberInteger >= 3601 && roomNumberInteger <= 3617) ||
                (roomNumberInteger >= 3701 && roomNumberInteger <= 3702)))) {
            correctRoom.append("2-").append(room);
        } else if (building.equalsIgnoreCase("Sorento") && roomNumberInteger >= 301 && roomNumberInteger < 1001) {
            correctRoom.append("30-").append(room);
        } else if (building.equalsIgnoreCase("Sorento") && roomNumberInteger >= 1001 && roomNumberInteger <= 1919) {
            correctRoom.append("3-").append(room);
        } else if (building.equalsIgnoreCase("chateau")) {
            correctRoom.append("").append(room);
        } else if (building.equalsIgnoreCase("versailles")) {
            correctRoom.append("").append(room);
        } else {
            throw new Exception("incorrect room");
        }
        return correctRoom.toString();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRoomDialogInteractionListener) {
            listener = (onRoomDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement onRoomDialogInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /*
       this is the place to set the room number array for wherever
       they are from
        */
    public void setRoomNumberForAutocomple(ArrayList arrayList) {
        for (int i = 301; i <= 1090; i++) {
            if ((i >= 701 && i <= 717) || (i >= 801 && i <= 817) || (i >= 901 && i <= 917) || (i >= 1001 && i <= 1017) ||
                (i >= 1101 && i <= 1117) || (i >= 1201 && i <= 1217) || (i >= 1401 && i <= 1417) || (i >= 1501 && i <= 1517) ||
                (i >= 1601 && i <= 1617) || (i >= 1701 && i <= 1717) || (i >= 1801 && i <= 1817) || (i >= 1901 && i <= 1917) ||
                (i >= 2001 && i <= 2017) || (i >= 2101 && i <= 2117) || (i >= 2201 && i <= 2217) || (i >= 2301 && i <= 2317) ||
                (i >= 2401 && i <= 2417) || (i >= 2501 && i <= 2517) || (i >= 2601 && i <= 2617) || (i >= 2701 && i <= 2717) ||
                (i >= 2801 && i <= 2817) || (i >= 2901 && i <= 2917) || (i >= 3001 && i <= 3017) || (i >= 3101 && i <= 3117) ||
                (i >= 3201 && i <= 3217) || (i >= 3301 && i <= 3317) || (i >= 3401 && i <= 3417) || (i >= 3501 && i <= 3517) ||
                (i >= 3601 && i <= 3617) || (i >= 3701 && i <= 3602)) {
                rooms.add(String.valueOf(i));
            }

        }
    }

    public interface onRoomDialogInteractionListener {
        void onRoomDialogInteraction(String room);
    }
}
