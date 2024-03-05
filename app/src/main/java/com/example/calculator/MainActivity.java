package com.example.calculator ;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_Tv;
    TextView solution_Tv ;

    MaterialButton button_1,button_2,button_3,button_ac;
    MaterialButton button_4,button_5,button_6,button_add;
    MaterialButton button_7,button_8,button_9,button_subtract;
    MaterialButton button_clear,button_0,button_decimal,button_divide;
    MaterialButton button_bracket1,button_bracket2,button_equal,button_multiply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_Tv = findViewById(R.id.result_Tv);
        solution_Tv = findViewById(R.id.solution_Tv);

        assignid(button_0,R.id.button_0);
        assignid(button_1,R.id.button_1);
        assignid(button_2,R.id.button_2);
        assignid(button_3,R.id.button_3);
        assignid(button_4,R.id.button_4);
        assignid(button_5,R.id.button_5);
        assignid(button_6,R.id.button_6);
        assignid(button_7,R.id.button_7);
        assignid(button_8,R.id.button_8);
        assignid(button_9,R.id.button_9);
        assignid(button_clear,R.id.button_clear);
        assignid(button_ac,R.id.button_ac);
        assignid(button_add,R.id.button_add);
        assignid(button_multiply,R.id.button_multiply);
        assignid(button_decimal,R.id.button_decimal);
        assignid(button_divide,R.id.button_divide);
        assignid(button_bracket1,R.id.button_bracket1);
        assignid(button_bracket2,R.id.button_bracket2);
        assignid(button_equal,R.id.button_equal);
        assignid(button_subtract,R.id.button_subtract);




    }

    void assignid(MaterialButton btn,int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button =(MaterialButton) v;
        String buttonText = button.getText() .toString();
        String dataToCalculate = solution_Tv.getText() .toString();

if(buttonText.equals("AC")) {
    solution_Tv.setText("");
    result_Tv.setText("0");
    return;
}
if(buttonText.equals("=")) {
    solution_Tv.setText(result_Tv.getText());
    return;
}
if(buttonText.equals("C")){
    dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);

}else {
    dataToCalculate = dataToCalculate + buttonText;
}
        solution_Tv.setText(dataToCalculate);

String finalResult = getResult(dataToCalculate);

if(!finalResult.equals("Error")) {

    result_Tv.setText(finalResult);
}

    }
    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            return context.evaluateString(scriptable,data,"javascript",1,null).toString();
        } catch (Exception e) {
        return "Error";
        }


    }

}