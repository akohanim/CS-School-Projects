/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;


 // @author akohanim

import java.util. *;


public class Driver {

	static int printOptions() //options for matrix manipulation
	{
		System.out.print("Select an option: " );
		System.out.print("\n1. Addtion \n2.Subtraction \n3.Dot Product \n4.Hadmard Product");
		int result = scan.nextInt();
		System.out.println("\n");
		return result;
	}
		
	
	static Scanner scan = new Scanner(System.in); 
        
        public static void main(String[] args) 
		{
			
			Matrix A = Driver.getMatrix("first");
				{
					System.out.println(A.toString());
				}
			Matrix B = Driver.getMatrix("second");
				{
					System.out.println(B.toString());
				}
			Matrix C = Driver.calculate(A,B);
				{
					System.out.println(C.toString());
				}
				
		}
				
	static Matrix getMatrix (String name)
            {
		System.out.println("Give the Parameters for the " + name + " matrix");
		System.out.println("Enter the number of rows: ");
		int row = scan.nextInt();
		System.out.println();
		System.out.print("Enter the number of columns: ");
		int col = scan.nextInt();
		return new Matrix (row,col);
            }
        
	static Matrix calculate (Matrix A, Matrix B)
            {
                boolean isValid;
                
                int selection = printOptions();
                                
            do
            {

                if (selection == 1 || selection == 2||selection == 4)
                    {
			isValid = Driver.validateSubAddHad(A, B);
                    }
		else if (selection == 3)
                    {
			isValid = Driver.validateDot(A, B);
                    }
    		else
                    {
			isValid = false;					
                    }
				
		if (!isValid)
                    {
                        System.out.print("invalid matricies please re-enter \n");
			
                        A = Driver.getMatrix("first"); 
                        
                        System.out.println(A.toString());
			
			B = Driver.getMatrix("second"); 
						
			System.out.println(B.toString());
                    }
					
            }
                                
            while(!isValid);
                    return Driver.doOpertaion(A, B, selection);
			
            }
        
		static boolean validateSubAddHad (Matrix A, Matrix B)
			{
				return (A.row == B.row && A.col == B.col);
			}
			
                static boolean validateDot(Matrix A, Matrix B)
			{
				return (A.col == B.col);
			}
		
		static Matrix doOpertaion (Matrix A, Matrix B, int selection)
			{
                            switch (selection)
                                {
				case 1:
					return A.Addition(B);
                                        
				case 2:
					return A.Subtract(B);
					
				case 3: 
					return A.Dot(B);
					
				default:
					return A.Hadamard(B); 
				}
				
			}
			
               		
}

