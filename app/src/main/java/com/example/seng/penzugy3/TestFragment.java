package com.example.seng.penzugy3;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class TestFragment extends Fragment{
    View testView;
    ImageButton imageButtonDaily, imageButtonHouse,imageButtonEntertainment, imageButtonCelebrations;
    ImageButton imageButtonTravel, imageButtonCredit, imageButtonIncome;
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        testView = inflater.inflate(R.layout.button_test_layout, container, false);

        imageButtonDaily = testView.findViewById(R.id.imageButtonDaily);
        imageButtonHouse = testView.findViewById(R.id.imageButtonHouse);
        imageButtonCelebrations = testView.findViewById(R.id.imageButtonCelebrations);
        imageButtonEntertainment = testView.findViewById(R.id.imageButtonEntertainment);
        imageButtonTravel = testView.findViewById(R.id.imageButtonTravel);
        imageButtonCredit = testView.findViewById(R.id.imageButtonCredit);
        imageButtonIncome = testView.findViewById(R.id.imageButtonIncome);

        // Adding new  button programmatically -----------------------------------------------------
        /*
        final ImageButton imageButton2 = new ImageButton(this.getActivity());
        imageButton2.setImageResource(R.drawable.ic_menu_gallery);
        linearLayout = testView.findViewById(R.id.linLayButton);
        imageButton2.setLayoutParams(new LinearLayout.LayoutParams(120,120));
        imageButton2.setBackgroundColor(Color.TRANSPARENT);
        imageButton2.setImageResource(R.drawable.ic_huf60);
        linearLayout.addView(imageButton2);

        imageButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imageButton2.setImageResource(R.drawable.ic_travel_inv60);
            }
        });
        */
        // End adding button ------------------------------------------------------------------------

        imageButtonDaily.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonDaily.setImageResource(R.drawable.ic_shopping_cart_inv60);
            }
        });

        imageButtonHouse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonHouse.setImageResource(R.drawable.ic_house_inv60);
            }
        });

        imageButtonCelebrations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonCelebrations.setImageResource(R.drawable.ic_celebrations_inv60);
            }
        });

        imageButtonEntertainment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonEntertainment.setImageResource(R.drawable.ic_entertainment_inv60);
            }
        });

        imageButtonTravel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonTravel.setImageResource(R.drawable.ic_travel_inv60);
            }
        });

        imageButtonCredit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonCredit.setImageResource(R.drawable.ic_credit_inv60);
            }
        });

        imageButtonIncome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setDefaultButtonImages();
                imageButtonIncome.setImageResource(R.drawable.ic_huf_inv60);
            }
        });

        return testView;
    }

    private void setDefaultButtonImages(){
        imageButtonDaily.setImageResource(R.drawable.ic_shopping_cart60);
        imageButtonHouse.setImageResource(R.drawable.ic_house60);
        imageButtonEntertainment.setImageResource(R.drawable.ic_entertainment60);
        imageButtonCelebrations.setImageResource(R.drawable.ic_celebrations60);
        imageButtonTravel.setImageResource(R.drawable.ic_travel60);
        imageButtonCredit.setImageResource(R.drawable.ic_credit60);
        imageButtonIncome.setImageResource(R.drawable.ic_huf60);
    }

}
