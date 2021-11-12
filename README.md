# <ins>**Goal-Stack-Planner**</ins>
# Description
This is a software that generates plans from the initial and goal state (that should be provided) using the Goal Stack technique. It also shows the status of the stacks at each stage of the conversion. 

The code is written through **JAVA**.

## Objective

### [Operator](https://github.com/blank0826/Goal-Stack-Planner/blob/master/Operator.java)
----
This class stores the type of operations that need to be performed on the stack to make it progressively reach the goal state.<br/>
### **<ins>Operations</ins>**
 **1. S (X, Y)<br/>**
  Put block X on top of block Y. For this operation, the top of Y must be clear and X should be in the robot’s arm. <br/><br/>
  **2. US (X, Y) <br/>**
  Pickup block X from block Y. For this operation, the arm must be empty and the top of X must be clear.<br/><br/>
  **3. PU (X) <br/>**
  Pickup X from the table and hold it. For this operation, the arm must be empty and the top of X must be clear.<br/><br/>
  **4. PD (X) <br/>**
  Put down X on the table. For this operation, the arm must be holding X before putting it down. <br/><br/>

### **<ins>Member Functions</ins>**
  **1. ArrayList<Predicate> getPre_C ()**<br/>
  It sets the predicates to be performed according to the operation.<br/><br/>
  **2.  ArrayList<Predicate> getInsList ()**<br/>
  This function inserts new predicates in the current state depending upon the current goal state.<br/><br/>
  **3.  ArrayList<Predicate> getRemoveList ()**<br/>
  This removes the old predicates from the current state depending upon the current goal state.<br/><br/>


### [Block World Program](https://github.com/blank0826/Goal-Stack-Planner/blob/master/BlockWorldProgram.java)
----
  **<ins>This file contains 4 classes:</ins>** <br /><br />
  **<ins>1. Block**</ins><br/>
  This class is being used to signify a block. It stores the notation of a particular block.<br/><br/>
  
  **<ins>2. Predicate**</ins><br/>
  This class stores and sets the type of predicate on a single block or on two blocks.<br/><br/>
  
  #### **Predicates**<br/>
   **1.	ON (X, Y)**<br/>
    Block X is on block Y.<br/><br/>
   **2.	ONT (X)**<br/>
    Block X is on the table.<br/><br/>
   **3.	CL (X)**<br/>
    Top of block X is clear.<br/><br/>
   **4.	HOLD (X)**<br/>
    The robot arm is holding block X.<br/><br/>
   **5.	AE**<br/>
    Robot arm is empty.<br/><br/>

  
  **<ins>3. Goal**</ins><br/>
  This class is being used to store the final goal desired by the user. It stores all the elements using an arraylist and keeps the operation to be done.<br/><br/>
  
  **<ins>4. BlockWorldProgram**</ins><br/>
  This is the main class of the application. It contains the main function and a static function to create stacks by providing appropriate predicates. It also contains two array   lists, which are being used to store the initial and the goal state. <br/><br/>
  The main function keeps in check all the intermediate stages, it checks what is the state of the current goal and set predicates for it accordingly. Then operator class is       called from which we get the operations that need to be performed in order to make our current stage reach the current goal state. This process of simultaneous insertion,       removal of predicates, and operations is performed until we reach the goal state.<br/><br/>

# Local Setup

## Pre-Requisites
An IDE that supports Java.
## Installation and Execution
1. Pull this code into any folder.<br />
2. Open this folder in your preferred IDE.<br />
3. Build the Project.<br />
4. Run the [BlockWorldProgram](https://github.com/blank0826/Goal-Stack-Planner/blob/master/BlockWorldProgram.java) file.<br />

# Screenshots
<img src="https://user-images.githubusercontent.com/33955028/141425506-8601c350-959c-4e2f-8e59-efdac7d18b87.png" width="600" height="525">
<img src="https://user-images.githubusercontent.com/33955028/141425549-3ab7c648-cf70-43b5-be1e-e1533cffff84.png" width="500" height="525">
<img src="https://user-images.githubusercontent.com/33955028/141425600-a3cbab38-f1c5-4f2e-b890-e3838464e2c8.png" width="450" height="300">


# Contact
## [Aditya Srivastava](mailto:aditya26052002@gmail.com?subject=GitHub)
