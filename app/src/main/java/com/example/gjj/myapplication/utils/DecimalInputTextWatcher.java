package com.example.gjj.myapplication.utils;

import android.support.annotation.StringDef;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Pattern;

/**
 * 功能描述：小数输入文本观察类
 *
 */

public class DecimalInputTextWatcher implements TextWatcher {

    private final EditText mDecimalInputEt;

    private Pattern mPattern;

    private  AfterText afterText;


    /**
     * 不限制整数位数和小数位数
     */
    public DecimalInputTextWatcher(EditText decimalInputEt,AfterText afterText) {
        mDecimalInputEt = decimalInputEt;
        this.afterText = afterText;
    }

    /**
     * 限制整数位数或着限制小数位数
     *
     * @param type   限制类型"INT"整型,"DECIMAL"小数
     * @param number 限制位数
     */
    public DecimalInputTextWatcher(EditText decimalInputEt, @Type String type, int number,AfterText afterText) {
        mDecimalInputEt = decimalInputEt;
        this.afterText = afterText;
        if (type.equals("DECIMAL")) {//限制小数部分
            mPattern = Pattern.compile("^[0-9]+(\\.[0-9]{0," + number + "})?$");
        } else if (type.equals("INT")) {//限制整数部分
            mPattern = Pattern.compile("^[0-9]{0," + number + "}+(\\.[0-9]{0,})?$");
        }
    }

    /**
     * 既限制整数位数又限制小数位数
     *
     * @param integers 整数位数
     * @param decimals 小数位数
     */

    public DecimalInputTextWatcher(EditText decimalInputEt, int integers, int decimals,AfterText afterText) {
        mDecimalInputEt = decimalInputEt;
        this.afterText = afterText;
        mPattern = Pattern.compile("^[0-9]{0," + integers + "}+(\\.[0-9]{0," + decimals + "})?$");
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Editable editable = mDecimalInputEt.getText();
        String text = s.toString();
        if (TextUtils.isEmpty(text)) {
            afterText.after("");
            return;
        }
        if ((s.length() > 1) && (s.charAt(0) == '0') && s.charAt(1) != '.') {   //删除整数首位的“0”
            editable.delete(0, 1);
            return;
        }
        if (text.equals(".")) {                                    //首位是“.”自动补“0”
            editable.insert(0, "0");
            return;
        }
        if (mPattern != null && !mPattern.matcher(text).matches() && editable.length() > 0) {
            editable.delete(editable.length() - 1, editable.length());
            return;
        }
        //TODO：
        afterText.after(s.toString());

    }

    @StringDef({"INT","DECIMAL"})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }
    public interface AfterText{
        public void after(String string);
    }
}
