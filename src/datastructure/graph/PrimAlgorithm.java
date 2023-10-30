package graph;

import java.util.HashSet;
import java.util.PriorityQueue;

public class PrimAlgorithm {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length, ans = 0;
        HashSet<Integer> mst = new HashSet<>();
        mst.add(0);
        int[] dist = new int[n];
        PriorityQueue<Edge> pq=new PriorityQueue<>((a, b)->a.dist-b.dist);
        for(int i = 1; i < n; i++){
            dist[i] = findDist(points, 0, i);
            pq.add(new Edge(dist[i],0,i));
        }
        while(mst.size() != n) {
            // Find the node that has the shortest distance

            Edge curr =pq.poll();
            int next=curr.v;
            while(!pq.isEmpty() && mst.contains(next)){
                curr=pq.poll();
                next=curr.v;
            }
            // Put the node into the Minning Spanning Tree
            mst.add(next);
            ans += dist[next];
            System.out.println(next);
            // Update distance array
            for(int i = 0; i < n; i++) {
                if(!mst.contains(i)) {
                    //dist[i] = Math.min(dist[i], findDist(points, i, next));
                    if(findDist(points, i, next)<dist[i]){
                        dist[i]=findDist(points, i, next);
                        pq.add(new Edge(dist[i],next,i));
                    }
                }
            }
        }
        return ans;
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
    private int findDist(int[][] points, int a, int b) {
        return Math.abs(points[a][0] - points[b][0]) + Math.abs(points[a][1] - points[b][1]);
    }
}