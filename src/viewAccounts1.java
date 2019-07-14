import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sugandha
 */
public class viewAccounts1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form viewAccounts
     */
    String username;
    Connection x;
    public viewAccounts1(String username,Connection x) {
        initComponents();
        this.x=x;
        this.username=username;
        showData();
        showExpense();
       
        for(int i=1972; i<=Calendar.getInstance().get(Calendar.YEAR);i++)
        {
            Comboy1.addItem(String.valueOf(i));
           Comboy2.addItem(String.valueOf(i));
           Comboy3.addItem(String.valueOf(i));
        }
       
        
    }
     void showData() {

        try {
            
            Vector h=new Vector();
           
            h.add("Entry");
            h.add("Name");
             h.add("Amount");
             h.add("Details");
            h.add("Date");
          String type="Income";
           
            
             
            String q = "select headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and accountHead.type=?)";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            ResultSet rs = y.executeQuery();
            
            Vector d=new Vector(); 
            
            String a1,a2,a3,a4,a5;
            while (rs.next()) {
                a1 = rs.getString("entry_id");
                a2 = rs.getString("l_name");
                a3 = rs.getString("amount");
                a4=rs.getString("details");
                 a5=rs.getString("date");
                
                Vector r=new Vector();
                r.add(a1);
                r.add(a2);
                r.add(a3);
                r.add(a4);
                r.add(a5);
               
                //inserted into data
                d.add(r);
            }
            y.close();
            
            
            DefaultTableModel z= new DefaultTableModel(d,h);
            jTable1.setModel(z);
            tabInMont.setModel(z);
             jTable5.setModel(z);
            jTable7.setModel(z);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
            ex.printStackTrace();
        }
     }
     void showExpense() {

        try {
            
            Vector h=new Vector();
           
            h.add("Entry");
            h.add("Name");
             h.add("Amount");
             h.add("Details");
            h.add("Date");
          String type="Expense";
           
            
             
            String q = "select headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and accountHead.type=?)";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            ResultSet rs = y.executeQuery();
            
            Vector d=new Vector(); 
            
            String a1,a2,a3,a4,a5;
            while (rs.next()) {
                a1 = rs.getString("entry_id");
                a2 = rs.getString("l_name");
                a3 = rs.getString("amount");
                a4=rs.getString("details");
                 a5=rs.getString("date");
                
                Vector r=new Vector();
                r.add(a1);
                r.add(a2);
                r.add(a3);
                r.add(a4);
                r.add(a5);
               
                //inserted into data
                d.add(r);
            }
            y.close();
            
            
            DefaultTableModel z= new DefaultTableModel(d,h);
            jTable2.setModel(z);
            tabExMont.setModel(z);
            jTable6.setModel(z);
            jTable8.setModel(z);
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
            ex.printStackTrace();
        }
     }
     void totalIncome()throws Exception
     {
         SimpleDateFormat d=new SimpleDateFormat("dd-MM-yyyy"); 
    String d1=d.format(date1.getDate());
    String d2=d.format(date2.getDate());
    String type="Income";
     String q = "select amount from headEntry where username=? and type=? and str_to_date(date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\")";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            
            ResultSet rs = y.executeQuery();
            
            
            
            Double a1,sum=0.0;
            while (rs.next()) {
               
                a1 = Double.valueOf(rs.getString("amount"));
               sum+=a1;
            }
            txtIncome.setText(String.valueOf(sum));
                
                
     }
void totalExMon()throws Exception
     {
         String mon=String.valueOf(ComboMon.getSelectedItem());
    String yr=String.valueOf(Comboy3.getSelectedItem());
    String d1="01-"+mon+"-"+yr;
    String d2="31-"+mon+"-"+yr;
         
    String type="Expense";
     String q = "select amount from headEntry where username=? and type=? and str_to_date(date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\")";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            
            ResultSet rs = y.executeQuery();
            
            
            
            Double a1,sum=0.0;
            while (rs.next()) {
               
                a1 = Double.valueOf(rs.getString("amount"));
               sum+=a1;
            }
            txtmonEx.setText(String.valueOf(sum));
                
                
     
     }
