package tra2.vk5;

import com.sun.security.jgss.GSSUtil;
import fi.uef.cs.tra.AbstractGraph;
import fi.uef.cs.tra.Edge;
import fi.uef.cs.tra.Graph;
import fi.uef.cs.tra.Vertex;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class TRAII_20_t20_21_pohja {

    public static void main(String[] args) {

        // defaults
        int vertices = 7;
        int edges = 7;

        if (args.length > 0)
            vertices = Integer.parseInt(args[0]);

        if (args.length > 1)
            edges = Integer.parseInt(args[1]);

        int seed = vertices+edges;

        if (args.length > 2)
            seed = Integer.parseInt(args[2]);

        Graph graph;


        System.out.println("\nVerkko1: ");
        graph = GraphMaker.createGraph(vertices, edges, seed);
        System.out.print(GraphMaker.toString(graph, 0));

        System.out.print("YhtenÃ¤inen: ");
        boolean yhten = onkoYhtenainen(graph);
        System.out.println(yhten + "\n");
        if (! yhten) {   // kutsutaan tehtÃ¤vÃ¤Ã¤ 20
            System.out.println("TÃ¤ydennys:");
            taydennaYhtenaiseksi(graph);
            System.out.print(GraphMaker.toString(graph, 0));
        }

        yhten = onkoYhtenainen(graph);
        System.out.println("YhtenÃ¤inen nyt: " + yhten);
        
        if (yhten) {
            System.out.print("\nLeikkaussolmut:   ");
            List<Vertex> ls = leikkausSolmut(graph);
            System.out.println(ls);
            if (! ls.isEmpty()) {
                System.out.println("TÃ¤ydennys:");
                taydenna2yhtenaiseksi(graph);
            }
            System.out.print("Leikkaussolmut nyt:   ");
            System.out.println(leikkausSolmut(graph));
            System.out.println(GraphMaker.toString(graph, 0));
        }


    } // main()

    /**
     * TÃ¤ydennÃ¤ annettu verkko yhtenÃ¤iseksi.
     * @param g verkko
     */
    static void taydennaYhtenaiseksi(Graph g) {
        varita(g, Graph.WHITE);
        Vertex w = g.firstVertex();
        dfsColor(w, Graph.BLACK);
        HashSet<Vertex> hs = new HashSet<>();
        for(Vertex v : g.vertices()) {
            if(v.getColor() == Graph.WHITE) {
                hs.add(v);
            }
        }
        for(Vertex v : hs) {
            w.addEdge(v);
        }
    }   // taydennaYhtenaiseksi()

    
    static boolean onkoYhtenainen(Graph g) {
        // kaikki valkoisiksi
        varita(g, Graph.WHITE);

        // syvyyssyyntainen lÃ¤pikynti jostain solmusta
        Vertex w = g.firstVertex();
        dfsColor(w, Graph.BLACK);

        // onko vielÃ¤ valkoiseksi jÃ¤Ã¤neitÃ¤ solmuja
        for (Vertex v : g.vertices())
            if (v.getColor() == Graph.WHITE)
                return false;
        return true;
    }

    // syvyyssuuntainen lÃ¤pikynti vÃ¤ritten vÃ¤rillÃ¤ c
    static void dfsColor(Vertex v, int color) {
        v.setColor(color);

        for (Vertex w : v.neighbors())
            if (w.getColor() != color)
                dfsColor(w, color);
    }

    // verkko annetun vriseksi
    static void varita(AbstractGraph g, int c) {
        for (Vertex v : g.vertices())
            v.setColor(c);
    }


    /**
     * TÃ¤ydentÃ¤Ã¤ verkon 2-yhtenÃ¤iseksi.
     * @param g
     */
    static void taydenna2yhtenaiseksi(Graph g) {
        LinkedList<Vertex> leikkausSolmut = leikkausSolmut(g);
        // TODO
        for(Vertex v : leikkausSolmut) {
            Vertex edellinen = null;
            for(Vertex w : v.neighbors()) {
                if(edellinen == null) {
                    edellinen = w;
                    continue;
                } else {
                    if(w.getEdge(edellinen) == null) {
                        w.addEdge(edellinen);
                    }
                    edellinen = w;
                }
            }
        }
    }


    // leikkaussolmut yhtenÃ¤isestÃ¤ suuntaamattomasta verkosta
    static LinkedList<Vertex> leikkausSolmut(Graph g) {
        
        Vertex[] vA = GraphMaker.getVertexArrayIndex(g);
        int n = g.size();

        // solmujen ja kaarten pohjavÃ¤ri
        varita(g, Graph.WHITE);
        for (Edge e : g.edges())
            e.setColor(Graph.WHITE);

        // taulukot
        int[] dfsnumber = new int[n];
        int[] low = new int[n];
        int i = 0;
        LinkedList<Vertex> L = new LinkedList<Vertex>();
        // komponentti kerrallaan
        for (Vertex v : g.vertices())
            if (v.getColor() == Graph.WHITE)
                i = numberdfs(v, dfsnumber, low, i, L, null);

        return L;
    }

    // dfsnumerointi taulukkoon, samalla luokittelee kaaret
    // puukaaret mustiksi, paluukaaret harmaiksi
    // isa on solmu josta tÃ¤tÃ¤ kutsutaan
    static int numberdfs(Vertex v, int[] dfsnumber, int[] low, int i,
                  LinkedList<Vertex> L, Vertex isa) {
        v.setColor(Graph.BLACK);
        dfsnumber[v.getIndex()] = i++;

        // dfs rekursio ja kaarien luokittelu
        // naapurien lÃ¤pikÃ¤ynnin kaarien avulla
        for (Edge e : v.edges()) {

            // kaari on jo kÃ¤sitelty toiseen suuntaan (isÃ¤-kaari,
            // tai jÃ¤lkelÃ¤isen paluukaari 
            if (e.getColor() != Graph.WHITE)
                continue;

            // naapurisolmu
            Vertex w = e.getEndPoint(v);

            if (w.getColor() == Graph.WHITE) {
                e.setColor(Graph.BLACK);    // puukaari
                // rekursiokutsu
                i = numberdfs(w, dfsnumber, low, i, L, v);
            } else if (w.getColor() == Graph.BLACK)
                e.setColor(Graph.GRAY); // paluukaari
        } // for(v.edges)

        // kaikkien rekursiokutsujen jÃ¤lkeen (jÃ¤lkijÃ¤rjestys)
        // low-arvon laskenta
        int min = dfsnumber[v.getIndex()];
        for (Edge e : v.edges()) {
            Vertex w = e.getEndPoint(v);
            if (w == isa)   // isÃ¤Ã¤ ei lasketa
                continue;

            // lasten low-luvut
            if (e.getColor() == Graph.BLACK) {
                if (low[w.getIndex()] < min)
                    min = low[w.getIndex()];

            // esi-isien (joihin paluukaari) dfsnumerot    
            } else if (e.getColor() == Graph.GRAY) {
                if (dfsnumber[w.getIndex()] < min)
                    min = dfsnumber[w.getIndex()];
            }

        }   // for(v.edges)

        low[v.getIndex()] = min;

        // leikkaussolmujen tunnistus
        if (v.getIndex() == 0) { // juurisolmu (vain yksi)
            int poikia = 0;
            // lasten lkm dfs-puussa
            for (Edge e : v.edges())
                if (e.getColor() == Graph.BLACK)
                    poikia++;
            if (poikia > 1)
                L.add(v);

        // muut solmut
        } else {
            // poika w, jolle low[w] >= dfnumber[v]
            for (Edge e : v.edges())
                if (e.getColor() == Graph.BLACK) {
                    Vertex w = e.getEndPoint(v);
                    if (low[w.getIndex()] >= dfsnumber[v.getIndex()]) {
                        L.add(v);
                        break;
                    }
                } // if BLACK
        } // else

        return i;   // palautetaan numeroitujen solmujen mÃ¤Ã¤rÃ¤
    }   // numberdfs() 

    // \leikkaussolmut


}
