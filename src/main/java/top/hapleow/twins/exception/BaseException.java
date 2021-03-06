package top.hapleow.twins.exception;

/**
 * @author wuyulin
 * @date 2020/9/28
 */
public class BaseException extends RuntimeException {


    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BaseException(String message) {
        super(message);
    }
}
