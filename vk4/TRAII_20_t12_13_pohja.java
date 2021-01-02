package tra2.vk4;

import fi.uef.cs.tra.AbstractGraph;
import fi.uef.cs.tra.DiGraph;
import fi.uef.cs.tra.Vertex;
import tra2.vk3.GraphMaker;

import java.security.DigestException;
import java.util.HashSet;
import java.util.Set;

public class TRAII_20_t12_13_pohja {

    public static void main(String[] args) {

        // defaults
        int vertices = 7;
        int edges = 9;

        if (args.length > 0)
            vertices = Integer.parseInt(args[0]);

        if (args.length > 1)
            edges = Integer.parseInt(args[1]);

        int seed = vertices+edges;

        if (args.length > 2)
            seed = Integer.parseInt(args[2]);


        // Luodaan satunnainen verkko
        DiGraph graph = GraphMaker.createDiGraph(vertices, edges, seed);
        System.out.println("\nVerkko:");
        System.out.println(GraphMaker.toString(graph, 0));

        System.out.println("kehainen: " + onkoKehainen(graph));

        // lisÃ¤tÃ¤Ã¤n sykli jotta varmasti on kehÃ¤inen verkko
        GraphMaker.addRandomCycle(graph, false);
        System.out.println("\nKehÃ¤inen (nyt pitÃ¤Ã¤ olla)");
        System.out.println(GraphMaker.toString(graph, 0));
        System.out.println("kehainen: " + onkoKehainen(graph));

        // tehdÃ¤Ã¤n verkko jossa ei ole kehÃ¤Ã¤
        graph = GraphMaker.createDAG(vertices, edges, seed);
        System.out.println("\nDAG");
        System.out.println(GraphMaker.toString(graph, 0));
        System.out.println("kehainen (ei saisi olla): " + onkoKehainen(graph));

    } // main()


    /**
     * Solmun seuraajien joukko.
     * @param node aloitussolmu
     * @return kaikki solmut joihin on polku solmusta node
     */




    /**
     * Onko verkossa kehÃ¤Ã¤?
     * @param G syÃ¶teverkko
     * @return true jos verkossa on kehÃ¤, muuten false
     */
    static Set<Vertex> onkoKehainen(DiGraph G) {
        HashSet<Vertex> hs = new HashSet<>();
        varita(G, DiGraph.WHITE);
        for(Vertex v : G.vertices()) {

            if(v.getColor() == DiGraph.WHITE) {
                kehainenRecursive(v, hs);
            }
        }
        return hs;
    }
    static void kehainenRecursive(Vertex v, HashSet<Vertex> set) {
        v.setColor(DiGraph.GRAY);
        for(Vertex vv : v.neighbors()) {
            if(vv.getColor() == DiGraph.WHITE) {
                kehainenRecursive(vv, set);
            }
            if(vv.getColor() == DiGraph.GRAY) {
                kehainenlisaa(vv, set);
            }
        }
        v.setColor(DiGraph.BLACK);
        return;
    }
    static void kehainenlisaa(Vertex v, HashSet<Vertex> set) {
        if(set.contains(v) || v.getColor() == DiGraph.WHITE) {
            return;
        }
        if(v.getColor() == DiGraph.BLUE) {
            set.add(v);
        }
        v.setColor(DiGraph.BLUE);
        int run = 0;
        for(Vertex vv : v.neighbors()) {
            run++;
            kehainenlisaa(vv, set);
        }
        if(0 == run ) {
            v.setColor(DiGraph.GRAY);
        }

    }



    /**
     * Syvyyssuuntainen lÃ¤pikÃ¤ynti (tekemÃ¤ttÃ¤ verkolle mitÃ¤Ã¤n)
     * Siis runko.
     *
     * @param G lÃ¤pikÃ¤ytÃ¤vÃ¤ verkko
     */
    static void dfsStart(DiGraph G) {
        for (Vertex v : G.vertices())                // kaikki solmut valkoisiksi
            v.setColor(DiGraph.WHITE);
        for (Vertex v : G.vertices())                // aloitus vielÃ¤ kÃ¤ymÃ¤ttÃ¶mistÃ¤ solmuista
            if (v.getColor() == DiGraph.WHITE)
                dfsRekursio(v);
    }

    /**
     * Syvyyssuuntainen lÃ¤pikÃ¤ynti solmusta node alkaen
     *
     * @param node aloitussolmu
     */
    static void dfsRekursio(Vertex node) {
        // tÃ¤hÃ¤n toimenpide solmulle node (jos esijÃ¤rjestys)
        node.setColor(DiGraph.GRAY);
        for (Vertex v : node.neighbors())                // vielÃ¤ kÃ¤ymÃ¤ttÃ¶mÃ¤t
            if (v.getColor() == DiGraph.WHITE)            // naapurit
                dfsRekursio(v);
        // tÃ¤hÃ¤n toimenpide solmulle node (jos jÃ¤lkijÃ¤rjestys)
    }


    /**
     * VÃ¤ritÃ¤ verkon kaikki solmut.
     * @param G vÃ¤ritettÃ¤vÃ¤ verkko
     * @param c vÃ¤ri jota kÃ¤ytetÃ¤Ã¤n
     */
    static void varita(AbstractGraph G, int c) {
        for (Vertex v : G.vertices())
            v.setColor(c);
    }



}
