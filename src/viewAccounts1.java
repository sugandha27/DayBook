import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

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
        try {
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
Calendar c=Calendar.getInstance();
int n=c.get(Calendar.MONTH);
ComboMon.setSelectedIndex(n+1);
int y=c.get(Calendar.YEAR);
Comboy1.setSelectedItem(String.valueOf(y));
Comboy2.setSelectedItem(String.valueOf(y));
Comboy3.setSelectedItem(String.valueOf(y));

showMonthlyEx();
showMonthlyIn();


showYearIn();
showYearEx();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,ex.getMessage());
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
        btnExp = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        date1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtIncome = new javax.swing.JLabel();
        txtExpense = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabInMont = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabExMont = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnExp1 = new javax.swing.JButton();
        ComboMon = new javax.swing.JComboBox<>();
        Comboy3 = new javax.swing.JComboBox<>();
        txtmonIn = new javax.swing.JLabel();
        txtmonEx = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnExp2 = new javax.swing.JButton();
        comboQua = new javax.swing.JComboBox<>();
        Comboy2 = new javax.swing.JComboBox<>();
        txtquatIn = new javax.swing.JLabel();
        txtquatEx = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        btnExp3 = new javax.swing.JButton();
        Comboy1 = new javax.swing.JComboBox<>();
        txtyrEx = new javax.swing.JLabel();
        txtyrIn = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 67, 81));
        setClosable(true);
        setIconifiable(true);
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

        btnExp.setBackground(new java.awt.Color(0, 181, 204));
        btnExp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnExp.setForeground(new java.awt.Color(255, 255, 255));
        btnExp.setText("Show");
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

        txtIncome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtIncome.setForeground(new java.awt.Color(255, 255, 255));
        txtIncome.setText("\n");

        txtExpense.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtExpense.setForeground(new java.awt.Color(255, 255, 255));
        txtExpense.setText("\n");

        jButton6.setBackground(new java.awt.Color(231, 76, 60));
        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("bar chart");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(26, 26, 26)
                                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnExp))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(29, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExp, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtIncome))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtExpense))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))))
                .addContainerGap(55, Short.MAX_VALUE))
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
        btnExp1.setText("Show");
        btnExp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExp1ActionPerformed(evt);
            }
        });

        ComboMon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        Comboy3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year" }));

        txtmonIn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtmonIn.setForeground(new java.awt.Color(255, 255, 255));

        txtmonEx.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtmonEx.setForeground(new java.awt.Color(255, 255, 255));
        txtmonEx.setText("\n");

        jButton5.setBackground(new java.awt.Color(231, 76, 60));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("bar chart");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ComboMon, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Comboy3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExp1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtmonIn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmonEx, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboMon)
                    .addComponent(Comboy3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExp1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtmonEx))
                    .addComponent(txtmonIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Monthly", jPanel4);

        jPanel5.setBackground(new java.awt.Color(0, 67, 81));

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
        btnExp2.setText("Show");
        btnExp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExp2ActionPerformed(evt);
            }
        });

        comboQua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Quarter", "1st", "2nd", "3rd", "4th", " " }));

        Comboy2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year" }));

        txtquatIn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtquatIn.setForeground(new java.awt.Color(255, 255, 255));
        txtquatIn.setText("\n");

        txtquatEx.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtquatEx.setForeground(new java.awt.Color(255, 255, 255));
        txtquatEx.setText("\n");

        jButton7.setBackground(new java.awt.Color(231, 76, 60));
        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("bar chart");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboQua, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Comboy2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExp2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtquatIn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtquatEx, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(526, 526, 526)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(526, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Comboy2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExp2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtquatIn)
                    .addComponent(jLabel10)
                    .addComponent(txtquatEx)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(94, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(43, 43, 43)))
        );

        jTabbedPane1.addTab("Quarterly", jPanel5);

        jPanel2.setBackground(new java.awt.Color(0, 67, 81));

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
        btnExp3.setText("Show");
        btnExp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExp3ActionPerformed(evt);
            }
        });

        Comboy1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year" }));

        txtyrEx.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtyrEx.setForeground(new java.awt.Color(255, 255, 255));

        txtyrIn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtyrIn.setForeground(new java.awt.Color(255, 255, 255));

        jButton8.setBackground(new java.awt.Color(231, 76, 60));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("bar chart");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Comboy1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExp3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtyrIn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtyrEx, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(526, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Comboy1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExp3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addComponent(txtyrEx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtyrIn, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(101, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 716, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExp3ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            totalExYr();
             showYearEx();
              totalInYr();
            showYearIn();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }//GEN-LAST:event_btnExp3ActionPerformed

    private void btnExp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExp2ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            totalExQuat();
            showQuartEx();
                totalInQuat();
             showQuartIn();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }//GEN-LAST:event_btnExp2ActionPerformed

    private void btnExp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExp1ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            totalExMon();
             showMonthlyEx();
              totalInMon();
            showMonthlyIn();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }//GEN-LAST:event_btnExp1ActionPerformed

    private void btnExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            totalExpense();
              showDateEx();
              totalIncome();
            showDateIn();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
    }//GEN-LAST:event_btnExpActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
           
           
            SimpleDateFormat d=new SimpleDateFormat("dd-MM-yyyy"); 
    String d1=d.format(date1.getDate());
    String d2=d.format(date2.getDate());
            String q = "select sum(amount) as sum ,headEntry.type,headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date,str_to_date(headEntry.date,\"%d-%m-%Y\")  from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and str_to_date(headEntry.date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\")) group by date, accountHead.type";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,d1);
            y.setString(3,d2);
          
          
     
            ResultSet rs = y.executeQuery();
            String a1,a2,a3,a4;
            Double de,dd;
                   DefaultCategoryDataset ds=new DefaultCategoryDataset();
            while (rs.next()) {
                a1 = rs.getString("date");
               
                a3=rs.getString("headEntry.type");
               
                if(a3.equals("Income"))
                {
                     a2 = rs.getString("sum");
                      de=Double.parseDouble(a2);
                 ds.setValue(de,"Income",a1);
                }
                else
                {
                    a4=rs.getString("sum");
                    dd=Double.parseDouble(a4);
                   ds.setValue(dd,"Expense",a1);  
                }
                 

            }
         
         
         JFreeChart chart=ChartFactory.createBarChart3D("Statistics", "Date", "Amount", ds,PlotOrientation.VERTICAL,true,true,false);
