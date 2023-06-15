import java.awt.BorderLayout;
import java.awt.Graphics2D;
import javax.swing.*;

import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.table.*;

public class Venta extends JPanel implements ActionListener,KeyListener
{
	JPanel panel;
    JTextArea text;
    //table
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tabla=new JTable(modelo);  
    JScrollPane panel2 = new JScrollPane(tabla);
    String col[]={"Cant","Producto","Precio","Impuesto","Descuento","Subtotal"};
    int tam[]={40,150,100,100,100,100};
    
    JTextField txtcantidad;
    JTextField txtdescuento;
    JTextField txtprecio;
    JTextField txtimpuesto;
    JTextField txtsubtotal;
    JTextField txttotal;
    JTextField txtdescuentototalt;
    JTextField txtusuario;
    JComboBox producto;
    JComboBox cliente;
    JButton btnagregar;
    JButton btnfactura;
    double descuento=0;
    double precio,impuesto,cantidad,subtotal;
    double subtotaltotal,impuestototal,descuentototal,total;
    String fila[]=new String[0];
    DecimalFormat Dos=new DecimalFormat("0.00");
    String estado="Habilitado";
    Archivo ar=new Archivo("Documentacion/Facturas.txt");
    String datos="";
    
    public JComponent build(String id, String us){
    	Sesion.iduser=id;
        Sesion.us=us;
        panel=new JPanel();
        panel.setLayout(null);
        //panel.setBackground(Color.gray);
        setSize(800,550);
        setVisible(true);
        setFrame();
        return panel;
    }
    
    public static int Generarindice(){
         int contar=0;
         Archivo ar=new Archivo("Documentacion/Usuarios.txt");
         String ca=ar.toString();
         String lineas[]=ca.split("\n");
         contar=lineas.length-1;
         return contar;
    }
    
    public int Generarindicefac(){
         int contar=0;
         Archivo ar=new Archivo("Documentacion/Facturas.txt");
         String ca=ar.toString();
         if(ca.equals("")){
        	 contar=1;
         }else{
        	 String lineas[]=ca.split("\n");
        	 contar=lineas.length+1;
         }
         return contar;
    }
    
    
    public void setFrame(){
        JLabel label=new JLabel("Punto de venta");
        label.setBounds(70,-19,150,80);//x,y,ancho,alto
        panel.add(label);
        
        label=new JLabel("Usuario");
        label.setBounds(600,40,100,50);//x,y,ancho,alto
        panel.add(label);
        
        txtusuario=new JTextField();
        txtusuario.setEditable(false);
        txtusuario.setBounds(660,60,80,20);//x,y,ancho,alto
        txtusuario.setText(Sesion.us);
        //txtusuario.setForeground(Color.black);
        txtusuario.addKeyListener(this);
        txtusuario.addActionListener(this);
        panel.add(txtusuario);
        
        
        label=new JLabel("Cantidad");
        label.setBounds(10,80,100,50);//x,y,ancho,alto
        panel.add(label);
        
        txtcantidad=new JTextField();
        txtcantidad.setEditable(true);
        txtcantidad.setBounds(70,90,80,20);//x,y,ancho,alto
        //txtcantidad.setForeground(Color.black);
        txtcantidad.addKeyListener(this);
        txtcantidad.addActionListener(this);
        panel.add(txtcantidad);
         
        
        label=new JLabel("Producto");
        label.setBounds(160,80,100,50);//x,y,ancho,alto
        panel.add(label);
       
        producto=new JComboBox();
        producto.setBounds(220,90,100,30);
        producto.addActionListener(this);
        panel.add(producto);

        
        label=new JLabel("Cliente");
        label.setBounds(600,80,100,50);//x,y,ancho,alto
        panel.add(label);
       
        cliente=new JComboBox();
        cliente.setBounds(670,90,100,30);
        cliente.addActionListener(this);
        panel.add(cliente);
        //llenarcomboclien();
        
        txtprecio=new JTextField();
        txtprecio.setEditable(false);
        txtprecio.setVisible(false);
        txtprecio.setBounds(310,90,120,20);//x,y,ancho,alto
        //txtprecio.setForeground(Color.black);
        txtprecio.addKeyListener(this);
        txtprecio.addActionListener(this);
        panel.add(txtprecio);   
         
        
        label=new JLabel("Descuento");
        label.setBounds(340,80,100,50);//x,y,ancho,alto
        panel.add(label);
        txtdescuentototalt=new JTextField();
        txtdescuentototalt.setEditable(true);
        txtdescuentototalt.setBounds(720,300,120,20);//x,y,ancho,alto
        //txtdescuentototalt.setForeground(Color.black);
        txtdescuentototalt.addKeyListener(this);
        txtdescuentototalt.addActionListener(this);
        panel.add(txtdescuentototalt);
        
        label=new JLabel("Subtotal");
        label.setBounds(630,190,100,50);//x,y,ancho,alto
        panel.add(label);
        txtsubtotal=new JTextField();
        txtsubtotal.setEditable(true);
        txtsubtotal.setBounds(720,200,120,20);//x,y,ancho,alto
        //txtsubtotal.setForeground(Color.black);
        txtsubtotal.addKeyListener(this);
        txtsubtotal.addActionListener(this);
        panel.add(txtsubtotal);   
        
        label=new JLabel("Impuesto");
        label.setBounds(630,240,100,50);//x,y,ancho,alto
        panel.add(label);
        txtimpuesto=new JTextField();
        txtimpuesto.setEditable(true);
        txtimpuesto.setBounds(720,250,120,20);//x,y,ancho,alto
        //txtimpuesto.setForeground(Color.black);
        txtimpuesto.addKeyListener(this);
        txtimpuesto.addActionListener(this);
        panel.add(txtimpuesto);
        
        label=new JLabel("Descuento");
        label.setBounds(630,290,100,50);//x,y,ancho,alto
        panel.add(label);
        
        txtdescuento=new JTextField();
        txtdescuento.setEditable(true);
        txtdescuento.setBounds(410,90,120,20);//x,y,ancho,alto
        //txtdescuento.setForeground(Color.black);
        txtdescuento.addKeyListener(this);
        txtdescuento.addActionListener(this);
        panel.add(txtdescuento);
        
        label=new JLabel("Total");
        label.setBounds(630,340,100,50);//x,y,ancho,alto
        panel.add(label);
        txttotal=new JTextField();
        txttotal.setEditable(true);
        txttotal.setBounds(720,350,120,20);//x,y,ancho,alto
        //txttotal.setForeground(Color.black);
        txttotal.addKeyListener(this);
        txttotal.addActionListener(this);
        panel.add(txttotal);
        
        //llenarcombo();
        
        ///Tabla
        for(int x=0;x<col.length;x++){
            modelo.addColumn(col[x]);
        }
        for(int i=0;i<tam.length;i++){
            TableColumn columnId=null;        
            columnId=tabla.getColumnModel().getColumn(i);
            columnId.setPreferredWidth(tam[i]);
        }
        
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setColumnSelectionAllowed(true); 
        panel2.setBounds(50,180,550,250);               
        panel.add(panel2);
         //
        btnagregar=new JButton("AGREGAR");
        btnagregar.setBounds(500,120,90,30);//x,y,ancho,alto
        panel.add(btnagregar);
        btnagregar.addActionListener(this);
        
        btnfactura=new JButton("facturar");
        btnfactura.setBounds(720,400,90,30);//x,y,ancho,alto
        panel.add(btnfactura);
        btnfactura.addActionListener(this);
        
        label = new JLabel("");
		label.setBounds(0,0,1200,800);
		label.setIcon(new ImageIcon("fondo.jpg"));
		panel.add(label);
    }
    
