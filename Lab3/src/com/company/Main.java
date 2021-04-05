package com.company;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import tools.Graph;
import tools.MatrixOperations;

public class Main {
    private static class Node{
        public int num;
        public int nextIndex;
        public boolean isStartPoint;
        public Node prev;

        public Node(Node prev,int point){
            this.prev=prev;
            num=point;
            isStartPoint=false;
        }
        public Node(Node prev,int point,boolean start){
            this.prev=prev;
            num=point;
            isStartPoint=start;
        }

        public int calculateLength(Graph graph){
            Node current=this;
            int sum=0;

            while(current.prev!=null){
                sum+=graph.getEdge(current.prev.num,current.num);
                current=current.prev;
            }

            return sum;
        }

        @Override
        public String toString(){
            if(prev==null)return Integer.toString(num);
            else{
                return prev.toString()+" "+Integer.toString(num);
            }
        }
    }

    public static final void main(String args[]) throws IOException{

        System.out.println("Input file: ");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        MatrixOperations matrix = new MatrixOperations();
        int [][] array = matrix.readingMatrixFromFile(path);

        Graph graph=Graph.load(array);

        Stack<Node> nodes=new Stack<>();

        /*node node=null;
        for(int i=1;i<args.length;i++){
            node=new node(node,Integer.parseInt(args[i]),true);
            nodes.push(node);
            graph.enter(node.num);
        }
*/

        System.out.println("Вхідна матриця:");
        System.out.println(graph);
        Node node = null;
        node = new Node(node,0,true);
        nodes.push(node);
        graph.enter(node.num);
        Node shortest=null;

        node=nodes.pop();

        while(!node.isStartPoint||!(node.nextIndex>=graph.getCount())){
            int index=node.nextIndex++;

            if(index>=graph.getCount()){
                graph.leave(node.num);
                node=nodes.pop();
            }else if(graph.hasEdge(node.num,index)&&graph.enter(index)){
                nodes.push(node);
                node=new Node(node,index);
            }

            if(graph.allVisited()){
                if(shortest==null){
                    shortest=node;

                }else{
                    if(shortest.calculateLength(graph)>node.calculateLength(graph)){
                        shortest=node;
                    }
                }
            }
        }

        String result  = shortest.toString();

        System.out.println("Результат:");
        for (String s : result.split(" ")){
            System.out.print((char) (Integer.parseInt(s) + 65) + " ");
        }
    }
}
