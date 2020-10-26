package org.electricuniverse.lab_31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Part 4:
 * */

public class MainActivity extends AppCompatActivity implements FragmentTracker
{
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private GestureDetectorCompat mDetector;
    private final PersonInfo pi = new PersonInfo();
    private int next =1;

    public TextView textView;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        loadTheFragment(fragment1);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        textView = (TextView) findViewById(R.id.title);
        textView.setText(Fragment1.fragmentTitle);
    }


    /**
     * Part 4.4: Implementation of the dispatchTouchEvent
     * call the onTouchEvent on our gesture detector variable mDetector
     * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        mDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }


    /**
     * Part 4.5: Implementation of the FragmentTracker Methods ***************************
     * */

    /**
     *fragmentVisible Changes the title on the  activity
     */
    @Override
    public void fragmentVisible(String s)
    {

    }

    /**
     * goNext switches to the next fragment
     * */
    @Override
    public void goNext()
    {
        nextFragment(1);

    }

    /**
     * goBack switches to the previous fragmnet
     * */
    @Override
    public void goBack()
    {
        nextFragment(-1);
    }

    /**
     * Saves the first and last name in PersonalInfo Object
     * */
    @Override
    public void saveNameAndLastName(String name, String lname)
    {
        pi.setName(name);
        pi.setLastName(lname);
    }

    /**
     * saves city and zip
     * */
    @Override
    public void saveCityAndZip(String city, String zip)
    {
        pi.setCity(city);
        pi.setZIp(zip);
    }


    /**
     * saves the language
     * */
    @Override
    public void saveLanguage(String language)
    {
        pi.setLanguage(language);
    }


    /**
     * finished method is invoked when the finished button is tapped in the Fragment 3
     * this method is repsonblie for passing all of the information entered by the user to the next
     * activity via an intent object. Passing primitve object via the intent is straightforward.
     * in This lab you are asked to pass a complex object using the intent. In order to do this
     * the object time must implement the Parcelable interface which is shown below
     * */
    @Override
    public void finished()
    {
        Intent i = new Intent(this, SummaryActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("pi", pi);
        startActivity(i);
    }


    /**
     * Part 4.1: extending gesture detector.SimpleOnGestureListener
     * */
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {


            if(e1.getX() < e2.getX()) // the event has not been crated
            {
               Toast toast = Toast.makeText(MainActivity.this, "Fling Right", Toast.LENGTH_SHORT);
               toast.show();
               nextFragment(1);

            }
            else
            {
                Toast toast = Toast.makeText(MainActivity.this, "Fling left", Toast.LENGTH_SHORT);
                toast.show();
                nextFragment(-1);


            }

            return  true;
        }
    }

    private void nextFragment(Integer frag)

    {
        next = next + frag;
        if(next == 2)
        {
            loadTheFragment(fragment2);
            textView.setText(Fragment2.fragmentTitle);
        }
        else if( next == 0)
        {
            next =3;
            loadTheFragment(fragment3);
            textView.setText(Fragment3.fragmentTitle);

        }
        else if(next==3)
        {
            loadTheFragment(fragment3);
            textView.setText(Fragment3.fragmentTitle);
         }
        else if(next==1)
        {
            loadTheFragment(fragment1);
            textView.setText(Fragment1.fragmentTitle);
        }
        else if(next==4)
        {
            next = 1;
            loadTheFragment(fragment1);
            textView.setText(Fragment1.fragmentTitle);
        }


}



    /**
     * Part 4.3: function to repalce fragment in fragment container (layout / fragment container
     * call this method in the OnStart method
     * */
    private  void loadTheFragment(Fragment f)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction  =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, f); // this is the layout container found in the main_xml
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.commit();
    }

    /**
     * Part 5: Create a parcelable class to be used with intent
     * The class should have fields to store the information that you want to send
     * to another acticity any non primitive type that you want to use in your intent extras
     * must be parcelable.
     */
    public static class PersonInfo implements Parcelable
    {
        private String name, lastname, city, zip, language;
        public PersonInfo()
        {
            name = "";
            lastname = "";
            city = "";
            zip = "";
            language = "";
        }

        public PersonInfo(String n, String l, String c, String z, String la)
        {
            name = n;
            lastname = l;
            city = c;
            zip = z;
            language =la;
        }

        public PersonInfo(Parcel in)
        {
            String[] data = new String[5];
            in.readStringArray(data);
            name = data[0];
            lastname = data[1];
            city = data[2];
            zip = data[3];
            language =data [4];
        }

        public void setName(String n)
        {
            name = n;
        }

        public void setLastName(String l)
        {
            lastname = l;
        }

        public void setCity(String c)
        {
            city = c;
        }

        public void setZIp(String z)
        {
            zip = z;
        }

        public void setLanguage(String l)
        {
            language=l;
        }

        public String getName()
        {
            return name;
        }

        public String getLastName()
        {
            return lastname;
        }

        public String getCity()
        {
            return city;
        }

        public String getZip()
        {
            return zip;
        }

        public String getLanguage()
        {
            return language;
        }

        @Override
        public int describeContents()
        {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeStringArray(new String[]
                    {
                            name, lastname, city, zip, language
                    });
        }

        public static final Creator<PersonInfo> CREATOR = new Creator<PersonInfo>()
        {
            @Override
            public PersonInfo createFromParcel(Parcel parcel)
            {
                return new PersonInfo(parcel);
            }

            @Override
            public PersonInfo[] newArray(int i)
            {
                return new PersonInfo[i];
            }
        };




    }

}