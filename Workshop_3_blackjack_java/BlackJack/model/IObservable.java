package BlackJack.model;

/*Implemented in player*/
public interface IObservable {
	public void addSubscriber(IObserver subscriber);
	public void removeSubscriber(IObserver subscriber);
	public void notifyObservers();
	
}