package controller;

import java.util.ArrayList;
import java.util.List;
import exception.*;
import model.*;
import persistence.MyFile;
import view.IoManager;
import java.text.DecimalFormat;

public class Control {
	IoManager io;
	Business businessObj;
	MyFile myfile;
    
   public Control() {
        io = new IoManager();
        businessObj=new Business();
        myfile = new MyFile("./data/Empleados.csv");
	}
	
	public void init() {
		saveDocEmployee(myfile);
		businessObj.setName(io.readGraphicString("Nombre de la empresa"));
		businessObj.setCity(io.readGraphicString("Ciudad de la empresa"));
		this.menu();  
		this.recordEmployee();
	}
	
	public String[]cutFields(String field, String patern){
		String [] fieldArray = field.split(patern);
		return fieldArray;
	}
	
	public void saveDocEmployee(MyFile file) {
		String oneRecord;
		String [] fieldsArray;
		myfile.openFile('r');
		while ((oneRecord = file.readFile())!= null) {
			fieldsArray = this.cutFields(oneRecord, ";");
			businessObj.addEmployee(this.createEmployee(Short.parseShort(fieldsArray[0]), fieldsArray[1], fieldsArray[2], Double.parseDouble(fieldsArray[3]), 
					Short.parseShort(fieldsArray[4]), fieldsArray[5], fieldsArray[6]));
		}
		myfile.closeFile();
	}
	
	public Employee createEmployee(short id, String name, String lastName, double salary, short childrenNumber, String birthDate, String hireDate) {
		 return new Employee(id, name, lastName, salary, childrenNumber, 
		this.createDate(birthDate.split("/")), this.createDate(hireDate.split("/")));
	}
	
	public MyDate createDate(String[] dateArray) {
		return new MyDate(Short.parseShort(dateArray[0]), Short.parseShort(dateArray[1]),Short.parseShort(dateArray[2]) );
	}
	
    public void menu() {
        int op=0;
        do {
            try { // Try abraza estas sentencias porque aqui podrian ocurrir errores
                op = io.readMenu();
                switch (op) {
                     case 1:
                        addEmployee();
                        break;
                     case 2:
                         removeEmployee();
                         break;
                     case 3:
                    	 printBusinessData();
                         break;    
                     case 4:
                    	 printInfoEmpoyee();
                        break;  
                     case 5:
                    	 printEmployeesData();
                        break;  
                     case 6:
                    	 io.showGraphicMessage(printEmployeesInOrder(businessObj.ordEmployeeForId()));
                        break;  
                     case 7:
                    	 io.showGraphicMessage(printEmployeesInOrder(businessObj.ordEmployeeForLastName()));
                        break;  
                     case 8:
                    	 try {
                    		 businessObj.agregarEmpleadoColado(io.readGraphicInt("id ?"), createEmployee());
						} catch (Exception e) {
							io.showErrorMessage(e.getMessage());
						}
                     break;
                     case 9:
                    	 businessObj.changeTwoEmployee(io.readGraphicInt("Ingrese id del primer empleado"), io.readGraphicInt("Ingrese id del segundo empleado"));
                    	break;
                     case 10:
                    	 io.showGraphicMessage(""+businessObj.calculateTimeInCompany(io.readGraphicInt("Ingrese id del empleado que desea saber los años que lleva trabajando en la empresa")));
                    	break;
                     case 11:
                    	 businessObj.newContract(io.readGraphicInt("Ingrese id del empleado"), io.readGraphicDouble("Ingrese nuevo salario"));
                    	break;
                     case 12:
                    	 io.showGraphicMessage( businessObj.showEmployeeWithinRange(io.readGraphicInt("Ingrese rango menor"), io.readGraphicInt("Ingrese rango mayor")));
                    	 break;
                     case 13:
                    	 io.showGraphicMessage(businessObj.calculateSalaryHigh());
                    	 break;
                     case 14:
                    	 io.showGraphicMessage(" "+businessObj.salaryAverage());
                    	 break;
                     case 15:
                    	 io.showGraphicMessage("Salida");
                    	 break;
                    default:
                        io.showErrorMessage("Opcion invalida");
                }
            } catch (NumberFormatException e) { //
                io.showErrorMessage("Se acepta solo numeros");
            }
        } while (op != 15);
    } 
    
    public void printInfoEmpoyee(){
		ArrayList<Employee> list = businessObj.getListEmployees();
		String find = "";
	    int idToSearch = io.readGraphicInt("Ingrese id para ver la informacion");
	    for(int i = 0; i < (list.size()); i++){
			if(idToSearch == list.get(i).getId()){
			   find += "Id: "+list.get(i).getId() + "\nNombre: " + list.get(i).getName() +"\nApellido: "+ list.get(i).getLastName() + "\nSalario: "+ list.get(i).getSalary();
			}
	    }
	    io.showGraphicMessage(find);
	}
  
