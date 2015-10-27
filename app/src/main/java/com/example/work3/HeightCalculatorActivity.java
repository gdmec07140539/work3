package com.example.work3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class HeightCalculatorActivity extends Activity {
    private Button calculatorButton;
    //���������
    private EditText weightEditText;
    //����ѡ���
    private CheckBox manCheckBox;
    //Ů�������
    private CheckBox womanCheckBox;
    //��ʾ���
    private TextView resultTextView;

    public HeightCalculatorActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //����ҳ�沼��
         //setContentView(R.layout.main);
        //��main.ҳ�沼���л�ö�Ӧ��UI�ؼ�
        setContentView(R.layout.activity_height_calculator);
        calculatorButton=(Button)findViewById(R.id.calculator);
        weightEditText=(EditText)findViewById(R.id.weight);
        manCheckBox=(CheckBox)findViewById(R.id.man);
        womanCheckBox=(CheckBox)findViewById(R.id.woman);
        resultTextView=(TextView)findViewById((R.id.result));

    }
    @Override
    protected void onStart(){
        super.onStart();
        //ע���¼�
        registerEvent();

    }

    private void registerEvent()
    {
        //ע�ᰴť�¼�
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // �ж��Ƿ�����д��������
                if (!weightEditText.getText().toString().trim().equals("")) {
                    //�ж��Ƿ���ѡ���Ա�
                    if (manCheckBox.isChecked() || womanCheckBox.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("-------------�������--------------\n");
                        if (manCheckBox.isChecked()) {
                            sb.append("���Ա�׼��ߣ�");
                            //ִ������
                            double result = evaluateHeight(weight, "��");
                            sb.append((int) result + "����");
                        }
                        if (womanCheckBox.isChecked()) {
                            sb.append("Ů�Ա�׼��ߣ�");
                            //ִ������
                            double result = evaluateHeight(weight, "Ů");
                            sb.append((int) result + "����");
                        }
                        //���ҳ����ʾ���
                        resultTextView.setText(sb.toString());
                    } else {
                        showMessage(" ��ѡ���Ա�");
                    }
                } else {
                    showMessage("����������");
                }
            }
        });
    }

    /**
     *
     * ���㴦��ִ�д�����¼�
     */
    private double evaluateHeight(double weight,String sex){
        double height;
        if(sex=="��"){
            height=170-(62-weight)/0.6;
        }else{
            height =158-(52-weight)/0.5;
        }
        return height;
    }

    /**
     *��Ϣ��ʾ
     */
    private  void  showMessage(String message){
        //��ʾ��
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("ϵͳ��Ϣ");
        alert.setMessage(message);
        alert.setButton("ȷ��", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();//��ʾ����

    }
    public boolean onCreateOptioinMenu(Menu menu){
        menu.add(Menu.NONE, 1,menu.NONE, "�˳�");
        return super.onCreateOptionsMenu(menu);
    }

    /**
     *
     *�˵��¼�
     */
    public boolean onOptionsItemSelect(MenuItem item){
        //TODO Auto-generated stub
        switch (item.getItemId()){
            case 1://�˳�
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_height_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
