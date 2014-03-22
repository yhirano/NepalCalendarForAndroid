/*
 * Copyright (c) 2014 Yuichi Hirano
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.uraroji.garage.android.ncalendar;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import com.uraroji.garage.android.ncalendar.util.NepalDate;
import com.uraroji.garage.android.ncalendar.util.NepaliStringUtility;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DailyFragment extends Fragment {
	private TextView mYearView;
	private TextView mMonthView;
	private TextView mDayView;
	private TextView mNepalTimeView;
	private TextView mLocalTimeView;

	private Timer mTimer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.daily_fragment, container, false);

		mYearView = (TextView) view.findViewById(R.id.year);
		mMonthView = (TextView) view.findViewById(R.id.month);
		mDayView = (TextView) view.findViewById(R.id.day);
		mNepalTimeView = (TextView) view.findViewById(R.id.nepal_time);
		mLocalTimeView = (TextView) view.findViewById(R.id.local_time);

		updateDate();

		mTimer = new Timer();
		UpdateDateTask updateDateTask = new UpdateDateTask();
		mTimer.scheduleAtFixedRate(updateDateTask, 100, 100);

		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();

		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
	}

	private void updateDate() {
		Date nowDate = new Date();
		NepalDate nepalDate = new NepalDate(nowDate, true);
		
		
		mYearView.setText(NepaliStringUtility.getStringToNelaliFromNumber(nepalDate.getNepalYear()));
		mMonthView.setText(NepaliStringUtility.getStringToNepaliMonth(nepalDate.getNepalMonth()));
		mDayView.setText(NepaliStringUtility.getStringToNelaliFromNumber(nepalDate.getNepalDay()));
		
		DateFormat nepalTimeDateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM);
		nepalTimeDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Katmandu"));
		String nepalTimeText = nepalTimeDateFormat.format(nowDate);
		
		DateFormat localTimeDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		String localTimeText = localTimeDateFormat.format(nowDate);

		mNepalTimeView.setText(nepalTimeText);
		mLocalTimeView.setText(localTimeText);
	}

	private class UpdateDateTask extends TimerTask {
		@Override
		public void run() {
			getActivity().runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					updateDate();
				}
			});
		}
	}
}
