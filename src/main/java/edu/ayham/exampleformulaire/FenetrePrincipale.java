package edu.ayham.exampleformulaire;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import edu.ayham.exampleformulaire.models.Pays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FenetrePrincipale extends JFrame implements WindowListener { //1

    protected boolean themeSombreActif = true; //11
    protected int defaultMargin = 10;


    public FenetrePrincipale() {
        setSize(500, 500); //4
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//5
        addWindowListener(this);


        //------- ajout du panneau pricipale avec 5 zones
        // (North, South, East, West, Center):

        JPanel panneau = new JPanel(new BorderLayout()); //7
        setContentPane(panneau); //8

        /*Box sideMenu = Box.createVerticalBox();
        sideMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        panneau.add(sideMenu,BorderLayout.WEST);*/


        //-------Bouton Theme-------

        JButton boutonTheme = new JButton("Changer le theme"); //9

        boutonTheme.addActionListener(e -> { //12
            try {
                if (themeSombreActif) {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                } else {
                    UIManager.setLookAndFeel(new FlatDarculaLaf());
                }
                SwingUtilities.updateComponentTreeUI(this);
                themeSombreActif = !themeSombreActif;

            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }

        });


        //-------Bouton Valider Formulaire-------

        JButton boutonValider = new JButton("Enregisrer");
        boutonValider.addActionListener(e -> {
            System.out.println("Formulaire validé");
        });
        boutonValider.setSize(new Dimension(100, 30));

        //---------- Disposition Des Composants ---------------

        /*Box conteneurZoneBoutonOption = Box.createVerticalBox();
        panneau.add(conteneurZoneBoutonOption, BorderLayout.NORTH);

        conteneurZoneBoutonOption.add(
                Box.createRigidArea(new Dimension(1,defaultMargin)));
        Box zoneBoutonOption = Box.createHorizontalBox();
        conteneurZoneBoutonOption.add(zoneBoutonOption);

        zoneBoutonOption.add(Box.createHorizontalGlue());
        zoneBoutonOption.add(boutonTheme);
        zoneBoutonOption.add(Box.createRigidArea(
                new Dimension(defaultMargin,1)));

        Box containerZoneBoutonAction = Box.createVerticalBox();
        panneau.add(containerZoneBoutonAction, BorderLayout.SOUTH);

        Box zoneBoutonAction = Box.createHorizontalBox();
        containerZoneBoutonAction.add(zoneBoutonAction);
        containerZoneBoutonAction.add(
                Box.createRigidArea(new Dimension(1,defaultMargin)));

        zoneBoutonAction.add(Box.createHorizontalGlue());
        zoneBoutonAction.add(boutonValider);
        zoneBoutonAction.add(Box.createRigidArea(
                new Dimension(defaultMargin,1)));*/


        //------- Bouton de haut
        panneau.add(HelperForm.generateRow(boutonTheme, 50, 50, 0, 0, HelperForm.ALIGN_RIGHT), BorderLayout.NORTH);

        //------- Bouton de bas
        panneau.add(HelperForm.generateRow(boutonValider, 0, 50, 50, 0, HelperForm.ALIGN_RIGHT), BorderLayout.SOUTH);


        //----Formulaire-----
       /* Box formulaire = Box.createVerticalBox();
        formulaire.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        panneau.add(formulaire, BorderLayout.CENTER);


        Box champs = Box.createHorizontalBox();
        champs.setBorder(BorderFactory.createLineBorder(Color.RED));

        formulaire.add(champs);
        champs.add(Box.createRigidArea(new Dimension(defaultMargin, 1)));
        JLabel label = new JLabel("Civilité");
        label.setPreferredSize(new Dimension(120, 30));
        label.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        champs.add(label);
        champs.add(Box.createRigidArea(new Dimension(defaultMargin, 1)));

        String[] listeCivilites = {"Monsieur", "Madame", "Mademosille", "Autre"};
        JComboBox<String> selectCivilite = new JComboBox<>(listeCivilites);
        selectCivilite.setMaximumSize(new Dimension(200,30));
        selectCivilite.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        champs.add(selectCivilite);
        champs.add(Box.createHorizontalGlue());*/

        Box formulaire = Box.createVerticalBox();
        formulaire.setBorder(BorderFactory.createLineBorder(Color.BLUE,5));
        panneau.add(formulaire, BorderLayout.CENTER);

        //----Liste civilite -----

        String[] listeCivilites = {"Monsieur", "Madame", "Mademosille", "Autre"};
        JComboBox<String> selectCivilite = new JComboBox<>(listeCivilites);
        selectCivilite.setMaximumSize(new Dimension(200,30));

        formulaire.add(HelperForm.generateField("Civilité",selectCivilite));

        //---- Liste pays -----


        Pays[] listePays = {
                new Pays("France", "FR", "fr.png"),
                new Pays("Royaume-unis", "GBR", "gb.png"),
                new Pays("Allemagne", "DE", "de.png")
        };

        JComboBox<Pays> selectPays = new JComboBox<>(listePays);
        selectPays.setMaximumSize(new Dimension(300,30));
        formulaire.add(HelperForm.generateField("Pays",selectPays));
selectPays.setRenderer(new DefaultListCellRenderer(){
    @Override // getListCellRendererComponent نكتب
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Pays pays = (Pays)value;
       setText(pays.getNom());

      

        return this;
    }
});




       //-------
        setVisible(true); //6
    }

    public static void main(String[] args) { //2
        FlatDarculaLaf.setup();
        new FenetrePrincipale();//3

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        String[] choix = {"Oui", "Ne pas fermer l'application"};
        int choixUtilisateur = JOptionPane.showOptionDialog(
                this,
                "Voulez - vous vraiment fermer l'application",
                "Confirmer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null,
                choix,
                choix[1]);

        if (choixUtilisateur == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
