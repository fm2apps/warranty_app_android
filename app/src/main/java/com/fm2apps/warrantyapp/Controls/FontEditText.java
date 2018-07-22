package com.fm2apps.warrantyapp.Controls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import static java.security.AccessController.getContext;

/**
 * Created by mohamed on 10/23/2017.
 */

public class FontEditText extends EditText {
    public FontEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "DroidKufi-Bold.ttf");
        setTypeface(tf);
    }
}
