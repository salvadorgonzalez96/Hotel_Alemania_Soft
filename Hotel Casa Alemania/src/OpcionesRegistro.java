import javax.swing.*;
import java.awt.*;
public class OpcionesRegistro extends JDialog
{
   
    static int i;
    public static void main(String args[])
    {
        new OpcionesRegistro();
    }
    
    public OpcionesRegistro()
    {
        setTitle("Reguistro Hotel");
        //Usuario.id=i;


        ImageIcon icono=new ImageIcon("mag.gif");
        getContentPane().add(build());
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        setSize(900,690);
        setLocation(300,100);
        setVisible(true);
    }
    
    public JComponent build()
    {
        JTabbedPane pest=new JTabbedPane();
        Registro n1=new Registro();
        ImageIcon icono=new ImageIcon("mag.gif");
        //if(ar.bus_linea("Usuario_Acceso.txt",Usuario.iduser+";5;"))
        //pest.addTab("Ingreso", null, n1.build(Usuario.iduser,Usuario.us));
        pest.addTab("Reguistro Hotel", null,n1.build());
        
        return pest;
    }
}