package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Employee {
	
   private int id;
   private String name;
   private String lastName;
   private double salary;
   private short childrenNumber;
   private MyDate hireDate;
   private MyDate birthDate;
   
   
   public Employee(int id, String name, String lastName, double salary, short childrenNumber, MyDate birthDate, MyDate hireDate) {
	   this.setId(id);
	   this.setName(name);
	   this.setLastName(lastName);
	   this.setSalary(salary);
	   this.setChildrenNumber(childrenNumber);
	   this.setBirthDate(birthDate);
	   this.setHireDate(hireDate);
	   
   }
   public Employee(int id, String name, String lastName, double salary, int childrenNumber) {
	   this.setId(id);
	   this.setName(name);
	   this.setLastName(lastName);
	   this.setSalary(salary);
   }
   
   
   public String getLastName() {
	return lastName;
   }


public void setLastName(String lastName) {
	this.lastName = lastName;
}


public int getId() {
	   return id;
   }
   public void setId(int id) {
	   this.id = id;
   }
   public String getName() {		
	   return name;
   }
   public void setName(String name) {
	   this.name = name;
   }
   public double getSalary() {
	   return salary;
   }
   public void setSalary(double salary) {
	   this.salary = salary;
   }
   
   public short getChildrenNumber() {
	return childrenNumber;
}
public void setChildrenNumber(short childrenNumber) {
	this.childrenNumber = childrenNumber;
}
public MyDate getHireDate() {
	   return hireDate;
   }
   public void setHireDate(MyDate hireDate) {
	   this.hireDate = hireDate;
   }
   public MyDate getBirthDate() {
	   return birthDate;
   }
   public void setBirthDate(MyDate birthDate) {
	   this.birthDate = birthDate;
   }
   
   public int getAge() {
       return getOldDate(this.birthDate);
   }

   public int getOldJob() {
       return getOldDate(this.hireDate);
   }

   private int getOldDate(MyDate date) {
       Calendar fecha = new GregorianCalendar();
       int OldJob = (fecha.get(Calendar.YEAR) - date.getYear());
       int monthsDiff = ((fecha.get(Calendar.MONTH) + 1) - date.getMonth());
       if (monthsDiff < 0 || (monthsDiff == 0 && fecha.get(Calendar.DAY_OF_MONTH) < date.getDay())) {
           OldJob--;
       }
       return OldJob;
   }

@Override
  public String toString() { 
	return "Codigo:" + this.id + " Nombre:" + this.name + " Salario:"+ this.salary ;
  }
}