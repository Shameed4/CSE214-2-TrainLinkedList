/**
 * The TrainCar is used to store a ProductLoad and has a length and width.
 * 
 * @author Sean Erfan
 */
public class TrainCar {
	private double carLength;
	private double carWeight;
	private ProductLoad load;

	public TrainCar() {
		carLength = 0;
		carWeight = 0;
		load = new ProductLoad();
	}

	/**
	 * Creates a new trainCar with initialized fields
	 * 
	 * @param carLength The length of the car
	 * @param carWeight The weight of the car (excluding the load)
	 */
	public TrainCar(double carLength, double carWeight) {
		this.carLength = carLength;
		this.carWeight = carWeight;

		setLoad(new ProductLoad());
	}

	/**
	 * @return the load
	 */
	public ProductLoad getLoad() {
		return load;
	}

	/**
	 * @param load the load to set
	 */
	public void setLoad(ProductLoad load) {
		this.load = load;
	}

	/**
	 * @return the carLength
	 */
	public double getCarLength() {
		return carLength;
	}

	/**
	 * @return the carWeight
	 */
	public double getCarWeight() {
		return carWeight;
	}

	/**
	 * @return if the load is empty
	 */
	public boolean isEmpty() {
		return load.getName().equals("Empty") && load.getValue() == 0
		        && load.getWeight() == 0;
	}
}