
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sugandha
 */
public class createNew extends javax.swing.JInternalFrame {

    /**
     * Creates new form createNew
     */
    String username;
    Connection x;
    public createNew(String username,Connection x) {
     this.username=username;
     this.x=x;
        initComponents();
        
             showDetails();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtlname = new javax.swing.JTextField();
        txtldetails = new javax.swing.JTextField();
        btnEntry = new javax.swing.JButton();
        btnHead = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtll = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtsearch = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ledger Code");

        jPanel4.setBackground(new java.awt.Color(1, 50, 67));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        jPanel3.setBackground(new java.awt.Color(240, 52, 52));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ledger Head");

        jPanel1.setBackground(new java.awt.Color(1, 50, 67));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ledger Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ledger Details");

        btnEntry.setBackground(new java.awt.Color(231, 76, 60));
        btnEntry.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEntry.setForeground(new java.awt.Color(255, 255, 255));
        btnEntry.setText("Create");
        btnEntry.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntryActionPerformed(evt);
            }
        });

        btnHead.setBackground(new java.awt.Color(0, 181, 240));
        btnHead.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHead.setForeground(new java.awt.Color(255, 255, 255));
        btnHead.setText("Update");
        btnHead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHeadActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(231, 76, 60));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Delete");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ledger Limit");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEntry)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnHead)
                        .addGap(34, 34, 34)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtlname, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(txtldetails)
                            .addComponent(txtll))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtlname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtldetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHead, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Income Head", jPanel3);

        jPanel2.setBackground(new java.awt.Color(240, 52, 52));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 67, 81));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Search Name");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("View Ledgers", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntryActionPerformed
        // TODO add your handling code here:
        String l_name=txtlname.getText();     
    String l_details=txtldetails.getText();
    String type="Income";
    String limit=txtll.getText();
     try
        {
       
                String q="INSERT INTO `accountHead`(`l_name`,`l_details`, `username`,`type`,l_limit) VALUES (?,?,?,?,?)";
                PreparedStatement y=x.prepareStatement(q);
                y.setString(1,l_name);
              y.setString(2,l_details);
              y.setString(3,username);
              y.setString(4,type);
              y.setString(5,limit);
             
               int n=y.executeUpdate();
                String st=null;
                if(n>0)
                   st="Income ledger head created";
             y.close();
             
              
             JOptionPane.showMessageDialog(this,st);
             //this.dispose();
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
     showDetails();
     
    txtlname.setText("");     
    txtldetails.setText("");
    txtll.setText("");
    }//GEN-LAST:event_btnEntryActionPerformed
    
    
    private void btnHeadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHeadActionPerformed
        // TODO add your handling code here:
       String l_name=txtlname.getText();
        String l_details=txtldetails.getText();
        String type="Income";
        String limit=txtll.getText();
        try
        {

            String q="UPDATE `accountHead` SET `l_details`=?, l_limit=? WHERE `username`=? and `type`=? and `l_name`=?";
            PreparedStatement y=x.prepareStatement(q);
            y.setString(1,l_details);
            y.setString(2,limit);
            y.setString(3,username);
            y.setString(4,type);
            y.setString(5,l_name);
            int n=y.executeUpdate();
                String st=null;
                if(n>0)
                   st="Income ledger head updated";
             y.close();
             
              
             JOptionPane.showMessageDialog(this,st);
             //this.dispose();
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this,"Ledger head name cannot be modified");
        }
        showDetails();
    
    }//GEN-LAST:event_btnHeadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         String l_name=txtlname.getText();
        String l_details=txtldetails.getText();
        String type="Income";
        
        //String pass="";
        try
        {

            String q="DELETE FROM `accountHead` WHERE l_name=? and l_details=? and username=? and type=?";
            PreparedStatement y=x.prepareStatement(q);
            y.setString(1,l_name);
            y.setString(2,l_details);
            y.setString(3,username);
            y.setString(4, type);

           int n=y.executeUpdate();
                String st=null;
                if(n>0)
                   st="Income ledger head deleted";
             y.close();
             
             JOptionPane.showMessageDialog(this,st);
            // this.dispose();
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this,"Ledger head can not be deleted because it contains entries");
        }
        showDetails();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row=jTable1.getSelectedRow();
        TableModel k1=jTable1.getModel();
        String a1=(String)k1.getValueAt(row, 1);
        String a2=(String)k1.getValueAt(row, 2);
        String a3=(String)k1.getValueAt(row, 3);
//       jTabbedPane1.setSelectedIndex(jTabbedPane1.getTabCount()-1);
       jTabbedPane1.setSelectedIndex(0);
        txtlname.setText(a1);
        txtldetails.setText(a2);
        txtll.setText(a3);
        
//        JOptionPane.showMessageDialog(this,"Open ledger head tab to edit");
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Vector h=new Vector();
            h.add("Code");
            h.add("Name");
            h.add("Details");
            h.add("Limit");
            String search=txtsearch.getText();
            
            String type="Expense";
             
            String q = "select * from accountHead where username=? and type=? and l_name=?";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,search);
                    
            ResultSet rs = y.executeQuery();
            Vector d=new Vector(); 
            
            String a1,a2,a3,a4;
            while (rs.next()) {
                a1 = rs.getString("l_code");
                a2 = rs.getString("l_name");
                a3 = rs.getString("l_details");
                a4=rs.getString("l_limit");
                
                Vector r=new Vector();
                r.add(a1);
                r.add(a2);
                r.add(a3);
                r.add(a4);
               
                //inserted into data
                d.add(r);
            }
            y.close();
            
            
            DefaultTableModel z= new DefaultTableModel(d,h);
            jTable1.setModel(z);
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"unable to find ledger head");
            showDetails();
        }
        txtsearch.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

void showDetails() {

        try {
            
            Vector h=new Vector();
            h.add("Code");
            h.add("Name");
            h.add("Details");
           h.add("Limit");
            String type="Income";
             
            String q = "select * from accountHead where username=? and type=? order by l_code";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            ResultSet rs = y.executeQuery();
            
            Vector d=new Vector(); 
            
            String a1,a2,a3,a4;
            while (rs.next()) {
                a1 = rs.getString("l_code");
                a2 = rs.getString("l_name");
                a3 = rs.getString("l_details");
                a4 = rs.getString("l_limit");
                
                Vector r=new Vector();
                r.add(a1);
                r.add(a2);
                r.add(a3);
                r.add(a4);
               
                //inserted into data
                d.add(r);
            }
            y.close();
            
            
            DefaultTableModel z= new DefaultTableModel(d,h);
            jTable1.setModel(z);
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
            ex.printStackTrace();
        }
     }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntry;
    private javax.swing.JButton btnHead;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtldetails;
    private javax.swing.JTextField txtll;
    private javax.swing.JTextField txtlname;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
