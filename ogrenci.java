package okul_sistemi;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ogrenci {

	 private String userName = "root";
	 private String password = "";
	 private String host = "127.0.0.1";
	 private  String db="okul_sistemi";
	 private int port=3306;
	 private Connection con=null;
	 
	 private int ogrenci_no,sinif;
	 private String ogrenci_adi;
	 private String ogrenci_soyadi;
	 
	 
	 
	 
	 public ogrenci(int ogrenci_no, int sinif, String ogrenci_adi, String ogrenci_soyadi) {
		super();
		this.ogrenci_no = ogrenci_no;
		this.sinif = sinif;
		this.ogrenci_adi = ogrenci_adi;
		this.ogrenci_soyadi = ogrenci_soyadi;
	}
		public ogrenci() {
		super();
		this.ogrenci_no = 0;
		this.sinif = 0;
		this.ogrenci_adi = "";
		this.ogrenci_soyadi = "";
	}

	


		public int getOgrenci_no() {
			return ogrenci_no;
		}
		public void setOgrenci_no(int ogrenci_no) {
			this.ogrenci_no = ogrenci_no;
		}
		public int getSinif() {
			return sinif;
		}
		public void setSinif(int sinif) {
			this.sinif = sinif;
		}
		public String getOgrenci_adi() {
			return ogrenci_adi;
		}
		public void setOgrenci_adi(String ogrenci_adi) {
			this.ogrenci_adi = ogrenci_adi;
		}
		public String getOgrenci_soyadi() {
			return ogrenci_soyadi;
		}
		public void setOgrenci_soyadi(String ogrenci_soyadi) {
			this.ogrenci_soyadi = ogrenci_soyadi;
		}
		
		@Override
		public String toString() {
			return "ogrenci [ogrenci_no=" + ogrenci_no + ", sinif=" + sinif + ", ogrenci_adi=" + ogrenci_adi
					+ ", ogrenci_soyadi=" + ogrenci_soyadi + "]";
		}
		
		
		
		//*************OGRNC� KAYIT*******************
	public boolean kaydet() throws ClassNotFoundException, SQLException
		{
			String sorgu="INSERT INTO `ogrenci_tablosu`(`ogrenci_no`, `ogrenci_adi`, `ogrenci_soyadi`, `sinif`) VALUES "+ "("+ogrenci_no+",'"+ogrenci_adi+"','"+ogrenci_soyadi+"',"+ sinif+")";
		System.out.print(sorgu);
		
		if(getir(ogrenci_no)==null)
		{
			int sonuc=sql_calistir(sorgu);
			if(sonuc!=-1)
				return true;
			else {
				return false;
			}
		}
		else 
		return false;
		
	}
	
	//*********OGRENC� S�LME*****************
	public int sil() throws ClassNotFoundException, SQLException {
		String sorgu ="DELETE FROM `ogrenci_tablosu` WHERE ogrenci_no="+Integer.toString(ogrenci_no);
		return sql_calistir(sorgu);
	}
	//************OGRENC� GUNCELLEME **************
	public int guncelle() throws ClassNotFoundException, SQLException {
		String sorgu="UPDATE `ogrenci_tablosu` SET `ogrenci_adi`='"+ogrenci_adi+"',`ogrenci_soyadi`='"+ogrenci_soyadi+"',`sinif`='"+sinif+"' WHERE `ogrenci_no` = "+Integer.toString(ogrenci_no);
	return sql_calistir(sorgu);
	}
	
	
	//********OGRENC� GET�R***********
	public ogrenci getir(int numara) throws ClassNotFoundException, SQLException
	{
		
		try {
			
		String url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.db;

    		 Class.forName ("com.mysql.jdbc.Driver");
			this.con=DriverManager.getConnection(url,userName,password);
			java.sql.Statement st=con.createStatement();
			String sorgu="select * from ogrenci_tablosu where ogrenci_no="+Integer.toString(numara);
			ResultSet res= st.executeQuery(sorgu);
		if(res.next())
    	   {
			this.ogrenci_no = res.getInt("ogrenci_no");
			this.ogrenci_adi = res.getString(2);
			this.ogrenci_soyadi = res.getString(3);
			this.sinif = res.getInt(4);
    		   
    	   }
    	   else {
			return null;
		}
		} catch (Exception e) {
			// TODO: handle exception
			 System.err.println ("Cannot connect to database server " + e.getMessage() );
	            return null;
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

			return this;
			
		}
		
	
	//************SQL FONKS�YONU**********
		public int sql_calistir(String sorgu) throws ClassNotFoundException, SQLException
	       {
			int etki =0;
	    	   String url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.db;
	    	   
	    	
	    	   Class.forName ("com.mysql.jdbc.Driver");
				this.con=DriverManager.getConnection(url,userName,password);
				java.sql.Statement st=con.createStatement();
				//String sorgu="select * from ogrenci_tablosu where ogrenci_no="+Integer.toString(numara);
				 etki=st.executeUpdate(sorgu);
	    	   if(con!=null)
	    	   {
	    		   System.out.println("basarili");
	    		   
	    	   }
	    	   else {
				System.out.print("basarisiz");
			}
			return etki;
	       }
	       public static void main(String[] args) {
	    	   ogrenci dnmDeneme =new ogrenci();
	    	   //dnmDeneme.sql_calistir();
			
		}
			
}
