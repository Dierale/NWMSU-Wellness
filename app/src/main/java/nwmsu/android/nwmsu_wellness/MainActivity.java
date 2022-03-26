package nwmsu.android.nwmsu_wellness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView dailyQuoteTV = findViewById( R.id.dailyQuoteTV);
        dailyQuoteTV.setText( getDailyQuote());
    }

    public String getDailyQuote() {
        // Should be keeping this inside a string resource
        String[] quotes = {
                "A Bearcat a day keeps the angry geese away",
                "Squirrels are a Bearcat's best friend",
                "When in doubt, wear green out",
                "Motivational quotes aren't necessary when you're awesome",
                "There's no motivation like impending doom"
        };
        final int maxSize = quotes.length;
        // ((Max - Min) + 1) + Min // Our min is 0 right now
        final int random = new Random().nextInt(maxSize+ 1);
        return "Daily Quote\n"+quotes[random];
    }
}