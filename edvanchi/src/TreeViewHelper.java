

import javafx.scene.control.TreeItem;

import java.math.MathContext;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;

/**
 * Created by edvanchi on 14.04.2017.
 */
public class TreeViewHelper {
    private MTsTail mts;
    public HashSet<TreeItem> getContent(){
        HashSet<TreeItem> tailCat = new HashSet<>();
        tailCat.addAll(getTails());
        return tailCat;
    }

    private HashSet<TreeItem> getTails(){
        HashSet<TreeItem> tails = new HashSet<>();
        for (MTsTail i: Main.tails){
            //ZoneId zid= (ZoneId)Main.myResources.getObject("ZoneID");
            //ZonedDateTime zdt = i.getDate();
            //zdt .getZone();
            //mts = new MTsTail(i.getH(),i.getS(),i.getV(),i.getLength(),zdt);
            tails.add(new TreeItem(i));
        }
        return tails;
    }
}
