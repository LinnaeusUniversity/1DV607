package BlackJack.view;


import BlackJack.model.Card;

public interface IView
{
  void DisplayWelcomeMessage();
  void DisplayUserInstructions();
  UserInput GetInput();
  void DisplayCard(Card a_card);
  void DisplayPlayerHand(Iterable<Card> a_hand, int a_score);
  void DisplayDealerHand(Iterable<Card> a_hand, int a_score);
  void DisplayGameOver(boolean a_dealerIsWinner);
  void printWhiteSpaces();
  void displayNewCard();
}