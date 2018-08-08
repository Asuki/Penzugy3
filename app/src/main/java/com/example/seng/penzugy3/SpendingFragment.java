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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SpendingFragment extends Fragment{
    View spendingView;
    Spinner spinnerCategories, spinnerUsers;
    Button btnV1, btnV2, btnV3, btnV4, btnV5, btnV6, btnV7, btnV8, btnV9, btnV0, btnV00, btnV000;
    Button btnBckSpc, btnEnter;
    EditText editTextSpending;

    private static final String TAG = "SpendingFragment";
    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        spendingView = inflater.inflate(R.layout.spending_layout, container, false);

        databaseHelper = new DatabaseHelper(getActivity());
        spinnerCategories = spendingView.findViewById(R.id.spinnerSpendingCategories);
        spinnerUsers = spendingView.findViewById(R.id.spinnerSpendingUsers);
        editTextSpending = spendingView.findViewById(R.id.editTextSpendingAmount);

        btnV1 = spendingView.findViewById(R.id.buttonV1);
        btnV2 = spendingView.findViewById(R.id.buttonV2);
        btnV3 = spendingView.findViewById(R.id.buttonV3);
        btnV4 = spendingView.findViewById(R.id.buttonV4);
        btnV5 = spendingView.findViewById(R.id.buttonV5);
        btnV6 = spendingView.findViewById(R.id.buttonV6);
        btnV7 = spendingView.findViewById(R.id.buttonV7);
        btnV8 = spendingView.findViewById(R.id.buttonV8);
        btnV9 = spendingView.findViewById(R.id.buttonV9);
        btnV000 = spendingView.findViewById(R.id.buttonV000);
        btnV00 = spendingView.findViewById(R.id.buttonV00);
        btnV0 = spendingView.findViewById(R.id.buttonV0);
        btnBckSpc = spendingView.findViewById(R.id.buttonBckSpc);
        btnEnter = spendingView.findViewById(R.id.buttonEnter);

        btnV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "1");
            }
        });
        btnV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "2");
            }
        });
        btnV3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "3");
            }
        });
        btnV4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "4");
            }
        });
        btnV5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "5");
            }
        });
        btnV6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "6");
            }
        });
        btnV7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "7");
            }
        });
        btnV8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "8");
            }
        });
        btnV9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "9");
            }
        });
        btnV000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "000");
            }
        });
        btnV00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "00");
            }
        });
        btnV0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSpending.setText(editTextSpending.getText() + "0");
            }
        });
        btnBckSpc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSpending.getText().length() > 0)
                    editTextSpending.setText(editTextSpending.getText().subSequence(0, editTextSpending.getText().length() - 1));
            }
        });

        //try {
            fillSpinner();
        /*}
        catch (Exception e) {
        }*/
        return spendingView;
    }

    private void fillSpinner(){
        Cursor data = databaseHelper.getCategories();
        ArrayList<String> arrayListCategories = new ArrayList<>();

        while (data.moveToNext()){
            arrayListCategories.add(data.getString(databaseHelper.NAME_POSITION) + "");
        }

        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayListCategories);
        spinnerCategories.setAdapter(adapter);


        data = databaseHelper.getUsers();
        ArrayList<String> arrayListUsers = new ArrayList<>();

        while (data.moveToNext()){
            arrayListUsers.add(data.getString(databaseHelper.NAME_POSITION) + "");
        }

        ArrayAdapter adapterUsers = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayListUsers);
        spinnerUsers.setAdapter(adapterUsers);
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
