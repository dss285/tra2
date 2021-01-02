package tra2;

import fi.uef.cs.tra.*;

public class Koe {
    public static void main(String[] args) {
        Graph g = new Graph();
        Vertex v = g.addVertex("asd1");
        Vertex v1 = g.addVertex("asd2");
        Vertex v2 = g.addVertex("asd3");

        v.addEdge(v2);
        v.addEdge(v1);
        v1.addEdge(v2);

        System.out.println(g.toString());
        System.out.println(onkoKehaa(g, v));
    }
    public static boolean onkoKehaa(Graph G, Vertex v) {

        varita(G, Graph.GRAY);
        // apumetodeja saa käyttää tarvittaessa
        return rekursio(v, null);

    }

    public static boolean rekursio(Vertex v, Vertex parent) {
        boolean tulos = false;
        v.setColor(Graph.WHITE);
        for(Vertex vv : v.neighbors()) {
            if(vv.getColor() == Graph.GRAY && vv != parent) {
                if (rekursio(vv,v)) {
                    return true;
                }
            }
            if(vv.getColor() == Graph.WHITE && vv != parent) {
                return true;
            }
        }
        v.setColor(Graph.BLACK);
        return false;
    }
    static void varita(AbstractGraph g, int c) {
        for (Vertex v : g.vertices())
            v.setColor(c);
    }
}