//                JFreeChart chart1=ChartFactory.createBarChart3D("Statistics", "Date", "Expense", df,PlotOrientation.VERTICAL,true,true,false);
                chart.setBackgroundPaint( new Color(0,181,204));
                chart.getTitle().setPaint(Color.WHITE);
                CategoryPlot p=chart.getCategoryPlot();
                p.setRangeGridlinePaint(Color.WHITE);
                BarRenderer r=(BarRenderer)p.getRenderer();
             Color c=new Color(0,81,67);
             r.setSeriesPaint(0, c);
             ChartFrame f=new ChartFrame("Monthly Income",chart);
//                ChartFrame f1=new ChartFrame("Monthly Expense",chart1);
                f.setVisible(true);
                f.setSize(450,350);
                        f.setLocationRelativeTo(null);
         }
            catch(Exception ex)
         {
             ex.printStackTrace();
         }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
           
            String mon=String.valueOf(ComboMon.getSelectedItem());
            String yr=String.valueOf(Comboy3.getSelectedItem());
            String d1="01-"+mon+"-"+yr;
            String d2="31-"+mon+"-"+yr;
            String q = "select sum(amount) as sum ,headEntry.type,headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date,str_to_date(headEntry.date,\"%d-%m-%Y\")  from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and str_to_date(headEntry.date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\")) group by date, accountHead.type";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,d1);
            y.setString(3,d2);
          
          
     
            ResultSet rs = y.executeQuery();
            String a1,a2,a3,a4;
            Double de,dd;
                   DefaultCategoryDataset ds=new DefaultCategoryDataset();
            while (rs.next()) {
                a1 = rs.getString("date");
               
                a3=rs.getString("headEntry.type");
               
                if(a3.equals("Income"))
                {
                     a2 = rs.getString("sum");
                      de=Double.parseDouble(a2);
                 ds.setValue(de,"Income",a1);
                }
                else
                {
                    a4=rs.getString("sum");
                    dd=Double.parseDouble(a4);
                   ds.setValue(dd,"Expense",a1);  
                }
                 

            }
         
         
         JFreeChart chart=ChartFactory.createBarChart3D("Statistics", "Date", "Amount", ds,PlotOrientation.VERTICAL,true,true,false);
//                JFreeChart chart1=ChartFactory.createBarChart3D("Statistics", "Date", "Expense", df,PlotOrientation.VERTICAL,true,true,false);
                chart.setBackgroundPaint( new Color(0,181,204));
                chart.getTitle().setPaint(Color.WHITE);
                CategoryPlot p=chart.getCategoryPlot();
                p.setRangeGridlinePaint(Color.WHITE);
                BarRenderer r=(BarRenderer)p.getRenderer();
             Color c=new Color(0,81,67);
             r.setSeriesPaint(0, c);
             ChartFrame f=new ChartFrame("Monthly Income",chart);

                f.setVisible(true);
                f.setSize(450,350);
                        f.setLocationRelativeTo(null);
         }
            catch(Exception ex)
         {
             ex.printStackTrace();
         }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
            try {
            // TODO add your handling code here:
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
    
            String q = "select sum(amount) as sum ,headEntry.type,headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date,str_to_date(headEntry.date,\"%d-%m-%Y\")  from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and str_to_date(headEntry.date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\")) group by date, accountHead.type";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,d1);
            y.setString(3,d2);
          
          
     
            ResultSet rs = y.executeQuery();
            String a1,a2,a3,a4;
            Double de,dd;
                   DefaultCategoryDataset ds=new DefaultCategoryDataset();
            while (rs.next()) {
                a1 = rs.getString("date");
               
                a3=rs.getString("headEntry.type");
               
                if(a3.equals("Income"))
                {
                     a2 = rs.getString("sum");
                      de=Double.parseDouble(a2);
                 ds.setValue(de,"Income",a1);
                }
                else
                {
                    a4=rs.getString("sum");
                    dd=Double.parseDouble(a4);
                   ds.setValue(dd,"Expense",a1);  
                }
                 

            }
         
         
         JFreeChart chart=ChartFactory.createBarChart3D("Statistics", "Date", "Amount", ds,PlotOrientation.VERTICAL,true,true,false);
