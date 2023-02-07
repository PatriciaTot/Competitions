package competition.match;

import competition.observer.MatchObserver;
import competition.observer.UnknownObserverException;

public interface ObservableMatch {
	
	public void register(MatchObserver matchObserver);
	
	public void unregister(MatchObserver matchObserver) throws UnknownObserverException;
	
	public void notifyObservers();

}
