package readability;

/**
 * Interface for all SyllableCounter class in this program.
 * @author Pawat Nakpiphatkul
 */
public interface SyllableCounter {
    
    /**
     * Perform to count of syllables in the word.
     * @param word is the word to count.
     * @return count of syllables in the word.
     */
    public int countSyllables(String word);
}
