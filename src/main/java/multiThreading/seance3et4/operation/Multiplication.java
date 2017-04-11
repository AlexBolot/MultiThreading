package multiThreading.seance3et4.operation;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Multiplication	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 11/04/17 23:51
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Multiplication extends Operation
{
    @Override
    public double compute ()
    {
        double product = operands[0];
        
        for (int i = 1; i < operands.length; i++)
        {
            product *= operands[i];
        }
        
        return product;
    }
}
