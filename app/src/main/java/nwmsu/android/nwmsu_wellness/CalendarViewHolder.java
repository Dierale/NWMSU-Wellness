package nwmsu.android.nwmsu_wellness;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    public final TextView dayOfMonth;
    public final CalendarAdapter.OnItemListener myItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById( R.id.cellDayTV);
        this.myItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        myItemListener.onItemClick( getAdapterPosition(), dayOfMonth.getText().toString());
    }
}
