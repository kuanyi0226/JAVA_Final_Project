import java.io.*;
import java.net.*;
import java.util.ArrayList;
public class GameServer {
    //Record the players
    public static ArrayList<Player> players = new ArrayList<Player>();
    //server state
    public static int WAITING = 0;
    public static int HOMEPAGE = 1;

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
    private int playerIndex;
    
    public ClientConnection(Socket socket) { this.socket = socket; }
    public void run() {
        try {
            DataOutputStream clientOutput = new DataOutputStream(socket.getOutputStream());
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                System.out.println("Current Player Number: " + GameServer.playerNum);
                String clientText = clientInput.readLine();
                System.out.println("From  Client: " + clientText);
                //read request from players
                if(GameServer.serverState != GameServer.WAITING){
                    if(GameServer.playerNum >= 3){
                        clientOutput.writeUTF("There are already 3 players!");
                        break;
                    }
                }else if(clientText.equals("Requesting to join")){
                    clientOutput.writeUTF("You joined the game!");
                    //create player info
                    playerIndex = GameServer.playerNum;
                    GameServer.players.add(new Player());

                    GameServer.playerNum++;

                    if(GameServer.playerNum >= 3){
                        GameServer.serverState = GameServer.HOMEPAGE; //start the game
                    }
                }
                
            }
            clientInput.close();
            socket.close();

        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
}