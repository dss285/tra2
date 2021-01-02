package tra2.vk4;// TRAII_20_t16.java SJ

import fi.uef.cs.tra.DiGraph;
import fi.uef.cs.tra.Edge;
import fi.uef.cs.tra.Graph;
import fi.uef.cs.tra.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class TRAII_20_t16_pohja {

    public static void main(String[] args) {

        // DiGraph graph = GraphMaker.createDiGraph(vertices, edges, rseed);
        DiGraph graph = Q1();

        System.out.println(GraphMaker.toString(graph, 1));

        Vertex dst = null;
        for (Vertex v : graph.vertices())
            if (v.getLabel().equals("0"))
                dst = v;
        if (dst == null)
            return;


        Set<Vertex> q = quorum(graph, dst, 0.6F);
        System.out.println("\nCompanies that are under quorum of " + dst + " : " + q);

    }   // main() 


    /**
     * 15. YhtiÃ¶llÃ¤ x on mÃ¤Ã¤rÃ¤ysvalta yhtiÃ¶ssÃ¤ y jos ja vain jos on olemassa yhtiÃ¶t z1,z 2,...,z k joissa yhtiÃ¶llÃ¤
     x on mÃ¤Ã¤rÃ¤ysvalta ja yhtiÃ¶t x,z1,z2,...,z k omistavat yhteensÃ¤ yli 50% yhtiÃ¶n y osakkeista. TÃ¤llaista
     laskentaa tarvitaan esimerkiksi yt-neuvotteluissa ja muissa lakiteknisissÃ¤ asioissa. Mallinnetaan
     omistuksia suunnatulla verkolla jossa jokainen yhtiÃ¶ on solmu ja kun yhtiÃ¶ x omistaa r% yhtiÃ¶n
     y osakkeista, niin verkossa on kaari (x,y) jonka paino on r. Hahmottele algoritmi joka etsii kaikki
     ne yhtiÃ¶t joihin yhtiÃ¶llÃ¤ x on mÃ¤Ã¤rÃ¤ysvalta.
     16. Toteuta tehtÃ¤vÃ¤n 15 algoritmi. SyÃ¶tteenÃ¤ ovat verkko yhtiÃ¶iden omistusosuuksista, tarkasteltava
     yhtiÃ¶ y (siis verkon solmu) ja mÃ¤Ã¤rÃ¤ysvaltaan riittÃ¤vÃ¤ osuus (yleensÃ¤ 50%). Tuloksena on se joukko
     yhtiÃ¶itÃ¤ (solmuja) jotka ovat yhtiÃ¶n y mÃ¤Ã¤rÃ¤ysvallassa.
     *
     * @param g graph of owning stocks
      * @param v the company under inspection
      * @param limit required limit of owning (e.g., 0.5)
      * @return the set of companies under quorum of v. Including v.
      **/
    static Set<Vertex> quorum(DiGraph g, Vertex v, float limit) {

        // initialize colours and weights
        for (Vertex w : g.vertices()) {
            w.setColor(DiGraph.WHITE);
        }

        // v has quorum of itself
        v.setWeight(1.0F);
        // it will be added to set, but it can be avoided if needed
        HashMap<Vertex, HashSet<Edge>> map = new HashMap<>();
        for(Edge e : g.edges()) {
            if(map.containsKey(e.getEndPoint())) {
                HashSet<Edge> ss = map.get(e.getEndPoint());
                ss.add(e);
                map.put(e.getEndPoint(), ss);
            } else {
                HashSet<Edge> ss = new HashSet<>();
                ss.add(e);
                map.put(e.getEndPoint(), ss);
            }
        }
        Set<Vertex> S = new HashSet<Vertex>();
        S.add(v);
        quorumrecursive(S, v, limit, map);
        // TODO tÃ¤hÃ¤n ja muualle omaa koodia

        return S;
    }
    static void quorumrecursive(Set set, Vertex v, float limit, HashMap<Vertex, HashSet<Edge>> map) {
        for(Edge e : v.edges()) {
            if(e.getWeight() >= limit) {
                e.getEndPoint().setWeight(e.getWeight());
                set.add(e.getEndPoint());
                quorumrecursive(set, e.getEndPoint(), limit, map);
            } else {
                HashSet<Edge> hset = map.get(e.getEndPoint());
                float lim_tmp = 0;
                for(Edge ee : hset) {
                    if(set.contains(ee.getStartPoint())) {
                        lim_tmp += ee.getWeight();
                        if(lim_tmp >= limit) {
                            set.add(e.getEndPoint());
                            break;
                        }
                    }
                }
            }
        }
    }





    // example graph
    // for company "0" and limit:
    //  0.5, the result should be (0,) 1, 2, 3, 4
    //  0.6, the result should be (0,) 2
    //  0.39, the result should be (0,) 1, 2, 3, 4, 5, 6
    static DiGraph Q1() {

        int n = 7;
        DiGraph g = new DiGraph();
        Vertex[] va = new Vertex[n];
        for (int i = 0; i < n; i++) 
            va[i] = g.addVertex(""+i);

        va[0].addEdge(va[1], 0.3F);
        va[0].addEdge(va[2], 0.7F);
        va[0].addEdge(va[4], 0.2F);
        va[1].addEdge(va[3], 0.2F);
        va[2].addEdge(va[1], 0.3F);
        va[2].addEdge(va[3], 0.6F);
        va[3].addEdge(va[4], 0.4F);
        va[3].addEdge(va[5], 0.4F);
        va[3].addEdge(va[6], 0.2F);
        va[4].addEdge(va[6], 0.2F);
        va[6].addEdge(va[5], 0.2F);

        return g;


    }

}
