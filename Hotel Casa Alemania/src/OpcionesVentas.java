import javax.swing.*;
import java.awt.*;
public class OpcionesVentas extends JDialog
{
    ArregloArchivo ar;
    static int i;
    
    /*public static void main(String args[]){
        new OpcionesVentas("1","");
    }*/
    
    void close(){
		if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			//System.exit(0);
			new Ventana(Sesion.iduser, Sesion.us);
			dispose();
		}
	}
    
    public OpcionesVentas(String i, String u){
        setTitle("Pincipal Ventas");
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
        
        Sesion.iduser=i;
        Sesion.us=u;
        ar=new ArregloArchivo();
        ImageIcon icono=new ImageIcon("mag.gif");
        getContentPane().add(build());
        //this.getContentPane().setBackground(Color.gray);
        setSize(900,600);
        setLocation(300,100);
        setVisible(true);
    }
    
    public JComponent build(){
        JTabbedPane pest=new JTabbedPane();
        Venta n1=new Venta();
        ImageIcon icono=new ImageIcon("mag.gif");
        //if(ar.bus_linea("Usuario_Acceso.txt",Sesion.iduser+";5;"))
        pest.addTab("Ingreso", null, n1.build(Sesion.iduser,Sesion.us));
        return pest;
    }
}