
/**
 * Created by edvanchi on 21.02.2017.
 */
public class WrongTailLengthException extends Exception {
    private int length;
    private String message;
    public int getLength() {
        return length;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public WrongTailLengthException(String message, int length){
        this.message=message;
        this.length=length;
    }
}
