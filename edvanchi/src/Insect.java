

/**
 * Created by edvanchi on 21.02.2017.
 */
public abstract class Insect extends Entity {
    private boolean isSilent;
    private String typeOfNoise;

    public void setSilent(boolean silent) {
        isSilent = silent;
    }

    public Boolean getSilent() {
        return isSilent;
    }
    public void setTypeOfNoise(String typeOfNoise) {
        this.typeOfNoise = typeOfNoise;
    }

    public String getTypeOfNoise() {
        return typeOfNoise;
    }
    static int valueOfHead=1;
    public static class HeadTesting{
        public void headTest(){
            if(valueOfHead!=1)System.exit(0);
        }
    }
}