void totalInMon()throws Exception
     {
         String mon=String.valueOf(ComboMon.getSelectedItem());
    String yr=String.valueOf(Comboy3.getSelectedItem());
    String d1="01-"+mon+"-"+yr;
    String d2="31-"+mon+"-"+yr;
         
    String type="Income";
     String q = "select amount from headEntry where username=? and type=? and str_to_date(date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\")";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            
            ResultSet rs = y.executeQuery();
            
            
            
            Double a1,sum=0.0;
            while (rs.next()) {
               
                a1 = Double.valueOf(rs.getString("amount"));
               sum+=a1;
            }
            txtmonIn.setText(String.valueOf(sum));
                
                
     
     }
void totalExQuat()throws Exception
     {
                  String m="";
    int mon=comboQua.getSelectedIndex();
    if(mon==1)
        m="01";
    else if(mon==2)
        m="04";
    else if(mon==3)
        m="07";
    else if(mon==4)
        m="10";
    String u=String.valueOf(Integer.parseInt(m)+2);
    String yr=String.valueOf(Comboy2.getSelectedItem());
    String d1="01-"+m+"-"+yr;
    String d2="31-"+u+"-"+yr;
         
    String type="Expense";
     String q = "select amount from headEntry where username=? and type=? and str_to_date(date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\")";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            
            ResultSet rs = y.executeQuery();
            
            
            
            Double a1,sum=0.0;
            while (rs.next()) {
               
                a1 = Double.valueOf(rs.getString("amount"));
               sum+=a1;
            }
            txtquatEx.setText(String.valueOf(sum));
                
                
     
     }
void totalInQuat()throws Exception
     {
                  String m="";
    int mon=comboQua.getSelectedIndex();
    
   System.out.println(mon+"MON");
    if(mon==1)
        m="01";
    else if(mon==2)
        m="04";
    else if(mon==3)
        m="07";
    else if(mon==4)
        m="10";
    String u=String.valueOf(Integer.parseInt(m)+2);
    String yr=String.valueOf(Comboy2.getSelectedItem());
    String d1="01-"+m+"-"+yr;
    String d2="31-"+u+"-"+yr;
         
    
    String type="Income";
     String q = "select amount from headEntry where username=? and type=? and str_to_date(date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\")";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            
            ResultSet rs = y.executeQuery();
            
            
            
            Double a1,sum=0.0;
            while (rs.next()) {
               
                a1 = Double.valueOf(rs.getString("amount"));
               sum+=a1;
            }
            txtquatIn.setText(String.valueOf(sum));
                
                
     
     }
void totalExYr()throws Exception
     {
         String yr=String.valueOf(Comboy1.getSelectedItem());
    String d1="01-01-"+yr;
    String d2="31-12-"+yr;
    
         
    String type="Expense";
     String q = "select amount from headEntry where username=? and type=? and str_to_date(date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\")";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            
            ResultSet rs = y.executeQuery();
            
            
            
            Double a1,sum=0.0;
            while (rs.next()) {
               
                a1 = Double.valueOf(rs.getString("amount"));
               sum+=a1;
            }
            txtyrEx.setText(String.valueOf(sum));
                
                
     
     }
void totalInYr()throws Exception
     {
         String yr=String.valueOf(Comboy1.getSelectedItem());
    String d1="01-01-"+yr;
    String d2="31-12-"+yr;
    
         
    String type="Income";
     String q = "select amount from headEntry where username=? and type=? and str_to_date(date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\")";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            
            ResultSet rs = y.executeQuery();
            
            
            
            Double a1,sum=0.0;
            while (rs.next()) {
               
                a1 = Double.valueOf(rs.getString("amount"));
               sum+=a1;
            }
            txtyrIn.setText(String.valueOf(sum));
                
                
     
     }



