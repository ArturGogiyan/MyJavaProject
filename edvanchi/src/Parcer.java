/**
 * Created by edvanchi on 04.06.2017.
 */
public class Parcer {
    public String parce(double hue, double saturation, double brightness) {
        String s="";
        //Alert! bad code danger
        if (saturation<0.33)s = Main.myResources.getString("s13");
        else if (saturation>0.66) s= Main.myResources.getString("s14");
        if(brightness<0.33) s=s.concat(Main.myResources.getString("s15"));
        if(brightness>0.66) s=s.concat(Main.myResources.getString("s16"));
        if (hue < 20) s=s.concat(Main.myResources.getString("s17"));
        else if(hue<45) s=s.concat(Main.myResources.getString("s18"));
        else if(hue<70) s=s.concat(Main.myResources.getString("s19"));
        else if(hue<135) s=s.concat(Main.myResources.getString("s20"));
        else if(hue<210) s=s.concat(Main.myResources.getString("s21"));
        else if(hue<260) s=s.concat(Main.myResources.getString("s22"));
        else if(hue<290) s=s.concat(Main.myResources.getString("s23"));
        else if(hue<340) s=s.concat(Main.myResources.getString("s24"));
        else s=s.concat(Main.myResources.getString("s17"));
        if((saturation<0.1) && (brightness>0.9)) s=Main.myResources.getString("s26");
        if(brightness<0.1) s=Main.myResources.getString("s27");
        return s;
    }
}
