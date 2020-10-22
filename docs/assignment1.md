# Assignment 1
## Introduction									
Author(s): `Surhay Bas, David Cheung, Simone Colombo and Lorenzo Sagrada`.
### Prologue

 The game is a TextAdventureGame based on the standalone Netlflix film Bandersnatch. The game is set in New York city where a young university computer science student, Jack Smith,lives his boring and casual student life. However, because he is a last year student he wants to find a plum and interesting job in order to start his working career. Therefore, Jack decides to start applying to different companies in the IT field. Unfortunately, after a few months of trials, he has not got any job and he is almost to give up. Unexpectedly, a start-up researching in the Virtual Reality field replies to him, offering a 6 months full-time job as a game tester of a cutting-edge Virtual Reality technology. Jack, who is really down due to all his previous job rejections, enthusiastically accepts the offer. When he meets the team members who explain him all the details about his job, he feels that there is something really mysterious in their cutting-edge technology. He has to work from home for 8 hours per day, every morning he has to take a pill that will throw him in a Virtual Reality city, really similar to New York, for 6 hours straight. Once into the game he has to solve puzzles and riddles in order to progress with the story of the game. After the first 6 hours he has to report a feedback, on the company private website, about his gaming experience and possible bugs that he found.
 Jack decides to go ahead and he starts working for the Virtual Reality start-up. During the first few days, he really likes testing the new technology and everything goes smooth and seems really enjoyable. After, the first week he notices that something wrong is happening during the game. Jack feels that he is stuck in the game after the effect of the pill should have theoretically ended. Then, his mind starts tricking him and he start thinking that he is blocked in a simulation and the only way to exit it is to complete the game solving all the puzzles.
 
 ### Game details:
 
 More details of the game are summarized in the following paragraph:
 
* Main type of user(s)

  * Consumers: people who like solving riddles and mysterious adventure games or games in general, for 12 and older. 
  
* Overall idea about how it works

The main character is a university student who is stuck in a game simulation. The main goal of the game is to escape from the simulation through the actions of the main character which will be issued by the user via the command-line. The game is structured around the main concept of the virtual/simulated reality. Hence, the game evolves in a way such that the main character comes to a point where the difference between reality and simulation is really blurred and distorted that he almost cannot distinguish them anymore. The only way the main character can exit the game, it is by solving all the different puzzles that he will find as he progresses in the story. The game structure is chapter-based, it means that the story is divided into multiple standalone chapters and the player will be able to save the his game progress once per chapter when he finds the checkpoint. In addition, it can happen that the main character dies due to severe wrong choices or unfortunate events(which may happen due to wrong accumulated previous choices); in this case the game structure is made so that the game restarts from the last checkpoint everytime the character dies. The main character also has a mental health bar which determines the rationality of his decisions. If the mental health bar is lower than a preset threshold, then the main character will go crazy and he will be able to choose from different set of actions which are classified as irrational.


The following bullet points elaborate more on the game mechanics:

* Main game mechanics
  * The character can leave notes for other players to be found containing clues.
  * The character has special perks which can be found on the screen to interact with or to modify the dynamics of the game (e.g. sanity level)
  * The player can choose between different actions(e.i. grab an object, use an object, inspect an object, talk with a person, etc...)
  * Every decision the player makes changes the ending
  * The puzzles will have different styles and will have a time limit to solve them
  
 ### Extensions

* Extensions
  * The game allows the player to save their progress
  * The game allows the player to load previous save
  * The game allows the player to player a multiplayer mode 
  * The game allows the player to update abilities and character traits
  * The game will be displayed with a GUI which will allow a more personalized and appealing look, alongside a clear change of the background to get the user more involved in the storyline. Also sounds, and user input and other extra functionalities will be added in this still relatively minimalistic user interface;
  
 ### Game inspiration
The idea for this game was inspired by the standalone Netflix movie in the Blackmirror universe called Bandersnatch [https://en.wikipedia.org/wiki/Black_Mirror:_Bandersnatch], where the user has to take decisions which will influence in variuous ways the story. We decided to change the story and add many extra fuinctionalities to give the user a more engaging experience.



## Features
Author(s): `Surhay Bas, David Cheung and Simone Colombo`

### Functional features

| ID  | Short name  | Description  |
|---|---|---|
| F1 | Load the save| The player can load from the last saving by issuing: load \<gameName\>|
| F2  | Save/load multiple games| The player is able to save/load multiple games by specifying their name after the respective command: save\load p \<gameName\
| F3  | Commands  | The player can control the main character by issuing command-line commands following this syntax: `command-name [target-objects]*`. The available `command-names` are the following: <br/> - move: ... <br/> - use: ... <br/> - inspect: ... <br/> |
| F4  | Movements  | The main character shall move freely in the environment according to his story path.|
| F5  | Choices | The main character can freely choose between actions belonging to a certain set of actions. Depending on the situation, different scenarios are possible: open questions, following the command syntax explained above, close questions(e.i yes or no) and multiple choice actions.|
| F6  | Set of choices | Depending on the characters mental health level, he gets to choose from a different set of actions. The lower his mental health is the crazier the possible actions are. |
| F7  | Dialogue | The main character can engage in conversations and answer w.r.t the possible different scenarios explained in F6 |
| F8  | Multiplayer mode | The multiplayer mode is developed as non-direct interaction among players. It means, that the maximum number of players per game is 1. Every player will be able to leave/hidden notes for the other players in order to ease or hinder their game. Notes can be read and picked up. |




### Quality requirements
Author(s): `Surhay Bas and Simone Colombo`

As a preamble to the table, you can discuss the main line of reasoning you followed for coming up with the quality requirements listed below.

| ID  | Short name  | Quality attribute | Description  |
|---|---|---|---|
| QR1  | Commands sanity checks | Reliability  | When the player issues a command, the syntax of the command shall always get validated against the format specified in F4,F2,F1. |
| QR2  | Extensible world | Maintainability  | The game is easilty extantable by using the premade classes to make new levels |
| QR3  | Instantaneous results | Responsiveness  | When a player makes a decision the outcome of the action will be provided within seconds to the player. The duration might differ depanding on the spesifications of the players computer |
| QR4  | Multiplayer Validation | Security | The messages that is left by other players are gonna be scaned for code injections |
| QR5  | Testing | Usability | All of the functionalies are gonna be checked before going out public to ensure that the possiblity of an error during using those functions would be close to zero. Also all of the futures are easy and pleasent to use |
| QR6  | Server up time | Availability | The multiplayer function would always run aslongs as the servers are under maintanence. If the server are under maintanence the player can play the game without the multiplayer function. |


### Java libraries
Author(s): `Surhay Bas`

| Name (with link) | Description  |
|---|---|
| [javax.swing](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html)  | Used for creating a user interface. The reason why we choosed this is it is built-in in intellij and easy to use | 
| [fastjson](https://github.com/alibaba/fastjson) | We will use it to save spesific informations about the level and player. |
| [javax.sound](https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/package-summary.html) | To play sounds and musics to echance the gameplay |
| [java.sql](https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html) | For online sql database to use in the multiplayer part of the game |
