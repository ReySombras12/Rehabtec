package clinicarehab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica extends JFrame {
    private JTable tablaPacientes;
    private JTextField txtNombre, txtApPaterno, txtApMaterno, txtExpediente;
    private JTextField txtEdad, txtFechaIngreso, txtHoraIngreso, txtSexo;
    private JTextField txtFechaNacimiento, txtDomicilio, txtRevisionMedica;
    private JTextField txtPsicologo, txtConsejero, txtPadrinoMadrina;
    private JButton btnAgregar, btnActualizar, btnLimpiar;
    private Pagina1_2 logicaNegocio;

    public InterfazGrafica() {
        logicaNegocio = new Pagina1_2();
        configurarVentana();
        initComponents();
        cargarDatosTabla();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Pacientes - RehabTec");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Panel principal con desplazamiento
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JScrollPane scrollPanePrincipal = new JScrollPane(panelPrincipal);
        add(scrollPanePrincipal);

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Paciente"));

        // Campos del formulario
        agregarCampo(panelFormulario, "Nombre:", txtNombre = new JTextField(20));
        agregarCampo(panelFormulario, "Apellido Paterno:", txtApPaterno = new JTextField(20));
        agregarCampo(panelFormulario, "Apellido Materno:", txtApMaterno = new JTextField(20));
        agregarCampo(panelFormulario, "N° Expediente:", txtExpediente = new JTextField(20));
        agregarCampo(panelFormulario, "Edad:", txtEdad = new JTextField(5));
        agregarCampo(panelFormulario, "Fecha Ingreso (YYYY-MM-DD):", txtFechaIngreso = new JTextField(10));
        agregarCampo(panelFormulario, "Hora Ingreso (HH:MM):", txtHoraIngreso = new JTextField(5));
        agregarCampo(panelFormulario, "Sexo (M/F):", txtSexo = new JTextField(1));
        agregarCampo(panelFormulario, "Fecha Nacimiento (YYYY-MM-DD):", txtFechaNacimiento = new JTextField(10));
        agregarCampo(panelFormulario, "Domicilio:", txtDomicilio = new JTextField(30));
        agregarCampo(panelFormulario, "Revisión Médica:", txtRevisionMedica = new JTextField(20));
        agregarCampo(panelFormulario, "Psicólogo/a:", txtPsicologo = new JTextField(20));
        agregarCampo(panelFormulario, "Consejero/a:", txtConsejero = new JTextField(20));
        agregarCampo(panelFormulario, "Padrino/Madrina:", txtPadrinoMadrina = new JTextField(20));

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAgregar = new JButton("Agregar Paciente");
        btnActualizar = new JButton("Actualizar Tabla");
        btnLimpiar = new JButton("Limpiar Campos");

        // Configurar acciones de los botones
        btnAgregar.addActionListener(e -> agregarPaciente());
        btnActualizar.addActionListener(e -> cargarDatosTabla());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnLimpiar);

        // Configurar tabla
        tablaPacientes = new JTable();
        JScrollPane scrollTabla = new JScrollPane(tablaPacientes);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Listado de Pacientes"));

        // Organizar componentes en el panel principal
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(scrollTabla, BorderLayout.SOUTH);
    }

    private void agregarCampo(JPanel panel, String etiqueta, JTextField campo) {
        JPanel panelCampo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCampo.add(new JLabel(etiqueta));
        panelCampo.add(campo);
        panel.add(panelCampo);
    }

    private void cargarDatosTabla() {
        try {
            Object[][] datos = logicaNegocio.getDatos();
            String[] columnas = {"Nombre", "Ap. Paterno", "Ap. Materno", "Expediente", "Edad", 
                                "F. Ingreso", "H. Ingreso", "Sexo", "F. Nacimiento", 
                                "Domicilio", "Rev. Médica", "Psicólogo", "Consejero", "Padrino/Madrina"};
            
            tablaPacientes.setModel(new DefaultTableModel(datos, columnas));
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarPaciente() {
        try {
            // Validar campos obligatorios
            if (txtNombre.getText().isEmpty() || txtApPaterno.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y Apellido Paterno son obligatorios", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            logicaNegocio.NuevoPaciente(
                txtNombre.getText(),
                txtApPaterno.getText(),
                txtApMaterno.getText(),
                txtExpediente.getText(),
                txtEdad.getText(),
                txtFechaIngreso.getText(),
                txtHoraIngreso.getText(),
                txtSexo.getText(),
                txtFechaNacimiento.getText(),
                txtDomicilio.getText(),
                txtRevisionMedica.getText(),
                txtPsicologo.getText(),
                txtConsejero.getText(),
                txtPadrinoMadrina.getText()
            );

            cargarDatosTabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Paciente agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar paciente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApPaterno.setText("");
        txtApMaterno.setText("");
        txtExpediente.setText("");
        txtEdad.setText("");
        txtFechaIngreso.setText("");
        txtHoraIngreso.setText("");
        txtSexo.setText("");
        txtFechaNacimiento.setText("");
        txtDomicilio.setText("");
        txtRevisionMedica.setText("");
        txtPsicologo.setText("");
        txtConsejero.setText("");
        txtPadrinoMadrina.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazGrafica interfaz = new InterfazGrafica();
            interfaz.setVisible(true);
        });
    }
}
