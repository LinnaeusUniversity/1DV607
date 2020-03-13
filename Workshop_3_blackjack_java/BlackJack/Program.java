package BlackJack;

import BlackJack.controller.PlayGame;
import BlackJack.model.Game;
import BlackJack.view.IView;
import BlackJack.view.SimpleView;
import BlackJack.view.SwedishView;

public class Program
{
/*
 * Changes: 
 * view display welcome message here so it doesn't
 * repeats each time you play the game. 
 * */
  public static void main(String[] a_args)
  {
  
    Game g = new Game();
    //IView v = new SwedishView();
    IView v =new SimpleView(); 
    PlayGame ctrl = new PlayGame(g,v);
    v.DisplayWelcomeMessage();
    while (ctrl.Play());
    
  }
}