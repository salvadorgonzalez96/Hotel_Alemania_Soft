import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Habitacion{
	
	String habitacion;
	String estado;
	
	Habitacion emp[];
	
	Archivo arch;
    
	public Habitacion(){
		arch=new Archivo("Habitaciones.txt");
		emp = new Habitacion[0];
		emp = llenarArchivo("Habitaciones.txt");
	}
	
	public Habitacion(String h,String e){
		habitacion=h;
		estado=e;
	}

	public String toString(){
		return habitacion+";"+estado+";";
	}
	
	Habitacion[] HabitacionCopia(Habitacion arr[]){
		Habitacion[]cop=new Habitacion[arr.length];
        for(int i=0;i<arr.length;i++){
            cop[i]=new Habitacion(arr[i].habitacion, arr[i].estado);
        }
        return cop;
    }
	
	String retEmp(int pos){
		String cadena="";
        for(int i=0; i<emp.length;i++){
        	if(i==pos){
        		cadena = emp[i].toString();
        	}
        }
        return cadena;
	}
	
	public int length(){
		int cont=0;
		for(int i=0; i<emp.length;i++){
			cont++;
		}
        return cont;
	}
	
	Habitacion[] llenarArchivo(String nom){
		try{
			Archivo a = new Archivo(nom);
			String cadena=a.toString();
			
			String palabras="";
			String ar[]=cadena.split("\n");
	        for(int i=0;i<ar.length;i++){
	            String interno[]=ar[i].split(";");
	            palabras=ar[i].toString();
	            redefinir();
	            emp[emp.length-1] = new Habitacion(interno[0], interno[1]);
	        }
		}catch(Exception exp){
			System.out.println("No Lleno el Arreglo");
		}
		return emp;
	}
	
	public void redefinir(){
		Habitacion aux[]=new Habitacion[emp.length+1];
	    for(int i=0;i<emp.length;i++){
	    	aux[i]=emp[i];
	    }
	    emp=aux;
	}
	
	public void insertar(String h, String e){
		redefinir();
		emp[emp.length-1] = new Habitacion(h,e);
		try{
			arch.guardarstring(h+";"+e+";");
		}catch(Exception exp){
			
		}
	}
	
	public void actualizarArch(Habitacion e[]){
        try{
			File f = new File("Habitaciones.txt");
			FileWriter fw;
			BufferedWriter bw;
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			for(int i=0; i<e.length; i++){
				bw.write(e[i].toString());
				bw.newLine();
			}
			bw.close();
			fw.close();
		}catch(IOException exp) {
			System.out.println("No se puede leer el archivo Habitaciones.txt");
		}
    }
	
	public String imprimirArchivo(){
		String cadena="";
		FileReader entrada=null;
		StringBuffer str=new StringBuffer();		
		try{
			entrada=new FileReader("Habitaciones.txt");
			int c;
			while((c=entrada.read())!=-1){
				cadena+=(char)c;
			}
		}
		catch(Exception ex){System.out.println("Error al Cargar");}
		return cadena;
	}
	
	public String verArr(){
		String imp="";
		for(int i=0; i<emp.length; i++){
			imp+=emp[i].toString()+"\n";
		}
		return imp;
	}
	
	public void mod_pos(int pos, String h, String e){
		Habitacion aux[]=new Habitacion[emp.length];
        int posi=pos;
        int cont=0;
        for(int i=0; i<emp.length; i++){
            if(i==posi){
            	emp[i].habitacion=h; emp[i].estado=e;
            	aux[i]=emp[i];
            }
            else{
                aux[i]=emp[i];
            }
            cont++;
        }
        emp=aux;
        modificar();
	}
	
	public void elim_pos(int pos){
		Habitacion aux[]=new Habitacion[emp.length-1];
        //int posi=pos-1;
        int cont=0;
        for(int i=0; i<emp.length; i++){
            if(i==pos){}
            else{
                aux[cont]=emp[i];
                cont++;
            }
        }
        emp=aux;
        modificar();
	}
	
	public void modificar(){
        try{
			File f = new File("Habitaciones.txt");
			FileWriter fw;
			BufferedWriter bw;
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			for(int i=0; i<emp.length; i++){
				bw.write(emp[i].toString());
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException exp) {
			System.out.println("No se puede leer el archivo Habitaciones.txt");
		}
    }
	
	Habitacion retoEmp(int pos){
        int tam_pos=length();
        Habitacion temp;
        if(emp.length==0){
    		return null;
    	}
        if(pos<length()){
        	return temp = emp[pos];
        }
        return null;
    }
	
	public void actualizar(){
		Habitacion nuevo[] = new Habitacion[0];
		emp = nuevo;
		emp = llenarArchivo("Habitaciones.txt");
	}
 }