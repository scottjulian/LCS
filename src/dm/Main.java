package dm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program finds the longest common subsequence in a Directed Acyclic Graph.
 * This program assumes the sequences given contain unique characters, 
 * and the same characters for every sequence.
 * 
 * @author scott julian, john bartko
 */
public class Main {
    
    /** The Directed Acyclic Graph **/
    public static DAG m_dag;
    
    /** The results file **/
    public static final String OUTFILE = "results.txt";
    
    /** Entry into System **/
    public static void main(String[] args) { 
        if(args.length != 1){
            System.err.println("too many or no arguments, exiting...\n");
            System.exit(-1);
        }
        System.out.print("\nRunning...  ");

        // open sequences file
        File seqFile = new File(args[0]);
        Scanner scan = null;
        try { scan = new Scanner(seqFile); }
        catch (FileNotFoundException e) {
            System.err.println("can not find " + args[0] + ", exiting...\n");
            System.exit(-1);
        }
        
        // open results.txt file
        FileWriter     fstream = null;
        BufferedWriter fout    = null;
        try{
            fstream = new FileWriter(OUTFILE);
            fout    = new BufferedWriter(fstream);
        }
        catch(IOException e){
            System.err.println("cannot establish results.txt, exiting...");
            System.exit(-1);
        } 

        // input sequences
        ArrayList<String> sequences = new ArrayList<String>();       
        while(scan.hasNext()) sequences.add(scan.next());
        
        // generate pairs from sequences
        Sequence allPairs[] = generatePairs(sequences);
        
        // create a Directed Acyclic Graph from pairs
        m_dag = new DAG(allPairs);
        
        System.out.print("done. \n");
        try{
            fout.write("FILE: " + args[0] + "\n");
            
            fout.write("\nsequences read: \n");
            for(String s : sequences) fout.write("  " + s + "\n"); 

            fout.write("\n" + "all pairs: \n");
            for(Sequence sq : allPairs) fout.write("  " + sq.toString() + "\n"); 
            
            fout.write("\n" + "intersected pairs: \n  " + m_dag.getIntersections());
            
            fout.write("\n\n" + "topological order of nodes: \n  " + m_dag.getTopOrder());
            
            fout.write("\n\n" + "longest common subsequence: \n  "+ m_dag.getLongestPath());
            
            fout.close();
            fstream.close();
        }
        catch (IOException e) {
            System.err.println("could not write to file! exiting...");
            System.exit(-1);
        }
        System.out.println("results.txt saved.");
        System.out.println("longest common subsequence: " + m_dag.getLongestPath());
    }
       
    /** 
     * Generates a set of unique pairs for each sequence
     * 
     * @param inSequences
     * @return an array of sequences with respective unique pairs
     */
    public static Sequence[] generatePairs(ArrayList<String> inSeqs){
        Sequence outSeqs[] = new Sequence[inSeqs.size()];
        int x = 0;
        char inChars[];
        for(String s : inSeqs){
            inChars = s.toCharArray();
            outSeqs[x] = new Sequence(s);
            for(int i = 0; i < inChars.length; i++)
                for(int j = i + 1; j < inChars.length; j ++)
                    outSeqs[x].addPair(inChars[i], inChars[j]);
            x++;
        }
        return outSeqs;
    }
            


}
