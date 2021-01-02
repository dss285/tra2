package tra2.vk3.xtehtava;

import java.util.*;

public class TRAII_20_X3_tommpuur implements TRAII_20_X3 {
    /*
    *   En ole 100% varma mittauksen tuloksista, kaikki operaatiot pitäisi olla jonoissa vakioaikaisia, jolloin suuria eroja ei ole,
    *   tosin jotkut jonototeutuksista ovat huomattavasti hitaampia kuin toiset.
    *   Aikaa kului kaikissa eri kokoisissa syötteissä tasasesti kaikissa toteutuksissa, joten vakioaikaista.
    *
    *   Virheitä pois, ajamalla mittaus useamman kerran ja viimeisen ottaminen talteen. Otetaan keskiarvo, jolla nähdään että nopeita ovat.
    *
    *
    *
     */

    /**
     * Testaa aikaa lisÃ¤tÃ¤ ja poistaa alkioita jonosta J.
     * Mittaa ajan alkioiden mÃ¤Ã¤rÃ¤lle n = { min, min*2, min*4, min*8, ... <=max}.
     * Kullekin alkiomÃ¤Ã¤rÃ¤lle n mitataan aika joka kuluu seuraavaan operaatiosarjaan:
     * 1. lisÃ¤tÃ¤Ã¤n jonoon n alkiota
     * 2. n kertaa vuorotellen lisÃ¤tÃ¤Ã¤n jonoon yksi alkio ja otetaan yksi alkio pois
     * 3. poistetaan jonosta n alkiota
     * Viimeinen mitattava alkioiden mÃ¤Ã¤rÃ¤ on siis suurin min*2^k joka on pienempi tai yhtÃ¤ suuri kuin max.
     * Tuloksena palautetaan kuvaus jossa on avaimena kukin testattu syÃ¶tteen koko ja kuvana kyseisen
     * syÃ¶tteen koon mittaustulos nanosekunteina.
     *
     * @param J   testattava jono
     * @param min lisÃ¤ttÃ¤vien/poistettavien alkioiden minimimÃ¤Ã¤rÃ¤
     * @param max lisÃ¤ttÃ¤vien/poistettavien alkioiden mÃ¤Ã¤rÃ¤n ylÃ¤raja
     * @return kuvaus jossa on kaikki testitulokset
     */
    @Override
    public SortedMap<Integer, Long> jononNopeus(Queue<Integer> J, int min, int max) {
        Random rnd = new Random();
        int[] randoms = new int[max];

        for (int i = min; i < max; i++) {
            randoms[i] = rnd.nextInt(max + 1 - min) + min;
        }
        SortedMap<Integer, Long> tulos = new TreeMap<>();
        int iters = 5;
        for (int j = 0; j < iters; j++) {
            for (int i = min; i <= max; i *= 2) {
                long alku = System.nanoTime();
                for (int k = 0; k < iters * 2; k++) {
                    mittaus(J, randoms, i);
                }
                long loppu = System.nanoTime();
                tulos.put(i, (loppu - alku) / (iters * 2));
            }
        }
        return tulos;
    }

    private void mittaus(Queue<Integer> J, int[] randoms, int current_max) {
        int i = 0;
        while (i < current_max) {
            J.offer(randoms[i]);
            i++;
        }
        i = 0;
        while (i < current_max) {
            J.offer(J.poll());
            i++;
        }
        while (!J.isEmpty()) {
            J.poll();
        }
    }

}
