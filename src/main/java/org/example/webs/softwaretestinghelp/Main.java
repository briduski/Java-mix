package org.example.webs.softwaretestinghelp;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // define edges of the graph 
    /*    List<Edgerar> edges = Arrays.asList(
                new Edgerar(0, 1, 2),
                new Edgerar(0, 2, 4),
                new Edgerar(1, 2, 4),
                new Edgerar(2, 0, 5),
                new Edgerar(2, 1, 4),
                new Edgerar(3, 2, 3),
                new Edgerar(4, 5, 1),
                new Edgerar(5, 4, 3));*/

        String Uber = "Uber";
        String Ucer = "Ucer";
        String Uder = "Uder";
        String Ufer = "Ufer";
        String Uger = "Uger";
        String Uher = "Uher";
        String Ujer = "Ujer";
        String Uker = "Uker";
        String Uler = "Uler";
        String Umer = "Umer";
        String Uner = "Uner";
        String Uper = "Uper";
        String Upper = "Upper";
        String Uqer = "Uqer";
        String User = "User";
        String Userv5 = "Userv5";
        String Uter = "Uter";
        String Uver = "Uver";
        String Uwer = "Uwer";
        String Uzer = "Uzer";
        List<Edge> edges = Arrays.asList(
                new Edge(User, Uzer, 1),
                new Edge(Uker, Uzer, 1),
                new Edge(Umer, Uzer, 1),
                new Edge(Uner, Ujer, 1),
                new Edge(Uner, Uter, 1),
                new Edge(Uter, Uwer, 1),
                new Edge(Userv5, Uver, 1),
                new Edge(Userv5, Uber, 1),
                new Edge(Uber, Uwer, 1),
                new Edge(Ucer, Uwer, 1),
                new Edge(Uder, Uwer, 1),
                new Edge(Uber, Uqer, 1),
                new Edge(Ufer, Userv5, 1),
                new Edge(Ufer, Uger, 1),
                new Edge(Uger, Uber, 1),
                new Edge(Uger, Uher, 1),
                new Edge(Uher, Uqer, 1),
                new Edge(Uqer, Uper, 1),
                new Edge(Uler, Ujer, 1),
                new Edge(Uler, Uver, 1),
                new Edge(Uler, Uber, 1),
                new Edge(Upper, Uber, 1)
        );

        // call graph class Constructor to construct a graph
        Graph graph = new Graph(edges);

        // print the graph as an adjacency list
        Graph.printGraph(graph);
    }
}


