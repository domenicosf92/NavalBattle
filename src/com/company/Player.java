package com.company;

import java.util.Objects;

public class Player {
    public int myGrid[][];
    public int opponentsGrid[][];
    public int ship [][];
    private String name;
    public enum Response{
        HIT,
        MISS,
        HIT_AND_SUNK,
    }

    public Player(String name, int [][] ship) {
        this.name = name;
        this.myGrid = new int[3][3];
        this.opponentsGrid= new int[3][3];
        this.ship= ship;
    }

    public Player.Response firedAt(int x, int y) throws Exception {
        if (x<0 || x>this.myGrid.length || y<0 || y>this.myGrid.length) throw new Exception("Invalid coordinate");
        for (int i=0; i<2; i++){
            if (ship[i][0]==x && ship[i][1]==y) {
                myGrid[x][y]=1;
                return checkSunk() ? Player.Response.HIT_AND_SUNK : Player.Response.HIT;
            }
        }
        myGrid[x][y]=-1;
        return Player.Response.MISS;
    }

    public void setHit(boolean hit, int x, int y){
        this.opponentsGrid[x][y] = hit ? 1 : -1; //ternary operator
    }

    public boolean checkSunk(){
        int counter=0;
        for (int i=0;i<this.ship.length;i++){
            int [] coords = this.ship[i];
            counter+= this.myGrid[coords[0]][coords[1]];
        }
        return counter==this.ship.length;
    }

    @Override
    public String toString() {
        StringBuilder s= new StringBuilder();
        for (int i=this.myGrid.length-1; i>=0; i--){
            for (int j=0; i<this.myGrid[i].length-1; j++){
                s.append("| ").append(this.myGrid[i][j]).append(" |");
            }
            s.append("\n");
        }

        s.append("\n\n");

        for (int i=this.opponentsGrid.length-1; i>=0; i--){
            for (int j=0; i<this.opponentsGrid[i].length-1; j++){
                s.append("| ").append(this.opponentsGrid[i][j]).append(" |");
            }
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }
}
