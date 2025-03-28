# 2048 Game

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#techstack)
- [Installaton & Setup](#prerequisites)

## Overview
This project was developed as part of a school project during my studies. In this project i decieded to recreate the classic 2048 puzzle game, with focus on object-oriented design principles. The player combines tiles stratigically to reach the 2048 tile.
Tiles with matching number are able to merge together if they collide. This creates a new tile with the sum of those two tiles, example: tile 2 and tile 2 merges together and creates tile 4, tile 4 and tile 4 merges together and creates tile 8, and so on...

### Example Start Screen
![image](https://github.com/user-attachments/assets/a3acd27a-ba2f-44d8-9ded-a3a60a6edded)

### Example Loss Screen
![image](https://github.com/user-attachments/assets/2231f901-3a40-4405-ba25-69d223d7e1a8)

### How to play

- Move: Arrow Keys (Right, Left, Up, Down)
- Exit Game: "Escape"
- Restart Game: "r"
- Game is won if tile 2048 is reached
- Game is lost if you can't move any tiles

## Features

- Score tracking
- Menu-System
- Responsive Design

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

 1. Navigate to the `2048` directory:

 ```bash
 cd 2048-Game/2048
 ```

2. Build the project:

 ```bash
 mvn clean compile
 ```

3. Run the game:
 ```bash
 cd /taget
 java -jar 2048-1.jar
 ```
