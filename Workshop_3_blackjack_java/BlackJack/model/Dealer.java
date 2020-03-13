package BlackJack.model;

import BlackJack.model.rules.IHitStrategy;
import BlackJack.model.rules.INewGameStrategy;
import BlackJack.model.rules.IWinnerOnTie;
import BlackJack.model.rules.RulesFactory;

public class Dealer extends Player {

  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;
  private IWinnerOnTie m_winRule;

  public Dealer(RulesFactory a_rulesFactory) {
  
    m_newGameRule = a_rulesFactory.GetNewGameRule();
    m_hitRule = a_rulesFactory.GetHitRule();
    m_winRule = a_rulesFactory.GetWinnerOnTie();
    /*for(Card c : m_deck.GetCards()) {
    c.Show(true);
    System.out.println("" + c.GetValue() + " of " + c.GetColor());
  }    */
  }
  
  
  public boolean NewGame(Player a_player) {
    if (m_deck == null || IsGameOver()) {
      m_deck = new Deck();
      ClearHand();
      a_player.ClearHand();
      return m_newGameRule.NewGame(m_deck, this, a_player);   
    }
    return false;
  }

  public boolean Hit(Player a_player) {
    if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {
    	getShowAndDealCardTo(a_player, true);          
      return true;
    }
    return false;
  }

  public boolean IsDealerWinner(Player a_player) {
	  return m_winRule.dealerWins(a_player, this, g_maxScore);
  }

  public boolean IsGameOver() {
    if (m_deck != null && m_hitRule.DoHit(this) != true) {
        return true;
    }
    return false;
  }
  
  public boolean Stand() {
      if (m_deck != null) {
          this.ShowHand();          
          while (m_hitRule.DoHit(this)) {
        	  getShowAndDealCardTo(this, true);           
          }
         return true;
       }
     return false;
   }
  
  public void getShowAndDealCardTo(Player a_player, boolean visibility) {
	  Card c = m_deck.GetCard();
	  c.Show(visibility);
	  a_player.DealCard(c);
  }
  
  

}