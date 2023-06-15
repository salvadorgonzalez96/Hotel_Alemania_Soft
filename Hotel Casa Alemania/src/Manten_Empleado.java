 
import java.sql.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;
import java.text.NumberFormat;

public class Manten_Empleado extends JDialog implements ActionListener,ListSelectionListener,KeyListener{
    JButton btnguardar,btnnew,btnmod,btnrest,btnel;
    JLabel lbltitulo,label;
    JTextField txtid,txtpnombre,txtsnombre,txtpapellido,txtsapellido;
    JComboBox cmbpuesto;
    JList lista,ListaId;
    Llenado l=new Llenado();
    DefaultListModel model=new DefaultListModel(),Ids=new DefaultListModel();
    String ident="";
    
    void close(){
		if(true){
			new Main();
			dispose();
		}
	}
        
    public Manten_Empleado(){
        setTitle("Mantenimiento de Empleado");
        setModal(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
        //iduser=ID;
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(193,205,205));
        
		int xx=220,yy=70;
		    
		label=new JLabel();
		TitledBorder titled = new TitledBorder("Datos de Empleado");
		titled.setTitleColor(Color.BLUE);
		label.setBorder(titled);
		label.setBounds(200,yy,450,200);
		label.setForeground(Color.BLACK);
		getContentPane().add(label);
		
		label=new JLabel("ID");label.setForeground(Color.BLACK);label.setBounds(xx,yy+=25,120,20);
		getContentPane().add(label);
		txtid=new JTextField();getContentPane().add(txtid);
		txtid.setBounds(label.getX()+label.getWidth(),yy,280,20);
		txtid.addKeyListener(this);
		
		label=new JLabel("Primer Nombre");label.setForeground(Color.BLACK);
		label.setBounds(xx,yy+=25,120,20);
		getContentPane().add(label);
		txtpnombre=new JTextField();getContentPane().add(txtpnombre);
		txtpnombre.setBounds(label.getX()+label.getWidth(),yy,280,20);
		
		label=new JLabel("Segundo Nombre");label.setForeground(Color.BLACK);
		label.setBounds(xx,yy+=25,120,20);
		getContentPane().add(label);
		txtsnombre=new JTextField();getContentPane().add(txtsnombre);
		txtsnombre.setBounds(label.getX()+label.getWidth(),yy,280,20);
		
		label=new JLabel("Primer Apellido");label.setForeground(Color.BLACK);
		label.setBounds(xx,yy+=25,120,20);
		getContentPane().add(label);
		txtpapellido=new JTextField();getContentPane().add(txtpapellido);
		txtpapellido.setBounds(label.getX()+label.getWidth(),yy,280,20);
		
		label=new JLabel("Segundo Apellido");label.setForeground(Color.BLACK);
		label.setBounds(xx,yy+=25,120,20);
		getContentPane().add(label);
		txtsapellido=new JTextField();getContentPane().add(txtsapellido);
		txtsapellido.setBounds(label.getX()+label.getWidth(),yy,280,20);
		
		label=new JLabel("Puesto");label.setForeground(Color.BLACK);
		label.setBounds(xx,yy+=25,120,20);
		getContentPane().add(label);
		cmbpuesto=new JComboBox();
		cmbpuesto.addItem("Seleccionar Puesto");cmbpuesto.addItem("Venta");cmbpuesto.addItem("SAC");cmbpuesto.addItem("ITS");
		cmbpuesto.setBounds(label.getX()+label.getWidth(),yy,150,20);
		getContentPane().add(cmbpuesto);
		
		lista=new JList(model);lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.addListSelectionListener(this);
		JScrollPane scr1=new JScrollPane(lista);
		scr1.setBounds(30,70,170,200);getContentPane().add(scr1);
		lista.setEnabled(false);
		
		ListaId=new JList(Ids);ListaId.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListaId.addListSelectionListener(this);
		JScrollPane scr2=new JScrollPane(ListaId);
		scr2.setBounds(30,70,170,200);getContentPane().add(scr2);
		scr2.setVisible(false);
		
		l.llenado_jlistid("SELECT emp_identidad FROM tbl_empleado;",Ids);
        l.llenado_jlist("SELECT emp_pnombre,emp_papellido FROM tbl_empleado;",model);
        
		btnnew=new JButton("Nuevo");
		btnnew.setToolTipText("Agregar Nuevo Usuario");
		btnnew.addActionListener(this);
		btnnew.setBackground(Color.WHITE);
		btnnew.setBounds(200,25,120,25);
		getContentPane().add(btnnew);
			
		btnmod=new JButton("Modificar");
		btnmod.setBackground(Color.WHITE);
		btnmod.setToolTipText("Modificar Usuario");
		btnmod.addActionListener(this);
		btnmod.setBounds(320,25,120,25);
		getContentPane().add(btnmod);
			
		btnel=new JButton("Eliminar");
		btnel.setBackground(Color.WHITE);
		btnel.setToolTipText("Desabilitar Usuario");
		btnel.addActionListener(this);
		btnel.setBounds(440,25,120,25);
		getContentPane().add(btnel);
			
		btnrest=new JButton("Restaurar");
		btnrest.setBackground(Color.WHITE);
		btnrest.setToolTipText("Restaurar Usuario");
		btnrest.addActionListener(this);
		btnrest.setBounds(560,25,120,25);
		getContentPane().add(btnrest);    
		
		btnguardar=new JButton("GUARDAR");
		btnguardar.setForeground(Color.BLACK);
		btnguardar.setBounds(230,280,120,50);
		btnguardar.setBackground(Color.WHITE);
		btnguardar.setEnabled(true);
		btnguardar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		                if(e.getActionCommand().equals("GUARDAR")){
		                    if(txtid.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar La Cedula","Campo Obligatorio",0);       
		                        txtid.requestFocus();
		                    }
		                    else if(txtpnombre.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Primer Nombre","Campo Obligatorio",0);
		                        txtpnombre.requestFocus();
		                    }     
		                    else if(txtsnombre.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Segundo Nombre","Campo Obligatorio",0);
		                        txtsnombre.requestFocus();
		                    }   
		                    else if(txtpapellido.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Primer Apellido","Campo Obligatorio",0);              
		                        txtpapellido.requestFocus();
		                    }
		                    else if(txtsapellido.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Segundo Apellido","Campo Obligatorio",0);              
		                        txtsapellido.requestFocus();
		                    }
		                    else if(((String) cmbpuesto.getSelectedItem()).isEmpty()){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar La Categoria","Campo Obligatorio",0);              
		                        cmbpuesto.requestFocus();
		                    }
		                    else{
		            			if(cmbpuesto.getSelectedItem().equals("Seleccionar Puesto")){
		            				JOptionPane.showMessageDialog(null, "Porfavor elija un Departamento");
		            				cmbpuesto.requestFocus();
		            			}
		                    else{
		                    	//JOptionPane.showMessageDialog(null, validarid(txtcedula.getText()));
		                    	if(!validarid(txtid.getText())){
			                        int res=JOptionPane.showConfirmDialog(null,"Seguro que desea Guardar a:\n"+txtid.getText()+"\n"+
		                    	txtpnombre.getText()+"\n"+txtsnombre.getText()+"\n"+txtpapellido.getText()+"\n"+txtsapellido.getText()+"\n"+
			                        		cmbpuesto.getSelectedItem().toString()+"\nComo Nuevo Empleado?","Confirmación",JOptionPane.YES_NO_OPTION);
			                        if(res == JOptionPane.YES_OPTION){
			                            guardar(txtid.getText(),txtpnombre.getText(),txtsnombre.getText(),txtpapellido.getText(),txtsapellido.getText(),cmbpuesto.getSelectedItem().toString());
			                            //JOptionPane.showMessageDialog(null,"Usuario "+txtuser.getText()+" Guardado Satisfactoriamente","Guardado",1);
			                            Limpiar();
			                            l.llenado_jlistid("SELECT emp_identidad FROM tbl_empleado;",Ids);
			                            l.llenado_jlist("SELECT emp_pnombre,emp_papellido FROM tbl_empleado;",model);
			                        }
		                    	}
		                    	else{
		                    		JOptionPane.showMessageDialog(null, "Numero de Cedula ya esta Registrado");
		                    		txtid.requestFocus();
		                    	}
		                    }
		                    }
		                }
		                else if(e.getActionCommand().equals("MODIFICAR")){
		                	if(txtid.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar La Cedula","Campo Obligatorio",0);                
		                        txtid.requestFocus();
		                    }
		                    else if(txtpnombre.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Primer Nombre","Campo Obligatorio",0);              
		                        txtpnombre.requestFocus();
		                    }     
		                    else if(txtsnombre.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Segundo Nombre","Campo Obligatorio",0);              
		                        txtsnombre.requestFocus();
		                    }     
		                    else if(txtpapellido.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Primer Apellido","Campo Obligatorio",0);              
		                        txtpapellido.requestFocus();
		                    }
		                    else if(txtsapellido.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Segundo Apellido","Campo Obligatorio",0);              
		                        txtsapellido.requestFocus();
		                    }
		                    else if(cmbpuesto.getSelectedItem().toString().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar La Categoria","Campo Obligatorio",0);              
		                        cmbpuesto.requestFocus();
		                    }
		                    else{
		                        int res=JOptionPane.showConfirmDialog(null,"Seguro que desea Modificar a:\n"+txtpnombre.getText()+"\n"+
		                        		txtsnombre.getText()+"\n"+txtpapellido.getText()+"\n"+txtsapellido.getText()+"\n"+cmbpuesto.getSelectedItem().toString()+
		                        "\nComo Empleado?","Confirmación",JOptionPane.YES_NO_OPTION);
		                        if(res == JOptionPane.YES_OPTION){
		                        	modificar(txtid.getText(),txtpnombre.getText(),txtsnombre.getText(),txtpapellido.getText(),txtsapellido.getText(),cmbpuesto.getSelectedItem().toString());
		                        	Limpiar();
		                        	l.llenado_jlistid("SELECT emp_identidad FROM tbl_empleado where emp_estado='activo';",Ids);
		                            l.llenado_jlist("SELECT emp_pnombre,emp_papellido FROM tbl_empleado where emp_estado='activo';",model);
		                        }
		                    }
		                }
		                else if(e.getActionCommand().equals("ELIMINAR")){
		                	if(txtid.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar La Cedula","Campo Obligatorio",0);                
		                        txtid.requestFocus();
		                    }
		                    else if(txtpnombre.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Primer Nombre","Campo Obligatorio",0);              
		                        txtpnombre.requestFocus();
		                    }     
		                    else if(txtsnombre.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Segundo Nombre","Campo Obligatorio",0);              
		                        txtsnombre.requestFocus();
		                    }     
		                    else if(txtpapellido.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Primer Apellido","Campo Obligatorio",0);              
		                        txtpapellido.requestFocus();
		                    }
		                    else if(txtsapellido.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Segundo Apellido","Campo Obligatorio",0);              
		                        txtsapellido.requestFocus();
		                    }
		                    else if(cmbpuesto.getSelectedItem().toString().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar La Categoria","Campo Obligatorio",0);              
		                        cmbpuesto.requestFocus();
		                    }
		                    else{
		                        int res=JOptionPane.showConfirmDialog(null,"Seguro que desea Eliminar a:\n"+txtpnombre.getText()+"\n"+
		                        		txtsnombre.getText()+"\n"+txtpapellido.getText()+"\n"+txtsapellido.getText()+"\n"+cmbpuesto.getSelectedItem().toString()+
		                        "\nComo Empleado?","Confirmación",JOptionPane.YES_NO_OPTION);
		                        if(res == JOptionPane.YES_OPTION){
		                        	eliminar(txtid.getText(),txtpnombre.getText(),txtsnombre.getText(),txtpapellido.getText(),txtsapellido.getText(),cmbpuesto.getSelectedItem().toString());
		                        	Limpiar();
		                        	l.llenado_jlistid("SELECT emp_identidad FROM tbl_empleado where emp_estado='activo';",Ids);
		                            l.llenado_jlist("SELECT emp_pnombre,emp_papellido FROM tbl_empleado where emp_estado='activo';",model);
		                        }
		                    }
		                }
		                else if(e.getActionCommand().equals("RESTAURAR")){
		                	if(txtid.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar La Cedula","Campo Obligatorio",0);                
		                        txtid.requestFocus();
		                    }
		                    else if(txtpnombre.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Primer Nombre","Campo Obligatorio",0);              
		                        txtpnombre.requestFocus();
		                    }     
		                    else if(txtsnombre.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Segundo Nombre","Campo Obligatorio",0);              
		                        txtsnombre.requestFocus();
		                    }     
		                    else if(txtpapellido.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Primer Apellido","Campo Obligatorio",0);              
		                        txtpapellido.requestFocus();
		                    }
		                    else if(txtsapellido.getText().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar El Segundo Apellido","Campo Obligatorio",0);              
		                        txtsapellido.requestFocus();
		                    }
		                    else if(cmbpuesto.getSelectedItem().toString().equals("")){
		                        JOptionPane.showMessageDialog(null,"Debe de Ingresar La Categoria","Campo Obligatorio",0);              
		                        cmbpuesto.requestFocus();
		                    }
		                    else{
		                        int res=JOptionPane.showConfirmDialog(null,"Seguro que desea Restaurar a:\n"+txtpnombre.getText()+"\n"+
		                        		txtsnombre.getText()+"\n"+txtpapellido.getText()+"\n"+txtsapellido.getText()+"\n"+cmbpuesto.getSelectedItem().toString()+
		                        "\nComo Empleado?","Confirmación",JOptionPane.YES_NO_OPTION);
		                        if(res == JOptionPane.YES_OPTION){
		                        	restaurar(txtid.getText(),txtpnombre.getText(),txtsnombre.getText(),txtpapellido.getText(),txtsapellido.getText(),cmbpuesto.getSelectedItem().toString());
		                        	Limpiar();
		                        	l.llenado_jlistid("SELECT emp_identidad FROM tbl_empleado where emp_estado='inactivo';",Ids);
		                            l.llenado_jlist("SELECT emp_pnombre,emp_papellido FROM tbl_empleado where emp_estado='inactivo';",model);
		                        }
		                    }
		                }
		            }});
			getContentPane().add(btnguardar);
		
		addWindowListener(new WindowAdapter(){
			public void windowOpened(WindowEvent e){}
			public void windowClosing(WindowEvent e){dispose();}
		});
		
		//validaracceso(user);
		
		label = new JLabel("");
		label.setBounds(0,0,1200,800);
		label.setIcon(new ImageIcon("fondo.jpg"));
		getContentPane().add(label);
		
		setSize(700,400);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int screenX = screen.width,screenY = screen.height;
		setLocation((screenX-this.getWidth())/2,((screenY-this.getHeight())/2)-10);
		setVisible(true);
		setResizable(false);
	}
    
	public void actionPerformed(ActionEvent e){
	    if(e.getActionCommand().equals("Nuevo")){
		        Limpiar();
		        lista.setEnabled(false);
		        habilitar(true);
		        l.llenado_jlistid("SELECT emp_identidad FROM tbl_empleado;",Ids);
                l.llenado_jlist("SELECT emp_pnombre,emp_papellido FROM tbl_empleado;",model);
		        btnguardar.setText("GUARDAR");
		    }
		    else if(e.getActionCommand().equals("Modificar")){
		        Limpiar();
		        lista.setEnabled(true);
		        habilitar(true);
		        l.llenado_jlistid("SELECT emp_identidad FROM tbl_empleado where emp_estado='activo';",Ids);
                l.llenado_jlist("SELECT emp_pnombre,emp_papellido FROM tbl_empleado where emp_estado='activo';",model);
		        btnguardar.setText("MODIFICAR");
		    }
		    else if(e.getActionCommand().equals("Eliminar")){
		        Limpiar();
		        btnguardar.setText("ELIMINAR");
		        lista.setEnabled(true);
		        habilitar(false);
		        l.llenado_jlistid("SELECT emp_identidad FROM tbl_empleado where emp_estado='activo';",Ids);
                l.llenado_jlist("SELECT emp_pnombre,emp_papellido FROM tbl_empleado where emp_estado='activo';",model);
		        Limpiar();btnguardar.setEnabled(true);
		    }
		    else if(e.getActionCommand().equals("Restaurar")){
		        Limpiar();
		        lista.setEnabled(true);
		        btnguardar.setText("RESTAURAR");
		        habilitar(false);
		        l.llenado_jlistid("SELECT emp_identidad FROM tbl_empleado where emp_estado='inactivo';",Ids);
                l.llenado_jlist("SELECT emp_pnombre,emp_papellido FROM tbl_empleado where emp_estado='inactivo';",model);
		        Limpiar();btnguardar.setEnabled(true);
		    }
		}
	
	public void habilitar(boolean cambio){
		txtid.setEnabled(cambio);
		txtpnombre.setEnabled(cambio);
		txtsnombre.setEnabled(cambio);
		txtpapellido.setEnabled(cambio);
		txtsapellido.setEnabled(cambio);
		cmbpuesto.setEnabled(cambio);
	}
		
		public void Limpiar(){
		    txtid.setText("");txtpnombre.setText("");txtsnombre.setText("");txtpapellido.setText("");txtsapellido.setText("");
		    cmbpuesto.setSelectedIndex(0);
		}
		
		public boolean validarid(String ide) {
			boolean continua=false;
	        try{
				Connection con=new Conexion().getConexion();
				Statement s1=con.createStatement();
				ResultSet rs=s1.executeQuery("select emp_identidad from tbl_empleado where emp_identidad='"+ide+"';");
				
				continua = rs.absolute(1);
				
				JOptionPane.showMessageDialog(null,"Ay Aja "+rs.getString(1));
				con.close();
				return continua;
			}
			catch(Exception exp){}
	        return continua;
	    }
		
		public boolean validaremp(int usuario) {
			ListaId.setSelectedIndex(usuario);
			int idus = ListaId.getSelectedIndex();
			//JOptionPane.showMessageDialog(null, idus);
			boolean continua=false;
	        String id1="",pnom="",snom="",pape="",sape="",puesto="";
			
	        //JOptionPane.showMessageDialog(null, "JMMM "+usuario+""+Ids.get(idus));
	        try{
				Connection con=new Conexion().getConexion();
				Statement s1=con.createStatement();
				ResultSet rs=s1.executeQuery("select emp_identidad,emp_pnombre,emp_snombre,emp_papellido,emp_sapellido,emp_puesto"+
											" from tbl_empleado where emp_identidad='"+Ids.get(idus)+"';");
				while(rs.next()){
					id1=rs.getString(1);
					pnom=rs.getString(2);
					snom=rs.getString(3);
					pape=rs.getString(4);
					sape=rs.getString(5);
					puesto=rs.getString(6);
					//JOptionPane.showMessageDialog(null, "Primero "+id1);
					//JOptionPane.showMessageDialog(null, "Segundo "+login2);
					txtid.setText(id1);
					txtpnombre.setText(pnom);
					txtsnombre.setText(snom);
					txtpapellido.setText(pape);
					txtsapellido.setText(sape);
					cmbpuesto.setSelectedItem(puesto);
					ident=id1;
				}
				con.close();
			}
			catch(Exception exp){
				//JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
			}
	        if(id1.equals(idus)){
	        	JOptionPane.showMessageDialog(null, "SE HA VALIDADO");
	        	continua=true;
	        }
	        return continua;
	    }
		
		public String estado(int usuario) {
			ListaId.setSelectedIndex(usuario);
			int idus = ListaId.getSelectedIndex();
	        String estado="";
	        //JOptionPane.showMessageDialog(null, "JMMM "+usuario+""+Ids.get(idus));
	        try{
				Connection con=new Conexion().getConexion();
				Statement s1=con.createStatement();
				ResultSet rs=s1.executeQuery("select emp_estado from tbl_empleado where emp_identidad='"+Ids.get(idus)+"';");
				while(rs.next()){
					estado=rs.getString(1);
				}
				con.close();
			}
			catch(Exception exp){
				//JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
			}
	        return estado;
	    }
		
		public void guardar(String id, String pnombre,String snombre,String papellido, String sapellido, String puesto){
			Llenado guardaremp = new Llenado();
			guardaremp.insertar("INSERT INTO tbl_empleado (emp_identidad,emp_pnombre,emp_snombre,emp_papellido,emp_sapellido,emp_puesto,emp_estado)"+
								" VALUES ('"+txtid.getText()+"','"+txtpnombre.getText()+"','"+txtsnombre.getText()+"','"+txtpapellido.getText()+
								"','"+txtsapellido.getText()+"','"+puesto+"', 'activo')");
			guardaremp.insertar("INSERT INTO tbl_emp_salarios (emp_identidad,emp_salario) VALUES ('"+txtid.getText()+"','0')");
		}
		
		public void modificar(String id, String pnombre,String snombre,String papellido, String sapellido, String puesto){
			Llenado modificarusu = new Llenado();
			modificarusu.insertar("UPDATE tbl_empleado SET emp_identidad='"+id+"', emp_pnombre='"+pnombre+"',emp_snombre='"+snombre+"',emp_papellido='"+papellido+"'"+
								",emp_sapellido='"+sapellido+"',emp_puesto='"+puesto+"',emp_estado='"+estado(lista.getSelectedIndex())+"' WHERE emp_identidad='"+ident+"';");
		}

		public void eliminar(String id, String pnombre,String snombre,String papellido, String sapellido, String puesto){
			Llenado eliminarusu = new Llenado();
			eliminarusu.insertar("UPDATE tbl_empleado SET emp_pnombre='"+pnombre+"',emp_snombre='"+snombre+"',emp_papellido='"+papellido+"'"+
					",emp_sapellido='"+sapellido+"',emp_puesto='"+puesto+"',emp_estado='inactivo' WHERE emp_identidad='"+id+"';");
		}
		
		public void restaurar(String id, String pnombre,String snombre,String papellido, String sapellido, String puesto){
			Llenado restaurarusu = new Llenado();
			restaurarusu.insertar("UPDATE tbl_empleado SET emp_pnombre='"+pnombre+"',emp_snombre='"+snombre+"',emp_papellido='"+papellido+"'"+
					",emp_sapellido='"+sapellido+"',emp_puesto='"+puesto+"',emp_estado='activo' WHERE emp_identidad='"+id+"';");
		}
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			//JOptionPane.showMessageDialog(null, lista.getSelectedValues());
			if(validaremp(lista.getSelectedIndex())){
				//JOptionPane.showMessageDialog(null, lista.getSelectedValues());
			}
		}
		
		@Override
		public void keyPressed(KeyEvent ev) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent ev) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent ev) {
			if(ev.getSource() == txtid){
				char c = ev.getKeyChar();
		        if(Character.isDigit(c) && txtid.getText().length()<13){}
		        else{
		        	ev.consume();
		        }
			}
		}
		
		public static void main(String args[]){
			new Manten_Empleado();
		}
}