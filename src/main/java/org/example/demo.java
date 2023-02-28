package org.example;

import java.util.ArrayList;
import org.example.object.*;
import org.example.object.Object;

public class demo {
    public static void main(String[] args) {
        int[][] matrix;
        matrix = new int[30][30];
        for(int i = 0; i < matrix.length; i++){
            for (int j= 0; j < matrix.length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

        ArrayList<Object> objList = new ArrayList<>();

        System.out.println(objList.size());
    }
}
