import java.io.*;
import java.net.*;
import java.util.ArrayList;
public class GameServer {
    //Record the players
    public static ArrayList<Player> players = new ArrayList<Player>();
    //server state
    public static int WAITING = 0;
    public static int STARTGAME = 1;
    public static int ENDGAME = 10;

    public static int serverState = WAITING; // waiting for 3 players
    public static int playerNum = 0;
    public static void main(String[] args) {
        
        try {
            ServerSocket serverSock = new ServerSocket(6666);
            System.out.print("Server started...");
            while (true) {
                Socket cSock = serverSock.accept();
                ClientConnection chat = new ClientConnection(cSock);
                Thread chatthread = new Thread(chat);
                chatthread.start();
            }
        } catch (IOException e) { 
            System.out.println("disconnected..."); 
        }
    }
}

class ClientConnection implements Runnable {
    private Socket socket;
    private int playerIndex = 0;
    
    public ClientConnection(Socket socket) { this.socket = socket; }
    public void run() {
        try {
            DataOutputStream clientOutput = new DataOutputStream(socket.getOutputStream());
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                System.out.println("Current Player Number: " + GameServer.playerNum);
                String clientText = clientInput.readLine();
                if(playerIndex != 0){
                    System.out.println("From  Player"+ playerIndex + ": " + clientText);
                }else{
                    System.out.println("From  Client: " + clientText);
                }
                
                //read request from players
                //Waiting
                switch(GameServer.serverState){
                    case 0:{
                        if(clientText.equals("Requesting to join")){
                            clientOutput.writeUTF("You joined the game!");
                            //create player info
                            playerIndex = GameServer.playerNum;
                            GameServer.players.add(new Player());
        
                            GameServer.playerNum++;
                            playerIndex = GameServer.playerNum;
        
                            if(GameServer.playerNum >= 3){
                                GameServer.serverState = GameServer.STARTGAME; //start the game
                            }
                        }else if(clientText.equals("Can I go to home screen?")){
                            clientOutput.writeUTF("No, do not go to home screen");
                        }
                        break;
                    }
                    case 1:{
                        if(clientText.equals("Requesting to join")){
                            clientOutput.writeUTF("There are already 3 players!");
                            clientInput.close();
                            socket.close();
                        }else if(clientText.equals("Can I go to home screen?")){
                            clientOutput.writeUTF("Yes, go to home screen");
                        }else if(clientText.equals("Record the score")){
                            clientOutput.writeUTF("Sent your score, please");
                            String score = clientInput.readLine();
                            int receiveGameIndex = Integer.parseInt(("" + score.charAt(0)));
                            String receiveScore = score.substring(3);
                            GameServer.players.get(playerIndex-1).setScore(receiveGameIndex, receiveScore);
                            clientOutput.writeUTF("Your " + receiveGameIndex + " game score is: " + receiveScore);
                        }
                        break;
                    }
                }
                if(GameServer.serverState == GameServer.ENDGAME){
                    break;
                }
            }
            clientInput.close();
            socket.close();

        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
}