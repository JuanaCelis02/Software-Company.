package model;

public class MyDate {
	
	private short year;
	private short month;
	private short day;
	
	public MyDate(short day, short month, short year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public short getYear() {
		return year;
	}
	public void setYear(short year) {
		this.year = year;
	}
	public short getMonth() {
		return month;
	}
	public void setMonth(short month) {
		this.month = month;
	}
	public short getDay() {
		return day;
	}
	public void setDay(short day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "Anio: " + this.year + ", Mes: " + this.month + ", Dia: " + this.day;
	}
	
}
