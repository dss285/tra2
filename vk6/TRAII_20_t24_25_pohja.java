package tra2.vk6;

import java.util.*;

public class TRAII_20_t24_25_pohja {

    public static void main(String[] args) {

        // defaults
        int rahamaara = 34;

        if (args.length > 0)
            rahamaara = Integer.parseInt(args[0]);

        ArrayList<Integer> Kolikot = new ArrayList<Integer>();

        for (int i = 1; i < args.length; i++)
            Kolikot.add(Integer.valueOf(args[i]));

        if (Kolikot.size() == 0) {
            Kolikot.add(1);
            Kolikot.add(2);
            Kolikot.add(5);
            Kolikot.add(10);
            Kolikot.add(20);
            Kolikot.add(50);
        }

        Ajastin2 a;
        int n;

        a = new Ajastin2("Ahne_" + rahamaara);
        n = ahnejako(rahamaara, Kolikot);
        a.stop();
        System.out.println(a.toString() + " " + n + " kpl");


        if (rahamaara < 35) {
            a = new Ajastin2("Hajoita-ja-hallitse_" + rahamaara);
            n = hajhalljako(rahamaara, Kolikot);
            a.stop();
            System.out.println(a.toString() + " " + n + " kpl");
        }

        a = new Ajastin2("Dynaaminen_" + rahamaara);
        LinkedList<Integer> kolikot = dynjako24(rahamaara, Kolikot);
        a.stop();
        System.out.println(a.toString() + " " + n + " kpl");
        System.out.println("Rahat ( " + kolikot.size() + " kpl) = " + kolikot);

        a = new Ajastin2("Hajoita-ja-hallitse vÃ¤limuistilla_" + rahamaara);
        kolikot = hajhalljako25(rahamaara, Kolikot);
        a.stop();
        System.out.println(a.toString() + " " + n + " kpl");
        System.out.println("Rahat ( " + kolikot.size() + " kpl) = " + kolikot);


    }


    /**
     * Rahajako dynaamisen ohjelmoinnin keinoin
     * @param rahamaara kasattava rahamÃ¤Ã¤rÃ¤
     * @param kolikot kÃ¤ytÃ¶ssÃ¤ olevien kolikkojen arvot
     * @return tarvittavien kolikkojen lukumÃ¤Ã¤rÃ¤
     */
    static int dynjako(int rahamaara, Collection<Integer> kolikot) {
        int[] ratkaisut = new int[rahamaara+1];
        ratkaisut[0] = 0;
        // haetaan ja talletetaan kaikki osaratkaisut
        for (int i = 1; i <= rahamaara; i++) {
            int min = i;
            // kullakin kolikolla
            for(Integer c : kolikot) {
                if (c <= i) {
                    int r = ratkaisut[i-c]+1;
                    if (r < min)
                        min = r;
                }
            }
            ratkaisut[i] = min;
        }
        // vastaus alkuperÃ¤iseen tehtÃ¤vÃ¤Ã¤n
        return ratkaisut[rahamaara];
    }


    /**
     * Rahajako dynaamisen ohjelmoinnin keinoin
     * @param rahamaara kasattava rahamÃ¤Ã¤rÃ¤
     * @param kolikot kÃ¤ytÃ¶ssÃ¤ olevien kolikkojen arvot
     * @return lista palautettavista kolikoista
     */
    static LinkedList<Integer> dynjako24(int rahamaara, Collection<Integer> kolikot) {

        LinkedList<Integer> tulos = new LinkedList<Integer>();
        int[] ratkaisut = new int[rahamaara+1];
        int[] rahat = new int[rahamaara+1];
        ratkaisut[0] = 0;
        rahat[0] = 0;
        // haetaan ja talletetaan kaikki osaratkaisut
        for (int i = 1; i <= rahamaara; i++) {
            int min = i;
            int minkolikko = 0;
            // kullakin kolikolla
            for(Integer c : kolikot) {
                if (c <= i) {
                    int r = ratkaisut[i-c]+1;
                    rahat[i-c] = c;

                    if (r < min) {
                        min = r;
                        minkolikko = c;
                    }
                }
            }
            ratkaisut[i] = min;
            rahat[i] = minkolikko;


        }
        int count = 0;
        for(int i = 0;i<ratkaisut[rahamaara];i++) {
            if(i == 0) {
                count = rahat[i];
                tulos.add(rahat[i]);
            } else {
                tulos.add((rahat[count]));
                count += rahat[count];
            }
        }
        return tulos;
    }



