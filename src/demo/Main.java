package demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//define data Structure
		Random random = new Random();
		char[][] rootData = { 
                {'_', '_','_'},
                {'_','_','_'},
                {'_', '_','_'},
               };
		//printState(rootData);
		MyTreeNode<char[][]> root= new MyTreeNode<char[][]>(rootData,(float) 0);
		char[][] gameState = Arrays.stream(root.getData()).map(char[]::clone).toArray(char[][]::new);
		
	
		
		for(int x=0;x<5000000;x++) {
			
		while(terminate(root.getData())==0) {
			gameState = Arrays.stream(root.getData()).map(char[]::clone).toArray(char[][]::new);
			
			//if map is empty, make a random move, otherwise
			//make either a random move or a greedy move 50/50
			List<MyTreeNode> possibleMoves=root.getChildren();
			/*if(possibleMoves.size()!=0)
				for(int i=0;i<possibleMoves.size();i++) {System.out.println("Hello:"+Arrays.deepToString((Object[]) possibleMoves.get(i).getData()));
				
				}*/
			if(possibleMoves.isEmpty()) {
				MyTreeNode parent=root;
				
				int firstRand;
				int secondRand;
				while(gameState[firstRand=random.nextInt(3)][secondRand=random.nextInt(3)]!='_');
				gameState[firstRand][secondRand]='X';
				char[][] copy = Arrays.stream(gameState).map(char[]::clone).toArray(char[][]::new);
				MyTreeNode newState=new MyTreeNode<char[][]>(copy,(float) 0.00);
				
				
				parent.addChild(newState);
				
				root=newState;
			}else {
				double randomDouble = Math.random();
				if(randomDouble<0.1) {
					MyTreeNode<char[][]> maxState= possibleMoves.get(0);
					for(int i=0;i<possibleMoves.size();i++) {
						//iterate through the possible moves and 
						//find the one with highest state value
						if(possibleMoves.get(i).getValue()>maxState.getValue()) {
							maxState=possibleMoves.get(i);
						}
					}
					root=maxState;
					
				}else {
					MyTreeNode parent=root;
					int firstRand;
					int secondRand;
					while(gameState[firstRand=random.nextInt(3)][secondRand=random.nextInt(3)]!='_');
					gameState[firstRand][secondRand]='X';
					possibleMoves=parent.getChildren();
					int in=-1;
					for(int i = 0;i<possibleMoves.size();i++) {
						if(Arrays.deepEquals((Object[]) possibleMoves.get(i).getData(), gameState)) {
							//System.out.println("yes");
							in=i;
							break;
						}
					}
					if(in ==-1) {
						//System.out.println("no");
						char[][] copy = Arrays.stream(gameState).map(char[]::clone).toArray(char[][]::new);
						MyTreeNode newState=new MyTreeNode<char[][]>(copy,(float) 0.00);
						
						parent.addChild(newState);
						root=newState;
					}else
					{
							root=possibleMoves.get(in);
					}
				}
			}
			if(terminate(root.getData())!=0)break;
			//printState(root);
			//Noughts makes random move and we add this to the tree
			//printState(root);
			gameState = Arrays.stream(root.getData()).map(char[]::clone).toArray(char[][]::new);
			
			MyTreeNode parent=root;
			int firstRand;
			int secondRand;
			while(gameState[firstRand=random.nextInt(3)][secondRand=random.nextInt(3)]!='_');
			gameState[firstRand][secondRand]='O';
			possibleMoves=parent.getChildren();
			int in=-1;
			for(int i = 0;i<possibleMoves.size();i++) {
				if(Arrays.deepEquals((Object[]) possibleMoves.get(i).getData(), gameState)) {
					//System.out.println("yes");
					in=i;
					break;
				}
			}
			if(in ==-1) {
				char[][] copy = Arrays.stream(gameState).map(char[]::clone).toArray(char[][]::new);
				MyTreeNode newState=new MyTreeNode<char[][]>(copy,(float) 0.00);
				//System.out.println("no");
				parent.addChild(newState);
				root=newState;
			}else
			{
					root=possibleMoves.get(in);
			}
			//printState(root);
			
			
		}
		if(terminate(root.getData())==2) {
			//System.out.println("draw");
			while(root.getParent()!=null) {
				//System.out.println("yes");
				root.newVisit();
				root.newDraw();
				//printState(root);
				root=root.getParent();
			}
		}
		else{
			//System.out.println("terminate");
			
			if(terminate(root.getData())==1) {
				while(root.getParent()!=null) {
					//System.out.println("yes");
					root.newVisit();
					root.newWin();
					//printState(root);
					root=root.getParent();
				}
			}else {
				while(root.getParent()!=null) {
					//System.out.println("yes");
					root.newVisit();
					root.newLoss();
					//printState(root);
					root=root.getParent();
				}
			}
		}root.newVisit();
		///???
		
		//printState(root);
		}
		
		//let person play
		//ai plays greedily
		while(true) {
			
			while(terminate(root.getData())==0) {
				gameState = Arrays.stream(root.getData()).map(char[]::clone).toArray(char[][]::new);
				//if map is empty, make a random move, otherwise
				//make either a random move or a greedy move 50/50
				List<MyTreeNode> possibleMoves=root.getChildren();
				if(possibleMoves.size()!=0)
					System.out.println(Arrays.deepToString((Object[]) possibleMoves.get(0).getData()));
				if(possibleMoves.isEmpty()) {
					MyTreeNode parent=root;
					
					int firstRand;
					int secondRand;
					while(gameState[firstRand=random.nextInt(3)][secondRand=random.nextInt(3)]!='_');
					gameState[firstRand][secondRand]='X';
					char[][] copy = Arrays.stream(gameState).map(char[]::clone).toArray(char[][]::new);
					MyTreeNode newState=new MyTreeNode<char[][]>(copy,(float) 0.00);
					
					
					parent.addChild(newState);
					
					root=newState;
				}else {
						MyTreeNode<char[][]> maxState= possibleMoves.get(0);
						for(int i=0;i<possibleMoves.size();i++) {
							//iterate through the possible moves and 
							//find the one with highest state value
							if(possibleMoves.get(i).getValue()>maxState.getValue()) {
								maxState=possibleMoves.get(i);
							}
						}
						root=maxState;
						
					
				}
				if(terminate(root.getData())!=0)break;
				//printState(root);
				//Noughts makes random move and we add this to the tree
				printState(root);
				gameState = Arrays.stream(root.getData()).map(char[]::clone).toArray(char[][]::new);
				
				MyTreeNode parent=root;
				Scanner input = new Scanner(System.in);
				int firstRand = input.nextInt();
				int secondRand = input.nextInt();
				possibleMoves=parent.getChildren();
				while(gameState[firstRand][secondRand]!='_');
				gameState[firstRand][secondRand]='O';
				boolean in=false;
				
				for(int i = 0;i<possibleMoves.size();i++) {
					if(Arrays.deepEquals((Object[]) possibleMoves.get(i).getData(), gameState)) {
						root=possibleMoves.get(i);
						in=true;
						break;
					}
				}
				if(in ==false) {
					char[][] copy = Arrays.stream(gameState).map(char[]::clone).toArray(char[][]::new);
					MyTreeNode newState=new MyTreeNode<char[][]>(copy,(float) 0.00);
					
					parent.addChild(newState);
					root=newState;
					}
				
				
				printState(root);
				
				
			}
			if(terminate(root.getData())==2) {
				System.out.println("draw");
				
			}
			else{
				if(terminate(root.getData())==1) {
					System.out.println("you lose");
				}else {
					System.out.println("you win");
				}	
				
			}
			while(root.getParent()!=null) {
				root=root.getParent();
			}
		}
		
		
		
		//profit
	}
	public static int terminate(char[][] gameState) {
		//check horizontals
		for(int i =0;i<3;i++)
			if(Arrays.equals(gameState[i],new char[] {'X','X','X'})) {
				return 1;}
			else if(Arrays.equals(gameState[i],new char[] {'O','O','O'})) {
				return -1;
			}
			
		//check verticals
		for(int i =0;i<3;i++)
			if(gameState[0][i]==('X')&& gameState[1][i]==('X')&&gameState[2][i] ==('X')) {
				return 1;}
			else if(gameState[0][i]==('O')&& gameState[1][i]==('O')&&gameState[2][i] ==('O'))
				return -1;
		
		//check diagonals
		if(gameState[0][0]==('X')&& gameState[1][1]==('X')&&gameState[2][2] ==('X')) {
			return 1;}
		else if(gameState[0][2]==('X')&& gameState[1][1]==('X')&&gameState[2][0] ==('X'))
			return -1;
		
		if(gameState[0][0]==('O')&& gameState[1][1]==('O')&&gameState[2][2] ==('O')) {
			return -1;}
		else if(gameState[0][2]==('O')&& gameState[1][1]==('O')&&gameState[2][0] ==('O'))
			return -1;
		
		
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(gameState[i][j]=='_')
					return 0;
		
		return 2;
	}
	
	public static void printState(MyTreeNode<char[][]> root) {
		char[][] state = (char[][]) root.getData();
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print("| "+state[i][j]+" ");
			}System.out.println("|");
				
		}
		System.out.println("Score: "+ root.getValue());
		System.out.println("Visit Count: "+ root.getVisit());
		if(root.getParent()!=null)
			System.out.println("Parent:"+Arrays.deepToString((Object[]) root.getParent().getData()));
		List<MyTreeNode> possibleMoves=root.getChildren();
		if(possibleMoves.size()!=0)
			for(int i=0;i<possibleMoves.size();i++) {System.out.println("Hello:"+Arrays.deepToString((Object[]) possibleMoves.get(i).getData())+possibleMoves.get(i).getValue());
			
			}
	}
}
