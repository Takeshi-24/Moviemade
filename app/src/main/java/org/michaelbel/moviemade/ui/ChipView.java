package org.michaelbel.moviemade.ui;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.michaelbel.moviemade.utils.DrawableUtil;
import org.michaelbel.moviemade.utils.LayoutHelper;

import androidx.annotation.StringRes;
import androidx.cardview.widget.CardView;

public class ChipView extends FrameLayout {

    private TextView textText;
    private CardView cardView;
    private Rect rect = new Rect();

    public ChipView(Context context) {
        super(context);

        cardView = new CardView(context);
        cardView.setCardElevation(0);
        cardView.setUseCompatPadding(true);
        cardView.setPreventCornerOverlap(false);
        cardView.setForeground(DrawableUtil.INSTANCE.selectableItemBackgroundBorderlessDrawable(context));
        //cardView.setLayoutParams(LayoutHelper.makeFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));
        addView(cardView);

        textText = new TextView(context);
        textText.setLines(1);
        textText.setMaxLines(1);
        textText.setGravity(Gravity.CENTER_VERTICAL);
        textText.setEllipsize(TextUtils.TruncateAt.END);
        textText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textText.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        textText.setLayoutParams(LayoutHelper.makeFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER_VERTICAL, 14, 6, 14, 6));
        cardView.addView(textText);
    }

    public ChipView setText(String text) {
        textText.setText(text);
        return this;
    }

    public ChipView setText(@StringRes int textId) {
        textText.setText(getContext().getText(textId).toString().toUpperCase());
        return this;
    }

    public ChipView setRadius(float radius) {
        cardView.setRadius(radius);
        return this;
    }

    public ChipView setTextColor(int color) {
        textText.setTextColor(color);
        return this;
    }

    public ChipView setColorBackground(int color) {
        cardView.setCardBackgroundColor(color);
        return this;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (cardView.getForeground() != null) {
            if (rect.contains((int) event.getX(), (int) event.getY())) {
                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                cardView.getForeground().setHotspot(event.getX(), event.getY());
            }
        }

        return super.onTouchEvent(event);
    }
}