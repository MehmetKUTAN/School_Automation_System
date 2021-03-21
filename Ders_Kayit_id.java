package okul_sistemi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.loading.PrivateClassLoader;


public class Ders_Kayit_id {
	
	 private String userName = "root";
	 private String password = "";
	 private String host = "127.0.0.1";
	 private  String db="okul_sistemi";
	 private int port=3306;
	 private Connection con=null;
	 

	private ders Ders;
	private ogrenci Ogrenci;
	private int kayit_Id;
	


public Ders_Kayit_id(ders ders, ogrenci ogrenci, int kayit_Id) {
		super();
		Ders = ders;
		Ogrenci = ogrenci;
		this.kayit_Id = kayit_Id;
	}




public Ders_Kayit_id() {
	super();
	Ders = new ders();
	Ogrenci = new ogrenci();
	this.kayit_Id = 0;
}




public ders getDers() {
	return Ders;
}




public void setDers(ders ders) {
	Ders = ders;
}




public ogrenci getOgrenci() {
	return Ogrenci;
}




public void setOgrenci(ogrenci ogrenci) {
	Ogrenci = ogrenci;
}




public int getKayit_Id() {
	return kayit_Id;
}




public void setKayit_Id(int kayit_Id) {
	this.kayit_Id = kayit_Id;
}



@Override
public String toString() {
	return "Ders_Kayit_id [Ders=" + Ders + ", Ogrenci=" + Ogrenci + ", kayit_Id=" + kayit_Id + "]";
}

public boolean Kaydet() throws ClassNotFoundException, SQLException
{
	String sorgu="insert into ders_kayit_id(ders_kayit_Id,ogrenci_no,ders_Id) values "
			+ "("+kayit_Id+",'"+Ogrenci.getOgrenci_no()+"','"+Ders.getDers_Id()+"')";
	System.out.println(sorgu);

	int sonuc = sql_calistir(sorgu);
	
	if(sonuc!=-1)
		return true;
	else 
		return false;
}

public int Sil() throws ClassNotFoundException, SQLException {
	// Veri tabanı silme yapılacak
	
	String sorgu="DELETE FROM `ders_kayit_id` WHERE `ders_kayit_Id` = " + Integer.toString(kayit_Id);
		
	return sql_calistir(sorgu);
	
			
}

public int Guncelle() throws ClassNotFoundException, SQLException {
	// Veri tabanı güncelleme yapılacak
	
	String sorgu="UPDATE `ders_kayit_Id` SET `ogrenci_no`='"+Ogrenci.getOgrenci_no()+"',`ders_Id`="+Ders.getDers_Id() +
			" WHERE `ders_kayit_id` = " + Integer.toString(kayit_Id);
		
	return sql_calistir(sorgu);
}



public  Ders_Kayit_id getir(int number) {
	try {
		
	
	String url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.db;

	 Class.forName ("com.mysql.jdbc.Driver");
	this.con=DriverManager.getConnection(url,userName,password);
	java.sql.Statement st=con.createStatement();
	  String sorgu="SELECT ders_kayit_id.ders_kayit_Id, ogrenci_tablosu.ogrenci_no, ogrenci_tablosu.ogrenci_Adi, ogrenci_tablosu.ogrenci_Soyadi, ogrenci_tablosu.sinif, "
      		+ "ders.ders_Id, ders.ders_Ad, hoca.hoca_Id, hoca.hoca_Ad, hoca.hoca_Soyad \n" + 
      		"FROM ders_kayit_id \n" + 
      		"INNER JOIN ogrenci_tablosu ON ders_kayit_id.ogrenci_no=ogrenci_tablosu.ogrenci_no \n" + 
      		"INNER JOIN ders ON ders_kayit_id.ders_Id=ders.ders_Id \n" + 
      		"INNER JOIN hoca ON ders.hoca_Id=hoca.hoca_Id\n" + 
      		"WHERE ders_kayit_id.ders_kayit_Id=" + Integer.toString(number);
     ResultSet res= st.executeQuery(sorgu);
	
	if(res.next())
	  {
		this.kayit_Id=res.getInt("ders_kayit_Id");
		this.Ders.setDers_Id(res.getInt("ders_Id"));
		this.Ders.setDers_adi(res.getString("ders_Ad"));
		this.Ders.setHoca(new hoca(res.getInt("hoca_Id"),res.getString("hoca_Ad"),res.getString("hoca_Soyad")));
		this.Ogrenci.setOgrenci_no(res.getInt("ogrenci_no"));
		this.Ogrenci.setOgrenci_adi(res.getString("ogrenci_Adi"));
		this.Ogrenci.setOgrenci_soyadi(res.getString("ogrenci_Soyadi"));
		this.Ogrenci.setSinif(res.getInt("sinif"));
    	
	  }
	else {
		return null;
	}
		}
	catch (Exception e)
	{
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
    	  Ders_Kayit_id dnmDeneme =new Ders_Kayit_id();
    	   //dnmDeneme.sql_calistir();
		
	}	
	
	
	
	
	

}
