import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Empleado{
	
	String cedula;
	String nombre;
	String apellido;
	String depto;
	double salario;
	double deduccion;
	
	Empleado emp[];
	
	Archivo arch;
    
	public Empleado(){
		arch=new Archivo("Empleado.txt");
		emp = new Empleado[0];
		emp = llenarArchivo("Empleado.txt");
	}
	
	public Empleado(String c,String n, String a, String d, double s, double de) {
		cedula=c;
		nombre=n;
		apellido=a;
		depto=d;
		salario=s;
		deduccion=de;
	}

	public String toString(){
		return cedula+";"+nombre+";"+apellido+";"+depto+";"+salario+";"+deduccion+";";
	}
	
	Empleado[] EmpleadoCopia(Empleado arr[]){
		Empleado[]cop=new Empleado[arr.length];
        for(int i=0;i<arr.length;i++){
            cop[i]=new Empleado(arr[i].cedula, arr[i].nombre, arr[i].apellido, arr[i].depto, arr[i].salario, arr[i].deduccion);
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
	
	Empleado[] llenarArchivo(String nom){
		try{
			Archivo a = new Archivo(nom);
			String cadena=a.toString();
			
			String palabras="";
			String ar[]=cadena.split("\n");
	        for(int i=0;i<ar.length;i++){
	            String interno[]=ar[i].split(";");
	            palabras=ar[i].toString();
	            redefinir();
	            emp[emp.length-1] = new Empleado(interno[0], interno[1], interno[2],interno[3], Double.parseDouble(interno[4]), Double.parseDouble(interno[5]));
	        }
		}catch(Exception exp){
			System.out.println("No Lleno el Arreglo");
		}
		return emp;
	}
	
	public void redefinir(){
		Empleado aux[]=new Empleado[emp.length+1];
	    for(int i=0;i<emp.length;i++){
	    	aux[i]=emp[i];
	    }
	    emp=aux;
	}
	
	public void insertar(String c, String n, String a, String d,double s, double ded){
		redefinir();
		emp[emp.length-1] = new Empleado(c,n,a,d,s,ded);
		try{
			arch.guardarstring(c+";"+n+";"+a+";"+d+";"+s+";"+ded+";");
		}catch(Exception exp){
			
		}
	}
	
	public void actualizarArch(Empleado e[]){
        try{
			File f = new File("Empleado.txt");
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
			System.out.println("No se puede leer el archivo Empleado.txt");
		}
    }
	
	public String imprimirArchivo(){
		String cadena="";
		FileReader entrada=null;
		StringBuffer str=new StringBuffer();		
		try{
			entrada=new FileReader("Empleado.txt");
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
	
	public void mod_pos(int pos, String c, String n, String a, String d, double s, double de){
		Empleado aux[]=new Empleado[emp.length];
        int posi=pos;
        int cont=0;
        for(int i=0; i<emp.length; i++){
            if(i==posi){
            	emp[i].cedula=c; emp[i].nombre=n; emp[i].apellido=a; emp[i].depto=d; emp[i].salario=s; emp[i].deduccion=de;
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
		Empleado aux[]=new Empleado[emp.length-1];
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
			File f = new File("Empleado.txt");
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
			System.out.println("No se puede leer el archivo Empleado.txt");
		}
    }
	
	Empleado retoEmp(int pos){
        int tam_pos=length();
        Empleado temp;
        if(emp.length==0){
    		return null;
    	}
        if(pos<length()){
        	return temp = emp[pos];
        }
        return null;
    }
	
	public void actualizar(){
		Empleado nuevo[] = new Empleado[0];
		emp = nuevo;
		emp = llenarArchivo("Empleado.txt");
	}
 }