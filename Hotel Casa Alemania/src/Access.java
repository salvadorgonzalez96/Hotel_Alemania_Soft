import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Access implements ActionListener
{
    	JPanel panel;
    	ArregloArchivo aa;
    	static Archivo arc;
    	static JComboBox cmbuser,cmbuserid;
    	static JCheckBox check[]=new JCheckBox[0];
    	static String info_ac[]=new String[0];
    	JButton btn=new JButton(),btngenerar;
    	String lineas[]=new String[0];
    	
    	public JComponent build(){
    		panel = new JPanel();
    		setFrame();
    		return panel;
    	}
    	
    	public void setFrame(){
    		setBotones();
    	}
    	
    	public void setBotones(){
    		panel.setLayout(null);
    		
    		JLabel label=new JLabel("USUARIO");
    		label.setBounds(70,10,120,20);
    		panel.add(label);
    		
    		label.setForeground(Color.BLACK);
    		label.setBackground(Color.darkGray);
    		label.setOpaque(false);
    		
    		cmbuser=new JComboBox();
    		cmbuser.setBounds(130,10,120,20);
    		panel.add(cmbuser);
    		cmbuserid=new JComboBox();
    		
    		llenarUserenCombo();
    		
    		String accesos[]=new Archivo("Accesos.txt").toString().trim().split("\n");
    		check=new JCheckBox[accesos.length-1];
    		info_ac=new String[accesos.length-1];
    		
    		btngenerar=new JButton("Generar");
    		btngenerar.setBounds(cmbuser.getX()+120,cmbuser.getY(),120,20);
    		panel.add(btngenerar);
    		btngenerar.addActionListener(this);
    		
    		int xx=10,yy=30;
    		int cont=0;
    		for(int i=0;i<check.length;i++){
    			String inter[]=accesos[i+1].trim().split(";");
    			check[i]=new JCheckBox(inter[1]);
    			info_ac[i]=inter[0];
    			
    			//String linea=Sesion.iduser+";"+inter[0]+";";
    			//boolean siesta=new Archivo("Usuario_Acceso.txt").toString().contains(linea);
    			
    			//check[i].setSelected(siesta);
    			check[i].setBounds(xx,yy,160,20);
    			xx+=160;
    			cont++;
    			if(cont==4){
    				yy+=30;
    				xx=10;
    				cont=0;
    			}
    			panel.add(check[i]);
    			check[i].setForeground(Color.BLACK);
    			check[i].setBackground(Color.darkGray);
    			check[i].setOpaque(false);
    		}
    		
    		btn=new JButton("Actualizar");
    		btn.setBounds(10,check[check.length-1].getY()+30,120,40);
    		btn.addActionListener(this);
    		panel.add(btn);
    		
    		label = new JLabel("");
    		label.setBounds(0,0,500,300);
    		label.setIcon(new ImageIcon("forms.png"));
    		panel.add(label);
    	}
    	
    	static void llenarAccesos(String idusuario){
    		for(int i=0;i<check.length;i++){
    			String linea=idusuario+";"+info_ac[i]+";";
    			boolean siesta=new Archivo("Usuario_Acceso.txt").toString().contains(linea);
    			check[i].setSelected(siesta);
    		}
    	}
    	
    	public void actionPerformed(ActionEvent evt){
    		if(evt.getSource()==btngenerar){
    			llenarAccesos(""+cmbuserid.getItemAt(cmbuser.getSelectedIndex()));
    		}
    		else if(evt.getSource()==btn){
    			guardaraccesos();
    			JOptionPane.showMessageDialog(null,"Se Actualizo Satisfactoriamente");
    		}
    	}
    	
    	static void llenarUserenCombo(){
    		cmbuser.removeAllItems();
    		cmbuserid.removeAllItems();
    		arc=new Archivo("Usuario.txt");
    		String lines[]=arc.toString().split("\n");
    		for(int i=1;i<lines.length;i++){
    			String inter[]=lines[i].split(";");
    			cmbuser.addItem(inter[1].toString().trim());
    			cmbuserid.addItem(inter[0].toString().trim());
    		}
    	}
    	
    	public void guardaraccesos(){
    		String iduser=cmbuserid.getItemAt(cmbuser.getSelectedIndex()).toString();
    		arc=new Archivo("Usuario_Acceso.txt");
	    	String accesos[]=arc.toString().split("\n");
	    	for(int i=0;i<accesos.length;i++){
	    		String inter[]=accesos[i].split(";");	    					
	    		if(i==0)agregarlinea(accesos[i].trim());
	    		else{
	    			if(inter[0].trim().equals(iduser)){}
	    			else agregarlinea(accesos[i].trim()); 
	    		}
	    	}
	    	for(int i=0;i<check.length;i++){
	    		if(check[i].isSelected())agregarlinea(iduser+";"+info_ac[i]+";");
	    	}

	    	try{
	    		imprimir();
	    		arc.guardararreglo(lineas);
	    		lineas=new String[0];
	    		cmbuser.setSelectedIndex(0);
	    		llenarAccesos("0");    		
	    	}
	    	catch(Exception exp){
	    		JOptionPane.showMessageDialog(null,exp);
	    	}
    	}
    	
    	public void imprimir(){
    		for(int i=0;i<lineas.length;i++){
    			System.out.println(lineas[i]);
    		}
    	}
    	
    	public void agregarlinea(String n){
    		String tmp[]=new String[lineas.length+1];
    		for(int i=0;i<lineas.length;i++){
    			tmp[i]=lineas[i];
    		}
    		tmp[lineas.length]=n;
    		lineas=tmp;
    	}
    }