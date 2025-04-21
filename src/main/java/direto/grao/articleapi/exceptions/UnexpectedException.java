package direto.grao.articleapi.exceptions;

public class UnexpectedException extends RuntimeException {
    public UnexpectedException(String message, Exception e) {
        super(message);
    }
}
