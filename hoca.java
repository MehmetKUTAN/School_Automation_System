package okul_sistemi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class hoca {
	
	
	 private String userName = "root";
	 private String password = "";
	 private String host = "127.0.0.1";
	 private  String db="okul_sistemi";
	 private int port=3306;
	 private Connection con=null;
	
	
	
	
	private int hoca_Id;
	private String Hoca_adi;
	private String Hoca_soyadi;

	public hoca(int hoca_Id, String hoca_adi, String hoca_soyadi) {
		super();
		this.hoca_Id = hoca_Id;
		Hoca_adi = hoca_adi;
		Hoca_soyadi = hoca_soyadi;
	}

	public hoca() {
		super();
		this.hoca_Id =0;
		Hoca_adi = "";
		Hoca_soyadi = "";
	}

	public int getHoca_Id() {
		return hoca_Id;
	}

	public void setHoca_Id(int hoca_Id) {
		this.hoca_Id = hoca_Id;
	}

	public String getHoca_adi() {
		return Hoca_adi;
	}

	public void setHoca_adi(String hoca_adi) {
		Hoca_adi = hoca_adi;
	}

	public String getHoca_soyadi() {
		return Hoca_soyadi;
	}

	public void setHoca_soyadi(String hoca_soyadi) {
		Hoca_soyadi = hoca_soyadi;
	}

	@Override
	public String toString() {
		return "hoca [hoca_Id=" + hoca_Id + ", Hoca_adi=" + Hoca_adi + ", Hoca_soyadi=" + Hoca_soyadi + "]";
	}
	
	
	public boolean kaydet() throws ClassNotFoundException, SQLException 
	{
		String sorgu="INSERT INTO `hoca`(`hoca_Id`, `hoca_Ad`, `hoca_Soyad`) VALUES "+ "("+hoca_Id+",'"+Hoca_adi+"','"+Hoca_soyadi+"')";
	System.out.print(sorgu);
	if(getir(hoca_Id)==null)
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
	
	public int sil() throws ClassNotFoundException, SQLException {
		String sorgu="DELETE FROM `hoca` WHERE hoca_Id="+Integer.toString(hoca_Id);
		return sql_calistir(sorgu);
		
	}
	
	
	public int guncelle() throws ClassNotFoundException, SQLException
	{
		String sorgu="UPDATE `hoca` SET `hoca_Ad`='"+Hoca_adi+"',`hoca_Soyad`='"+Hoca_soyadi+"' WHERE `hoca_Id`="+Integer.toString(hoca_Id);
	return sql_calistir(sorgu);
	}
	
	public hoca getir(int numara) throws ClassNotFoundException, SQLException
	{
		
		try {
			
		String url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.db;

    		 Class.forName ("com.mysql.jdbc.Driver");
			this.con=DriverManager.getConnection(url,userName,password);
			java.sql.Statement st=con.createStatement();
			String sorgu="select * from hoca where hoca_Id="+Integer.toString(numara);
			ResultSet res= st.executeQuery(sorgu);
		if(res.next())
    	   {
			this.hoca_Id = res.getInt("hoca_Id");
			this.Hoca_adi = res.getString(2);
			this.Hoca_soyadi= res.getString(3);
			    		   
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
			@SuppressWarnings("finally")
			public int sql_calistir(String sorgu) throws ClassNotFoundException, SQLException
		       {
				int etki =0;
		    	   try {
					String url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.db;
					   
					
					   Class.forName ("com.mysql.jdbc.Driver");
						this.con=DriverManager.getConnection(url,userName,password);
						java.sql.Statement st=con.createStatement();
						//String sorgu="select * from ogrenci_tablosu where ogrenci_no="+Integer.toString(numara);
						 etki=st.executeUpdate(sorgu);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println ("database server error = " + e.getMessage() );
			        return -1;
				}
		    	 finally
		    	 {if(con!=null)
		    	   {
		    		   System.out.println("basarili");
		    		   
		    	   }
		    	   else {
					System.out.print("basarisiz");
		    	   		}
				return etki;
		    	 }
		       }
		       public static void main(String[] args) {
		    	  hoca dnmDeneme =new hoca();
		    	   //dnmDeneme.sql_calistir();
				
			}	
}
