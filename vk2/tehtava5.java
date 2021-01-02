package tra2.vk2;


import java.util.PriorityQueue;
import java.util.Random;
/*
    Koodi kaksinkertaistaa syötteen jokakerta, ajossa menee vaihtelevasti aikaa, mutta peräkkäisistä syötteistä näkee, kun sitä on kaksinkertaistettu, niin myös
    contains haun aika vaatii n. kaksinkertaisen ajan.
    Kokonaisaika suoritukseen n. 4 kertaistuu peräkkäisillä syötteillä, jolloin voi päätellä, että mittaus algoritmi on O(n^2) ja contains O(n)

 */

public class tehtava5 {
    public static void main(String[] args) {
        Random rnd = new Random();
        lammita(10000, rnd, 3);
        testaaPQ(650000, rnd);
    }
    static void lammita(int n, Random rnd, int sek) {

        System.out.println("LÃ¤mmitys alkaa " + sek + "s");
        long loppu = System.nanoTime() + sek*1000L*1000*1000;
        while (System.nanoTime() < loppu)
            testaaPQ(n, rnd);
        System.out.println("LÃ¤mmitys loppuu");
    }
    static void testaaPQ(int n, Random rnd) {

        int k = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (pq.size() < n) {
            while (pq.size() < k)
                pq.offer(rnd.nextInt());
            testaaPQ(pq);
            k *= 2;
        }
    }
    static void testaaPQ(PriorityQueue<Integer> pq) {
        System.out.println();
        System.out.println("Testi: PriorityQueue koko:" + pq.size());
        int i = 0;
        long alku = 0, loppu = 0;
        alku = System.nanoTime();
        long tulos = mittaa(pq);
        loppu = System.nanoTime();
        System.out.println("Tulos: "+ tulos+"ns/alkio");
        System.out.println("Testi kesti " + ((loppu-alku)/1000000.0) + " ms");
        System.out.println();
    }
    static long mittaa(PriorityQueue<Integer> pq) {
        int extra_iters = 10, i = 0;
        int jakaja = pq.size()*(extra_iters+1);
        long alku = 0, loppu = 0, sum = 0;
        while(i < pq.size()) {
            alku = System.nanoTime();
            pq.contains(i);
            loppu = System.nanoTime()-alku-20;
            sum += loppu;
            if(i == pq.size()-1 && extra_iters != 0) {
                extra_iters--;
                i = 0;
                continue;
            }
            i++;
        }
        return sum/jakaja;

    }
}
