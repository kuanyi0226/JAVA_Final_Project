package ncku_game;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import eater.*;
import obj.*;
import utils.*;

public class PlayerSocket {
	public static String[] name_scores = {"NO DATA","NO DATA","NO DATA"};
	public static String[] playerProgress = new String[3];
	public static String playerName = "No Name";
	
    //player state
    public static int NOT_JOINED = 0;
    public static int JOINED = 1;
    public static int HOME_SCREEN = 2;
	public static int GAMIMG = 3;
	public static int SCORE_SCREEN = 4;
	public static int FINISH = 5;

    static int playerState = NOT_JOINED;
    static int currentGame = 1;
	static private String tempScore;
	static private boolean gameOver = false;
    
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
		    while (!gameOver) {
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
							case 4:{
								HomeScreen4 homeScreen4 = new HomeScreen4();
								homeScreen4.setVisible(true);
								boolean allFinish;

								//wait for others finishing
								while(true){
									allFinish = true;
									//sent and read the progress of all the players/ check whether all finished
									serverOutput.writeBytes("Check other players are Finished" + "\n");
									for(int i = 0; i < 3; i++){
										playerProgress[i] = serverInput.readUTF();
										if(!playerProgress[i].substring(0, 10).equals("(Finished)")){
											allFinish = false;
										}
									}
									if(allFinish == true){
										break;
									}
								}
								
								//wait for 2 sec
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								homeScreen4.setVisible(false);
								homeScreen4 = null;//destroy

								sentToServer = true;
								receiveFromServer = true;
								changePage = true;
								playerState = SCORE_SCREEN;

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
								//join first game
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
								//join second game
								GamePlane gamePlane = new GamePlane();
								gamePlane.launch();
								while(true) {
									if (GamePlane.isComplete){
										break;
									}
									tempScore = ("" + GamePlane.score);
									sentToServer = true;
									receiveFromServer = true;
									changePage = false;
									gamePlane.setVisible(false);
									gamePlane = null;
								}
								break;
							}
							case 3:{
								//join third game
								GameFoodie gameFoodie = new GameFoodie();	
								gameFoodie.launch();
								while(true) {
									if(GameFoodie.isComplete) {
										try {
											Thread.sleep(5000);
										} catch (InterruptedException e1) {
											e1.printStackTrace();
										}
										break;
									}
								}
								tempScore = ("" + GameImage2.score);//record score
								sentToServer = true;
								receiveFromServer = true;
								changePage = false;
								
								gameFoodie.closeWindow();
								gameFoodie = null;
								break;		
							}
						}
						break;
					}
					case 4:{ //score screen
						ScorePage scorePage = new ScorePage();
						scorePage.setVisible(true);

						try {
							Thread.sleep(10000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						
						sentToServer = true;
						receiveFromServer = false;
						gameOver = true;
						playerState = FINISH;
						break;
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
		                    msg = "Requesting to join, I'm " + playerName;
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
							msg = "Record the current game score";
							receiveFromServer = true;
		                	break;
		                }case 4:{//score page
							//get all the scores
							msg = "Get all the names & total scores";
							break;
						}case 5:{//finish
							msg = "Finished Game, Disconnect me";
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
						System.out.println("from server: " + serverInput.readUTF());
		                changePage = true;
						currentGame++;
		            }else if(serverReply.equals("Please Read the following 3 names and scores")) {
		            	receiveFromServer = false;
		                sentToServer = false;
						changePage = true;
						for(int i = 0; i <3;i++){
							name_scores[i] = serverInput.readUTF();
						}
		                
						
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
