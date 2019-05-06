package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Board boardGame = new Board();
        boardGame.nextPlayer();
        try {
            System.out.println(boardGame.attackAt(2,2));
        } catch (Exception e) {
            System.out.println(e);
        }
        //boardGame.player1.toString();
        //boardGame.player2.toString();
    }
}