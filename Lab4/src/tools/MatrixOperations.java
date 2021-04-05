package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixOperations {

    public  int [][] readingMatrixFromFile(String path) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(path));

        List<String> matrixlist = new ArrayList<>();

        while (file.ready())
        {
            matrixlist.add(file.readLine());
        }

        int matrixWidth = matrixlist.get(0).split(" ").length;
        int matrixHeight = matrixlist.size();
        int [][] matrix = new int[matrixHeight][matrixWidth];

        for(int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                String [] line = matrixlist.get(i).split(" ");
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }


        return matrix;

    }


    public List<List<Integer>> returnData(int[][] array)
    {
        //String point_1, point_2;
        List<List<Integer>> list_result = new ArrayList<>();
        int f = 0;
        for(int i = 0; i < array.length; i++)
        {
  start:    for(int j = 0; j < array[i].length; j++)
            {
                if(array[i][j] != 0)
                {
                    if(list_result != null)
                    {
                        if(list_result != null)
                        {
                            for (List<Integer> k : list_result) {
                                if (((i+1) == k.get(0) && (j+1) == k.get(1)) || ((i+1) == k.get(1) && (j+1) == k.get(0))) {
                                    continue start;
                                }
                            }
                        }
                    }

                    List<Integer> list_rows = new ArrayList<>();
                    list_rows.add((i+1));
                    list_rows.add((j+1));
                    list_rows.add(array[i][j]);
                    list_result.add(list_rows);

                }
            }
        }
        return list_result;
    }

    public void printListInt(List<List<Integer>> list)
    {
        for(List<Integer> i : list)
        {
            for(int j : i)
            {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public void printListString(List<List<String>> list)
    {
        for(List<String> i : list)
        {
            for(String j : i)
            {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public List<List<String>> convertListIntToString(List<List<Integer>> list)
    {
        List<List<String>> list_result = new ArrayList<>();
        String point_1 = null, point_2 = null;
        int j = 0;
        for(List<Integer> i : list)
        {
                if(i.get(0) == 1) { point_1 = "A"; }
                else if(i.get(0) == 2) { point_1 = "B"; }
                else if(i.get(0) == 3) { point_1 = "C"; }
                else if(i.get(0) == 4) { point_1 = "D"; }
                else if(i.get(0) == 5) { point_1 = "E"; }
                else if(i.get(0) == 6) { point_1 = "F"; }
                else if(i.get(0) == 7) { point_1 = "G"; }
                else if(i.get(0) == 8) { point_1 = "H"; }

                if(i.get(1) == 1) { point_1 = "A"; }
                else if(i.get(1) == 2) { point_2 = "B"; }
                else if(i.get(1) == 3) { point_2 = "C"; }
                else if(i.get(1) == 4) { point_2 = "D"; }
                else if(i.get(1) == 5) { point_2 = "E"; }
                else if(i.get(1) == 6) { point_2 = "F"; }
                else if(i.get(1) == 7) { point_2 = "G"; }
                else if(i.get(1) == 8) { point_2 = "H"; }

                List<String> list_row = new ArrayList<>();
                list_row.add(point_1);
                list_row.add(point_2);
                list_row.add(String.valueOf(i.get(2)));
                list_result.add(list_row);
            }
        return list_result;

        }

        public ArrayList<Edge> makeGraphStructure(List<List<String>> data)
        {
            ArrayList<Edge> graph_structure = new ArrayList<>();
            for(List<String> edge_data : data)
            {
                Edge edge = new Edge();
                edge.setA((edge_data.get(0)).charAt(0));
                edge.setB((edge_data.get(1)).charAt(0));
                edge.setWeight(Integer.parseInt(edge_data.get(2)));
                graph_structure.add(edge);
            }

            return graph_structure;
        }
    }






