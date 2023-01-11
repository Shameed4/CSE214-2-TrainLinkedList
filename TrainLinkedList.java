/**
 * The TrainLinkedList class stores multiple TrainCar objects in a doubly linked
 * list structure, allowing operations such as insertions and removals in O(1)
 * complexity
 * 
 * @author Sean Erfan
 */
public class TrainLinkedList {
	private TrainCarNode head;
	private TrainCarNode tail;
	private TrainCarNode cursor;

	private int trainSize;
	private double trainLength;
	private double trainValue;
	private double trainWeight;
	private int dangerousTrainCarCount;

	/**
	 * Creates a TrainLinkedList without initializing any variables.
	 */
	public TrainLinkedList() {
		head = null;
		tail = null;
		cursor = null;
	}

	/**
	 * @return The TrainCar at the node currently referenced by the cursor.
	 * @throws NullCursorException When the cursor is null
	 */
	public TrainCar getCursorData() throws NullCursorException {
		if (cursor == null)
			throw new NullCursorException("Can't get the cursor.");
		return cursor.getCar();
	}

	/**
	 * @param car The car to place in the node currently referenced by the
	 *            cursor.
	 */
	public void setCursorData(TrainCar car) {
		if (car == null)
			throw new NullPointerException();
		if (cursor.getCar() != null) {
			trainLength -= cursor.getCar().getCarLength();
			trainWeight -= cursor.getCar().getCarWeight()
			        + cursor.getCar().getLoad().getWeight();
			trainValue -= cursor.getCar().getLoad().getValue();
			dangerousTrainCarCount -= cursor.getCar().getLoad().isDangerous()
			        ? 1
			        : 0;
		}
		cursor.setCar(car);
		trainLength += car.getCarLength();
		trainWeight += car.getCarWeight() + car.getLoad().getWeight();
		trainValue += car.getLoad().getValue();
		dangerousTrainCarCount += car.getLoad().isDangerous() ? 1 : 0;

	}

	/**
	 * Moves the cursor to point at the next TrainCarNode
	 * 
	 * @throws NullCursorException      List is empty
	 * @throws NoNextElementException() Cursor is at the end of the list
	 */
	public void cursorForward()
	        throws NullCursorException, NoNextElementException {
		if (cursor == null)
			throw new NullCursorException("Cannot move cursor forward.");
		if (cursor == tail)
			throw new NoNextElementException();
		cursor = cursor.getNext();
		System.out.println("Cursor moved forward.");
	}

	/**
	 * Moves the cursor to point at the previous TrainCarNode
	 * 
	 * @throws NullCursorException      List is empty
	 * @throws NoPrevElementException
	 * @throws NoNextElementException() Cursor is at the end of the list
	 */
	public void cursorBackward()
	        throws NullCursorException, NoPrevElementException {
		if (cursor == null)
			throw new NullCursorException("Cannot move cursor backward.");
		if (cursor == head)
			throw new NoPrevElementException();
		cursor = cursor.getPrev();
		System.out.println("Cursor moved backward");
	}

	/**
	 * Inserts a car into the train after the cursor position.
	 * 
	 * @param newCar The new TrainCar to be inserted into the train
	 * @throws IllegalArgumentException Indicates that newCar is null
	 */
	public void insertAfterCursor(TrainCar newCar) {
		if (newCar == null)
			throw new IllegalArgumentException("There is no car to insert.");

		TrainCarNode t = new TrainCarNode();
		if (cursor == null) {
			head = t;
			tail = t;
		}
		else if (cursor == tail) {
			tail.setNext(t);
			t.setPrev(tail);
			tail = t;
		}
		else {
			t.setNext(cursor.getNext());
			t.setPrev(cursor);
			t.getNext().setPrev(t);
			cursor.setNext(t);
		}

		cursor = t;
		setCursorData(newCar);
		trainSize++;

		System.out.println("New train car " + t.getCar().getCarLength()
		        + " meters " + t.getCar().getCarWeight()
		        + " tons inserted into train.");
	}

	/**
	 * Removes the TrainCarNode referenced by the cursor and returns the
	 * TrainCar contained within the node
	 * 
	 * @throws NullCursorException Cursor is null (list is empty)
	 */
	public TrainCar removeCursor() throws NullCursorException {
		if (cursor == null)
			throw new NullCursorException("No elements to remove.");

		TrainCar c = cursor.getCar();

		setCursorData(new TrainCar());
		trainSize--;

		if (cursor != head && cursor != tail) {
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
			cursor = cursor.getNext();
		}

		else if (cursor == head && cursor == tail) {
			head = null;
			tail = null;
			cursor = null;
		}

		else if (cursor == head) {
			head = head.getNext();
			cursor = head;
			if (head != null) {
				head.setPrev(null);
			}
		}

		else if (cursor == tail) {
			cursor = cursor.getPrev();
			cursor.setNext(null);
			tail = cursor;
		}
		return c;
	}

