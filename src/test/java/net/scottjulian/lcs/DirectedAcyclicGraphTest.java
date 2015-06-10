package net.scottjulian.lcs;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class DirectedAcyclicGraphTest {

    DirectedAcyclicGraph _dag;

    @Test
    public void testSmallSet(){
        ArrayList<String> sequences = new ArrayList<String>();
        sequences.add("abcde");
        sequences.add("bcaed");
        sequences.add("adbce");

        _dag = new DirectedAcyclicGraph(sequences);
        assertEquals("Small Set Topological Order Does Not Match",
                "abdec", _dag.getTopologicalOrder());
        assertEquals("Small Set Longest Path Does Not Match",
                "bce", _dag.getLongestPath());
    }

    @Test
    public void testLargeSet(){
        ArrayList<String> sequences = new ArrayList<String>();
        sequences.add("pmSUTnsgKGzBJhyYwZPrkXvNIlbRfqOtcHaoMedAiVDEuQLjCFWx");
        sequences.add("GNShuUgKoaTcwQAdPOxIVZDRiXEFCyvbmfrpBHWnqkMsetlJjLzY");
        sequences.add("BhTtDfLKsGQndekWNHXRUFVAipwyIlcoZaOzqMxbgSCvjEmruPYJ");
        sequences.add("nHkUOzcypmbhaPdSKJGBLIisEFvWxtwrjTuZYlAfNVoXgDQqeCRM");
        sequences.add("cgBQfyLNUslIdpGhFrXaDkZSvTjPKzAtVnOYquCWRHiJweEbMoxm");
        sequences.add("LchSXvQjDCzEaABFRiqrewOfktnxlsMHGoJpUKPWuIdgVNbymTZY");

        _dag = new DirectedAcyclicGraph(sequences);
        assertEquals("Large Set Topological Order Does Not Match",
                "pSUTnsKGzBhykfOcdQYJvEujCZPIbVMWolqtewrXRaAiDFx", _dag.getTopologicalOrder());
        assertEquals("Large Set Longest Path Does Not Match",
                "Svj", _dag.getLongestPath());
    }

}
