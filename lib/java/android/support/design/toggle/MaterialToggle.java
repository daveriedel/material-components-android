/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.support.design.toggle;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.annotation.StyleRes;
import android.support.design.ripple.RippleUtils;
import android.support.design.theme.ThemeUtils;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * A convenience class for creating a Material toggle.
 *
 * <p>This class supplies updated Material styles for the toggle in the constructor. The widget will
 * display the correct default Material styles without the use of the style flag.
 *
 * <p>TODO: Extend documentation with a complete list of attributes the user can set.
 * The values being set in the styles need to be aligned with the base component.
 *
 * @see MaterialToggle
 */
public class MaterialToggle extends SwitchCompat {

  public MaterialToggle(Context context) {
    this(context, null /* attrs */);
  }

  public MaterialToggle(Context context, AttributeSet attrs) {
    this(context, attrs, R.attr.materialToggleStyle);
  }

  public MaterialToggle(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    ThemeUtils.checkAppCompatTheme(context);

    TypedArray attributes =
        context.obtainStyledAttributes(
            attrs,
            R.styleable.MaterialToggle,
            defStyleAttr,
            R.style.Widget_Design_Toggle_MaterialToggle);

    // Track
    // TODO: TrackTintMode needs to be added. I have also left the radius value
    // hardcoded for now due to the component change.
    GradientDrawable trackDrawable = new GradientDrawable();
    trackDrawable.setCornerRadius(20);
    setTrackDrawable(trackDrawable);
    ColorStateList trackTint = attributes.getColorStateList(R.styleable.MaterialToggle_trackTint);
    setTrackTintList(trackTint);

    // Thumb drawable
    // TODO: Add elevation to the thumb and need to work on the ThumbTintMode. I have
    // also left the setSize/Radius values hardcoded for now due to the component change.
    GradientDrawable thumbDrawable = new GradientDrawable();
    thumbDrawable.setSize(53, 53);
    thumbDrawable.setCornerRadius(26);
    setThumbDrawable(thumbDrawable);
    ColorStateList thumbTint = attributes.getColorStateList(R.styleable.MaterialToggle_thumbTint);
    setThumbTintList(thumbTint);

    // Thumb text
    @StyleRes
    int switchTextAppearanceStyleRes =
        attributes.getResourceId(R.styleable.MaterialToggle_switchTextAppearance, 0);
    setSwitchTextAppearance(this.getContext(), switchTextAppearanceStyleRes);
    int thumbTextPadding =
        attributes.getDimensionPixelSize(R.styleable.MaterialToggle_thumbTextPadding, 0);
    setThumbTextPadding(thumbTextPadding);
    boolean showText = attributes.getBoolean(R.styleable.MaterialToggle_showText, false);
    setShowText(showText);
    CharSequence onText = attributes.getString(R.styleable.MaterialToggle_android_textOn);
    setTextOn(onText);
    CharSequence offText = attributes.getString(R.styleable.MaterialToggle_android_textOff);
    setTextOff(offText);

    // Switch itself
    int switchMinWidth =
        attributes.getDimensionPixelSize(R.styleable.MaterialToggle_switchMinWidth, 0);
    setSwitchMinWidth(switchMinWidth);
    int switchPadding =
        attributes.getDimensionPixelSize(R.styleable.MaterialToggle_switchPadding, 0);
    setSwitchPadding(switchPadding);
    boolean isFocusable = attributes.getBoolean(R.styleable.MaterialToggle_android_focusable, true);
    setFocusable(isFocusable);
    boolean isClickable = attributes.getBoolean(R.styleable.MaterialToggle_android_clickable, true);
    setClickable(isClickable);
    @StyleRes
    int textAppearanceStyleRes =
        attributes.getResourceId(R.styleable.MaterialToggle_android_textAppearance, 0);
    setTextAppearance(textAppearanceStyleRes);
    TextViewCompat.setTextAppearance(this, textAppearanceStyleRes);
    int gravity = attributes.getInt(R.styleable.MaterialToggle_android_gravity, Gravity.CENTER);
    setGravity(gravity);

    // TODO: Ripple needs to be support by earlier versions and the ripple diameter is
    // smaller due to shape width. Alpha of the ripple needs attention.
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
      ColorStateList rippleColor =
          attributes.getColorStateList(R.styleable.MaterialToggle_rippleColor);
      RippleDrawable rp =
          new RippleDrawable(RippleUtils.convertToRippleDrawableColor(rippleColor), null, null);
      setBackground(rp);
    }

    attributes.recycle();
  }

}
