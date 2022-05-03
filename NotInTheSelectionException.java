package taskperf6;

public class NotInTheSelectionException extends Exception {
    public NotInTheSelectionException() {
        super("Not in the given option. Please try again.");
    }
    
}
