package macropad;

import com.rngtng.launchpad.Launchpad;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matt
 */
public class main {
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.print("Could not set look and feel");
        }
        //</editor-fold>
        
        Launchpad device = new Launchpad();
        MacroPad pad = new MacroPad(device);
        
        
        
        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(new Runnable() {
        //    public void run() {
        //        
        //    }
        //});
    }
}
