package com.example.monanvietngon.views.widgets.sildermenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.monanvietngon.R;

import java.util.List;

public class SliderMenuImpl extends FrameLayout implements SliderMenu {

    // danh sach cac menu trong slider
    private List<Menu> menus;
    private View rootView;
    private ImageButton btnNext;
    private ImageButton btnPrev;
    private TextView tvTitle;
    private Menu currentMenu;
    private int currentIndex;
    private OnMenuChanged onMenuChanged;
    private float MIN_DISTANCE = 100f;

    public SliderMenuImpl(@NonNull Context context) {
        super(context);
        initView();
    }

    public SliderMenuImpl(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SliderMenuImpl(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public SliderMenuImpl(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @SuppressLint("ClickableViewAccessibility")
    void initView() {

        currentMenu = new Menu(-1, "");

        rootView = LayoutInflater.from(getContext()).inflate(R.layout.slider_menu, this);
        btnNext = rootView.findViewById(R.id.btn_next);
        btnPrev = rootView.findViewById(R.id.btn_prev);
        tvTitle = rootView.findViewById(R.id.tv_title);

        tvTitle.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x1 = 0, x2 = 0;
                switch(motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        x1 = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = motionEvent.getX();
                        float deltaX = x2 - x1;

                        if (Math.abs(deltaX) > MIN_DISTANCE)
                        {
                            // Left to Right swipe action
                            if (x2 > x1)
                            {
                                prev();
                            }

                            // Right to left swipe action
                            else
                            {
                                next();
                            }

                        }
                        else
                        {
                            // consider as something else - a screen tap for example
                        }
                        break;
                }
                return true;
            }
        });

        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }
        });

        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                prev();
            }
        });

        tvTitle.setText(currentMenu.getTitle());

    }

    private void next() {
        if (menus == null) return;
        if (currentIndex + 1 < menus.size()) {
            currentIndex++;
            currentMenu = menus.get(currentIndex );
            onMenuChange();
        } else {
            currentIndex = 0;
            currentMenu = menus.get(currentIndex);
            onMenuChange();
        }
    }

    private void prev() {
        if (menus == null) return;
        if (currentIndex - 1 >= 0) {
            currentIndex--;
            currentMenu = menus.get(currentIndex);
            onMenuChange();
        } else {
            currentIndex = menus.size() - 1;
            currentMenu = menus.get(currentIndex);
            onMenuChange();
        }
    }

    private void onMenuChange() {
        if (onMenuChanged != null) onMenuChanged.onChange(currentMenu);
        tvTitle.setText(currentMenu.getTitle());
    }

    @Override
    public void setMenu(List<Menu> menus) {
        this.menus = menus;
        currentMenu = menus.get(0);
        onMenuChange();
    }

    @Override
    public void setOnMenuChange(OnMenuChanged l) {
        this.onMenuChanged = l;
    }
}
