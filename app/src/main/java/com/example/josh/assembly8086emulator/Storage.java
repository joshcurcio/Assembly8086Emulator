package com.example.josh.assembly8086emulator;

/**
 * Created by Josh on 9/27/2016.
 */

public abstract class Storage
{
    protected String name;
    protected int size;

    public String toHex(String val)
    {
        //100101110b
        //101 || 101d
        //101o
        //010101h || 0x1234 || 0x1234h
        val = val.toLowerCase();
        try
        {
            if(Integer.parseInt(val) < 0)
            {
                String newVal = "";
                val = val.substring(1);
                do
                {
                    val = '0' + val;
                } while (val.length() < 16);

                val = Integer.toBinaryString(Integer.parseInt(val));
                for(int i = 0; i < val.length(); i++)
                {
                    if (val.charAt(i) == '0')
                    {
                        newVal += '1';
                    }
                    else
                    {
                        newVal += '0';
                    }
                }
                int twosCompliment = Integer.parseInt(newVal, 2) + 1;
                return Integer.toHexString(twosCompliment);
            }
            else
            {
                return Integer.toHexString(Integer.parseInt(val));
            }

        }
        catch(Exception e)
        {
            //must be something other than decimal or a decimal with a d at the end
            char suffix = val.charAt(val.length()-1);
            if(suffix == 'd' && !val.startsWith("0x") && suffix != 'h')
            {
                return Integer.toHexString(Integer.parseInt(val.substring(0, val.length() - 1)));
            }
            else if(suffix == 'b' && !val.startsWith("0x"))
            {
                return Integer.toBinaryString(Integer.parseInt(val.substring(0,val.length()-1),2));
            }
            else if(suffix == 'o')
            {
                return Integer.toBinaryString(Integer.parseInt(val.substring(0,val.length()-1),8));
            }
            else
            {
                val = val.replace("h","");
                val = val.replace("x","");
                return val;
            }
        }
    }
}
