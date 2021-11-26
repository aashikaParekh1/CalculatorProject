package com.example.calculatorproject;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button clear;
    Button zero;
    Button enter;
    Button add;
    Button one;
    Button two;
    Button three;
    Button subtract;
    Button four;
    Button five;
    Button six;
    Button multiply;
    Button seven;
    Button eight;
    Button nine;
    Button divide;

    TextView output;

    int count; //counter
    //delimiter used to split up all the
    String spliter = "(?<=[*/+-])|(?=[*/+-])"; //regex format to make the steps quicker
    double res; //result
    String str;
    int temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //IDing all the buttons and textviews used in the front end xml
        clear = findViewById(R.id.button);
        zero = findViewById(R.id.button2);
        enter = findViewById(R.id.button3);
        add = findViewById(R.id.button4);
        one = findViewById(R.id.button5);
        two = findViewById(R.id.button6);
        three = findViewById(R.id.button7);
        subtract = findViewById(R.id.button8);
        four = findViewById(R.id.button9);
        five = findViewById(R.id.button10);
        six = findViewById(R.id.button11);
        multiply = findViewById(R.id.button12);
        seven = findViewById(R.id.button13);
        eight = findViewById(R.id.button14);
        nine = findViewById(R.id.button15);
        divide = findViewById(R.id.button16);

        output = findViewById(R.id.textView);

        //connecting the onClick to the main method
        clear.setOnClickListener(this);
        zero.setOnClickListener(this);
        enter.setOnClickListener(this);
        add.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        subtract.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        multiply.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        divide.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String expression = "";
        Button but = (Button) v;

        if (but == clear) { //if clear button is clicked than the textbox will clear
            output.setText("");
        }

        if (but == enter) { //what happens if the enter button is clicked
            if(output.getText().equals("0") || output.getText().equals("1") || output.getText().equals("2") || output.getText().equals("3") || output.getText().equals("4") || output.getText().equals("5") || output.getText().equals("6") || output.getText().equals("7") || output.getText().equals("8") || output.getText().equals("9"))
                output.setText(output.getText()); //make sure that 2 = 2...
            else
                computeEquation(); //calling the method below for the computations

        }

        else if (but != clear) { //shows what the textview had + any button clicked
            output.setText(output.getText().toString() + but.getText().toString());
        }

        str = output.getText().toString(); //turning the output into a string so it can go inside the array/arraylist

    }

    public void computeEquation() {
        try {
            count = 0;
            float numb1;  //before sign (*)
            float numb2;  //after sign (*)
            float numb3; //before sign (/)
            float numb4; //after sign (/)
            temp = 0;

            String[] result = str.split(spliter); //using the delimiter to split different operations in the output

            ArrayList<String> list = new ArrayList<>(Arrays.asList(result));
            //System.out.println("AASHIKA" + list); ~ used to see the array list that was prinitng


            while (count < list.size() - 1) {
                count++;
                if (list.get(count).contentEquals("*")) {
                    numb1 = (float) Float.parseFloat(list.get(count - 1)); //in order to switch from string to float
                    numb2 = (float) Float.parseFloat(list.get(count + 1)); //in order to switch from string to float
                    res = numb1 * numb2;
                    list.set(count - 1, Float.toString((float) res));
                    list.remove(count);
                    list.remove(count); //since the sign was removed everything got pushed back one so we are removing the number after the counter as well
                    count = 0; //set count back to 0, to start the loop over again

                } else if (list.get(count).contentEquals("/")) {
                    numb1 = (float) Float.parseFloat(list.get(count - 1)); //in order to switch from string to float
                    numb2 = (float) Float.parseFloat(list.get(count + 1)); //in order to switch from string to float
                    if (numb2 == 0)
                        temp = 1;
                    res = numb1 / numb2;
                    list.set(count - 1, Float.toString((float) res));
                    list.remove(count);
                    list.remove(count); //since the sign was removed everything got pushed back one so we are removing the number after the counter as well
                    count = 0; //set count back to 0, to start the loop over again
                }

            }

            count = 0;

            while (count < list.size() - 1) {
                count++;
                if (list.get(count).contentEquals("+")) {
                    numb3 = (float) Float.parseFloat(list.get(count - 1)); //in order to switch from string to float
                    numb4 = (float) Float.parseFloat(list.get(count + 1)); //in order to switch from string to float
                    res = numb3 + numb4;
                    list.set(count - 1, Float.toString((float) res));
                    list.remove(count);
                    list.remove(count); //since the sign was removed everything got pushed back one so we are removing the number after the counter as well
                    count = 0; //set count back to 0, to start the loop over again

                } else if (list.get(count).contentEquals("-")) {
                    numb3 = (float) Float.parseFloat(list.get(count - 1)); //in order to switch from string to float
                    numb4 = (float) Float.parseFloat(list.get(count + 1)); //in order to switch from string to float
                    res = numb3 - numb4;
                    list.set(count - 1, Float.toString((float) res));
                    list.remove(count);
                    list.remove(count); //since the sign was removed everything got pushed back one so we are removing the number after the counter as well
                    count = 0; //set count back to 0, to start the loop over again
                }

            }

            if (temp != 1) { //Check to see if num1 is being divided by 0
                if (res % 1 != 0) { //double equation comes out a double
                    output.setText(Double.toString(res));
                } else {
                    output.setText(Integer.toString((int) Math.round(res))); //Make sure an int equation comes out as an int
                }
            } else
                output.setText("ERROR"); //for the divided by 0 computations

        } catch(Exception e ){
            output.setText("ERROR"); //overall error
        }

    }
}









