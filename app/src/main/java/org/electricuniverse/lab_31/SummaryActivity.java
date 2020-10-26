package org.electricuniverse.lab_31;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        MainActivity.PersonInfo pi = (MainActivity.PersonInfo) getIntent().getParcelableExtra("pi");
        TextView nl=findViewById(R.id.name_lastname);
        TextView cz=findViewById(R.id.city_zip);
        TextView lang=findViewById(R.id.language2);
        nl.setText("Name : "+pi.getName()+" LastName : "+pi.getLastName());
        cz.setText("City : "+pi.getCity()+" Zip : "+pi.getZip());
        lang.setText("Language : "+pi.getLanguage());

    }
}