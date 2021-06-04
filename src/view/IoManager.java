package view;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class IoManager {
		
		private Scanner input;
		
		public IoManager() {
			input = new Scanner(System.in);
		}
		public void showMessage(String message) {
			System.out.println(message);
		}
		public short readShort(String message) {
			this.showMessage(message);
			return input.nextShort();
		}
		
		public int readInt(String message) {
			this.showMessage(message);
			return input.nextInt();
		}
		
		public double readDouble(String message) {
			this.showMessage(message);
			return input.nextDouble();
		}
		public String readString(String message) {
			this.showMessage(message);  
			return input.next();
		}
		
		public void showGraphicMessage(String message) {
			JOptionPane.showMessageDialog(null, message);
		}
		public short readGraphicShort(String message) {
			return Short.parseShort(JOptionPane.showInputDialog(message));
		}
		public int readGraphicInt(String message) {
			return Integer.parseInt(JOptionPane.showInputDialog(message));
		}
		public double readGraphicDouble(String message) {
			return Double.parseDouble(JOptionPane.showInputDialog(message));
		}
		public String readGraphicString(String message) {
			return JOptionPane.showInputDialog(message);
		}
		public void showErrorMessage(String message){
	        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	    }
		public short readMenu(){
	        String menuText = "1. Adicionar un empleado\n"
	                + "2. Borrar un empleado\n"
	                + "3. Informacion de la empresa\n"
	                + "4. Datos de un empleado\n"
	                + "5. Datos de todos los empleados\n"
	                + "6. Ordenar por el id\n"
	                + "7. Ordena por apellido\n"
	                + "8. Añadir empleado colado\n"
	                + "9. Intercambiar dos empleados\n"
	                + "10. Años trabajados de un empleado\n"
	                + "11. Cambio salario empleado\n"
	                + "12. Mostrar lista de empleados en un rango de tiempo\n"
	                + "13. Mostrar empleado con mejor salario\n"
	                + "14. Porcentaje de salario de los empleados\n"
	                + "15. Salir";
	        return readGraphicShort(menuText);
	    }
		
}
