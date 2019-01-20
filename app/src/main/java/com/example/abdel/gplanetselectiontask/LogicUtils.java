package com.example.abdel.gplanetselectiontask;

import com.example.abdel.gplanetselectiontask.Readers.Interval;
import com.example.abdel.gplanetselectiontask.Readers.Reader;

import java.util.List;
import java.util.Stack;

public class LogicUtils {

    public static boolean isNumber(String s)
    {
        if(s.equals(""))
            return false;
        for(int i=0;i<s.length();i++)
            if(s.charAt(i) < '0' || s.charAt(i) > '9')
                return false;
        return true;
    }

    public static boolean isOverlapped(Interval i1, Interval i2)
    {
        if (i2.getStart() <= i1.getEnd())
            return true;
        return false;
    }

    public static Interval mergeIntervals(Interval i1, Interval i2)
    {
        return new Interval(i1.getStart(),i2.getEnd());
    }

    public static String getName(List<Reader> readerList, int id)
    {
        for(Reader reader : readerList)
            if(reader.getId() == id)
                return reader.getReaderName();
        return "";
    }

    public static int calculateNumberOfPages(Stack<Interval> intervals)
    {
        int result = 0;
        while (!intervals.isEmpty())
        {
            Interval i = intervals.pop();
            result += i.getEnd() - i.getStart() + 1;
        }
        return result;
    }
}
