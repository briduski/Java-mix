package org.example.nitobook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Thread24 {
    public static void main(String[] args) {
        List<Integer>list = Collections.synchronizedList(new ArrayList<>());
        for (int i=0; i<=100; ++i){
            list.add(i);
        }
        int sum = 0;
        for (int i =0; i<=100; ++i){
            sum +=i;
        }
        System.out.println(" sum: "+sum);
        // problems  if you update while reading another thread
    }
}
