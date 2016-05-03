package lab1_7188;

import java.util.Scanner;

/*
 * 
 * @lab_1_7188
 * 9/5/15
 */
public class Lab1_7188 {
    
    /* declaration of HugeInteger class
     * declares initial length 
     * boolean to handle negative numbers
    */
    static class HugeInteger{

	private static final int NUM_DIGITS=40;
	private int digits[]=new int[NUM_DIGITS];
	private boolean positive;
                
        
	/*
        converts user inputs to instance of HugeInteger
        */
	public HugeInteger(String num){
                //accounts for length of user input
		int length = num.length();
                //search for and return negative input
		if(num.charAt(0)=='-'){
                    length--;
                    positive = false;
		}else{ 
                    positive = true;
		}
                //fills instace array with user input
		for(int i=0;i<length;i++){
                    digits[NUM_DIGITS-1-i] = 
			num.charAt(num.length()-1-i) - (int)'0';
		}
	}
        
        /*
        comparrison method to determine if inputs are equal 
        */
	public boolean isEqualTo(HugeInteger hi){
                //two HugeInteger objects
                //this
                //hi
            
                //if inputs have opposite signs and are not 0
		if(positive!=hi.positive && !isZero()){
                    return false;
		}
		for(int i=NUM_DIGITS-1;i>=0;i--){
                    if(digits[i]!=hi.digits[i]){
			return false;
                    }
		}
            return true;
	}
        /*
        comparrison method to determine if inputs are not equal
        */
        public boolean isNotEqualTo(HugeInteger hi){
                //two HugeInteger objects
                //this
                //hi
            
                //if inputs have opposite signs and are not 0
                if(positive!=hi.positive && !isZero()){
                    if(isEqualTo(hi)){
                        return false;
                    }
                }
            return true;
        }
        
        /*
        comparrison to determine which input is greater
        */
        public boolean isGreaterThan(HugeInteger hi){
                //two HugeInteger objects
                //this
                //hi
            
                //if inputs have opposite signs
                if(positive!=hi.positive){
                    //if first input is not positive
                    if(!positive){
                        return false;
                    }
                    return true;
                }
                //if inputs have the same sign
                else{
                    for(int i=0;i<digits.length;i++){
                        //skips leading 0's
                        if(this.digits[i]>0){
                            //if both inputs are positive and this is greater
                            if(positive && hi.positive && digits[i] > 
                                    hi.digits[i]){
                                return true;
                            }
                            //if both inputs are positive and hi is greater
                            else if(positive && hi.positive && digits[i] < 
                                    hi.digits[i]){
                                return false;
                            }
                            //if both values are negative and this is greater
                            else if(!positive && !hi.positive && digits[i] > 
                                    hi.digits[i]){
                                return false;
                            }
                            //if both values are negative and hi is greater
                            else if(!positive && !hi.positive && digits[i] < 
                                    hi.digits[i]);{
                                return true;
                            }
                        }
                    }
                }
                return true;
        }
        
        /*
        method to test if one input is less than the other
        */
        public boolean isLessThan(HugeInteger hi){
                //two HugeInteger objects
                //this
                //hi
            
                //if inputs have opposite signs 
                if(positive!=hi.positive){
                    //if this > hi then hi < this
                    if(isGreaterThan(hi)){
                        return false;
                    }
                }
                //if inputs are both postive
                else if(positive && hi.positive){
                    //if this > hi then hi < this
                    if(isGreaterThan(hi)){
                        return false;
                    }
                }
                //if inputs are both negative
                else if(!positive && !hi.positive){
                    //if this > hi then hi < this
                    if(isGreaterThan(hi)){
                        return false;
                    }
                }
                return true;
        }
        
        /*
        method to determine if this is greater than or equal to hi
        */
        public boolean isGreaterThanOrEqualTo(HugeInteger hi){
                //two HugeInteger objects
                //this
                //hi
            
                //if inputs have opposite signs or equal 0
                if(positive!=hi.positive || !isZero()){
                    //if this > hi
                    if(isGreaterThan(hi)){
                        return true;
                    }
                    //if this = hi
                    else if(isEqualTo(hi)){
                        return true;
                    }
                }
                return false;
        }
        
        /*
        method to determin if this is less than or equal to hi
        */
        public boolean isLessThanOrEqualTo(HugeInteger hi){
                //two HugeInteger objects
                //this
                //hi
            
                //if inputs have opposite signs or eual 0
                if(positive!=hi.positive || !isZero()){
                    //if this < hi
                    if(isLessThan(hi)){
                        return true;
                    }
                    //if this = hi
                    else if(isEqualTo(hi)){
                        return true;
                    }
                }
                return false;
        }
        
