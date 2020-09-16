package br.com.fiap.nacloja.exception;

import br.com.fiap.nacloja.exception.business.UrlFormatException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final String URL_FORMAT_EXCEPTION = "A URL deve começar com 'http://www' ou 'https://www'";
    private static final String INTERNAL_SERVER_ERRO = "Houve um erro inesperado no sistema.";
    private static final String INVALID_ARGUMENTS = "Argumentos inválidos.";
    private static final String RESOURCE_NOT_FOUND = "Recurso não encontrado.";

    @ExceptionHandler(value = UrlFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseException urlFormatException(HttpServletRequest request, Exception exception){
        return new ResponseException(request, URL_FORMAT_EXCEPTION, null);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseException exception(HttpServletRequest request, Exception exception){
        return new ResponseException(request, INTERNAL_SERVER_ERRO, exception.getMessage());
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class, NoSuchElementException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseException notFoundException(HttpServletRequest request, Exception exception) {
        return new ResponseException(request, RESOURCE_NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseException badRequestException(HttpServletRequest request, MethodArgumentNotValidException exception) {

        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        HashMap<String, String> detail = new HashMap<String, String>();

        for (FieldError fieldError : errors) {
            detail.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseException(request, INVALID_ARGUMENTS, detail);
    }

}
