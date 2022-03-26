package nwmsu.android.nwmsu_wellness;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class EventActivity extends AppCompatActivity {
    Calendar myCalendar; // = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        myCalendar = Calendar.getInstance();

        EditText startDateET = (EditText) findViewById( R.id.startDateET);
        EditText endDateET = (EditText) findViewById( R.id.endDateET);

        setupPopupDatePicker( startDateET, true);
        setupPopupDatePicker( endDateET, false);

        Button saveEventBTN = findViewById(R.id.saveEventBTN);
        saveEventBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ArrayList<String> errorText = new ArrayList<>();
                    TextView errorTV = findViewById(R.id.errorTV);
                    errorTV.setText("");

                    TextView nameET = findViewById(R.id.nameET);
                    String nameText = nameET.getText().toString();
                    if (!validateName(nameText)) {
                        errorText.add("Name cannot be empty");
                    }

                    // Don't need to validate Date because popupPicker formats already
                    // Nothing should be able to change the data within disabled ET fields
                    TextView startDateET = findViewById(R.id.startDateET);
                    String sDateText = startDateET.getText().toString();
                    //Log.d("date text", sDateText);

                    // Don't need to validate for same reasons above
                    TextView endDateET = findViewById(R.id.endDateET);
                    String eDateText = endDateET.getText().toString();

                    TextView descriptionET = findViewById(R.id.descriptionET);
                    String descText = descriptionET.getText().toString();

                    // Send Toast with error
                    if(!errorText.isEmpty()) {
                        String errorMessage = getErrorString( errorText);
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG);
                        // update errorTV location. toast better alone at the moment
                        //errorTV.setText( errorMessage);
                        Log.d("Invalid Event Input", "Error: Invalid event input data");
                    } else {
                        CalendarEventModel myEvent = new CalendarEventModel();
                        myEvent.initModel( nameText, sDateText, eDateText);
                        if( descText != "")
                            myEvent.setDescription(descText);
                        // Save the model in storage
                        Log.d("Save Calendar Event", "The Calendar Event was saved successfully");
                    }
                    /**
                    Intent eventsIntent = new Intent( getApplicationContext(), CalendarActivity.class);
                    startActivity(eventsIntent);
                     */
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

    private void setupPopupDatePicker( EditText myDateET, boolean isStartDate) {
        DatePickerDialog.OnDateSetListener myDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateDate( isStartDate);
            }
        };

        myDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EventActivity.this, myDate,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
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

    private void updateDate(boolean isStartDate){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        EditText dateText = (isStartDate) ?
                findViewById(R.id.startDateET) : findViewById( R.id.endDateET);
        dateText.setText(dateFormat.format(myCalendar.getTime()));
    }
}