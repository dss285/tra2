package tra2.vk1.xtehtava;

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;

import java.util.HashSet;
import java.util.Set;

public class TRAII_20_X1_tommpuur implements TRAII_20_X1 {
    /**
     * Puun haarautumissolmut.
     * Palauttaa joukkona kaikki ne binÃ¤Ã¤ripuun T solmut joilla on yksi tai kaksi lasta.
     *
     * Yksinkertainen, tarkistetaan onko root lehtisolmu, jos on niin palautetaan.
     * Jos ei ole, lisätään settiin root, ja aloitetaan rekursiivinen läpikäynti ekana vasemmalta ja sitten oikealta, lopussa lisätään kaikki koottuna yhteen.
     * Aikavaativuus on vaikea sanoa.
     * aikavaativuutena on O(2^logn) itse rekursiosta, mutta addAll vie myös aikavaativuutta, joten sanoisin että
     * tarkempi on n*2^logn = O(2n^logn) = O(n^logn)
     *
     *
     *
     *   1
     * /   \
     * 2    6
     *
     * @param T syÃ¶tepuu
     * @return haarautusmissolmujen joukko
     */
    @Override
    public <E> Set<BTreeNode<E>> haarautumisSolmut(BTree<E> T) {
        Set<BTreeNode<E>> hsj = new HashSet<>();
        if(T.getRoot() != null) {
            if(onkoLehti(T.getRoot())) {
                return hsj;
            } else {
                hsj.add(T.getRoot()); // Tähän metodiin hashsetin kuljetus alifunktioihin
                Set<BTreeNode<E>> vasen = haarautumisSolmut(new BTree<>(T.getRoot().getLeftChild()));
                Set<BTreeNode<E>> oikea = haarautumisSolmut(new BTree<>(T.getRoot().getRightChild()));
                hsj.addAll(vasen);
                hsj.addAll(oikea);
            }
        }
        return hsj;
    }
    private <E> boolean onkoLehti(BTreeNode<E> n) {
        if(n.getLeftChild() == null && n.getRightChild() == null)
            return true;
        return false;
    }

}
