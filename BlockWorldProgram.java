package assn4ai;

import java.io.*;
import java.util.*;

class Block{ //class that uniquely identifies each block
	char element; //a single element in the stack

	Block(char element){
            this.element = element;
	}

	public char getBlock(){ // function to retrieve element
            return element;
	}
}

class Predicate {
	int predicateVariation; //5 type of variation:- 1 - ON(X,Y), 2 for ONT(X), 3 for CL(X), 4 for HOLD(X), 5 for AE
	Block block1;   //reference to 1st block
	Block block2;   //reference to 2nd block
        
	Predicate(){ //constructor if no value is provided
            this.predicateVariation = -1;
            block1 = null;
            block2 = null;
	} 
        
	Predicate(int variation){ // constructor is only the variation is provided. Generally used by variation 5
            this.predicateVariation = variation;
            this.block1 = null;
            this.block2 = null;
	}	
        
	Predicate(int variation, Block block1){ //constructor if variation and 1 block is provided. Generally used by variation 2,3,4
            this.predicateVariation = variation;
            this.block1 = block1;
            this.block2 = null;
	}
        
	Predicate(int variation, Block block1, Block block2){//constructor if variation and both blocks are provided. Generally used by variation 1
            this.predicateVariation = variation;
            this.block1 = block1;
            this.block2 = block2;
	}

	@Override
	public boolean equals(Object obj){ //Checks the equality betweent the two predicate objects
            Predicate otherPred = (Predicate)obj;
            int myPred = otherPred.predicateVariation;
            if(myPred != this.predicateVariation) return false;
            if(myPred == 1 && (otherPred.block1.getBlock()==this.block1.getBlock()) && (otherPred.block2.getBlock() == this.block2.getBlock())) return true;
            if(myPred == 2 && (otherPred.block1.getBlock()==this.block1.getBlock()) ) return true;
            if(myPred == 3 && (otherPred.block1.getBlock()==this.block1.getBlock()) ) return true;
            if(myPred == 4 && (otherPred.block1.getBlock()==this.block1.getBlock()) ) return true;
            if(myPred == 5) return true;
            return false;
	}
}

class Goal{  
	ArrayList<Predicate> finalStateList; //hold the final goal
	Operator operatorElement; //keeps in the operation to be done
	int check; // variation of different type of goals:- 1 for compound goal, 2 for single goal, 3 for operator
        
	Goal(ArrayList<Predicate> goalList){ // constructor that sees if the goal is compound or single
            this.finalStateList = goalList;
            this.operatorElement = null;
            if(goalList.size() == 1){ //checks whether a single goal
                check = 2;
            }
            else check = 1;
	}
	Goal(Operator operatorElement){ // constrcutor if only the operation to be perfomed is given.
            this.finalStateList = null;
            this.operatorElement = operatorElement;
            check = 3;
	}
}

public class BlockWorldProgram {
    static ArrayList<ArrayList<Block>> initialState; //initial state of the Block World Problem
    static ArrayList<ArrayList<Block>> finalState; // goal state of the Block World Problem

    // creates the stack by providing with appropriate predicates
    public static ArrayList<Predicate> getStackFormation(ArrayList<ArrayList<Block>> initialState, int sizeOfState) { 
        
        ArrayList<Predicate> startState = new ArrayList<Predicate>(); //creates arraylist of predicates for start state
        
        for (int i = 0; i < sizeOfState; i++) {
            ArrayList<Block> currentStack = initialState.get(i); // get one of the stack from the state
            if (currentStack.size() == 1) { //checks if there is only 1 element in the stack 
                Block currentBlock = (Block) currentStack.get(0);
                startState.add(new Predicate(2, currentBlock));
                startState.add(new Predicate(3, currentBlock));
            } else {
                for (int j = 0; j < currentStack.size() - 1; j++) { //if there are multiple elements assign it with appropriate predicates
                    Block curr_block = (Block) currentStack.get(j);
                    Block next_block = (Block) currentStack.get(j + 1);
                    if (j == 0) {
                        startState.add(new Predicate(2, curr_block)); // ONT condition
                    }
                    startState.add(new Predicate(1, next_block, curr_block));
                    if (j == (currentStack.size() - 2)) {
                        startState.add(new Predicate(3, next_block)); //CL condition
                    }
                }
            }
        }
        startState.add(new Predicate(5)); // sets the arm as empty
        return startState; 
    }

