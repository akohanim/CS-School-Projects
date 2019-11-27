/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;
import java.util. *;
import java.util.stream.IntStream;


public class Matrix
        
{
final int row;
final int col;
final int [][] element;
	
Matrix() // constructor to initialize w/o any input parameters 
{
    this(2,2);
}

Matrix (int row, int col) //makes matrices with values being random integers rangeing 0-1000
{
    this.row = row;
    this.col = col;
    this.element = IntStream.range(0,row).parallel().mapToObj(
                                               x-> new Random().ints(0,1000).limit(col).toArray())
                                               .toArray(int[][]::new);
}

Matrix(int[][] input) //takes in an array as an input parameter and produces an equivalent matrix object with that particular array.
{
    this.row = input.length;
    this.col = input.length;
    this.element = Arrays.stream(input).parallel().map(
                                    x -> Arrays.copyOf(x, x.length))
                                    .toArray(int[][]::new);
}

static boolean vaildiateSubAddHad(Matrix A, Matrix B)
{
    return (A.row == B.row && A.col == B.col);
}

static boolean validateDot (Matrix A, Matrix B)
{
    return (A.col == B.row);
}



Matrix Subtract(Matrix input)
{
    return new Matrix (IntStream.range(0,this.row).mapToObj(
                                                        x-> IntStream.range(0,this.col).map(
							y-> this.element[x][y] - input.element[x][y]
                                                        ).toArray()
                                                    	).toArray(int[][]::new));
                                                        
}

Matrix Addition(Matrix input)
{
	return new Matrix (IntStream.range(0,this.row).mapToObj(
                                                                x-> IntStream.range(0,this.col).map(
								y-> this.element[x][y] + input.element[x][y]
								).toArray()
                                                		).toArray(int[][]::new) );
                                                               
}

Matrix Dot(Matrix input)
{
	return new Matrix (IntStream.range(0, this.row).mapToObj(
								i-> IntStream.range(0, input.col).map(
								j-> IntStream.range(0,this.col).map(																							k -> this.element[i][k] * input.element[i][j]
                                                                ).reduce(0, (a,b) -> a + b)
								).toArray()
								).toArray(int[][]::new));
}
                                                                

Matrix Hadamard(Matrix input)
{
	return new Matrix (IntStream.range(0,this.row).mapToObj(
                                				x-> IntStream.range(0,this.col).map(
                                				y-> this.element[x][y] * input.element[x][y]
								).toArray()
								).toArray(int[][]::new));
		
} 

Object Clone()
{
	return(Object) new Matrix (this.element);
}

	
@Override
public String toString()
    {
	return (Arrays.stream(this.element).map(
						r -> Arrays.stream(r).mapToObj(
						e -> String.format ("%6d", e)
                				).reduce("\n", (A, B) -> A + B)
                                                ).reduce("", (A, B) -> A + B));
				
    }
	
    boolean Equals(Object obj)
    {
        // Look for a method in Arrays that compares array values
        // Look for a method that compares the types of instances
         
        if (obj instanceof Matrix)  
            {
                Matrix input = (Matrix) obj;
                return Arrays.deepEquals(this.element, input.element);
            }
                
        else
            {
                return false;		
            }
    }
   //works 

}


