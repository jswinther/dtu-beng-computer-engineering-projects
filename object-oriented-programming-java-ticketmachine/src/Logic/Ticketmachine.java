package Logic;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

public final class Ticketmachine extends JPanel {

    private String password;
    private boolean adminState;
    private double totalMachine;
    private double balance;
    private double cartPrice;

    public ArrayList<TicketType> TicketTypeList = new ArrayList<>();
    public ArrayList<Ticket> TicketList = new ArrayList<>();
    public ArrayList<Integer> TicketTypeListSoldCounter = new ArrayList<>();
    public ArrayList<Integer> TicketTypeListAddedToTicketListCounter = new ArrayList<>();
    public ArrayList<ArrayList> Puchases = new ArrayList<>();
    
    public ArrayList<Event> AllEvents = new ArrayList<>();
    public ArrayList<Event> BalanceChangesEvents = new ArrayList<>();
    public ArrayList<Event> AdminEvents = new ArrayList<>();
   

    public Ticketmachine() {
        password = "1234";
        adminState = false;
        totalMachine = 0;
        balance = 0;
        cartPrice = 0;
        addTicketType("Child 0 - 12", 10);
        addTicketType("Teen 13 - 25", 15);
        addTicketType("Adult 26 - 60", 20);
        addTicketType("Senior 60 +", 17);
        addTicketType("Bike", 5);
    }
     
    /**
     * Checks if a given string can be parsed as an int.
     *
     * @param input
     * @return
     */
    public boolean isInt(String input) {
        boolean parsable = true;
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            parsable = false;
        }
        return parsable;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Add/Remove Ticket Types from TicketTypesList.">
    /**
     * Adds a tickettype to the arraylist.
     *
     * @param name Name of the ticket type.
     * @param zonePris Zone price of the ticket type.
     */
    public void addTicketType(String name, int zonePris) {
        TicketType TT = new TicketType(name, zonePris);
        TicketTypeList.add(TT);
        TicketTypeListSoldCounter.add(0);
        TicketTypeListAddedToTicketListCounter.add(0);
        addEvent("A Ticket Type was added, Name: " + TT.getTicketName() + " Price: " + TT.getZonePrice(), zonePris, AdminEvents);
    }

