package tra2.vk1.xtehtava;

import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;
import java.util.Set;

public interface TRAII_20_X1 {

    /**
     * Puun haarautumissolmut.
     * Palauttaa joukkona kaikki ne puun T solmut joilla on yksi tai kaksi lasta.
     * @param T syÃ¶tepuu
     * @param <E> puun alkioiden tyyppi (ei kÃ¤ytetÃ¤ muuten kuin muuttujien parametrointiin)
     * @return haaratusmissolmujen joukko
     */
    public <E> Set<BTreeNode<E>> haarautumisSolmut(BTree<E> T);

}
