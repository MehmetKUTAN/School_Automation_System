package okul_sistemi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ders {
	

	 private String userName = "root";
	 private String password = "";
	 private String host = "127.0.0.1";
	 private  String db="okul_sistemi";
	 private int port=3306;
	 private Connection con=null;
	
	
	
  private int ders_Id;
  private String ders_adi;
  private hoca Hoca;
public ders(int ders_Id, String ders_adi, okul_sistemi.hoca hoca_Id) {
	super();
	this.ders_Id = ders_Id;
	this.ders_adi = ders_adi;
	this.Hoca = hoca_Id;
}
public ders() {
	super();
	this.ders_Id = 0;
	this.ders_adi = "";
	this.Hoca = new hoca();
}
public int getDers_Id() {
	return ders_Id;
}
public void setDers_Id(int ders_Id) {
	this.ders_Id = ders_Id;
}
public String getDers_adi() {
	return ders_adi;
}
public void setDers_adi(String ders_adi) {
	this.ders_adi = ders_adi;
}
public hoca getHoca() {
	return Hoca;
}
public void setHoca(hoca hoca) {
	this.Hoca = hoca;
}
@Override
public String toString() {
	return "ders [ders_Id=" + ders_Id + ", ders_adi=" + ders_adi + ", hoca=" + Hoca + "]";
}


public boolean kaydet() throws ClassNotFoundException, SQLException
{
	String sorgu="INSERT INTO `ders`(`ders_Id`, `ders_Ad`, `hoca_Id`) VALUES "+ "("+ders_Id+",'"+ders_adi+"','"+Hoca.getHoca_Id()+"')";
	System.out.print(sorgu);
	if(getir(ders_Id)==null)
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
	
	String sorgu="DELETE FROM `ders` WHERE ders_Id="+Integer.toString(ders_Id);
	return sql_calistir(sorgu);
	
}

public int Guncelle() throws ClassNotFoundException, SQLException {
	//Veri tabani guncelleme yapilacak
	
	String sorgu="UPDATE `ders` SET `ders_Ad`='"+ders_adi+"',`hoca_Id`="+Hoca.getHoca_Id() +
			" WHERE `ders_Id` = " + Integer.toString(ders_Id);
		
	return sql_calistir(sorgu);
}





//*******ders getir***************************//
public ders getir(int numara) throws ClassNotFoundException, SQLException
{
	try
	{
		String url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.db;

	 Class.forName ("com.mysql.jdbc.Driver");
	this.con=DriverManager.getConnection(url,userName,password);
	java.sql.Statement st=con.createStatement();
	String sorgu="SELECT ders.ders_Id, ders.ders_Ad, ders.hoca_Id, hoca.hoca_Ad, hoca.hoca_Soyad FROM ders, hoca WHERE ders.hoca_Id = hoca.hoca_Id and ders.hoca_Id="+Integer.toString(numara);
	ResultSet res= st.executeQuery(sorgu);
if(res.next())
  {
	this.ders_Id=res.getInt("ders_Id");
	this.ders_adi=res.getString("ders_Ad");
	int de=res.getInt("hoca_Id");
	this.Hoca.setHoca_Id(de);
	this.Hoca.setHoca_adi(res.getString("hoca_Ad"));
	this.Hoca.setHoca_soyadi(res.getString("hoca_Soyad"));	    
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
	  hoca dnmDeneme =new hoca();
	   //dnmDeneme.sql_calistir();
   }	
}