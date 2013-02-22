package dm;

import java.util.ArrayList;

/** Represents a sequence of unique chars
 * @author scott julian, john bartko
 */
public class Sequence {
    
    @SuppressWarnings("unused")
    private String m_sequence = null;
    
    private ArrayList<Pair> m_pairs = new ArrayList<Pair>();
    
    public Sequence(String inLine){
        m_sequence = inLine;
    }
    
    public ArrayList<Pair> getAllPairs(){
        return m_pairs;
    }
    
    public void addPair(char a, char b){
        m_pairs.add(new Pair(a, b));
    }
    
    public boolean containsPair(Pair inPair){
        for(Pair p : m_pairs)
            if(p.equalsTo(inPair)) 
                return true;
        return false;
    }
    
    public int getNumPairs(){
        return m_pairs.size();
    }
    
    public Pair getPairAt(int pos){
        return m_pairs.get(pos);
    }
    
    @Override
    public String toString(){
        String out = "";
        for(Pair p : m_pairs)
            out += "(" + p.getFirst() + " " + p.getSecond() + ") ";   
        return out;
    }  
}
