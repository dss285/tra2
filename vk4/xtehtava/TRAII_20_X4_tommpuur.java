package tra2.vk4.xtehtava;

import fi.uef.cs.tra.AbstractGraph;
import fi.uef.cs.tra.DiGraph;
import fi.uef.cs.tra.Vertex;
import fi.uef.cs.tra.Edge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


public class TRAII_20_X4_tommpuur implements TRAII_20_X4 {

    /**
     * ITSEARVIOINTI TÃ„HÃ„N:
     * juuriSolmut O(n+e)
     * puidenLukumaara O(n+e+e+n+n) = O(3n+2e) = O(n+e)
     * Ratkaisut ovat lineaarisia.
     *
     * juuriSolmut ratkaisu on hyvin simppeli ajatus:
     * Laitetaan kaikkien kaarien pääty hashsettiin ja tämän jälkeen käydään kaikki verkon solmut/alkiot läpi ja tarkistetaan että löytyykö joukosta, jos ei löydy, on tämä juurisolmu.
     *
     * puidenLukumäärässä käytetään juurisolmujen hakua, ja samaa ideaa, mutta tällä kertaa mappi,
     * jotta nähdään että meneekö vain 1 kaari kyseiseen solmuun, tällä tavalla nähdään ettei tämä ole minkäänmuun kuin yhden vanhempi.
     * väritetään kaikki valkoiseksi ja käydään läpi kaikki juurisolmut ja aloitetaan rekursiivinen kutsu.
     * rekursiivisessa kutsussa värjätään solmu harmaaksi ja sen jälkeen tarkistellaan tämän naapureita.
     * Naapureista tarkistetaan onko mapissa enemmän kuin 1 kaari(tarkemmin != 1, mutta ajaa saman asian),
     * (ensimmäisellä solmulla ei ole mitään kaaria, jolloin ei tarvitse tehdä mapista hakua ennen naapureitten käymistä)
     * onko naapurin väri valkoinen, jos on edetään rekursiossa syvemmälle. Rekursiosta palutuessa laitetaan tulos arvoksi true, jos päästään ulos loopista ilman että törmätään mihinkään
     * rajoitukseen, niin palautetaan, ja rekursio palautuu aina ylemmälle tasolle kunnes kaikki on käyty läpi.
     *
     *
     *
     *
     * Palauttaa joukkona kaikki ne solmut joihin ei johda yhtÃ¤Ã¤n kaarta.
     *
     * @param G syÃ¶teverkko
     * @return juurisolmujen joukko
     */
    @Override
    public Set<Vertex> juuriSolmut(DiGraph G) {
        Set<Vertex> tulos = new HashSet<>();
        HashSet<Vertex> setti = new HashSet<>();
        for(Edge e : G.edges()) {
            setti.add(e.getEndPoint());
        }
        for(Vertex v : G.vertices()) {
            if(!setti.contains(v)) {
                tulos.add(v);
            }
        }
        return tulos;
    }


    /**
     * Palauttaa moniko verkon G komponentti on puu (eli sellainen komponentti jossa
     * ei ole kehÃ¤Ã¤, etenemiskaaria, eikÃ¤ ristikkÃ¤iskaaria samasta eikÃ¤ toisesta komponentista).
     *
     * @param G syÃ¶teverkko
     * @return ehjien puiden lukumÃ¤Ã¤rÃ¤
     */
    @Override
    public int puidenLukumaara(DiGraph G) {

        Set<Vertex> potentiaalisetPuunJuuret = juuriSolmut(G);
        HashMap<Vertex, Integer> mappi = new HashMap<>();

        int tulos = 0;
        // juurisolmujen hakua ei ole pakko kÃ¤yttÃ¤Ã¤, mutta se auttaa
        // juurisolmujen haku on kuitenkin pakollinen osa tehtÃ¤vÃ¤Ã¤
        for(Edge e : G.edges()) {
            Vertex v = e.getEndPoint();
            if(mappi.containsKey(v)) {
                mappi.put(v, mappi.get(v)+1);
            } else {
                mappi.put(v, 1);
            }
        }
        varita(G, DiGraph.WHITE);
        for(Vertex v : potentiaalisetPuunJuuret) {
            boolean bool = puuKaynti(v, mappi);
            if (bool) {
                tulos++;
            }
        }

        return tulos; // TODO: tÃ¤hÃ¤n oikea palautusarvo
    }
    static boolean puuKaynti(Vertex V, HashMap<Vertex, Integer> mappi) {
        boolean tulos = true;
        V.setColor(DiGraph.GRAY);
        for(Vertex vv : V.neighbors()) {
            if(mappi.get(vv) != 1) {
                return false;
            }
            if(vv.getColor() == DiGraph.WHITE) {
                tulos = puuKaynti(vv, mappi);
                if(!tulos)
                    return false;
            } else {
                return false;
            }
        }
        return tulos;
    }
    static void varita(AbstractGraph G, int c) {
        for (Vertex v : G.vertices()) {
            v.setColor(c);
            v.setIndex(0);
        }
    }
}
