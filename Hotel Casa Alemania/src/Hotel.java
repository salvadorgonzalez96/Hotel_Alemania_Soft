import javax.swing.*;
import java.awt.*;

public class Hotel extends JDialog{

	ArregloArchivo ar;
	
	/*public static void main(String args[]){
    	new Form();
    }*/
	
	void close(){
		if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			//System.exit(0);
			new Ventana(Sesion.iduser, Sesion.us);
			dispose();
		}
	}
	
    public Hotel(String iduser, String user){
    	//setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    	Sesion.iduser = iduser;
    	Sesion.us = user;
    	setTitle("Hotel");
    	
    	setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
		
    	ar=new ArregloArchivo();
		
    	getContentPane().add(build());
    	
    	setSize(500,350);
    	setLocationRelativeTo(null);
    	setVisible(true);
    	setResizable(false);
    }
    
    public JComponent build(){
    	JTabbedPane pest = new JTabbedPane();
    	
    	/*Nuevo n = new Nuevo();
    	Nuevo n2 = new Nuevo();
    	ImageIcon icono = new ImageIcon("mag.gif");*/
    	
    	//Reservacion re = new Reservacion();
    	//Desocupacion de = new Desocupacion();
    	
    	//pest.addTab("Reservar", null, re.build(Sesion.iduser, Sesion.us), "Reservacion de Habitacion");
    	//pest.addTab("Desocupacion", null, de.build(Sesion.iduser, Sesion.us), "Desocupacion de Habitacion");
        
    	return pest;
    }
}