    public static void main(String args[]) throws IOException {

        initialState = new ArrayList<ArrayList<Block>>(); //state for initial stage
        finalState = new ArrayList<ArrayList<Block>>(); //state for goal stage

        //taking in the number of stack for the initial stage
        System.out.println("Enter the number of stacks in initial stage.");
        Scanner scanner = new Scanner(System.in);

        int numberOfInitialStack = scanner.nextInt();
        while (numberOfInitialStack <= 0) {
            System.out.println("Provided number should be positive. ");
            numberOfInitialStack = scanner.nextInt();
        }
        for (int i = 0; i < numberOfInitialStack; i++) {
            initialState.add(new ArrayList<Block>()); // object creation till n
        } 
        
        //taking in the blocks for the initial stage
        System.out.println("Enter the blocks in the initial stack (For a different stack give space in between): ");
        int stackCounter = 0;
        while (stackCounter < numberOfInitialStack) {
            String blocks = scanner.next();
            blocks = blocks.trim();
            for (int i = 0; i < blocks.length(); i++)
                initialState.get(stackCounter).add(new Block(blocks.charAt(i)));
            stackCounter++;
        }

        //taking in the number of stack for the final stage
        System.out.println("Enter the number of stacks in goal stage.");
        int numberOfFinalStack = scanner.nextInt();
        while (numberOfFinalStack <= 0) {
            System.out.println("Provided number should be positive. ");
            numberOfFinalStack = scanner.nextInt();
        }
        for (int i = 0; i < numberOfFinalStack; i++) {
            finalState.add(new ArrayList<Block>());
        }
        
        //taking in the blocks for the final stage
        System.out.println("Enter the blocks in the goal stack (For a different stack give space in between): ");
        stackCounter = 0;
        while (stackCounter < numberOfFinalStack) {
            String blocks = scanner.next().trim();
            for (int i = 0; i < blocks.length(); i++) {
                finalState.get(stackCounter).add(new Block(blocks.charAt(i)));
            }
            stackCounter++;
        }

        ArrayList<Predicate> intialStackState = getStackFormation(initialState, numberOfInitialStack); // stack creation for initial stage
        ArrayList<Predicate> finalStackState = getStackFormation(finalState, numberOfFinalStack); // stack creation for final state
        ArrayList<Goal> intermediateStackState = new ArrayList<Goal>(); //List for have intermediate stack states
        intermediateStackState.add(new Goal(finalStackState));

        while (intermediateStackState.size() != 0) { 
            Goal intermediateGoal = intermediateStackState.get(intermediateStackState.size() - 1); //gets the intermediate goal in which the stack has to be converted
            if (intermediateGoal.check == 1) { //checks if goal state has more than 1 block
                ArrayList<Predicate> goal = (ArrayList<Predicate>) intermediateGoal.finalStateList; //stores the intermediate goal stack
                if (intialStackState.containsAll(goal)) {
                    intermediateStackState.remove(intermediateStackState.size() - 1); // if current goal state already exists just remove it from the intermediate stack
                } else {
                    for (int i = 0; i < goal.size(); i++) {
                        Predicate currentPredicate = (Predicate) goal.get(i); // gets the predicate for the current block in goal
                        if (!intialStackState.contains(currentPredicate)) { // add goals which are not present in the current state
                            ArrayList<Predicate> listOfCurrentPredicate = new ArrayList<Predicate>();
                            listOfCurrentPredicate.add(currentPredicate);
                            intermediateStackState.add(new Goal(listOfCurrentPredicate));
                        }
                    }
                }
            } else if (intermediateGoal.check == 2) { //checks if the goal state has only 1 block
                ArrayList<Predicate> goal = (ArrayList<Predicate>) intermediateGoal.finalStateList; //stores the intermediate goal stack 
                Predicate singlePred = goal.get(0); // gets the predicate for that block
                if (intialStackState.contains(singlePred)) { // checks if goal already exists then just remove it from the intermediate stack
                    intermediateStackState.remove(intermediateStackState.size() - 1);
                } else { 
                    Operator operatorOfInsList = new Operator(5, null);
                    //assigns operator depending on the predicate
                    if (singlePred.predicateVariation == 1) {
                        operatorOfInsList = new Operator(1, singlePred.block1, singlePred.block2);
                    } else if (singlePred.predicateVariation == 2) {
                        operatorOfInsList = new Operator(4, singlePred.block1);
                    } else if (singlePred.predicateVariation == 3) { 
                        Block block = new Block('.');
                        for (int i = 0; i < intialStackState.size(); i++) { //checks if there is any block above the block that has to be unstacked
                            Predicate currentPredicate = (Predicate) intialStackState.get(i);
                            if (currentPredicate.predicateVariation == 1
                                    && currentPredicate.block2.getBlock() == singlePred.block1.getBlock()) { 
                                block = currentPredicate.block1;
                            }
                        }
                        if (block.getBlock() != '.') // if no block above it then just unstack it
                            operatorOfInsList = new Operator(2, block, singlePred.block1);
                        else { // if there is then nothing can be done
                            System.out.println("Given problem cannot be solved.");
                            System.exit(0);
                        }
                    } else if (singlePred.predicateVariation == 4) {
                        Block block = new Block('.');
                        for (int i = 0; i < intialStackState.size(); i++) {//checks if there is any block below the block that we need to perform operation on
                            Predicate currentPredicate = (Predicate) intialStackState.get(i);
                            if (currentPredicate.predicateVariation == 1
                                    && currentPredicate.block1.getBlock() == singlePred.block1.getBlock()) {
                                block = currentPredicate.block2;
                            }
                        }
                        
                        if (block.getBlock() == '.') // if no then just pull up
                            operatorOfInsList = new Operator(3, singlePred.block1);
                        else { // if yes then we have to unstack it
                            operatorOfInsList = new Operator(2, singlePred.block1, block);
                        }
                    } else if (singlePred.predicateVariation == 5) {
                        Block block = new Block('.');
                        for (int i = 0; i < intialStackState.size(); i++) {
                            Predicate currentPredicate = (Predicate) intialStackState.get(i);
                            if (currentPredicate.predicateVariation == 4)
                                block = currentPredicate.block1;
                        }
                        if (block.getBlock() != '.') // if there is any block in the arm then put it down
                            operatorOfInsList = new Operator(4, block);
                        else {
                            System.out.println("Error occured in AE Predicate!");
                            System.exit(0);
                        }
                    } else { // any other predicate given then recognizable by the program
                        System.out.println("Given predicate is not correct!");
                        System.exit(0);
                    }
                    
                    intermediateStackState.remove(intermediateStackState.size() - 1); // remove the goal from the intermediate stack
                    intermediateStackState.add(new Goal(operatorOfInsList)); // push the operators for the goal on the intermediate stack
                    intermediateStackState.add(new Goal(operatorOfInsList.getPre_C())); // push the preconditions as a compound goal on the stack

                }
            } else if (intermediateGoal.check == 3) { // this is executed when instead of the goal state stack operations are given on how to perform the transformation

                Operator operationOnBlock = (Operator) intermediateGoal.operatorElement; //gets the operator
                intermediateStackState.remove(intermediateStackState.size() - 1); // remove the action that has to be performed

                // obtain the new state by appropriate adds and deletes in the initial stage
                ArrayList<Predicate> insList = operationOnBlock.getInsList();
                ArrayList<Predicate> removeList = operationOnBlock.getRemoveList();
                for (int i = 0; i < removeList.size(); i++) {
                    Predicate predicateToBeRemoved = (Predicate) removeList.get(i);
                    int removeIndex = intialStackState.indexOf(predicateToBeRemoved);
                    if (removeIndex != -1)
                        intialStackState.remove(removeIndex);
                }
                for (int i = 0; i < insList.size(); i++) {
                    Predicate predicateToBeInserted = (Predicate) insList.get(i);
                    intialStackState.add(predicateToBeInserted);
                }

                System.out.println("");
                System.out.println("");
                
                //checks for the type of operation that has to be performed
                if (operationOnBlock.variation == 1) {
                    System.out.println("STACK(" + operationOnBlock.block1.getBlock() + ","
                            + operationOnBlock.block2.getBlock() + ")");
                } else if (operationOnBlock.variation == 2) {
                    System.out.println("UNSTACK(" + operationOnBlock.block1.getBlock() + ","
                            + operationOnBlock.block2.getBlock() + ")");
                } else if (operationOnBlock.variation == 3) {
                    System.out.println("PICKUP(" + operationOnBlock.block1.getBlock() + ")");
                } else if (operationOnBlock.variation == 4) {
                    System.out.println("PUTDOWN(" + operationOnBlock.block1.getBlock() + ")");
                }
                // add action to plans
                Stack[] stack = new Stack[intialStackState.size() - 1]; //array for stack for the visual representation of the current state
                boolean state = false; 
                int stackIndex = 0; 
                int armCounter = 0; //checks if arm is holding any block
                
                for (int iter = 0; iter < intialStackState.size(); iter++) { //gets each predicate from the initial stack state to build the stack appropriately.

                    Predicate currentPred = intialStackState.get(iter);

                    if (currentPred.predicateVariation == 1) { // this represent ON(X,Y) so it checks Y in the stack and then Put X above it.
                        Character ch = currentPred.block2.getBlock();
                        for (int t = 0; t <= stackIndex; t++) {
                            if (stack[t].peek() == ch) {
                                stack[t].push(currentPred.block1.getBlock());
                                break;
                            }
                        }

                    } else if (currentPred.predicateVariation == 2) { // this represent ONT(X) to create a new stack and place X on it.
                        if (state == true) {
                            stackIndex++;
                        }

                        state = true;
                        stack[stackIndex] = new Stack<Character>();
                        stack[stackIndex].push(currentPred.block1.getBlock());

                    } else if (currentPred.predicateVariation == 4) { // this represents HOLD(X) to check if their is any block in the arm
                        System.out.println("\nBlock in arm: \n" + currentPred.block1.getBlock());
                        armCounter = 1;
                    }
                }

                if (armCounter == 0) { // if arm does not hold any block
                    System.out.println("\nArm Empty\n");
                }

                System.out.println("-----------CURRENT STATE---------------");

                int maxSize = 0;

                for (int k = 0; k <= stackIndex; k++) { //gets the max size of a stack from the array
                    int size = stack[k].size();
                    maxSize = Math.max(maxSize, size);
                }

                for (int h = maxSize; h > 0; h--) { //shows visual representation of the state in each stage
                    for (int k = 0; k <= stackIndex; k++) {
                        if (stack[k].size() >= h) {
                            System.out.print(stack[k].pop() + " ");
                        } else {
                            System.out.print("  ");
                        }
                        System.out.print(" ");
                    }
                    System.out.println("");
                }
            } else {
                System.out.println("Goal State not given properly."); // if there is any problem
                System.exit(0);
            }
        }
    }
}