 
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

public class Accesos_Usuarios extends JDialog implements ActionListener,ListSelectionListener{
    private JButton btnguardar,btnlimpiar;//,t,n,x;
    private JList lista,ListaId;
    private JList lista2,ListaId2;
    private DefaultListModel model=new DefaultListModel(),Ids=new DefaultListModel();
    private DefaultListModel model2=new DefaultListModel(),Ids2=new DefaultListModel();
    private JLabel lbltitulo,label,lbltitulo2;//,imagen;
    //private JTextField txtuser,txtpass;
    Llenado l=new Llenado();
    Llenado l2=new Llenado();
    private int Id=0,iduser;
    String ad,userfijo,bool,user;
    JButton btnderecha,btnizquierda,btnallderecha,btnallizquierda;
    String login1 = "", login2 = "";
    JComboBox cmbEmpleado,cmbIdEmpleado;
    static String idemp="";
    
    void close(){
		//if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
		//		JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
		if(true){
			//new ManUser(user);
			dispose();
		}
	}
        
    public Accesos_Usuarios(/*final int ID,*/ final String names){
        setTitle("Accesos de Usuarios || Usuario: "+names);   
        Image icon = Toolkit.getDefaultToolkit().getImage("Images/food.gif");
        this.setIconImage(icon);
        setModal(true);
        user=names;
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
		
		JLabel label=new JLabel("Empleado");
		label.setBounds(20,20,120,30);
		getContentPane().add(label);
		
		cmbIdEmpleado = new JComboBox();
		cmbIdEmpleado.setBounds(label.getWidth(),label.getY(),120,20);
		cmbIdEmpleado.addItem("");
		cmbIdEmpleado.setVisible(false);
		
		cmbEmpleado = new JComboBox();
		cmbEmpleado.setBounds(label.getWidth(),label.getY(),200,20);
		cmbEmpleado.addItem("Seleccione Empleado");
		validarUsuario();
		cmbEmpleado.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cmbIdEmpleado.setSelectedIndex(cmbEmpleado.getSelectedIndex());
            	idemp=cmbIdEmpleado.getSelectedItem().toString();
            	
            	if(idemp.equals("")){}
            	else{
            		if(accesosSinRegistrar(idemp)==true){}
            		
            		l.llenado_jlist("SELECT M.modulo_codigo FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='inactivo';",Ids);
    		        l.llenado_jlist("SELECT M.modulo_nombre FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='inactivo';",model);
    		        
    		        l2.llenado_jlist("SELECT M.modulo_codigo FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='activo';",Ids2);
    		        l2.llenado_jlist("SELECT M.modulo_nombre FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='activo';",model2);
            	}
            }
        });
		getContentPane().add(cmbEmpleado);
		
		label=new JLabel();
		TitledBorder titled = new TitledBorder("Accesos de Usuario");
		label.setBorder(titled);
		label.setBounds(260,70,200,320);
		label.setForeground(Color.BLACK);
		getContentPane().add(label);

		btnderecha=new JButton(">");
		btnderecha.setBounds(320,120,100,40);
		getContentPane().add(btnderecha);
		btnderecha.addActionListener(this);
		
		btnizquierda=new JButton("<");
		btnizquierda.setBounds(320,160,100,40);
		getContentPane().add(btnizquierda);
		btnizquierda.addActionListener(this);
		
		lbltitulo=new JLabel("Accesos Enlistados");lbltitulo.setBounds(50,45,280,25);
		getContentPane().add(lbltitulo);
			        
		lista=new JList(model);lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.addListSelectionListener(this);JScrollPane scr1=new JScrollPane(lista);
		scr1.setBounds(50,70,180,350);getContentPane().add(scr1);
			        
		ListaId=new JList(Ids);//ShowHide(false);
			
		l.llenado_jlist("SELECT M.modulo_nombre FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='inactivo';",Ids);
        l.llenado_jlist("SELECT M.modulo_nombre FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='inactivo';",model);
            
		//l.llenado_jlist("SELECT modulo_codigo FROM tbl_acceso where acceso_estado=inactivo",Ids);
		//l.llenado_jlist("SELECT modulo_codigo FROM tbl_acceso where acceso_estado=inactivo",model);
		//bd.LlenarTable(model,Ids,"select cliente_codigo,cliente_nombre from tbl_cliente where cliente_estado like 'ACTIVO' order by cliente_nombre");
			        
		lbltitulo2=new JLabel("Accesos Agregados");lbltitulo2.setBounds(500,45,280,25);
		getContentPane().add(lbltitulo2);
			        
		lista2=new JList(model2);lista2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista2.addListSelectionListener(this);JScrollPane scrl2=new JScrollPane(lista2);
		scrl2.setBounds(500,70,180,350);getContentPane().add(scrl2);
			        
		ListaId2=new JList(Ids2);//ShowHide(false);
			
		l2.llenado_jlist("SELECT M.modulo_nombre FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='activo';",Ids2);
        l2.llenado_jlist("SELECT M.modulo_nombre FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='activo';",model2);
        
        
		addWindowListener(new WindowAdapter(){
		public void windowOpened(WindowEvent e){}
		public void windowClosing(WindowEvent e){dispose();}
		});
		//validaracceso(user);
		setSize(750,550);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int screenX = screen.width,screenY = screen.height;
		setLocation((screenX-this.getWidth())/2,((screenY-this.getHeight())/2)-10);
		setVisible(true);
    }
    
    	/*public static void main(String args[]){
		    ManUser aplication=new ManUser("Admin");
		}*/
		
		public void actionPerformed(ActionEvent e){
		    if(e.getSource()==btnderecha){
		    	//JOptionPane.showMessageDialog(null, accesosSinRegistrar(user));
		    	
		    	if(idemp.equals("")){
		    		JOptionPane.showMessageDialog(null,"Seleccione un Usuario");
		    	}
		    	else{
		    		uno_derecha(lista.getSelectedIndex());
			    	
			    	//JOptionPane.showMessageDialog(null,Ids.get(lista.getSelectedIndex()));
		    	}
		    	
		    	l.llenado_jlist("SELECT M.modulo_codigo FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='inactivo';",Ids);
		        l.llenado_jlist("SELECT M.modulo_nombre FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='inactivo';",model);
		        
		        l2.llenado_jlist("SELECT M.modulo_codigo FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='activo';",Ids2);
		        l2.llenado_jlist("SELECT M.modulo_nombre FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='activo';",model2);

		        
		    }
		    if(e.getSource()==btnizquierda){
		    	//JOptionPane.showMessageDialog(null, accesosSinRegistrar(user));
		    	
		    	if(idemp.equals("")){
		    		JOptionPane.showMessageDialog(null,"Seleccione un Usuario");
		    	}
		    	else{
		    		uno_izquierda(lista2.getSelectedIndex());
			    	
			    	//JOptionPane.showMessageDialog(null,Ids2.get(lista2.getSelectedIndex()));
		    	}
		    	
		    	l.llenado_jlist("SELECT M.modulo_codigo FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='inactivo';",Ids);
		        l.llenado_jlist("SELECT M.modulo_nombre FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='inactivo';",model);
		        
		        l2.llenado_jlist("SELECT M.modulo_codigo FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='activo';",Ids2);
		        l2.llenado_jlist("SELECT M.modulo_nombre FROM tarea2.tbl_acceso A, tarea2.tbl_modulo M where A.modulo_codigo = M.modulo_codigo and A.usuario_codigo='"+idemp+"'  and A.acceso_estado='activo';",model2);

		        
		    }
		}
		
		public boolean validarUsuario() {
			boolean continua=false;
	        String codus="",passus="",estadous="";
	        try{
				Connection con=new Conexion().getConexion();
				Statement s1=con.createStatement();
				ResultSet rs=s1.executeQuery("SELECT usuario_codigo,usuario_clave,usuario_estado FROM tarea2.tbl_usuario;");
				while(rs.next()){
					codus=rs.getString(1);
					passus=rs.getString(2);
					estadous=rs.getString(3);
					cmbEmpleado.addItem(codus);
					cmbIdEmpleado.addItem(codus);
					continua=true;
				}
				con.close();
			}
			catch(Exception exp){
				//JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
			}
	        return continua;
	    }
		
		public boolean accesosSinRegistrar(String usuario) {
	        boolean continua=false;
	        //JOptionPane.showMessageDialog(null, "JMMM "+usuario+""+model.get(usuario));
	        try{
				Connection con=new Conexion().getConexion();
				Statement s1=con.createStatement();
				//ResultSet rs=s1.executeQuery("select usuario_codigo,usuario_clave from tarea2.tbl_usuario where usuario_codigo='"+model.get(usuario)+"';");
				ResultSet rs=s1.executeQuery("select t1.modulo_codigo from tarea2.tbl_modulo t1"+
											" where  not exists (SELECT NULL from tarea2.tbl_acceso t2 where t2.modulo_codigo = t1.modulo_codigo"+
											" and t2.usuario_codigo='"+usuario+"');");
				while(rs.next()){
					login1=rs.getString(1);
					guardar(login1,usuario);
				}
				continua = rs.absolute(1);
				//JOptionPane.showMessageDialog(null,"Ay Aja "+rs.getString(1));
				con.close();
				return continua;
			}
			catch(Exception exp){
				//JOptionPane.showMessageDialog(null,""+exp,"Error al encontrar el Usuario en la Base",0);//System.exit(0);
				return continua;
			}
	    }
		
		public void guardar(String modulo, String usuario){
			Llenado guardarusu = new Llenado();
			guardarusu.insertar("INSERT INTO tarea2.tbl_acceso (modulo_codigo,usuario_codigo,acceso_estado)"+
								" VALUES ('"+modulo+"','"+usuario+"','inactivo')");
			//JOptionPane.showMessageDialog(null, "Se Guardo");
		}
		
		public void uno_derecha(int acces){
			Llenado d1 = new Llenado();
			d1.insertar("UPDATE tarea2.tbl_acceso SET acceso_estado='activo' WHERE modulo_codigo='"+Ids.get(acces)+"' and usuario_codigo='"+idemp+"';");
		}
		
		public void uno_izquierda(int acces){
			Llenado i1 = new Llenado();
			i1.insertar("UPDATE tarea2.tbl_acceso SET acceso_estado='inactivo' WHERE modulo_codigo='"+Ids2.get(acces)+"' and usuario_codigo='"+idemp+"';");
		}

		public void todos_derecha(){
			Llenado dall = new Llenado();
			dall.insertar("UPDATE tarea2.tbl_acceso SET acceso_estado='activo' WHERE usuario_codigo='"+user+"';");
		}
		
		public void todos_izquierda(){
			Llenado iall = new Llenado();
			iall.insertar("UPDATE tarea2.tbl_acceso SET acceso_estado='inactivo' WHERE usuario_codigo='"+user+"';");
		}
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			//JOptionPane.showMessageDialog(null, lista.getSelectedValues());
			if(lista.getSelectedValues().equals("")){
				uno_derecha(lista.getSelectedIndex());
			}
			if(lista.getSelectedValues().equals("")){
				uno_derecha(lista.getSelectedIndex());
			}
		}
}