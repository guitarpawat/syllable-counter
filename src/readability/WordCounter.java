package readability;

/**
 * WordCounter with OO-design state machine.
 * @author Pawat Nakpiphatkul
 */
public class WordCounter {
    
    private final AbstractState START = new StartState();
    private final AbstractState CONSONANT = new ConsonantState();
    private final AbstractState SINGLE_VOWEL = new SingleVowelState();
    private final AbstractState MULTI_VOWEL = new MultiVowelState();
    private final AbstractState HYPHEN = new HyphenState();
    private final AbstractState NONWORD = new NonWordState();
    private final AbstractState CHECKLASTE = new CheckLastEState();
    
    private AbstractState state = START;
    private int syllableCount = 0;
    
    /**
     * Check that current state is NONWORD or not.
     * @return true if state is NONWORD, else false.
     */
    public boolean isNonWord() {
        return state == NONWORD;
    }
    
    /**
     * Get the count of syllable in the word.
     * @return count of syllable in the word.
     */
    public int getSyllableCount() {
        return syllableCount;
    }
    
    /**
     * Get the current state of WordCounter.
     * @return current state of WordCounter.
     */
    public AbstractState getState() {
        return state;
    }
    
    /**
     * Set new state to WordCounter and do exitState() and enterState() method.
     * @param state is a new state.
     */
    public void setState(AbstractState state) {
        this.state.exitState();
        this.state = state;
        this.state.enterState();
    }

    /**
     * Class for CHECKLASTE state.
     */
    private class CheckLastEState extends AbstractState {

        private boolean isZero;
        
        /**
         * @see AbstractState#handleChar(char) 
         */
        @Override
        public void handleChar(char c) {
            if(c == '-') setState(HYPHEN);
            else if(isVowel(c)) setState(MULTI_VOWEL);
            else if(Character.isLetter(c)) setState(CONSONANT);
            else if(c == '\'');
            else setState(NONWORD);
        }
        
        /**
         * @see AbstractState#enterState() 
         */
        @Override
        public void enterState() {
            isZero = syllableCount == 0;
            if (isZero) syllableCount++;
        }
        
        /**
         * @see AbstractState#exitState() 
         */
        @Override
        public void exitState() {
            if(!isZero) syllableCount++;
        }
    }

    /**
     * Class for START state.
     */
    private class StartState extends AbstractState {

        /**
         * @see AbstractState#handleChar(char) 
         */
        @Override
        public void handleChar(char c) {
            if(c == '-') setState(NONWORD);
            else if(isVowelOrY(c)) setState(SINGLE_VOWEL);
            else if(Character.isWhitespace(c)) ;
            else if(Character.isLetter(c)) setState(CONSONANT);
            else if(c == '\'');
            else setState(NONWORD);
        }
    }

    /**
     * Class for NONWORD state.
     */
    private class NonWordState extends AbstractState {

        /**
         * @see AbstractState#handleChar(char) 
         */
        @Override
        public void handleChar(char c) {}
        
        /**
         * @see AbstractState#enterState() 
         */
        @Override
        public void enterState() {
            syllableCount = 0;
        }

    }

    /**
     * Class for CONSONANT state.
     */
    private class ConsonantState extends AbstractState {

        /**
         * @see AbstractState#handleChar(char) 
         */
        @Override
        public void handleChar(char c) {
            if(c == '-') setState(HYPHEN);
            else if(isE(c)) setState(CHECKLASTE);
            else if(isVowelOrY(c)) setState(SINGLE_VOWEL);
            else if(Character.isLetter(c)) ;
            else if(c == '\'');
            else setState(NONWORD);
        }

    }

    /**
     * Class for SINGLE_VOWEL state.
     */
    private class SingleVowelState extends AbstractState {

        /**
         * @see AbstractState#handleChar(char) 
         */
        @Override
        public void handleChar(char c) {
            if(c == '-') setState(HYPHEN);
            else if(isVowel(c)) setState(MULTI_VOWEL);
            else if(Character.isLetter(c)) setState(CONSONANT);
            else if(c == '\'');
            else setState(NONWORD);
        }
        
        /**
         * @see AbstractState#enterState() 
         */
        @Override
        public void enterState() {
            syllableCount++;
        }
    }

    /**
     * Class for MULTI_VOWEL state.
     */
    private class MultiVowelState extends AbstractState {

        /**
         * @see AbstractState#handleChar(char) 
         */
        @Override
        public void handleChar(char c) {
            if(c == '-') setState(HYPHEN);
            else if(isVowel(c)) ;
            else if(Character.isLetter(c)) setState(CONSONANT);
            else if(c == '\'');
            else setState(NONWORD);
        }

    }

    /**
     * Class for HYPHEN state.
     */
    private class HyphenState extends AbstractState {

        /**
         * @see AbstractState#handleChar(char) 
         */
        @Override
        public void handleChar(char c) {
            if(c == '-') setState(NONWORD);
            else if(isVowelOrY(c)) setState(SINGLE_VOWEL);
            else if(Character.isLetter(c)) setState(CONSONANT);
            else if(c == '\'');
            else setState(NONWORD);         
        }

    }
    
    /**
     * Check that the character is vowel or 'y'.
     * @param c is the current character of the word.
     * @return true if the character is vowel or 'y', else false.
     */
    private static boolean isVowelOrY(char c) {
        return isVowel(c) || c=='y' || c=='Y';
    }
    
    /**
     * Check that the character is vowel
     * @param c is the current character of the word.
     * @return true if the character is vowel, else false.
     */
    private static boolean isVowel(char c) {
        return (c=='a' || c=='e' || c=='i' || c=='o' || c=='u'
                || c=='A' || c=='E' || c=='I' || c=='O' || c=='U');
    }
    
    /**
     * Check that the character 'e'.
     * @param c is the current character of the word.
     * @return true if the character is 'e', else false.
     */
    private static boolean isE(char c) {
        return (c == 'e' || c == 'E');
    }
    
}
