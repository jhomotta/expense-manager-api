package in.bushansirgur.expensetrackerapi.exceptions;

public class ItemAreadyExistsExeption extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ItemAreadyExistsExeption(String message) {
        super(message);
    }
}
