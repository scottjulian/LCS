package dm;

/**
 * This class represents a pair of characters
 * @author scott julian, john bartko
 */
public class Pair {
    private char m_first;
    private char m_second;

    public Pair(char a, char b) {
        m_first  = a;
        m_second = b;
    }

    public char getFirst() { return m_first; }

    public char getSecond() { return m_second; }

    /**
     * checks if a pair is equal to a given pair
     * @param inPair
     * @return true if pairs are equal
     */
    public boolean equalsTo(Pair p) {
        if (p.getFirst() == m_first && p.getSecond() == m_second) 
            return true;
        return false;
    }

}