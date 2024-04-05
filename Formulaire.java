package info_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

class Formulaire extends JFrame {
	private JPanel panel ;
	JTextField NomField , PrenomField , ageField , adreesField ;
	
	
	
	private static final long serialVersionUID = 1L;
	
	public Formulaire() {
		this.setTitle("Formulaire");
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new JPanel(new  BorderLayout()) {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
			}
		};
		
		//// parie de haut  (header )
		JPanel panel_header = new JPanel( new FlowLayout(FlowLayout.CENTER , 0,20)) {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
			}
		};
		
		JLabel text_header = new JLabel("Client info");
		text_header.setFont(new Font( "Arial", Font.BOLD, 24));
		panel_header.add(text_header);
		 ////parie  de main (body )
		
		JPanel panel_body = new JPanel( new GridLayout(4,2 ,20,10)) {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
			}
		};
		
		JLabel nomLabel = new JLabel("Nom: ");
		nomLabel.setBorder(BorderFactory.createEmptyBorder(0,100 , 0,0));
        JLabel prenomLabel = new JLabel("Prénom: ");
        prenomLabel.setBorder(BorderFactory.createEmptyBorder(0,100 , 0,0));
        JLabel ageLabel = new JLabel("Âge: ");
        ageLabel.setBorder(BorderFactory.createEmptyBorder(0,100 , 0,0));
        JLabel adresseLabel = new JLabel("Adresse: ");
        adresseLabel.setBorder(BorderFactory.createEmptyBorder(0,100 , 0,0));
        
        nomLabel.setFont(new Font("Arial", Font.BOLD, 15));
        prenomLabel.setFont(new Font("Arial", Font.BOLD, 15));
        ageLabel.setFont(new Font("Arial", Font.BOLD, 15));
        adresseLabel.setFont(new Font("Arial", Font.BOLD, 15));
		NomField = new JTextField(20);
		PrenomField = new JTextField(20);
		ageField = new JTextField(5);
		adreesField = new JTextField(30);
		 
		panel_body.add(nomLabel);
		panel_body.add(NomField);
		panel_body.add(prenomLabel);
		panel_body.add(PrenomField);
		panel_body.add(ageLabel);
		panel_body.add(ageField);
		panel_body.add(adresseLabel);
		panel_body.add(adreesField);
		
		
		
		
		 ////parie  en bas (footer )
		
		JPanel panel_footer = new JPanel( new GridLayout(2,1 , 20 ,0)) {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
			}
		};
		JPanel panel_footer_top = new JPanel( new GridLayout(1,2 , 20 ,0)) {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
			}
		};
		
		JButton envoyer = new JButton("Envoyes") ;
		envoyer.setPreferredSize(new Dimension(50, 50));
		JButton annuler = new JButton("Annuler")  ;
		annuler.setPreferredSize(new Dimension(50, 50));
		panel_footer_top.add(envoyer);
		panel_footer_top.add(annuler);
		panel_footer.add(panel_footer_top);
		JLabel finLabel = new JLabel("Fait par SAAD et OMAR", SwingConstants.CENTER);
		finLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel_footer.add(finLabel);
		
		
		// panel 
        panel_body.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        panel_footer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

		panel.add(panel_header , BorderLayout.NORTH);
		panel.add(panel_body , BorderLayout.CENTER);
		panel.add(panel_footer,BorderLayout.SOUTH);
		
		
		// add eventes 
		envoyer.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (NomField.getText().isEmpty()) {
		        	JOptionPane.showMessageDialog(null, "Champ nom est vide, requis !", "worning", JOptionPane.INFORMATION_MESSAGE);
		        } else if (!NomField.getText().matches("[a-zA-Z]+")) {
		        	JOptionPane.showMessageDialog(null, "Nom invalide, veuillez saisir uniquement des lettres !", "worning", JOptionPane.INFORMATION_MESSAGE);
		        } else if (PrenomField.getText().isEmpty()) {
		        	JOptionPane.showMessageDialog(null, "Champ prénom est vide, requis !", "worning", JOptionPane.INFORMATION_MESSAGE);
		        } else if (!PrenomField.getText().matches("[a-zA-Z]+")) {
		        	JOptionPane.showMessageDialog(null,"Prénom invalide, veuillez saisir uniquement des lettres !", "worning", JOptionPane.INFORMATION_MESSAGE);
		        } else if (ageField.getText().isEmpty()) {
		        	JOptionPane.showMessageDialog(null,"Champ âge est vide, requis !", "worning", JOptionPane.INFORMATION_MESSAGE);
		        } else if (!ageField.getText().matches("\\d+")) {
		        	JOptionPane.showMessageDialog(null,"Âge invalide, veuillez saisir un nombre valide !", "worning", JOptionPane.INFORMATION_MESSAGE);
		        } else if (adreesField.getText().isEmpty()) {
		        	JOptionPane.showMessageDialog(null,"Champ adresse est vide, requis !", "worning", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		        	// Connexion à la base de données
		        	 String user ="root";
		        	 String password ="root";
		        	 String url ="jdbc:mysql://localhost:8889/info_clients";
		        	Connection Myconnection = null ;
		        	try {
		    			Myconnection = DriverManager.getConnection(url, user, password);
		    		} catch (SQLException e1) {
		    			System.out.println("probleme de conexion");
		    		}
		        	
		        	String requite="INSERT INTO info_client ( nom , prenom ,age , adresse ) VALUES(?,?,?,?) " ;
		        	try {
						PreparedStatement pr = Myconnection.prepareStatement(requite);
						pr.setString(1, NomField.getText());
						pr.setString(2, PrenomField.getText());
						pr.setString(3, ageField.getText());
						pr.setString(4, adreesField.getText());
						pr.executeUpdate();
			        	JOptionPane.showMessageDialog(null,"ajouter dons la basse des donnees avec resu", "worning", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	}
		    }
		});
		
		
		annuler.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	NomField.setText("");
		        PrenomField.setText("");
		        ageField.setText("");
		        adreesField.setText("");

		    }
		});

		
		this.add(panel);
		this.setVisible(true);
	}
	
   
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Formulaire f = new Formulaire();
	}
	
}