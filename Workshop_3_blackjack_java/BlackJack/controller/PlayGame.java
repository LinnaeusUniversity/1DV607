package BlackJack.controller;

import BlackJack.model.Game;
import BlackJack.model.IObserver;
import BlackJack.view.IView;
import BlackJack.view.UserInput;

public class PlayGame implements IObserver {
	
	private Game game;
	private IView view;
	
	/*CONSTRUCTOR*/
	public PlayGame(Game game, IView view) {
		this.game = game;
		this.view = view;
		game.addGameObserver(this);
	}


  public boolean Play()  {  
	  
	  UserInput input = view.GetInput();
	  switch (input) {
		case PLAY:
			game.NewGame();
			break;

		case HIT:
			game.Hit();
			break;

		case STAND:
			game.Stand();
			break;

		case QUIT:
			return false;

		default:
			break;
		}
	    
	  displayCards();
		
		if(game.IsGameOver()) {
			view.DisplayGameOver(game.IsDealerWinner());
		}
		return true;  

  }
  
  /*
   * displayCards
   * Prints the instructions to play the game and
   * the current hands of the dealer and player.
   * */
  public void displayCards() {
	    view.printWhiteSpaces();
	    view.DisplayUserInstructions();
	    view.DisplayDealerHand(game.GetDealerHand(), game.GetDealerScore());
		view.DisplayPlayerHand(game.GetPlayerHand(), game.GetPlayerScore());
  }

  @Override
	public void newCard() {				
		try {				
			view.displayNewCard();
			view.DisplayDealerHand(game.GetDealerHand(), game.GetDealerScore());
			view.DisplayPlayerHand(game.GetPlayerHand(), game.GetPlayerScore());		
			
			//Thread.sleep(2000);
			//What is funnier than waiting 2 seconds? Waiting random seconds!
			Thread.sleep((long)(Math.random() * 2000));
			
		}catch(InterruptedException e) {
			System.out.println("Problem in thread ->" + e.getMessage());
		}
		 
	}
}