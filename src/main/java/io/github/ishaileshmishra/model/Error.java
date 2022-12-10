package io.github.ishaileshmishra.model;

/**
 * <p>Error class.</p>
 *
 * @author shaileshmishra
 * @version $Id: $Id
 */
public class Error {

    String errorMessage = "No Error Message Found";
    int errorCode = 0;
    String errDetails = "No Error Details Found";

    /**
     * <p>Constructor for Error.</p>
     */
    public Error() {
    }

    /**
     * <p>Constructor for Error.</p>
     *
     * @param errorMessage a {@link java.lang.String} object
     * @param errorCode a int
     * @param errDetails a {@link java.lang.String} object
     */
    public Error(String errorMessage, int errorCode, String errDetails) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errDetails = errDetails;
    }

    /**
     * <p>Getter for the field <code>errorMessage</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * <p>Setter for the field <code>errorMessage</code>.</p>
     *
     * @param errorMessage a {@link java.lang.String} object
     */
    protected void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * <p>Getter for the field <code>errorCode</code>.</p>
     *
     * @return a int
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * <p>Setter for the field <code>errorCode</code>.</p>
     *
     * @param errorCode a int
     */
    protected void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * <p>getErrorDetail.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getErrorDetail() {
        return this.errDetails;
    }

    /**
     * <p>setErrorDetail.</p>
     *
     * @param errDetails a {@link java.lang.String} object
     */
    protected void setErrorDetail(String errDetails) {
        this.errDetails = errDetails;
    }

}
