package Logic;

import java.util.Date;

public class Ticket {

    private double ticketPrice;
    private double numberOfZones;
    private String ticketName;

    //Ticket bilelt = new TicketType("billetNavn", 25);
    public TicketType TT;

    public Ticket(String ticketName, int zonePrice, int numberOfZones) {
        this.ticketName = ticketName;
        this.numberOfZones = numberOfZones;
        ticketPrice = numberOfZones * zonePrice;
    }

    public Ticket(TicketType TT, int zonePrice, int Numberofzoens) {
        this(TT.getTicketName(), zonePrice, Numberofzoens);
        this.TT = TT;

    }

    public String ticketToString() {
        String ticketString = this.ticketName + "   Zoner: " + (int) this.numberOfZones + "   Pris: " + getTicketPrice() + "kr ";
        return ticketString;
    }

    public void printTicket() {
        System.out.println("\n##############################");
        System.out.println(ticketName + "\n" + (int) this.numberOfZones + " Zone(s)" + "\n" + new Date() + "\nValid for 2 hours");
        System.out.println("##############################\n");
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public double getAntalZoner() {
        return numberOfZones;
    }

    public void setNumberOfZones(int numberOfZones) {
        this.numberOfZones = numberOfZones;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

}
