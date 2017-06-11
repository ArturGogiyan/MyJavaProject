
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by edvanchi on 21.02.2017.
 */
public class MTsTail extends Tail implements Serializable{
    static Parcer parcer=new Parcer();
    private ZonedDateTime zdt;
    private double h;
    private double s;
    private double v;
    private OffsetDateTime odt;
    public MTsTail(double h, double s,double v, int length) {
        this.h=h;
        this.s=s;
        this.v=v;
        this.setLength(length);
        odt=OffsetDateTime.now();
    }
    public MTsTail(double h, double s,double v, int length, OffsetDateTime date) {
        this.h = h;
        this.s = s;
        this.v = v;
        this.setLength(length);
        odt=date;
    }

    public MTsTail(String color, int length){
        this.setColor(color);
        this.setLength(length);
    }
    public OffsetDateTime getDate() {

        return odt;
    }

    public void setDate(OffsetDateTime date) {
        this.odt=date;
    }

    public double getH() {
        return h;
    }

    public double getS() {
        return s;
    }

    public double getV() {
        return v;
    }
    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Main.myResources.getString("TimeFormat"));
        System.out.println(odt.format(formatter));
        return (Main.myResources.getString("s10")+ parcer.parce(h,s,v) + ", "+Main.myResources.getString("s11") + getLength()
                + ", "+Main.myResources.getString("s40") +  getDate().format(formatter)+""+Main.myResources.getString("ZoneID"));
    }

}

