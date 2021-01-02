package tra2.vk3;

import fi.uef.cs.tra.AbstractGraph;
import fi.uef.cs.tra.DiGraph;
import fi.uef.cs.tra.Graph;
import fi.uef.cs.tra.Vertex;
import jdk.swing.interop.SwingInterOpUtils;

import java.security.DigestException;
import java.util.HashSet;
import java.util.Set;

public class TRAII_20_t12_13_pohja {

    public static void main(String[] args) {

        // defaults
        int vertices = 43;
        int edges = 49;

        if (args.length > 0)
            vertices = Integer.parseInt(args[0]);

        if (args.length > 1)
            edges = Integer.parseInt(args[1]);

        int seed = vertices+edges;

        if (args.length > 2)
            seed = Integer.parseInt(args[2]);


        // Luodaan satunnainen verkko
        DiGraph graph = GraphMaker.createDiGraph(vertices, edges, seed);
        System.out.println("\nVerkko (numerot ovat solmujen nimiÃ¤, kirjaimet kaarten nimiÃ¤):");
        System.out.println(graph.toString());
        System.out.println("kehainen: " + onkoKehainen(graph));


        System.out.println("\nSeuraajat:");;
        for (Vertex v : graph.vertices()) {
            varita(graph, DiGraph.WHITE);
            System.out.println(v + " : " + seuraajat(v));
        }
        // lisÃ¤tÃ¤Ã¤n sykli jotta varmasti on kehÃ¤inen verkko
        GraphMaker.addRandomCycle(graph, false);
        System.out.println("\nKehÃ¤inen (nyt pitÃ¤Ã¤ olla)");
        for (Vertex v : graph.vertices()) {
            System.out.print(v + " : " );
            for (Vertex w : v.neighbors())
                System.out.print(w + " ");
            System.out.println();
        }
        System.out.println("kehainen: " + onkoKehainen(graph));

        // tehdÃ¤Ã¤n verkko jossa ei ole kehÃ¤Ã¤
        graph = GraphMaker.createDAG(vertices, edges, seed);
        System.out.println("\nDAG");
        for (Vertex v : graph.vertices()) {
            System.out.print(v + " : " );
            for (Vertex w : v.neighbors())
                System.out.print(w + " ");
            System.out.println();
        }
        System.out.println("kehainen (ei saisi olla): " + onkoKehainen(graph));

    } // main()


    /**
     * Solmun seuraajien joukko.
     * @param node aloitussolmu
     * @return kaikki solmut joihin on polku solmusta node
     */
    static Set<Vertex> seuraajat(Vertex node) {
        Set<Vertex> s = new HashSet<Vertex>();
        seuraajatRecursive(node, s);
        return s;
    }
    private static void seuraajatRecursive(Vertex node, Set<Vertex> set) {
        node.setColor(DiGraph.GRAY);
        for(Vertex e : node.neighbors()) {
            set.add(e);
            if(e.getColor() == DiGraph.WHITE) {
                seuraajatRecursive(e, set);
            }
        }
        return;
    }



    /**
     * Onko verkossa kehÃ¤Ã¤?
     * @param G syÃ¶teverkko
     * @return true jos verkossa on kehÃ¤, muuten false
     */
    static boolean onkoKehainen(DiGraph G) {
        boolean tulos = false;
        varita(G, DiGraph.WHITE);
        for(Vertex v : G.vertices()) {

            if(v.getColor() == DiGraph.WHITE) {
                tulos = kehainenRecursive(v);
                if(tulos) {
                    return tulos;
                }
            }
        }
        return tulos;
    }
    static boolean kehainenRecursive(Vertex v) {
        boolean tulos = false;
        v.setColor(DiGraph.GRAY);
        for(Vertex vv : v.neighbors()) {
            if(vv.getColor() == DiGraph.WHITE) {
                tulos = kehainenRecursive(vv);
                if(tulos) {
                    return tulos;
                }
            }
            if(vv.getColor() == DiGraph.GRAY) {
                return true;
            }
        }
        v.setColor(DiGraph.BLACK);
        return false;
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
