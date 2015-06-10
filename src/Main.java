import net.scottjulian.lcs.DirectedAcyclicGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program is a runner to find the longest common subsequence in a Directed Acyclic Graph.
 * This program assumes the sequences given contain unique characters, and the same characters for every sequence.
 *
 * @author scott julian
 */
public class Main {

    public static DirectedAcyclicGraph _dag;
    public static ArrayList<String>    _charSequences = new ArrayList<String>();

    public static final String OUTFILE = "results.txt";

    public static void main(String[] args) {
        init(args);
        _dag = new DirectedAcyclicGraph(_charSequences);
        writeOutput(args[0]);
    }

    /**
     * reads the given file and stores data sequences into _charSequences
     * @param args
     */
    private static void init(String args[]){
        if(args.length != 1){
            System.err.println("too many or no arguments, exiting...\n");
            System.exit(-1);
        }

        // open sequences file
        File seqFile = new File(args[0]);
        Scanner scan = null;
        try {
            scan = new Scanner(seqFile);
        }
        catch (FileNotFoundException e) {
            System.err.println("can not find " + args[0] + ", exiting...\n");
            System.exit(-1);
        }

        while(scan.hasNextLine()) {
            _charSequences.add(scan.nextLine());
        }

        scan.close();
    }

    /**
     * writes output to OUTFILE
     */
    private static void writeOutput(String origFile){
        // open results.txt file
        BufferedWriter fout = null;
        try{
            fout = new BufferedWriter(new FileWriter(OUTFILE));
        }
        catch(IOException e){
            System.err.println("cannot establish results.txt, exiting...");
            System.exit(-1);
        }

        // write output
        try{
            fout.write("FILE: " + origFile + "\n");

            fout.write("\nsequences read: \n");
            for(String s : _charSequences){
                fout.write("  " + s + "\n");
            }

            fout.write("\n" + "all pairs: \n" + _dag.getAllPairs());
            fout.write("\n" + "intersected pairs: \n  " + _dag.getIntersections());
            fout.write("\n\n" + "topological order of nodes: \n  " + _dag.getTopologicalOrder());
            fout.write("\n\n" + "longest common subsequence: \n  "+ _dag.getLongestPath());

            fout.close();
        }
        catch (IOException e) {
            System.err.println("could not write to file! exiting...");
            System.exit(-1);
        }
        System.out.println("results.txt saved.");
    }
}
