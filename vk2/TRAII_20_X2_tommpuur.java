package tra2.vk2;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

public class TRAII_20_X2_tommpuur implements TRAII_20_X2 {
    /**
     * Mittaa keskiarvon suoritusajasta,
     * Keskiarvo antaa n. 10-30ns mittauksessa.
     * Mittauksia tehdään (extra_iters+1) * M koko, jolloin saadaan melko tarkkoja tuloksia.
     * Kahden peräkkäisen syötteen koko kasvaa vähän yli lineaarisessa ajassa, kun vertaa testin kestoa.
     *
     * Testi, Map = class java.util.TreeMap n = 10000
     *   tulos = 14 ns
     * Testi kesti 6.2051 ms
     *
     * Testi, Map = class java.util.TreeMap n = 100000
     *   tulos = 21 ns
     * Testi kesti 68.9683 ms
     *
     * Testi, Map = class java.util.TreeMap n = 1000000
     *   tulos = 29 ns
     * Testi kesti 775.5292 ms
     *
     * Tästä voi nähdä, että syöte hieman yli kymmenenkertaistuu, jolloin voi päätellä, että se on alle n^2, mutta yli n
     *
     *
     *
     * Mittaa annetun kuvauksen contansKey -operaation aikavaativuuden nanosekunteina.
     * Mittaa ns. normaalin onnistuneen suorituksen, eli ei esimerkiksi parasta tapausta
     * (joka voi olla todella nopea) tai huonointa (jossa voi olla mittavirhettÃ¤).
     * Ei muuta kuvausta (lisÃ¤Ã¤ tai poista alkioita).
     *
     * @param M testattava kuvaus
     * @return containsKey -operaation kesto nanosekunteina
     */

    @Override
    public long containsKeyAika(Map<Double, Double> M) {

        int extra_iters = 10, i = 0;
        int jakaja = M.size()*(extra_iters+1);
        long alku = 0, loppu = 0, sum = 0;
        while(i<M.size()) {
            alku = System.nanoTime();
            M.containsKey(Double.valueOf(i)); // Tähän looppi
            loppu = System.nanoTime()-alku-20;
            sum += loppu;
            if(i == M.size()-1 && extra_iters != 0) {
                extra_iters--;
                i = 0;
                continue;
            }
            i++;
        }

        return sum/jakaja;
    }
}
