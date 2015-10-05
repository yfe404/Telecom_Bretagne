package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.naming.*;
import java.util.List;

import eu.telecom_bretagne.data.model.Personne;
import eu.telecom_bretagne.services.IPersonnelBeanRemote;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;

	public Main() {
		super("Swing Client");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		JButton b1 = new JButton("Access EJB");

		getContentPane().add(b1, BorderLayout.PAGE_END);

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String msg = "";
				// TODO nom de l'interface distante de l'EJB à appeler
				String jndiName = "java:global/gestpers3Tier-ear/gestpers3Tier-ejb/PersonnelBean!eu.telecom_bretagne.services.IPersonnelBeanRemote";
				try {
					// Accès au contexte initial JNDI
					InitialContext ctx = new InitialContext();

					// TODO Recherche de l'interface distante de l'EJB
					// que l'on souhaite accéder
					IPersonnelBeanRemote remote = (IPersonnelBeanRemote) ctx.lookup(jndiName);
					
					// TODO Appel à la méthode de l'EJB
					List<Personne> personnes = remote.annuaire("nom", "asc");
					
					// TODO Création du message avec la réponse de l'EJB
					msg = "Message From EJB --> " + personnes.toString();
					
				} catch (Exception ex) {
					msg = "Error --> " + ex.getCause().toString();
				}
				// Affichage de la réponse
				JOptionPane.showMessageDialog(Main.this, msg, "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		setSize(200, 200);
	} //

	public static void main(String args[]) {
		new Main().setVisible(true);
	}
}
