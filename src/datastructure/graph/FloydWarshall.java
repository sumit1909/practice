package graph;

public class FloydWarshall {
// Time complexity O(V^3)
    public int[][] solve(int[][] A) {
        int [][]distance=new int[A.length][A[0].length];
        for(int i=0;i<A.length;i++)
            for(int j=0;j<A.length;j++)
                distance[i][j]=A[i][j];

        for(int k=0;k<A.length;k++){
            for(int i=0;i<A.length;i++){
                for(int j=0;j<A.length;j++){
                    if(distance[i][k]==-1 || distance[k][j]==-1)
                        continue;

                    if(distance[i][j]>distance[i][k]+distance[k][j] || distance[i][j]==-1){
                        distance[i][j]=distance[i][k]+distance[k][j];
                    }
                }
            }
        }
        return distance;
    }
}


