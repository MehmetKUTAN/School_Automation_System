package okul_sistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;

public class Search extends JFrame {
	
	 private String userName = "root";
	 private String password = "";
	 private String host = "127.0.0.1";
	 private  String db="okul_sistemi";
	 private int port=3306;
	 private Connection con=null;
	 
	private JPanel contentPane;
	private JTextField txtOSinif;
	/**
	 * Create the frame.
	 */
	public Search() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ders");
		lblNewLabel_1.setBounds(34, 72, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		JComboBox cmbODers = new JComboBox();
		cmbODers.setForeground(Color.RED);
		cmbODers.setBackground(Color.WHITE);
		cmbODers.setBounds(102, 67, 61, 27);
		combo_doldur(cmbODers, "ders");
		contentPane.add(cmbODers);
		
		JLabel lblNewLabel_2 = new JLabel("Hoca");
		lblNewLabel_2.setBounds(34, 117, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		JComboBox cmbOHoca = new JComboBox();
		cmbOHoca.setBackground(Color.WHITE);
		cmbOHoca.setForeground(Color.RED);
		cmbOHoca.setBounds(102, 112, 61, 27);
		combo_doldur(cmbOHoca, "hoca");
		contentPane.add(cmbOHoca);
		
		JTextArea txtOgrRapor = new JTextArea();
		txtOgrRapor.setForeground(Color.WHITE);
		txtOgrRapor.setBackground(Color.DARK_GRAY);
		txtOgrRapor.setBounds(34, 248, 135, 187);
		contentPane.add(txtOgrRapor);
		
		JButton btnOTum = new JButton("Tüm Öğrenciler");
		btnOTum.setForeground(Color.RED);
		btnOTum.setBackground(Color.BLACK);
		btnOTum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sorgu="select * from ogrenci_tablosu";
				ogrenci_sorgula(sorgu,txtOgrRapor);

			}
		});
		btnOTum.setBounds(25, 6, 198, 29);
		contentPane.add(btnOTum);
		
		JLabel lblNewLabel = new JLabel("Sınıf");
		lblNewLabel.setBounds(34, 44, 61, 16);
		contentPane.add(lblNewLabel);
		
		txtOSinif = new JTextField();
		txtOSinif.setBounds(102, 40, 49, 26);
		txtOSinif.setText("");
		contentPane.add(txtOSinif);
		txtOSinif.setColumns(10);
		
		JButton btnNewButton = new JButton("Sorgula");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sorgu="";
				if(txtOSinif.getText().compareTo("")==-1)
				{
					sorgu="select * from ogrenci_tablosu where sinif="+ txtOSinif.getText();
					System.out.println (sorgu);
					ogrenci_sorgula(sorgu,txtOgrRapor);
					return;
				}
				
				if(cmbODers.getSelectedItem().toString() != "")
				{
					sorgu="SELECT ogrenci_tablosu.`ogrenci_no`, ogrenci_tablosu.`ogrenci_adi`, ogrenci_tablosu.`ogrenci_soyadi`, ogrenci_tablosu.`sinif` FROM `ogrenci_tablosu` "
							+ "INNER JOIN ders_kayit_id on ogrenci_tablosu.ogrenci_no=ders_kayit_id.ogrenci_no WHERE ders_kayit_id.ders_Id=" + cmbODers.getSelectedItem().toString() ;
					System.out.println (sorgu);
					ogrenci_sorgula(sorgu,txtOgrRapor);
					return;
				}
				
				if(cmbOHoca.getSelectedItem().toString() != "")
				{
					sorgu="SELECT ogrenci_tablosu.`ogrenci_no`, ogrenci_tablosu.`ogrenci_adi`, ogrenci_tablosu.`ogrenci_soyadi`, ogrenci_tablosu.`sinif` FROM `ogrenci_tablosu` INNER JOIN ders_kayit_id on ogrenci_tablosu.ogrenci_no=ders_kayit_id.ogrenci_no INNER JOIN ders on ders_kayit_id.ders_Id=ders.ders_Id "
							+ "INNER JOIN hoca on ders.hoca_Id=hoca.hoca_Id WHERE hoca.hoca_Id=" + cmbOHoca.getSelectedItem().toString() ;
					System.out.println (sorgu);
					ogrenci_sorgula(sorgu,txtOgrRapor);
				}

			}
		});
		btnNewButton.setBounds(34, 207, 117, 29);
		contentPane.add(btnNewButton);
		
		
	}
	
	private int ogrenci_sorgula(String sorgu,JTextArea txtOgrRapor) {
		try
	    {
			String url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.db;
	    	   Class.forName ("com.mysql.jdbc.Driver");
				this.con=DriverManager.getConnection(url,userName,password);
				//String sorgu="select * from ogrenci_tablosu where ogrenci_no="+Integer.toString(numara);	
	    Statement st = con.createStatement();
	        ResultSet res = st.executeQuery(sorgu);
			String str="";
			while(res.next()) {
				
				str += res.getInt(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getInt(4) + "\n";

			}
			txtOgrRapor.setText(str);
	        return 0;
	        	    
	    }
	    catch (Exception e1)
	    {
	        System.err.println ("Cannot connect to database server " + e1.getMessage() );
	        return 1;
	    }
	    finally
	    {
	        if (con != null)
	        {
	            try
	            {
	                con.close ();
	                System.out.println ("Database connection terminated");
	            }
	            catch (Exception e1) { /* ignore close errors */ }
	        }
	    }
	}

	private void combo_doldur(JComboBox cmb, String tur) {
		try {
			String url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.db;
	    	   Class.forName ("com.mysql.jdbc.Driver");
				this.con=DriverManager.getConnection(url,userName,password);
				java.sql.Statement st=con.createStatement();
				//String sorgu="select * from ogrenci_tablosu where ogrenci_no="+Integer.toString(numara);
				
				String sorgu="select * from "+tur;
				ResultSet res=st.executeQuery(sorgu);

		        while(res.next()) {
		        	
		        	cmb.addItem(res.getObject(1));
		        }
		} catch (Exception e)
	    {
	        System.err.println ("Cannot connect to database server " + e.getMessage() );
	    }
		
	      
	    finally
	    {
	        if (con != null)
	        {
	            try
	            {
	                con.close ();
	                System.out.println ("Database connection terminated");
	            }
	            catch (Exception e) { /* ignore close errors */ }
	        }
	    }
	}
}
	