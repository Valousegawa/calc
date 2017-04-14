package com.a2017.dev.insta.calculatrice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button digit0;
    Button digit1;
    Button digit2;
    Button digit3;
    Button digit4;
    Button digit5;
    Button digit6;
    Button digit7;
    Button digit8;
    Button digit9;

    Button digitP;
    Button digitM;
    Button digitMu;
    Button digitC;
    Button digitD;
    Button digitPo;
    Button egal;

    TextView ecran;
    CalculateModel model;

    /* Avec un tableau :
    * for(int i = 0; i < tab.length; i++){
    *    tab[i] = (Button) findViewById(getRessources().getIdentifier('digit" + i, "id", getPackageName)));
    * }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        digit0 = (Button)findViewById(R.id.digit0);
        digit1 = (Button)findViewById(R.id.digit1);
        digit2 = (Button)findViewById(R.id.digit2);
        digit3 = (Button)findViewById(R.id.digit3);
        digit4 = (Button)findViewById(R.id.digit4);
        digit5 = (Button)findViewById(R.id.digit5);
        digit6 = (Button)findViewById(R.id.digit6);
        digit7 = (Button)findViewById(R.id.digit7);
        digit8 = (Button)findViewById(R.id.digit8);
        digit9 = (Button)findViewById(R.id.digit9);

        digitP = (Button)findViewById(R.id.digitP);
        digitM = (Button)findViewById(R.id.digitM);
        digitMu = (Button)findViewById(R.id.digitMu);
        digitC = (Button)findViewById(R.id.digitC);
        digitPo = (Button)findViewById(R.id.digitPo);
        digitD = (Button)findViewById(R.id.digitD);
        egal = (Button)findViewById(R.id.egal);

        digit0.setOnClickListener(this);
        digit1.setOnClickListener(this);
        digit2.setOnClickListener(this);
        digit3.setOnClickListener(this);
        digit4.setOnClickListener(this);
        digit5.setOnClickListener(this);
        digit6.setOnClickListener(this);
        digit7.setOnClickListener(this);
        digit8.setOnClickListener(this);
        digit9.setOnClickListener(this);

        digitP.setOnClickListener(this);
        digitM.setOnClickListener(this);
        digitMu.setOnClickListener(this);
        digitC.setOnClickListener(this);
        digitPo.setOnClickListener(this);
        digitD.setOnClickListener(this);
        egal.setOnClickListener(this);

        ecran = (TextView) findViewById(R.id.result);

        model = new CalculateModel();
    }

    public void onClick(View v) {
        testToast(v);
        if(v.equals(digitP)){
            model.setOperator(Constant.OPERATOR_PLUS);
        } else if(v.equals(digitM)) {
            model.setOperator(Constant.OPERATOR_MOINS);
        } else if(v.equals(digitMu)) {
            model.setOperator(Constant.OPERATOR_MULT);
        } else if(v.equals(digitD)) {
            model.setOperator(Constant.OPERATOR_DIV);
        } else if(v.equals(digitC)) {
            model.clear();
            ecran.setText("0");
        } else if(v.equals(egal)) {
            try {
                double result = model.calcul();
                ecran.setText(String.valueOf(result));
                model.clear();
                model.setFirstValue(String.valueOf(result));
            } catch(NumberFormatException e){
                showToast("Invalid Value");
                model.clear();
                ecran.setText("0");
            }
        } else {
            Button numberButton = (Button) v;
            if(model.getOperator() == ""){
                model.addFirstValueNumber(numberButton.getText().toString());
                ecran.setText(String.valueOf(model.getFirstValue()));
            } else {
                model.addSecondValueNumber(numberButton.getText().toString());
                ecran.setText(String.valueOf(model.getSecondValue()));
            }
        }
    }

    public void testToast(View v) {
        switch (v.getId()) {
            case  R.id.digit0: {
                showToast(Constant.DIGIT_0);
                break;
            }
            case R.id.digit1: {
                showToast(Constant.DIGIT_1);
                break;
            }
            case R.id.digit2: {
                showToast(Constant.DIGIT_2);
                break;
            }
            case R.id.digit3: {
                showToast(Constant.DIGIT_3);
                break;
            }
            case R.id.digit4: {
                showToast(Constant.DIGIT_4);
                break;
            }
            case R.id.digit5: {
                showToast(Constant.DIGIT_5);
                break;
            }
            case R.id.digit6: {
                showToast(Constant.DIGIT_6);
                break;
            }
            case R.id.digit7: {
                showToast(Constant.DIGIT_7);
                break;
            }
            case R.id.digit8: {
                showToast(Constant.DIGIT_8);
                break;
            }
            case R.id.digit9: {
                showToast(Constant.DIGIT_9);
                break;
            }
            case R.id.digitP: {
                showToast(Constant.OPERATOR_PLUS);
                break;
            }
            case R.id.digitM: {
                showToast(Constant.OPERATOR_MOINS);
                break;
            }
            case R.id.digitMu: {
                showToast(Constant.OPERATOR_MULT);
                break;
            }
            case R.id.digitD: {
                showToast(Constant.OPERATOR_DIV);
                break;
            }
            case R.id.digitC: {
                showToast(Constant.OPERATOR_C);
                break;
            }
            case R.id.digitPo: {
                showToast(Constant.OPERATOR_POINT);
                break;
            }
            case R.id.egal: {
                showToast(Constant.OPERATOR_EGAL);
                break;
            }
        }
    }

    public void showToast(String text){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.show();
    }
}
