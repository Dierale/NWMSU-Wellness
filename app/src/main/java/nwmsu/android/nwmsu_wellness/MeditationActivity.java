package nwmsu.android.nwmsu_wellness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MeditationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        TextView motivQuoteTV = findViewById(R.id.quoteTV);
        motivQuoteTV.setText(getMotivQuote());

        Button phonebt = findViewById(R.id.wellnessBTN);
        phonebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phno = "tel:6605621348";
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(phno));
                startActivity(i);

            }
        });
        Button addCalanderBTN = findViewById(R.id.serviceBTN);
        addCalanderBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //
                    //Intent eventsIntent = new Intent(getApplicationContext(), progMed.class);
                    //startActivity(eventsIntent);
                    Uri uri = Uri.parse("https://www.nwmissouri.edu/wellness/services.htm");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                } catch (Exception e) {
                    // Error handling
                    Log.d("click", "Error on: click add button ");
                }
            }
        });


        Button bt = findViewById(R.id.musicBTN);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=1ZYbU82GVz4");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Button nextBt = findViewById(R.id.nextBTN);
        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motivQuoteTV.setText(getMotivQuote());
            }
        });
    }



    public String getMotivQuote(){
        // Should be keeping this inside a string resource
        Resources res = getResources();
        String[] quotesArr = res.getStringArray(R.array.motivQT);
        final int maxSize = quotesArr.length;
        // ((Max - Min) + 1) + Min // Our min is 0 right now // we take out +1 for indexing
        final int random = new Random().nextInt(maxSize);

        return quotesArr[random];
    }


    }



