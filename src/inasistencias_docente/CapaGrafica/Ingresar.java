/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CapaGrafica;

import CapaLogica.Administrador;
import CapaLogica.Docente;
import CapaLogica.Inasistencia_docente;
import CapaLogica.FachadaLogica;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana principal para la gestión de inasistencias de docentes.
 * Pertenece a la capa gráfica.
 */
public class Ingresar extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(Ingresar.class.getName());

    private final FachadaLogica fachada;
    private final String usuarioAdministrativo;
    private String ciAdministrativo;

    public Ingresar(String usuarioAdministrativo, String ciAdministrativo) {
        this.usuarioAdministrativo = usuarioAdministrativo;
        this.ciAdministrativo = ciAdministrativo;
        this.fachada = new FachadaLogica();
        initComponents();
        setLocationRelativeTo(null);
        prepararTabla();
        actualizarEtiquetaUsuario();
        cargarTodasLasInasistencias();
    }

    public Ingresar() {
        this(null, null);
    }

    /**
     * Configura el modelo y comportamiento de la tabla principal.
     */
    private void prepararTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Docente", "Grupo", "Turno", "Tipo", "Motivo", "Desde", "Hasta", "Administrativo"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Integer.class;
                }
                return Object.class;
            }
        };
        tablaRegistros.setModel(modelo);
        tablaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * Recupera todas las inasistencias y las muestra en la tabla.
     */
    private void cargarTodasLasInasistencias() {
        cargarRegistros(fachada.obtenerTodas());
    }

    /**
     * Recupera únicamente las inasistencias vigentes hoy.
     */
    private void cargarInasistenciasDeHoy() {
        cargarRegistros(fachada.obtenerDeHoy());
    }

    /**
     * Pinta en la tabla los registros recibidos desde la capa lógica.
     *
     * @param registros lista de inasistencias a renderizar.
     */
    private void cargarRegistros(List<Inasistencia_docente> registros) {
        DefaultTableModel modelo = (DefaultTableModel) tablaRegistros.getModel();
        modelo.setRowCount(0);

        if (registros == null || registros.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No se encontraron registros para los filtros seleccionados.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Inasistencia_docente registro : registros) {
            Docente docente = registro.getDocente();
            String nombreDocente = docente != null ? docente.getNombreCompleto() : null;
            if (nombreDocente == null || nombreDocente.isBlank()) {
                nombreDocente = registro.getCiDocente();
            }

            Administrador admin = registro.getAdministrador();
            String nombreAdmin = admin != null ? admin.getNombreCompleto() : null;
            if (nombreAdmin == null || nombreAdmin.isBlank()) {
                nombreAdmin = registro.getCiAdministrativo();
            }

            modelo.addRow(new Object[]{
                registro.getId(),
                nombreDocente,
                docente != null ? docente.getGrupo() : null,
                docente != null ? docente.getTurno() : null,
                registro.getTipo(),
                registro.getMotivo(),
                registro.getFechaInicio(),
                registro.getFechaFin(),
                nombreAdmin
            });
        }
    }

    /**
     * Actualiza la etiqueta informativa con el usuario administrativo activo.
     */
    private void actualizarEtiquetaUsuario() {
        String usuario = usuarioAdministrativo;
        String ci = ciAdministrativo;
        if ((usuario == null || usuario.isBlank()) && (ci == null || ci.isBlank())) {
            lblUsuarioActivo.setText("Usuario no identificado");
            return;
        }

        String textoUsuario = Objects.toString(usuario, "N/D").trim();
        String textoCi = Objects.toString(ci, "N/D").trim();
        lblUsuarioActivo.setText("Usuario: " + textoUsuario + " - CI: " + textoCi);
    }

    /**
     * Reinicia los campos del formulario para ingresar un nuevo registro.
     */
    private void limpiarFormulario() {
        txtCiDocente.setText("");
        txtTipo.setText("");
        txtMotivo.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        txtCiDocente.requestFocusInWindow();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblUsuarioActivo = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        lblCiDocente = new javax.swing.JLabel();
        txtCiDocente = new javax.swing.JTextField();
        lblTipo = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        lblMotivo = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        lblFechaInicio = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        lblFechaFin = new javax.swing.JLabel();
        txtFechaFin = new javax.swing.JTextField();
        lblFormatoFechas = new javax.swing.JLabel();
        BtnGuardarLic = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();
        btnVerHoy = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tablaRegistros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitulo.setText("Registro de inasistencias");

        lblUsuarioActivo.setText("Usuario no identificado");

        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        lblCiDocente.setText("CI del docente");

        lblTipo.setText("Tipo");

        lblMotivo.setText("Motivo");

        lblFechaInicio.setText("Fecha inicio");

        lblFechaFin.setText("Fecha fin");

        lblFormatoFechas.setText("Formato fecha: yyyy-MM-dd");

        BtnGuardarLic.setText("Guardar");
        BtnGuardarLic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarLicActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar seleccionado");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRefrescar.setText("Refrescar");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });

        btnVerHoy.setText("Ver de hoy");
        btnVerHoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerHoyActionPerformed(evt);
            }
        });

        tablaRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Docente", "Grupo", "Turno", "Tipo", "Motivo", "Desde", "Hasta", "Administrativo"
            }
        ));
        scrollTabla.setViewportView(tablaRegistros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTabla)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCerrarSesion)
                        .addGap(18, 18, 18)
                        .addComponent(lblTitulo)
                        .addGap(18, 18, 18)
                        .addComponent(lblUsuarioActivo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCiDocente)
                            .addComponent(lblTipo)
                            .addComponent(lblMotivo)
                            .addComponent(lblFechaInicio)
                            .addComponent(lblFechaFin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCiDocente)
                            .addComponent(txtTipo)
                            .addComponent(txtMotivo)
                            .addComponent(txtFechaInicio)
                            .addComponent(txtFechaFin)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnGuardarLic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefrescar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerHoy)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFormatoFechas)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrarSesion)
                    .addComponent(lblTitulo)
                    .addComponent(lblUsuarioActivo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCiDocente)
                    .addComponent(txtCiDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMotivo)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaInicio)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaFin)
                    .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFormatoFechas)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnGuardarLic)
                    .addComponent(btnEliminar)
                    .addComponent(btnRefrescar)
                    .addComponent(btnVerHoy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Procesa el formulario para registrar una nueva inasistencia.
     */
    private void BtnGuardarLicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarLicActionPerformed
        try {
            String ciDocente = txtCiDocente.getText().trim();
            String tipo = txtTipo.getText().trim();
            String motivo = txtMotivo.getText().trim();
            String fechaInicioStr = txtFechaInicio.getText().trim();
            String fechaFinStr = txtFechaFin.getText().trim();

            if (ciDocente.isEmpty() || tipo.isEmpty() || motivo.isEmpty()
                    || fechaInicioStr.isEmpty() || fechaFinStr.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Debe completar todos los campos.",
                        "Datos faltantes",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate inicio = LocalDate.parse(fechaInicioStr, formatter);
            LocalDate fin = LocalDate.parse(fechaFinStr, formatter);

            if (fin.isBefore(inicio)) {
                JOptionPane.showMessageDialog(this,
                        "La fecha de fin no puede ser anterior a la de inicio.",
                        "Rango inválido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String ciAdmin = this.ciAdministrativo;
            if (ciAdmin == null || ciAdmin.isBlank()) {
                String sugerencia = usuarioAdministrativo != null ? usuarioAdministrativo : "";
                ciAdmin = JOptionPane.showInputDialog(this,
                        "Ingrese el CI del administrativo",
                        sugerencia);
                if (ciAdmin == null) {
                    return;
                }
                ciAdmin = ciAdmin.trim();
                if (ciAdmin.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Debe indicar el CI del administrativo.",
                            "Dato faltante",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                this.ciAdministrativo = ciAdmin;
                actualizarEtiquetaUsuario();
            }

            Inasistencia_docente registro = new Inasistencia_docente(null, tipo, motivo, inicio, fin, ciDocente, ciAdmin);
            boolean exito = fachada.registrarInasistencia(registro);

            if (exito) {
                JOptionPane.showMessageDialog(this,
                        "Datos guardados correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
                cargarTodasLasInasistencias();
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo registrar la inasistencia.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (DateTimeParseException ex) {
            logger.log(Level.FINE, "Error al convertir las fechas", ex);
            JOptionPane.showMessageDialog(this,
                    "El formato de fecha debe ser yyyy-MM-dd.",
                    "Error de fecha",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnGuardarLicActionPerformed

    /**
     * Elimina el registro seleccionado en la tabla.
     */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tablaRegistros.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un registro para eliminar.",
                    "Selección requerida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object valorId = tablaRegistros.getValueAt(filaSeleccionada, 0);
        if (valorId == null) {
            JOptionPane.showMessageDialog(this,
                    "El registro seleccionado no tiene un identificador válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id;
        if (valorId instanceof Number) {
            id = ((Number) valorId).intValue();
        } else {
            try {
                id = Integer.parseInt(valorId.toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "El identificador seleccionado no es válido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        boolean exito = fachada.eliminarInasistencia(id);
        if (exito) {
            JOptionPane.showMessageDialog(this,
                    "Registro eliminado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            cargarTodasLasInasistencias();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error al eliminar registro.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * Recarga todas las inasistencias desde la base de datos.
     */
    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        cargarTodasLasInasistencias();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    /**
     * Muestra únicamente las inasistencias activas en el día.
     */
    private void btnVerHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerHoyActionPerformed
        cargarInasistenciasDeHoy();
    }//GEN-LAST:event_btnVerHoyActionPerformed

    /**
     * Cierra la sesión actual y regresa a la pantalla de login.
     */
    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        dispose();
        setVisible(false);
        IniciarSesi iniciar = new IniciarSesi();
        iniciar.setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardarLic;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnVerHoy;
    private javax.swing.JLabel lblCiDocente;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblFormatoFechas;
    private javax.swing.JLabel lblMotivo;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuarioActivo;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tablaRegistros;
    private javax.swing.JTextField txtCiDocente;
    private javax.swing.JTextField txtFechaFin;
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
