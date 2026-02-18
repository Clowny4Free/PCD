package Es6_Giugno2002;

public class ContoInesistente extends RuntimeException {
    public ContoInesistente(String message) {
        super(message);
    }
}
