package tra2.vk5.xtehtava;

import fi.uef.cs.tra.Graph;
import fi.uef.cs.tra.Vertex;

import java.util.List;
import java.util.Set;

public interface TRAII_20_X5 {

    /**
     * Kaikki annetusta solmusta v lÃ¤htevÃ¤t korkeintaan p painoiset yksinkertaiset polut.
     * Polku annetaan solmujen listana siten, ettÃ¤ polun perÃ¤kkÃ¤isten solmujen vÃ¤lillÃ¤ on kaari
     * syÃ¶teverkossa. Polulla on vÃ¤hintÃ¤Ã¤n kaksi solmua (ja yksi kaari).
     * Polun paino on polun kaarten painojen summa.
     * Verkossa ei ole negatiivispainoisia kaaria.
     * Yksikertaisella polulla ei ole kehÃ¤Ã¤ (ts. siinÃ¤ ei ole mitÃ¤Ã¤n solmua kahdesti)
     * @param G syÃ¶teverkko
     * @param v lÃ¤htÃ¶solmu
     * @param p polkujen maksimipaino
     * @return polkujen joukko
     */
    public Set<List<Vertex>> kaikkiMaxPPolut(Graph G, Vertex v, float p);
}
