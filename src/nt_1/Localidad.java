/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nt_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Alumno
 */
public class Localidad extends javax.swing.JFrame {
public boolean empty = true;
public Connection coneccion;
public Statement sentencia;
public String bd="pais";
public String usuario="root";
public String contraseña="123456";
public String url="jdbc:mysql://localhost/"+bd;
public static ResultSet rs;
public static ResultSet rs2;
public String estado;
int [] A = new int [50];
    public void PrepararBaseDeDatos(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            coneccion = DriverManager.getConnection(url, usuario, contraseña);
            if (coneccion != null){
                System.out.println("Conexion a base de datos ... OK");
            }
        } catch (Exception e){
            System.out.println("No se puedo conectar con la base de datos");
        }
        try{
            sentencia = coneccion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch(Exception e){
            System.out.println("Error al crear objeto consulta");
        }
    }
    public void Refrescar(){
        try{
            int i=0;
            cboProvincia.removeAllItems();
            rs2 = sentencia.executeQuery("SELECT * FROM pais.provincia ORDER BY nombre");
            rs2.last();
            if(rs2.getRow()==0){
                JOptionPane.showMessageDialog(null, "Debe cargar una provincia");
                btnAlta.setEnabled(false);
                //Dejo el btnProvincia habilitada
            }else{
                rs2.beforeFirst();
                while(rs2.next()){
                    cboProvincia.addItem(rs2.getString("nombre"));
                    A[i]=rs2.getInt("id_provincia");
                    i++;
                }
            }
            //CagarConsulta();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void CargarConsulta(){
        Refrescar();
        try{
            rs2 = sentencia.executeQuery("SELECT localidad.id_localidad, localidad.nombre_localidad, localidad.cod_postal, localidad.provincia_id, provincia.nombre FROM pais.localidad, pais.provincia WHERE pais.localidad.provincia_id = pais.provincia.id_provincia ORDER BY localidad.id_localidad");
            rs2.next();
          if(rs2.isFirst()){
              CargarDatos();
            System.out.println("No Vacio");
            empty = false;
        }else{
            System.out.println("Vacio");
        }   
        }catch (Exception e){
            e.printStackTrace();
        }
    }
        void CargarConsultaMod(){
        try{
            rs2 = sentencia.executeQuery("SELECT * FROM pais.localidad WHERE (`id_localidad` = '"+Integer.parseInt(txtId.getText())+"');");
            rs2.next();
          if(rs2.isFirst()){
            txtId.setText(rs2.getString("id_localidad"));
            txtNombre.setText(rs2.getString("nombre_localidad"));
            txtCodigoPostal.setText(rs2.getString("cod_postal"));
            cboProvincia.setSelectedIndex(rs2.getInt("provincia_id"));
            System.out.println("No Vacio");
            empty = false;
        }else{
            System.out.println("Vacio");
        }   
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void CargarDatos(){
//        Refrescar();
        int i = 0, in = 0;
        try{
            txtId.setText(rs2.getString("id_localidad"));
            txtNombre.setText(rs2.getString("nombre_localidad"));
            txtCodigoPostal.setText(rs2.getString("cod_postal"));
            in = rs2.getInt("provincia_id");
            while (in != A[i]){
                i++;
            }
            cboProvincia.setSelectedIndex(i);
        }catch(Exception e){
            e.printStackTrace();
//            try{
//                rs2.getString("provincia.nombre");
//            }catch(Exception e1){
//                btnAlta.setEnabled(false);
//            }
            btnBaja.setEnabled(false);
            btnModificar.setEnabled(false);
            btnSiguiente.setEnabled(false);
            btnUltimo.setEnabled(false);
            btnAnterior.setEnabled(false);
            btnPrimero.setEnabled(false);
            
        }
    }
    void Activar(){
        txtNombre.setEnabled(false);
        txtCodigoPostal.setEnabled(false);
        cboProvincia.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnAceptar.setEnabled(false);
        btnBaja.setEnabled(true);
        btnModificar.setEnabled(true);
        btnConsulta.setEnabled(true);
        btnSiguiente.setEnabled(true);
        btnUltimo.setEnabled(true);
        btnAnterior.setEnabled(true);
        btnPrimero.setEnabled(true);
    }
    void Desactivar(){
        txtNombre.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnAceptar.setEnabled(true);
        txtCodigoPostal.setEnabled(true);
        cboProvincia.setEnabled(true);
        btnBaja.setEnabled(false);
        btnModificar.setEnabled(false);
        btnConsulta.setEnabled(false);
        btnSiguiente.setEnabled(false);
        btnUltimo.setEnabled(false);
        btnAnterior.setEnabled(false);
        btnPrimero.setEnabled(false);
    }
    void Alta(){
        btnAlta.setEnabled(true);
    }
    /**
     * Creates new form Localidad
     */
    public Localidad() {
        initComponents();
        PrepararBaseDeDatos();
        CargarConsulta();
        CargarDatos();
        System.out.println(empty);
        if(empty){
            txtNombre.setEnabled(false);
            cboProvincia.setEnabled(false);
            txtCodigoPostal.setEnabled(false);
            btnCancelar.setEnabled(false);
            btnAceptar.setEnabled(false);
            btnBaja.setEnabled(false);
            btnModificar.setEnabled(false);
            btnConsulta.setEnabled(false);
            btnSiguiente.setEnabled(false);
            btnUltimo.setEnabled(false);
            btnAnterior.setEnabled(false);
            btnPrimero.setEnabled(false);
        }else{
            txtNombre.setEnabled(false);
            btnCancelar.setEnabled(false);
            btnAceptar.setEnabled(false);
            txtCodigoPostal.setEnabled(false);
            cboProvincia.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCodigoPostal = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cboProvincia = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        btnProvincia = new javax.swing.JButton();
        btnAlta = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        btnUltimo = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnPrimero = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Codigo");

        jLabel2.setText("Nombre");

        txtId.setEnabled(false);

        jLabel3.setText("Codigo Postal");

        btnAceptar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Check2.png"))); // NOI18N
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Deny.png"))); // NOI18N
        btnCancelar.setActionCommand("X");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        cboProvincia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboProvinciaItemStateChanged(evt);
            }
        });
        cboProvincia.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cboProvinciaPopupMenuWillBecomeVisible(evt);
            }
        });
        cboProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProvinciaActionPerformed(evt);
            }
        });

        jLabel4.setText("Provincia");

        btnProvincia.setText("...");
        btnProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvinciaActionPerformed(evt);
            }
        });

        btnAlta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnAlta.setText("ALTA");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        btnBaja.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnBaja.setText("BAJA");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnModificar.setText("MOD");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnConsulta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnConsulta.setText("CONSULTA");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });

        btnUltimo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });

        btnSiguiente.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAnterior.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnPrimero.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cboProvincia, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtCodigoPostal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnProvincia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsulta))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnPrimero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUltimo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnProvincia))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUltimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBaja)
                    .addComponent(btnModificar)
                    .addComponent(btnConsulta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        int aa=0;
        if(estado == "Alta"){
            try{
                if(txtNombre.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Debe escribir un nombre");
                    txtNombre.requestFocus();
                }else if(txtCodigoPostal.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Debe escribir un codigo postal");
                    txtNombre.requestFocus();                    
                }else{
                    while (aa != cboProvincia.getSelectedIndex()){
                        aa++;
                    } 
                }
                if (!txtNombre.getText().isEmpty()){
                    System.out.println("INSERT INTO `pais`.`localidad` (`id_localidad`, `nombre_localidad`, `cod_postal`, `provincia_id`) VALUES ('"+Integer.parseInt(txtId.getText())+"', '"+txtNombre.getText()+"','"+txtCodigoPostal.getText()+"','"+A[cboProvincia.getSelectedIndex()]+"');");
                    //                sentencia = coneccion.createStatement();
                    sentencia.execute("INSERT INTO `pais`.`localidad` (`id_localidad`, `nombre_localidad`, `cod_postal`, `provincia_id`) VALUES ('"+Integer.parseInt(txtId.getText())+"', '"+txtNombre.getText()+"','"+txtCodigoPostal.getText()+"','"+A[cboProvincia.getSelectedIndex()]+"');");
                    CargarConsulta();
                    rs2.last();
                    CargarDatos();
                }else{
                    JOptionPane.showMessageDialog(null, "Debe escribir una provincia");
                    txtNombre.requestFocus();
                }
                //                sentencia = coneccion.createStatement();
                //                sentencia.execute("INSERT INTO `pais`.`provincia` (`id_provincia`, `nombre`) VALUES ('"+Integer.parseInt(txtId.getText())+"', '"+txtNombre.getText()+"');");
                //                CargarConsulta();
                //                rs.last();
                //                CargarDatos();
            } catch (SQLException e){
                e.printStackTrace();
                System.out.println("No funca el Alta");
            }

        }else{
            try{
                //                sentencia = coneccion.createStatement();
                //                int i = Integer.parseInt(txtId.getText());
                String t = txtNombre.getText();
                System.out.println("UPDATE `pais`.`localidad` SET `nombre_localidad` = '"+txtNombre.getText()+"', `cod_postal` = '"+txtCodigoPostal.getText()+"', `provincia_id` = '"+A[cboProvincia.getSelectedIndex()]+"' WHERE (`id_localidad` = '"+Integer.parseInt(txtId.getText())+"');");
                sentencia.execute("UPDATE `pais`.`localidad` SET `nombre_localidad` = '"+txtNombre.getText()+"', `cod_postal` = '"+txtCodigoPostal.getText()+"', `provincia_id` = '"+A[cboProvincia.getSelectedIndex()]+"' WHERE (`id_localidad` = '"+Integer.parseInt(txtId.getText())+"');");
                CargarConsulta();
                while (rs2.next()){
                    if(rs2.getString("nombre_localidad").equals(t)){
                        break;
                    }
                }
                if (rs2.getRow()==0){
                    rs2.absolute(1);
                }
                System.out.println(rs2.getRow());
                CargarDatos();
            } catch (SQLException e){
                e.printStackTrace();
                System.out.println("No funca el Mod");
            }
        }
        Activar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Activar();
        try{
            CargarDatos();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        Desactivar();
        txtNombre.setText("");
        txtCodigoPostal.setText("");
        cboProvincia.setSelectedIndex(0);
        boolean b = false;
        try{
            estado = "Alta";
            try{
                int row;
                int txt;
                rs2.first();
                row = rs2.getRow();
                txt = (rs2.getInt("id_localidad"));
                System.out.println(row);
                System.out.println(rs2.getString("nombre_localidad"));
                System.out.println(txt);
                //                sentencia.execute("UPDATE `pais`.`provincia` SET `id_provincia` = '"+row+"' WHERE (`id_provincia` = '"+txt+"');");
                //                rs = sentencia.executeQuery("SELECT * FROM pais.provincia");
                //                rs.absolute(row);
                while (rs2.next()){
                    if(row != txt){
                        System.out.println("Hay un lugar");
                        txtId.setText(Integer.toString(row));
                        b = true;
                        break;
                    }else{
                        row = rs2.getRow();
                        txt = (rs2.getInt("id_localidad"));
                        System.out.println(row);
                        System.out.println(rs2.getString("nombre_localidad"));
                        System.out.println(txt);
                    }
                    //                    row = rs.getRow();
                    //                    txt = (rs.getInt("id_provincia"));
                    //                    System.out.println(row);
                    //                    System.out.println(rs.getString("nombre"));
                    //                    System.out.println(txt);
                    //                    sentencia.execute("UPDATE `pais`.`provincia` SET `id_provincia` = '"+row+"' WHERE (`id_provincia` = '"+txt+"');");
                    //                    rs = sentencia.executeQuery("SELECT * FROM pais.provincia");
                    //                    rs.absolute(row);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            if (b == false){
                rs2.last();
                int a = rs2.getInt("id_localidad")+1;
                txtId.setText(Integer.toString(a));
            }
        }catch(Exception e){
            e.printStackTrace();
            txtId.setText("1");
        }
    }//GEN-LAST:event_btnAltaActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        int a;
        a = JOptionPane.showConfirmDialog(null, "Esta seguro que desea borrar el registro", "Atención", 0);
        if(a == 0){
            try{
                //                sentencia = coneccion.createStatement();
                sentencia.execute("DELETE FROM `pais`.`localidad` WHERE (`id_localidad` = '"+Integer.parseInt(txtId.getText())+"');");
                CargarConsulta();
                rs2.last();
                CargarDatos();
            } catch (SQLException e){
                e.printStackTrace();
            }
            //            try{
                //                int row;
                //                int txt;
                //                rs.first();
                //                row = rs.getRow();
                //                txt = (rs.getInt("id_provincia"));
                //                System.out.println(row);
                //                System.out.println(rs.getString("nombre"));
                //                System.out.println(txt);
                //                sentencia.execute("UPDATE `pais`.`provincia` SET `id_provincia` = '"+row+"' WHERE (`id_provincia` = '"+txt+"');");
                //                rs = sentencia.executeQuery("SELECT * FROM pais.provincia");
                //                rs.absolute(row);
                //                while (rs.next()){
                    //                    row = rs.getRow();
                    //                    txt = (rs.getInt("id_provincia"));
                    //                    System.out.println(row);
                    //                    System.out.println(rs.getString("nombre"));
                    //                    System.out.println(txt);
                    //                    sentencia.execute("UPDATE `pais`.`provincia` SET `id_provincia` = '"+row+"' WHERE (`id_provincia` = '"+txt+"');");
                    //                    rs = sentencia.executeQuery("SELECT * FROM pais.provincia");
                    //                    rs.absolute(row);
                    //                }
                //            }catch(Exception e){
                //                e.printStackTrace();
                //            }
        }
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Desactivar();
        try{
            estado = "Modificar";
            int a = rs2.getInt("id_localidad");
            txtId.setText(Integer.toString(a));
        }catch(Exception e){
            txtId.setText("1");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
//        CConsulta bp=new CConsulta();
//        bp.setVisible(true);
    }//GEN-LAST:event_btnConsultaActionPerformed

    private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        try{
            rs2.last();
//            txtId.setText(rs2.getString("id_localidad"));
//            txtNombre.setText(rs2.getString("nombre_localidad"));
//            txtCodigoPostal.setText(rs2.getString("cod_postal"));
//            cboProvincia.setSelectedIndex(rs2.getInt("provincia_id"));
            CargarDatos();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUltimoActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        try{
            if(rs2.isLast()){

            }else{
                rs2.next();
//                txtId.setText(rs2.getString("id_localidad"));
//                txtNombre.setText(rs2.getString("nombre_localidad"));
//                txtCodigoPostal.setText(rs2.getString("cod_postal"));
//                cboProvincia.setSelectedIndex(rs2.getInt("provincia_id"));
                CargarDatos();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        //        BasedeDatos bd=new BasedeDatos();
        //        try{
            //            rs.first();
            //            txtId.setText(rs.getString("id_provincia"));
            //            txtNombre.setText(rs.getString("nombre"));
            ////        rs = bd.ConectarDatos("SELECT * FROM provincia WHERE id_provincia = 2");
            ////        System.out.println(select);
            ////        System.out.println(cant);
            ////            IsEmpty();
            ////            txtId.setText(rs.getString("id_provincia"));
            ////            txtNombre.setText(rs.getString("nombre"));
            //
            //        }catch(Exception e){
            //
            //        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        try{
            if(rs2.isFirst()){

            }else{
                rs2.previous();
//                txtId.setText(rs2.getString("id_localidad"));
//                txtNombre.setText(rs2.getString("nombre_localidad"));
//                txtCodigoPostal.setText(rs2.getString("cod_postal"));
//                cboProvincia.setSelectedIndex(rs2.getInt("provincia_id"));
                CargarDatos();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //        ResultSet rs=null;
        //        BasedeDatos bd=new BasedeDatos();
        //        try{
            //        rs = bd.ConectarDatos("SELECT * FROM provincia WHERE id_provincia = 2");
            //        System.out.println(select);
            //        System.out.println(cant);
            //        select --;
            //        if (select <= 0){
                //            select += 1;
                //        }
            //            IsEmpty();
            ////            txtId.setText(rs.getString("id_provincia"));
            ////            txtNombre.setText(rs.getString("nombre"));
            //        }catch(Exception e){
            //
            //        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        try{
            rs2.first();
//            txtId.setText(rs2.getString("id_localidad"));
//            txtNombre.setText(rs2.getString("nombre_localidad"));
//            txtCodigoPostal.setText(rs2.getString("cod_postal"));
//            cboProvincia.setSelectedIndex(rs2.getInt("provincia_id"));
            CargarDatos();
        }catch(Exception e){
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrimeroActionPerformed

    private void btnProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvinciaActionPerformed
        Provincia1 bp=new Provincia1();
        bp.setVisible(true);
    }//GEN-LAST:event_btnProvinciaActionPerformed

    private void cboProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProvinciaActionPerformed
//        CargarConsulta();
//        System.out.println("Aprete el combo box");
    }//GEN-LAST:event_cboProvinciaActionPerformed

    private void cboProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboProvinciaItemStateChanged
//        CargarConsulta();
//        System.out.println("Aprete el combo box");
    }//GEN-LAST:event_cboProvinciaItemStateChanged

    private void cboProvinciaPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboProvinciaPopupMenuWillBecomeVisible
        Refrescar();
    }//GEN-LAST:event_cboProvinciaPopupMenuWillBecomeVisible

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Localidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Localidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Localidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Localidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Localidad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnPrimero;
    private javax.swing.JButton btnProvincia;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnUltimo;
    private javax.swing.JComboBox cboProvincia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtCodigoPostal;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