void totalExpense()throws Exception
     {
         SimpleDateFormat d=new SimpleDateFormat("dd-MM-yyyy"); 
    String d1=d.format(date1.getDate());
    String d2=d.format(date2.getDate());
    String type="Expense";
     String q = "select amount from headEntry where username=? and type=? and str_to_date(date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\")";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            
            ResultSet rs = y.executeQuery();
            
            
            
            Double a1,sum=0.0;
            while (rs.next()) {
               
                a1 = Double.valueOf(rs.getString("amount"));
               sum+=a1;
            }
            txtExpense.setText(String.valueOf(sum));
                
                
     }
void showQuartIn() throws Exception
{
    String type="Income";
    Vector h=new Vector();
           
            h.add("Entry");
            h.add("Name");
             h.add("Amount");
             h.add("Details");
            h.add("Date");
            String m="";
    int mon=comboQua.getSelectedIndex();
    if(mon==1)
        m="01";
    else if(mon==2)
        m="04";
    else if(mon==3)
        m="07";
    else if(mon==4)
        m="10";
    String u=String.valueOf(Integer.parseInt(m)+2);
    String yr=String.valueOf(Comboy2.getSelectedItem());
    String d1="01-"+m+"-"+yr;
    String d2="31-"+u+"-"+yr;
    
    String q = "select headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date,str_to_date(headEntry.date,'%d-%m-%Y')  from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and accountHead.type=? and str_to_date(headEntry.date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\"))";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            System.out.println(y);
            
            ResultSet rs = y.executeQuery();
            
             
            Vector d=new Vector(); 
            
            String a1,a2,a3,a4,a5;
            while (rs.next()) {
                a1 = rs.getString("entry_id");
                a2 = rs.getString("l_name");
                a3 = rs.getString("amount");
                a4=rs.getString("details");
                 a5=rs.getString("date");
                
                Vector r=new Vector();
                r.add(a1);
                r.add(a2);
                r.add(a3);
                r.add(a4);
                r.add(a5);
               
                //inserted into data
                d.add(r);
            }
            y.close();
             DefaultTableModel z= new DefaultTableModel(d,h);
            jTable5.setModel(z);
}
void showQuartEx() throws Exception
{
    String type="Expense";
    Vector h=new Vector();
           
            h.add("Entry");
            h.add("Name");
             h.add("Amount");
             h.add("Details");
            h.add("Date");
            String m="";
    int mon=comboQua.getSelectedIndex();
    if(mon==1)
        m="01";
    else if(mon==2)
        m="04";
    else if(mon==3)
        m="07";
     else if(mon==4)
        m="10";
    String u=String.valueOf(Integer.parseInt(m)+2);
   
    String yr=String.valueOf(Comboy2.getSelectedItem());
    String d1="01-"+m+"-"+yr;
    String d2="31-"+u+"-"+yr;
    
    String q = "select headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date,str_to_date(headEntry.date,'%d-%m-%Y')  from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and accountHead.type=? and str_to_date(headEntry.date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\"))";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            System.out.println(y);
            
            ResultSet rs = y.executeQuery();
            
             
            Vector d=new Vector(); 
            
            String a1,a2,a3,a4,a5;
            while (rs.next()) {
                a1 = rs.getString("entry_id");
                a2 = rs.getString("l_name");
                a3 = rs.getString("amount");
                a4=rs.getString("details");
                 a5=rs.getString("date");
                
                Vector r=new Vector();
                r.add(a1);
                r.add(a2);
                r.add(a3);
                r.add(a4);
                r.add(a5);
               
                //inserted into data
                d.add(r);
            }
            y.close();
             DefaultTableModel z= new DefaultTableModel(d,h);
            jTable6.setModel(z);
}
            void showMonthlyEx() throws Exception
{
    String type="Expense";
    Vector h=new Vector();
           
            h.add("Entry");
            h.add("Name");
             h.add("Amount");
             h.add("Details");
            h.add("Date");
    String mon=String.valueOf(ComboMon.getSelectedItem());
    String yr=String.valueOf(Comboy3.getSelectedItem());
    String d1="01-"+mon+"-"+yr;
    String d2="31-"+mon+"-"+yr;
    
    String q = "select headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date,str_to_date(headEntry.date,\"%d-%m-%Y\")  from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and accountHead.type=? and str_to_date(headEntry.date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\"))";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            ResultSet rs = y.executeQuery();
            
             
            Vector d=new Vector(); 
            
            String a1,a2,a3,a4,a5;
            while (rs.next()) {
                a1 = rs.getString("entry_id");
                a2 = rs.getString("l_name");
                a3 = rs.getString("amount");
                a4=rs.getString("details");
                 a5=rs.getString("date");
                
                Vector r=new Vector();
                r.add(a1);
                r.add(a2);
                r.add(a3);
                r.add(a4);
                r.add(a5);
               
                //inserted into data
                d.add(r);
            }
            y.close();
             DefaultTableModel z= new DefaultTableModel(d,h);
            tabExMont.setModel(z);
}
void showDateEx() throws Exception
{
     SimpleDateFormat d=new SimpleDateFormat("dd-MM-yyyy"); 
    String d1=d.format(date1.getDate());
    String d2=d.format(date2.getDate());
    String type="Expense";
    Vector h=new Vector();
           
            h.add("Entry");
            h.add("Name");
             h.add("Amount");
             h.add("Details");
            h.add("Date");
    
    
    String q = "select headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date,str_to_date(headEntry.date,\"%d-%m-%Y\")  from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and accountHead.type=? and str_to_date(headEntry.date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\"))";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            ResultSet rs = y.executeQuery();
            
             
            Vector e=new Vector(); 
            
            String a1,a2,a3,a4,a5;
            while (rs.next()) {
                a1 = rs.getString("entry_id");
                a2 = rs.getString("l_name");
                a3 = rs.getString("amount");
                a4=rs.getString("details");
                 a5=rs.getString("date");
                
                Vector r=new Vector();
                r.add(a1);
                r.add(a2);
                r.add(a3);
                r.add(a4);
                r.add(a5);
               
                //inserted into data
                e.add(r);
            }
            y.close();
             DefaultTableModel z= new DefaultTableModel(e,h);
            jTable2.setModel(z);
}
void showDateIn() throws Exception
{
     SimpleDateFormat d=new SimpleDateFormat("dd-MM-yyyy"); 
    String d1=d.format(date1.getDate());
    String d2=d.format(date2.getDate());
    String type="Income";
    Vector h=new Vector();
           
            h.add("Entry");
            h.add("Name");
             h.add("Amount");
             h.add("Details");
            h.add("Date");
    
    
    String q = "select headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date,str_to_date(headEntry.date,\"%d-%m-%Y\")  from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and accountHead.type=? and str_to_date(headEntry.date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\"))";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            ResultSet rs = y.executeQuery();
            
             
            Vector e=new Vector(); 
            
            String a1,a2,a3,a4,a5;
            while (rs.next()) {
                a1 = rs.getString("entry_id");
                a2 = rs.getString("l_name");
                a3 = rs.getString("amount");
                a4=rs.getString("details");
                 a5=rs.getString("date");
                
                Vector r=new Vector();
                r.add(a1);
                r.add(a2);
                r.add(a3);
                r.add(a4);
                r.add(a5);
               
                //inserted into data
                e.add(r);
            }
            y.close();
             DefaultTableModel z= new DefaultTableModel(e,h);
            jTable1.setModel(z);
}
void showMonthlyIn() throws Exception
{
    String type="Income";
    Vector h=new Vector();
           
            h.add("Entry");
            h.add("Name");
             h.add("Amount");
             h.add("Details");
            h.add("Date");
    String mon=String.valueOf(ComboMon.getSelectedItem());
    String yr=String.valueOf(Comboy3.getSelectedItem());
    String d1="01-"+mon+"-"+yr;
    String d2="31-"+mon+"-"+yr;
    
    String q = "select headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date,str_to_date(headEntry.date,'%d-%m-%Y')  from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and accountHead.type=? and str_to_date(headEntry.date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\"))";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            System.out.println(y);
            
            ResultSet rs = y.executeQuery();
            
             
            Vector d=new Vector(); 
            
            String a1,a2,a3,a4,a5;
            while (rs.next()) {
                a1 = rs.getString("entry_id");
                a2 = rs.getString("l_name");
                a3 = rs.getString("amount");
                a4=rs.getString("details");
                 a5=rs.getString("date");
                
                Vector r=new Vector();
                r.add(a1);
                r.add(a2);
                r.add(a3);
                r.add(a4);
                r.add(a5);
               
                //inserted into data
                d.add(r);
            }
            y.close();
             DefaultTableModel z= new DefaultTableModel(d,h);
            tabInMont.setModel(z);
}
void showYearIn() throws Exception
{
    String type="Income";
    Vector h=new Vector();
           
            h.add("Entry");
            h.add("Name");
             h.add("Amount");
             h.add("Details");
            h.add("Date");
           
    
    String yr=String.valueOf(Comboy1.getSelectedItem());
    String d1="01-01-"+yr;
    String d2="31-12-"+yr;
    
    String q = "select headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date,str_to_date(headEntry.date,'%d-%m-%Y')  from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and accountHead.type=? and str_to_date(headEntry.date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\"))";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            System.out.println(y);
            
            ResultSet rs = y.executeQuery();
            
             
            Vector d=new Vector(); 
            
            String a1,a2,a3,a4,a5;
            while (rs.next()) {
                a1 = rs.getString("entry_id");
                a2 = rs.getString("l_name");
                a3 = rs.getString("amount");
                a4=rs.getString("details");
                 a5=rs.getString("date");
                
                Vector r=new Vector();
                r.add(a1);
                r.add(a2);
                r.add(a3);
                r.add(a4);
                r.add(a5);
               
                //inserted into data
                d.add(r);
            }
            y.close();
             DefaultTableModel z= new DefaultTableModel(d,h);
            jTable7.setModel(z);
}
void showYearEx() throws Exception
{
    String type="Expense";
    Vector h=new Vector();
           
            h.add("Entry");
            h.add("Name");
             h.add("Amount");
             h.add("Details");
            h.add("Date");
           
    
    String yr=String.valueOf(Comboy1.getSelectedItem());
    String d1="01-01-"+yr;
    String d2="31-12-"+yr;
    
    String q = "select headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date,str_to_date(headEntry.date,'%d-%m-%Y')  from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and accountHead.type=? and str_to_date(headEntry.date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\"))";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,type);
            y.setString(3,d1);
            y.setString(4,d2);
            System.out.println(y);
            
            ResultSet rs = y.executeQuery();
            
             
            Vector d=new Vector(); 
            
            String a1,a2,a3,a4,a5;
            while (rs.next()) {
                a1 = rs.getString("entry_id");
                a2 = rs.getString("l_name");
                a3 = rs.getString("amount");
                a4=rs.getString("details");
                 a5=rs.getString("date");
                
                Vector r=new Vector();
                r.add(a1);
                r.add(a2);
                r.add(a3);
                r.add(a4);
                r.add(a5);
               
                //inserted into data
                d.add(r);
            }
            y.close();
             DefaultTableModel z= new DefaultTableModel(d,h);
            jTable8.setModel(z);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        date2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnInc = new javax.swing.JButton();
        btnExp = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        date1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtIncome = new javax.swing.JTextField();
        txtExpense = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtmonIn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabInMont = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabExMont = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtmonEx = new javax.swing.JTextField();
        btnExp1 = new javax.swing.JButton();
        ComboMon = new javax.swing.JComboBox<>();
        btnInc4 = new javax.swing.JButton();
        Comboy3 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btnInc2 = new javax.swing.JButton();
        txtquatIn = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtquatEx = new javax.swing.JTextField();
        btnExp2 = new javax.swing.JButton();
        comboQua = new javax.swing.JComboBox<>();
        Comboy2 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnInc3 = new javax.swing.JButton();
        txtyrIn = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        txtyrEx = new javax.swing.JTextField();
        btnExp3 = new javax.swing.JButton();
        Comboy1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 67, 81));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel6.setBackground(new java.awt.Color(240, 52, 52));
        jPanel6.setForeground(new java.awt.Color(240, 52, 52));

        jTabbedPane1.setBackground(new java.awt.Color(0, 67, 81));

        jPanel1.setBackground(new java.awt.Color(0, 67, 81));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("to");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("from");

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
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        btnInc.setBackground(new java.awt.Color(231, 76, 60));
        btnInc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInc.setForeground(new java.awt.Color(255, 255, 255));
        btnInc.setText("Get Income");
        btnInc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncActionPerformed(evt);
            }
        });

        btnExp.setBackground(new java.awt.Color(0, 181, 204));
        btnExp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnExp.setForeground(new java.awt.Color(255, 255, 255));
        btnExp.setText("Get Expense");
        btnExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Income Ledger Entries");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Expense Ledger Entries");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(375, 375, 375)
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(btnInc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExp)
                .addGap(196, 196, 196))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(txtIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(btnExp, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnInc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Day Wise", jPanel1);

        jPanel4.setBackground(new java.awt.Color(0, 67, 81));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Income Ledger Entries");

        tabInMont.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tabInMont);

        tabExMont.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tabExMont);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Expense Ledger Entries");

        btnExp1.setBackground(new java.awt.Color(0, 181, 204));
        btnExp1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnExp1.setForeground(new java.awt.Color(255, 255, 255));
        btnExp1.setText("Get Expense");
        btnExp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExp1ActionPerformed(evt);
            }
        });

        ComboMon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        btnInc4.setBackground(new java.awt.Color(0, 181, 204));
        btnInc4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInc4.setForeground(new java.awt.Color(255, 255, 255));
        btnInc4.setText("Get Income");
        btnInc4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc4ActionPerformed(evt);
            }
        });

        Comboy3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(txtmonIn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtmonEx, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInc4)
                .addGap(391, 391, 391)
                .addComponent(btnExp1)
                .addGap(196, 196, 196))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addComponent(ComboMon, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Comboy3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboMon, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(Comboy3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnInc4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtmonIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnExp1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmonEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("Monthly", jPanel4);

        jPanel5.setBackground(new java.awt.Color(0, 67, 81));

        btnInc2.setBackground(new java.awt.Color(231, 76, 60));
        btnInc2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInc2.setForeground(new java.awt.Color(255, 255, 255));
        btnInc2.setText("Get Income");
        btnInc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc2ActionPerformed(evt);
            }
        });

        txtquatIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquatInActionPerformed(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTable5);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Income Ledger Entries");

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTable6);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Expense Ledger Entries");

        btnExp2.setBackground(new java.awt.Color(0, 181, 204));
        btnExp2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnExp2.setForeground(new java.awt.Color(255, 255, 255));
        btnExp2.setText("Get Expense");
        btnExp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExp2ActionPerformed(evt);
            }
        });

        comboQua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Quarter", "1st", "2nd", "3rd", "4th", " " }));

        Comboy2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(373, 373, 373)
                .addComponent(comboQua, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Comboy2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(txtquatIn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtquatEx, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .addComponent(btnInc2)
                .addGap(400, 400, 400)
                .addComponent(btnExp2)
                .addGap(192, 192, 192))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(139, 139, 139)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(105, 105, 105))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(28, 28, 28)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Comboy2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExp2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInc2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtquatIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtquatEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(483, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap(159, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(37, 37, 37)))
        );

        jTabbedPane1.addTab("Quarterly", jPanel5);

        jPanel2.setBackground(new java.awt.Color(0, 67, 81));

        btnInc3.setBackground(new java.awt.Color(231, 76, 60));
        btnInc3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInc3.setForeground(new java.awt.Color(255, 255, 255));
        btnInc3.setText("Get Income");
        btnInc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInc3ActionPerformed(evt);
            }
        });

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(jTable7);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Income Ledger Entries");

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jTable8);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Expense Ledger Entries");

        btnExp3.setBackground(new java.awt.Color(0, 181, 204));
        btnExp3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnExp3.setForeground(new java.awt.Color(255, 255, 255));
        btnExp3.setText("Get Expense");
        btnExp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExp3ActionPerformed(evt);
            }
        });

        Comboy1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(txtyrIn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtyrEx, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(btnInc3)
                .addGap(143, 143, 143)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 253, Short.MAX_VALUE)
                        .addComponent(btnExp3)
                        .addGap(194, 194, 194))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Comboy1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(139, 139, 139)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(105, 105, 105))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(28, 28, 28)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Comboy1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInc3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExp3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtyrIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtyrEx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(491, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap(159, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(37, 37, 37)))
        );

        jTabbedPane1.addTab("Yearly", jPanel2);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("View All Income & Expense Entries");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(217, 217, 217))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 711, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtquatInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquatInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtquatInActionPerformed

    private void btnInc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc3ActionPerformed
        try {
            // TODO add your handling code here:
            totalInYr();
            showYearIn();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }//GEN-LAST:event_btnInc3ActionPerformed

    private void btnExp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExp3ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            totalExYr();
             showYearEx();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }//GEN-LAST:event_btnExp3ActionPerformed

    private void btnInc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc2ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            totalInQuat();
             showQuartIn();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }//GEN-LAST:event_btnInc2ActionPerformed

    private void btnExp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExp2ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            totalExQuat();
            showQuartEx();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }//GEN-LAST:event_btnExp2ActionPerformed

    private void btnInc4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInc4ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            totalInMon();
            showMonthlyIn();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }//GEN-LAST:event_btnInc4ActionPerformed

    private void btnExp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExp1ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            totalExMon();
             showMonthlyEx();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }//GEN-LAST:event_btnExp1ActionPerformed

    private void btnIncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            totalIncome();
            showDateIn();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }//GEN-LAST:event_btnIncActionPerformed

    private void btnExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            totalExpense();
              showDateEx();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }//GEN-LAST:event_btnExpActionPerformed
   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboMon;
    private javax.swing.JComboBox<String> Comboy1;
    private javax.swing.JComboBox<String> Comboy2;
    private javax.swing.JComboBox<String> Comboy3;
    private javax.swing.JButton btnExp;
    private javax.swing.JButton btnExp1;
    private javax.swing.JButton btnExp2;
    private javax.swing.JButton btnExp3;
    private javax.swing.JButton btnInc;
    private javax.swing.JButton btnInc2;
    private javax.swing.JButton btnInc3;
    private javax.swing.JButton btnInc4;
    private javax.swing.JComboBox<String> comboQua;
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable tabExMont;
    private javax.swing.JTable tabInMont;
    private javax.swing.JTextField txtExpense;
    private javax.swing.JTextField txtIncome;
    private javax.swing.JTextField txtmonEx;
    private javax.swing.JTextField txtmonIn;
    private javax.swing.JTextField txtquatEx;
    private javax.swing.JTextField txtquatIn;
    private javax.swing.JTextField txtyrEx;
    private javax.swing.JTextField txtyrIn;
    // End of variables declaration//GEN-END:variables
}