    public void llenarcomboclien(){
         cliente.removeAllItems();
         int contar=0;
         Archivo ar=new Archivo("Documentacion/Clientes.txt");
         String ca=ar.toString();
         String lineas[]=ca.split("\n");
         for(int i=0;i<lineas.length;i++){
        	 String Split[]=lineas[i].split(";");
        	 if(Split[7].equals("Habilitado")){
        		 cliente.addItem(Split[2]+""); 
        	 }
         }
    }
    
   public double obtenerdid(int indice_producto,int indice_archivo){
       Archivo ar=new Archivo("Documentacion/Clientes.txt");
       String ca=ar.toString();
       String lineas[]=ca.split("\n");
       String interno[]=lineas[indice_producto].split(";");
       double num=Double.parseDouble(interno[indice_archivo]+"");
       return num;
    }
    
    
    public double obtenerd(int indice_producto,int indice_archivo){
       Archivo ar=new Archivo("Documentacion/Productos.txt");
       String ca=ar.toString();
       String lineas[]=ca.split("\n");
       String interno[]=lineas[indice_producto].split(";");
       double num=Double.parseDouble(interno[indice_archivo]+"");
       return num;
    }
    

    
    public void llenarcombo(){
         producto.removeAllItems();
         int contar=0;
         Archivo ar=new Archivo("Documentacion/Productos.txt");
         String ca=ar.toString();
         String lineas[]=ca.split("\n");
         for(int i=0;i<lineas.length;i++){
        	 String Split[]=lineas[i].split(";");
        	 if(Split[7].equals("Habilitado")){
        		 producto.addItem(Split[1]+"");
        	 }
         }
    }
   
