package com.teamtreehouselearningjulienlaurent.hotsos_ext.DialogFragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamtreehouselearningjulienlaurent.hotsos_ext.R;
import com.teamtreehouselearningjulienlaurent.hotsos_ext.utility.RoomsUtility;

import java.util.ArrayList;


public class RoomDialog extends DialogFragment implements View.OnClickListener {
    String inputRoomValue;
    AutoCompleteTextView inputAutocomplete;
    RadioGroup buildingRadioGroup;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference, tresortRoomsRef, sorentoRoomsRef, chateauRoomsRef, versailleRoomsRef;
    ArrayList<String> tresorRoomList, sorentoRoomList, chateauRoomList, versaillesRoomList;
    private String whichBuilding;
    private onRoomDialogInteractionListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tresorRoomList = new ArrayList<>();
        sorentoRoomList = new ArrayList<>();
        chateauRoomList = new ArrayList<>();
        versaillesRoomList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference("Hotsos");
        tresortRoomsRef = mDatabaseReference.child(getString(R.string.rooms)).child(getString(R.string.firebaseKeyTresor));
        sorentoRoomsRef = mDatabaseReference.child(getString(R.string.rooms)).child(getString(R.string.firebaseKeySorento));
        chateauRoomsRef = mDatabaseReference.child(getString(R.string.rooms)).child(getString(R.string.firebaseKeyChateau));
        versailleRoomsRef = mDatabaseReference.child(getString(R.string.rooms)).child(getString(R.string.firebaseKeyVersailles));
        tresortRoomsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    tresorRoomList.add(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        sorentoRoomsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    sorentoRoomList.add(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        chateauRoomsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    chateauRoomList.add(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        versailleRoomsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    versaillesRoomList.add(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.room_number_dialog, null);
        inputAutocomplete = (AutoCompleteTextView) view.findViewById(R.id.room);
        inputAutocomplete.setImeOptions(EditorInfo.IME_ACTION_DONE);
        buildingRadioGroup = (RadioGroup) view.findViewById(R.id.buildings);
        RadioButton tresor = (RadioButton) view.findViewById(R.id.tresor);
        tresor.setOnClickListener(this);
        RadioButton sorrento = (RadioButton) view.findViewById(R.id.sorento);
        sorrento.setOnClickListener(this);
        RadioButton chateau = (RadioButton) view.findViewById(R.id.chateau);
        chateau.setOnClickListener(this);
        RadioButton versailles = (RadioButton) view.findViewById(R.id.versaille);
        versailles.setOnClickListener(this);
        //buildingRadioGroup.setOnCheckedChangeListener(this);
        Button addRoom = (Button) view.findViewById(R.id.button_add_room_add);
        Button cancelRoom = (Button) view.findViewById(R.id.button_add_room_cancel);
        cancelRoom.setOnClickListener(this);
        addRoom.setOnClickListener(this);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_room_add:
                inputRoomValue = inputAutocomplete.getText().toString();
                if (inputRoomValue.length() > 0 && whichBuilding != null) {
                    if (listener != null) {
                        try {
                            listener.onRoomDialogInteraction(RoomsUtility.roomForOrder(whichBuilding, inputRoomValue));
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
        ArrayList<String> rooms = null;
        int id = buildingRadioGroup.getCheckedRadioButtonId();
        switch (id) {
            case R.id.tresor:
                whichBuilding = getString(R.string.tresor);
                rooms = tresorRoomList;
                break;
            case R.id.sorento:
                whichBuilding = getString(R.string.sorento);
                rooms = sorentoRoomList;
                break;
            case R.id.chateau:
                whichBuilding = getString(R.string.chateau);
                rooms = chateauRoomList;
                break;
            case R.id.versaille:
                whichBuilding = getString(R.string.versailles);
                rooms = versaillesRoomList;
                break;
            default:
                Toast.makeText(getActivity(), "Click a building", Toast.LENGTH_SHORT).show();
        }

        if (rooms != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, rooms);
            inputAutocomplete.setThreshold(1);
            inputAutocomplete.setAdapter(adapter);
        }
        inputAutocomplete.setVisibility(View.VISIBLE);
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

    public interface onRoomDialogInteractionListener {
        void onRoomDialogInteraction(String room);
    }
}
