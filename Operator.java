package assn4ai;

import java.util.*;

class Operator{

	int variation;  // variation for operator:- 1 for S(X,Y), 2 for US(X,Y), 3 for PU(X), and 4 for PD(X)
	Block block1; //reference to 1st block
	Block block2; //reference to 2nd block
	ArrayList<Predicate> pre_C; // list of all the predicates necessary for an operation.
	ArrayList<Predicate> insList; // list of all the predication to be inserted into a stage.
	ArrayList<Predicate> removeList; // list of all the predicates to be removed from a stage.

	Operator(int variation, Block block1, Block block2){ // constructor if all the variations and blocks are provided.
		this.variation = variation;
		this.block1 = block1;
		this.block2 = block2;
		pre_C = new ArrayList<Predicate>();
		insList = new ArrayList<Predicate>();
		removeList = new ArrayList<Predicate>(); 
	}

	Operator(int variation, Block block1){ // constructor if only the variation and 1 block is provided.
		this.variation = variation;
		this.block1 = block1;
		this.block2 = null;
		pre_C = new ArrayList<Predicate>();
		insList = new ArrayList<Predicate>();
		removeList = new ArrayList<Predicate>(); 
	}

	ArrayList<Predicate> getPre_C(){ //sets the predicates to be performed according to the operation.
                
                if(variation == 1){
                    pre_C.add(new Predicate(3, block2));
                    pre_C.add(new Predicate(4, block1));
                }else if(variation == 2){
                    pre_C.add(new Predicate(1, block1, block2));
                    pre_C.add(new Predicate(3, block1));
                    pre_C.add(new Predicate(5));
                }else if(variation == 3){
                    pre_C.add(new Predicate(2, block1));
                    pre_C.add(new Predicate(3, block1));
                    pre_C.add(new Predicate(5));
                }else if(variation == 4){
                    pre_C.add(new Predicate(4, block1));
                }else {
                    System.out.println("Kindly check the operator called again.");
                }
                
		return pre_C;
	}

	ArrayList<Predicate> getInsList(){ // insert new predicates depending upon the current goal state
            
            if(variation == 1){
                insList.add(new Predicate(5));
                insList.add(new Predicate(1, block1, block2));
                insList.add(new Predicate(3, block1));
            }else if(variation == 2){
                insList.add(new Predicate(4, block1));
                insList.add(new Predicate(3, block2));
            }else if(variation == 3){
                insList.add(new Predicate(4, block1));
            }else if(variation == 4){
                insList.add(new Predicate(2, block1));
                insList.add(new Predicate(5));
                insList.add(new Predicate(3, block1));
            }else {
                System.out.println("Kindly check the operator called again.");
            }
                
            return insList;
	}

	ArrayList<Predicate> getRemoveList(){ // remove old predicates depending upon the current goal state
            
                if(variation == 1){
                    removeList.add(new Predicate(3, block2));
                    removeList.add(new Predicate(4, block1));
                }else if(variation == 2){
                    removeList.add(new Predicate(1, block1, block2));
                    removeList.add(new Predicate(5));
                    removeList.add(new Predicate(3, block1));
                }else if(variation == 3){
                    removeList.add(new Predicate(2, block1));
                    removeList.add(new Predicate(5));
                    removeList.add(new Predicate(3, block1));
                }else if(variation == 4){
                    removeList.add(new Predicate(4, block1));
                }else {
                    System.out.println("Kindly check the operator called again.");
                }
                
		return removeList;
	}

}