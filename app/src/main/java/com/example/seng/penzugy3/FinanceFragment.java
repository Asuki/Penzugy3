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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FinanceFragment extends Fragment{
    View financeListView;
    ListView listView;

    String userName;
    int userID;

    private static final String TAG = "EditUserFragment";
    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        financeListView = inflater.inflate(R.layout.finance_layout, container, false);

        databaseHelper = new DatabaseHelper(getActivity());
        listView = financeListView.findViewById(R.id.financeListView);


        populateListView();
        return financeListView;
    }

    private void populateListView(){
        Log.d(TAG, "displaying user list");

        //Cursor data = databaseHelper.getFullTable(databaseHelper.FINANCE_TABLE);
        Cursor data = databaseHelper.getFinance();
        ArrayList<FinanceElements> arrayList = new ArrayList<>();
        Log.d(TAG, "Adding elements");

        while(data.moveToNext()){
            FinanceElements financeElements = new FinanceElements(data.getString(1), data.getString(2), data.getInt(3) + "", data.getString(4));
            arrayList.add(financeElements);
            Log.d(TAG, "Adding the next element: " + data.getString(1) + "|" + data.getString(2) + "|" +  data.getInt(3)  + "|" +  data.getString(4));
        }

        ListAdapter adapter = new FinanceAdapter(this.getContext(), R.layout.finance_adapter, arrayList);
        listView.setAdapter(adapter);

    }
    private void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.
                LENGTH_SHORT).show();
    }
}
