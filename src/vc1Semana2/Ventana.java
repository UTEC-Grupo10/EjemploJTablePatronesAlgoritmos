package vc1Semana2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Ventana {

	private JFrame frame;
	private JTextField textCedula;
	private JTextField textApellido;
	private JTextField textNombre;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JButton btnMostrarTodo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textCedula = new JTextField();
		textCedula.setBounds(132, 36, 86, 20);
		frame.getContentPane().add(textCedula);
		textCedula.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setBounds(132, 114, 86, 20);
		frame.getContentPane().add(textApellido);
		textApellido.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(132, 69, 86, 20);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ci=textCedula.getText();
				String nombre=textNombre.getText();
				String apellido=textApellido.getText();
				//Se controla que no se ingresen valores vacios
					if(
							!ci.isEmpty() &&
							!nombre.isEmpty() &&
							!apellido.isEmpty()
					) { 
						if(esCIValida(ci)) { //Se controla que la cedula se ingrese con el largo y formato correcto

							Empleado e = new Empleado(ci,nombre,apellido);
							DAOEmpleados.insert(e);
							JOptionPane.showMessageDialog(null, "Se ingreso correctamente.","Exito", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null,"Formato de cedula incorrecto.", "Error",JOptionPane.ERROR_MESSAGE);

						}
					}else {
						JOptionPane.showMessageDialog(null,"Tiene que completar todos los datos", "Error",JOptionPane.ERROR_MESSAGE);
					}
				


			}
		});
		btnAlta.setBounds(254, 35, 89, 23);
		frame.getContentPane().add(btnAlta);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ci=textCedula.getText();
				String nombre=textNombre.getText();
				String apellido=textApellido.getText();
				Empleado e1 = new Empleado(ci,nombre,apellido);
				//Se controla que se ingresen los datos correctamente y dispara una alerta en cada caso
				if(DAOEmpleados.delete(e1)) {
					JOptionPane.showMessageDialog(null, "Eliminacion correcta","Exito", JOptionPane.INFORMATION_MESSAGE);

				}else {
					JOptionPane.showMessageDialog(null,"No se pudo eliminar.", "Error",JOptionPane.ERROR_MESSAGE);

				}

				//DAOEmpleados.delete(e1);
			}
		});
		btnEliminar.setBounds(254, 83, 89, 23);
		frame.getContentPane().add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ci=textCedula.getText();
				Empleado re=DAOEmpleados.find(ci);
				//Se controla que se ingresen los datos correctamente y dispara una alerta en cada caso
				if(re != null) {
					textNombre.setText(re.getNombre());
					textApellido.setText(re.getApellido());
				}else {
					JOptionPane.showMessageDialog(null,"La busqueda no obtuvo resultados.", "Error",JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		btnBuscar.setBounds(254, 128, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ci=textCedula.getText();
				String nombre=textNombre.getText();
				String apellido=textApellido.getText();
				Empleado e1 = new Empleado(ci,nombre,apellido);
				//Se controla que se ingresen los datos correctamente y dispara una alerta en cada caso
				if(DAOEmpleados.update(e1)) {
					JOptionPane.showMessageDialog(null, "Modificacion correcta","Exito", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null,"Ocurrio un error al modificar, verifique los datos.", "Error",JOptionPane.ERROR_MESSAGE);
				};
			}			
		});
		btnModificar.setBounds(254, 176, 89, 23);
		frame.getContentPane().add(btnModificar);
		
		btnMostrarTodo = new JButton("Mostrar Todo");
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				MostrarTodo mt=new MostrarTodo();
				mt.mostrar();
			}
		});
		btnMostrarTodo.setBounds(61, 176, 157, 23);
		frame.getContentPane().add(btnMostrarTodo);
		
		JLabel lblCedula = new JLabel("cedula");
		lblCedula.setBounds(48, 39, 46, 14);
		frame.getContentPane().add(lblCedula);
		
		JLabel lblNombre = new JLabel("nombre");
		lblNombre.setBounds(48, 72, 46, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(48, 117, 46, 14);
		frame.getContentPane().add(lblApellido);
		
		
	}
	public static boolean esCIValida(String ci) {

		if(ci.length() != 7 && ci.length() != 8){
			return false;
		}else{
			try{
				Integer.parseInt(ci);
			}catch (NumberFormatException e){
				return false;
			}
	    }
		return true;
	}
}
