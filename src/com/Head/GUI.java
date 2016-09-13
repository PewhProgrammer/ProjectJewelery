package com.Head;

import java.util.Scanner;

/**
 * Created by Lea on 13.09.2016.
 */
public class GUI {

    private Scanner Input = new Scanner(System.in);

    public int[] userInput() {

        int[] Coord = new int[4];

        System.out.print("Please enter first coordinates.\n");

        String s = "";

        if (Input.hasNextLine())  s = Input.nextLine();
        Scanner scan = new Scanner(s);

        Coord[0] = scan.nextInt();
        Coord[1] = scan.nextInt();

        System.out.print("Please enter second coordinates.\n");

        String s2 = "";

        if (Input.hasNextLine())  s2 = Input.nextLine();
        Scanner scan2 = new Scanner(s2);

        Coord[2] = scan2.nextInt();
        Coord[3] = scan2.nextInt();

        return Coord;


    }




}
