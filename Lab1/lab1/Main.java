package lab1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lab1.tools.*;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Input file: ");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        MatrixOperations matrix = new MatrixOperations();
        int [][] array = matrix.readingMatrixFromFile(path);
        List<List<Integer>> list = matrix.returnData(array);
        matrix.printListInt(list);
        List<List<String>> list_string = matrix.convertListIntToString(list);
        matrix.printListString(list_string);
        ArrayList<Edge> graph_structure = new ArrayList<>();
        graph_structure = matrix.makeGraphStructure(list_string);
        Graph graph = new Graph(graph_structure);
        MSTree forest = new MSTree();

        System.out.println("Вхідні дані:");
        System.out.println(graph);
        for (int i = 0; i < graph.getPointList().size(); i++) {
            if (!forest.havePoint((char)graph.getPointList().get(i))){
                int minWeight = 100;
                Edge minEdge = new Edge();
                for (int j = 0; j < graph.getEdges().size(); j++) {
                    if (graph.getEdges().get(j).havePoint((char)graph.getPointList().get(i))){
                        if (graph.getEdges().get(j).getWeight() < minWeight){
                            minEdge = graph.getEdges().get(j);
                            minWeight = graph.getEdges().get(j).getWeight();
                        }
                    }
                }
                forest.addEdge(minEdge);
            }
        }

        Graph resultGraph = forest.toTree(graph);
        System.out.println("Результат:");
        System.out.println(resultGraph);
        System.out.println("Вага остового дерева " + resultGraph.getWeight());

    }
}
