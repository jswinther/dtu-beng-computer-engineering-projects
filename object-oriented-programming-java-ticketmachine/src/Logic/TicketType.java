package Logic;

public class TicketType {
    
    public String getTicketString() {
        return ticketName + " | " + zonePrice + " DKK";
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public int getZonePrice() {
        return zonePrice;
    }

    public void setZonePrice(int zonePrice) {
        this.zonePrice = zonePrice;
    }
    private String ticketName;
    private int zonePrice;
    
    public TicketType(String name, int zonePrice) {
        this.ticketName = name;
        this.zonePrice = zonePrice;
    }
    
    
}
