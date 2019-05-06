package com.company;

public class Board {
    public Player player1;
    public Player player2;
    public Player currentPlayer;

    public Board() {
        int [][] ship1 = new int[][]{{1,0},{2,0}};
        int [][] ship2 = new int[][]{{0,1},{0,2}};
        this.player1 = new Player("Player1", ship1);
        this.player2 = new Player("Player2", ship2);
        this.currentPlayer = player1;

    }

    public Player nextPlayer(){
        System.out.println(this.currentPlayer);
        System.out.println(this.player1);
        if (this.currentPlayer.equals(this.player1)) {
            return this.player2;
        } else {
            return this.player1;
        }
    }

    public Player.Response attackAt(int x, int y) throws Exception {
        Player p = this.nextPlayer();
        Player.Response response = p.firedAt(x,y);
        if (response.equals(Player.Response.HIT_AND_SUNK)) {
            return response;
        }
        boolean hit = response.equals(Player.Response.HIT);
        this.currentPlayer.setHit(hit,x,y);
        this.currentPlayer=this.nextPlayer();
        return response;
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                player1.myGrid[i][j] = 0;
                player1.opponentsGrid[i][j] = 0;
                player2.myGrid[i][j] = 0;
                player2.opponentsGrid[i][j] = 0;
            }
        }
    }
}