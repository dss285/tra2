package tra2.vk2;


import fi.uef.cs.tra.Tree;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;
/*
    Koodi kaksinkertaistaa syötteen jokakerta, ajossa menee vaihtelevasti aikaa.

 */

public class tehtava7 {
    public static void main(String[] args) {
        Random rnd = new Random();
        lammita(10000, rnd, 3);
        testaaTreeset(10000000, rnd);
    }
    static void lammita(int n, Random rnd, int sek) {

        System.out.println("LÃ¤mmitys alkaa " + sek + "s");
        long loppu = System.nanoTime() + sek*1000L*1000*1000;
        while (System.nanoTime() < loppu)
            testaaTreeset(n, rnd);
        System.out.println("LÃ¤mmitys loppuu");
    }
    static void testaaTreeset(int n, Random rnd) {

        int k = 1;
        TreeSet<Integer> pq = new TreeSet<>();
        while (pq.size() < n) {
            while (pq.size() < k)
                pq.add(rnd.nextInt());
            testaaTreeset(pq, rnd);
            k *= 2;
        }
    }
    static void testaaTreeset(TreeSet<Integer> ts, Random rnd) {
        System.out.println();
        System.out.println("Testi: TreeSet koko:" + ts.size());
        int i = 0;
        long alku, loppu;
        alku = System.nanoTime();
        long tulos = mittaa(ts, rnd);
        loppu = System.nanoTime();
        System.out.println("Tulos: "+ tulos+"ns");
        System.out.println("Testi kesti " + ((loppu-alku)/1000000.0) + " ms");
        System.out.println();
    }
    static long mittaa(TreeSet<Integer> ts, Random rnd) {
        int i = 0;
        long sum = 0;
        while(i<ts.size()) {
            long alku = System.nanoTime();
            ts.subSet(rnd.nextInt(ts.size()), ts.size());
            long loppu = System.nanoTime();
            sum += loppu-alku;
            i++;
        }
        return sum/ts.size();

    }
}
