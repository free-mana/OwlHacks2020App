package edu.temple.owlhacks2020app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Scanner;

public class RiskActivity extends AppCompatActivity {
    Button calculateButton;
    RadioButton maleChoice;
    RadioButton femaleChoice;
    EditText heightField;
    EditText weightField;
    EditText ageInput;
    Switch isCompromised;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk);
        calculateButton = findViewById(R.id.calculateButton);
        maleChoice = findViewById(R.id.maleChoice);
        femaleChoice = findViewById(R.id.femaleChoice);
        heightField = findViewById(R.id.heightInches);
        weightField = findViewById(R.id.weightPounds);
        ageInput = findViewById(R.id.ageInput);
        isCompromised = findViewById(R.id.isCompromised);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((!maleChoice.isChecked() && !femaleChoice.isChecked()) || heightField.getText().toString().isEmpty() || weightField.getText().toString().isEmpty() || ageInput.getText().toString().isEmpty()){
                    Toast.makeText(RiskActivity.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
                }else{
                    double sexRisk;
                    if(maleChoice.isChecked()){
                        sexRisk = 1.2;
                    }else{
                        sexRisk = .8;
                    }
                    Scanner s = new Scanner(ageInput.getText().toString());
                    double ageRisk = userAgeRisk(s.nextInt());
                    s = new Scanner(heightField.getText().toString());
                    Scanner s2 = new Scanner(weightField.getText().toString());
                    double bmiRisk = userBMIrisk(s.nextDouble(), s2.nextDouble());
                    double immuneRisk;
                    if(isCompromised.isChecked()){
                        immuneRisk = 1.6;
                    }else{
                        immuneRisk = 1;
                    }
                    userRiskCat(userRiskNum(ageRisk, sexRisk, bmiRisk, immuneRisk));
                }
            }
        });
    }
    public static double userAgeRisk(int age){
        double ageRisk;
        if(age <= 18){
            ageRisk = 0.0004;
        }
        else{
            ageRisk = (0.0028*age*age)-(0.043*age)-0.1448;
        }
        return ageRisk;
    }
    public static double userBMIrisk(double height, double weight){
        double bmi = (weight * 703)/(height * height);
        return (0.0043*bmi*bmi) - (0.2119*bmi) + 3.5104;
    }
    public static double userRiskNum(double ageRisk, double sexRisk, double BMIrisk, double immuneRisk){
        return ageRisk * sexRisk * BMIrisk * immuneRisk;
    }
    public void userRiskCat(double riskNum){
        if(riskNum < 0.6){
            Toast.makeText(RiskActivity.this, "Your risk level is low.", Toast.LENGTH_SHORT).show();
        }
        else if(riskNum < 2){
            Toast.makeText(RiskActivity.this, "Your risk level is normal.", Toast.LENGTH_SHORT).show();
        }
        else if(riskNum < 8){
            Toast.makeText(RiskActivity.this, "Your risk level is high.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(RiskActivity.this, "Your risk level is very high.", Toast.LENGTH_SHORT).show();
        }
    }
}