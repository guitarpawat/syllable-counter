package readability;

/**
 * Enum contains all state for SimpleSyllableCounter class.
 * @author Pawat Nakpiphatkul
 */
public enum State {

    START,CONSONANT,SINGLE_VOWEL,MULTIVOWEL,HYPHEN,NONWORD;
    
    private State() {}
    
}