    public void actionPerformed(ActionEvent evt){
       if(evt.getSource()==btnagregar){
         if(txtcantidad.getText().equals("0")||txtcantidad.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"favor Poner Cantidad Para Facturar");
           }
           else if(txtdescuento.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"favor Poner Establecer descuento Para Facturar");
           }
           else{
        	   cantidad=Double.parseDouble(txtcantidad.getText()+""); //cantidad txtfield
         
        	   impuesto=obtenerd(producto.getSelectedIndex(),2); //solo es el impuesto del producto         
        	   precio=(obtenerd(producto.getSelectedIndex(),5)+(obtenerd(producto.getSelectedIndex(),4))); //es el precio + utilidad         
        	   double preciou=obtenerd(producto.getSelectedIndex(),5); //precio unitario sin impuesto ni utilidad         
        	   descuento=((Double.parseDouble(txtdescuento.getText()))*preciou)/100;
        	   subtotal=((precio+impuesto)*cantidad)-(descuento); //precio sin impuesto + el impuesto - descuento.
        	   String fila[]={txtcantidad.getText()+"",producto.getSelectedItem()+"",precio+"",impuesto+"",descuento+"",subtotal+""};
        	   modelo.addRow(fila);
        	   subtotaltotal+=(precio*cantidad);
        	   impuestototal+=impuesto;
        	   descuentototal+=descuento;
        	   total+=subtotal;
        	   txtsubtotal.setText(""+Dos.format(subtotaltotal));
        	   txtimpuesto.setText(""+Dos.format(impuestototal));
        	   txtdescuentototalt.setText(""+Dos.format(descuentototal));
        	   txttotal.setText(""+Dos.format(total));
            }
       }
        
       if(evt.getSource()==btnfactura){
    	   Archivo ar=new Archivo("Documentacion/Facturas.txt");
    	   deta(Generarindicefac());
         
    	   String cadena="Usuario:"+";"+txtusuario.getText()+";"+"Cliente: "+
    			   cliente+";"+"Impuesto: "+txtimpuesto.getText()+";"+"Descuento: "+ txtdescuentototalt.getText()+
    			   ";"+"Sub Total: "+txtsubtotal.getText()+";"+"Total: "+txttotal.getText()+";"+"Usuario: "+
    			   txtusuario.getText()+";"+"Estado: "+estado+";";
         
    	   String client=""+cliente.getSelectedItem();
    	   String linea=(Generarindicefac()+";"+"Usuario:"+";"+txtusuario.getText()+";"+"Cliente:"+
    			   client+";"+"Impuesto:"+txtimpuesto.getText()+";"+"Descuento:"+ txtdescuentototalt.getText()+
    			   ";"+"Sub Total:"+txtsubtotal.getText()+";"+"Total:"+txttotal.getText()+";"+"Estado:"+estado+";");
    	   try{
    		   ar.guardarstring(linea);
    		   imagen(cadena);
    	   }
    	   catch(Exception e){}
    	   JOptionPane.showMessageDialog(null,"Factura Exitosa");
    	   //new TestImagen(("Factura"+"\n"+"Empresa:"+"\n"+"cliente:"+client+"\n"+"Detalle"+"\n"+datos+"\n"+"Totales"+"\n"+"Subtotal: "+txtsubtotal.getText()+"\n"+"Impuesto: "+txtimpuesto.getText()+"\n"+"Descuento: "+ txtdescuentototalt.getText()+"\n"+"Total: "+txttotal.getText()), Sesion.iduser, Sesion.us);
       }   
    }
    
    void imagen(String tx){
        text.setWrapStyleWord(true);
        text.setColumns(15);
        text.setLineWrap(true);
        text.setText(tx);
    }
    
    public void deta(int indice){
       Archivo ar=new Archivo("Documentacion/DetalleFactura.txt");
       for(int x=0;x<modelo.getRowCount();x++){ 
          String a=""+indice+";"+x+";"+""+modelo.getValueAt(x,0)+";"+""+modelo.getValueAt(x,1)+";"+""+modelo.getValueAt(x,2)+";"+""+modelo.getValueAt(x,3)+";"+""+modelo.getValueAt(x,4)+";"+""+modelo.getValueAt(x,5)+"";
          datos+=""+modelo.getValueAt(x,0)+"  "+modelo.getValueAt(x,1)+"  "+modelo.getValueAt(x,2)+"  "+modelo.getValueAt(x,5)+" \n";
          try{
        	  ar.guardarstring(a);
          }
          catch(Exception e){}
       }
    }
    
    public void keyReleased(KeyEvent evt){
        if(evt.getSource()==txtdescuento){
            if(txtdescuento.getText().isEmpty()){
             //txtdescuento.setText("0.00");
            }
        }
        if(txtcantidad.getText().isEmpty()){
        	//txtcantidad.setText("0.00");
        }
    }
    
    public void keyPressed(KeyEvent evt){}
    
    public void keyTyped(KeyEvent evt){
       if(evt.getSource()==txtcantidad){
           char c=evt.getKeyChar();
           if(Character.isDigit(c)&&txtcantidad.getText().length()<6){}            
           else{
              evt.consume();
           }
       }
       else if(evt.getSource()==txtdescuento){
           char c=evt.getKeyChar();
           if(Character.isDigit(c)&&txtdescuento.getText().length()<6){}
           else{
              evt.consume();
           }
       }
    }
}