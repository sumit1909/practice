package graph;

import java.util.*;
/*
Sample problem: https://leetcode.com/problems/course-schedule-ii/
 */

public class TopologicalSort {

    public int[] sort(int count, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph=new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int u = prerequisite[0];
            int v = prerequisite[1];
            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(v);
        }

        LinkedList<Integer> ans=new LinkedList<>();
        int [] vis=new int[count];
        for(int i=0;i<count;i++){
            if(vis[i]==0 && !dfs(graph,vis,ans,i))
                return new int[0];

        }
        return ans.stream().mapToInt(a->a).toArray();

    }

    private boolean dfs(Map<Integer,List<Integer>> graph, int[] vis,List<Integer> curr, int node){
        vis[node]=1;
        List<Integer> nb=graph.get(node);
        if(nb!=null && !nb.isEmpty()){
            for(int neighboursNode:nb){
                if(vis[neighboursNode]==0 && !dfs(graph,vis,curr,neighboursNode))
                    return false;
                if(vis[neighboursNode]==1)
                    return false;
            }
        }
        curr.add(node);
        vis[node]=2;
        return true;
    }
}
