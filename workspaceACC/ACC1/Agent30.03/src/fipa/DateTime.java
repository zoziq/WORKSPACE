package fipa;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class implements the DateTime structure as adescribed in
 * <a href="http://www.fipa.org/specs/fipa00070/">FIPA ACL Message Representation in String Specification</a>
 * @author Aegeant-S Group
 * Aug 13, 2003
 */
public class DateTime implements IExpressionElement {


    /**
     * The pattern for a valid DateTime object
     */
	public final static String PATTERN = "[+-]?\\d{8}T\\d{9}\\p{Alpha}?";
	/**
	 * The buffer area that is used while encode/decode process
	 */
	private StringBuffer buffer = new StringBuffer();
	private GregorianCalendar calendar = new GregorianCalendar();
	private String hour, minute, second, millisecond;
	private char sign, typeDesignator;
	private boolean signed;
	private String year, month, day;


	/**
	 * Constructor.
	 */
	public DateTime() {
		setYear(calendar.get(Calendar.YEAR));
		setMonth(calendar.get(Calendar.MONTH));
		setDay(calendar.get(Calendar.DATE));
		setHour(calendar.get(Calendar.HOUR_OF_DAY));
		setMinute(calendar.get(Calendar.MINUTE));
		setSecond(calendar.get(Calendar.SECOND));
		setMillisecond(calendar.get(Calendar.MILLISECOND));
		signed = false;
	}
	
	/**
	 * Indicates whether some other object is "equal to" this one
	 */
	public boolean equals(Object o) {
		if (o instanceof DateTime) {
			DateTime dt = (DateTime)o;
			return (dt.getDay().equals(getDay()) && dt.getHour().equals(getHour()) &&
			dt.getMillisecond().equals(getMillisecond()) && dt.getMinute().equals(getMinute()) &&
			dt.getMonth().equals(getMonth()) && dt.getSecond().equals(getSecond()) &&
			(dt.getTypeDesignator() == getTypeDesignator()) &&
			((dt.isSigned() && dt.getSign() == getSign()) || !dt.isSigned()));
		}
		return false;
	}

	/**
	 * Getter of day.
	 * @return day.
	 */
	public String getDay() {
		return day;
	}


	/**
	 * Getter of dateTimeBuffer.
	 * @return the content of dateTimeBuffer in String format.
	 */
	public String getEncodedStr() {
		return buffer.toString();
	}

	/**
	 * Getter of hour.
	 * @return hour.
	 */
	public String getHour() {
		return hour;
	}

	/**
	 * Getter of millisecond.
	 * @return millisecond.
	 */
	public String getMillisecond() {
		return millisecond;
	}

	/**
	 * Getter of minute.
	 * @return minute.
	 */
	public String getMinute() {
		return minute;
	}

	/**
	 * Getter of month.
	 * @return month.
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Getter of second.
	 * @return second.
	 */
	public String getSecond() {
		return second;
	}

	/**
	 * Getter of sign.
	 * @return + or - character.
	 */
	public char getSign() {
		return sign;
	}

	/**
	 * Getter of typeDesignator.
	 * @return typeDesignator.
	 */
	public char getTypeDesignator() {
		return typeDesignator;
	}

	/**
	 * Getter of year.
	 * @return year.
	 */
	public String getYear() {
		return year;
	}

	/* public auxiliary methods */

	/**
	 * Is sign is used?.
	 * @return true if yes.
	 */
	public boolean isSigned() {
		return signed;
	}

	/**
	 * Setter of day. Defaults to 1 if the parameter < 1 or parameter > 31
	 * @param day value of day. 	 
	 */
	public void setDay(int day) {
		if (day < 1 || day > 31)
			day = 1;
		this.day = String.valueOf(day);
		if (this.day.length() == 1)
			this.day = "0" + this.day;
	}

	/**
	 * Setter of dateTimeBuffer area.
	 * @param encodedStr the string to be set to the dateTimeBuffer. 	 
	 */
	public void setEncodedStr(String encodedStr) {
		buffer = new StringBuffer(encodedStr);
	}

	/**
	 * Setter of hour. Defaults to 0 if the parameter < 0 or parameter > 23
	 * @param hour value of hour. 	 
	 */
	public void setHour(int hour) {
		if (hour < 0 || hour > 23)
			hour = 0;
		this.hour = String.valueOf(hour);
		if (this.hour.length() == 1)
			this.hour = "0" + this.hour;
	}

	/**
	 * Setter of millisecond. Defaults to 0 if the parameter < 0 or parameter > 999
	 * @param millisecond value of millisecond. 	 
	 */
	public void setMillisecond(int millisecond) {
		if (millisecond < 0 || millisecond > 999)
			millisecond = 0;
		this.millisecond = String.valueOf(millisecond);
		if (this.millisecond.length() == 1) {
			this.millisecond = "00" + this.millisecond;
		} else if (this.millisecond.length() == 2) {
			this.millisecond = "0" + this.millisecond;
		}
	}

	/**
	 * Setter of minute. Defaults to 0 if the parameter < 0 or parameter > 59
	 * @param minute value of minute. 	 
	 */
	public void setMinute(int minute) {
		if (minute < 0 || minute > 59)
			minute = 0;
		this.minute = String.valueOf(minute);
		if (this.minute.length() == 1)
			this.minute = "0" + this.minute;
	}

	/**
	 * Setter of month. Defaults to 1 if the parameter < 1 or parameter > 12
	 * @param month value of month. 
	 */
	public void setMonth(int month) {
		if (month < 1 || month > 12)
			month = 1;
		this.month = String.valueOf(month);
		if (this.month.length() == 1)
			this.month = "0" + this.month;
	}

	/**
	 * Setter of second. Defaults to 0 if the parameter < 0 or parameter > 59
	 * @param second  	 
	 */
	public void setSecond(int second) {
		if (second < 0 || second > 59)
			second = 0;
		this.second = String.valueOf(second);
		if (this.second.length() == 1)
			this.second = "0" + this.second;
	}

	/**
	 * Setter of sign. Defaults to '+' if the parameter != '+' or parameter != '-'
	 * @param c + or - character. 	 
	 */
	public void setSign(char c) {
		if (c != '+' || c != '-')
			sign = '+';
		else
			sign = c;
		setSigned(true);
	}

	/**
	 * Setter of typeDesignator.
	 * @param c typeDesignator c. 	 
	 */
	public void setTypeDesignator(char c) {
		typeDesignator = c;
	}

	/**
	 * Setter of year. 
	 * Defaults to 1000 if the parameter < 1000 or parameter > 9999
	 * @param year 	 
	 */
	public void setYear(int year) {
		if (year < 1000 || year > 9999)
			year = 1000;
		this.year = String.valueOf(year);
	}

	/**
	 * Sets the signed.
	 * @param s true if you want to use signs. 	 
	 */
	private void setSigned(boolean s) {
		signed = s;
	}

}