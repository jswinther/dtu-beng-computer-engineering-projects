/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonat
 */
public class TicketmachineTest {
    
    public TicketmachineTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isInt method, of class Ticketmachine.
     */
    @Test
    public void testIsInt() {
        System.out.println("isInt");
        String input = "Not a number";
        Ticketmachine instance = new Ticketmachine();
        boolean expResult = false;
        boolean result = instance.isInt(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of addTicketType method, of class Ticketmachine.
     */
    @Test
    public void testAddTicketType() {
        System.out.println("addTicketType");
        String name = "TicketName";
        int zonePris = 0;
        Ticketmachine instance = new Ticketmachine();
        instance.addTicketType(name, zonePris);
        assertEquals(6, instance.getArrayListTicketTypeList().size());
    }

    /**
     * Test of removeTicketType method, of class Ticketmachine.
     */
    @Test
    public void testRemoveTicketType() {
        System.out.println("removeTicketType");
        int selectedIndex = 0;
        Ticketmachine instance = new Ticketmachine();
        instance.removeTicketType(selectedIndex);
        assertEquals(4, instance.getArrayListTicketTypeList().size());
    }

    /**
     * Test of setZonePris method, of class Ticketmachine.
     */
    @Test
    public void testSetZonePris() {
        System.out.println("setZonePris");
        int TicketType = 0;
        int ZonePris = 0;
        Ticketmachine instance = new Ticketmachine();
        instance.setZonePris(TicketType, ZonePris);
        assertEquals(0, instance.getArrayListTicketTypeList().get(0).getZonePrice());
    }

    /**
     * Test of setTicketName method, of class Ticketmachine.
     */
    @Test
    public void testSetTicketName() {
        System.out.println("setTicketName");
        int TicketType = 0;
        String TicketName = "Ticketname";
        Ticketmachine instance = new Ticketmachine();
        instance.setTicketName(TicketType, TicketName);
        assertEquals("Ticketname", instance.getArrayListTicketTypeList().get(TicketType).getTicketName());
    }

    /**
     * Test of getTicketName method, of class Ticketmachine.
     */
    @Test
    public void testGetTicketName() {
        System.out.println("getTicketName");
        int TicketType = 0;
        Ticketmachine instance = new Ticketmachine();
        String expResult = "Child 0 - 12";
        String result = instance.getTicketName(TicketType);
        assertEquals(expResult, result);
    }

    /**
     * Test of getZonePris method, of class Ticketmachine.
     */
    @Test
    public void testGetZonePris() {
        System.out.println("getZonePris");
        int TicketType = 0;
        Ticketmachine instance = new Ticketmachine();
        double expResult = 10;
        double result = instance.getZonePris(TicketType);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of addTicketToTicketList method, of class Ticketmachine.
     */
    @Test
    public void testAddTicketToTicketList() {
        System.out.println("addTicketToTicketList");
        int ticketTypeIndex = 0;
        int zoneNumberIndex = 0;
        Ticketmachine instance = new Ticketmachine();
        instance.addTicketToTicketList(ticketTypeIndex, zoneNumberIndex);
        assertEquals(1, instance.getArrayListTicketList().size());
    }

    /**
     * Test of removeTicketFromTicketList method, of class Ticketmachine.
     */
    @Test
    public void testRemoveTicketFromTicketList() {
        System.out.println("removeTicketFromTicketList");
        int ticketTypeIndex = 0;
        int zoneNumberIndex = 0;
        int ticketListIndex = 0;
        Ticketmachine instance = new Ticketmachine();
        instance.addTicketToTicketList(ticketTypeIndex, zoneNumberIndex);
        instance.removeTicketFromTicketList(ticketListIndex);
        assertEquals(0, instance.getArrayListTicketList().size());
    }

    /**
     * Test of removeAllTicketsFromTicketList method, of class Ticketmachine.
     */
    @Test
    public void testRemoveAllTicketsFromTicketList() {
        System.out.println("removeAllTicketsFromTicketList");
        Ticketmachine instance = new Ticketmachine();
        instance.removeAllTicketsFromTicketList();
        assertEquals(0, instance.getArrayListTicketList().size());
    }

    /**
     * Test of getTicketPrice method, of class Ticketmachine.
     */
    @Test
    public void testGetTicketPrice() {
        System.out.println("getTicketPrice");
        int ticketTypeIndex = 0;
        int zoneNumberIndex = 0;
        Ticketmachine instance = new Ticketmachine();
        double expResult = 10;
        double result = instance.getTicketPrice(ticketTypeIndex, zoneNumberIndex);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTicketListPrice method, of class Ticketmachine.
     */
    @Test
    public void testGetTicketListPrice() {
        System.out.println("getTicketListPrice");
        Ticketmachine instance = new Ticketmachine();
        double expResult = 0.0;
        double result = instance.getTicketListPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of payWithBalance method, of class Ticketmachine.
     */
    @Test
    public void testPayWithBalance() {
        System.out.println("payWithBalance");
        Ticketmachine instance = new Ticketmachine();
        instance.payWithBalance();
        assertEquals(0, instance.getTotalMachine(), 0.0);
    }

    /**
     * Test of payWithCard method, of class Ticketmachine.
     */
    @Test
    public void testPayWithCard() {
        System.out.println("payWithCard");
        Ticketmachine instance = new Ticketmachine();
        instance.payWithCard();
        assertEquals(0, instance.getTotalMachine(), 0.0);
    }

    /**
     * Test of addToBalance method, of class Ticketmachine.
     */
    @Test
    public void testAddToBalance() {
        System.out.println("addToBalance");
        double plusbalance = 1.0;
        Ticketmachine instance = new Ticketmachine();
        instance.addToBalance(plusbalance);
        assertEquals(1, instance.getBalance(), 0.0);
    }

    /**
     * Test of subtractFromBalance method, of class Ticketmachine.
     */
    @Test
    public void testSubtractFromBalance() {
        System.out.println("subtractFromBalance");
        double minusbalance = 1.0;
        Ticketmachine instance = new Ticketmachine();
        instance.addToBalance(minusbalance);
        instance.subtractFromBalance(minusbalance);
        assertEquals(0, instance.getBalance(), 0.0);
    }

    /**
     * Test of refund method, of class Ticketmachine.
     */
    @Test
    public void testRefund() {
        System.out.println("refund");
        Ticketmachine instance = new Ticketmachine();
        instance.addToBalance(500);
        instance.refund();
        assertEquals(0, instance.getBalance(), 0.0);
    }

    /**
     * Test of getArrayListTicketTypeList method, of class Ticketmachine.
     */
    @Test
    public void testGetArrayListTicketTypeList() {
        System.out.println("getArrayListTicketTypeList");
        Ticketmachine instance = new Ticketmachine();
        assertEquals(instance.TicketTypeList, instance.getArrayListTicketTypeList());
    }

    /**
     * Test of getDefaultListModelTicketTypeList method, of class Ticketmachine.
     */
    @Test
    public void testGetDefaultListModelTicketTypeList() {
        System.out.println("getDefaultListModelTicketTypeList");
        Ticketmachine instance = new Ticketmachine();
        DefaultListModel<String> expResult = new DefaultListModel<>();
        expResult.addElement(instance.getArrayListTicketTypeList().get(0).getTicketString());
        expResult.addElement(instance.getArrayListTicketTypeList().get(1).getTicketString());
        expResult.addElement(instance.getArrayListTicketTypeList().get(2).getTicketString());
        expResult.addElement(instance.getArrayListTicketTypeList().get(3).getTicketString());
        expResult.addElement(instance.getArrayListTicketTypeList().get(4).getTicketString());
        DefaultListModel<String> result = instance.getDefaultListModelTicketTypeList();
        assertEquals(expResult.get(0), result.get(0));
    }

    /**
     * Test of getDefaultComboBoxModelTicketTypeList method, of class Ticketmachine.
     */
    @Test
    public void testGetDefaultComboBoxModelTicketTypeList() {
        System.out.println("getDefaultComboBoxModelTicketTypeList");
        Ticketmachine instance = new Ticketmachine();
        DefaultComboBoxModel<String> expResult = new DefaultComboBoxModel<>();
        expResult.addElement(instance.getArrayListTicketTypeList().get(0).getTicketString());
        expResult.addElement(instance.getArrayListTicketTypeList().get(1).getTicketString());
        expResult.addElement(instance.getArrayListTicketTypeList().get(2).getTicketString());
        expResult.addElement(instance.getArrayListTicketTypeList().get(3).getTicketString());
        expResult.addElement(instance.getArrayListTicketTypeList().get(4).getTicketString());
        DefaultComboBoxModel<String> result = instance.getDefaultComboBoxModelTicketTypeList();
        assertEquals(expResult.getElementAt(0), result.getElementAt(0));
    }

    /**
     * Test of getArrayListTicketList method, of class Ticketmachine.
     */
    @Test
    public void testGetArrayListTicketList() {
        System.out.println("getArrayListTicketList");
        Ticketmachine instance = new Ticketmachine();
        instance.addTicketToTicketList(0, 1);
        int result = instance.getArrayListTicketList().size();
        assertEquals(1, result);
    }

    /**
     * Test of getPassword method, of class Ticketmachine.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Ticketmachine instance = new Ticketmachine();
        String expResult = "1234";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class Ticketmachine.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "4321";
        Ticketmachine instance = new Ticketmachine();
        instance.setPassword(password);
        assertEquals(password, instance.getPassword());
    }

    /**
     * Test of enableAdmin method, of class Ticketmachine.
     */
    @Test
    public void testEnableAdmin() {
        System.out.println("enableAdmin");
        String password = "1234";
        Ticketmachine instance = new Ticketmachine();
        instance.enableAdmin(password);
        assertEquals(true, instance.isAdminState());
    }

    /**
     * Test of disableAdmin method, of class Ticketmachine.
     */
    @Test
    public void testDisableAdmin() {
        System.out.println("disableAdmin");
        Ticketmachine instance = new Ticketmachine();
        instance.disableAdmin();
        assertEquals(false, instance.isAdminState());
    }

    /**
     * Test of isAdminState method, of class Ticketmachine.
     */
    @Test
    public void testIsAdminState() {
        System.out.println("isAdminState");
        Ticketmachine instance = new Ticketmachine();
        boolean expResult = false;
        boolean result = instance.isAdminState();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAdminState method, of class Ticketmachine.
     */
    @Test
    public void testSetAdminState() {
        System.out.println("setAdminState");
        boolean adminState = false;
        Ticketmachine instance = new Ticketmachine();
        instance.setAdminState(adminState);
        assertEquals(false, instance.isAdminState());
    }

    /**
     * Test of resetBalance method, of class Ticketmachine.
     */
    @Test
    public void testResetBalance() {
        System.out.println("resetBalance");
        Ticketmachine instance = new Ticketmachine();
        instance.resetBalance();
        assertEquals(0, instance.getBalance(), 0.0);
    }

    /**
     * Test of resetTotalMachine method, of class Ticketmachine.
     */
    @Test
    public void testResetTotalMachine() {
        System.out.println("resetTotalMachine");
        Ticketmachine instance = new Ticketmachine();
        instance.resetTotalMachine();
        assertEquals(0, instance.getTotalMachine(), 0.0);
    }

    /**
     * Test of resetTicketsSold method, of class Ticketmachine.
     */
    @Test
    public void testResetTicketsSold() {
        System.out.println("resetTicketsSold");
        Ticketmachine instance = new Ticketmachine();
        instance.resetTicketsSold();
        assertEquals(0, instance.TicketTypeListAddedToTicketListCounter.get(0).intValue());
    }

    /**
     * Test of getBalance method, of class Ticketmachine.
     */
    @Test
    public void testGetBalance() {
        System.out.println("getBalance");
        Ticketmachine instance = new Ticketmachine();
        double expResult = 0.0;
        double result = instance.getBalance();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTotalMachine method, of class Ticketmachine.
     */
    @Test
    public void testGetTotalMachine() {
        System.out.println("getTotalMachine");
        Ticketmachine instance = new Ticketmachine();
        double expResult = 0.0;
        double result = instance.getTotalMachine();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTicketsSold method, of class Ticketmachine.
     */
    @Test
    public void testGetTicketsSold() {
        System.out.println("getTicketsSold");
        Ticketmachine instance = new Ticketmachine();
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(0);
        expResult.add(0);
        expResult.add(0);
        expResult.add(0);
        expResult.add(0);
        ArrayList result = instance.getTicketsSold();
        assertEquals(expResult.size(), result.size());
    }
    
}
