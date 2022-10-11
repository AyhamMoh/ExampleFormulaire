package edu.ayham.exampleformulaire;

import javax.swing.*;
import java.awt.*;

public class HelperForm {

    private HelperForm() {
    }

    public static final int ALIGN_RIGHT = 1;
    public static final int ALIGN_LEFT = 2;

    public static Box generateField(String textLabel, Component component){
        Box champs = Box.createHorizontalBox();
        champs.setBorder(BorderFactory.createLineBorder(Color.RED,5));
        champs.add(Box.createRigidArea(new Dimension(10, 1)));
        JLabel label = new JLabel(textLabel);
        label.setPreferredSize(new Dimension(120, 30));
        label.setBorder(BorderFactory.createLineBorder(Color.GREEN,5));
        champs.add(label);
        champs.add(Box.createRigidArea(new Dimension(10, 1)));
        champs.add(component);
        champs.add(Box.createHorizontalGlue());

        return champs;
    };

    public static Box generateRow(
            JComponent component,
            int marginTop,
            int marginRight,
            int marginBottom,
            int marginLeft,
            int alignement
    ) {

        Box contenurVerticale = Box.createVerticalBox();
        //ajout de la marge verticale en heateur
        contenurVerticale.add(Box.createRigidArea(new Dimension(1, marginTop)));
        contenurVerticale.setBorder(BorderFactory.createLineBorder(Color.WHITE,5));

        Box contenurHorizontal = Box.createHorizontalBox();
        contenurVerticale.add(contenurHorizontal);

        //ajout de la marge verticale en bas
        contenurVerticale.add(Box.createRigidArea(new Dimension(1, marginBottom)));

        //ajout de la marge horizontal à gauche
        contenurHorizontal.add(Box.createRigidArea(new Dimension(marginLeft, 1)));
        contenurHorizontal.setBorder(BorderFactory.createLineBorder(Color.YELLOW,5));
        if (alignement == ALIGN_RIGHT) {
            contenurHorizontal.add(Box.createHorizontalGlue());
        }
        contenurHorizontal.add(component);
        //ajout de la marge horizontal à droite
        contenurHorizontal.add(Box.createRigidArea(new Dimension(marginRight, 1)));

        contenurHorizontal.add(component);
        return contenurVerticale;
    }


}
