package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public class DealerWinsTie implements IWinnerOnTie {

	@Override
	public boolean dealerWins(Player a_player, Dealer a_dealer, int maxScore) {
		if (a_dealer.CalcScore() == maxScore)
			return true;
		if (a_dealer.CalcScore() < maxScore && a_dealer.CalcScore() >= a_player.CalcScore())
			return true;
		if (a_player.CalcScore() > maxScore)
			return true;
			
		return false;
		
		//if(a_player.CalcScore() > maxScore || a_player.CalcScore() >= a_dealer.CalcScore())
		//	return true;
		//return false;
	}

}