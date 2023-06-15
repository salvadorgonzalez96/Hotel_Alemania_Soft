import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
															//Runnable Implementacion para Tiempo Real
public class Reservacion extends JFrame implements ActionListener,Runnable
{
	Calendar calendario = new GregorianCalendar();
	final Date fecha = calendario.getTime();
	
	ArrayList<JButton> btnroom=new ArrayList<JButton>();
	
	Font fuente;
	JLabel label,labelfecha,labelhor;
	Thread hilo;
	
	void close(){
		//if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
			//	JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			//System.exit(0);
			new Ventana(Sesion.iduser, Sesion.us);
			dispose();
		//}
	}
	
	public Reservacion(String idus, String us){
		super("Reservacion");
        
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
		
		Font font = new Font("Modern No. 20",0,25);
		label=new JLabel("Reservación de Habitación");
		label.setBounds(20,10,600,40);
		label.setFont(font);
		getContentPane().add(label);
		
		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		//
		arregloHabitacion();
		//
		
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
	
	public void arregloHabitacion(){
		Color col = new Color(51,153,255);
		String id="",estado="",tipo="";
		int xs=50,ys=60,n=0, xd=350,yd=60,xt=550,yt=60,xf=750,yf=60,xp=850,yp=650;
		try{
			Connection con=new Conexion().getConexion();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("select habitacion_codigo,habitacion_estado,habitacion_tipo from tbl_habitacion"+
										 " group by habitacion_codigo;");
			while(rs.next()){
				id=rs.getString(1);
				estado=rs.getString(2);
				tipo=rs.getString(3);
				
				if(tipo.equals("Sencilla")){
					btnroom.add(new JButton (id));
					btnroom.get(n).setBounds(xs,ys,65,30);
					ys+=40;
					if(ys==660){
						xs+=65;
						if(xs==180){
							label=new JLabel();
							TitledBorder titled = new TitledBorder(tipo);
							titled.setTitleColor(Color.BLUE);
							label.setBorder(titled);
							label.setBounds(30,40,xs+45,ys-35);
							label.setForeground(Color.BLACK);
							getContentPane().add(label);
						}
						ys=60;
					}
				}
				else if(tipo.equals("Doble")){
					btnroom.add(new JButton (id));
					btnroom.get(n).setBounds(xd,yd,85,30);
					yd+=40;
					if(yd==580){
						JLabel label1=new JLabel();
						TitledBorder titled1 = new TitledBorder(tipo);
						titled1.setTitleColor(Color.BLUE);
						label1.setBorder(titled1);
						label1.setBounds(335,40,110,yd-35);
						label1.setForeground(Color.BLACK);
						getContentPane().add(label1);
						xd+=65;
						yd=60;
					}
				}
				else if(tipo.equals("Triple")){
					btnroom.add(new JButton (id));
					btnroom.get(n).setBounds(xt,yt,65,30);
					yt+=40;
					if(yt==650){
						yt=50;
						xt+=65;
					}
				}
				else if(tipo.equals("Familiar")){
					btnroom.add(new JButton (id));
					btnroom.get(n).setBounds(xf,yf,65,30);
					yf+=40;
					if(yf==650){
						yf=50;
						xf+=65;
					}
				}
				else if(tipo.equals("Penthouse")){
					JOptionPane.showMessageDialog(null, "Ay Aja");
					btnroom.add(new JButton (id));
					btnroom.get(n).setBounds(xp,yp,200,30);
					/*yp+=40;
					if(yp==650){
						yp=50;
						xp+=65;
					}*/
				}
				if(tipo.equals("Penthouse")){
					JOptionPane.showMessageDialog(null, "Ay Aja");
				}
				
				if(estado.equals("Disponible")){
					btnroom.get(n).setBackground(Color.GREEN);
				}
				else if(estado.equals("Ocupado")){
					btnroom.get(n).setBackground(Color.RED);
				}
				else if(estado.equals("Limpieza")){
					btnroom.get(n).setBackground(col);
				}
				else if(estado.equals("Mantenimiento")){
					btnroom.get(n).setBackground(Color.YELLOW);
				}
				getContentPane().add(btnroom.get(n));
				n++;
			}
			con.close();
		}
		catch(Exception exp){
			//JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
		}
		
		/*for(int i=0; i<btnroom.size();i++){
			btnroom.get(i).setBounds(50,y,60,30);
			getContentPane().add(btnroom.get(i));
			btnroom.get(i).addActionListener(this);
			//JOptionPane.showMessageDialog(null, y);
			btnroom.get(i).setBackground(Color.GREEN);
			y+=50;
			//JOptionPane.showMessageDialog(null, btnroom.get(0).getText().toString());
			//JOptionPane.showMessageDialog(null, btnroom.get(i));
		}*/
	}
	
	public void actionPerformed(ActionEvent evt){}
	
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