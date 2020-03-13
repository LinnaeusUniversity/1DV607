package BlackJack.view;


import BlackJack.model.Card;

public class SimpleView implements IView 
{


  public void DisplayWelcomeMessage()
        {
	  
	  
	  String sb="	 	 _________________________\r\n"
		  		+ "	 	|WELCOM TO BLACK JACK GAME!|\r\n"
		  		+ "	 	|__________________________|\r\n"
		  	
		  		+ "	 ___________________ ___________________\r\n"
		  		+ " 	|                   |                   |    \r\n"
		  		+ " 	| A                 | 10                |              \r\n"
		  		+ " 	|                   |                   |\r\n"
		  		+ " 	|                   |                   |\r\n"
		  		+ " 	|       B           |            K      |\r\n"
		  		+ " 	|          L        |          C        |\r\n"
		  		+ " 	|            A      |       A           |\r\n"
		  		+ " 	|              C    |   J               |\r\n"
		  		+ " 	|                   K                   |\r\n"
		  		+ " 	|                   |                   |\r\n"
		  		+ " 	|                   |                   |\r\n"
		  		+ " 	|                   |                   |\r\n"
		  		+ " 	|                   |                   |\r\n"
		  		+ " 	|                 A |                10 |\r\n"
		  		+ " 	|___________________ ___________________|\r\n";
		  
	  	  printWhiteSpaces();
          
          System.out.println(sb);
          DisplayUserInstructions();
          
        }
  
  public void DisplayUserInstructions() {
		System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
		
	} 

  public UserInput GetInput()
        {
          try {
            int c = System.in.read();
            while (c == '\r' || c =='\n') {
              c = System.in.read();
            }
            
            
            switch (c) {
            case 'p':
				return UserInput.PLAY;

			case 'h':
				return UserInput.HIT;

			case 's':
				return UserInput.STAND;

			case 'q':
				return UserInput.QUIT;

			default:
				System.err.println("There was an error, please try again.\n");
				return UserInput.ERROR;
			}
          } catch (java.io.IOException e) {
            System.out.println("" + e);
            return UserInput.ERROR;
          }
        }

        public void DisplayCard(Card a_card)
        {
            System.out.println("" + a_card.GetValue() + " of " + a_card.GetColor());
        }

        public void DisplayPlayerHand(Iterable<Card> a_hand, int a_score)
        {
            DisplayHand("Player", a_hand, a_score);
        }

        public void DisplayDealerHand(Iterable<Card> a_hand, int a_score)
        {
            DisplayHand("Dealer", a_hand, a_score);
        }

        private void DisplayHand(String a_name, Iterable<Card> a_hand, int a_score)
        {
            System.out.println(a_name + " Has: ");
            for(Card c : a_hand)
            {
                DisplayCard(c);
            }
            System.out.println("Score: " + a_score);
            System.out.println("");
        }

        public void DisplayGameOver(boolean a_dealerIsWinner)
        {
            System.out.println("GameOver: ");
            if (a_dealerIsWinner)
            {
                System.out.println("Dealer Won!");
            }
            else
            {
                System.out.println("You Won!");
            }
            
        }
        public void printWhiteSpaces() {for(int i = 0; i < 40; i++) {System.out.print("\n");}; }
        
        public void displayNewCard() {
        	printWhiteSpaces();
        	System.out.println("\nNew cards draw: ");
        }


		
    }