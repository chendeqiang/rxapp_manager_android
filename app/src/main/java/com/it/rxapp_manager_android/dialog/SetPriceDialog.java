package com.it.rxapp_manager_android.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.it.rxapp_manager_android.R;
import com.it.rxapp_manager_android.utils.TextUtil;


/**
 * 作者：Created by chendeqiang on 2017/9/26
 * 邮箱：keshuixiansheng@126.com
 * 描述：
 */
public class SetPriceDialog extends Dialog {

    private Button btnYes;
    private EditText etMsg;
    private View.OnClickListener onYesClickListener;

    public SetPriceDialog(@NonNull Context context) {
        super(context, R.style.dialog);
    }

    public String message() {
        if (!TextUtil.isEmpty(etMsg.getText().toString())) {
            return etMsg.getText().toString();
        }
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_setprice);
        btnYes = (Button) findViewById(R.id.btn_yes);
        etMsg = findViewById(R.id.et_set_price);
        btnYes.setOnClickListener(onYesClickListener);
    }

    public void setOnYesClickListener(View.OnClickListener listener) {
        this.onYesClickListener = listener;
    }

    @Override
    public void dismiss() {
        if (isShowing() && getContext() != null) {
            super.dismiss();
        }
    }

    @Override
    public void show() {
        if (!isShowing() && getContext() != null) {
            super.show();
        }
    }
}
