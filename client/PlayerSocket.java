import java.io.*;
import java.net.*;
import java.util.Scanner;


public class PlayerSocket {
    //player state
    private static int NOT_JOINED = 0;
    private static int JOINED = 1;
    //private final int NOT_JOINED = 0;

    static int playerState = NOT_JOINED;

public static void main(String[] args) {
    
    try {
    // Connecting to server on port 8000
    Socket connectionSock = new Socket("140.116.115.231", 6666);
    DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream());
    DataInputStream serverInput = new DataInputStream(connectionSock.getInputStream());
    // Connection made, sending name.;
    while (true) {
        String msg = "";
        // Text to the server
        switch(playerState){
            case 0:{
                msg = "Requesting to join";
                break;
            }
            case 1:{
                while(true){

                }

            }
            default:{
                msg = "";
                break;
            }
        }
        if(!msg.equals("")) serverOutput.writeBytes(msg + "\n");

        String serverReply = serverInput.readUTF();
        System.out.println("from server: " + serverReply);
        //Analyze server's reply
        if(serverReply.equals("There are already 3 players!")){
            break;
        }else if(serverReply.equals("You joined the game!")){
            playerState = JOINED;
        }
    }
    serverOutput.close();
    connectionSock.close();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
}
