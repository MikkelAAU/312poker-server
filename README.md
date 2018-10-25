# 312pokerServer

The Server side of this Poker Texas hold'em game, consists of various classes that make it function with networking and multithreading on a basic level.

To start a game a server is required to be running, when a server is running it is possible for clients to join the lobby and from there join the game with the command 'ready'. When ready has been entered by all clients, a poker session will start.

The server side is developed by Jons Valvik (jvalvik), Mikkel Rosholm (dumleman + MikkelAAU) and Theo Khumsan (TheoAAU)

Project Poker Server:

Before scrum:
Team is assembled and the group decides that the game they will make is Poker: Texas Hold-em. The team splits into two groups, server and client. They draw their individual uml diagrams to get a better overview of what will happen and what should be the end result.

Sprint 1 - day 0 - server:
Goals for the 1. sprint:
Create a server that is able to host a game
Create classes for Cards, a deck, suits and values and a class to handle the users.

Sprint 1 - day 1 - server:
The group meets up for the first sprint planning meeting. After the Sprint Planning meeting Mikkel - the Scrum Master of the team - calls the 3 man team to define the details of how the different parts of the program is supposed to be implemented. The resulting tasks are written down in Github on the project board. Now everyone of the Scrum Team selects a task to work on, and the sprint begins.

Sprint 1 - Day 3
At the beginning of the day, the team gets together for their Daily Scrum meeting and discusses how everything goes, if there are any issues. So far everything is going as scheduled. In the afternoon one of the Scrum team members feels unable to implement individual players for each client. He discusses the issue with the rest of the group and together the team finds out what to do, so they can continue with the implementation.

Sprint 1 - Day 5
Again the team meets to in the morning to discuss progress and issues. Many classes have successfully been implemented (card, deck, suit, value), other classes require a lot of work to function, but that will be the goal of the next sprint, this sprint ends today. At the end of the day the group tries to connect the server and client to ensure that connection can be established and that data can be sent and received between the clients. This test proves successful and the team therefore goes home in a good mood.

In the morning of day 6 the team met up for a review / retrospective meeting, to discuss how the first sprint went and what difficulties there were and how the workload was, and there were so far no big differences and the team were happy with the first progress but recognised that the next sprint should be more focused on the networking part of the assignment and linking together the classes and the game in its entirety.

Sprint 2 - day 0 - server
Goals for the 2. sprint:
Get the server to work with the other classes
make a class that handles a poker session
finish the rules/combination of hands

Sprint 2 - day 1
The group meets up for the first daily scrum meeting of the 2. sprint and discusses the plans for the day. Today they would implement a class that could handle a poker session and setup a poker session with all the players currently connected, and deal cards to them and deal cards to the table during the game. The team works all day on this setup.

Sprint 2 - day 2
The group meets up for a short meeting and discuss how to continue from yesterday's progress and talks about what to do to finish with the handle session today.

The groups splits again and works again and manages by the end of the day  to complete the handle session class with commands for playing the game (call, check, raise, fold).
At the end of the day the group meets up with the client team and tests the progress to see if the handle session can designate turns and that all players can play through all rounds.
The test proves successful to some degree, the players can play the game all the way through with different cards in the hand and the same cards on the table, and the rounds of the game can be completed with the requirement that the players only call og check throughout the game. If a player raises, the commands would stop working and the game would not be able to continue.

Sprint 2 - day 3
The group again meets up for a short meeting to discuss what to do with the raise command and how to fix it and that after that the rules and combinations of cards should be implemented.








