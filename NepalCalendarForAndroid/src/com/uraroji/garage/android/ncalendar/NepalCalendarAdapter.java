package com.uraroji.garage.android.ncalendar;

import java.util.Calendar;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.tyczj.extendedcalendarview.CalendarAdapter;
import com.tyczj.extendedcalendarview.Day;
import com.tyczj.extendedcalendarview.Event;
import com.uraroji.garage.android.ncalendar.util.NepalDate;
import com.uraroji.garage.android.ncalendar.util.NepaliStringUtility;

public class NepalCalendarAdapter<T extends Event> extends CalendarAdapter<T> {

	public NepalCalendarAdapter(Context context, Calendar cal, boolean duplicatesVisibility) {
		super(context, cal, duplicatesVisibility);
	}

	@Override
	protected void onSetDay(View dayView, Day<T> day) {
		TextView nepalMonthView = (TextView)dayView.findViewById(R.id.nepal_month_textView);
		TextView nepalDatView = (TextView)dayView.findViewById(R.id.nepal_day_textView);
		
		NepalDate nepalDate = new NepalDate(day.getDate(), false);
		
		if (nepalDate.getNepalDay() == 1) {
			nepalMonthView.setText(NepaliStringUtility.getStringToNepaliMonth(nepalDate.getNepalMonth()));
		}
		nepalDatView.setText(NepaliStringUtility.getStringToNelaliFromNumber(nepalDate.getNepalDay()));
	}
}
