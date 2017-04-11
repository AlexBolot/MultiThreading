package multiThreading.seance3et4.operation;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Addition	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 11/04/17 23:51
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Addition extends Operation
{
    public double compute ()
    {
        double sum = 0;
        
        for (double d : operands)
        {
            sum += d;
        }
        
        return sum;
    }
}
