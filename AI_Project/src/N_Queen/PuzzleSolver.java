package N_Queen;

public class PuzzleSolver {
	public static void main(String[] args){
		Solver solver = new Solver(50);
		
		solver.curS.info();
		State goal = solver.FindGoal();
		goal.info();
	}
}

class State{
	int N;
	int[] location;  	// index of Row for each column
	int[] attack;		// the number of attack which queen in each column has
	int totalAttack;	// sum of attacks, constraint value
	int maxColumn;		// the column whose queen has biggest number of attack
	int prevMoveColumn;
	
	State(int[] location, int prev){
		this.location = location;
		prevMoveColumn = prev;
		N = location.length;
		attack = new int[N];
		maxColumn = -1;
		totalAttack = numAttack();
		
	}
	
	int numAttack(){  	// return total attack on each other, and renew the attack of each queen.
		int totalAttack = 0;
		int maxNumAttack = 0;
		
		for(int col=0 ; col<N ; col++){		// iteration of queens in each column
			int curRow = location[col];		// row = curRow, column = col
			int attackLocation;				// attack location is determined by row and column of the current queen.
			
			
			for(int compCol=0 ; compCol<N ; compCol++){		// iteration for counting number of attack between (location[col], col) and (location[compCol], compCol)
				if(compCol != col){
					//row
					attackLocation = curRow;
					if(location[compCol]==attackLocation){
						attack[col]++;
						totalAttack++;
					}
					// column, possibility of attack in column is zero because we locate a queen in each column
					 
					// right diagonal
					attackLocation = curRow - (-1)*(col-compCol);
					if(location[compCol]==attackLocation){
						attack[col]++;
						totalAttack++;
					}
					 
					// left diagonal
					attackLocation = curRow - (col-compCol);
					if(location[compCol] == attackLocation){
						attack[col]++;
						totalAttack++;
					}
				}
			}
			if(attack[col] > maxNumAttack){
				if(col != prevMoveColumn){
					maxColumn = col;
					maxNumAttack = attack[col];
				}
				else{
					//System.out.println(col +" is column number of queen which is just moved");
				}
			}
		}
		return totalAttack;
	}
	
	void printState(){
		for(int i=0 ; i<N ; i++){
			for(int j=0 ; j<N ; j++){
				if(location[j] == i)
					System.out.format("%2d", 1);
				else
					System.out.format("%2d", 0);
			}
			System.out.println();
		}
		System.out.println("Total Attack : "+totalAttack+"\n");
	}
	 
	void printLocationList(){
		System.out.println("  Row   Column :");
		for(int i=0 ; i<location.length ; i++){
			System.out.format("  %2d     %2d\n", location[i], i);
		}
		System.out.println();
	}
	 
	void printAttackList(){
		System.out.println(" Attack  Column");
		for(int i=0 ; i<attack.length ; i++){
			System.out.format("  %2d     %2d\n", attack[i], i);
		}
		System.out.println();
	}
	
	void info(){
		System.out.println("=================================");
		printState();
//		printLocationList();
		printAttackList();
		if(maxColumn != -1){ System.out.println("Column of Max Attack = " + maxColumn +"("+ attack[maxColumn] +")");}
		System.out.println("=================================");
	}
}

class Solver{
	int N;
	State curS;
	static int maxStep = 10000;
	 
	Solver(int N){
		this.N = N;
		int[] location = new int[N];
		for(int i=0 ; i<N ; i++){
			location[i] = (int)(Math.random()*N);
		}
		curS = new State(location, -1);
	}
	 
	 
	 State FindGoal(){
		 int step = 0;
		 while(step < maxStep){
			 // if there is no attack, then current state is goal state
			 if(curS.totalAttack == 0) {
				 System.out.println("answer is found. number of steps : " + step);
				 return curS;
			 }
			 if(step%1000 == 0) System.out.println("iteration "+ step);
			 // look up next steps by changing the location of queen which has the maximum attack value
			 State[] nextStep = new State[N-1];
			 int maxColumn = curS.maxColumn;
			
			 int j = 0;
			 //System.out.println("NEXT STEPS--------------------");
			 for(int i=0 ; i<N ; i++){
				 int[] newLocation = curS.location.clone();
				 int prevRow = curS.location[maxColumn];
				 if(i != prevRow){
					newLocation[maxColumn] = i;
					nextStep[j] = new State(newLocation, maxColumn);
					//nextStep[j].printState();
					j++;
				 }
			 }
			 //System.out.println("-----------------------------");
			 
			 // Select next step which has total attack number less than equal to attack number of current
			 int minAttack = Integer.MAX_VALUE;
			 //int curAttack = curS.totalAttack;
			 int selectedIndex = -1;
			 for(int i=0 ; i<nextStep.length ; i++){
				 int nextAttack = nextStep[i].totalAttack;
				 if(nextAttack < minAttack){ //&& nextAttack <= curAttack){
					 minAttack = nextAttack;
					 selectedIndex = i;
				 }
			 }
			 //System.out.println(selectedIndex + " is selected");
			 curS = nextStep[selectedIndex];
			 
			 step++;
			 //curS.info();
		 }
		 return null;
	 }
}