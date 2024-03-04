package com.example.notes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.Notes;
import com.example.notes.R;
import com.example.notes.database.DBManager;


public class AddNotteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_notte, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DBManager dbManager = new DBManager(getContext());
        dbManager.openDb();
        EditText title = view.findViewById(R.id.editTextNoteAddTitle);
        EditText text = view.findViewById(R.id.editTextNoteAddText);
        Button save = view.findViewById(R.id.buttonSaveNote);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notes notes = new Notes(title.getText().toString(),text.getText().toString());
                if(notes.getTitle().equals("") | notes.getText().equals("")){
                    Toast.makeText(getContext(),"Заполните поля",Toast.LENGTH_SHORT).show();
                    return;
                }
                dbManager.addNotes(notes);
                dbManager.closeDb();
                getParentFragmentManager().popBackStack();
            }
        });
    }
}