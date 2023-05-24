
public class Player {
    private String name = "No Name";
    private int[] score = {0,0,0};

    public void setName(String name){
        this.name = name;
    }
    public void setScore(int gameIndex, int score){
        this.score[gameIndex] = score;
    }

    public String getName(){
        return this.name;
    }

    public int getScore(int gameIndex){
        return this.score[gameIndex];
    }
}
