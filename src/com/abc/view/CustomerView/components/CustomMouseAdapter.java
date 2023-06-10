package com.abc.view.CustomerView.components;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author LahiruCW
 */
public class CustomMouseAdapter extends MouseAdapter{
    
    private final JLabel label;
    
    public CustomMouseAdapter(JLabel label){
        this.label = label;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        label.setBackground(new Color(244,242,242));
        label.setForeground(Color.BLACK);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        label.setBackground(new Color( 9, 120, 211 ));
        label.setForeground(Color.WHITE);
    } 
}
