package dm;

import java.util.ArrayList;
import java.util.List;

/**
 * A Directed Acyclic Graph
 * @author scott julian, john bartko
 */
public class DAG {
    
    /** A list of intersected pairs in the DAG **/
    private ArrayList<Pair> m_intersectedPairs = new ArrayList<Pair>();
    
    /** The DAG nodes topological sorted **/
    private String m_topologicalOrder = "";
    
    /** The longest path / Longest common subsequence of the DAG **/
    private char[] m_longestPath;
    
    /** The DAG's pairs where pairs are edges and each char is a node **/
    private Sequence[] m_allPairs;
    
    public DAG(Sequence[] allPairs) {  
       m_allPairs  = allPairs;
       findIntersectingPairs();
       topologicalSortDAG();
       findLongestPath();
    }
    
    /** sets the intersecting pairs of the DAG's given sequences **/
    private void findIntersectingPairs(){
        int numOfSeqs = m_allPairs.length;
        Pair currentPair = null;  
        
        // Find the intersected pairs
        for(int i = 0; i < numOfSeqs; i++){
            for(int j = 0; j < m_allPairs[i].getNumPairs(); j++){
                currentPair = m_allPairs[i].getPairAt(j);
                int count = 1;
                for(int k = 0; k < numOfSeqs; k++){
                    if(k == i) continue;
                    if(m_allPairs[k].containsPair(currentPair))
                        count++;       
                }
                if(count == numOfSeqs){
                    Boolean contains = false;
                    for(Pair p : m_intersectedPairs)
                        if(p.equalsTo(currentPair)) 
                            contains = true;
                    if(!contains)
                        m_intersectedPairs.add(currentPair); 
                }
            }
        }
    }
    
    /**
     * Topological Sort Algorithm for a DAG
     * 
    
    L - Empty list that will contain the sorted elements
    S - Set of all nodes with no incoming edges
    
        while S is non-empty do
            remove a node n from S
            insert n into L
            for each node m with an edge e from n to m do
                remove edge e from the graph
                if m has no other incoming edges then
                    insert m into S
        if graph has edges then
            return error (graph has at least one cycle)
        else 
            return L (a topologically sorted order)
            
    **/  
    private void topologicalSortDAG() {
        List<Character> L = new ArrayList<Character>();
        List<Character> S = new ArrayList<Character>();
        List<Pair> DAG = new ArrayList<Pair>(m_intersectedPairs);
        List<Pair> DAG_copy = new ArrayList<Pair>(m_intersectedPairs);

        // Get nodes with no incoming edges
        char inChar = 0;
        for (Pair p : DAG) {
            Boolean found = false;
            inChar = p.getFirst();
            for (Pair q : DAG){
                if (inChar == q.getSecond()){
                    found = true;
                    break;
                }
            }
            if (!found && !S.contains(inChar))
                S.add(inChar);
        }

        // Begin Sort
        char n, m;
        while (S.size() != 0) {
            n = S.remove(0);
            L.add(n);
            for (Pair f : DAG_copy){
                if (n == f.getFirst()) {
                    m = f.getSecond();
                    DAG.remove(f);
                    Boolean hasIncEdge = false;
                    for (Pair e : DAG_copy) {
                        if(e != null){
                            if (m == e.getSecond() && e != f) hasIncEdge = true;
                            if (!hasIncEdge && !S.contains(m) && !L.contains(m)) 
                                S.add(m);
                            hasIncEdge = false;
                        }
                    }
                }
            }
        }
        
        if (DAG.size() != 0) System.out.println("Error");
        for (char c : L)
            m_topologicalOrder += c;
    }
    
   /**
    * Longest Path Algorithm for a DAG
    * 
        G = DAG
        output: Length of the longest path
    
        length_to = array with |V(G)| elements of type int with default value 0
    
        for each vertex v in topOrder(G) do
            for each edge (v, w) in E(G) do
                if length_to[w] <= length_to[v] + 1 then
                    length_to[w] = length_to[v] + 1
     
        return max(length_to[v] for v in V(G))
        
    **/
    public void findLongestPath(){
        ArrayList<Pair> G = new ArrayList<Pair>(m_intersectedPairs);
        String topOrder = m_topologicalOrder;
        
        int length_to[] = new int[topOrder.length()];
        for(int f = 0; f < topOrder.length(); f++)
            length_to[f] = 0;
        
        // find length of each node
        char v, w = 0;
        for(int j = 0; j < topOrder.length(); j++){
            v = topOrder.charAt(j);
            for(int k = 0; k < G.size(); k++){
                if(v == G.get(k).getFirst()){
                    w = G.get(k).getSecond();
                    if(length_to[topOrder.indexOf(w)] <= length_to[topOrder.indexOf(v)] + 1){
                        length_to[topOrder.indexOf(w)] = length_to[topOrder.indexOf(v)] + 1;
                    }
                }
            }
        }
        
        // find longest length and associated node
        int longestPathLength = 0;
        char lastNode = 0;
        for(int i = 0; i < length_to.length; i ++){
            if(length_to[i] > longestPathLength){
                longestPathLength = length_to[i];
                lastNode = topOrder.charAt(i);
            }
        }
        
        m_longestPath = new char[longestPathLength + 1];
        m_longestPath[longestPathLength] = lastNode;
            
        // Find longest path starting from the end
        int prevLength = longestPathLength - 1;
        int prevNode = lastNode;
        for(int t = prevLength; t >= 0; t--){
            for(Pair p : m_intersectedPairs){
                if(p.getSecond() == prevNode){
                    if(prevLength == length_to[m_topologicalOrder.indexOf(p.getFirst())]){
                        m_longestPath[prevLength] = p.getFirst();
                        prevLength--;
                        prevNode = p.getFirst();
                        break;
                    }
                }           
            }
        
        }       
        
    }
    
    /** @return a String of the longest path, from left to right **/
    public String getLongestPath(){
        String lp = "";
        for(char q : m_longestPath)
            lp += q + " ";
        return lp;
    }
    
    /** @return a String of the nodes topological sorted **/
    public String getTopOrder(){
        return m_topologicalOrder;
    }

    /** @return a String of all intersected pairs **/
    public String getIntersections(){
        String out = "";
        for(Pair p : m_intersectedPairs)
            out += "(" + p.getFirst() + " " + p.getSecond() + ") ";
        return out;
    }

}
