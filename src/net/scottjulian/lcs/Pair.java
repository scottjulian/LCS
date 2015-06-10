package net.scottjulian.lcs;

/**
 * A pair of characters
 * @author scott julian
 */
public class Pair {
    private char _first;
    private char _second;

    public Pair(char a, char b) {
        _first  = a;
        _second = b;
    }

    public char getFirst() {
        return _first;
    }

    public char getSecond() {
        return _second;
    }

    /**
     * checks if a pair is equal to a given pair
     * @param p
     * @return true if pairs are equal
     */
    public boolean equalsTo(Pair p) {
        if (p.getFirst() == _first && p.getSecond() == _second) {
            return true;
        }
        return false;
    }

}