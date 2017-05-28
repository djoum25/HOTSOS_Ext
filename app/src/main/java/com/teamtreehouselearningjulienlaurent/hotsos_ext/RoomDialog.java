package com.teamtreehouselearningjulienlaurent.hotsos_ext;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class RoomDialog extends DialogFragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    String inputRoomValue;
    AutoCompleteTextView inputAutocomplete;
    private RadioGroup buildingRadioGroup;
    private String whichBuilding;
    private Button addRoom, cancelRoom;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ArrayList<String> rooms = new ArrayList<>();

        for (int i = 301; i <= 1090; i++) {
            rooms.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
            android.R.layout.simple_dropdown_item_1line, rooms);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.room_number_dialog, null);
        inputAutocomplete = (AutoCompleteTextView) view.findViewById(R.id.room);
        inputAutocomplete.setImeOptions(EditorInfo.IME_ACTION_DONE);
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
    };
    
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_add_room_add:
                inputRoomValue = inputAutocomplete.getText().toString();
                if (inputRoomValue.length() > 0 && whichBuilding != null){
                    try {
                        Toast.makeText(getActivity(), "You choose " + roomForOrder(inputRoomValue, whichBuilding), Toast.LENGTH_SHORT).show();
                        dismiss();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
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
     * @param inputRoomValue to check the value of the room depend on
     * @param whicBuilding that user chooses and
     * @return the right room number to send to the other fragment
     * @throws Exception in case room do not match
     */
    public String roomForOrder(String inputRoomValue, String whicBuilding) throws Exception {
        int roomNumberInteger = Integer.parseInt(inputRoomValue);
        StringBuilder correctRoom = new StringBuilder();
        if (whicBuilding.equalsIgnoreCase("tresor") && roomNumberInteger >= 701 && roomNumberInteger < 1001){
            correctRoom.append("20-").append(inputRoomValue);
        }else if (whicBuilding.equalsIgnoreCase("tresor") && roomNumberInteger >= 1001 && roomNumberInteger <= 3702){
            correctRoom.append("2-").append(inputRoomValue);
        }else if (whicBuilding.equalsIgnoreCase("Sorento") && roomNumberInteger >= 301 && roomNumberInteger < 1001){
            correctRoom.append("30-").append(inputRoomValue);
        }else if (whicBuilding.equalsIgnoreCase("Sorento") && roomNumberInteger >= 1001 && roomNumberInteger <= 1919){
            correctRoom.append("3-").append(inputRoomValue);
        }else {
            throw new Exception("incorrect room");
        }
        return correctRoom.toString();
    }
}
