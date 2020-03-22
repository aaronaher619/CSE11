import java.util.Scanner;

public class DoubleArrayMain {

    // These checkXXX methods are used to make sure a user's input
    // satisfies constraints for a particular command.
    // You can add new ones for commands you write.
    // Each method returns true if the command is in an incorrect format
    // and false if it's correct
    private boolean checkBadCreateUsage(String[] parts) {
	return parts.length != 2 || !isInt(parts[1]);
    }
    private boolean checkBadAppendUsage(String[] parts) {
	return parts.length != 2 || !isDouble(parts[1]);
    }
    private boolean checkBadGetUsage(String[] parts) {
	return parts.length != 2 || !isInt(parts[1]);
    }
    private boolean checkBadSetUsage(String[] parts) {
	return parts.length != 3 || !isInt(parts[1]) || !isDouble(parts[2]);
    }
    private boolean checkBadDeleteUsage(String[] parts) {
        return parts.length != 2 || !isInt(parts[1]);
    }
    private boolean checkBadInsertUsage(String[] parts) {
        return parts.length != 3 || !isInt(parts[1]) || !isDouble(parts[2]);
    }
    private boolean checkBadReverseFromUsage(String[] parts) {
        return parts.length != 3 || !isInt(parts[1]) || !isInt(parts[2]);
    }
    private boolean checkBadSwapUsage(String[] parts) {
        return parts.length != 3 || !isInt(parts[1]) || !isInt(parts[2]);
    }

    // These printXXX methods are used to respond to the user by
    // printing out helpful instructions in case they mis-use a
    // command.  You can add new ones for commands you write.
    private void printCreateUsage() {
	System.out.println("Usage: create n  Creates a new array with capacity n (an integer)");
    }
    private void printAppendUsage() {
	System.out.println("Usage: append d  Adds d (a decimal) to the current array");
    }
    private void printSetUsage() {
	System.out.println("Usage: set n d  Sets the index n (an integer) to the value d (a decimal)");
    }
    private void printGetUsage() {
        System.out.println("Usage: get n  Gets the element at index n (an integer)");
    }
    private void printDeleteUsage() {
        System.out.println("Usage: delete n  Deletes the element at index n (an integer)");
    }
    private void printInsertUsage() {
        System.out.println("Usage: insert n d  Inserts the value d (a decimal) at the index n (an ingeter)");
    }
    private void printReverseFromUsage() {
        System.out.println("Usage: reverse-from n n  Reverses the array from start index n (an integer) to end index n (an integer)");
    }
    private void printSwapUsage() {
        System.out.println("Usage: swap n n Swaps the elements at the first index n (an integer) with the second index n (an integer)");
    }    
    private void printUnknownCommand(String[] parts) {
	System.out.println("Unknown command: " + parts[0]);
    }

    // This one is a little more complicated.
    // It uses == to make sure that each time we return an array from getArray(),
    // an actual copy is returned.  If the two arrays are ==, then it means
    // a copy wasn't made.
    // As long as they're different, it prints out one of the arrays for the user
    // (in a slightly different format, to tell apary DoubleArray11 prints from
    // copied array prints)
    private void handleArray(String[] parts, DoubleArray11 currentArray) {
	double[] dArr1 = currentArray.getArray();
	double[] dArr2 = currentArray.getArray();
	if(dArr1 == dArr2) {
	    System.out.println("Error: arrays were identical");
	}
	else {
	    String out = "{";
	    for(int i = 0; i < dArr1.length; i += 1) {
		String template;
		if(i < dArr1.length - 1) {
		    template = "%6.3g, ";
		}
		else {
		    template = "%6.3g";
		}
		out += String.format(template, dArr1[i]);
	    }
	    out += "}";
	    System.out.println(out);
	}
    }


    // These use Scanner methods to check if a string will be valid
    // as a particular number (to avoid errors with parseDouble/parseInt)
    private boolean isDouble(String s)
    {
	Scanner scnr=new Scanner(s);
	return scnr.hasNextDouble();
    }
    private  boolean isInt(String s)
    {
	Scanner scnr=new Scanner(s);
	return scnr.hasNextInt();
    }
    private void runInterpreter() {

	DoubleArray11 currentArray = new DoubleArray11();
	Scanner scan = new Scanner(System.in);    
	String cmd = "";

	while(true) {
	    System.err.print("> ");
	    cmd = scan.nextLine();
	    String[] parts = cmd.split("\\s+");
	    if(parts.length == 0) { continue; }
	    switch(parts[0]) {
	    case "show":
		System.out.println(currentArray.toString());
		continue;
	    case "create":
		if(checkBadCreateUsage(parts)) {
		    printCreateUsage();
		    continue;
		}
		currentArray = new DoubleArray11(Integer.parseInt(parts[1]));
		continue;
	    case "append":
		if(checkBadAppendUsage(parts)) {
		    printAppendUsage();
		    continue;
		}
		currentArray.append(Double.parseDouble(parts[1]));
		continue;
            case "size":
                System.out.println(currentArray.getSize());
                continue;
	    case "capacity":
		System.out.println(currentArray.getCapacity());
		continue;
	    case "get":
		if(checkBadGetUsage(parts)) {
		    printCreateUsage();
		    continue;
		}
		System.out.println(currentArray.getElement(Integer.parseInt(parts[1])));
                continue;

	    case "get-array":
		System.out.println(currentArray.getArray());
		continue;
		
	    case "count-arrays":
		System.out.println(DoubleArray11.getNArrays());
		continue;
	    case "set":
		if(checkBadSetUsage(parts)) {
		    printSetUsage();
		    continue;
		}
		currentArray.setElement(Integer.parseInt(parts[1]), Double.parseDouble(parts[2]));
		continue;
	    case "array":
		handleArray(parts, currentArray);
		continue;
	    case "delete":
		if(checkBadDeleteUsage(parts)) {
                    printDeleteUsage();
                    continue;
                }
		currentArray.delete(Integer.parseInt(parts[1]));
		continue;
	    case "insert":
		if(checkBadInsertUsage(parts)) {
                    printInsertUsage();
                    continue;
                }
		currentArray.insert(Integer.parseInt(parts[1]), Double.parseDouble(parts[2]));
		continue;
	    case "reverse":
		currentArray.reverse();
		continue;
	    case "reverse-from":
		if(checkBadReverseFromUsage(parts)) {
                    printReverseFromUsage();
                    continue;
                }
		currentArray.reverse(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
		continue;
	    case "swap":
		if(checkBadSwapUsage(parts)) {
                    printSwapUsage();
                    continue;
                }
		currentArray.swap(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
		continue;
	    case "min":
		System.out.println(currentArray.min());
		continue;
            case "max":
                System.out.println(currentArray.max());
                continue;

		// You will add delete, insert, reverse, swap, min, max, size

		// delete, insert, swap, and reverse have no output

		// min, max, and size will print the corresponding quantity
          

	    case "exit":
		break;
	    default:
		printUnknownCommand(parts);
		continue;
	    }
	    break;
	} 
    }
    public static void main(String[] args) {
	DoubleArrayMain myMain = new DoubleArrayMain();
	myMain.runInterpreter();
    }

}