package XindusAssignment.WishlistManagement.Exceptions;

/**
 * CustomException is a subclass of RuntimeException used for custom application-specific exceptions.
 */
public class CustomException extends RuntimeException{
    /**
     * Constructs a new CustomException with the specified error message.
     * @param msg The error message describing the exception.
     */
    public CustomException(String msg){
        super(msg);
    }
}
