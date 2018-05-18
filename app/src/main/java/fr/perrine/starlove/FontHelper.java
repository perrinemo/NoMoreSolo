package fr.perrine.starlove;

import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;

public class FontHelper {
    public static void setFont(TextView textView, String fontName) {
        if (fontName != null) {
            try {
                Typeface typeface = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName);
                textView.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }
}
