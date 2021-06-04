package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import persistence.MyFile;

public class Business {
	private ArrayList<Employee> listEmployees ;
	private String name;
	private String city;
	private MyFile myfile;
	
	public Business() {
		this.listEmployees = new ArrayList<Employee>();
	}
	
	public ArrayList<Employee> getListEmployees() {
		return listEmployees;
	}

	public void setListEmployees(ArrayList<Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public void cambiarEmpleado(Employee newEmployee) {
		listEmployees.set(2, newEmployee);
	}
	
	public void deleteEmployee(Employee empleado) {
		listEmployees.remove(empleado);
	}
	
	public void clearListEmployees() {
		listEmployees.clear();
	}
	
	public int cantidadEmpleados(){
		return listEmployees.size();
	}
	
	public void borrarEmpleado(int index) {
		listEmployees.remove(index);
		
	}
	
	public void newContract(int id, double newSalary) {
		int inicio = 0;
		int fin = listEmployees.size();
		listEmployees.get(orderEmployeeforId(id, inicio, fin)).setSalary(newSalary);
	}
	
	public boolean comprobarEmpleado(Employee nameEmployee) {
		boolean employee = false;
	    if (listEmployees.contains(nameEmployee))
	    	employee = true;
	    return employee;
	}
	
	public void addEmployee(Employee employee) {
		listEmployees.add(employee);
	}
	
	public String muestraListaEmpleados(){
		String salida="";
		Iterator<Employee> itrListaEmpleados = listEmployees.iterator();
	    while(itrListaEmpleados.hasNext()){
	    	Employee empleado = itrListaEmpleados.next();
	    	salida+=empleado.getId()+" "+empleado.getName()+" "+empleado.getSalary()+" | ";
	    }	
		return salida;
	}
	public void removeEmployee(int id) {
		int position;
		position = this.findEmployee(id);
		if (position!= -1) {
			listEmployees.remove(position);
		}
	}
	public int findEmployee(int id) {
		int i = 0;
		boolean find = false;
		Iterator iteratorEmployee = listEmployees.iterator();
		Employee actualEmployee;
		while (iteratorEmployee.hasNext() && !find) {
			actualEmployee = (Employee)iteratorEmployee.next();
			i++;
			if (actualEmployee.getId()==id) 
				find = true;
		}
		if (find) return i-1;
		else return -1;
	}
	
	public String muestraListaEmpleados2(){
    	StringBuilder text = new StringBuilder();
    	for (Employee emp : listEmployees) {
            if (emp != null) {
                text.append(emp.getId()+" "+emp.getName()+" "+emp.getSalary());
                text.append("\n");
            }
        }
    	return(text.toString());
    }
	public int orderEmployeeforId(int id , int inicio, int fin) {
		int media;
		int pos = -1;
		while((pos == -1)&&(inicio <= fin)) {
			media = (inicio + fin)/2;
			if(listEmployees.get(media).getId() == id)
				pos = media;
			else if(id > listEmployees.get(media).getId())
				pos = orderEmployeeforId(id, inicio = media + 1, fin);
			else 
				pos = orderEmployeeforId(id, inicio, fin = media - 1);
		}
		return pos;
	}
	
	public void nuevoContrato(int id, double salario) {
		int inicio = 0;
		int fin = listEmployees.size();
		listEmployees.get(orderEmployeeforId(id, inicio, fin)).setSalary(salario);
		
	}
	
	public void agregarEmpleadoColado (int id, Employee employee) {
		listEmployees.add(id, employee); 
	}
	
	public ArrayList<Employee> ordEmployeeForLastName() {
		ArrayList<Employee> ordList = listEmployees;
		for (int i = 0; i < (ordList.size() - 1); i++) 
			for (int j = i + 1; j < ordList.size(); j++) {
				if (ordList.get(i).getLastName().compareToIgnoreCase(ordList.get(j).getLastName()) > 0) {
					Employee aux = ordList.get(i);
					ordList.set(i, ordList.get(j));
					ordList.set(j, aux);
				}
			}
		return ordList;
	}
	
	public ArrayList<Employee> ordEmployeeForId() {
		ArrayList<Employee> ordList = listEmployees;
		for (int i = 0; i < (ordList.size() - 1); i++) 
			for (int j = i + 1; j < ordList.size(); j++) {
				if (ordList.get(i).getId() > ordList.get(j).getId()) {
					Employee aux = ordList.get(i);
					ordList.set(i, ordList.get(j));
					ordList.set(j, aux);
				}
			}
		return ordList;
	}
	public void changeTwoEmployee (int idOne, int idTwo){
		int posOne = findEmployee(idOne);
		int posTwo = findEmployee(idTwo);
		
		if ( posOne != -1 && posTwo != -1 ) {
			Employee empOne = listEmployees.get(posOne);
			Employee empTwo = listEmployees.get(posTwo);
			listEmployees.set(posTwo, empOne);
			listEmployees.set(posOne, empTwo);
		}	
	}
	public int calculateTimeInCompany (int id) {
	int find = findEmployee(id);
	int oldJob = -1;
		if (find != -1)
			oldJob = listEmployees.get(find).getOldJob();	
		
	return oldJob;
	}
	public String showEmployeeWithinRange (int one, int two) {
		String aux = "";
		for (int i = 0; i < listEmployees.size(); i++) {
			if (listEmployees.get(i).getOldJob() >= one && listEmployees.get(i).getOldJob() < two) {
				aux += listEmployees.get(i).getName() + "  " + listEmployees.get(i).getOldJob() + "\n";  
			}
		}
		return  aux;
	}
	public String calculateSalaryHigh() {
	double max = 0;
	String aux = "";
	int auxTwo = 0;
    for (int i = 0; i < listEmployees.size(); i++) {
        if (listEmployees.get(i).getSalary() > max) {
            max = listEmployees.get(i).getSalary(); 
            auxTwo = i;
        	}
    	}
    	aux = listEmployees.get(auxTwo).toString() ;
    	return  aux;
	}
	public double salaryAverage () {
	
		double salaryTotal = 0;	
		for (int i = 0; i < listEmployees.size(); i++) {
			salaryTotal += listEmployees.get(i).getSalary();		
		}
		
		return  salaryTotal/listEmployees.size();
	}
	
	/*public String salaryAverage () {
		String aux = "";
		for (int i = 0;i < listEmployees.size(); i++) {
			for (int j = 1; j < listEmployees.size(); j++) {
				if(listEmployees.get(i).getSalary() > listEmployees.get(j).getSalary() ) {
					aux = listEmployees.get(i).getName() + "  " + listEmployees.get(i).getSalary();
				}
			}	
		}
		return aux;
	}*/
}