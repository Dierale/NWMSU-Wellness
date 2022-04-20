package nwmsu.android.nwmsu_wellness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventAdapter extends ArrayAdapter<CalendarEventModel>
{
    public EventAdapter(@NonNull Context context, List<CalendarEventModel> events)
    {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        CalendarEventModel event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);

        // Cap our description
        String tDescription = event.getDescription();
        if( tDescription.length() >= 40) {
            tDescription.substring(0, 40);
        }

        // Set up what we're passing the event listing under the calendar
        String eventTitle = event.getEventName() + " "
                + CalendarActivity.formattedTime(event.getStartTime())
                + "\n - " + tDescription;

        // Set the text to our big 'ol string & return the view
        eventCellTV.setText(eventTitle);
        return convertView;
    }
}