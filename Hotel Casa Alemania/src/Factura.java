import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class Factura extends JFrame implements ActionListener, KeyListener
{
	Calendar calendario = new GregorianCalendar();
	int dia = calendario.get(Calendar.DAY_OF_MONTH);
	int mes = calendario.get(Calendar.MONTH)+1;
	int anio = calendario.get(Calendar.YEAR);
	int hora = calendario.get(Calendar.HOUR_OF_DAY);
	int min = calendario.get(Calendar.MINUTE);
	int seg = calendario.get(Calendar.SECOND);
	
	int boton, dias;
	static double imp=0, ph=0, total=0;
	static boolean cambio=false;
	String room;
	static Archivo arc;
	
	JTextField txtCedula, txtNombre, txtDias;
	static JTextField txtPagoH, txtImp, txtPagoT;
	static JComboBox /*cmbAfi,*/ cmbH;
	
	JButton btnguardar, btnactualizar;
	JButton btnsalir;
	static boolean seguir=false;
	//JPanel panel;
	
	/*public static void main(String args[]){
		new Factura();
	}*/
	
	void close(){
		if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			//System.exit(0);
			//new Hotel(Sesion.iduser, Sesion.us);
			dispose();
			seguir=false;
		}
	}
	
	public Factura(String iduser, String user, String room){
		Sesion.iduser=iduser;
		Sesion.us=user;
		this.room=room;
		setFrame();
	}
	
	public void setFrame(){
		//super("Principal");
		getContentPane().setLayout(null);
		//panel.setLayout(null);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
		
		//getContentPane().setBackground(Color.darkGray);
		
		JLabel label=new JLabel("Facturacion de Habitacion");
		label.setBounds(80,5,150,30);//x,y,ancho,alto
		getContentPane().add(label);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		//Nombre
		label=new JLabel("Nombre");
		label.setBounds(25,40,120,30);//x,y,ancho,alto
		getContentPane().add(label);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
		
		txtNombre=new JTextField();
		txtNombre.setBounds(75,45,120,20);
		getContentPane().add(txtNombre);
		txtNombre.addKeyListener(this);
		
		//Cedula
		label=new JLabel("Cedula");
		label.setBounds(25,70,120,30);//x,y,ancho,alto
		getContentPane().add(label);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
				
		txtCedula=new JTextField();
		txtCedula.setBounds(75,75,120,20);
		getContentPane().add(txtCedula);
		txtCedula.addKeyListener(this);
		//---------------------------------------------------------------
		
		//Habitacion
		label=new JLabel("Tipo Habi.");
		label.setBounds(25,130,120,30);//x,y,ancho,alto
		getContentPane().add(label);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
				
		cmbH = new JComboBox();
		llenarCombo();
		cmbH.setBounds(90,135,75,20);
		getContentPane().add(cmbH);
		
		//Dias Reservacion
		label=new JLabel("Dias");
		label.setBounds(180,130,120,30);//x,y,ancho,alto
		getContentPane().add(label);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
								
		txtDias=new JTextField();
		txtDias.setBounds(210,135,20,20);
		getContentPane().add(txtDias);
		txtDias.addKeyListener(this);
		//---------------------------------------------------------------
		
		//Pago Habitacion
		label=new JLabel("Pago H");
		label.setBounds(25,160,120,30);//x,y,ancho,alto
		getContentPane().add(label);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
						
		txtPagoH=new JTextField();
		txtPagoH.setBounds(75,165,50,20);
		getContentPane().add(txtPagoH);
		txtPagoH.addKeyListener(this);
		txtPagoH.setEditable(false);
		//---------------------------------------------------------------
		
		//Pago Habitacion
		label=new JLabel("Imp 15%");
		label.setBounds(25,190,120,30);//x,y,ancho,alto
		getContentPane().add(label);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
								
		txtImp=new JTextField();
		txtImp.setBounds(75,195,50,20);
		getContentPane().add(txtImp);
		txtImp.addKeyListener(this);
		txtImp.setEditable(false);
		//---------------------------------------------------------------
		
		//Pago Habitacion
		label=new JLabel("Pago T");
		label.setBounds(25,220,120,30);//x,y,ancho,alto
		getContentPane().add(label);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
										
		txtPagoT=new JTextField();
		txtPagoT.setBounds(75,225,50,20);
		getContentPane().add(txtPagoT);
		txtPagoT.addKeyListener(this);
		txtPagoT.setEditable(false);
		//---------------------------------------------------------------
		
		//Boton Guardar
		btnguardar=new JButton("Guardar");
		btnguardar.setBounds(155,200,100,50);//x,y,ancho,alto
		getContentPane().add(btnguardar);
		btnguardar.addActionListener(this);
		btnguardar.addKeyListener(this);
		
		//Boton Actualizar
		btnactualizar=new JButton("Actualizar H.");
		btnactualizar.setBounds(155,165,105,20);//x,y,ancho,alto
		getContentPane().add(btnactualizar);
		btnactualizar.addActionListener(this);
		btnactualizar.addKeyListener(this);
		
		label = new JLabel("");
		label.setBounds(0,0,500,300);
		label.setIcon(new ImageIcon("forms.png"));
		getContentPane().add(label);
		
		setSize(300,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	public void actionPerformed(ActionEvent evt){
		
		if(evt.getSource()==btnguardar){
			verificar();
			crearFact();
			dispose();
		}
		if(evt.getSource()==btnactualizar){
			total = cobro();
			imp = total*0.15;
			ph = total-imp;
			txtPagoH.setText(""+ph);
			txtImp.setText(""+imp);
			if(!txtDias.getText().isEmpty()){
				txtPagoT.setText(""+total*(Integer.parseInt(txtDias.getText().toString())));
			}
			else{
				txtPagoT.setText("0.0");
			}
		}
		if(evt.getSource()==btnsalir){
			dispose();
		}
	}
	
	public void verificar(){
		if(txtNombre.getText().isEmpty()){
			JOptionPane.showMessageDialog(null,"Favor Ingrese Nombre");
			txtNombre.requestFocus();
		}
		else if(txtCedula.getText().isEmpty()){
			JOptionPane.showMessageDialog(null,"Favor Ingrese Cedula");
			txtCedula.requestFocus();
		}
		else if(txtDias.getText().isEmpty()){
			JOptionPane.showMessageDialog(null,"Favor Ingresar los Dias de Reservacion");
			txtDias.requestFocus();
		}
		if(!txtDias.getText().isEmpty() && !txtCedula.getText().isEmpty() && !txtNombre.getText().isEmpty()){
			dias = Integer.parseInt(txtDias.getText());
			if(dias>3){
				total = Double.parseDouble(txtPagoT.getText());
				imp = Double.parseDouble(txtImp.getText());
				ph = Double.parseDouble(txtPagoH.getText());
			}
			else{
				total = Double.parseDouble(txtPagoT.getText());
				imp = Double.parseDouble(txtImp.getText());
				ph = Double.parseDouble(txtPagoH.getText());
			}
		}
	}
	
	public void keyPressed(KeyEvent ev){
		boton = ev.getKeyCode();
		if(boton == ev.VK_ENTER){
			verificar();
		}
        if(boton == 27){
            System.exit(0);
        }
   }
       
	public void keyReleased(KeyEvent ev){
		try{
			if(ev.getSource() == txtDias){
				if(txtDias.getText().length()==0){
					txtPagoH.setText("0.0");
					txtImp.setText("0.0");
					txtPagoT.setText("0.0");
				}
				else{
					if(cmbH.getSelectedItem().equals("Sencilla")){
						total = cobro();
						imp = total*0.15;
						ph = total-imp;
						
						txtPagoH.setText(""+ph);
						txtImp.setText(""+imp);
						txtPagoT.setText(""+total*(Integer.parseInt(txtDias.getText().toString())));
					}
					if(cmbH.getSelectedItem().equals("Doble")){
						total = cobro();
						imp = total*0.15;
						ph = total-imp;
						
						txtPagoH.setText(""+ph);
						txtImp.setText(""+imp);
						txtPagoT.setText(""+total*(Integer.parseInt(txtDias.getText().toString())));
					}
					if(cmbH.getSelectedItem().equals("Triple")){
						total = cobro();
						imp = total*0.15;
						ph = total-imp;
						
						txtPagoH.setText(""+ph);
						txtImp.setText(""+imp);
						txtPagoT.setText(""+total*(Integer.parseInt(txtDias.getText().toString())));
					}
				}
			}
		}catch(Exception e){}
	}
	
	public double cobro(){
		   Archivo a = new Archivo("Reservacion.txt");
		   String cadena=a.toString();
		   double valor=0;
		   
		   String ar[]=cadena.split("\n");
	       for(int i=0;i<ar.length;i++){
	    	   String interno[]=ar[i].split(";");
	    	   if(cmbH.getSelectedIndex()==i){
	    		   valor = Double.parseDouble(interno[1].toString());
	    	   }
	       }
		   return valor;
	}
	
	static void llenarCombo(){
		cmbH.removeAllItems();
		arc=new Archivo("Reservacion.txt");
		String lines[]=arc.toString().split("\n");
		for(int i=0;i<lines.length;i++){
			String inter[]=lines[i].split(";");
			cmbH.addItem(inter[0].toString().trim());
		}
	}
	
	public void keyTyped(KeyEvent ev){
	   if(ev.getSource() == txtCedula){
           char c = ev.getKeyChar();
           if(Character.isDigit(c) && txtCedula.getText().length()<15){
                if(txtCedula.getText().length() == 4 || txtCedula.getText().length() == 9){
                	txtCedula.setText(txtCedula.getText()+"-");
                }
            }               
             else{
                ev.consume();
             }
       }
	   else if(ev.getSource() == txtNombre){
           char c = ev.getKeyChar();
           if(Character.isLetter(c) || Character.isSpaceChar(c)){}            
           else{
                ev.consume();
           }
       }
	   else if(ev.getSource() == txtDias){
           char c = ev.getKeyChar();
           if(Character.isDigit(c) && txtDias.getText().length()<2){}               
             else{
                ev.consume();
             }
       }
	   else if(ev.getSource() == txtImp){
           char c = ev.getKeyChar();
           if(txtDias.getText().length()<5){}               
             else{
                ev.consume();
             }
       }
	}
	
	public void crearFact() {
		String fecha=dia+"/"+mes+"/"+anio;
		String horas=hora+":"+min+":"+seg;
		try {
			File f = new File("Fact("+dia+"-"+mes+"-"+anio+").txt");
			FileWriter fw;
			BufferedWriter bw;			
			if (f.exists() && f.length() != 0) {
				fw = new FileWriter(f, true);
				bw = new BufferedWriter(fw);
				bw.newLine();
				bw.write("      Hotel Narnia\nCliente:"+txtNombre.getText()+"\nID:"+txtCedula.getText()+
						"\nHabitacion:"+room+" "+cmbH.getSelectedItem().toString()+"\nCantidad Dias:"+txtDias.getText()+
						"\nPago Habitacion:"+txtPagoH.getText()+"\nImp 15%:"+txtImp.getText()+"\nPago Total:"+txtPagoT.getText()+
						"\nFecha:"+fecha+"  "+horas);
				JOptionPane.showMessageDialog(null,dia+"/"+mes+"/"+anio+" - "+hora+"-"+min+"-"+seg);
				bw.newLine();
				bw.write("--------------------------");
			}else{
				fw = new FileWriter(f, true);
				bw = new BufferedWriter(fw);
				bw.write("       Hotel Narnia\nCliente:"+txtNombre.getText()+"\nID:"+txtCedula.getText()+
						"\nHabitacion:"+room+" "+cmbH.getSelectedItem().toString()+"\nCantidad Dias:"+txtDias.getText()+
						"\nPago Habitacion:"+txtPagoH.getText()+"\nImp 15%:"+txtImp.getText()+"\nPago Total:"+txtPagoT.getText()+
						"\nFecha:"+fecha+"  "+horas);
				JOptionPane.showMessageDialog(null,dia+"/"+mes+"/"+anio+" - "+hora+"-"+min+"-"+seg);
				bw.newLine();
				bw.write("--------------------------");
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"No se puede leer el Archivo "+"Fact("+anio+"-"+mes+"-"+dia+").txt");
		}
		new CrearImagen(txtNombre.getText().toString(), txtCedula.getText().toString(),room,
				cmbH.getSelectedItem().toString(), txtDias.getText(),txtPagoH.getText(),txtImp.getText(),txtPagoT.getText()
				, fecha, horas);
		seguir=true;
	}
}