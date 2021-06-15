package GUIAdminPanels;

import Logic.Event;
import Logic.Ticketmachine;
import Main.Main;
import java.util.ArrayList;

public class EventListPanel extends JTemplates.PanelWithSetup {

    public ArrayList<Event> EventListContainer = new ArrayList<>();
    
    /**
     * Creates new form EventListPanel
     */
    public EventListPanel() {
        initComponents();
        buttonGroup1.add(radioString);
        buttonGroup1.add(radioBelow);
        buttonGroup1.add(radioAbove);
        radioString.setSelected(true);

    }


    @Override
    public void setup(Ticketmachine TM, Main main) {
        this.TM = TM;
        this.m = main;
        eventListList.setModel(TM.getDefaultListModelAllEvents());
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        adminEventsButton = new javax.swing.JButton();
        balanceEventsButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        eventListList = new javax.swing.JList<>();
        allEventsButton = new javax.swing.JButton();
        searchTextField = new javax.swing.JTextField();
        searchLabel = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        radioBelow = new javax.swing.JRadioButton();
        radioString = new javax.swing.JRadioButton();
        radioAbove = new javax.swing.JRadioButton();

        adminEventsButton.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        adminEventsButton.setText("Admin Events");
        adminEventsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminEventsButtonActionPerformed(evt);
            }
        });

        balanceEventsButton.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        balanceEventsButton.setText("Balance Events");
        balanceEventsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceEventsButtonActionPerformed(evt);
            }
        });

        eventListList.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jScrollPane2.setViewportView(eventListList);

        allEventsButton.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        allEventsButton.setText("All Events");
        allEventsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allEventsButtonActionPerformed(evt);
            }
        });

        searchTextField.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        searchLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        searchLabel.setText("Seach:");

        searchButton.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        radioBelow.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        radioBelow.setText("Int below");

        radioString.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        radioString.setText("String");
        radioString.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioStringActionPerformed(evt);
            }
        });

        radioAbove.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        radioAbove.setText("Int above");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(allEventsButton)
                            .addComponent(adminEventsButton))
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radioString)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioBelow)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioAbove)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchButton))))
                    .addComponent(balanceEventsButton))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(allEventsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminEventsButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchLabel)
                            .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioString)
                            .addComponent(radioBelow)
                            .addComponent(radioAbove)
                            .addComponent(searchButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(balanceEventsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void adminEventsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminEventsButtonActionPerformed
        eventListList.setModel(TM.getDefaultListModelAdminEvents());
        EventListContainer = TM.getAdminEvents();
    }//GEN-LAST:event_adminEventsButtonActionPerformed

    private void balanceEventsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceEventsButtonActionPerformed
        eventListList.setModel(TM.getDefaultListModelBalanceChangesEvents());
        EventListContainer = TM.getBalanceChangesEvents();
    }//GEN-LAST:event_balanceEventsButtonActionPerformed

    private void allEventsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allEventsButtonActionPerformed
        eventListList.setModel(TM.getDefaultListModelAllEvents());
        EventListContainer = TM.getAllEvents();
    }//GEN-LAST:event_allEventsButtonActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if (radioString.isSelected() && searchTextField.getText()!=null) {
            eventListList.setModel(TM.searchStringInEvents(searchTextField.getText(), EventListContainer));
        } else if(radioBelow.isSelected() && searchTextField.getText()!=null) {
            eventListList.setModel(TM.searchNumberBelowInEvents(Double.valueOf(searchTextField.getText()), EventListContainer));
        } else if(radioAbove.isSelected() && searchTextField.getText()!=null) {
            eventListList.setModel(TM.searchNumberAboveInEvents(Double.valueOf(searchTextField.getText()), EventListContainer));
        }
        m.update();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void radioStringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioStringActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioStringActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminEventsButton;
    private javax.swing.JButton allEventsButton;
    private javax.swing.JButton balanceEventsButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JList<String> eventListList;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton radioAbove;
    private javax.swing.JRadioButton radioBelow;
    private javax.swing.JRadioButton radioString;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables
}
