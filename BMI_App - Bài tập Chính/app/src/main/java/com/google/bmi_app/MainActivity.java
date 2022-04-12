package com.google.bmi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //có phải là boy
        final boolean[] istrueBoy = {true};

        //image view
        ImageView imageBoy = findViewById(R.id.image_boy);
        ImageView imageGirl = findViewById(R.id.image_girl);

        // Lầy giá trị weigh và height
        EditText weight = findViewById(R.id.weight_value);
        EditText height = findViewById(R.id.height_value);

        // button tính toán
        Button calculateButton = findViewById(R.id.calculate_button);

        // hiện BMI
        TextView bmi = findViewById(R.id.bmi);

        // hiện mức độ BMI
        TextView bmiStatus = findViewById(R.id.bmi_status);

        // textview de lam lai lan nua
        LinearLayout bmiView = findViewById(R.id.bmiView);
        TextView calculateAgainButton = findViewById(R.id.calculate_again);

        imageBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageBoy.setImageResource(R.drawable.ic_boy);
                imageGirl.setImageResource(R.drawable.ic_girl_blur);
                istrueBoy[0] = true;
            }
        });

        imageGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageBoy.setImageResource(R.drawable.ic_boy_blur);
                imageGirl.setImageResource(R.drawable.ic_girl);
                istrueBoy[0] = false;
            }
        });


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double weightValue = Double.valueOf(weight.getText().toString());
                double heightValue = Double.valueOf(height.getText().toString()) / 100;

                if (weightValue > 0 && heightValue > 0) {
                    String bmiValue = String.format("%.2f", weightValue/Math.pow(heightValue, 2));
                    bmi.setText(bmiValue);
                    bmiStatus.setText(bmiStatusValue(weightValue/Math.pow(heightValue, 2), istrueBoy[0]));
                    bmiView.setVisibility(View.VISIBLE);
                    calculateButton.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.Canh_bao), Toast.LENGTH_SHORT).show();
                }
            }
        });

        calculateAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmiView.setVisibility(View.GONE);
                calculateButton.setVisibility(View.VISIBLE);
                weight.setText("");
                height.setText("");
                weight.requestFocus();
            }
        });
    }

    private String bmiStatusValue(double bmi, boolean istrueboy) {
        String bmiStatus = "";
        if (bmi < 18.5)
            bmiStatus = getString(R.string.underweight);
        else if (bmi >= 18.5 && bmi < 25)
            bmiStatus = getString(R.string.Normal);
        else if (bmi >= 25 && bmi < 30)
            bmiStatus = getString(R.string.Overweight);
        else if (bmi > 30)
            bmiStatus = getString(R.string.Obese);
        return bmiStatus;
    }
}