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
public class Fragment3 extends Fragment
{
    private FragmentTracker ft;
    private View v;
    public static final String fragmentTitle= "Details and Such";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        ft.fragmentVisible(fragmentTitle);
        v = inflater.inflate(R.layout.fragment_3,container, false);

        Button frag3_finishBtn = v.findViewById(R.id.frag3_finishBtn);
        frag3_finishBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ft.finished();
                EditText language = v.findViewById(R.id.frag3_Details1); // edit text where? Main or fragment? fragment per convo with kevin
               // EditText lang2 = v.findViewById(R.id.frag3_Details2); // edit text where? main or fragment fragment per convo with kevin

               


            }
        });

        Button back_button2 = v.findViewById(R.id.frag3_backBtn);
        back_button2.setOnClickListener(new View.OnClickListener()
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
        EditText language = v.findViewById(R.id.frag3_Details1); // edit text where? Main or fragment? fragment per convo with kevin
        EditText lang2 = v.findViewById(R.id.frag3_Details2); // edit text where? main or fragment fragment per convo with kevin

        ft.saveLanguage(language.getText().toString());
        v=null;
    }


}