        /*
        method used to subtract inputs
        */
        private static int[] subtractArrayDigits(int[] array1, int[] array2){
            //two arrays passed (this, hi)
            
            //initialize array to store and return values
            int[] difference = new int[array2.length];
            //borrow variable for calculation
            int borrow = 0;
            //traverse the array from left to right
            for(int i=array2.length-1;i>=0;i--){
                
                //update difference array with new values
                difference[i] = array1[i] - array2[i] - borrow;
                
                //updating borrow for the next iteration
                borrow = (difference[i]<0)?1:0;
                difference[i] += (difference[i]<0)?10:0;
            }
            return difference;
        }
        
        /*
        method used to add inputs
        */
        private static int[] addArrayDigits(int[] array1, int[] array2){
            //two arrays passed (this, hi)
            
            //initialize array to store and return values
            int[] sum = new int[array2.length];
            //variable to handle values over 10
            int carry=0;
            //traverse the array from right to left
            for(int i=array2.length-1;i>=0;i--){
                
                //updating sum array with new values
                sum[i] = (array1[i] + array2[i] + carry) % 10;
                
                //updates carry for next iteration
                carry = (array1[i] + array2[i] + carry)/10;
                
            }
            return sum;
        }
        
        /*
        method used to multiply inputs
        */
        private static int[] multiplyArrayDigits(int[] array1, int[] array2){
            //two arrays passed (this, hi)
            
            //store and update values
            int value =0;
            int[] sum = new int[array1.length];
            
            for(int i=array1.length-1;i>=0;i--){
                value = array1[i] * array2[i];
                sum[i] = sum[i] + value;
            }
            return sum;
        }
        
        /*
        method used to handle sign change before addition
        */
        public void add(HugeInteger hi){
            // two huge integer objects are accessible 
            // 1. this
            // 2. hi
		
            if(positive!=hi.positive){ 
		if(this.positive){
                    // "this" is positive, hi is negative
                    hi.negate(); // negate hi temporarily
				
                    if(this.isGreaterThan(hi)){ 
                        // |this| > |hi| [example: 8+(-5)]
			this.digits = subtractArrayDigits(this.digits, hi.digits);
                    }else{	// example 8+(-15)
			// |this| <= |hi| [example: 8+(-15)]
			this.digits = subtractArrayDigits(hi.digits, this.digits);
			// negate the "this"
			negate();
                    }
	
                    hi.negate(); // restore hi's sign
                }else{
                    // "this" is negative, hi is positive
                    this.negate(); // negate this temporarily
				
                    if(hi.isLessThan(this)){ 
			// |hi| > |this| [example: 8+(-5)]
			this.digits = subtractArrayDigits(this.digits, hi.digits);
                    }else{	// example 8+(-15)
			// |hi| <= |this| [example: 8+(-15)]
			this.digits = subtractArrayDigits(hi.digits, this.digits);
			// negate the "hi"
			negate();
                    }
	
                    this.negate(); // restore hi's sign
		}
		}else{
                    // same sign, easy :)
                    digits = addArrayDigits(hi.digits, this.digits);
            }	
	}
        
        /*
        method used to handle sign change before subtracion
        */
        public void subtract(HugeInteger hi){
            //two HugeInteger objects
            //this
            //hi
            
            //opposite signs
            if(positive!=hi.positive){ 
                if(this.positive){
                    // "this" is positive, hi is negative
                    this.negate(); // negate hi temporarily
				
                    if(this.isGreaterThan(hi)){ 
                        // |this| > |hi| [example: 8-(-5)]
                        hi.negate();
                        digits = addArrayDigits(this.digits, hi.digits);
                        negate();
                    }else{	// example 8-(-15)
                        // |this| <= |hi| [example: 8-(-15)]
                        digits = addArrayDigits(hi.digits, this.digits);
                        // negate the "this"
                        negate();
                    }
			
                        
                }
                else{
                    // "this" is negative, hi is positive
                    hi.negate(); // negate this temporarily
				
                    if(hi.isGreaterThan(this)){ 
                	// |hi| > |this| [example: -8-(-5)]
			this.digits = addArrayDigits(hi.digits, this.digits);
                    }else{	// example 8+(-15)
			// |hi| <= |this| [example: -8(-15)]
			this.digits = addArrayDigits(this.digits, hi.digits);
			// negate the "hi"
			hi.negate();
                    }
                    hi.negate(); //restore hi's sign
                }
	}
        else {
            if(positive && hi.positive){
                if(isGreaterThan(hi)){
                    //both are positive and this > hi
                    //example 8-5
                    this.digits = subtractArrayDigits(this.digits, hi.digits);
                }
                else{
                    //both are positive and this < hi
                    //example 8-15
                    this.digits = subtractArrayDigits(hi.digits, this.digits);
                    negate(); //flip final result sign example answer -7
                }
            }
            else if(!positive && !hi.positive){
                if(isLessThan(hi)){
                    //both signs are negative and this < hi
                    //example -8-(-15)
                    this.digits = subtractArrayDigits(this.digits, hi.digits);
                }
                else{
                    //both signs are negative and hi > this
                    //example -8-(-5)
                    this.digits = subtractArrayDigits(hi.digits, this.digits);
                    negate();
                }
            }
	}	
    }
     
