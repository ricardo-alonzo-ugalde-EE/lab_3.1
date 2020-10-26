package org.electricuniverse.lab_31;



import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Part 3: see comments below
 */
public class Fragment2 extends Fragment
{
    private FragmentTracker ft;
    private View v;
    public static final String fragmentTitle= "Address and Such";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        ft.fragmentVisible(fragmentTitle);
        v = inflater.inflate(R.layout.fragment_2,container, false);
        Button frag2_nextBtn= v.findViewById(R.id.frag2_nextBtn);
        frag2_nextBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ft.goNext();
            }
        });

        Button frag2_backBtn= v.findViewById(R.id.frag2_backBtn);
        frag2_backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ft.goBack();
            }
        });


        return v;
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        try
        {
            ft = (FragmentTracker) context;
        }
        catch (ClassCastException ex)
        {
            throw new ClassCastException(context.toString() + "must implement event");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        EditText city = v.findViewById(R.id.frag2_city); // edit text where? Main or fragment? fragment per convo with kevin
        EditText  zips= v.findViewById(R.id.frag2_zip); // edit text where? main or fragment fragment per convo with kevin

        ft.saveCityAndZip(city.getText().toString(),zips.getText().toString());
        v=null;
    }


}