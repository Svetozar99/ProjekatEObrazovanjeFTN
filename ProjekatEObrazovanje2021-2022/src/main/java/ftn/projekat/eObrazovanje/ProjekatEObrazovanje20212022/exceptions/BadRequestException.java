package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.exceptions;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {

    public BadRequestException() {}

    public BadRequestException(String message) {
        super(message);
    }

}
