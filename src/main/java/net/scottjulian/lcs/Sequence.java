package net.scottjulian.lcs;

import java.util.ArrayList;

/** Represents a sequence of unique chars
 * @author scott julian
 */
public class Sequence {

    private ArrayList<Pair> _pairs = new ArrayList<Pair>();

    public void addPair(char a, char b){
        _pairs.add(new Pair(a, b));
    }

    public void addPair(Pair p){
        _pairs.add(p);
    }

    public boolean containsPair(Pair inPair){
        for(Pair p : _pairs) {
            if (p.equalsTo(inPair)) {
                return true;
            }
        }
        return false;
    }

    public int getNumPairs(){
        return _pairs.size();
    }

    public Pair getPairAt(int pos){
        return _pairs.get(pos);
    }

    @Override
    public String toString(){
        String out = "";
        for(Pair p : _pairs) {
            out += "(" + p.getFirst() + " " + p.getSecond() + ") ";
        }
        return out;
    }
}
