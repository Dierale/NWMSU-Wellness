package nwmsu.android.nwmsu_wellness;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final ArrayList<LocalDate> daysOfMonth;
    private final OnItemListener myItemListener;

    public CalendarAdapter(ArrayList<LocalDate> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.myItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View myView = inflater.inflate( R.layout.calendar_cell, parent, false);

        ViewGroup.LayoutParams layoutParams = myView.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * (1.0/16.0));

        return new CalendarViewHolder(myView, myItemListener, daysOfMonth);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        final LocalDate date = daysOfMonth.get(position);
        if( date == null)
            holder.dayOfMonth.setText("");
        else {
            String tDisplay = String.valueOf(date.getDayOfMonth());
            holder.dayOfMonth.setText(tDisplay);
            if(date.equals(CalendarActivity.getSelectedDate())) {
                holder.cellParentTV.setBackgroundColor(Color.LTGRAY);
            }
        }
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, LocalDate date);
    }
}
