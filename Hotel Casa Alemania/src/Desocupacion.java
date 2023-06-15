import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
															//Runnable Implementacion para Tiempo Real
public class Desocupacion extends JFrame implements ActionListener,Runnable
{
	Calendar calendario = new GregorianCalendar();
	final Date fecha = calendario.getTime();
	
	JButton btnroom[];
	Archivo arc;
	
	Font fuente;
	JLabel labelfecha,labelhor;
	Thread hilo;
	
	void close(){
		//if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
			//	JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			//System.exit(0);
			new Main();
			dispose();
		//}
	}
	
	public Desocupacion(String idus, String us){
		super("Desocupacion");
        
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
		Sesion.iduser = idus;
		Sesion.us = us;
		getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(193,205,205));
		
		Font font = new Font("Modern No. 20",0,55);
		JLabel label=new JLabel("Desocupacion de Habitacion");
		label.setBounds(300,70,600,40);
		label.setFont(font);
		getContentPane().add(label);
		
		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		/*btnroom=new JButton("Sencilla");
		btnroom.setBounds(40,290,120,40);
		getContentPane().add(btnroom);
		btnroom.addActionListener(this);*/
		
		fuente = new Font("Modern No. 20",0,40);
		labelfecha=new JLabel("");
		labelfecha.setBounds(60,680,1000,40);
		labelfecha.setFont(fuente);
		getContentPane().add(labelfecha);
		
		//Hilo Para Hora
		hilo = new Thread(this);
		hilo.start();
		
		label = new JLabel("");
		label.setBounds(0,0,1200,800);
		label.setIcon(new ImageIcon("fondo.png"));
		getContentPane().add(label);
		
		setSize(1200,800);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public String User(int n){
		arc=new Archivo("Usuario.txt");
    	String lines[]=arc.toString().split("\n");
    	for(int i=1;i<lines.length;i++){
    	    String inter[]=lines[i].split(";");
    	    if(i==n){
    	        return inter[1].toString().trim();
    	    }
    	}
    	return "";
    }
	
	public void actionPerformed(ActionEvent evt){
		
	}
	
	//Metodo Para Hora
	public void run(){
		 Thread ct = Thread.currentThread();
		 while(ct == hilo) {   
			 Calendar calendario = new GregorianCalendar();
			 Date fecha = calendario.getTime();
			 int dia = calendario.get(Calendar.DAY_OF_MONTH);
			 //String mes = calendario.getDisplayName(Calendar.MONTH,2,"pm");
			 int mes = calendario.get(Calendar.MONTH)+1;
			 int anio = calendario.get(Calendar.YEAR);
			 calendario.setTime(fecha);
			 //labelfecha.setText("Fecha: "+dia+"-"+mes+"-"+anio);
			 labelfecha.setText(fecha.toLocaleString());
			 //labelhor.setText("Hora: "+fecha.getHours()+":"+fecha.getMinutes()+":"+fecha.getSeconds());
		  try {
		   Thread.sleep(1000);
		  }catch(InterruptedException e) {}
		}
	}
}