    /**
     * Rahajako ahneella algoritmilla
     * @param rahamaara kasattava rahamÃ¤Ã¤rÃ¤
     * @param kolikot kÃ¤ytÃ¶ssÃ¤ olevien kolikkojen arvot
     * @return tarvittavien kolikkojen mÃ¤Ã¤rÃ¤
     */
    static int ahnejako(int rahamaara, Collection<Integer> kolikot) {
        int[] kol = new int[kolikot.size()];
        int n = 0;
        for (int c : kolikot)
            kol[n++] = c;
        Arrays.sort(kol);

        int r = 0;
        while (rahamaara > 0) {

            // valitaan suurin mahdollinen kolikko
            for (int j = n-1; j >= 0; j--) {
                if (kol[j] <= rahamaara) {
                    rahamaara -= kol[j];    // lÃ¶ytyi
                    r++;
                    break;
                }
            }
        }
        return r;
    }


    /**
     * Rahajako hajoita-ja-hallitse -tekniikalla
     * @param rahamaara kasattava rahamÃ¤Ã¤rÃ¤
     * @param kolikot kÃ¤ytÃ¶ssÃ¤ olevien kolikkojen arvot
     * @return tarvittavien kolikkojen mÃ¤Ã¤rÃ¤
     */
    static int hajhalljako(int rahamaara, Collection<Integer> kolikot) {
        if (rahamaara == 0)
            return 0;
        int tulos = rahamaara;		// ylÃ¤raja
        for(Integer c : kolikot)	// kolikkojen arvojen lÃ¤pikÃ¤ynti
            if (c <= rahamaara) {
                int r = hajhalljako(rahamaara - c, kolikot) + 1;
                if (r < tulos)
                    tulos = r;
            }
        return tulos;
    }

    /**
     * Rahajako hajoita-ja-hallitse -tekniikalla viritettynÃ¤ vÃ¤limuistilla
     * @param rahamaara kasattava rahamÃ¤Ã¤rÃ¤
     * @param kolikot kÃ¤ytÃ¶ssÃ¤ olevien kolikkojen arvot
     * @return palautettavat kolikot listana
     */
    static LinkedList<Integer> hajhalljako25(int rahamaara, Collection<Integer> kolikot) {
        HashMap<Integer, LinkedList<Integer>> hm = new HashMap<>();
        LinkedList<Integer> tulos = hajhalljako25recursive(rahamaara, kolikot, hm);
        int maara = rahamaara;
        for(Integer c : kolikot)	// kolikkojen arvojen lÃ¤pikÃ¤ynti
            if (c <= rahamaara) {
                LinkedList<Integer> r = hajhalljako25recursive(rahamaara - c, kolikot, hm);

            }
        return tulos;
    }
    private static int findMinCoins(int[] currency, int amount, int min){
        int min1 = min;
        for(int i=currency.length-1; i>=0; i--) {
            if (amount>=currency[i]){
                amount = amount - currency[i];
                System.out.print(currency[i] + " ");
                min1 = findMinCoins(currency, amount, min1);
                return ++min1;
            }
        }
        return min1;
    }
    static LinkedList<Integer> hajhalljako25recursive(int rahamaara, Collection<Integer> kolikot, HashMap<Integer, LinkedList<Integer>> hm) {

        if(rahamaara == 0) {
            return null;
        }
        if(hm.containsKey(rahamaara)) {
            return hm.get(rahamaara);
        }
        LinkedList<Integer> tulos = new LinkedList<>();
        for(Integer c : kolikot) {    // kolikkojen arvojen lÃ¤pikÃ¤ynti
            if (c <= rahamaara) {
                tulos.add(c);
                LinkedList<Integer> r = hajhalljako25recursive(rahamaara - c, kolikot, hm);
            }
        }
        return tulos;
    }


}
