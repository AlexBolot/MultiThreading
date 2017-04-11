package multiThreading.seance3et4.operation;

import java.io.Serializable;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Operation	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 11/04/17 23:51
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public abstract class Operation implements Serializable
{
    double[] operands;
    
    public abstract double compute ();
    
    public void setOperands (double[] operands)
    {
        this.operands = operands;
    }
}
