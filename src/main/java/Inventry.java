
import java.awt.event.KeyEvent;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asken
 */
public class Inventry extends javax.swing.JFrame {

    /**
     * Creates new form Inventory
     */
    public Inventry() {
        initComponents();
    }
  String pnn;
  String newpn;
  String id;
  String idd;
    
    public Inventry(String pn, String user) {
        initComponents();
        this.pnn=pn;
        this.id=user;
        newpn=pnn;
        idd=id;
        
        pnid.setText(pn);
        LoadItem();
    }
    

    public class Item{
        String id;
        String name;
        public Item(String id, String name){
            this.id=id;
            this.name=name;
     
    }
        public String toString(){
            return id;
        }
    }
    Connection con;
    PreparedStatement pst;
    public void LoadItem(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            pst= con.prepareStatement("select * from items");
            ResultSet rs = pst.executeQuery();
            tid.removeAll();
            
            while(rs.next()){
               tid.addItem(new Item(rs.getString(1), rs.getString(2)));
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Inventry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Inventry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ResultSet rs;
    public void sales(){
        DateTimeFormatter daf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = daf.format(now);
        
        String Total_Cost = tc.getText();
        String Amount_Paid = ap.getText();
        String Balance = b.getText();
        
        int lastinsertedid = 0;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            String sql =("insert into sales ( log_id,Date, Subtotal, Amount_Paid, Balance) values(?,?,?,?,?)");
            pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, idd);
            pst.setString(2, date);
            pst.setString(3, Total_Cost);
            pst.setString(4, Amount_Paid);
            pst.setString(5, Balance);
            pst.executeUpdate();
            
             rs = pst.getGeneratedKeys();
             if(rs.next()){
                 lastinsertedid= rs.getInt(1);
             }
            int rows = jTable1.getColumnCount();
            String sql1 ="insert into sales_product (log_id, sales_id, product_id, SellPrice, Quantity , Total )values(?,?,?,?,?,?)";
            pst= con.prepareStatement(sql1);
            String Prescription_id;
            String Item_Id;
            int Price;
            String Quantity;
            int Total;
            
            for(int i=0; i<jTable1.getRowCount(); i++){
                Prescription_id = (String)jTable1.getValueAt(i, 0);
                Item_Id = (String)jTable1.getValueAt(i, 1);
                Price = (int)jTable1.getValueAt(i, 3);
                
                Quantity =jTable1.getValueAt(i, 4).toString();
                int qty = Integer.parseInt(Quantity);
                
                Total = (int)jTable1.getValueAt(i, 5);
                
               pst.setString(1, idd);
               pst.setInt(2, lastinsertedid);
                pst.setString(3, Item_Id);
                pst.setInt(4, Price);
                pst.setInt(5, qty);
                pst.setInt(6, Total);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(rootPane, "Record added");
                tc.setText("");
                ap.setText("");
                 b.setText("");
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Inventry.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnid = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        tid = new javax.swing.JComboBox();
        in = new javax.swing.JTextField();
        qa = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tc = new javax.swing.JTextField();
        ap = new javax.swing.JTextField();
        b = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(java.awt.Color.orange);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setForeground(java.awt.Color.green);
        jLabel6.setText("Prescription_No");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setForeground(java.awt.Color.green);
        jLabel7.setText("Item_Id");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setForeground(java.awt.Color.green);
        jLabel8.setText("Item_Name");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setForeground(java.awt.Color.green);
        jLabel9.setText("Quantity");

        pnid.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        pnid.setForeground(java.awt.Color.red);
        pnid.setText("jLabel5");

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tid.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        tid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tidKeyPressed(evt);
            }
        });

        in.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        qa.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(java.awt.Color.green);
        jLabel4.setText("Total_Cost");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setForeground(java.awt.Color.green);
        jLabel5.setText("Amount_Paid");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setForeground(java.awt.Color.green);
        jLabel10.setText("Balance");

        tc.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        tc.setForeground(java.awt.Color.red);

        ap.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        b.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton2.setText("Sales_Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pnid, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tid, 0, 284, Short.MAX_VALUE)
                        .addComponent(in)
                        .addComponent(qa, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(177, 177, 177))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jButton2)
                                .addGap(0, 185, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tc, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(ap)
                    .addComponent(b)
                    .addComponent(jButton3))
                .addGap(342, 342, 342))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(ap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(119, 119, 119)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(qa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(76, 76, 76))
        );

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prescription_Id", "Item_Id", "Item_Name", "Price", "Quantity", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tidKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                String Item_Id = tid.getSelectedItem().toString();
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
                 pst= con.prepareStatement("select * from items where Item_Id=?");
                  pst.setString(1, Item_Id);
                 ResultSet rs = pst.executeQuery();
                if(rs.next()==false){
                   JOptionPane.showMessageDialog(rootPane, "Item not found");
                    
                }
                else{
                    String Item_Name=rs.getString("Item_Name");
                    in.setText(Item_Name.trim());
                    qa.requestFocus();
     
                     in.setEditable(false);
                }
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Inventry.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Inventry.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_tidKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String Item_Id = tid.getSelectedItem().toString();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            pst= con.prepareStatement("select * from items where Item_Id=?");
            pst.setString(1, Item_Id);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                int currentqty;
                int  sellprice;
                int qty;
                
                currentqty= rs.getInt("Quantity");
                sellprice= rs.getInt("SellPrice");
                
                qty= Integer.parseInt(qa.getValue().toString());
                
                int total = sellprice * qty;
                
                if(qty>=currentqty){
                    JOptionPane.showMessageDialog(rootPane, "This Item is not sufficient");
                }
                else{
                    DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
                    df.addRow(new Object[]
                    {
                        pnid.getText(),
                        tid.getSelectedItem().toString(),
                        in.getText(),
                        sellprice,
                        qa.getValue().toString(),
                        total
                    
                    });
                    
                    int sum=0;
                    
                    for(int i=0; i<jTable1.getRowCount(); i++)
                    {
                        sum= sum + Integer.parseInt(jTable1.getValueAt(i, 5).toString());
                    }
                    
                    tc.setText(Integer.toString(sum));
                    
                    tid.setSelectedIndex(-1);
                    in.setText("");
                    qa.setValue(0);
                    
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            int Amount_Paid = Integer.parseInt(ap.getText()) ;  
            int Total_Cost = Integer.parseInt(tc.getText()) ;  
            
            int Balance = Amount_Paid - Total_Cost;
            
            if(Amount_Paid<Total_Cost){
                JOptionPane.showMessageDialog(rootPane, "Insufficient Fund, Please Recharge your Account");
            }
            else{
               
            b.setText(String.valueOf(Balance));
            
             sales();
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);       // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Inventry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() { 
            public void run() {
                new Inventry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ap;
    private javax.swing.JTextField b;
    private javax.swing.JTextField in;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel pnid;
    private javax.swing.JSpinner qa;
    private javax.swing.JTextField tc;
    private javax.swing.JComboBox tid;
    // End of variables declaration//GEN-END:variables
}
