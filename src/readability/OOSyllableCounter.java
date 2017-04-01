package readability;

/**
 * OO-design state machine for SyllableCounter.
 * @author Pawat Nakpiphatkul
 */
public class OOSyllableCounter implements SyllableCounter {
    
    /**
     * Constructor for initialize OOSyllableCounter.
     */
    public OOSyllableCounter() {}
    
    /**
     * @see SyllableCounter#countSyllables(java.lang.String) 
     */
    public int countSyllables(String word) {
        WordCounter counter = new WordCounter();
        for(int i=0 ; i<word.length() && !counter.isNonWord() ; i++) {
            counter.getState().handleChar(word.charAt(i));
        }
        return counter.getSyllableCount();
    }
}
