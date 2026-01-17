# Drop-Zone 

## Overview
Drop-Zone is a terminal-based arcade game written in Java, inspired by old NES and arcade games. The player navigates a dangerous drop zone, avoiding hazards and catching objectives, surviving as long as possible in an increasingly difficult environment.

## Why I Built This
I built this project as a text based game for AP Computer Science A. I wanted to explore going further than text based input for my terminal game, and settled on a fixed update, arcade style game.

This project had me experiment with basic custom collisions with fixed movement, and game state management. 

## How It Works
The game runs on a fixed-timestep loop that updates player movement, enemy behavior, and collisions each frame. All rendering is done using terminal output, forcing the game logic to remain simple and efficient.

Difficulty increases over time by adjusting spawn rates and movement speed. The walls of the drop zone will close in on the player as the difficulty increases, as well as the addition of green objectives the player must catch.

## How to Run
Requirements:
- Java 8 or Higher

Compile and run:
```bash
javac DropZone.java
java DropZone
```

## Playing
Space Bar to start and restart

Arrow Keys to move left and right

You must be focusing on the JFrame window for the game to detect user input

<img src="https://github.com/user-attachments/assets/98c8edd1-46d8-40df-8476-0595ad6b7186" width="400">

This GIF is sped up
