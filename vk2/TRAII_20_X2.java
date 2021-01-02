package tra2.vk2;

import java.util.Map;

public interface TRAII_20_X2 {

    /**
     * Mittaa annetun kuvauksen contansKey -operaation aikavaativuuden nanosekunteina.
     * Mittaa ns. normaalin onnistuneen suorituksen. Ei siis minimiÃ¤, maksimia.
     * Ei muuta kuvausta (lisÃ¤Ã¤ tai poista alkioita).
     *
     * @param M testattava kuvaus
     * @return containsKey operaation normikesto nanosekunteina
     */
    public long containsKeyAika(Map<Double, Double> M);

}
