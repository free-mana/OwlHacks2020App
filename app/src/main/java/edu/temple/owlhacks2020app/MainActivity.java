package edu.temple.owlhacks2020app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView localCases;
    ListView caseList;
    Button launchRiskActivityButton;
    String names[];
    Location locations[];
    boolean infected[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localCases = findViewById(R.id.localCases);
        caseList = findViewById(R.id.caseList);
        launchRiskActivityButton = findViewById(R.id.launchRiskActivityButton);
        names = new String[]{"Amy", "Bob", "Carol", "Dylan", "Elizabeth", "Frank", "George", "Hank",
                "Ian", "Jill", "Karen", "Liam", "Matt", "Neil", "Olivia", "Penelope", "Quentin",
                "Rachel", "Sam", "Tom", "Ursula", "Violet", "William", "Xavier", "Yolanda", "Zelda"};
        locations = new Location[26];
        infected = new boolean[26];
        Arrays.fill(infected, false);
        infected[1] = true;
        infected[4] = true;
        infected[11] = true;
        infected[17] = true;
        infected[23] = true;
        caseList.setAdapter(new CaseAdapter(this, names, locations, infected));
        launchRiskActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RiskActivity.class));
            }
        });
    }
}