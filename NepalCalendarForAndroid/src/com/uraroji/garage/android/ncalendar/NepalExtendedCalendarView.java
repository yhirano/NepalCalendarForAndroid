package com.uraroji.garage.android.ncalendar;

import java.util.Calendar;

import android.content.Context;
import android.util.AttributeSet;

import com.tyczj.extendedcalendarview.CalendarAdapter;
import com.tyczj.extendedcalendarview.Event;
import com.tyczj.extendedcalendarview.ExtendedCalendarView;
import com.uraroji.garage.android.ncalendar.util.NepalDate;
import com.uraroji.garage.android.ncalendar.util.NepaliStringUtility;

public class NepalExtendedCalendarView<T extends Event> extends ExtendedCalendarView<T> {

	public NepalExtendedCalendarView(Context context) {
		super(context);
	}

	public NepalExtendedCalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NepalExtendedCalendarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected CalendarAdapter<T> createCalendarAdapter() {
		return new NepalCalendarAdapter<T>(getContext(), getCal(), areDuplicatesAvoided());
	}

	@Override
	protected String getMonthText() {
		Calendar firstDayOfMonthCalendar = (Calendar) getCal().clone();
		firstDayOfMonthCalendar.set(Calendar.DATE, 1);
		Calendar lastDayOfMonthCalendar = (Calendar) getCal().clone();
		lastDayOfMonthCalendar.set(Calendar.DATE, lastDayOfMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		NepalDate nepalFirstDate = new NepalDate(firstDayOfMonthCalendar.getTime(), false);
		NepalDate nepalLastDate = new NepalDate(lastDayOfMonthCalendar.getTime(), false);

		String nepalMonthStr = null;
		if (nepalFirstDate.getNepalYear() == nepalLastDate.getNepalYear()) {
			if (nepalFirstDate.getNepalMonth() == nepalLastDate.getNepalMonth()) {
				nepalMonthStr = NepaliStringUtility.getStringToNepaliMonth(nepalFirstDate.getNepalMonth()) + " "
						+ NepaliStringUtility.getStringToNelaliFromNumber(nepalFirstDate.getNepalYear());
			} else {
				nepalMonthStr = NepaliStringUtility.getStringToNepaliMonth(nepalFirstDate.getNepalMonth()) + "-"
						+ NepaliStringUtility.getStringToNepaliMonth(nepalLastDate.getNepalMonth()) + " "
						+ NepaliStringUtility.getStringToNelaliFromNumber(nepalFirstDate.getNepalYear());
			}
		} else {
			nepalMonthStr = NepaliStringUtility.getStringToNepaliMonth(nepalFirstDate.getNepalMonth()) + " "
					+ NepaliStringUtility.getStringToNelaliFromNumber(nepalFirstDate.getNepalYear()) + "-"
					+ NepaliStringUtility.getStringToNepaliMonth(nepalLastDate.getNepalMonth()) + " "
					+ NepaliStringUtility.getStringToNelaliFromNumber(nepalLastDate.getNepalYear());
		}

		String result = super.getMonthText() + " / " + nepalMonthStr;

		return result;
	}
}
