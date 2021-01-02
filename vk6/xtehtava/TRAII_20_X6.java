package tra2.vk6.xtehtava;

import java.util.Collection;

public interface TRAII_20_X6 {

    /**
     * Ryhmittelee kokonaislukulistan kahteen osaan siten, ettÃ¤ kukin syÃ¶telistan alkio kopioidaan
     * jompaankumpaan tuloslistaan (mutta ei molempiin). Tavoitteena on, ettÃ¤ tuloslistojen alkioiden summat
     * olisivat mahdollisimman lÃ¤hellÃ¤ toisiaan. Koska tehtÃ¤vÃ¤ on NP vaikea, optimaalista tulosta ei yleensÃ¤
     * saavuteta, mutta pyritÃ¤Ã¤n kohtuullisessa ajassa jotenkin kohtuulliseen tulokseen.
     * Algoritmin on suoriuduttava tehtÃ¤vÃ¤stÃ¤ maxAika sekunnissa.
     *  @param syote syÃ¶telista, tÃ¤tÃ¤ ei saa muuttaa mitenkÃ¤Ã¤n
     * @param tulos1 toinen tulos (kutsuttaessa tyhjÃ¤, palautettaessa sisÃ¤ltÃ¤Ã¤ osan alkioista)
     * @param tulos2 toinen tulos (kutsuttaessa tyhjÃ¤, palautettaessa sisÃ¤ltÃ¤Ã¤ osan alkioista)
     * @param maxAika suurin kÃ¤ytettÃ¤vissÃ¤ oleva aika (sekunteja)
     */
    public void ryhmitteleKahteenTasakokoiseen(Collection<Integer> syote, Collection<Integer> tulos1, Collection<Integer> tulos2, int maxAika);
}
