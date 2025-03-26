# 2048 Game

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#techstack)
- [Installaton & Setup](#prerequisites)

## Overview
This project was developed in my spare time. I deciced to create a clone of the popular cookie clicker web game, but usinign java instead of javascript. When developing this game i have focusd on using OOP and following a MVC pattern. These are principles i have learned in courses in my studies and also wanted to improve in my spare time. The game is still under development but the core gameplay is finished.

### Coockie Clicker Game
![image](https://github.com/user-attachments/assets/74ca1de9-0edd-4528-8b66-44f8b2a7b84b)

### How to play

Click on the "Click Me" button to recieve cookies. Spend your cookies on buildings to increas Cps (cookies per second), upgrades (to increase multipler) and on power ups (increase buildings multiplier).
You can save your games but also reset if you want. If you save the game, quit and start the game will load the saved game state and also calculate the amount of cookies earned while you were away.


## Features

- Purchase buildings, Upgrades and Power Ups
- Save game state (and calculate offline cookies earned)
- Reset game state

## Tech Stack
- Java
- JUnit
- Maven

## Installation & Setup

### Prerequisites

Before you begin, ensure you have the following installed on your machine:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (version 11 or later)
- [Maven](https://maven.apache.org/install.html)

 ### Setup

 1. Navigate to the `game` directory:

 ```bash
 cd game
 ```

2. Build the project:

 ```bash
 mvn clean package
 ```

3. Run the game:
 ```bash
 cd /taget
 java -jar game-1.jar
 ```
