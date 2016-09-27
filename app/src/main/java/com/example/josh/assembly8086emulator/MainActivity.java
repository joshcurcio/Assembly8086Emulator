package com.example.josh.assembly8086emulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText instructionET;
    private TextView outputTV;
    private TextView outputAX;
    private TextView outputAH;
    private TextView outputAL;
    private TextView outputBX;
    private TextView outputBH;
    private TextView outputBL;
    private TextView outputCX;
    private TextView outputCH;
    private TextView outputCL;
    private TextView outputDX;
    private TextView outputDH;
    private TextView outputDL;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.instructionET = (EditText) this.findViewById(R.id.instructionET);
        this.outputTV = (TextView) this.findViewById(R.id.outoutTV);

        this.outputAX = (TextView) this.findViewById(R.id.outputAX);
        this.outputAH = (TextView) this.findViewById(R.id.outputAH);
        this.outputAL = (TextView) this.findViewById(R.id.outputAL);
        this.outputBX = (TextView) this.findViewById(R.id.outputBX);
        this.outputBH = (TextView) this.findViewById(R.id.outputBH);
        this.outputBL = (TextView) this.findViewById(R.id.outputBL);
        this.outputCX = (TextView) this.findViewById(R.id.outputCX);
        this.outputCH = (TextView) this.findViewById(R.id.outputCH);
        this.outputCL = (TextView) this.findViewById(R.id.outputCL);
        this.outputDX = (TextView) this.findViewById(R.id.outputDX);
        this.outputDH = (TextView) this.findViewById(R.id.outputDH);
        this.outputDL = (TextView) this.findViewById(R.id.outputDL);

        this.outputAX.setText("0000000000000000");
        this.outputAH.setText("00000000");
        this.outputAL.setText("00000000");
        this.outputBX.setText("0000000000000000");
        this.outputBH.setText("00000000");
        this.outputBL.setText("00000000");
        this.outputCX.setText("0000000000000000");
        this.outputCH.setText("00000000");
        this.outputCL.setText("00000000");
        this.outputDX.setText("0000000000000000");
        this.outputDH.setText("00000000");
        this.outputDL.setText("00000000");
    }

    public void emulateButtonPressed(View v)
    {
        this.outputTV.setText(this.instructionET.getText().toString());

        String input = this.instructionET.getText().toString();
        String instruction = input.substring(0, input.indexOf(" "));
        String destination = input.substring(input.indexOf(" ") + 1, input.indexOf(","));
        String value = input.substring(input.indexOf(",") + 2);
        String binaryString = "";

        if(value.equalsIgnoreCase("ax"))
        {
            binaryString = this.outputAX.getText().toString();
        }
        else if(value.equalsIgnoreCase("bx"))
        {
            binaryString = this.outputBX.getText().toString();
        }
        else if(value.equalsIgnoreCase("cx"))
        {
            binaryString = this.outputCX.getText().toString();
        }
        else if(value.equalsIgnoreCase("dx"))
        {
            binaryString = this.outputDX.getText().toString();
        }
        else if(isNumeric(value))
        {
            binaryString = Integer.toBinaryString(Integer.parseInt(value));
            if (binaryString.length() < 16)
            {
                while (binaryString.length() < 16)
                {
                    binaryString = "0" + binaryString;
                }
            }
            else if (binaryString.length() > 16)
            {
                while (binaryString.length() > 15)
                {
                    binaryString = binaryString.substring(1);
                }
            }
        }


        if (instruction.equalsIgnoreCase("mov"))
        {
            if (destination.equalsIgnoreCase("ax"))
            {
                this.outputAX.setText(binaryString);
                this.outputAH.setText(binaryString.substring(0,8));
                this.outputAL.setText(binaryString.substring(8));
            }
            else if (destination.equalsIgnoreCase("ah"))
            {
                this.outputAH.setText(binaryString.substring(8));
            }
            else if (destination.equalsIgnoreCase("al"))
            {
                this.outputAL.setText(binaryString.substring(8));
            }
            else if (destination.equalsIgnoreCase("bx"))
            {
                this.outputBX.setText(binaryString);
                this.outputBH.setText(binaryString.substring(0,8));
                this.outputBL.setText(binaryString.substring(8));
            }
            else if (destination.equalsIgnoreCase("bh"))
            {
                this.outputBH.setText(binaryString.substring(8));
            }
            else if (destination.equalsIgnoreCase("bl"))
            {
                this.outputBL.setText(binaryString.substring(8));
            }
            else if (destination.equalsIgnoreCase("cx"))
            {
                this.outputCX.setText(binaryString);
                this.outputCH.setText(binaryString.substring(0,8));
                this.outputCL.setText(binaryString.substring(8));
            }
            else if (destination.equalsIgnoreCase("ch"))
            {
                this.outputCH.setText(binaryString.substring(8));
            }
            else if (destination.equalsIgnoreCase("cl"))
            {
                this.outputCL.setText(binaryString.substring(8));
            }
            else if (destination.equalsIgnoreCase("dx"))
            {
                this.outputDX.setText(binaryString);
                this.outputDH.setText(binaryString.substring(0,8));
                this.outputDL.setText(binaryString.substring(8));
            }
            else if (destination.equalsIgnoreCase("dh"))
            {
                this.outputDH.setText(binaryString.substring(8));
            }
            else if (destination.equalsIgnoreCase("dl"))
            {
                this.outputDL.setText(binaryString.substring(8));
            }

        }
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
