package multiThreading.seance3et4.operation;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Soustraction	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 11/04/17 23:51
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Soustraction extends Operation
{
    @Override
    public double compute ()
    {
        if(operands.length == 1) return operands[0];
        
        double difference = operands[0];
        
        for (int i = 1; i < operands.length; i++)
        {
            difference -= operands[i];
        }
        
        return difference;
    }
}