	/**
	 * 
	 * @param load The ProductLoad to put into the cursor's TrainCar
	 * @throws NullCursorException
	 */
	public void LoadCursor(ProductLoad load) throws NullCursorException {
		if (cursor == null)
			throw new NullCursorException("No cars to load");
		TrainCar newCar = new TrainCar(cursor.getCar().getCarLength(),
		        cursor.getCar().getCarWeight());
		newCar.setLoad(load);
		System.out.println(load.getWeight()
		        + (load.getWeight() == 1 ? " ton " : " tons ") + "of "
		        + load.getName() + " added to the current car.");
		setCursorData(newCar);
	}

	/**
	 * @return The number of TrainCar objects currently on the train
	 */
	public int size() {
		return trainSize;
	}

	/**
	 * @return The total length of the train in meters
	 */
	public double getLength() {
		return trainLength;
	}

	/**
	 * @return the total value of all loads in the train
	 */
	public double getValue() {
		return trainValue;
	}

	/**
	 * @return The total weight in tons of the train (includes both the cars and
	 *         their loads)
	 */
	public double getWeight() {
		return trainWeight;
	}

	/**
	 * @return if there are any dangerous ProductLoad's
	 */
	public boolean isDangerous() {
		return dangerousTrainCarCount == 0;
	}

	/**
	 * Searches the list for all ProductLoad objects with the indicated name and
	 * sums together their weight and value (Also keeps track of whether the
	 * product is dangerous or not), then prints a single ProductLoad record to
	 * the console.
	 * 
	 * @param name The name ofthe ProductLoad to find on the train
	 */
	public void findProduct(String name) {
		TrainCarNode tempPtr = head;
		ProductLoad totalLoad = null;
		int counter = 0;
		while (tempPtr != null) {
			ProductLoad tempLoad = tempPtr.getCar().getLoad();
			if (tempLoad.getName().equals(name)) {
				counter++;
				if (totalLoad == null) {
					totalLoad = new ProductLoad(tempLoad.getName(),
					        tempLoad.getWeight(), tempLoad.getValue(),
					        tempLoad.isDangerous());
				}
				else {
					totalLoad.setValue(
					        totalLoad.getValue() + tempLoad.getValue());
					totalLoad.setWeight(
					        totalLoad.getWeight() + tempLoad.getWeight());
				}
			}
			tempPtr = tempPtr.getNext();
		}
		if (totalLoad == null)
			System.out.println("No record of " + name + " on board train");
		else {
			System.out.println("The following products were found on " + counter
			        + " cars:\n");
			totalLoad.printSingleLoad();
		}
	}

	/**
	 * Prints a neatly formatted table of the car number, car length, car
	 * weight, load name, load weight, load value, and load dangerousness for
	 * all of the cars on the train.
	 */
	public void printManifest() {
		System.out.println(String.format("%8s%36s", "CAR:", "LOAD:") + "\n"
		        + String.format("%9s%13s%14s%3s", "Num", "Length (m)",
		                "Weight (t)", "|")
		        + ProductLoad.getProductLoadHeader());
		System.out.println(String.format("%39s",
		        "=============================" + "=====+")
		        + ProductLoad.getProductLoadHeaderDivider());
		TrainCarNode tempPtr = head;
		for (int i = 1; tempPtr != null; i++) {
			TrainCar c = tempPtr.getCar();
			if (tempPtr == cursor)
				System.out.print(" ->");
			else
				System.out.print("   ");
			System.out.println(String.format("%5s", i) + tempPtr);
			tempPtr = tempPtr.getNext();
		}
	}

	/**
	 * Removes all dangerous cars from the train, maintaining the order of the
	 * cars in the train.
	 */
	public void removeDangerousCars() {
		if (dangerousTrainCarCount > 0) {
			TrainCarNode tempPtr = cursor;
			cursor = head;
			while (cursor != null && dangerousTrainCarCount > 0) {
				try {
					if (cursor.getCar().getLoad().isDangerous()) {
						if (cursor == tempPtr) {
							removeCursor();
							tempPtr = cursor;
						}
						else
							removeCursor();
					}
					else
						cursor = cursor.getNext();
				}
				catch (NullCursorException e) {
					System.out.println("If this is printed something is "
					        + "wrong with code");
				}
			}
			System.out.println(
			        "Dangerous cars successfully removed from the train.");
			cursor = tempPtr;
		}
		else
			System.out.println("No dangerous cars!");
	}

	/**
	 * Returns a neatly formatted String representation of the train.
	 * 
	 * @return A neatly formatted string containing information about the train,
	 *         including its size (number of cars), length in meters, weight in
	 *         tons, value in dollars, and whether it is dangerous or not.
	 */
	public String toString() {
		return "Train: " + trainSize + " cars, " + trainLength + " meters, "
		        + trainWeight + " tons, $" + String.format("%,.2f", trainValue)
		        + " value, " + (dangerousTrainCarCount == 0 ? "not dangerous."
		                : "DANGEROUS.");
	}
}
