package abhik26.tournament.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import abhik26.tournament.exception.TournamentException;

@ControllerAdvice
public class TournamentControllerAdvice {

    @ExceptionHandler(TournamentException.class)
    public ResponseEntity<Object> handleTTException(TournamentException ex) {
        return new ResponseEntity<Object>(ex.getMessage(), ex.getHttpStatus());
    }
}
