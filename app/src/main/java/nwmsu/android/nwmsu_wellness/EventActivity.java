package nwmsu.android.nwmsu_wellness;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class EventActivity extends AppCompatActivity {
    Calendar myCalendar; // = Calendar.getInstance();

    private TextView descriptionET, nameET;
    private EditText dateET, timeET;

    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        myCalendar = Calendar.getInstance();
        initWidgets();
        time = LocalTime.now();

        setUpWidgets();
        attachOnClicks();
    }

    private void initWidgets() {
        nameET = findViewById(R.id.nameET);
        dateET = (EditText) findViewById( R.id.dateET);
        timeET = (EditText) findViewById( R.id.timeET);
        descriptionET = findViewById( R.id.descriptionET);
    }

    private void setUpWidgets() {
        LocalDate selectedDate = CalendarActivity.getSelectedDate();
        dateET.setText(CalendarActivity.formattedDate(selectedDate));
        timeET.setText(CalendarActivity.formattedTime(time));

        DatePickerDialog.OnDateSetListener myDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateDate();
            }
        };

        dateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EventActivity.this, myDate,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void attachOnClicks() {
        Button saveEventBTN = findViewById(R.id.saveEventBTN);
        saveEventBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String eventName = nameET.getText().toString();
                    LocalDate eventDate = CalendarActivity.getSelectedDate();
                    LocalTime eventTime = time;
                    //LocalDate eventDate = CalendarActivity.formattedDate(dateET.getText());
                    String eventDescription = descriptionET.getText().toString();
                    CalendarEventModel newEvent = new CalendarEventModel(eventName, eventDate, eventTime, eventDescription);
                    CalendarEventModel.eventList.add( newEvent);
                    finish();
                } catch (Exception e) {
                    // Error handling
                    Log.d("click", "Error on: click add button ");
                }
            }
        });
    }

    private boolean validateName(String contents) {
        return (contents != "");
    }

    private String getErrorString( ArrayList<String> errors) {
        String errorMessage = "";
        if( !errors.isEmpty()) {
            int tSize = errors.size();
            for(int i=0; i < tSize; i++) {
                errorMessage += errors.get(i) + "\n";
            }
        }
        return errorMessage;
    }

    private void updateDate(){
        String myFormat="dd MMMM yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        EditText dateText = findViewById(R.id.dateET) ;
        dateText.setText(dateFormat.format(myCalendar.getTime()));
    }
}