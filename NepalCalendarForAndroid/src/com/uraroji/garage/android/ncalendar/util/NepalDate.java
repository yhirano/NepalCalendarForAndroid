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

package com.uraroji.garage.android.ncalendar.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.util.SparseArray;

public class NepalDate {
	private static SparseArray<int[]> sNepalCalendarMap;
	private int mNepalYear;
	private int mNepalMonth;
	private int mNepalDay;

	public NepalDate(boolean nepalTimeZone) {
		calcNepalDate(new Date(), nepalTimeZone);
	}

	public NepalDate(Date date, boolean nepalTimeZone) {
		calcNepalDate(date, nepalTimeZone);
	}

	public int getNepalYear() {
		return mNepalYear;
	}

	public int getNepalMonth() {
		return mNepalMonth;
	}

	public int getNepalDay() {
		return mNepalDay;
	}

	private void calcNepalDate(Date date, boolean nepalTimeZone) {
		createNepalCalendarMap();

		Calendar calendar = Calendar.getInstance();
		if (nepalTimeZone) {
			calendar.setTimeZone(TimeZone.getTimeZone("Asia/Katmandu"));
		}
		calendar.setTime(date);

		int[] nepalDateData = sNepalCalendarMap.get(calendar.get(Calendar.YEAR));

		mNepalYear = nepalDateData[0];

		// Gregorian calendar date Jan 1 always fall in Poush(9) month of Nepali Calendar.
		mNepalMonth = 9;

		// Initializing Nepali DaysInMonth with total days in the month of Poush
		int nepalDaysInMonth = nepalDateData[2];

		// This is sum of total days in each Nepali month starting Jan 1 in Nepali month Poush
		int nepalTempDays = nepalDateData[2] - nepalDateData[1] + 1;

		int gregorianDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

		for (int i = 3; gregorianDayOfYear > nepalTempDays; i++) {
			nepalTempDays += nepalDateData[i];
			nepalDaysInMonth = nepalDateData[i];
			++mNepalMonth;

			if (mNepalMonth > 12) {
				mNepalMonth = 1;
				++mNepalYear;
			}
		}

		mNepalDay = nepalDaysInMonth - (nepalTempDays - gregorianDayOfYear);
	}

