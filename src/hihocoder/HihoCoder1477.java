package hihocoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This is the ACM problem solving program for hihoCoder 1477
 * 
 * @version 2017-03-19
 * @author Zhang Yufei
 */
public class HihoCoder1477 {
	/**
	 * Record all leapSeconds;
	 */
	private static int[][] leapSeconds = { { 1972, 6, 30, 23, 59, 60 },
			{ 1972, 12, 31, 23, 59, 60 }, { 1973, 12, 31, 23, 59, 60 },
			{ 1974, 12, 31, 23, 59, 60 }, { 1975, 12, 31, 23, 59, 60 },
			{ 1976, 12, 31, 23, 59, 60 }, { 1977, 12, 31, 23, 59, 60 },
			{ 1978, 12, 31, 23, 59, 60 }, { 1979, 12, 31, 23, 59, 60 },
			{ 1981, 6, 30, 23, 59, 60 }, { 1982, 6, 30, 23, 59, 60 },
			{ 1983, 6, 30, 23, 59, 60 }, { 1985, 6, 30, 23, 59, 60 },
			{ 1987, 12, 31, 23, 59, 60 }, { 1989, 12, 31, 23, 59, 60 },
			{ 1990, 12, 31, 23, 59, 60 }, { 1992, 6, 30, 23, 59, 60 },
			{ 1993, 6, 30, 23, 59, 60 }, { 1994, 6, 30, 23, 59, 60 },
			{ 1995, 12, 31, 23, 59, 60 }, { 1997, 6, 30, 23, 59, 60 },
			{ 1998, 12, 31, 23, 59, 60 }, { 2005, 12, 31, 23, 59, 60 },
			{ 2008, 12, 31, 23, 59, 60 }, { 2012, 6, 30, 23, 59, 60 },
			{ 2015, 6, 30, 23, 59, 60 }, { 2016, 12, 31, 23, 59, 60 } };

	/**
	 * Record the number of days since the day 0000-00-00
	 */
	private static Map<Integer, Integer> daysOfYear;

	/**
	 * Input data;
	 */
	private static int year1, year2, month1, month2, day1, day2, hour1, hour2,
			minute1, minute2, second1, second2;