    /**
     * Removes a tickettype from the the arraylist.
     *
     * @param selectedIndex Index of the ticket type to be removed, from the
     * ticket type list.
     */
    public void removeTicketType(int selectedIndex) {
        if (selectedIndex != -1) {
            addEvent("A Ticket Type was removed, Name: " + getArrayListTicketTypeList().get(selectedIndex).getTicketName() + " Price: " + getArrayListTicketTypeList().get(selectedIndex).getZonePrice(), AdminEvents);
            getArrayListTicketTypeList().remove(selectedIndex);
            TicketTypeListSoldCounter.remove(selectedIndex);
            TicketTypeListAddedToTicketListCounter.remove(selectedIndex);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Edit Ticket Type.">
    /**
     * Sets the zonePrice of the TicketType at the specified index.
     *
     * @param TicketType index of the ticket type to be modified.
     * @param ZonePris the new zone price.
     */
    public void setZonePris(int TicketType, int ZonePris) {
        TicketTypeList.get(TicketType).setZonePrice(ZonePris);

    }
    /**
     * Sets the ticketName of the TicketType at the specified index.
     *
     * @param TicketType
     * @param TicketName
     */
    public void setTicketName(int TicketType, String TicketName) {
        TicketTypeList.get(TicketType).setTicketName(TicketName);
    }
    
    /**
     * Sets the ticketName and zonePrice of the TicketType at the specified index.
     * @param TicketType
     * @param TicketName
     * @param ZonePrice 
     */
    public void setTicketNameAndPrice(int TicketType, String TicketName, int ZonePrice) {
        setTicketName(TicketType, TicketName);
        setZonePris(TicketType, ZonePrice);
    }
    
    public String getTicketName(int TicketType) {
        return getArrayListTicketTypeList().get(TicketType).getTicketName();
    }
    
    public double getZonePris(int TicketType) {
        return getArrayListTicketTypeList().get(TicketType).getZonePrice();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Add/Remove Ticket from TicketList.">
    /**
     * Adds a ticket object to TicketList.
     *
     * @param ticketTypeIndex
     * @param zoneNumberIndex
     */
    public void addTicketToTicketList(int ticketTypeIndex, int zoneNumberIndex) {
        if (!TicketTypeList.isEmpty()) {
            Ticket T = new Ticket(TicketTypeList.get(ticketTypeIndex).getTicketName(), 
                    TicketTypeList.get(ticketTypeIndex).getZonePrice(), zoneNumberIndex);
            getArrayListTicketList().add(T);
            TicketTypeListAddedToTicketListCounter.set(ticketTypeIndex, 
                    TicketTypeListAddedToTicketListCounter.get(ticketTypeIndex) + 1);
        }
    }

    /**
     * Removes a ticket object from TicketList at a specified index.
     *
     * @param ticketListIndex
     */
    public void removeTicketFromTicketList(int ticketListIndex) {
        if (!getArrayListTicketList().isEmpty()) {
            for (TicketType ticketType : TicketTypeList) {
                if (getArrayListTicketList().get(ticketListIndex).getTicketName().equalsIgnoreCase(ticketType.getTicketName())) {
                    TicketTypeListAddedToTicketListCounter.set(TicketTypeList.indexOf(ticketType), 
                            TicketTypeListAddedToTicketListCounter.get(TicketTypeList.indexOf(ticketType)) - 1);
                    TicketList.remove(ticketListIndex);
                    return;
                }
            }
        }
    }

    /**
     * Sets the price of all the tickets in the TicketList.
     */
    public void removeAllTicketsFromTicketList() {
        this.TicketList.removeAll(this.TicketList);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Return TicketPrice/TicketListPrice">
    /**
     * Returns ticket price for a given ticket at a given index.
     *
     * @param ticketTypeIndex
     * @param zoneNumberIndex
     * @return
     */
    public double getTicketPrice(int ticketTypeIndex, int zoneNumberIndex) {
        if (!TicketTypeList.isEmpty()) {
            return new Ticket(TicketTypeList.get(ticketTypeIndex).getTicketName(), TicketTypeList.get(ticketTypeIndex).getZonePrice(), zoneNumberIndex + 1).getTicketPrice();
        }
        return 0;
    }

    /**
     * Returns the price of all the tickets in the TicketList.
     *
     * @return
     */
    public double getTicketListPrice() {
        cartPrice = 0;
        TicketList.forEach((Ticket Ticket) -> {
            cartPrice += Ticket.getTicketPrice();
        });
        return cartPrice;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Add TicketListPrice to Total.">
    public void payWithBalance() {
        if (!getArrayListTicketList().isEmpty()) {
            totalMachine = totalMachine + getTicketListPrice();
            addEvent("Puchase for: " + getTicketListPrice(), getTicketListPrice(), BalanceChangesEvents);
            subtractFromBalance(getTicketListPrice());
            for (int i = 0; i < TicketTypeListSoldCounter.size(); i++) {
                TicketTypeListSoldCounter.set(i, TicketTypeListSoldCounter.get(i) + TicketTypeListAddedToTicketListCounter.get(i));
            }
            
            getArrayListTicketList().forEach((arrayListTicketList) -> {
                arrayListTicketList.printTicket();
            });
            removeAllTicketsFromTicketList();
            resetTicketsSold();
        }

    }

    public void payWithCard() {
        if (!getArrayListTicketList().isEmpty()) {
            totalMachine = totalMachine + getTicketListPrice();
            addEvent("Puchase for: " + getTicketListPrice(), getTicketListPrice(), BalanceChangesEvents);
            for (int i = 0; i < TicketTypeList.size(); i++) {
                TicketTypeListSoldCounter.set(i, TicketTypeListSoldCounter.get(i) + TicketTypeListAddedToTicketListCounter.get(i));
            }
            getArrayListTicketList().forEach((arrayListTicketList) -> {
                arrayListTicketList.printTicket();
            });
            System.out.println("Printed: " + getArrayListTicketList().size() + " tickets.");
            removeAllTicketsFromTicketList();
            resetTicketsSold();
        }

    }
    /**
     * Adds an amount to balance.
     *
     * @param plusbalance
     */
    public void addToBalance(double plusbalance) {
        balance += plusbalance;
        addEvent(plusbalance + "added to balance, balance is now" + getBalance(), plusbalance, getBalanceChangesEvents());
    }

    /**
     * Subtracts an amount from balance.
     *
     * @param minusbalance
     */
    public void subtractFromBalance(double minusbalance) {
        balance = balance - minusbalance;
        addEvent(minusbalance + "subtracted from balance, balance is now" + getBalance(), minusbalance, getBalanceChangesEvents());
    }

    public void refund() {
        addEvent(getBalance() + "is refunded from balance", getBalance(), getBalanceChangesEvents());
        this.balance = this.balance - getBalance();

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get the list of Ticket Types.">
    public ArrayList<TicketType> getArrayListTicketTypeList() {
        return TicketTypeList;
    }

    public DefaultListModel<String> getDefaultListModelTicketTypeList() {
        DefaultListModel<String> DLM = new DefaultListModel<>();
        TicketTypeList.forEach((ticketType) -> {
            DLM.addElement(ticketType.getTicketString());
        });
        return DLM;
    }

    public DefaultComboBoxModel<String> getDefaultComboBoxModelTicketTypeList() {
        DefaultComboBoxModel<String> DCBM = new DefaultComboBoxModel<>();
        TicketTypeList.forEach((ticketType) -> {
            DCBM.addElement(ticketType.getTicketString());
        });
        return DCBM;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get the list of Tickets.">
    public ArrayList<Ticket> getArrayListTicketList() {
        return this.TicketList;
    }

    public DefaultListModel<String> getDefaultListModelTicketList() {
        DefaultListModel<String> DLM = new DefaultListModel<>();
        getArrayListTicketList().forEach((ticket) -> {
            DLM.addElement(ticket.ticketToString());
        });
        return DLM;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Add Events">
    public void addEvent(String action, ArrayList AL) {
        Event E = new Event(action);
        AL.add(E);
        getAllEvents().add(E);
    }

    public void addEvent(String action, double number, ArrayList AL) {
        Event E = new Event(action, number);
        AL.add(E);
        getAllEvents().add(E);
    }

    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get the list of Events">
    public ArrayList<Event> getAllEvents() {
        return AllEvents;
    }

    public ArrayList<Event> getBalanceChangesEvents() {
        return BalanceChangesEvents;
    }

    public ArrayList<Event> getAdminEvents() {
        return AdminEvents;
    }
    
    public DefaultListModel getDefaultListModelAllEvents() {
        DefaultListModel DLM = new DefaultListModel();
        if (!getAllEvents().isEmpty()) {
            for (int i = 0; i < getAllEvents().size(); i++) {
                DLM.addElement(getAllEvents().get(i).getEvent());
            }
        }
        return DLM;
    }

    public DefaultListModel getDefaultListModelBalanceChangesEvents() {
        DefaultListModel DLM = new DefaultListModel();
        if (!getBalanceChangesEvents().isEmpty()) {
            for (int i = 0; i < getBalanceChangesEvents().size(); i++) {
                DLM.addElement(getBalanceChangesEvents().get(i).getEvent());
            }
        }
        return DLM;
    }

    public DefaultListModel getDefaultListModelAdminEvents() {
        DefaultListModel DLM = new DefaultListModel();
        if (!getAdminEvents().isEmpty()) {
            for (int i = 0; i < getAdminEvents().size(); i++) {
                DLM.addElement(getAdminEvents().get(i).getEvent());
            }
        }
        return DLM;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Get an set Password.">
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    // </editor-fold>    
    
    // <editor-fold defaultstate="collapsed" desc="Get and set admin state.">
    /**
     * Enables administrator methods.
     *
     * @param password
     */
    public void enableAdmin(String password) {
        if (password.equals(this.password)) {
            setAdminState(true);
            addEvent("Admin is enabled", getAdminEvents());
        }
    }
    
    public void disableAdmin() {
        setAdminState(false);
        addEvent("Admin is disabled.", getAdminEvents());
    }
    
    public boolean isAdminState() {
        return adminState;
    }

    public void setAdminState(boolean adminState) {
        this.adminState = adminState;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Search">
    public DefaultListModel searchStringInEvents(String search, ArrayList<Event> AL) {
        DefaultListModel<String> listFromSearch = new DefaultListModel();
        for (int i = 0; i < AL.size(); i++) {
            if (AL.get(i).getEvent().toUpperCase().toLowerCase().contains(search.toUpperCase().toLowerCase())) {
                listFromSearch.addElement(AL.get(i).getEvent());
            }
        }
        return listFromSearch;
    }

    public DefaultListModel searchNumberBelowInEvents(double number, ArrayList<Event> AL) {
        DefaultListModel<String> listFromSearch = new DefaultListModel();
        for (int i = 0; i < AL.size(); i++) {
            if (AL.get(i).getDouble() <= number) {
                listFromSearch.addElement(AL.get(i).getEvent());
            }
        }
        return listFromSearch;
    }

    public DefaultListModel searchNumberAboveInEvents(double number, ArrayList<Event> AL) {
        DefaultListModel<String> listFromSearch = new DefaultListModel();
        for (int i = 0; i < AL.size(); i++) {
            if (AL.get(i).getDouble() >= number) {
                listFromSearch.addElement(AL.get(i).getEvent());
            }
        }
        return listFromSearch;
    }

    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Reset Total, Balance, Tickets Sold, Tickets Counter.">
    /**
     * Sets balance to 0.
     */
    public void resetBalance() {
        this.balance = 0;
    }

    /**
     * Sets totalMachine to 0.
     */
    public void resetTotalMachine() {
        this.totalMachine = 0;
    }

    /**
     * Sets TicketTypeListSoldCounter to 0.
     */
    public void resetTicketsSold() {
        for (int i = 0; i < TicketTypeListAddedToTicketListCounter.size(); i++) {
            TicketTypeListAddedToTicketListCounter.set(i, 0);
        }
    }
    
    /**
     * Resets all.
     */
    public void resetAll() {
        resetBalance();
        removeAllTicketsFromTicketList();
        resetTicketsSold();
        resetTotalMachine();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get Total, Balance, Tickets Sold">
    /**
     * Returns the balance.
     *
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns the amount of money that the ticketmachine contains.
     *
     * @return
     */
    public double getTotalMachine() {
        return totalMachine;
    }

    /**
     * Returns the amount of tickets sold.
     *
     * @return
     */
    public ArrayList getTicketsSold() {
        return TicketTypeListSoldCounter;
    }
    // </editor-fold>
    
    
}
