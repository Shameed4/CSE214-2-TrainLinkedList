/**
 * The ProductLoad contains information about a product contained in a TrainCar.
 * 
 * @author Sean Erfan
 */
public class ProductLoad {
	private String name;
	private double weight;
	private double value;
	private boolean isDangerous;

	/**
	 * Initializes an empty load
	 */
	public ProductLoad() {
		name = "Empty";
		weight = 0;
		value = 0;
		isDangerous = false;
	}

	/**
	 * Initializes variables
	 * 
	 * @param name        The name of the load
	 * @param weight      The weight of the load in tons
	 * @param value       How much the load is worth in dollars
	 * @param isDangerous If the load is dangerous
	 */
	public ProductLoad(String name, double weight, double value,
	        boolean isDangerous) {
		setName(name);
		setWeight(weight);
		setValue(value);
		setDangerous(isDangerous);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if (!name.equals("") && !name.equals("Empty"))
			this.name = name;
		else
			throw new IllegalArgumentException("Must enter a product name");
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		if (weight > 0)
			this.weight = weight;
		else
			throw new IllegalArgumentException("Weight must be greater than 0");
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		if (value > 0)
			this.value = value;
		else
			throw new IllegalArgumentException("Value must be greater than 0");
	}

	/**
	 * @return if the product is dangerous
	 */
	public boolean isDangerous() {
		return isDangerous;
	}

	/**
	 * @param isDangerous if the product is dangerous
	 */
	public void setDangerous(boolean isDangerous) {
		this.isDangerous = isDangerous;
	}

	/**
	 * @return a header for when a load will be printed.
	 */
	public static String getProductLoadHeader() {
		return String.format("%8s%16s%14s%12s", "Name", "Weight (t)",
		        "Value ($)", "Dangerous");
	}

	/**
	 * @return a divider between the header and the load information when a load
	 *         will be printed
	 */
	public static String getProductLoadHeaderDivider() {
		return "===================================================";
	}

	/**
	 * @return information about this load without printing a header
	 */
	public String toString() {
		return String.format("%10s%14s%14s%12s", name, weight,
		        String.format("%,.2f", value), isDangerous ? "YES" : "NO");
	}

	/**
	 * prints information about this load and includes a header
	 */
	public void printSingleLoad() {
		System.out.println(String.format("%55s", getProductLoadHeader()));
		System.out
		        .println(String.format("%55s", getProductLoadHeaderDivider()));
		System.out.println(String.format("%55s", toString()));
	}
}