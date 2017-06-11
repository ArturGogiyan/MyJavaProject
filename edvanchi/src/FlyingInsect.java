

/**
 * Created by edvanchi on 21.02.2017.
 */
public class FlyingInsect extends Insect implements Flyable{
    public FlyingInsect(String name, Boolean isSilent, String typeOfNoise) {
        setName(name);
        setSilent(isSilent);
        if(!(getSilent())) {
            setTypeOfNoise(typeOfNoise);
        }
    }

    public String fly(Place place){
        return place.getSubstance();
    }
    public class Noise{
        private int volumeOfNoise;
        public Noise(int volumeOfNoise){
            this.volumeOfNoise=volumeOfNoise;
        }
        public int getVolumeOfNoise() {
            return volumeOfNoise;
        }
    }
}