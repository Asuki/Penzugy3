package com.example.seng.penzugy3;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminFragment extends Fragment{
    View adminView;
    Button btnReset;

    private static final String TAG = "EditUserFragment";
    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        adminView = inflater.inflate(R.layout.admin_layout, container, false);

        databaseHelper = new DatabaseHelper(getActivity());

        btnReset = adminView.findViewById(R.id.buttonReset);
        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                databaseHelper.rebuild();
                Log.d(TAG, "-------- REBUILD -----------");
                toastMessage("Database rebuild has been run successful.");
            }
        });

        return adminView;
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
