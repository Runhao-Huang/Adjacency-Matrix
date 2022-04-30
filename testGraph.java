import java.util.Arrays;

public class testGraph
{
    public static void main(String[] args)
    {
        GraphMatrix test = new GraphMatrix();
        test.init(8);
        System.out.println(test.nodeCount());
        System.out.println(test.edgeCount());
        test.addEdge(0,1,5);
        test.addEdge(1,5,2);
        test.addEdge(2,0,5);
        test.addEdge(2,5,5);
        test.addEdge(2,4,5);
        test.addEdge(6,4,5);
        test.addEdge(4,7,5);
        test.addEdge(7,5,10);
        System.out.println(test.nodeCount());
        System.out.println(test.edgeCount());

        System.out.println(test.getWeight(7,5));
        test.setWeight(7,5,12);
        System.out.println(test.getWeight(7,5));
        test.removeEdge(0,1);
        System.out.println(test.edgeCount());
        int[] array = test.neighbors(5);
        System.out.println(Arrays.toString(array));

        test.resetVisited();
        System.out.println(test.BFS(2));

        test.resetVisited();
        System.out.println(test.hasPath(2,5));

        System.out.println(test.topologicalSort());
        System.out.println();
        System.out.println();
        System.out.println();

    }
}
