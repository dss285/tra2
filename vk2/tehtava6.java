package tra2.vk2;


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
/*
    Koodi kaksinkertaistaa syötteen jokakerta, ajossa menee vaihtelevasti aikaa, mutta peräkkäisistä syötteistä näkee, kun sitä on kaksinkertaistettu, niin myös
    addAll saa kaksinkertaisen ajan.
    Tästä voidaan päätellä, että addAll on O(n)

 */

public class tehtava6 {
    public static void main(String[] args) {
        Random rnd = new Random();
        lammita(10000, rnd, 3);
        testaaPQ(6500000, rnd);
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
            testaaPQ(pq, rnd);
            k *= 2;
        }
    }
    static void testaaPQ(PriorityQueue<Integer> pq, Random rnd) {
        System.out.println();
        System.out.println("Testi: PriorityQueue koko:" + pq.size());
        int i = 0;
        long alku = 0, loppu = 0;
        alku = System.nanoTime();
        long tulos = mittaa(pq, rnd);
        loppu = System.nanoTime();
        System.out.println("Tulos: "+ tulos+"ns");
        System.out.println("Testi kesti " + ((loppu-alku)/1000000.0) + " ms");
        System.out.println();
    }
    static long mittaa(PriorityQueue<Integer> pq, Random rnd) {
        ArrayList<Integer> lista = new ArrayList<>();
        int i = 0;
        while(i < pq.size()) {
            lista.add(rnd.nextInt());
            i++;
        }
        long alku = System.nanoTime();
        pq.addAll(lista);
        long loppu = System.nanoTime();
        return loppu-alku;

    }
}
