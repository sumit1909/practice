package graph;

import java.util.*;

public class Dijkstra {
    public int[] solve(int A, int[][] B, int C) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int[] edge:B){
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1],edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0],edge[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0,C});
        Map<Integer,Integer> dist = new HashMap<>();
        while(!pq.isEmpty()){
            int[] info = pq.poll();
            int d = info[0], node = info[1];
            if(dist.containsKey(node)) continue;
            dist.put(node,d);
            if(graph.containsKey(node))
                for(int[] edge:graph.get(node)){
                    int nei = edge[0],d2 = edge[1];
                    if(!dist.containsKey(nei)){
                        pq.add(new int[]{d+d2,nei});
                    }
                }
        }
        int[] ans = new int[A];
        Arrays.fill(ans,-1);
        for(int key:dist.keySet()){
            ans[key]=dist.get(key);
        }
        return ans;
    }
}


