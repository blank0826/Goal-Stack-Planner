# <ins>**Goal-Stack-Planner**</ins>
# Description
This is a software that generates plans from the initial and goal state (that should be provided) using the Goal Stack technique. It also shows the status of the stacks at each stage of the conversion. 

The code is written through **JAVA**.

## Objective

### 1. [AlreadyOccupied](https://github.com/blank0826/AI-CheckersGame/blob/master/AlreadyOccupiedException.java)
----
This class gives an exception when the piece is already occupied.<br />

### 2. [Checkers](https://github.com/blank0826/AI-CheckersGame/blob/master/Checkers.java)
----
This class initializes the game board. it sets properties for the javaFX UI and adds menu giving the option of Algorithms and Settings.<br /><br />
**1. <ins>Algorithms:-</ins>** Random and Mini-max Algorithms.<br /><br/>
**2. <ins>Settings:-</ins>** Reset and Exit.<br />

### 3. [Logic](https://github.com/blank0826/AI-CheckersGame/blob/master/Logic.java)
----
This is the main class of the Checkers Game where the whole game takes place updating the board, moving pieces, and making all the algorithm-based decisions.

### **<ins>Member Functions</ins>**
**1.	Logic (int algoNum)**<br />

Constructor to initialize the board, maintain what functions to be called whenever a piece is moved or <br />

selected, display appropriate messages after each move is played.

**2.	public chooseAlgo(int algo, char[][] game, int depth, String player)**<br />

Function defined so as to switch between the two algorithms.


# Local Setup

## Pre-Requisites
An IDE that supports Java.
## Installation and Execution
1. Pull this code into any folder.<br />
2. Open this folder in your preferred IDE.<br />
3. Build the Project.<br />
4. Run the [BlockWorldProgram](https://github.com/blank0826/Goal-Stack-Planner/blob/master/BlockWorldProgram.java) file.<br />

# Screenshots of the Gameplay
<img src="https://user-images.githubusercontent.com/33955028/141425506-8601c350-959c-4e2f-8e59-efdac7d18b87.png" width="600" height="525">
<img src="https://user-images.githubusercontent.com/33955028/141425549-3ab7c648-cf70-43b5-be1e-e1533cffff84.png" width="500" height="525">
<img src="https://user-images.githubusercontent.com/33955028/141425600-a3cbab38-f1c5-4f2e-b890-e3838464e2c8.png" width="450" height="300">


# Contact
## [Aditya Srivastava](mailto:aditya26052002@gmail.com?subject=GitHub)
