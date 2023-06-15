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
public class Ventana extends JFrame implements ActionListener,Runnable
{
	Calendar calendario = new GregorianCalendar();
	final Date fecha = calendario.getTime();
	
	JButton btnusuario,btnHotel,btncliente,btnFactura;
	JButton btnreservacion,btndesocupacion,btnventa,btncotizacion,btnmantenimiento;
	JButton btnconfig;
	Archivo arc;
	
	Font fuente;
	JLabel labelfecha,labelhor;
	Thread hilo;
	
	void close(){
		if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar el Sistema?","Salir del Sistema",
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			//System.exit(0);
			new Main();
			dispose();
		}
	}
	
	public Ventana(String idus, String us){
		super("Menu Principal");
        
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
		ArregloArchivo ar=new ArregloArchivo();
		Sesion.iduser = idus;
		Sesion.us = us;
		getContentPane().setLayout(null);
		
		Font font = new Font("Modern No. 20",0,55);
		JLabel label=new JLabel("Bienvenido "+Sesion.us);
		label.setBounds(380,70,600,40);
		label.setFont(font);
		getContentPane().add(label);
		
		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		Font fuente = new Font("Modern No. 20",0,25);
		btnreservacion=new JButton("Reservacion");
		btnreservacion.setBounds(100,250,180,50);
		btnreservacion.setFont(fuente);
		getContentPane().add(btnreservacion);
		btnreservacion.addActionListener(this);
		
		btndesocupacion=new JButton("Desocupacion");
		btndesocupacion.setBounds(500,250,180,50);
		btndesocupacion.setFont(fuente);
		getContentPane().add(btndesocupacion);
		btndesocupacion.addActionListener(this);
		
		btnmantenimiento=new JButton("Mantenimiento");
		btnmantenimiento.setBounds(100,375,200,50);
		btnmantenimiento.setFont(fuente);
		getContentPane().add(btnmantenimiento);
		btnmantenimiento.addActionListener(this);
		
		btncotizacion=new JButton("Cotizacion");
		btncotizacion.setBounds(100,500,180,50);
		btncotizacion.setFont(fuente);
		getContentPane().add(btncotizacion);
		btncotizacion.addActionListener(this);
		
		btnventa=new JButton("Venta");
		btnventa.setBounds(500,500,180,50);
		btnventa.setFont(fuente);
		getContentPane().add(btnventa);
		btnventa.addActionListener(this);
		
		//---------------------------------------------------------------------------------
		btnconfig=new JButton("Configuracion");
		btnconfig.setBounds(950,55,180,50);
		btnconfig.setFont(fuente);
		getContentPane().add(btnconfig);
		btnconfig.addActionListener(this);
		
		//---------------------------------------------------------------------------------
		btnusuario=new JButton("Sis. Empleado");
		btnusuario.setBounds(950,300,180,50);
		btnusuario.setFont(fuente);
		getContentPane().add(btnusuario);
		btnusuario.addActionListener(this);
		//btnusuario.setVisible(ar.bus_linea("Usuario_Acceso.txt",Sesion.iduser+";11;"));
		
		btnFactura=new JButton("Factura Mes");
		btnFactura.setBounds(950,400,180,50);
		btnFactura.setFont(fuente);
		getContentPane().add(btnFactura);
		btnFactura.addActionListener(this);
		//btnFactura.setVisible(true);
		
		fuente = new Font("Modern No. 20",0,40);
		labelfecha=new JLabel("");
		labelfecha.setBounds(60,680,1000,40);
		labelfecha.setFont(fuente);
		getContentPane().add(labelfecha);
		
		fuente = new Font("Modern No. 20",0,30);
		labelhor=new JLabel("");
		labelhor.setBounds(60,700,1000,40);
		labelhor.setFont(fuente);
		getContentPane().add(labelhor);
		
		//Hilo Para Hora
		hilo = new Thread(this);
		hilo.start();
		
		label = new JLabel("");
		label.setBounds(0,0,1200,800);
		label.setIcon(new ImageIcon("fondo.jpg"));
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
		if(evt.getSource()==btnconfig){
			new Form(Sesion.iduser, Sesion.us);
			dispose();
		}
		if(evt.getSource()==btnreservacion){
			new Reservacion(Sesion.iduser, Sesion.us);
			dispose();
		}
		if(evt.getSource()==btnventa){
			new OpcionesVentas(Sesion.iduser, Sesion.us);
			dispose();
		}
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