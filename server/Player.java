
public class Player {
    private String name = "No Name";
    private String[] score = {"0","0","0"};

    Player(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setScore(int gameIndex, String score){
        this.score[gameIndex] = score;
    }

    public String getName(){
        return this.name;
    }

    public String getScore(int gameIndex){
        return this.score[gameIndex];
    }
}