//                JFreeChart chart1=ChartFactory.createBarChart3D("Statistics", "Date", "Expense", df,PlotOrientation.VERTICAL,true,true,false);
                chart.setBackgroundPaint( new Color(0,181,204));
                chart.getTitle().setPaint(Color.WHITE);
                CategoryPlot p=chart.getCategoryPlot();
                p.setRangeGridlinePaint(Color.WHITE);
                BarRenderer r=(BarRenderer)p.getRenderer();
             Color c=new Color(0,81,67);
             r.setSeriesPaint(0, c);
             ChartFrame f=new ChartFrame("Monthly Income",chart);
//                ChartFrame f1=new ChartFrame("Monthly Expense",chart1);
                f.setVisible(true);
                f.setSize(450,350);
                        f.setLocationRelativeTo(null);
         }
            catch(Exception ex)
         {
             ex.printStackTrace();
             JOptionPane.showMessageDialog(this,"Please select quarter for the year");
         }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
              try {
            String yr=String.valueOf(Comboy1.getSelectedItem());
    String d1="01-01-"+yr;
    String d2="31-12-"+yr;
    
            String q = "select sum(amount) as sum ,headEntry.type,headEntry.entry_id,accountHead.l_name,headEntry.amount,headEntry.details,headEntry.date,str_to_date(headEntry.date,\"%d-%m-%Y\")  from headEntry inner join accountHead on headEntry.username=accountHead.username and headEntry.type=accountHead.type and headEntry.l_code=accountHead.l_code where (accountHead.username=? and str_to_date(headEntry.date,\"%d-%m-%Y\") BETWEEN str_to_date(?,\"%d-%m-%Y\") and str_to_date(?,\"%d-%m-%Y\")) group by date, accountHead.type";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,username);
            y.setString(2,d1);
            y.setString(3,d2);
          
          
     
            ResultSet rs = y.executeQuery();
            String a1,a2,a3,a4;
            Double de,dd;
                   DefaultCategoryDataset ds=new DefaultCategoryDataset();
            while (rs.next()) {
                a1 = rs.getString("date");
               
                a3=rs.getString("headEntry.type");
               
                if(a3.equals("Income"))
                {
                     a2 = rs.getString("sum");
                      de=Double.parseDouble(a2);
                 ds.setValue(de,"Income",a1);
                }
                else
                {
                    a4=rs.getString("sum");
                    dd=Double.parseDouble(a4);
                   ds.setValue(dd,"Expense",a1);  
                }
                 

            }
         
         
         JFreeChart chart=ChartFactory.createBarChart3D("Statistics", "Date", "Amount", ds,PlotOrientation.VERTICAL,true,true,false);
//                JFreeChart chart1=ChartFactory.createBarChart3D("Statistics", "Date", "Expense", df,PlotOrientation.VERTICAL,true,true,false);
                chart.setBackgroundPaint( new Color(0,181,204));
                chart.getTitle().setPaint(Color.WHITE);
                CategoryPlot p=chart.getCategoryPlot();
                p.setRangeGridlinePaint(Color.WHITE);
                BarRenderer r=(BarRenderer)p.getRenderer();
             Color c=new Color(0,81,67);
             r.setSeriesPaint(0, c);
             ChartFrame f=new ChartFrame("Monthly Income",chart);
//                ChartFrame f1=new ChartFrame("Monthly Expense",chart1);
                f.setVisible(true);
                f.setSize(450,350);
                        f.setLocationRelativeTo(null);
         }
            catch(Exception ex)
         {
             ex.printStackTrace();
         }
    }//GEN-LAST:event_jButton8ActionPerformed
   
                

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboMon;
    private javax.swing.JComboBox<String> Comboy1;
    private javax.swing.JComboBox<String> Comboy2;
    private javax.swing.JComboBox<String> Comboy3;
    private javax.swing.JButton btnExp;
    private javax.swing.JButton btnExp1;
    private javax.swing.JButton btnExp2;
    private javax.swing.JButton btnExp3;
    private javax.swing.JComboBox<String> comboQua;
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
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
    private javax.swing.JLabel txtExpense;
    private javax.swing.JLabel txtIncome;
    private javax.swing.JLabel txtmonEx;
    private javax.swing.JLabel txtmonIn;
    private javax.swing.JLabel txtquatEx;
    private javax.swing.JLabel txtquatIn;
    private javax.swing.JLabel txtyrEx;
    private javax.swing.JLabel txtyrIn;
    // End of variables declaration//GEN-END:variables
}
