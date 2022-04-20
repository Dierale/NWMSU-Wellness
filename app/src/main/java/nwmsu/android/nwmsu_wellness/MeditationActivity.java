package nwmsu.android.nwmsu_wellness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
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

        TextView motivQuoteTV = findViewById( R.id.QuoteTV);
        motivQuoteTV.setText( getMotivQuote());

        ImageButton phonebt = (ImageButton) findViewById(R.id.phoneBTN);
        phonebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phno = "tel:6605621348";
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(phno));
                startActivity(i);

            }
        });

        ImageButton bt = (ImageButton) findViewById(R.id.musicBTN);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=1ZYbU82GVz4");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }
    public String getMotivQuote() {
        // Should be keeping this inside a string resource
        String[] quotes = {
                "Health is wealth",
                "Do not stop until you are proud",
                "Believe good things are on the way",
                "Everyday is a chance to be better",

        };
        final int maxSize = quotes.length;
        // ((Max - Min) + 1) + Min // Our min is 0 right now // we take out +1 for indexing
        final int random = new Random().nextInt(maxSize);
        return "Motivational Quote\n-----\n"+quotes[random];
    }
}