package readability;

/**
 *
 * @author Pawat Nakpiphatkul
 */
public abstract class AbstractState {
    
    /**
     * Perform action due to the current state and character.
     * @param c is the current character of the word.
     */
    abstract void handleChar(char c);
    
    /**
     * Perform Action when enter to the state.
     */
    public void enterState() {}
    
    /**
     * Perform action when exit from the state.
     */
    public void exitState() {}
}
