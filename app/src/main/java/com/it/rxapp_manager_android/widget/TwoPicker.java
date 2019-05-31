package com.it.rxapp_manager_android.widget;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.it.rxapp_manager_android.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.WheelPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * Created by zhouwei on 2017/7/28.
 */

public class TwoPicker extends WheelPicker {
    private List<String> firstData = new ArrayList<>();
    private List<String> secondData = new ArrayList<>();


    private int selectedFirstIndex = 0;
    private int selectedSecondIndex = 0;

    private OnWheelListener onWheelListener;
    private OnPickListener onPickListener;

    private WheelView firstView, secondView;


    public TwoPicker(Activity activity, List<String> firstData, List<String> secondData) {
        super(activity);
        this.firstData = firstData;
        this.secondData = secondData;
    }

    public void setSelectedIndex(int firstIndex, int secondIndex, int threeIndex) {
        if (firstIndex >= 0 && firstIndex < firstData.size()) {
            selectedFirstIndex = firstIndex;
        }
        if (secondIndex >= 0 && secondIndex < secondData.size()) {
            selectedSecondIndex = secondIndex;
        }

    }

    public String getSelectedFirstItem() {
        if (firstData.size() > selectedFirstIndex) {
            return firstData.get(selectedFirstIndex);
        }
        return "";
    }

    public String getSelectedSecondItem() {
        if (secondData.size() > selectedSecondIndex) {
            return secondData.get(selectedSecondIndex);
        }
        return "";
    }


    @NonNull
    @Override
    protected View makeCenterView() {
        LinearLayout layout = new LinearLayout(activity);
        layout.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);

        firstView = createWheelView();

        int width = DisplayUtil.getWindowWidth(activity);
        firstView.setLayoutParams(new LinearLayout.LayoutParams(width / 3, WRAP_CONTENT));
        layout.addView(firstView);

        secondView = createWheelView();
        secondView.setLayoutParams(new LinearLayout.LayoutParams(width / 3, WRAP_CONTENT));
        layout.addView(secondView);

        firstView.setItems(firstData, selectedFirstIndex);
        firstView.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                selectedFirstIndex = index;
                if (onWheelListener != null) {
                    onWheelListener.onFirstWheeled(selectedFirstIndex, firstData.get(selectedFirstIndex));
                }
            }
        });
        secondView.setItems(secondData, selectedSecondIndex);
        secondView.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                selectedSecondIndex = index;
                if (onWheelListener != null) {
                    onWheelListener.onSecondWheeled(selectedSecondIndex, secondData.get(selectedSecondIndex));
                }
            }
        });

        return layout;
    }

    @Override
    public void onSubmit() {
        if (onPickListener != null) {
            onPickListener.onPicked(selectedFirstIndex, selectedSecondIndex);
        }
    }

    public void setOnWheelListener(OnWheelListener onWheelListener) {
        this.onWheelListener = onWheelListener;
    }

    public void setOnPickListener(OnPickListener onPickListener) {
        this.onPickListener = onPickListener;
    }

    public void updateDataSecond(List<String> secondData, int selectedSecondIndex) {
        this.secondData = secondData;
        this.selectedSecondIndex = selectedSecondIndex;

        secondView.setItems(secondData, selectedSecondIndex);
    }

    /**
     * 数据条目滑动监听器
     */
    public interface OnWheelListener {

        void onFirstWheeled(int index, String item);

        void onSecondWheeled(int index, String item);

    }

    /**
     * 数据选择完成监听器
     */
    public interface OnPickListener {

        void onPicked(int selectedFirstIndex, int selectedSecondIndex);

    }

}
