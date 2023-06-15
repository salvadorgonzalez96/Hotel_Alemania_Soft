import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Registro extends JPanel implements ActionListener,KeyListener
{
    JPanel panel;
    
    JTextField txtcodigo;
    JTextField txthabitacionNu;
    JTextField txtedificio;
    JTextField txtnombre;
    JTextField txtempresa;
    JTextField txtrtn;
    JTextField txttelefono;
    JTextField txtidentidad;
    JTextField txtpais;
    JTextField txtprocedencia;
    JTextField txtprofesion;
    JTextField txtnumero_personas;
    JTextField txtnumero_ninos;
    JTextField txtnumero_noches;
    JTextField txtentrada;
    JTextField txtsalida;
    JTextField txttarifa_noche;
    JTextField txttotal;
    JTextField txtdeposito;
    JTextField txtsaldo;
    JTextField txtformapago;
    JTextField txtnumerofactura;
    JTextField txtautomarca;
    JTextField txtautocolor;
    
    JButton btnguardar;
    JButton btnlimpiar;
    JButton btnsalir;
    JRadioButton rbFormaPago[] = new JRadioButton[2];
    ButtonGroup grupo = new ButtonGroup();
    
    public JComponent build(){
        panel=new JPanel();
        //panel.setLayout(null);
        setSize(680,550);
        setVisible(true);
        setFrame();
        return panel;
    }
    
    public void setFrame(){
        panel.setLayout(null);
        
        // Nombre de From========================================================
        Font font = new Font("Modern No. 20",0,20);
        JLabel label=new JLabel("Registro De Clientes");
        label.setBounds(70,-5,200,80);//x,y,ancho,alto
        label.setFont(font);
        panel.add(label);
        //Ingreso de Numero Habitacion==================================================
        label=new JLabel("# Habitacion");
        label.setBounds(10,70,80,20);//x,y,ancho,alto
        panel.add(label);
        
        txthabitacionNu=new JTextField();
        txthabitacionNu.setEditable(true);
        txthabitacionNu.setBounds(90,70,50,20);//x,y,ancho,alto
        //txthabitacionNu.setBackground(Color.BLACK);
        panel.add(txthabitacionNu);
        txthabitacionNu.addKeyListener(this);
       
        /////// ingreso de edificio
        
        label=new JLabel("Edificio");
        label.setBounds(190,70,50,20);//x,y,ancho,alto
        panel.add(label);
        
        txtedificio=new JTextField();
        txtedificio.setEditable(true);
        txtedificio.setBounds(240,70,40,20);//x,y,ancho,alto
        //txtedificio.setForeground(Color.BLACK);
        panel.add(txtedificio);        
        txtedificio.addKeyListener(this);
        //label.setForeground(Color.BLACK);
        //label.setBackground(Color.white);
        /////////////////////
        
        label=new JLabel("Nombre Cliente");
        label.setBounds(10,110,100,20);//x,y,ancho,alto
        panel.add(label);
        
        txtnombre=new JTextField();
        txtnombre.setEditable(true);
        txtnombre.setBounds(110,110,180,20);//x,y,ancho,alto
        txtnombre.setForeground(Color.black);
        panel.add(txtnombre);        
        txtnombre.addKeyListener(this);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        /////////////////////
        
        label=new JLabel("Empresa");
        label.setBounds(10,150,60,20);//x,y,ancho,alto
        panel.add(label);
        
        txtempresa=new JTextField();
        txtempresa.setEditable(true);
        txtempresa.setBounds(70,150,180,20);//x,y,ancho,alto
        txtempresa.setForeground(Color.black);
        panel.add(txtempresa);
        txtempresa.addKeyListener(this);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        label=new JLabel("RTN ");
        label.setBounds(10,180,40,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtrtn=new JTextField();
        txtrtn.setEditable(true);
        txtrtn.setBounds(70,180,110,20);//x,y,ancho,alto
        txtrtn.setForeground(Color.black);
        txtrtn.addKeyListener(this);
        panel.add(txtrtn);        
        
        /////////////////////
        label=new JLabel("Telefono");
        label.setBounds(10,210,60,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txttelefono=new JTextField();
        txttelefono.setEditable(true);
        txttelefono.setBounds(70,210,120,20);//x,y,ancho,alto
        txttelefono.setForeground(Color.black);
        panel.add(txttelefono);        
        txttelefono.addKeyListener(this);
        ////////////////////
        
        /////////////////////
        label=new JLabel("Identidad Cliente");
        label.setBounds(10,250,100,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtidentidad=new JTextField();
        txtidentidad.setEditable(true);
        txtidentidad.setBounds(110,250,120,20);//x,y,ancho,alto
        txtidentidad.setForeground(Color.black);
        panel.add(txtidentidad);        
        txtidentidad.addKeyListener(this);
        ////////////////////
        
        /////////////////////
        label=new JLabel("Pais");
        label.setBounds(10,290,50,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtpais=new JTextField();
        txtpais.setEditable(true);
        txtpais.setBounds(60,290,90,20);//x,y,ancho,alto
        txtpais.setForeground(Color.black);
        panel.add(txtpais);        
        txtpais.addKeyListener(this);
        ////////////////////
        
        /////////////////////
        label=new JLabel("Procedencia");
        label.setBounds(10,330,75,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtprocedencia=new JTextField();
        txtprocedencia.setEditable(true);
        txtprocedencia.setBounds(90,330,120,20);//x,y,ancho,alto
        txtprocedencia.setForeground(Color.black);
        panel.add(txtprocedencia);        
        txtprocedencia.addKeyListener(this);
        ////////////////////
        
          /////////////////////
        label=new JLabel("Profesion");
        label.setBounds(10,370,60,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtprofesion=new JTextField();
        txtprofesion.setEditable(true);
        txtprofesion.setBounds(70,370,120,20);//x,y,ancho,alto
        txtprofesion.setForeground(Color.black);
        panel.add(txtprofesion);        
        txtprofesion.addKeyListener(this);
        ////////////////////
        
        /////////////////////
        label=new JLabel("Numero De personas");
        label.setBounds(10,400,120,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtnumero_personas=new JTextField();
        txtnumero_personas.setEditable(true);
        txtnumero_personas.setBounds(135,400,30,20);//x,y,ancho,alto
        txtnumero_personas.setForeground(Color.black);
        panel.add(txtnumero_personas);        
        txtnumero_personas.addKeyListener(this);
        ////////////////////
        
        /////////////////////
        label=new JLabel("Numero De Niños");
        label.setBounds(180,400,100,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtnumero_ninos=new JTextField();
        txtnumero_ninos.setEditable(true);
        txtnumero_ninos.setBounds(280,400,30,20);//x,y,ancho,alto
        txtnumero_ninos.setForeground(Color.black);
        panel.add(txtnumero_ninos);        
        txtnumero_ninos.addKeyListener(this);
        
        
        /////////////////////
        label=new JLabel("Numero De Noches");
        label.setBounds(320,400,110,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtnumero_noches=new JTextField();
        txtnumero_noches.setEditable(true);
        txtnumero_noches.setBounds(430,400,30,20);//x,y,ancho,alto
        txtnumero_noches.setForeground(Color.black);
        panel.add(txtnumero_noches);        
        txtnumero_noches.addKeyListener(this);
        //////////////////////////
        
        /////////////////////
        label=new JLabel("Fecha Entrada");
        label.setBounds(10,430,90,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtentrada=new JTextField();
        txtentrada.setEditable(true);
        txtentrada.setBounds(100,430,70,20);//x,y,ancho,alto
        txtentrada.setForeground(Color.black);
        panel.add(txtentrada);        
        txtentrada.addKeyListener(this);
        //////////////////////////
        
        /////////////////////
        label=new JLabel("Fecha Salida");
        label.setBounds(180,430,80,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtsalida=new JTextField();
        txtsalida.setEditable(true);
        txtsalida.setBounds(260,430,70,20);//x,y,ancho,alto
        txtsalida.setForeground(Color.black);
        panel.add(txtsalida);        
        txtsalida.addKeyListener(this);
        //////////////////////////
        
        /////////////////////
        label=new JLabel("Tarifa Por Noche");
        label.setBounds(350,430,100,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txttarifa_noche=new JTextField();
        txttarifa_noche.setEditable(true);
        txttarifa_noche.setBounds(450,430,60,20);//x,y,ancho,alto
        txttarifa_noche.setForeground(Color.black);
        panel.add(txttarifa_noche);        
        txttarifa_noche.addKeyListener(this);
        //////////////////////////
        
        /////////////////////
        label=new JLabel("Total");
        label.setBounds(10,460,40,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txttotal=new JTextField();
        txttotal.setEditable(true);
        txttotal.setBounds(50,460,75,20);//x,y,ancho,alto
        txttotal.setForeground(Color.black);
        panel.add(txttotal);        
        txttotal.addKeyListener(this);
        //////////////////////////
        
        /////////////////////
        label=new JLabel("Deposito L");
        label.setBounds(140,460,60,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtdeposito=new JTextField();
        txtdeposito.setEditable(true);
        txtdeposito.setBounds(210,460,75,20);//x,y,ancho,alto
        txtdeposito.setForeground(Color.black);
        panel.add(txtdeposito);
        txtdeposito.addKeyListener(this);        
        //////////////////////////
        
        /////////////////////
        label=new JLabel("Saldo L");
        label.setBounds(300,460,50,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtsaldo=new JTextField();
        txtsaldo.setEditable(true);
        txtsaldo.setBounds(350,460,70,20);//x,y,ancho,alto
        txtsaldo.setForeground(Color.black);
        panel.add(txtsaldo);        
        txtsaldo.addKeyListener(this);
        //////////////////////////
        
        
        /////////////////////
        label=new JLabel("Forma de Pago");
        label.setBounds(10,490,90,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        rbFormaPago[0] = new JRadioButton("Contado");
		rbFormaPago[1] = new JRadioButton("Credito");
		rbFormaPago[0].setBounds(label.getX()+label.getWidth(),490,75,20);
		rbFormaPago[1].setBounds(label.getX()+label.getWidth()+label.getWidth(),490,70,20);
		panel.add(rbFormaPago[0]);
		panel.add(rbFormaPago[1]);
		rbFormaPago[0].setForeground(Color.BLACK);
		rbFormaPago[1].setForeground(Color.BLACK);
		rbFormaPago[0].setOpaque(false);
		rbFormaPago[1].setOpaque(false);
		
		grupo.add(rbFormaPago[0]);
		grupo.add(rbFormaPago[1]);
        ///////////////////////
        
        /////////////////////
        label=new JLabel("Auto Marca");
        label.setBounds(10,520,70,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtautomarca=new JTextField();
        txtautomarca.setEditable(true);
        txtautomarca.setBounds(90,520,80,20);//x,y,ancho,alto
        txtautomarca.setForeground(Color.black);
        panel.add(txtautomarca);        
        txtautomarca.addKeyListener(this);
        ///////////////////////
        
        /////////////////////
        label=new JLabel("Auto Color");
        label.setBounds(200,520,70,20);//x,y,ancho,alto
        panel.add(label);
        label.setForeground(Color.black);
        //label.setBackground(Color.white);
        
        txtautocolor=new JTextField();
        txtautocolor.setEditable(true);
        txtautocolor.setBounds(270,520,80,20);//x,y,ancho,alto
        txtautocolor.setForeground(Color.black);
        panel.add(txtautocolor);        
        txtautocolor.addKeyListener(this);
        ///////////////////////
        
        //Boton Guardar==========================================================================
        btnguardar=new JButton("GUARDAR");
        btnguardar.setBounds(550,370,90,30);//x,y,ancho,alto
        panel.add(btnguardar);
        btnguardar.addActionListener(this);
        
        
        //Boton limpiar==========================================================================
        btnlimpiar=new JButton("Limpiar");
        btnlimpiar.setBounds(550,410,90,30);//x,y,ancho,alto
        panel.add(btnlimpiar);
        btnlimpiar.addActionListener(this);
        
          //Boton Salir==========================================================================
        btnsalir=new JButton("Salir");
        btnsalir.setBounds(550,450,90,30);//x,y,ancho,alto
        panel.add(btnsalir);
        btnsalir.addActionListener(this);
        
        label = new JLabel("");
		label.setBounds(0,0,1200,800);
		label.setIcon(new ImageIcon("fondo.jpg"));
		panel.add(label);
    }
    
    void vaciar(){
        txtnombre.setText("");
        txtempresa.setText("");
        txtrtn.setText("");
        txttelefono.setText("");
        txtidentidad.setText("");
        txtpais.setText("");
        txtprocedencia.setText("");
        txtprofesion.setText("");
        txtnumero_personas.setText("");
        txtnumero_ninos.setText("");
        txtnumero_noches.setText("");
        txtentrada.setText("");
        txtsalida.setText("");
        txttarifa_noche.setText("");
        txttotal.setText("");
        txtdeposito.setText("");
        txtsaldo.setText("");
        txtautomarca.setText("");
        txtautocolor.setText("");
    }
    
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource()==btnguardar){
        	/*if(txtnombre.getText().isEmpty()){
	            JOptionPane.showMessageDialog(null,"El Campo Nombre Esta Vacio");
	            txtnombre.requestFocus();
			}*/
        }
        
        if(evt.getSource()==btnlimpiar){
        	vaciar();
        }
            
        if(evt.getSource()==btnsalir){
        	
        }
    }
    
    public void keyReleased(KeyEvent evt){}
    
    public void keyPressed(KeyEvent evt){}
    
    public void keyTyped(KeyEvent evt){
    	///validacion de Nombre
    	if(evt.getSource()==txtnombre){
    		char c=evt.getKeyChar();
    		if(Character.isLetter(c) && txtnombre.getText().length()<14){}            
    		else{
    			evt.consume();
    		}
    	}
       else if(evt.getSource()==txtempresa){
    	   char c=evt.getKeyChar();
    	   if(Character.isLetter(c) && txtempresa.getText().length()<14){}            
    	   else{
    		   evt.consume();
    	   }
       }
       else if(evt.getSource()==txtrtn){
    	   char c=evt.getKeyChar();
           if(Character.isDigit(c)&&txtrtn.getText().length()<14){}            
           else{
              evt.consume();
           }
       }
       else if(evt.getSource()==txttelefono){
    	   char c=evt.getKeyChar();
    	   if(Character.isDigit(c)&&txttelefono.getText().length()<9){
    		   if(txttelefono.getText().length()==4||txttelefono.getText().length()==9){
    			   txttelefono.setText(txttelefono.getText()+"-");
    		   }
    	   }               
    	   else{
    		   evt.consume();
    	   }
       }
       else if(evt.getSource()==txtidentidad){
    	   char c=evt.getKeyChar();
    	   if(Character.isDigit(c)&&txtidentidad.getText().length()<15){
    		   if(txtidentidad.getText().length()==4||txtidentidad.getText().length()==9){
    			   txtidentidad.setText(txtidentidad.getText()+"-");
    		   }
    	   }               
    	   else{
    		   evt.consume();
    	   }
       }
       else if(evt.getSource()==txtpais){
    	   char c=evt.getKeyChar();
           if(Character.isLetter(c) && txtpais.getText().length()<14){}            
           else{
        	   evt.consume();
           }
       }
       else if(evt.getSource()==txtprocedencia){
    	   char c=evt.getKeyChar();
           if(Character.isLetter(c) && txtprocedencia.getText().length()<14){}            
           else{
        	   evt.consume();
           }
       }
      else if(evt.getSource()==txtprofesion){
           char c=evt.getKeyChar();
           if(Character.isLetter(c) && txtprofesion.getText().length()<14){}            
           else{
              evt.consume();
           }
       }
       else if(evt.getSource()==txtnumero_personas){
           char c=evt.getKeyChar();
           if(Character.isDigit(c)&&txtnumero_personas.getText().length()<4){}            
           else{
              evt.consume();
           }
       }
       else if(evt.getSource()==txtnumero_ninos){
           char c=evt.getKeyChar();
           if(Character.isDigit(c)&&txtnumero_ninos.getText().length()<4){}            
           else{
              evt.consume();
           }
       }
       else if(evt.getSource()==txtnumero_noches){
           char c=evt.getKeyChar();
           if(Character.isDigit(c)&&txtnumero_noches.getText().length()<4){}            
           else{
              evt.consume();
           }
       }
       else if(evt.getSource()==txttotal){
           char c=evt.getKeyChar();
           if(Character.isDigit(c)&&txttotal.getText().length()<14){}            
           else{
              evt.consume();
           }
       }
       else if(evt.getSource()==txttarifa_noche){
           char c=evt.getKeyChar();
           if(Character.isDigit(c)&&txttarifa_noche.getText().length()<14){}            
           else{
              evt.consume();
           }
       }
       else if(evt.getSource()==txtdeposito){
           char c=evt.getKeyChar();
           if(Character.isDigit(c)&&txtdeposito.getText().length()<4){}            
           else{
              evt.consume();
           }
       }
       else if(evt.getSource()==txtsaldo){
           char c=evt.getKeyChar();
           if(Character.isDigit(c)&&txtsaldo.getText().length()<4){}            
           else{
              evt.consume();
           }
       }
       else if(evt.getSource()==txtautomarca){
    	   char c=evt.getKeyChar();
           if(Character.isLetter(c) && txtautomarca.getText().length()<14){}            
           else{
              evt.consume();
           }
       }
       else if(evt.getSource()==txtautocolor){
           char c=evt.getKeyChar();
           if(Character.isLetter(c) && txtautocolor.getText().length()<14){}            
           else{
              evt.consume();
           }
       }
    }
}