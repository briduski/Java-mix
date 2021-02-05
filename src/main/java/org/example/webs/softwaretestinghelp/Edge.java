package org.example.webs.softwaretestinghelp;


//class to store edges of the weighted graph
public  class Edge {
    String src, dest;
    int weight;
    Edge(String src, String dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}