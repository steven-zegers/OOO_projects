package model.domain;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public interface Subject {
	void addObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObservers();
}