   public Employee createEmployee() throws DuplicateException {
		int docId = 0;
		io.showGraphicMessage("Ingrese los siguientes datos del Empleado");
		do {
			try {
				docId = io.readGraphicInt("Codigo (Entre 10-99)");
				if (docId < 10 || docId > 99)
					throw new ValidateException("el codigo debe ser entre 10 y 99");
			} catch (NumberFormatException e) {
				io.showErrorMessage("debe ser un valor numerico");
			} catch (ValidateException e) {
				io.showErrorMessage(e.getMessage());
			}
		} while (docId < 10 || docId > 99);
		if (businessObj.findEmployee(docId) != -1) {
			throw new DuplicateException("El empleado ya existe");
		} else {
			String firstName = io.readGraphicString("Nombre");
			String lastName = io.readGraphicString("Apellido");
			double salary = io.readGraphicDouble("Salario");
			short childrenNum = io.readGraphicShort("Numero de hijos");
			MyDate objDateB,objDateH ;
			objDateB = createDate("Ingrese Fecha de Nacimiento");
			objDateH = createDate("Ingrese Fecha de Contratación");
			return new Employee(docId, firstName, lastName, salary,childrenNum,objDateB,objDateH);
		}
	}
	public MyDate createDate(String message) {
		io.showGraphicMessage(message);
		short day = io.readGraphicShort("dia:");
		short month = io.readGraphicShort("Mes:");
		short year = io.readGraphicShort("anio:");
		return new MyDate(day, month, year);
	}

	public Employee createEmployee(int id, String firstName, String lastName, double salary, short childrenNumber,
			MyDate birthDate, MyDate hireDate) {
		return new Employee(id, firstName, lastName, salary, childrenNumber, birthDate, hireDate);
	}
	
    public void printBusinessData2() {
	   ArrayList<Employee> employeesList = businessObj.getListEmployees();
	   StringBuilder text = new StringBuilder();
	   for (Employee emp : employeesList) {
		   if (emp != null) {
			   text.append(emp);
			   text.append("\n");   
		   }
	   }
	   io.showGraphicMessage(text.toString());
    }
      
    public void printEmployeesData(){
	   ArrayList<Employee> employeesList = businessObj.getListEmployees();
	      StringBuilder text = new StringBuilder();
	       	for (Employee emp : employeesList) {
	           text.append(emp);
	           text.append("\n");
	        }
	      io.showGraphicMessage(text.toString());
	}
    
    public void addEmployee() {
	   try {
		   Employee emp = this.createEmployee();
		   businessObj.addEmployee(emp);
	   }catch (DuplicateException e) {
		io.showErrorMessage(e.getMessage());
	   }
    }
    
    public String printEmployeesInOrder(List<Employee> ordEmployeeForLastName) {
		StringBuilder text = new StringBuilder();
		if (ordEmployeeForLastName.isEmpty())
			text.append("No hay empleados en la empresa");
		else
			for (Employee emp : ordEmployeeForLastName)
				if (emp != null) {
					text.append(
							emp.getId() + " " + emp.getName() + " " + emp.getLastName() + " " + emp.getSalary());
					text.append("\n");
				}
		return (text.toString());
	}
   
   public void printBusinessData(){
   	io.showGraphicMessage("Nombre: "+businessObj.getName()+"\nCiudad Origen: "+businessObj.getCity());
   }
   
   public void removeEmployee() {
	   int id = io.readGraphicInt("Codigo de empleado a borrar");
	   businessObj.removeEmployee(id);
   }
   
   public void deleteEmployee() {
		businessObj.borrarEmpleado(search());
   }
   
   public int search() {
	   int id = io.readGraphicInt("Ingrese id del empleado");
	   int inicio = 0;
	   int fin = businessObj.getListEmployees().size();
	   int pos = businessObj.orderEmployeeforId(id, inicio, fin);
			return pos;
   }
   
   public void recordEmployee(){
	   myfile.openFile('w');
	   ArrayList<Employee> employeesList = businessObj.getListEmployees();
	   String record;
	   for(Employee empleado : employeesList){
		   if (empleado != null) {
			   record = "";
			   record += empleado.getId() + ";"+ empleado.getName() +";" + empleado.getLastName() +";" + empleado.getSalary() +";" +empleado.getChildrenNumber() +";"+
			   empleado.getBirthDate().getDay() +"/"+ empleado.getBirthDate().getMonth() + "/" + empleado.getBirthDate().getYear() +
			   ";"+ empleado.getHireDate().getDay() +"/"+ empleado.getHireDate().getMonth() + "/" + empleado.getHireDate().getYear();
			   myfile.recordFile(record);
					   
		   }
	   }   
	   myfile.closeFile();
   }

}  
	
