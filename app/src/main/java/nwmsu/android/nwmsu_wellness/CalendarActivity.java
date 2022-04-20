package nwmsu.android.nwmsu_wellness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity
        implements  CalendarAdapter.OnItemListener{

    private TextView monthYearTV;
    private RecyclerView calendarRV;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Prep our private variables
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();

        Button addEventBTN = findViewById(R.id.addEventBTN);
        addEventBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent eventsIntent = new Intent(getApplicationContext(), EventActivity.class);
                    startActivity(eventsIntent);
                } catch (Exception e) {
                    // Error handling
                    Log.d("click", "Error on: click add button ");
                }
            }
        });
    }

    private void initWidgets() {
        calendarRV = findViewById( R.id.calendarRV);
        monthYearTV = findViewById( R.id.calendarMainTV);
    }

    private void setMonthView() {
        monthYearTV.setText(getMonthYearFromDate(selectedDate));
        ArrayList<String> daysForMonth = getDaysForMonthList(selectedDate);

        // It really wanted me to make sure i converted this to the onItemListener so I did
        CalendarAdapter calendarAdapter = new CalendarAdapter( daysForMonth, (CalendarAdapter.OnItemListener) this);
        // We're going to have seven columns (seven days) so spanCount is 7
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);

        // Now that we have the vars, give them to our RV
        calendarRV.setLayoutManager(layoutManager);
        calendarRV.setAdapter( calendarAdapter);
    }

    private String getMonthYearFromDate(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(dtf);
    }

    private ArrayList<String> getDaysForMonthList(LocalDate date) {
        // Prep results & get yearMonth from given date
        ArrayList<String> daysForMonth = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        // Set up some variables to calculate what day/range we're in
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstDay = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstDay.getDayOfWeek().getValue();

        for( int i=1; i<=42; i++) {
            // if it's before the 1st of the month or after the end of the month
            if( i <= dayOfWeek || i > (daysInMonth + dayOfWeek)) {
                // add a blank square to the calendar
                daysForMonth.add("");
            } else {
                // add a properly formatted day
                String tValue = String.valueOf(i - dayOfWeek);
                daysForMonth.add(tValue);
            }
        }
        return daysForMonth;
    }

    // BUTTON ON CLICK FUNCTIONS

    public void backMonthAction( View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction( View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick( int position, String dayName) {
        // If we select a non-empty calendar square aka it's a day
        if( !dayName.equals("")) {
            // Do stuff
            String message = "Selected Date " + dayName + " "
                    + getMonthYearFromDate(selectedDate);
            Log.d("Calendar", message);
        }
    }
}