	/**
	 * Check if the give date is a leap day.
	 * 
	 * @param year
	 * @param month
	 * @return Return <code>true</code> if the given date is leap day or
	 *         return <code>false</code>.
	 */
	private static boolean isLeapDay(int year, int month, int day) {
		for (int i = 0; i < leapSeconds.length; i++) {
			if (year == leapSeconds[i][0] && month == leapSeconds[i][1]
					&& day == leapSeconds[i][2]) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Get the number of days of the given year and month.
	 * 
	 * @param year
	 *            Given year.
	 * @param month
	 *            Given month.
	 * @return The number of days of the given month in given year.
	 */
	private static int getDaysOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
	}

	/**
	 * Check if a year is leap year.
	 * 
	 * @param year
	 *            The year to search.
	 * @return A boolean value indicating if the year is a leap year.
	 */
	private static boolean isLeapYear(int year) {
		return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
	}

	/**
	 * Check if the input 2 date data is the same day.
	 * 
	 * @return If the inputed 2 date data is the same day, return
	 *         <code>true></code>, or return <code> false </code>.
	 */
	private static boolean isSameDay() {
		if (year1 == year2 && month1 == month2 && day1 == day2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if the input 2 date data is the same month.
	 * 
	 * @return If the inputed 2 date data is the same month, return
	 *         <code>true></code>, or return <code> false </code>.
	 */
	private static boolean isSameMonth() {
		if (year1 == year2 && month1 == month2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if the input 2 date data is the same year.
	 * 
	 * @return If the inputed 2 date data is the same year, return
	 *         <code>true></code>, or return <code> false </code>.
	 */
	private static boolean isSameYear() {
		if (year1 == year2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Compute the number of seconds between 2 given time.
	 * 
	 * @param hour1
	 * @param minute1
	 * @param second1
	 * @param hour2
	 * @param minute2
	 * @param second2
	 * @return The number of seconds.
	 */
	private static int computeSeconds(int hour1, int minute1, int second1,
			int hour2, int minute2, int second2) {
		int result = 0;
		if (second2 < second1) {
			second2 += 60;
			minute2--;
		}
		result += second2 - second1;

		if (minute2 < minute1) {
			minute2 += 60;
			hour2--;
		}
		result += (minute2 - minute1) * 60;

		result += (hour2 - hour1) * 3600;

		return result;
	}

	/**
	 * The main program.
	 * 
	 * @param args
	 *            The command-line parameters list.
	 */
	public static void main(String[] args) {
		daysOfYear = new HashMap<>();
		int days = 0;
		for (int i = 0; i <= 2017; i++) {
			if (isLeapYear(i)) {
				days += 366;
			} else {
				days += 365;
			}
			daysOfYear.put(i, days);
		}

		Scanner scan = new Scanner(System.in);
		String str1 = scan.nextLine();
		String str2 = scan.nextLine();

		year1 = Integer.parseInt(str1.substring(0, 4));
		month1 = Integer.parseInt(str1.substring(5, 7));
		day1 = Integer.parseInt(str1.substring(8, 10));
		hour1 = Integer.parseInt(str1.substring(11, 13));
		minute1 = Integer.parseInt(str1.substring(14, 16));
		second1 = Integer.parseInt(str1.substring(17, 19));

		year2 = Integer.parseInt(str2.substring(0, 4));
		month2 = Integer.parseInt(str2.substring(5, 7));
		day2 = Integer.parseInt(str2.substring(8, 10));
		hour2 = Integer.parseInt(str2.substring(11, 13));
		minute2 = Integer.parseInt(str2.substring(14, 16));
		second2 = Integer.parseInt(str2.substring(17, 19));

		long result = 0;
		if (isSameDay()) {
			result += computeSeconds(hour1, minute1, second1, hour2, minute2,
					second2);
		} else {
			if(isLeapDay(year1, month1, day1)) {
				result += computeSeconds(hour1, minute1, second1, 23, 59, 60);
			} else {
				result += computeSeconds(hour1, minute1, second1, 23, 59, 59);
				
			}
			result += computeSeconds(0, 0, 0, hour2, minute2, second2) + 1;
			if (isSameMonth()) {
				result += (day2 - day1 - 1) * 24 * 3600;
			} else {
				result += (getDaysOfMonth(year1, month1) - day1) * 24 * 3600;
				if(month1 == 6 && day1 != 30 && isLeapDay(year1, month1, 30)) {
					result++;
				}
				if(month1 == 12 && day1 != 31 && isLeapDay(year1, month1, 31)) {
					result++;
				}
				result += (day2 - 1) * 24 * 3600;
				if (isSameYear()) {
					for (int m = month1 + 1; m < month2; m++) {
						result += getDaysOfMonth(year1, m) * 24 * 3600;
						if(m == 6 && isLeapDay(year1, m, 30)) {
							result++;
						} else if(m == 12 && isLeapDay(year1, m, 31)) {
							result++;
						}
					}
				} else {
					for (int m = month1 + 1; m <= 12; m++) {
						result += getDaysOfMonth(year1, m) * 24 * 3600;
						if(m == 6 && isLeapDay(year1, m, 30)) {
							result++;
						} else if(m == 12 && isLeapDay(year1, m, 31)) {
							result++;
						}
					}
					for (int m = 1; m < month2; m++) {
						result += getDaysOfMonth(year2, m) * 24 * 3600;
						if(m == 6 && isLeapDay(year2, m, 30)) {
							result++;
						} else if(m == 12 && isLeapDay(year2, m, 31)) {
							result++;
						}
					}
					for(int y = year1 + 1; y < year2; y++) {
						if(isLeapYear(y)) {
							result += 366 * 24 * 3600;
						} else {
							result += 365 * 24 * 3600;
						}
						
						if(isLeapDay(y, 6, 30)) {
							result++;
						} 
						if(isLeapDay(y, 12, 31)) {
							result++;
						}
					}
				}
			}
		}
		
		System.out.println(result);
		scan.close();
	}
}

