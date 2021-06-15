package Logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Event {

    private final String event;
    private double Double;
    private final Date date;

    public Event(String action) {
        this.date = new Date();
        this.event = action + " " + date + "\n";
        writeToFile(this.event);
    }

    public Event(String action, double Double) {
        this.date = new Date();
        this.Double = Double;
        this.event = action + " " + date + "\n";
        writeToFile(this.event);
    }

    public String getEvent() {
        return event;
    }

    public double getDouble() {
        return Double;
    }

    public Date getDate() {
        return date;
    }

    /**
     * Fundet på nettet, men funktionaliteten af den er forstået.
     *
     * @param action
     */
    public void writeToFile(String action) {
        String str = action;
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("EventLog.txt", true));
            writer.append(' ');
            writer.append(str);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
