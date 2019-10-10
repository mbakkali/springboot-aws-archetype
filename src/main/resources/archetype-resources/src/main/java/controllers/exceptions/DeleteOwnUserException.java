#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controllers.exceptions;

public class DeleteOwnUserException extends Exception {
    public DeleteOwnUserException(String errorMessage) {
        super(errorMessage);
    }
}
