package nwmsu.android.nwmsu_wellness;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    public final View cellParentTV;
    public final TextView dayOfMonth;
    private final ArrayList<LocalDate> daysOfMonth;
    public final CalendarAdapter.OnItemListener myItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener,
                              ArrayList<LocalDate> daysOfMonth) {
        super(itemView);
        cellParentTV = itemView.findViewById( R.id.cellParentView);
        dayOfMonth = itemView.findViewById( R.id.cellDayTV);
        this.myItemListener = onItemListener;
        this.daysOfMonth = daysOfMonth;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        myItemListener.onItemClick( getAdapterPosition(),
                daysOfMonth.get(getAdapterPosition()));
                //dayOfMonth.getText().toString());
    }
}
