/**
 * The TrainCarNode stores a TrainCar and the previous and next nodes
 * 
 * @author Sean Erfan
 */
public class TrainCarNode {
	private TrainCarNode prev;
	private TrainCarNode next;
	private TrainCar car;

	/**
	 * Creates an instance of the class without initializing variables
	 */
	public TrainCarNode() {
		prev = null;
		next = null;
		car = null;
	}

	/**
	 * @param car Creates an instance of the class while initializing the car
	 */
	public TrainCarNode(TrainCar car) {
		prev = null;
		next = null;
		this.car = car;
	}

	/**
	 * @return the previous node
	 */
	public TrainCarNode getPrev() {
		return prev;
	}

	/**
	 * @param prev the previous node to set
	 */
	public void setPrev(TrainCarNode prev) {
		this.prev = prev;
	}

	/**
	 * @return the next node
	 */
	public TrainCarNode getNext() {
		return next;
	}

	/**
	 * @param next the next node to set
	 */
	public void setNext(TrainCarNode next) {
		this.next = next;
	}

	/**
	 * @return the car
	 */
	public TrainCar getCar() {
		return car;
	}

	/**
	 * @param car the car to set
	 */
	public void setCar(TrainCar car) {
		this.car = car;
	}

	/**
	 * @return the car with the length, weight, and load
	 */
	public String toString() {
		return String.format("%14s%14s%3s%10s", car.getCarLength(),
		        car.getCarWeight(), "|", car.getLoad());
	}
}
