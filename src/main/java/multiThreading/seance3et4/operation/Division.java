package multiThreading.seance3et4.operation;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Division	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 11/04/17 23:51
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Division extends Operation
{
    @Override
    public double compute ()
    {
        if(operands.length == 1) return operands[0];
        
        double quotient = operands[0];
        
        for (int i = 1; i < operands.length; i++)
        {
            if(operands[i] == 0) return Integer.MAX_VALUE;
            
            quotient /= operands[i];
        }
        
        return quotient;
    }
}
