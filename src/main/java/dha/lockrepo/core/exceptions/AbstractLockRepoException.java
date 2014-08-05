package dha.lockrepo.core.exceptions;

public abstract class AbstractLockRepoException extends Exception {

    private static final long serialVersionUID = 1L;

    private String message;
    
    public AbstractLockRepoException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
