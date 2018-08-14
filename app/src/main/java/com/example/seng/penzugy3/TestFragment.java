package com.example.seng.penzugy3;

import android.app.Fragment;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestFragment extends Fragment{
    View testView;
    ImageButton imageButton;
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        testView = inflater.inflate(R.layout.button_test_layout, container, false);

        imageButton = testView.findViewById(R.id.imageButton);

        // Adding new  button programmatically -----------------------------------------------------
        final ImageButton imageButton2 = new ImageButton(this.getActivity());
        imageButton2.setImageResource(R.drawable.ic_menu_gallery);
        linearLayout = testView.findViewById(R.id.linLayButton);
        imageButton2.setLayoutParams(new LinearLayout.LayoutParams(120,120));
        imageButton2.setBackgroundColor(Color.TRANSPARENT);
        imageButton2.setImageResource(R.drawable.ic_huf60);
        linearLayout.addView(imageButton2);
        // End adding button ------------------------------------------------------------------------

        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imageButton.setImageResource(R.drawable.ic_huf_inv60);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imageButton2.setImageResource(R.drawable.ic_huf_inv60);
            }
        });

        return testView;
    }

}
