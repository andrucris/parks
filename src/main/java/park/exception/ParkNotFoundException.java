package park.exception;

public class ParkNotFoundException extends RuntimeException {

   public ParkNotFoundException(Long code) {
        super("Could not find park " + code);
    }
}
