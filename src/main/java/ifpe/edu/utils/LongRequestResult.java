package ifpe.edu.utils;

import java.util.ArrayList;

public class LongRequestResult extends RequestResult
{
    public ArrayList<Long> Data;
    
    public LongRequestResult()
    {
        this.hasErrors = false;
        this.Data = new ArrayList<Long>();
    }
}
