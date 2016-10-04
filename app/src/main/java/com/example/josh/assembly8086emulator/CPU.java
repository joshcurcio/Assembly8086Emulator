package com.example.josh.assembly8086emulator;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Josh on 9/27/2016.
 */

public class CPU
{
    static GeneralPurposeRegister ax = new GeneralPurposeRegister("ax",16,"ah","al");
    static GeneralPurposeRegister bx = new GeneralPurposeRegister("bx",16,"bh","bl");
    static GeneralPurposeRegister cx = new GeneralPurposeRegister("cx",16,"ch","cl");
    static GeneralPurposeRegister dx = new GeneralPurposeRegister("dx",16,"dh","dl");
    static HashMap<String, Storage> registers = new HashMap<String, Storage>() {{ put("ax", ax);
        put("bx", bx); put("cx", cx); put("dx", dx);}};
    static HashMap<String, String> variables = new HashMap<String, String>();

    static void processInstruction(LinkedList<String> parts)
    {
        String command = parts.get(0).toLowerCase();
        if(command.equals("mov"))
        {
            Storage dest = registers.get(parts.get(1).toLowerCase());
            if(dest == null) //dest must be a variable or a subregister
            {

                boolean placed = false;
                for(Storage s : registers.values())
                {
                    if(s instanceof GeneralPurposeRegister)
                    {
                        if (((GeneralPurposeRegister) s).hasHighSubRegister(parts.get(1).toLowerCase())) {
                            ((GeneralPurposeRegister) s).loadHigh(parts.get(2).toLowerCase());
                            placed = true;
                            break;
                        } else if (((GeneralPurposeRegister) s).hasLowSubRegister(parts.get(1).toLowerCase())) {
                            ((GeneralPurposeRegister) s).loadLow(parts.get(2).toLowerCase());
                            placed = true;
                            break;
                        }
                    }
                }
                if(!placed)
                {
                    //destination must be a variable or does not exist
                    variables.put(parts.get(1).toLowerCase(), parts.get(2).toLowerCase());
                }
            }
            else
            {
                //we have our destination register
                ((GeneralPurposeRegister)dest).load(parts.get(2));
            }

        }
    }
}
