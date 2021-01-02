package tra2.vk5.xtehtava;

import fi.uef.cs.tra.*;

import java.io.PipedOutputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TRAII_20_X5_tommpuur implements TRAII_20_X5 {

    /**
     * Aikavaativuus O(n*n)
     * Pahimmassa tapauksessa kaikki solmut käydään läpi kerran ja paikallinen työ on n
     *
     * Jos solmu on harmaa palautetaan, jotta ei käydä solmuja mitä ei olla loppuunkäyty tämänhetkisellärekursiotasolla.
     * Luo uuden listan joka rekursio tasolla, johon lisätään polut.
     * Värjää solmun harmaaksi ja tarkistetaan thän johtavia kaaria. Jos polun paino+kaarenpaino on vähemmän tai yhtäsuuri kuin raja, aloitetaan rekursio.
     *
     * Aikavaativuutta varmaan saa paremmaksi viisaammalla ratkaisulla kun koko ajan uuden listan tekemisellä.
     *
     *
     * Kaikki annetusta solmusta v lÃ¤htevÃ¤t korkeintaan p painoiset yksinkertaiset polut.
     * Polku annetaan solmujen listana siten, ettÃ¤ polun perÃ¤kkÃ¤isten solmujen vÃ¤lillÃ¤ on kaari
     * syÃ¶teverkossa.
     * Polun paino on polun kaarten painojen summa.
     * Yksikertaisella polulla ei ole kehÃ¤Ã¤ (ts. siinÃ¤ ei ole mitÃ¤Ã¤n solmua kahdesti)
     *
     * @param G syÃ¶teverkko
     * @param v lÃ¤htÃ¶solmu
     * @param p polkujen maksimipaino
     * @return polkujen joukko
     */
    @Override
    public Set<List<Vertex>> kaikkiMaxPPolut(Graph G, Vertex v, float p) {
        HashSet<List<Vertex>> tulos = new HashSet<>();
        for(Vertex vv : G.vertices()) {
            vv.setColor(Graph.WHITE);
        }
        // TODO: tÃ¤stÃ¤ alkaa polkujen haku, apumetodeja saa ja kannattaa kÃ¤yttÃ¤Ã
        LinkedList<Vertex> li = new LinkedList<>();
        polut(v, p, tulos, li, 0);
        return tulos;
    }
    public void polut(Vertex v,float p,HashSet<List<Vertex>> tulos, LinkedList<Vertex> ll, float paino) {
        if(v.getColor() == Graph.GRAY) {
            return;
        }
        LinkedList<Vertex> ll2 = new LinkedList<>(ll); // O(n) kaikki muut vakioaikaisia, paitsi rekursiivinen kutsu
        ll2.add(v);
        v.setColor(Graph.GRAY);

        for(Edge e  : v.edges()) {
            if(paino+e.getWeight() <= p) {
                if (e.getEndPoint().getColor() != Graph.GRAY) {
                    polut(e.getEndPoint(), p, tulos, ll2, paino + e.getWeight());
                } else if(e.getStartPoint().getColor() != Graph.GRAY) {
                    polut(e.getStartPoint(), p, tulos, ll2, paino+e.getWeight());
                }
            }
        }

        v.setColor(Graph.BLACK);
        if(ll2.size() > 1)
            tulos.add(ll2);
    }

    /*
                LinkedQueue<Vertex> vq = new LinkedQueue<>();
        vq.add(v);
        v.setWeight(0);
        while(!vq.isEmpty()) {
            LinkedList<Vertex> li2 = new LinkedList<>();
            li2.addAll(li);
            Vertex w = vq.remove();
            li2.add(w);
            for(Edge e : w.edges()) {
                if(e.getStartPoint().getWeight() + e.getWeight() <= p) {
                    e.getEndPoint().setWeight(e.getStartPoint().getWeight()+e.getWeight());
                    vq.add(w);
                }
            }
        }

     */
   /* */

}
