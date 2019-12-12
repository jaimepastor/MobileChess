package com.example.mobilechess;
import java.util.*;

public class Game_Class{
	
	public static boolean is_check() {		//is_check method for check condition in chess
	
		Board_Class board_obj=new Board_Class();
		boolean ret_flag=false;
		int x=0,y=0;
		
		for(int i=0;i<8;i++) {		// loop for getting king's location
			for(int j=0;j<8;j++) {
				
				if(board_obj.get_board()[i][j]!=null) {
					if(board_obj.get_board()[i][j].color!=board_obj.player_color&&board_obj.get_board()[i][j].name=="king") {
						x=i;y=j;
						//System.out.println("x="+x+"y="+y);
					}
				}	
			}
		}
		
		boolean bool=false;
		for(int i=0;i<8;i++) {		//loop for checking opposite can attack or not
			for(int j=0;j<8;j++) {
				
				if(board_obj.get_board()[i][j]!=null&&(board_obj.get_board()[i][j].color!=board_obj.get_board()[x][y].color)) {
					bool =board_obj.get_board()[i][j].move_piece(i, j, x, y);
					//System.out.println("i="+i+"j="+j);
					//System.out.println(bool);
					if(bool==true) {
						ret_flag=true;
						break;
					}
				}
				
			}
		}
		
		return ret_flag;
	}		//end of is_check function
	
	
	
	public static boolean is_checkmate() {		//method for is_method
		boolean ret_flag=false;
		Board_Class board_obj=new Board_Class();
		
		for(int i=0;i<8;i++) {			//loop for getting board piece
			for(int j=0;j<8;j++) {
				
			Piece_Class piece_obj=board_obj.get_board()[i][j];		//access piece from chess board
				
			if(piece_obj!=null) {
					
					for(int k=0;k<8;k++) {
						for(int m=0;m<8;m++) {
							//System.out.println("i called piece");
							//System.out.println("move is called for "+i+","+j+","+k+","+m+" piece type "+piece_obj.color);
							board_obj.change_turn();
							//System.out.println("Turn is :"+board_obj.player_color);
							
							boolean  flag=false;
							if(piece_obj.color==board_obj.player_color) {
								flag=piece_obj.move_piece(i, j, k, m);
							}
							
							//System.out.println(flag);
							
							if(flag==true) {
								
								//System.out.println("for a move is possible for: "+piece_obj.symbol +" turn is ="+board_obj.player_color);
								board_obj.set_piece(k, m, piece_obj);
								board_obj.set_piece(i, j, null);
								
								board_obj.change_turn();
								boolean temp_flag=is_check();
								
								
								if(temp_flag==true) {
									//System.out.println("can not prevent");
								}else {
									//System.out.println("we can prevent");
									ret_flag=true;
									//board_obj.show_board();
								}
								
								
								board_obj.set_piece(k, m, null);
								board_obj.set_piece(i, j, piece_obj);
								
								
							}else {
								board_obj.change_turn();
							}
								
						}
					}
							
			}
			
			}
		}
		
		if(ret_flag==true) {
			return false;
		}else {
			return true;
		}
	}		//end of checkMate function

}		//end of game class
