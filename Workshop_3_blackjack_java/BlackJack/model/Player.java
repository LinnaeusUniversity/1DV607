package BlackJack.model;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/* 
 * We have brought some changes here. it was easier for us 
 * to use two Interfaces.we have asked regarding these class and you replied
 * slack question:
 * sorry just remembered we have another question, in you class diagram you 
 * have dependencies from the controller package to the model package, but now
 *  we have an association instead of a dependency, is this a correct approach or not?
 *  you answered:"yes, that is the consequence of the observer stuff"
 *  slack question:
 *  sorry just remembered we have another question, in you class diagram you have dependencies
 *   from the controller package to the model package, but now we have an association
 *    instead of a dependency, is this a correct approach or not?
 *    you replied:"yes, that is the consequence of the observer stuff".
 * */
public class Player implements IObservable {

	private List<Card> m_hand;
	protected final int g_maxScore = 21;
	private List<IObserver> subscribers;

	public Player() {

		m_hand = new LinkedList<Card>();
		subscribers = new ArrayList<IObserver>();
	}

	public void DealCard(Card a_addToHand) {
		m_hand.add(a_addToHand);
		notifyObservers();
	}

	public Iterable<Card> GetHand() {
		return m_hand;
	}

	public void ClearHand() {
		m_hand.clear();
	}

	public void ShowHand() {
		for (Card c : GetHand()) {
			c.Show(true);
		}
	}

	public int CalcScore() {
		int cardScores[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
		assert (cardScores.length == Card.Value.Count
				.ordinal()) : "Card Scores array size does not match number of card values";

		int score = 0;

		for (Card c : GetHand()) {
			if (c.GetValue() != Card.Value.Hidden) {
				score += cardScores[c.GetValue().ordinal()];
			}
		}

		if (score > g_maxScore) {
			for (Card c : GetHand()) {
				if (c.GetValue() == Card.Value.Ace && score > g_maxScore) {
					score -= 10;
				}
			}
		}

		return score;
	}

	public void addSubscriber(IObserver subscriber) {
		subscribers.add(subscriber);
	}

	public void removeSubscriber(IObserver subscriber) {
		subscribers.remove(subscriber);
	}

	public void notifyObservers() {
		subscribers.forEach(sub -> sub.newCard());
	}

}