    /*
    method used to handle sign change before multiplying    
    */    
    public void multiply(HugeInteger hi){
            if(positive!=hi.positive){
                if(!positive){
                    //signs are different and this is negative
                    //positive * negative = negative
                    negate();//negates this sign
                    this.digits = multiplyArrayDigits(digits, hi.digits);
                    negate();//makes answer negative
                }
                else{
                    //signs different and this is positive
                    //negative * positive = negative
                    hi.negate();//negates hi sign
                    this.digits = multiplyArrayDigits(digits, hi.digits);
                    negate();//make answer negative
                }
            }
            else{
                if(!positive && !hi.positive){
                    //both signs are negative
                    //negative * negative = positive
                    this.negate();//negate this
                    hi.negate();//negate hi
                    this.digits = multiplyArrayDigits(digits, hi.digits);
                }
                else{
                    //both signs are positive
                    //positive * positive = positive
                    this.digits = multiplyArrayDigits(digits, hi.digits);
                }
            }
        }

        /*
        method used to print values to a string
        */
        @Override
        public String toString() {
            //used to include all leading 0's 
            int counter = 0;
            
            //declaration of new string buffer
            StringBuffer numbers = new StringBuffer(digits.length);
            
            //adds negative sign for applicable numbers
            if(!positive){
                numbers.append('-');
            }
            
            for(int i=0;i<digits.length;i++){
                if(counter >= 1){//appends all numbers after leading 0's
                    numbers.append(digits[i]);
                }
                else if(counter == 0){//initialized to zero
                    
                    //locates first digit > 0 after leading 0's
                    if(digits[i] > 0){
                        numbers.append(digits[i]);
                        counter++;//makes next instance true
                    }
                }
            }
            return numbers.toString();
        }
        
        
        /*
        method used to change signs from 
        positive to negative and vice versa
        */
        public void negate()
        {
            positive=!positive;
        }
        
        /*
        method used to determine if inputs are zero
        */
        public boolean isZero(){
            for(int i=0;i<NUM_DIGITS;i++){
                if(digits[i] != 0){
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HugeInteger h1, h2; //instances of HugeInteger
        String num; //variable for user input
        Scanner scan=new Scanner(System.in);
		
	System.out.print("Please enter the first huge integer (h1): ");
	num=scan.nextLine(); //scans user input
	h1=new HugeInteger(num); //passes first input to h1
		
	System.out.print("Please enter the second huge integer (h2): ");
	num=scan.nextLine(); //scans user input
	h2=new HugeInteger(num); //passes second value to h2
                
		
	if(h1.isEqualTo(h2)){ //runs method to check equality
            System.out.println("h1 is equal to h2.");
	}
	if(h1.isNotEqualTo(h2)){ //runs method to check inequality
            System.out.println("h1 is not equal to h2.");
	}
	if(h1.isGreaterThan(h2)){ //runs method to check largest value
            System.out.println("h1 is greater than h2.");
	}
	if(h1.isLessThan(h2)){ //runs method to check lowest value
            System.out.println("h1 is less than to h2.");
	}
	if(h1.isGreaterThanOrEqualTo(h2)){ //runs check for largest or equal
            System.out.println("h1 is greater than or equal to h2.");
	}
	if(h1.isLessThanOrEqualTo(h2)){ //runs check for lowest or equal
            System.out.println("h1 is less than or equal to h2.");
	}
	h1.add(h2);
	System.out.printf("h1+h2=%s\n",h1);
	h1.subtract(h2);
	h1.subtract(h2);
	System.out.printf("h1-h2=%s\n",h1);
	h1.add(h2);
	h1.multiply(h2);
	System.out.printf("h1*h2=%s\n",h1);
    }
}
