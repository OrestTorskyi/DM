package com.company;

import tools.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        System.out.println("Вхідні дані:");
        System.out.println(graph);

        ArrayList<FlowAlgorithm> flows = new ArrayList<>();
        System.out.println(start(graph, flows));
        int totalWeight = 0;
        for (FlowAlgorithm flow : flows){
            totalWeight += flow.getWeight();
        }
        System.out.println("Величина потоку " + totalWeight);
    }


    private static ArrayList<FlowAlgorithm> start(Graph graph, ArrayList<FlowAlgorithm> flows) {
        char start = graph.getStartPoint(), end = graph.getEndPoint();


        ArrayList<Character> availablePoint = graph.getPointsList();
        char point = start;
        FlowAlgorithm flow = new FlowAlgorithm();
        while (point < end){
            try {
                availablePoint.remove(availablePoint.indexOf(point));
            }catch (ArrayIndexOutOfBoundsException ignored){}
            Edge maxEdge = new Edge('A', 'A', 0);
            for (Edge edge : graph.getAdjacentEdges(point)) {
                if ( edge.getWeight() > maxEdge.getWeight()){
                    for (char p :  availablePoint){
                        if (edge.havePoint(p)){
                            maxEdge = edge;
                        }
                    }
                }
            }
            if (maxEdge.getWeight() == 0){
                if (point == start){
                    return flows;
                }
                char previousPoint = flow.get(flow.size() - 1).getA() == point ?
                        flow.get(flow.size() - 1).getB() : flow.get(flow.size() - 1).getA();
                point = previousPoint;
                flow.removeLast();
            }else {
                flow.add(maxEdge);
                point = point == maxEdge.getA() ? maxEdge.getB() : maxEdge.getA();
            }
        }

        int minWeight = Integer.MAX_VALUE;

        for (Edge edge : flow.getEdges()) {
            if (minWeight > edge.getWeight()) {
                minWeight = edge.getWeight();
            }
        }

        flow.setWeight(minWeight);

        for (Edge edge : flow.getEdges()){
            edge.setWeight(edge.getWeight() - minWeight);
        }

        flows.add(flow);

        return start(graph, flows);
    }
}
