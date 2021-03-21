package okul_sistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Okul_form extends JFrame {
	
	 private String userName = "root";
	 private String password = "";
	 private String host = "127.0.0.1";
	 private  String db="okul_sistemi";
	 private int port=3306;
	 private Connection con=null;
		
	private ogrenci ogr;
	private hoca Hoca;
	private ders Ders;
	private Ders_Kayit_id kayit;	
	
	private JPanel contentPane;
	private JTextField ogr_no_txt;
	private JTextField ogr_adi_txt;
	private JTextField ogr_soyadi_txt;
	private JTextField ogr_sinif_txt;
	private JTextField hoca_ıd_txt;
	private JTextField hoca_adi_txt;
	private JTextField hoca_soyadi_txt;
	private JTextField ders_ıd_txt;
	private JTextField ders_adi_txt;
	private JTextField ders_kayit_Id_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Okul_form frame = new Okul_form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Okul_form() {
		setBackground(Color.YELLOW);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 396);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("OGRENC\u0130 HOCA OTOMASYON S\u0130STEM\u0130");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		label.setBackground(Color.WHITE);
		label.setBounds(5, 0, 621, 32);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Ogrenci No:");
		label_1.setBounds(39, 49, 89, 14);
		contentPane.add(label_1);
		
		ogr_no_txt = new JTextField();
		ogr_no_txt.setForeground(Color.WHITE);
		ogr_no_txt.setColumns(10);
		ogr_no_txt.setBackground(Color.RED);
		ogr_no_txt.setBounds(177, 44, 86, 20);
		contentPane.add(ogr_no_txt);
		
		JLabel label_2 = new JLabel("Ogrenci Adi:");
		label_2.setBounds(39, 80, 89, 14);
		contentPane.add(label_2);
		
		ogr_adi_txt = new JTextField();
		ogr_adi_txt.setForeground(Color.WHITE);
		ogr_adi_txt.setColumns(10);
		ogr_adi_txt.setBackground(Color.RED);
		ogr_adi_txt.setBounds(177, 75, 86, 20);
		contentPane.add(ogr_adi_txt);
		
		JLabel label_3 = new JLabel("Ogrenci Soyadi:");
		label_3.setBounds(39, 108, 120, 14);
		contentPane.add(label_3);
		
		ogr_soyadi_txt = new JTextField();
		ogr_soyadi_txt.setForeground(Color.WHITE);
		ogr_soyadi_txt.setColumns(10);
		ogr_soyadi_txt.setBackground(Color.RED);
		ogr_soyadi_txt.setBounds(177, 103, 86, 20);
		contentPane.add(ogr_soyadi_txt);
		
		JLabel label_4 = new JLabel("Ogrenci sinifi:");
		label_4.setBounds(39, 136, 120, 14);
		contentPane.add(label_4);
		
		ogr_sinif_txt = new JTextField();
		ogr_sinif_txt.setForeground(Color.WHITE);
		ogr_sinif_txt.setColumns(10);
		ogr_sinif_txt.setBackground(Color.RED);
		ogr_sinif_txt.setBounds(177, 131, 86, 20);
		contentPane.add(ogr_sinif_txt);
		
		JButton btn_ogr_kyt = new JButton("KAYDET");
		btn_ogr_kyt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ogr =new ogrenci();
				ogr.setOgrenci_no(Integer.parseInt(ogr_no_txt.getText()));
				ogr.setOgrenci_adi(ogr_adi_txt.getText());
				ogr.setOgrenci_soyadi(ogr_soyadi_txt.getText());
				ogr.setSinif(Integer.parseInt(ogr_sinif_txt.getText()));
				try {
					if(ogr.kaydet())
						{
							JOptionPane.showMessageDialog(null,ogr.getOgrenci_no()+"numarali ogrenci kaydedildi");
							
						}
						else
						{
							JOptionPane.showMessageDialog(null,ogr.getOgrenci_no()+"ogrenci kaydedilmedi ");
						}
				} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 			
			
			}
		});
		btn_ogr_kyt.setForeground(Color.RED);
		btn_ogr_kyt.setBackground(Color.BLACK);
		btn_ogr_kyt.setBounds(5, 161, 93, 20);
		contentPane.add(btn_ogr_kyt);
		
		JButton btn_ogr_getir = new JButton("GET\u0130R");
		btn_ogr_getir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ogr=null;
				try {
					ogr=(new ogrenci()).getir(Integer.parseInt(ogr_no_txt.getText()));
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(ogr!=null)
				{
					ogr_no_txt.setText(Integer.toString(ogr.getOgrenci_no()));
					ogr_adi_txt.setText(ogr.getOgrenci_adi());
					ogr_soyadi_txt.setText(ogr.getOgrenci_soyadi());;
					ogr_sinif_txt.setText(Integer.toString(ogr.getSinif()));
				}
				else
				{
				 ogr_adi_txt.setText("" );
				 ogr_soyadi_txt.setText("");
					JOptionPane.showMessageDialog(null, ogr_no_txt.getText() + "  ogrenci bulunamadi");
				}
				
			}
		});
		btn_ogr_getir.setForeground(Color.RED);
		btn_ogr_getir.setBackground(Color.BLACK);
		btn_ogr_getir.setBounds(94, 161, 75, 20);
		contentPane.add(btn_ogr_getir);
		
		JButton btn_ogr_sil = new JButton("S\u0130L");
		btn_ogr_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ogr=null;
			try {
				ogr=(new ogrenci()).getir(Integer.parseInt(ogr_no_txt.getText()));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ogr!=null)
			{
				ogr.setOgrenci_no(Integer.parseInt(ogr_no_txt.getText()));
				try {
					ogr.sil();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(null,ogr_no_txt.getText()+" ogrenci bulunamadi");
			}
			
			}
			
		});
		btn_ogr_sil.setForeground(Color.RED);
		btn_ogr_sil.setBackground(Color.BLACK);
		btn_ogr_sil.setBounds(164, 161, 65, 20);
		contentPane.add(btn_ogr_sil);
		
		JButton btn_ogr_guncelle = new JButton("GUNCELLE");
		btn_ogr_guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ogr =null;
				 try {
					ogr=(new ogrenci()).getir(Integer.parseInt(ogr_no_txt.getText()));
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 if(ogr!=null)
				 {
					 ogr.setOgrenci_no(Integer.parseInt(ogr_no_txt.getText()));
					 ogr.setOgrenci_adi(ogr_adi_txt.getText());
					 ogr.setOgrenci_soyadi(ogr_soyadi_txt.getText());
					 ogr.setSinif(Integer.parseInt(ogr_sinif_txt.getText()));
				 try {
					ogr.guncelle();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 }
				 else {
					 ogr=new ogrenci();
					 ogr.setOgrenci_no(Integer.parseInt(ogr_no_txt.getText()));
					 ogr.setOgrenci_adi(ogr_adi_txt.getText());
					 ogr.setOgrenci_soyadi(ogr_soyadi_txt.getText());
					 ogr.setSinif(Integer.parseInt(ogr_sinif_txt.getText()));
					 try {
						ogr.kaydet();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 JOptionPane.showMessageDialog(null,ogr_no_txt.getText()+"  numarali ogrenci bulunamadi ve "+ogr_no_txt.getText()+" numarli ogrenci kaydedildi");
					 
					 
				 }
				
			}
		});
		btn_ogr_guncelle.setForeground(Color.RED);
		btn_ogr_guncelle.setBackground(Color.BLACK);
		btn_ogr_guncelle.setBounds(227, 161, 109, 20);
		contentPane.add(btn_ogr_guncelle);
		
		JLabel label_5 = new JLabel("Hoca ID:");
		label_5.setBounds(427, 46, 89, 14);
		contentPane.add(label_5);
		
		hoca_ıd_txt = new JTextField();
		hoca_ıd_txt.setForeground(Color.WHITE);
		hoca_ıd_txt.setColumns(10);
		hoca_ıd_txt.setBackground(Color.RED);
		hoca_ıd_txt.setBounds(535, 44, 86, 20);
		contentPane.add(hoca_ıd_txt);
		
		JLabel label_6 = new JLabel("Hoca Adi:");
		label_6.setBounds(427, 77, 89, 14);
		contentPane.add(label_6);
		
		hoca_adi_txt = new JTextField();
		hoca_adi_txt.setForeground(Color.WHITE);
		hoca_adi_txt.setColumns(10);
		hoca_adi_txt.setBackground(Color.RED);
		hoca_adi_txt.setBounds(535, 72, 86, 20);
		contentPane.add(hoca_adi_txt);
		
		JLabel label_7 = new JLabel("Hoca Soyadi:");
		label_7.setBackground(Color.RED);
		label_7.setBounds(427, 105, 108, 14);
		contentPane.add(label_7);
		
		hoca_soyadi_txt = new JTextField();
		hoca_soyadi_txt.setForeground(Color.WHITE);
		hoca_soyadi_txt.setColumns(10);
		hoca_soyadi_txt.setBackground(Color.RED);
		hoca_soyadi_txt.setBounds(535, 100, 86, 20);
		contentPane.add(hoca_soyadi_txt);
		
		JButton btn_hoca_kaydet = new JButton("KAYDET");
		btn_hoca_kaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Hoca =new hoca();
				Hoca.setHoca_Id(Integer.parseInt(hoca_ıd_txt.getText()));
				Hoca.setHoca_adi(hoca_adi_txt.getText());
				Hoca.setHoca_soyadi(hoca_soyadi_txt.getText());
				try {
					if(Hoca.kaydet())
					{
						JOptionPane.showMessageDialog(null, hoca_ıd_txt.getText()+" Id'sine ait hoca kaydedildi");
						
					}
					else
					{
						JOptionPane.showMessageDialog(null,hoca_ıd_txt.getText()+" Id'sine sahip hoca kayıt edilemedi");
					}
				} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btn_hoca_kaydet.setForeground(Color.RED);
		btn_hoca_kaydet.setBackground(Color.BLACK);
		btn_hoca_kaydet.setBounds(346, 158, 93, 20);
		contentPane.add(btn_hoca_kaydet);
		
		JButton btn_hoca_getir = new JButton("GET\u0130R");
		btn_hoca_getir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hoca=null;
				try {
					
					Hoca=(new hoca()).getir(Integer.parseInt(hoca_ıd_txt.getText()));
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(Hoca!=null)
				{
					hoca_adi_txt.setText(Hoca.getHoca_adi());
					hoca_soyadi_txt.setText(Hoca.getHoca_soyadi());
					
				}
				else {
					{
						JOptionPane.showMessageDialog(null,hoca_ıd_txt.getText()+" numarali hoca bulunamadi");
						
					}
				}
				
				
			}
		});
		btn_hoca_getir.setForeground(Color.RED);
		btn_hoca_getir.setBackground(Color.BLACK);
		btn_hoca_getir.setBounds(435, 158, 75, 20);
		contentPane.add(btn_hoca_getir);
		
		JButton btn_hoca_sil = new JButton("S\u0130L");
		btn_hoca_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hoca =null;
				try {
					Hoca=(new hoca()).getir(Integer.parseInt(hoca_ıd_txt.getText()));
				} catch (NumberFormatException | ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, hoca_ıd_txt.getText()+"hoca bulunamdi");
					e2.printStackTrace();
				}
				
				if(Hoca!=null)
				{
					try {
						Hoca.sil();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println("ogrenci silinemedi");
						
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_hoca_sil.setForeground(Color.RED);
		btn_hoca_sil.setBackground(Color.BLACK);
		btn_hoca_sil.setBounds(505, 158, 65, 20);
		contentPane.add(btn_hoca_sil);
		
		JButton btn_hoca_guncelle = new JButton("GUNCELLE");
		btn_hoca_guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hoca =null;
				try {
					Hoca=(new hoca()).getir(Integer.parseInt(hoca_ıd_txt.getText()));
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			Hoca.setHoca_Id(Integer.parseInt(hoca_ıd_txt.getText()));
			Hoca.setHoca_adi(hoca_adi_txt.getText());
			Hoca.setHoca_soyadi(hoca_soyadi_txt.getText());
			try {
				Hoca.guncelle();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,hoca_ıd_txt.getText()+" hoca Id si bulunamadi" );
				e1.printStackTrace();
			}
			
			}
		});
		btn_hoca_guncelle.setForeground(Color.RED);
		btn_hoca_guncelle.setBackground(Color.BLACK);
		btn_hoca_guncelle.setBounds(568, 158, 109, 20);
		contentPane.add(btn_hoca_guncelle);
		
		JLabel label_8 = new JLabel("Ders_Id:");
		label_8.setBounds(39, 214, 89, 14);
		contentPane.add(label_8);
		
		ders_ıd_txt = new JTextField();
		ders_ıd_txt.setForeground(Color.WHITE);
		ders_ıd_txt.setColumns(10);
		ders_ıd_txt.setBackground(Color.RED);
		ders_ıd_txt.setBounds(138, 208, 86, 20);
		contentPane.add(ders_ıd_txt);
		
		JLabel label_9 = new JLabel("Ders Adi:");
		label_9.setBounds(39, 245, 89, 14);
		contentPane.add(label_9);
		
		ders_adi_txt = new JTextField();
		ders_adi_txt.setForeground(Color.WHITE);
		ders_adi_txt.setColumns(10);
		ders_adi_txt.setBackground(Color.RED);
		ders_adi_txt.setBounds(138, 239, 86, 20);
		contentPane.add(ders_adi_txt);
		
		JComboBox hoca_ıd_combox = new JComboBox();
		hoca_ıd_combox.setBounds(138, 270, 86, 22);
		combo_doldur(hoca_ıd_combox, "hoca");
		contentPane.add(hoca_ıd_combox);
		
		
		
		
		JLabel label_10 = new JLabel("Hoca_Id");
		label_10.setBounds(39, 273, 89, 14);
		contentPane.add(label_10);
		
		JButton btn_ders_kaydet = new JButton("KAYDET");
		btn_ders_kaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ders =new ders();
				Ders.setDers_Id(Integer.parseInt(ders_ıd_txt.getText()));
				Ders.setDers_adi(ders_adi_txt.getText());
				try {
					Ders.setHoca((new hoca()).getir((int)hoca_ıd_combox.getSelectedItem()));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(Ders.getHoca().getHoca_Id());
				try {
					if(Ders.kaydet())
					{
						JOptionPane.showMessageDialog(null,Ders.getDers_Id()+" Id'li ders kaydedildi");
						
					}
					else {
						JOptionPane.showMessageDialog(null,Ders.getDers_Id()+" Id'li ders kaydedilmedi");
						
					}
				} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_ders_kaydet.setForeground(Color.RED);
		btn_ders_kaydet.setBackground(Color.BLACK);
		btn_ders_kaydet.setBounds(0, 303, 93, 20);
		contentPane.add(btn_ders_kaydet);
		
		JButton btn_ders_getir = new JButton("GET\u0130R");
		btn_ders_getir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ders =null;
				try {
					Ders=(new ders()).getir(Integer.parseInt(ders_ıd_txt.getText()));
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(Ders!=null)
				{
				ders_adi_txt.setText(Ders.getDers_adi());
				hoca_ıd_combox.setSelectedItem(Ders.getHoca().getHoca_Id());
				hoca_ıd_txt.setText(Integer.toString(Ders.getHoca().getHoca_Id()));
				hoca_adi_txt.setText(Ders.getHoca().getHoca_adi());
				hoca_soyadi_txt.setText(Ders.getHoca().getHoca_soyadi());
				}
				else {
					JOptionPane.showMessageDialog(null,ders_ıd_txt.getText()+" Ders  bulunamadi");
				}
				}
		});
		btn_ders_getir.setForeground(Color.RED);
		btn_ders_getir.setBackground(Color.BLACK);
		btn_ders_getir.setBounds(89, 303, 75, 20);
		contentPane.add(btn_ders_getir);
		
		JButton btn_ders_sil = new JButton("S\u0130L");
		btn_ders_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ders =null;
				Ders =new ders();
				Ders.setDers_Id(Integer.parseInt(ders_ıd_txt.getText()));;
			try {
				Ders.sil();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,ders_ıd_txt.getText()+"  Id'li  ders silinemedi");
				e1.printStackTrace();
			}
			}
		});
		btn_ders_sil.setForeground(Color.RED);
		btn_ders_sil.setBackground(Color.BLACK);
		btn_ders_sil.setBounds(159, 303, 65, 20);
		contentPane.add(btn_ders_sil);
		
		JButton btn_ders_guncelle = new JButton("GUNCELLE");
		btn_ders_guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ders=null;
				Ders=new ders();
				Ders.setDers_Id(Integer.parseInt(ders_ıd_txt.getText()));
				Ders.setDers_adi(ders_adi_txt.getText());
				try {
					Ders.setHoca((new hoca()).getir((int)hoca_ıd_combox.getSelectedItem()));
					Ders.Guncelle();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,ders_ıd_txt.getText()+" ders bulunamadi  Kaydet tusuna basiniz");
					e1.printStackTrace();
				}
			}
		});
		btn_ders_guncelle.setForeground(Color.RED);
		btn_ders_guncelle.setBackground(Color.BLACK);
		btn_ders_guncelle.setBounds(222, 303, 109, 20);
		contentPane.add(btn_ders_guncelle);
		
		JLabel lblDersAdi = new JLabel("Ders Id:");
		lblDersAdi.setBounds(460, 274, 89, 14);
		contentPane.add(lblDersAdi);
		
		JComboBox cmbbox_ders_Id = new JComboBox();
		cmbbox_ders_Id.setBounds(591, 265, 86, 22);
		combo_ders_Id_doldur(cmbbox_ders_Id, "ders");
		contentPane.add(cmbbox_ders_Id);
		
		JComboBox cmbbox_ogrenci_no = new JComboBox();
		cmbbox_ogrenci_no.setBounds(591, 232, 86, 22);
		combo_ogrenci_Id_doldur(cmbbox_ogrenci_no, "ogrenci_tablosu");
		contentPane.add(cmbbox_ogrenci_no);
		
		JButton button = new JButton("KAYDET");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kayit=new Ders_Kayit_id();
				try {
					kayit.setKayit_Id(Integer.parseInt(ders_kayit_Id_txt.getText()));
					kayit.setOgrenci((new ogrenci()).getir((int) cmbbox_ogrenci_no.getSelectedItem()));
					kayit.setDers((new ders()).getir((int) cmbbox_ders_Id.getSelectedItem()));
					if (kayit.Kaydet()) {
						JOptionPane.showMessageDialog(null,ders_kayit_Id_txt.getText()+" 'ıd'li Kayit basarili");
						
					}
					else {
						JOptionPane.showMessageDialog(null,ders_kayit_Id_txt.getText()+" 'ıd'li Kayit basarisiz");
						
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button.setForeground(Color.RED);
		button.setBackground(Color.BLACK);
		button.setBounds(346, 303, 93, 20);
		contentPane.add(button);
		
		JButton button_1 = new JButton("GET\u0130R");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kayit=null;
				kayit=(new Ders_Kayit_id()).getir(Integer.parseInt(ders_kayit_Id_txt.getText()));
				if(kayit!=null)
				{
					cmbbox_ogrenci_no.setSelectedItem(kayit.getOgrenci().getOgrenci_no());
					ogr_no_txt.setText(Integer.toString(kayit.getOgrenci().getOgrenci_no()));
					ogr_adi_txt.setText(kayit.getOgrenci().getOgrenci_adi());
					ogr_soyadi_txt.setText(kayit.getOgrenci().getOgrenci_soyadi());
					ogr_sinif_txt.setText(Integer.toString(kayit.getOgrenci().getSinif()));
					cmbbox_ders_Id.setSelectedItem(kayit.getDers().getDers_Id());
					ders_ıd_txt.setText(Integer.toString(kayit.getDers().getDers_Id()));
					ders_adi_txt.setText(kayit.getDers().getDers_adi());
					hoca_ıd_combox.setSelectedItem(kayit.getDers().getDers_Id());
					
								
				}
				else {
					JOptionPane.showMessageDialog(null,ders_kayit_Id_txt.getText() +" Id'sine sahip Ders kayit ıd'si bulunamadi");
				}
				
				
				
				
			}
		});
		button_1.setForeground(Color.RED);
		button_1.setBackground(Color.BLACK);
		button_1.setBounds(435, 303, 75, 20);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("S\u0130L");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kayit=null;
				kayit=new Ders_Kayit_id();
				kayit.setKayit_Id(Integer.parseInt(ders_kayit_Id_txt.getText()));
				try {
					kayit.Sil();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setForeground(Color.RED);
		button_2.setBackground(Color.BLACK);
		button_2.setBounds(505, 303, 65, 20);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("GUNCELLE");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kayit=null;
				kayit=new Ders_Kayit_id();
				kayit.setKayit_Id(Integer.parseInt(ders_kayit_Id_txt.getText()));
				try {
					kayit.setOgrenci((new ogrenci()).getir((int) cmbbox_ogrenci_no.getSelectedItem()));
					kayit.setDers((new ders()).getir((int) cmbbox_ders_Id.getSelectedItem()));
					kayit.Guncelle();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_3.setForeground(Color.RED);
		button_3.setBackground(Color.BLACK);
		button_3.setBounds(568, 303, 109, 20);
		contentPane.add(button_3);
		
		ders_kayit_Id_txt = new JTextField();
		ders_kayit_Id_txt.setBounds(591, 203, 86, 20);
		contentPane.add(ders_kayit_Id_txt);
		ders_kayit_Id_txt.setColumns(10);
		
		JLabel ders_kayit_Id = new JLabel("Ders kayit Id:");
		ders_kayit_Id.setBounds(460, 211, 110, 14);
		contentPane.add(ders_kayit_Id);
		
		
		
		JLabel lblOgrenciNo = new JLabel("Ogrenci no:");
		lblOgrenciNo.setBounds(460, 245, 110, 14);
		contentPane.add(lblOgrenciNo);
		
		JButton Search = new JButton("Ara...");
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search ara = new Search();
				ara.setVisible(true);
			}
		});
		Search.setBounds(564, 5, 117, 25);
		contentPane.add(Search);
		
	
		
		
		
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
	private void combo_ders_Id_doldur(JComboBox cmb, String tur) {
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
	
	private void combo_ogrenci_Id_doldur(JComboBox cmb, String tur) {
		try {
			String url="jdbc:mysql://"+this.host+":"+this.port+"/"+this.db;
	    	   Class.forName ("com.mysql.jdbc.Driver");
				this.con=DriverManager.getConnection(url,userName,password);
				java.sql.Statement st=con.createStatement();
				
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