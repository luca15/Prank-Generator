package model;

/**
 * This exception class represents errors of the configuration file.
 *
 * @author Luca Sivillica
 * @author Dany Tchente
 */
public class ErrorConfigPropertiesException extends Exception {

    public ErrorConfigPropertiesException(String errorMessage) {
        super(errorMessage);
    }
}
