package abhik26.tournament.exception;

import org.springframework.http.HttpStatus;

public class TournamentException extends RuntimeException {

    private HttpStatus httpStatus;

    public TournamentException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public TournamentException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
}
