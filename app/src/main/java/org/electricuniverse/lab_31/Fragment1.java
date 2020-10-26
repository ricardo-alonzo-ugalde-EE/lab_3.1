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
public class Fragment1 extends Fragment
{
    private FragmentTracker ft;
    private View v;
    public static final String fragmentTitle= "Personal Info";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
       ft.fragmentVisible(fragmentTitle);
       v = inflater.inflate(R.layout.fragment_1,container, false);
       Button b_next = v.findViewById(R.id.next_button);
       b_next.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               ft.goNext();
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
        EditText uname = v.findViewById(R.id.frag1_Name); // edit text where? Main or fragment? fragment per convo with kevin
        EditText lname = v.findViewById(R.id.frag1_LastName); // edit text where? main or fragment fragment per convo with kevin
        ft.saveNameAndLastName(uname.getText().toString(),lname.getText().toString());
        v=null;
    }


}