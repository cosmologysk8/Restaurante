package windows.administrator;

import bbdd.EmpleadoBD;
import modelos.ModeloEmpleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;



public class Empleado extends JFrame{
        private static final ImageIcon imagenFondo = rutaDeImagen();
        public static final Dimension pantalla = new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.85), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.85));
    JFrame window = new JFrame("Restaurante Paco");// creando instancia FJframe
        static Font fuente=new Font("Arial", Font.ITALIC, 30);

        private static JTextField id;
        private static JTextField codigo_empleado;
        private static JTextField nombre;
        private static JTextField apellidos;

        public Empleado() {

             // indica tamaño de la ventana

            window.setSize(pantalla); // indica tamaño de la ventana

            JPanel panel = panel1();
            panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
            panel.setOpaque(false);

            JPanel panel2 = panel2();
            panel2.setOpaque(false);
            JPanel fondo = crearPanelImagenFondo();

            JButton boton_crear, boton_modificar, boton_eliminar, boton_buscar;
            boton_crear = boton_crear();
            boton_modificar = boton_modificar();
            boton_eliminar = boton_eliminar();
            boton_buscar = boton_buscar();
            JLabel etiquetaid = id();
            id = new JTextField();
            etiquetaid.setLabelFor(id);

            JLabel etiquetacodigo = codigo();
            codigo_empleado = new JTextField();
            etiquetacodigo.setLabelFor(codigo_empleado);

            JLabel etiquetasapellidos = apellidos();
            apellidos = new JTextField();
            etiquetasapellidos.setLabelFor(apellidos);

            JLabel etiquetanombre = nombre();
            nombre = new JTextField();
            etiquetanombre.setLabelFor(nombre);

            panel.add(etiquetaid);
            panel.add(id);
            panel.add(etiquetacodigo);
            panel.add(codigo_empleado);
            panel.add(etiquetanombre);
            panel.add(nombre);
            panel.add(etiquetasapellidos);
            panel.add(apellidos);


            //Añado archivos en Panel de los botones.
            panel2.add(boton_crear);
            panel2.add(boton_buscar);
            panel2.add(boton_eliminar);


            window.add(panel);
            window.add(panel2);
            window.add(fondo);
            window.setVisible(true);
        }
        private static JPanel panel1 (){
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 2,15 ,15));
            panel.setBounds(270, 100, 700, 500);
            return panel;
        }
        private static JPanel panel2 (){
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 4, 5 ,5 ));
            panel.setBounds(320, 600, 600, 70);
            return panel;
        }
        public static JLabel id(){
            JLabel id = new JLabel();
            id.setFont(fuente);
            id.setForeground(Color.WHITE);
            id.setText("Id");
            return id;
        }
        public static JLabel codigo(){
            JLabel codigo = new JLabel();
            codigo.setFont(fuente);
            codigo.setForeground(Color.WHITE);
            codigo.setText("Código");
            return codigo;
        }
        public static JLabel nombre(){
            JLabel nombre = new JLabel();
            nombre.setFont(fuente);
            nombre.setForeground(Color.WHITE);
            nombre.setText("Nombre");
            return nombre;
        }

        public static JLabel apellidos(){
            JLabel apellidos = new JLabel();
            apellidos.setFont(fuente);
            apellidos.setForeground(Color.WHITE);
            apellidos.setText("Apellidos");
            return apellidos;
        }


        private static JButton boton_crear() {
            JButton boton = new JButton("Crear");
            boton.setFocusPainted(false);
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ModeloEmpleado empleado = new ModeloEmpleado();
                    empleado.setId(Integer.parseInt(id.getText()));
                    empleado.setCodigoEmpleado(codigo_empleado.getText());
                    empleado.setNombre(nombre.getText());
                    empleado.setApellidos(apellidos.getText());
                    EmpleadoBD.crearActualizarEmpleado(empleado);
                }

            });
            // boton.addActionListener(new AccionAbrirMenuCocinero());
            return boton;
            }

        private static JButton boton_buscar() {
            JButton boton = new JButton("Buscar");
            boton.setFocusPainted(false);
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Integer empleado = Integer.valueOf(id.getText());
                    ModeloEmpleado empl = EmpleadoBD.obtenerPorId(empleado);
                    id.setText(String.valueOf(empl.getId()));
                    codigo_empleado.setText(String.valueOf(empl.getCodigoEmpleado()));
                    nombre.setText(String.valueOf(empl.getNombre()));
                    apellidos.setText(String.valueOf(empl.getApellidos()));
                }
            });
            // boton.addActionListener(new AccionAbrirMenuCocinero());
            return boton;
        }
        private static JButton boton_eliminar() {
            JButton eliminar  = new JButton("Eliminar");
            eliminar.addActionListener(new ActionListener( ) {
                public void actionPerformed(ActionEvent e) {
                    ModeloEmpleado empleado = new ModeloEmpleado();
                    empleado.setId(Integer.parseInt(id.getText()));
                    EmpleadoBD.eliminarEmpleado(empleado);
                }
            });
            return eliminar;
        }
        private static JButton boton_modificar() {
            JButton boton = new JButton("Modificar");
            boton.setFocusPainted(false);
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Se ha pulsado el botón cocinero");
                }
            });
            // boton.addActionListener(new AccionAbrirMenuCocinero());
            return boton;
        }


        private static JPanel crearPanelImagenFondo(){
            JPanel panel = new JPanel(new BorderLayout()){
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(imagenFondo.getImage(), 0, 0, null);
                }
            };
            return panel;
        }
        private static ImageIcon rutaDeImagen () {
            String ruta = new File("").getAbsolutePath() + "\\imagenes\\empleado.jpg";
            ImageIcon imagenempleado = new ImageIcon(ruta);
            Image tamayo = imagenempleado.getImage().getScaledInstance(1400, 1000, Image.SCALE_SMOOTH);
            imagenempleado.setImage(tamayo);

            return imagenempleado;
        }
    }


