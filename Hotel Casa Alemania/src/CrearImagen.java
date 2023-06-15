import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

class CrearImagen extends JFrame{
    
	private JTextArea text;
    
	Calendar calendario = new GregorianCalendar();
	int dia = calendario.get(Calendar.DAY_OF_MONTH);
	int mes = calendario.get(Calendar.MONTH)+1;
	int anio = calendario.get(Calendar.YEAR);
	int horas = calendario.get(Calendar.HOUR_OF_DAY);
	int min = calendario.get(Calendar.MINUTE);
	int seg = calendario.get(Calendar.SECOND);
	
    /*public static void main(String args[]){
        new CrearImagen();
    }*/
    
	void close(){
		if(JOptionPane.showConfirmDialog(rootPane,"Desea Cerrar la Ventana del Sistema?","Salir del Sistema",
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			//System.exit(0);
			new Ventana(Sesion.iduser, Sesion.us);
			dispose();
		}
	}
	
    public CrearImagen(String fa){
    	setTitle("Creando Imagen de Factura");

    	setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
		
        setLayout(new BorderLayout());
        text = new JTextArea();
        add(new JScrollPane(text));

        JButton btnPrint = new JButton("Crear Factura");
        add(btnPrint, BorderLayout.SOUTH);

        btnPrint.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	BufferedImage img = new BufferedImage(text.getWidth(), text.getHeight(), BufferedImage.TYPE_INT_RGB);
	            Graphics2D g2d = img.createGraphics();
	            text.printAll(g2d);
	            g2d.dispose();
	            try {
	            	ImageIO.write(img, "png", new File("Fct("+dia+"-"+mes+"-"+anio+").png"));
	            }catch(IOException ex) {
	            	ex.printStackTrace();
	            }
	        }
        });

        text.setWrapStyleWord(true);
        text.setColumns(15);
        text.setLineWrap(true);
        text.setText(fa);
        text.setEditable(false);
        
        setSize(200, 300);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public CrearImagen(String n, String id, String r, String h, String d, String ph, String imp, String pt, String fecha, String hora) {
    	//setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Creando Imagen de Factura");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowClosing(java.awt.event.WindowEvent evt){
				close();
			}
		});
		
        setLayout(new BorderLayout());
        text = new JTextArea();
        add(new JScrollPane(text));

        JButton btnPrint = new JButton("Crear Factura");
        add(btnPrint, BorderLayout.SOUTH);

        btnPrint.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	BufferedImage img = new BufferedImage(text.getWidth(), text.getHeight(), BufferedImage.TYPE_INT_RGB);
	            Graphics2D g2d = img.createGraphics();
	            text.printAll(g2d);
	            g2d.dispose();
	            try {
	            	ImageIO.write(img, "png", new File("Fct("+dia+"-"+mes+"-"+anio+")("+horas+"-"+min+"-"+seg+").png"));
	            }catch(IOException ex) {
	            	ex.printStackTrace();
	            }
	            dispose();
	        }
        });

        text.setWrapStyleWord(true);
        text.setColumns(15);
        text.setLineWrap(true);
        text.setText("        Hotel Narnia\nCliente:"+n+"\nID:"+id+"\nHabitacion:"+r+" "+h+"\nCantidad Dias:"+d+
        		"\nPago Habitacion:"+ph+"\nImp15%:"+imp+"\nPago Total:"+pt+"\nFecha:"+fecha+"  "+hora);
        text.setEditable(false);
        
        setSize(200, 300);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}