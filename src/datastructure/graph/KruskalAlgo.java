package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgo {
    public int minCostConnectPoints(int[][] points) {
        List<Edge> edges=new ArrayList<>();
        for(int i=0;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                int manDist= calManDist(points[i],points[j]);
                edges.add(new Edge(manDist,i,j));
            }
        }
        Collections.sort(edges, (a, b)->a.dist-b.dist);
        int size=edges.size()+1;
        parent = new int[size];
        for (int i = 0; i < size; i++) parent[i] = i;
        rank = new int[size];
        int mst=1;
        int cost=0;
        for(Edge e:edges){
            int d=e.dist;
            int u=e.u;
            int v=e.v;
            if(union(u,v)){
                cost+=d;
                mst+=1;
                if(mst==points.length)
                    break;
            }
        }
        return cost;
    }
    int[] parent;
    int[] rank;
    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean union(int x, int y) {
        int xr = find(x), yr = find(y);
        if (xr == yr) {
            return false;
        } else if (rank[xr] < rank[yr]) {
            parent[xr] = yr;
        } else if (rank[xr] > rank[yr]) {
            parent[yr] = xr;
        } else {
            parent[yr] = xr;
            rank[xr]++;
        }
        return true;
    }

    private int calManDist(int[] u, int[] v){
        return (int)(Math.abs(u[0]-v[0])+ Math.abs(u[1]-v[1]));
    }

    private static class Edge{
        int dist;
        int u;
        int v;
        public Edge(int d, int u, int v){
            this.dist=d;
            this.u=u;
            this.v=v;
        }
    }
}
