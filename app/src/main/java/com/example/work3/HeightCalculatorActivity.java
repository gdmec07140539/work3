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
    //体重输入框
    private EditText weightEditText;
    //男性选择框
    private CheckBox manCheckBox;
    //女性输入框
    private CheckBox womanCheckBox;
    //显示结果
    private TextView resultTextView;

    public HeightCalculatorActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //设置页面布局
         //setContentView(R.layout.main);
        //从main.页面布局中获得对应的UI控件
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
        //注册事件
        registerEvent();

    }

    private void registerEvent()
    {
        //注册按钮事件
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断是否已填写体重数据
                if (!weightEditText.getText().toString().trim().equals("")) {
                    //判断是否已选择性别
                    if (manCheckBox.isChecked() || womanCheckBox.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("-------------评估结果--------------\n");
                        if (manCheckBox.isChecked()) {
                            sb.append("男性标准身高：");
                            //执行运算
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "厘米");
                        }
                        if (womanCheckBox.isChecked()) {
                            sb.append("女性标准身高：");
                            //执行运算
                            double result = evaluateHeight(weight, "女");
                            sb.append((int) result + "厘米");
                        }
                        //输出页面显示结果
                        resultTextView.setText(sb.toString());
                    } else {
                        showMessage(" 请选择性别");
                    }
                } else {
                    showMessage("请输入体重");
                }
            }
        });
    }

    /**
     *
     * 计算处理执行代码的事件
     */
    private double evaluateHeight(double weight,String sex){
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height =158-(52-weight)/0.5;
        }
        return height;
    }

    /**
     *消息提示
     */
    private  void  showMessage(String message){
        //提示框
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();//显示窗口

    }
    public boolean onCreateOptioinMenu(Menu menu){
        menu.add(Menu.NONE, 1,menu.NONE, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    /**
     *
     *菜单事件
     */
    public boolean onOptionsItemSelect(MenuItem item){
        //TODO Auto-generated stub
        switch (item.getItemId()){
            case 1://退出
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
