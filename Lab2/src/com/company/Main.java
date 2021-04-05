package com.company;

import tools.EulerianPath;
import tools.Graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tools.MatrixOperations;
import tools.Edge;

public class Main {

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
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
        System.out.println("Алгоритм рішення задачі листоноші");
        System.out.println("Вхідний граф:");
        System.out.println(graph);
        EulerianPath eulerian_path = new EulerianPath(graph);

        System.out.println("Результат:");
        System.out.println(eulerian_path.getPath());
    }
}
