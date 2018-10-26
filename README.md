# 312pokerServer

The Server side of this Poker Texas hold'em game, consists of various classes that make it function with networking and multithreading on a basic level.

To start a game a server is required to be running, when a server is running it is possible for clients to join the lobby and from there join the game with the command 'ready'. When ready has been entered by all clients, a poker session will start.

The server side is developed by Jons Valvik (jvalvik), Mikkel Rosholm (dumleman + MikkelAAU) and Theo Khumsan (TheoAAU)

Find the adjoining client here: https://github.com/MikkelAAU/312poker-client

--------------------------------------------------------------------------------------------------------------------------

In hold'em, players receive two down cards as their personal hand (holecards), after which there is a round of betting. Three table cards are turned simultaneously (called the flop) and another round of betting occurs. The next two board cards are turned one at a time, with a round of betting after each card. The board cards are community cards, and a player can use any five-card combination from among the board and personal cards. A player can even use all of the board cards and no personal cards to form a hand ("play the board").

  Rounds of Betting:
Opening deal - Each player is dealt two cards face down, which are known as hole cards or pocket cards.

  First round of betting:
Starting with a random player, each player can call the current highest bet, raise, or fold.
  
  The flop:
The dealer burns a card, and then deals three community cards face up. The first three cards are referred to as the flop, while all of the community cards are collectively called the board.
  
  Second round of betting:
Starting with the player to the left of the dealer button, each player can check or bet. Once a bet has been made, each player can raise, call, or fold.
  
  The turn:
The dealer burns another card, and then adds a fourth card face-up to the community cards. This fourth card is known as the turn card, or fourth street.

  Third round of betting:
It follows the same format as the second round.
  
  The river:
The dealer burns another card, and then adds a fifth and final card to the community cards. This fifth card is known as the river card, or fifth street.
  
  Final round of betting:
It follows the same format as the second and third rounds.
  
  The showdown:
Using the best five-card combination of their hole cards and the community cards, the remaining players have their hands compared. The highest five-card hand wins the pot. 

------------------------------------------------------------------------------------------------------------------------------------







