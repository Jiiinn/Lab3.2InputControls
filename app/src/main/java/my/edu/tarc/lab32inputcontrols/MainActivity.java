package my.edu.tarc.lab32inputcontrols;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerAge;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;
    private double basicPremium, temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerAge = (Spinner)findViewById(R.id.spinnerAge);
        spinnerAge.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.age, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAge.setAdapter(adapter);

        radioButtonMale = (RadioButton)findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton)findViewById(R.id.radioButtonFemale);
        checkBoxSmoker = (CheckBox)findViewById(R.id.checkBoxSmoker);
        textViewPremium = (TextView)findViewById(R.id.textViewPremium);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //TODO: calculate basic premium according to age group

        switch (position){
            case 0:
                basicPremium = 50;
                break;
            case 1:
                basicPremium = 55;
                break;
            case 2:
                basicPremium = 60;
                break;
            case 3:
                basicPremium = 70;
                break;
            case 4:
                basicPremium = 120;
                break;
            case 5:
                basicPremium = 160;
                break;
            case 6:
                basicPremium = 200;
                break;
            case 7:
                basicPremium = 250;
                break;
        }
        temp = basicPremium;
    }

    public void onCalculate(View v){
        int i = spinnerAge.getSelectedItemPosition();
        basicPremium = temp;
        switch (i) {
            case 2:
                if (radioButtonMale.isSelected())
                    basicPremium += 50;
                break;
            case 3:
                if (radioButtonMale.isSelected() && checkBoxSmoker.isChecked())
                    basicPremium += 200;
                else if (radioButtonMale.isSelected())
                    basicPremium += 100;
                else if (checkBoxSmoker.isChecked())
                    basicPremium += 100;
                break;
            case 4:
                if (radioButtonMale.isSelected() && checkBoxSmoker.isChecked())
                    basicPremium += 250;
                else if (radioButtonMale.isSelected())
                    basicPremium += 100;
                else if (checkBoxSmoker.isChecked())
                    basicPremium += 150;
                break;
            case 5:
                if (radioButtonMale.isSelected() && checkBoxSmoker.isChecked())
                    basicPremium += 200;
                else if (radioButtonMale.isSelected())
                    basicPremium += 50;
                else if (checkBoxSmoker.isChecked())
                    basicPremium += 150;
                break;
            case 6:
                if (checkBoxSmoker.isChecked())
                    basicPremium += 250;
                break;
            case 7:
                if (checkBoxSmoker.isChecked())
                    basicPremium += 250;
                break;
        }

        textViewPremium.setText(getResources().getString(R.string.premium) + basicPremium);
    }

    public  void onReset(View v){
        spinnerAge.setSelection(0);
        radioButtonMale.setChecked(false);
        radioButtonFemale.setChecked(false);
        checkBoxSmoker.setChecked(false);
        textViewPremium.setText(getResources().getString(R.string.premium));
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
