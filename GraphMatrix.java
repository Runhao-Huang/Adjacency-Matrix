import java.util.ArrayList;

public class GraphMatrix implements Graph
{
    private int[][] ajMatrix;
    private int numEdge;
    private int numNode;
    private boolean[] Visited;

    //This method is used to initialize the GraphMatrix according to the number of vertices.
    public void init(int n)
    {
        ajMatrix = new int[n][n];
        numNode = n;
        numEdge = 0;
        Visited = new boolean[n];

    }

    //This method is used to return the number of nodes in the graph matrix.
    public int nodeCount() {
        return numNode;
    }


    //This method is used to return the number of edges in the graph matrix.
    public int edgeCount() {
        return numEdge;
    }


    //This method is used to add an edge from node v to node w and the weight of new the edge is wgt.
    public void addEdge(int v, int w, int wgt)
    {
        //If the weight is 0, then edge doesn't exist.
        if(wgt == 0)
        {
            return;
        }
        //If there hasn't been an edge, we need to update the number of edge.
        if(ajMatrix[v][w] == 0)
        {
            numEdge += 1;
        }
        ajMatrix[v][w] = wgt;

    }


    //This method returns the weight of the edge from node v to node w.
    public int getWeight(int v, int w) {
        return ajMatrix[v][w];
    }


    //This method is used to set the weight of the edge from node v to node w.
    public void setWeight(int v, int w, int wgt)
    {
        ajMatrix[v][w] = wgt;

    }


    //This method is used to remove the edge from node v to node w.
    public void removeEdge(int v, int w)
    {
        //Check if there is actually an edge before.
        if(ajMatrix[v][w] != 0)
        {
            ajMatrix[v][w] = 0;
            numEdge -= 1;
        }

    }


    //This method is used to determine whether there is an edge from node v to node w.
    public boolean hasEdge(int v, int w)
    {
        if(ajMatrix[v][w] == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    //This method is used to return an array of integers that are the nodes which node v has an edge to.
    public int[] neighbors(int v)
    {
        int count = 0;
        //Get the number of neighbors of the node.
        for(int i = 0; i < numNode; ++i)
        {
            if(ajMatrix[v][i] != 0)
            {
                count += 1;
            }
        }
        int[] neighborList = new int[count];
        int j = 0;
        //Put the neighbors' node key into the array.
        for(int i = 0; i < numNode; ++i)
        {
            if(ajMatrix[v][i] != 0)
            {
                neighborList[j] = i;
                j += 1;
            }
        }
        return neighborList;
    }


    //This method is used to set all elements in the Visited array to false.
    public void resetVisited()
    {
        for(int i = 0; i < numNode; ++i)
        {
            Visited[i] = false;
        }

    }


    //This method is the Breadth First Search and returns an arrayList holding the result of BFS.
    public ArrayList<Integer> BFS(int v)
    {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> queue = new ArrayList<>();
        queue.add(v);
        Visited[v] = true;

        //Run if there is still node hasn't been dequeued.
        while(queue.isEmpty() == false)
        {
            int curr = queue.get(0);
            queue.remove(0);
            result.add(curr);
            int[] nextNodes = neighbors(curr);

            //Check if there is unvisited neighbors of this node and enqueue them.
            for(int i = 0; i < nextNodes.length; ++i)
            {
                if(Visited[nextNodes[i]] == false)
                {
                    queue.add(nextNodes[i]);
                    Visited[nextNodes[i]] = true;
                }
            }
        }

        return result;
    }


    //This method returns whether there is a path from node v to node w in the graph using BFS.
    public boolean hasPath(int v, int w)
    {
        resetVisited();
        ArrayList<Integer> result = BFS(v);

        //Check if node w is found in BFS of node v.
        if(result.contains(w))
        {
            return true;
        }
        return false;
    }


    //This method is the topologicalSort of a DAG graph and returns an ArrayList that contains the vertex labels/id in topologically sorted order.
    public ArrayList<Integer> topologicalSort()
    {
        resetVisited();
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> queue = new ArrayList<>();
        int[] count = new int[numNode];
        //Find the number of ingoing nodes of each node.
        for(int i = 0; i < numNode; ++i)
        {
            int[] temp = neighbors(i);
            for(int j = 0; j < temp.length; ++j)
            {
                count[temp[j]] += 1;
            }
        }
        //Enqueue the nodes that has no ingoing nodes.
        for(int i = 0; i < numNode; ++i)
        {
            if(count[i] == 0)
            {
                queue.add(i);
                Visited[i] = true;
            }
        }

        //Run if there is still node hasn't been dequeued.
        while(queue.isEmpty() == false)
        {
            int curr = queue.get(0);
            queue.remove(0);
            result.add(curr);
            int[] nextNodes = neighbors(curr);
            //Update the number of incoming nodes of the neighbors of this visited node. And enqueue if the updated number is 0.
            for(int i = 0; i < nextNodes.length; ++i)
            {
                count[nextNodes[i]] -= 1;
                if(count[nextNodes[i]] == 0)
                {
                    queue.add(nextNodes[i]);
                    Visited[nextNodes[i]] = true;
                }
            }
        }

        return result;
    }
}
