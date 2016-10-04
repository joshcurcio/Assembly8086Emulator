package com.example.josh.assembly8086emulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

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
        this.showRegisterValues();
    }

    public void emulateButtonPressed(View v)
    {
        String input = this.instructionET.getText().toString();
        LinkedList<String> parts = this.getParts(input);
        CPU.processInstruction(parts);
        this.showRegisterValues();

    }

    private void showRegisterValues()
    {
        this.outputAX.setText(((GeneralPurposeRegister)CPU.registers.get("ax")).getValue());
        this.outputBX.setText(((GeneralPurposeRegister)CPU.registers.get("bx")).getValue());
        this.outputCX.setText(((GeneralPurposeRegister)CPU.registers.get("cx")).getValue());
        this.outputDX.setText(((GeneralPurposeRegister)CPU.registers.get("dx")).getValue());
    }

    private LinkedList<String> getParts(String entry)
    {
        LinkedList<String> answer = new LinkedList<String>();
        entry = entry.trim();
        String command = "";
        int pos = 0;

        //get command
        while(entry.charAt(pos) != ' ')
        {
            command += entry.charAt(pos);
            pos++;
        }
        answer.addLast(command);

        if(pos == entry.length())
        {
            return answer;
        }

        //skip whitespace
        while(entry.charAt(pos) == ' ')
        {
            pos++;
        }

        //read dest
        String dest = "";
        while(pos != entry.length() && entry.charAt(pos) != ',' && entry.charAt(pos) != ' ')
        {
            dest += entry.charAt(pos);
            pos++;
        }
        answer.addLast(dest);

        //was this a command
        if(pos == entry.length())
        {
            return answer;
        }

        while(pos != entry.length())
        {
            //skip whitespace
            while(entry.charAt(pos) == ' ')
            {
                pos++;
            }

            //move passed comma
            pos++;

            //skip whitespace
            while(entry.charAt(pos) == ' ')
            {
                pos++;
            }

            //read param
            String param = "";
            while(pos != entry.length() && entry.charAt(pos) != ',' && entry.charAt(pos) != ' ')
            {
                param += entry.charAt(pos);
                pos++;
            }
            answer.addLast(param);
        }

        return answer;

    }


}
