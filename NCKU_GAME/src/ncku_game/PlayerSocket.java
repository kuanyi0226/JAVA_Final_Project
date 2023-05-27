package ncku_game;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.*;



public class PlayerSocket {
    //player state
    public static int NOT_JOINED = 0;
    public static int JOINED = 1;
    public static int HOME_SCREEN = 2;
	public static int GAMIMG = 3;

    static int playerState = NOT_JOINED;
    static int currentGame = 1;
	static private String tempScore;
    
    //create init window
    static WaitingScreen waitingScreen = new WaitingScreen();
    static String msg = "";   
    
    //signal
    public static boolean sentToServer = false;
    public static boolean receiveFromServer = false;
    public static boolean changePage = true;
	

	public static void main(String[] args) {
		    try {
		    // Connecting to server on port 8000(192.168.56.1/140.116.115.231)
		    Socket connectionSock = new Socket("192.168.56.1", 6666);
		    DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream());
		    DataInputStream serverInput = new DataInputStream(connectionSock.getInputStream());
		    // Connection made, sending name.;
		    while (true) {
		    	//Page exchange
		    	if(changePage) {
		    		switch(playerState){
		            case 0:{//not joined
		            	waitingScreen.setVisible(true);
		            	break;
		            }
		            case 1:{//joined
		            	changePage = false;
		        		break;
		            }
		            case 2:{//home screen
						changePage = false;
						switch(currentGame){
							case 1:{
								waitingScreen.setVisible(false);
		            			waitingScreen = null; //destroy
								HomeScreen1 homeScreen1 = new HomeScreen1();
								homeScreen1.setVisible(true);
								
								//wait for 3 sec
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								homeScreen1.setVisible(false);
								homeScreen1 = null;//destroy
								break;
							}
							case 2:{
								HomeScreen2 homeScreen2 = new HomeScreen2();
								homeScreen2.setVisible(true);
								
								//wait for 3 sec
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								homeScreen2.setVisible(false);
								homeScreen2 = null;//destroy
								break;
							}
							case 3:{
								HomeScreen3 homeScreen3 = new HomeScreen3();
								homeScreen3.setVisible(true);
								
								//wait for 3 sec
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								homeScreen3.setVisible(false);
								homeScreen3 = null;//destroy
								break;
							}
						}
		            	//signal
						playerState = GAMIMG;
						receiveFromServer = false;
						sentToServer = false;
						changePage = true;
		            	break;
		            }
					case 3:{//gaming
						switch(currentGame){
							case 1:{
								//join fist game
								GameCode gameCode = new GameCode();
								gameCode.launch();
								while(true) {
									if(Panel.isComplete) {
										
										if(Panel.isFail) {
											try {
												Thread.sleep(3000);
											} catch (InterruptedException e1) {
												e1.printStackTrace();
											}
										}else {
											try {
												Thread.sleep(8000);
											} catch (InterruptedException e1) {
												e1.printStackTrace();
											}
										}
										tempScore = ("" + Panel.elapsedSeconds);//record score
										sentToServer = true;
										receiveFromServer = true;
										changePage = false;
										
										gameCode.setVisible(false);
										gameCode = null;									
										break;
									}
								}
								break;
							}
							case 2:{
								//join fist game
								GameCode gameCode = new GameCode();
								gameCode.launch();
								while(true) {
									if(Panel.isComplete) {
										
										if(Panel.isFail) {
											try {
												Thread.sleep(3000);
											} catch (InterruptedException e1) {
												e1.printStackTrace();
											}
										}else {
											try {
												Thread.sleep(8000);
											} catch (InterruptedException e1) {
												e1.printStackTrace();
											}
										}
										tempScore = ("" + Panel.elapsedSeconds);//record score
										sentToServer = true;
										receiveFromServer = true;
										changePage = false;
										
										gameCode.setVisible(false);
										gameCode = null;									
										break;
									}
								}
								break;
							}
							case 3:{
								break;
							}
						}
					}
		            default:{
		            	
		                break;
		            }
		        }
		    	}
		    	//sent message to server
		    	if(sentToServer) {
		    		msg = "";
		            // Text to the server
		            switch(playerState){
		                case 0:{//not joined
		                    msg = "Requesting to join";
		                    sentToServer = false;
		                    break;
		                }
		                case 1:{//joined
		                	msg = "Can I go to home screen?";
		                	break;
		                }
		                case 2:{ //Home screen
		                	break;
		                }
						case 3:{ //gaming
							playerState = HOME_SCREEN;
							msg = "Record the score";
		                	break;
		                }
		                default:{
		                    msg = "";
		                    break;
		                }
		            }
		            if(!msg.equals("")) serverOutput.writeBytes(msg + "\n");
		    	}
		    	//recieve meassge from server
		    	if(receiveFromServer) {
		    		String serverReply = serverInput.readUTF();
		            System.out.println("from server: " + serverReply);
		            //Analyze server's reply
		            if(serverReply.equals("There are already 3 players!")){
		                break;
		            }else if(serverReply.equals("You joined the game!")){
		                playerState = JOINED;
		                receiveFromServer = true;
		                sentToServer = true;
		            }else if(serverReply.equals("Yes, go to home screen")) {
		            	playerState = HOME_SCREEN;
		            	receiveFromServer = false;
		                sentToServer = false;
		                changePage = true;
		            }
		            else if(serverReply.equals("No, do not go to home screen")) {
		            	receiveFromServer = true;
		                sentToServer = true;
		                changePage = false;
		            }else if(serverReply.equals("Sent your score, please")) {
		            	receiveFromServer = false;
		                sentToServer = false;
						String currentGameString = ("" + currentGame);
						serverOutput.writeBytes(currentGameString + ": " + tempScore + "\n");
		                changePage = true;
						currentGame++;
		            }
		            
		    	}
		        //delay 0.5s
				try {
					Thread.sleep(500);
				} catch (InterruptedException err) {
					err.printStackTrace();
				}
		        
		    }//end of while
		    serverOutput.close();
		    connectionSock.close();
		    
		} catch (IOException e) {
			e.printStackTrace();
	    }
	}//end of main
}//end of class
