package GUIMain;

import Main.Main;
import JTemplates.PanelWithSetup;
import Logic.Ticketmachine;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class AdminPanel extends PanelWithSetup {


    
    @Override
    public void setup(Ticketmachine TM, Main main) {
        this.TM = TM;
        this.m = main;
        addTicketTypePanel1.setup(TM, main);
        removeTicketPanel1.setup(TM, main);
        setPasswordPanel1.setup(TM, main);
        eventListPanel1.setup(TM, main);
        update();
    }
    
    

    @Override
    public void update() {
        displayPasswordLabel.setText("Password: " + TM.getPassword());
        displayTicketsSoldLabel.setText("Tickets Sold: " + TM.getTicketsSold());
        displayTotalInMachineLabel.setText("Total in Machine: " + TM.getTotalMachine());
        displayTicketTypesLabel.setText("Number of ticket types: " + TM.getArrayListTicketTypeList().size());
        addTicketTypePanel1.update();
        removeTicketPanel1.update();
        setPasswordPanel1.update();
    }
    

    /**
     * Creates new form AdminPanel
     */
    public AdminPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        displayTicketsSoldLabel = new javax.swing.JLabel();
        displayTotalInMachineLabel = new javax.swing.JLabel();
        displayTicketTypesLabel = new javax.swing.JLabel();
        displayPasswordLabel = new javax.swing.JLabel();
        adminMainResetPasswordButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        addTicketTypePanel1 = new GUIAdminPanels.AddTicketTypePanel();
        removeTicketPanel1 = new GUIAdminPanels.RemoveTicketPanel();
        eventListPanel1 = new GUIAdminPanels.EventListPanel();
        setPasswordPanel1 = new GUIAdminPanels.SetPasswordPanel();

        displayTicketsSoldLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        displayTicketsSoldLabel.setText("Tickets Sold:");

        displayTotalInMachineLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        displayTotalInMachineLabel.setText("Total in Machine:");

        displayTicketTypesLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        displayTicketTypesLabel.setText("Ticket Types:");

        displayPasswordLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        displayPasswordLabel.setText("Password:");

        adminMainResetPasswordButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        adminMainResetPasswordButton.setText("Reset Password to 1234");
        adminMainResetPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminMainResetPasswordButtonActionPerformed(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTabbedPane1.addTab("Add Ticket Type", addTicketTypePanel1);
        jTabbedPane1.addTab("Remove Ticket Type", removeTicketPanel1);
        jTabbedPane1.addTab("Event List", eventListPanel1);
        jTabbedPane1.addTab("Set Password", setPasswordPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayTotalInMachineLabel)
                    .addComponent(displayTicketsSoldLabel)
                    .addComponent(displayTicketTypesLabel)
                    .addComponent(displayPasswordLabel)
                    .addComponent(adminMainResetPasswordButton))
                .addContainerGap(820, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1247, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(displayTicketsSoldLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayTotalInMachineLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(displayTicketTypesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayPasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminMainResetPasswordButton)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void adminMainResetPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminMainResetPasswordButtonActionPerformed
        TM.setPassword("1234");
        m.update();
    }//GEN-LAST:event_adminMainResetPasswordButtonActionPerformed

     @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // super fordi den arver fra JPanel class og paintComponent så den kan udskrive rigtigt på alle computere. 

        Graphics2D g2D = (Graphics2D) g;
       // AffineTransform original = g2.getTransform();
       g2D.setRenderingHint( // sæt tegnevink til trappeudjævning (antialias)
			RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2D.setColor(Color.BLUE);
        g2D.fillOval(1000, 50, 680, 235);

        g.setColor(Color.CYAN);
        Font font = g.getFont().deriveFont(52.0f);
        g.setFont(font);
        g.drawString("JOMANI AWAY", 1160, 180);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUIAdminPanels.AddTicketTypePanel addTicketTypePanel1;
    private javax.swing.JButton adminMainResetPasswordButton;
    private javax.swing.JLabel displayPasswordLabel;
    private javax.swing.JLabel displayTicketTypesLabel;
    private javax.swing.JLabel displayTicketsSoldLabel;
    private javax.swing.JLabel displayTotalInMachineLabel;
    private GUIAdminPanels.EventListPanel eventListPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private GUIAdminPanels.RemoveTicketPanel removeTicketPanel1;
    private GUIAdminPanels.SetPasswordPanel setPasswordPanel1;
    // End of variables declaration//GEN-END:variables
}
