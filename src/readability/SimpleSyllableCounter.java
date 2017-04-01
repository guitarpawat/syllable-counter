package readability;

/**
 * Non OO-design state machine (switch-case) for SyllableCounter.
 * @author Pawat Nakpiphatkul
 */
public class SimpleSyllableCounter implements SyllableCounter {
    
    /**
     * Constructor for initialize SimpleSyllableCounter.
     */
    public SimpleSyllableCounter() {}
    
    /**
     * @see SyllableCounter#countSyllables(java.lang.String) 
     */
    public int countSyllables(String word) {
        int syllables = 0;
        State state = State.START;
        for(int i=0 ; i<word.length() ; i++) {
            char c = word.charAt(i);
            if(c == '\'') continue;
            switch(state) {
                case START:
                    if(c == '-') state = State.NONWORD;
                    else if(isVowelOrY(c)) {
                        state = State.SINGLE_VOWEL;
                        syllables++;
                    }
                    else if(Character.isWhitespace(c)) break;
                    else if(Character.isLetter(c)) state = State.CONSONANT;
                    else state = State.NONWORD;
                    break;
                    
                case CONSONANT:
                    if(c == '-') state = State.HYPHEN;
                    else if(isE(c) && (i == word.length()-1) && (syllables != 0)) break;
                    else if(isVowelOrY(c)) {
                        state = State.SINGLE_VOWEL;
                        syllables++;
                    }
                    else if(Character.isLetter(c)) break;
                    else state = State.NONWORD;
                    break;
                    
                case SINGLE_VOWEL:
                    if(c == '-') state = State.HYPHEN;
                    else if(isVowel(c)) state = State.MULTIVOWEL;
                    else if(Character.isLetter(c)) state = State.CONSONANT;
                    else state = State.NONWORD;
                    break;
                    
                case MULTIVOWEL:
                    if(c == '-') state = State.HYPHEN;
                    else if(isVowel(c)) break;
                    else if(Character.isLetter(c)) state = State.CONSONANT;
                    else state = State.NONWORD;
                    break;
                    
                case HYPHEN:
                    if(c == '-') state = State.NONWORD;
                    else if(isVowelOrY(c)) {
                        state = State.SINGLE_VOWEL;
                        syllables++;
                    }
                    else if(Character.isLetter(c)) state = State.CONSONANT;
                    else state = State.NONWORD;
                    break;
                    
                case NONWORD:
                    return 0;
                    
                default :
                    throw new RuntimeException("Invalid state");
            }
        }
        return syllables;
    }
    
    /**
     * Check that the character is vowel or 'y'.
     * @param c is the current character of the word.
     * @return true if the character is vowel or 'y', else false.
     */
    private boolean isVowelOrY(char c) {
        return isVowel(c) || c=='y' || c=='Y';
    }
    
    /**
     * Check that the character is vowel
     * @param c is the current character of the word.
     * @return true if the character is vowel, else false.
     */
    private boolean isVowel(char c) {
        return (c=='a' || c=='e' || c=='i' || c=='o' || c=='u'
                || c=='A' || c=='E' || c=='I' || c=='O' || c=='U');
    }
    
    /**
     * Check that the character 'e'.
     * @param c is the current character of the word.
     * @return true if the character is 'e', else false.
     */
    private boolean isE(char c) {
        return (c == 'e' || c == 'E');
    }
}
