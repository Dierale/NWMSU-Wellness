package nwmsu.android.nwmsu_wellness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MeditationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        ((ImageButton) findViewById(R.id.phoneBTN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phno = "6605621348";
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
}