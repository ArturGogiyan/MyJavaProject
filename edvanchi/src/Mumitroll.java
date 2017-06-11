
/**
 * Created by edvanchi on 21.02.2017.
 */
public class Mumitroll extends Entity implements Movable {

    private boolean depression;
    private int sadness;
    public Tail tail;
    public Mumitroll (String name, int depression, int tailLeigth) {
        sadness = depression;
        if(this.sadness < 10){
            this.depression = true;
        }
        try {
            if (tailLeigth < 1) throw new WrongTailLengthException("Длина не может быть меньше 1", tailLeigth);
            setName(name);
        }catch (WrongTailLengthException exception){
            System.out.print(exception.getMessage()+exception.getLength());
        }
    }
    public void toWatch(Place place) {
        if(!(tail.getIsFold())) {
            System.out.println("задумчиво глядит в " + place.getSubstance());
        } else {
            System.out.println("как можно думать пока хвост не свёрнут....");
            throw new RuntimeException();
        }
    }
    public void toListen(FlyingInsect flyingInsect) {
        if(!(tail.getIsFold())) {
            System.out.println("слышит " + flyingInsect.getTypeOfNoise() + " издаваемый " + flyingInsect.getName());
        } else {
            System.out.println("как можно слышать пока хвост не свёрнут....");
        }
    }
    public String move(Place place){
        return place.getSubstance();
    }
}