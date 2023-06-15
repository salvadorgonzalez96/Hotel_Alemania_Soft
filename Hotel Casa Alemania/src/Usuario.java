import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Usuario{
	
	String id;
	String user;
	String pass;
	String estado;
	
	Usuario us[];
	
	Archivo arch;
    
	public Usuario(){
		arch=new Archivo("Usuario.txt");
		us = new Usuario[0];
		us = llenarArchivo("Usuario.txt");
	}
	
	public Usuario(String i,String u, String p, String e) {
		id=i;
		user=u;
		pass=p;
		estado=e;
	}

	public String toString(){
		return id+";"+user+";"+pass+";"+estado+";";
	}
	
	Usuario[] UsuarioCopia(Usuario arr[]){
		Usuario[]cop=new Usuario[arr.length];
        for(int i=0;i<arr.length;i++){
            cop[i]=new Usuario(arr[i].id, arr[i].user, arr[i].pass, arr[i].estado);
        }
        return cop;
    }
	
	String retEmp(int pos){
		String cadena="";
        for(int i=0; i<us.length;i++){
        	if(i==pos){
        		cadena = us[i].toString();
        	}
        }
        return cadena;
	}
	
	public int length(){
		int cont=0;
		for(int i=0; i<us.length;i++){
			cont++;
		}
        return cont;
	}
	
	Usuario[] llenarArchivo(String nom){
		try{
			Archivo a = new Archivo(nom);
			String cadena=a.toString();
			
			String palabras="";
			String ar[]=cadena.split("\n");
	        for(int i=1;i<ar.length;i++){
	            String interno[]=ar[i].split(";");
	            palabras=ar[i].toString();
	            redefinir();
	            us[us.length-1] = new Usuario(interno[0], interno[1], interno[2],interno[3]);
	        }
		}catch(Exception exp){
			System.out.println("No Lleno el Arreglo");
		}
		return us;
	}
	
	public void redefinir(){
		Usuario aux[]=new Usuario[us.length+1];
	    for(int i=0;i<us.length;i++){
	    	aux[i]=us[i];
	    }
	    us=aux;
	}
	
	public void insertar(String i, String u, String p, String e){
		redefinir();
		us[us.length-1] = new Usuario(i,u,p,e);
		try{
			arch.guardarstring(i+";"+u+";"+p+";"+e+";");
		}catch(Exception exp){}
	}
	
	public void actualizarArch(Usuario e[]){
        try{
			File f = new File("Usuario.txt");
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
			System.out.println("No se puede leer el archivo Usuario.txt");
		}
    }
	
	public String imprimirArchivo(){
		String cadena="";
		FileReader entrada=null;
		StringBuffer str=new StringBuffer();		
		try{
			entrada=new FileReader("Usuario.txt");
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
		for(int i=0; i<us.length; i++){
			imp+=us[i].toString()+"\n";
		}
		return imp;
	}
	
	public void mod_pos(int pos, String id, String u, String p, String e){
		Usuario aux[]=new Usuario[us.length];
        int posi=pos;
        int cont=0;
        for(int i=0; i<us.length; i++){
            if(i==posi){
            	us[i].id=id; us[i].user=u; us[i].pass=p; us[i].estado=e;
            	aux[i]=us[i];
            }
            else{
                aux[i]=us[i];
            }
            cont++;
        }
        us=aux;
        modificar();
	}
	
	public void elim_pos(int pos){
		Usuario aux[]=new Usuario[us.length-1];
        //int posi=pos-1;
        int cont=0;
        for(int i=0; i<us.length; i++){
            if(i==pos){}
            else{
                aux[cont]=us[i];
                cont++;
            }
        }
        us=aux;
        modificar();
	}
	
	public void modificar(){
        try{
			File f = new File("Usuario.txt");
			FileWriter fw;
			BufferedWriter bw;
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			bw.write("Codigo;Usuario;Clave;Estado;");
			bw.newLine();
			for(int i=0; i<us.length; i++){
				bw.write(us[i].toString());
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException exp) {
			System.out.println("No se puede leer el archivo Usuario.txt");
		}
    }
	
	Usuario retoEmp(int pos){
        int tam_pos=length();
        Usuario temp;
        if(us.length==0){
    		return null;
    	}
        if(pos<length()){
        	return temp = us[pos];
        }
        return null;
    }
	
	public void actualizar(){
		Usuario nuevo[] = new Usuario[0];
		us = nuevo;
		us = llenarArchivo("Usuario.txt");
	}
 }