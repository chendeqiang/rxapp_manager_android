package com.it.rxapp_manager_android.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.it.rxapp_manager_android.R;
import com.it.rxapp_manager_android.utils.TextUtil;


/**
 * Created by deqiangchen on 2017/7/13.
 */

public class RemarkDialog extends Dialog {
    private EditText etMsg;
    private Button btnCancel, btnAgain;
    private View.OnClickListener onOkClickListener, onCancelClickListener;

    public RemarkDialog(@NonNull Context context) {
        super(context, R.style.dialog);
    }

    public String message() {
        return etMsg.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_remark);
        etMsg = (EditText) findViewById(R.id.et_remark_content);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnAgain = (Button) findViewById(R.id.btn_ok);
        btnCancel.setOnClickListener(onCancelClickListener);
        btnAgain.setOnClickListener(onOkClickListener);
    }


    public void setOnCancelClickListener(View.OnClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
    }

    public void setOnOkClickListener(View.OnClickListener listener) {
        this.onOkClickListener = listener;
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
