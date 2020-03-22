
public class Electronics implements Item{
    String description;
    double itemWeight;

    /** Constructor
     * @param description - description of item 
     * @param weight - weight of item
     */
    public Electronics (String description, double weight){
	this.description = description;
	this.itemWeight = weight;
    }
    
    @Override
    /** ocerride default toString and instead gives a nice formatted string
     * @return String describing object
     */
    public java.lang.String toString(){
	return description();
    }

    /** Getter - gets the descriptio of the item
     * @return description of item in a nice format
     */
    public String description(){
	return "".format ("Electronics: %s (%f KG)",description,itemWeight); //"Electronics: " + description +  " (" + itemWeight + " KG)";
    }

    /** Getter - Gets the weight of the item
     * @return the weight of the item
     */
    public double getWeight(){
	return this.itemWeight;
    }
}
