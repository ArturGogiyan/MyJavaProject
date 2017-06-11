


import java.io.Serializable;

/**
 * Created by edvanchi on 21.02.2017.
 */
public abstract class Entity implements Serializable{
    private String name;
    private String location;
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setLocation(Place location) {
        this.location = location.getSubstance();
    }
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
