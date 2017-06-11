

import java.io.Serializable;

/**
 * Created by edvanchi on 21.02.2017.
 */
public abstract class Tail extends Entity implements Serializable{
    private int length;
    private String color;
    private boolean isFold;

    public void setColor(String color) {
        this.color=color;
    }
    public void setFold(boolean isFold){
        this.isFold=isFold;
    }
    public boolean getIsFold(){
        return isFold;
    }
    public String getcolor() {
        return color;
    }

    public void setLength(int length){
        this.length=length;
    }

    public int getLength() {
        return length;
    }
}