	private static void createNepalCalendarMap() {
		if (sNepalCalendarMap != null) {
			return;
		}
		sNepalCalendarMap = new SparseArray<int[]>(86);
		// Year : NepalYear, nepal day for Jan 1, total days in Poush, days in Month of Jan, Feb...
		sNepalCalendarMap.put(1949, new int[] { 2005, 17, 30, 29, 30, 30, 31, 31, 32, 32, 31, 30, 30, 29, 30 });
		sNepalCalendarMap.put(1950, new int[] { 2006, 18, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1951, new int[] { 2007, 17, 29, 29, 30, 31, 31, 31, 31, 32, 31, 31, 29, 30, 30 });
		sNepalCalendarMap.put(1952, new int[] { 2008, 17, 30, 29, 29, 31, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1953, new int[] { 2009, 18, 30, 29, 30, 30, 31, 31, 32, 32, 31, 30, 30, 29, 30 });
		sNepalCalendarMap.put(1954, new int[] { 2010, 18, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1955, new int[] { 2011, 17, 29, 29, 30, 31, 31, 31, 31, 32, 31, 31, 29, 30, 30 });
		sNepalCalendarMap.put(1956, new int[] { 2012, 17, 30, 29, 30, 30, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1957, new int[] { 2013, 18, 30, 29, 30, 30, 31, 31, 32, 32, 31, 30, 30, 29, 30 });
		sNepalCalendarMap.put(1958, new int[] { 2014, 18, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1959, new int[] { 2015, 17, 29, 29, 30, 31, 31, 31, 31, 32, 31, 31, 29, 30, 30 });
		sNepalCalendarMap.put(1960, new int[] { 2016, 17, 30, 29, 30, 30, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1961, new int[] { 2017, 18, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 29, 30 });
		sNepalCalendarMap.put(1962, new int[] { 2018, 18, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1963, new int[] { 2019, 17, 29, 30, 29, 31, 31, 31, 31, 32, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1964, new int[] { 2020, 17, 30, 29, 30, 30, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1965, new int[] { 2021, 18, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1966, new int[] { 2022, 17, 29, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1967, new int[] { 2023, 17, 29, 30, 29, 31, 31, 31, 31, 32, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1968, new int[] { 2024, 17, 30, 29, 30, 30, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1969, new int[] { 2025, 18, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1970, new int[] { 2026, 17, 29, 29, 30, 31, 30, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1971, new int[] { 2027, 17, 29, 30, 29, 31, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1972, new int[] { 2028, 17, 30, 29, 30, 30, 31, 31, 32, 31, 32, 30, 30, 29, 30 });
		sNepalCalendarMap.put(1973, new int[] { 2029, 18, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1974, new int[] { 2030, 17, 29, 29, 30, 31, 30, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1975, new int[] { 2031, 17, 29, 30, 29, 31, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1976, new int[] { 2032, 17, 30, 29, 30, 30, 31, 31, 32, 32, 31, 30, 30, 29, 30 });
		sNepalCalendarMap.put(1977, new int[] { 2033, 18, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1978, new int[] { 2034, 17, 29, 29, 30, 31, 30, 32, 31, 32, 31, 31, 29, 30, 30 });
		sNepalCalendarMap.put(1979, new int[] { 2035, 17, 30, 29, 29, 31, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1980, new int[] { 2036, 17, 30, 29, 30, 30, 31, 31, 32, 32, 31, 30, 30, 29, 30 });
		sNepalCalendarMap.put(1981, new int[] { 2037, 18, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1982, new int[] { 2038, 17, 29, 29, 30, 31, 31, 31, 31, 32, 31, 31, 29, 30, 30 });
		sNepalCalendarMap.put(1983, new int[] { 2039, 17, 30, 29, 30, 30, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1984, new int[] { 2040, 17, 30, 29, 30, 30, 31, 31, 32, 32, 31, 30, 30, 29, 30 });
		sNepalCalendarMap.put(1985, new int[] { 2041, 18, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1986, new int[] { 2042, 17, 29, 29, 30, 31, 31, 31, 31, 32, 31, 31, 29, 30, 30 });
		sNepalCalendarMap.put(1987, new int[] { 2043, 17, 30, 29, 30, 30, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1988, new int[] { 2044, 17, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 29, 30 });
		sNepalCalendarMap.put(1989, new int[] { 2045, 18, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1990, new int[] { 2046, 17, 29, 29, 30, 31, 31, 31, 31, 32, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1991, new int[] { 2047, 17, 30, 29, 30, 30, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1992, new int[] { 2048, 17, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1993, new int[] { 2049, 17, 29, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1994, new int[] { 2050, 17, 29, 30, 29, 31, 31, 31, 31, 32, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1995, new int[] { 2051, 18, 30, 29, 30, 30, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1996, new int[] { 2052, 17, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1997, new int[] { 2053, 17, 29, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(1998, new int[] { 2054, 17, 29, 30, 29, 31, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(1999, new int[] { 2055, 17, 30, 29, 30, 30, 31, 31, 32, 31, 32, 30, 30, 29, 30 });
		sNepalCalendarMap.put(2000, new int[] { 2056, 17, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(2001, new int[] { 2057, 17, 29, 29, 30, 31, 30, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(2002, new int[] { 2058, 17, 29, 30, 29, 31, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(2003, new int[] { 2059, 17, 30, 29, 30, 30, 31, 31, 32, 32, 31, 30, 30, 29, 30 });
		sNepalCalendarMap.put(2004, new int[] { 2060, 17, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(2005, new int[] { 2061, 17, 29, 29, 30, 31, 30, 32, 31, 32, 31, 31, 29, 30, 29 });
		sNepalCalendarMap.put(2006, new int[] { 2062, 17, 29, 30, 29, 31, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(2007, new int[] { 2063, 17, 30, 29, 30, 30, 31, 31, 32, 32, 31, 30, 30, 29, 30 });
		sNepalCalendarMap.put(2008, new int[] { 2064, 17, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(2009, new int[] { 2065, 17, 29, 29, 30, 31, 31, 31, 31, 32, 31, 31, 29, 30, 30 });
		sNepalCalendarMap.put(2010, new int[] { 2066, 17, 30, 29, 29, 31, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(2011, new int[] { 2067, 17, 30, 29, 30, 30, 31, 31, 32, 32, 31, 30, 30, 29, 30 });
		sNepalCalendarMap.put(2012, new int[] { 2068, 17, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(2013, new int[] { 2069, 17, 29, 29, 30, 31, 31, 31, 31, 32, 31, 31, 29, 30, 30 });
		sNepalCalendarMap.put(2014, new int[] { 2070, 17, 30, 29, 30, 30, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(2015, new int[] { 2071, 17, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 29, 30 });
		sNepalCalendarMap.put(2016, new int[] { 2072, 17, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(2017, new int[] { 2073, 17, 29, 29, 30, 31, 31, 31, 31, 32, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(2018, new int[] { 2074, 17, 30, 29, 30, 30, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(2019, new int[] { 2075, 17, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(2020, new int[] { 2076, 17, 29, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(2021, new int[] { 2077, 17, 29, 30, 29, 31, 31, 31, 31, 32, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(2022, new int[] { 2078, 17, 30, 29, 30, 30, 31, 31, 32, 31, 31, 31, 30, 29, 30 });
		sNepalCalendarMap.put(2023, new int[] { 2079, 17, 30, 29, 30, 30, 31, 32, 31, 32, 31, 30, 30, 30, 29 });
		sNepalCalendarMap.put(2024, new int[] { 2080, 16, 29, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 });
		sNepalCalendarMap.put(2025, new int[] { 2081, 17, 29, 31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
		sNepalCalendarMap.put(2026, new int[] { 2082, 17, 29, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
		sNepalCalendarMap.put(2027, new int[] { 2083, 17, 29, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30 });
		sNepalCalendarMap.put(2028, new int[] { 2084, 17, 29, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30 });
		sNepalCalendarMap.put(2029, new int[] { 2085, 17, 29, 31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30 });
		sNepalCalendarMap.put(2030, new int[] { 2086, 17, 29, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
		sNepalCalendarMap.put(2031, new int[] { 2087, 16, 29, 31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30 });
		sNepalCalendarMap.put(2032, new int[] { 2088, 16, 29, 30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30 });
		sNepalCalendarMap.put(2033, new int[] { 2089, 17, 29, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
		sNepalCalendarMap.put(2034, new int[] { 2090, 17, 29, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 });
	}
}
