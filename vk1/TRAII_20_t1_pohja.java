package tra2.vk1;// TRAII_20_t1.java SJ

import java.util.*;

/**
 * 1. Kirjoita algoritmi, joka saa syÃ¶tteenÃ¤Ã¤n kokoelman (Collection<E>) ja kokonaisluvun k,
 * ja joka palauttaa tuloksenaan listan niistÃ¤ alkioista jotka esiintyivÃ¤t kokoelmassa tasan k
 * kertaa. Ã„lÃ¤ muuta syÃ¶tettÃ¤. Alkiot ovat samat jos niiden .equals() -metodi palauttaa true.
 * KÃ¤ytÃ¤ apuna kuvausta (Map<E, Integer>). MikÃ¤ on algoritmisi aikavaativuus? Ota pohjaa
 * ja esimerkkiÃ¤ Moodlesta.
 */
public class TRAII_20_t1_pohja {


    // PÃ¤Ã¤ohjelman kÃ¤yttÃ¶:
    // java TRAII_20_t1 [N] [K] [siemen]
    // missÃ¤ N on alkioiden mÃ¤Ã¤rÃ¤
    // K vaadittu alkiomÃ¤Ã¤rÃ¤
    // ja siemen on satunnaislukusiemen

    public static void main(String[] args) {

        // á¸±okoelman koko
        int N = 20;
        if (args.length > 0)
            N = Integer.parseInt(args[0]);

        // taulukoiden koko
        int K = 2;
        if (args.length > 1)
            K = Integer.parseInt(args[1]);

        // satunnaislukusiemen
        int siemen = N + K;
        if (args.length > 2)
            siemen = Integer.parseInt(args[2]);


        Random r = new Random(siemen);
        LinkedList<Integer> L = new LinkedList<>();

        // tÃ¤ytetÃ¤Ã¤n alkioilla
        for (int i = 0; i < N; i++) {
            L.add(r.nextInt(N / 3));
        }

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (N <= 30) {
            System.out.println(L);
        }

        List<Integer> kEsiintyjat = kKertaaEsiintyvat(L, K);

        if (N <= 30) {
            System.out.println("Tasan " + K + " kertaa esiintuivÃ¤t:");
            System.out.println(kEsiintyjat);
        }

    } // main()


    /**
     * Palauttaa kokoelmasta ne alkiot jotka esiintyvÃ¤t tasan k kertaa.
     * @param C syÃ¶tekokoelma
     * @param k vaadittava mÃ¤Ã¤rÃ¤ alkioita
     * @param <E> alkiotyyppi
     * @return k kertaa esiintyneet alkiot listana
     */
    public static <E> List<E> kKertaaEsiintyvat(Collection<E> C, int k) {

        List<E> result = new ArrayList<>();
        HashMap<E, Integer> mappi = new HashMap<>();
        for(E s : C) {
            if(!mappi.containsKey(s)) {
                mappi.put(s, 1);
            } else {
                mappi.put(s, mappi.get(s)+1);
            }
        }
        for(Map.Entry<E, Integer> entry : mappi.entrySet()) {
            if(entry.getValue() == k) {
                result.add(entry.getKey());
            }
        }


        return result;
    }


} // class

