import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;

public class FacT extends JFrame implements ActionListener
{
	JComboBox cmbFact;
	Archivo arc;
	String imp;
	JButton btnver;
	
	void close(){
		if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			//System.exit(0);
			new Ventana(Sesion.iduser, Sesion.us);
			dispose();
		}
	}
	
	public FacT(String idus, String us){
		super("Factura Total");
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
		
		Font font = new Font("Modern No. 20",0,22);
		JLabel label=new JLabel("Bienvenido "+Sesion.us);
		label.setBounds(40,20,200,30);
		label.setFont(font);
		getContentPane().add(label);
		
		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);

		//Habitacion
		label=new JLabel("Factura por Mes");
		label.setBounds(25,50,120,30);//x,y,ancho,alto
		getContentPane().add(label);

		label.setForeground(Color.BLACK);
		label.setBackground(Color.darkGray);
		label.setOpaque(false);
						
		cmbFact = new JComboBox();
		llenarCombo(mesArchivo());
		//mesArchivo();
		cmbFact.setBounds(30,75,100,20);
		getContentPane().add(cmbFact);
		//--------------------------------------------
		
		//Boton Ver
		btnver=new JButton("Generar Factura");
		btnver.setBounds(30,100,130,20);//x,y,ancho,alto
		getContentPane().add(btnver);
		btnver.addActionListener(this);
				
		
		label = new JLabel("");
		label.setBounds(0,0,300,300);
		label.setIcon(new ImageIcon("ventana.png"));
		getContentPane().add(label);
		
		setSize(300,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public void llenarCombo(int n){
		String mes[]={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		int day[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		for(int i=0; i<mes.length; i++){
			//for(int j=0; j<day.length;j++){
				if(i==(mesArchivo()-1)){
					cmbFact.addItem(mes[i].toString());
				}
			//}
		}
		//cmbFact.addItem("");
	}
	
	public String imprimirArchivoMes(){
		String cadena="";
		FileReader entrada=null;
		StringBuffer str=new StringBuffer();
		for(int i=1; i<13; i++){
			for(int j=1; j<32;j++){
				try{
					entrada=new FileReader("Fact("+j+"-"+i+"-2019).txt");
					int c;
					while((c=entrada.read())!=-1){
						cadena+=(char)c;
					}
				}
				catch(Exception ex){}
			}
		}
		return cadena;
	}

	public int mesArchivo(){
		int cont = 0;
		FileReader entrada=null;
		StringBuffer str=new StringBuffer();
		for(int i=1; i<13; i++){
			for(int j=1; j<32;j++){
				try{
					entrada=new FileReader("Fact("+j+"-"+i+"-2019).txt");
					//cont++;
					return i;
				}
				catch(Exception ex){}
			}
		}
		return cont;
	}
	
	public int length(){
		int cont = 0;
		FileReader entrada=null;
		StringBuffer str=new StringBuffer();
		for(int i=1; i<13; i++){
			for(int j=1; j<32;j++){
				try{
					entrada=new FileReader("Fact("+j+"-"+i+"-2019).txt");
					cont++;
				}
				catch(Exception ex){}
			}
		}
		return cont;
	}
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource()==btnver){
			new CrearImagen(imprimirArchivoMes());
		}
	}
}