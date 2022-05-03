
package taskperf6;
 class NotAlphaNumeric extends Exception {
    public NotAlphaNumeric() {
        super("Please enter a number or a letter only for username and password. Please try again.");
    }
    
}
