package tra2.vk6.xtehtava;

import java.lang.reflect.Array;
import java.util.*;

public class TRAII_20_X6_tommpuur implements TRAII_20_X6 {

    /**
     * Ensimmäisena kokeillaan perus järjestetty ja sitten järjestämätön lisääminen. Lisääminen tapahtuu jos tämänhetkinen summa on pienempi kun toisen listan summa.
     * Satunnaistettua syotteen tekemistä hoidetaan maxAika verran, jolla testien mukaan saadaan ero hyvin pieneksi, ellei jopa nollaksi.
     * Aikavaativuus on O(nlogn) järjestetyssäfunktiossa, O(n) randomT ja  jarjestematon funktioissa.
     *
     *
     *
     * Ryhmittelee kokonaislukulistan kahteen osaan siten, ettÃ¤ kukin syÃ¶telistan alkio kopioidaan
     * jompaankumpaan tuloslistaan (mutta ei molempiin). Tavoitteena on, ettÃ¤ tuloslistojen alkioiden summat
     * olisivat mahdollisimman lÃ¤hellÃ¤ toisiaan. Koska tehtÃ¤vÃ¤ on NP vaikea, optimaalista tulosta ei yleensÃ¤
     * saavuteta, mutta pyritÃ¤Ã¤n kohtuullisessa ajassa jotenkin kohtuulliseen tulokseen.
     * Algoritmin on suoriuduttava tehtÃ¤vÃ¤stÃ¤ maxAika sekunnissa.
     * @param syote   syÃ¶telista, tÃ¤tÃ¤ ei saa muuttaa mitenkÃ¤Ã¤n
     * @param tulos1  toinen tulos (kutsuttaessa tyhjÃ¤, palautettaessa sisÃ¤ltÃ¤Ã¤ osan alkioista)
     * @param tulos2  toinen tulos (kutsuttaessa tyhjÃ¤, palautettaessa sisÃ¤ltÃ¤Ã¤ osan alkioista)
     * @param maxAika suurin kÃ¤ytettÃ¤vissÃ¤ oleva aika (sekunteja)
     */
    @Override
    public void ryhmitteleKahteenTasakokoiseen(Collection<Integer> syote, Collection<Integer> tulos1,
                                               Collection<Integer> tulos2, int maxAika) {

        // TÃ¤ssÃ¤ on esimerkkinÃ¤ jaotellaan joka toinen alkio yhteen listaan
        // ja joka toinen alkio toiseen listaan
        // tuloksena kelvollinen jaottelu, mutta tasapaino on (yleensÃ¤)
        // melko huono

        Random rnd = new Random();
        rnd.setSeed(System.nanoTime());
        HashMap<Integer, HashMap<ArrayList, ArrayList>> hm = jarjestetty(syote);
        jarjestamaton(syote, hm);
        long loppu = System.nanoTime() + (maxAika-1)*1000L*1000*1000;
        while (System.nanoTime() < loppu) {
            if(!hm.containsKey(0))
                randomT(syote, hm, rnd);
            else
                break;
        }

        Map.Entry<Integer, HashMap<ArrayList, ArrayList>> minentry = null;
        for(Map.Entry<Integer, HashMap<ArrayList, ArrayList>> entry : hm.entrySet()) {
            if(minentry == null) {
                minentry = entry;
            } else {
                if(minentry.getKey() > entry.getKey()) {
                    minentry = entry;
                }
            }
        }
        HashMap<ArrayList, ArrayList> hmfinal = minentry.getValue();
        Map.Entry<ArrayList, ArrayList> entry = hmfinal.entrySet().iterator().next();
        tulos1.addAll(entry.getKey());
        tulos2.addAll(entry.getValue());
        // TODO: tee siis tilalle oma versio joka tekee jaottelun
        // tasapainoisemmin

        // vihjeitÃ¤:
        // (a) mieti missÃ¤ jÃ¤rjestyksessÃ¤ alkiot kannattaa lisÃ¤tÃ¤ ja kumpaan listaan
        //     alkio kannattaa milloinkin lisÃ¤tÃ¤
        // (b) kÃ¤ytÃ¤ (sopivasti) satunnaisuutta ja kokeile useita erilaisia jaotteluita
        //     ja lopuksi (ajan loputtua) lisÃ¤Ã¤ paras niistÃ¤ tuloslistoihin
    }
    public HashMap<Integer, HashMap<ArrayList, ArrayList>> jarjestetty(Collection<Integer> syote) {
        ArrayList<Integer> tulos1 = new ArrayList<>();
        ArrayList<Integer> tulos2 = new ArrayList<>();
        ArrayList<Integer> lista = new ArrayList<>(syote);
        Collections.sort(lista);
        int t1 = 0, t2 = 0;
        for(Integer i : lista) {
            if(t1+i <= t2) {
                tulos1.add(i);
                t1 += i;
            } else if(t2+i <= t1) {
                tulos2.add(i);
                t2 += i;
            } else {
                tulos1.add(i);
                t1 += i;
            }
        }
        HashMap<Integer, HashMap<ArrayList, ArrayList>> hm = new HashMap<>();
        HashMap<ArrayList, ArrayList> hs = new HashMap<>();
        hs.put(tulos1, tulos2);
        if(t1 < t2) {
            hm.put(t2-t1, hs);
        } else {
            hm.put(t1-t2, hs);
        }
        return hm;
    }
    public void jarjestamaton(Collection<Integer> syote, HashMap<Integer, HashMap<ArrayList, ArrayList>> hm) {
        ArrayList<Integer> tulos1 = new ArrayList<>();
        ArrayList<Integer> tulos2 = new ArrayList<>();
        int t1 = 0, t2 = 0;
        for(Integer i : syote) {
            if(t1+i <= t2) {
                tulos1.add(i);
                t1 += i;
            } else if(t2+i <= t1) {
                tulos2.add(i);
                t2 += i;
            } else {
                tulos1.add(i);
                t1 += i;
            }
        }
        HashMap<ArrayList, ArrayList> hs = new HashMap<>();
        hs.put(tulos1, tulos2);
        if(t1 < t2) {
            hm.put(t2-t1, hs);
        } else {
            hm.put(t1-t2, hs);
        }
    }
    public void randomT(Collection<Integer> syote, HashMap<Integer, HashMap<ArrayList, ArrayList>> hm, Random rnd) {
        HashSet<Integer> lapikaydyt = new HashSet<>();
        ArrayList<Integer> tulos1 = new ArrayList<>();
        ArrayList<Integer> tulos2 = new ArrayList<>();
        ArrayList<Integer> syotelista = new ArrayList<>(syote);
        int randomn = randomMin(0, syote.size()-1, rnd);
        int t1 = 0, t2 = 0;
        while(lapikaydyt.size() != syote.size()) {
            while(lapikaydyt.contains(randomn)) {
                randomn = randomMin(0, syote.size()-1, rnd);
            }
            lapikaydyt.add(randomn);
            int i = syotelista.get(randomn);
            if(t1+i <= t2) {
                tulos1.add(i);
                t1 += i;
            } else if(t2+i <= t1) {
                tulos2.add(i);
                t2 += i;
            } else {
                tulos1.add(i);
                t1 += i;
            }
        }
        HashMap<ArrayList, ArrayList> hs = new HashMap<>();
        hs.put(tulos1, tulos2);
        if(t1 < t2) {
            hm.put(t2-t1, hs);
        } else {
            hm.put(t1-t2, hs);
        }

    }
    public int randomMin(int min, int max, Random rnd) {
        return rnd.nextInt(max+1-min)+min;
    }
}
