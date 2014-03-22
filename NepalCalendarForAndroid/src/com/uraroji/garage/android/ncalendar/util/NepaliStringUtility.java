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

public class NepaliStringUtility {
	public static String getStringToNelaliFromNumber(int number) {
		String[] nepaliNumArray = new String[] { "०", "१", "२", "३", "४", "५", "६", "७", "८", "९" };
		StringBuilder result = new StringBuilder();
		while (number > 0) {
			int n = number % 10;
			result.insert(0, nepaliNumArray[n]);
			number /= 10;
		}
		return result.toString();
	}

	public static String getStringToNepaliMonth(int month) {
		switch (month) {
		case 1:
			return "बैशाख";
		case 2:
			return "जेष्ठ";
		case 3:
			return "आषाढ";
		case 4:
			return "श्रावण";
		case 5:
			return "भाद्र";
		case 6:
			return "आश्विन";
		case 7:
			return "कार्तिक";
		case 8:
			return "मार्ग";
		case 9:
			return "पौष";
		case 10:
			return "माघ";
		case 11:
			return "फाल्गुन";
		case 12:
			return "चैत्र";
		default:
			return null;
		}
	}
}
