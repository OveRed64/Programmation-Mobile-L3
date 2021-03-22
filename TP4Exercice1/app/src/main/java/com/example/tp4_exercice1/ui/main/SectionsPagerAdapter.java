package com.example.tp4_exercice1.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tp4_exercice1.R;
import com.example.tp4_exercice1.ui.main.NatureFragment;

import java.util.Locale;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NatureFragment.newInstance(0, mContext.getString(R.string.section_title_spring));
            case 1:
                return NatureFragment.newInstance(1, mContext.getString(R.string.section_title_summer));
            case 2:
                return NatureFragment.newInstance(2, mContext.getString(R.string.section_title_autumn));
            case 3:
                return NatureFragment.newInstance(3, mContext.getString(R.string.section_title_winter));
            case 4:
                return NatureFragment.newInstance(4, mContext.getString(R.string.section_menu));
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        android.content.res.Resources resources = mContext.getResources();
        String title="";
        Drawable icon=null;

        if( position == 4 ){
            title = mContext.getString(R.string.section_menu).toUpperCase(l);
            return new SpannableString(title);
        }

        switch (position) {
            case 0:
                title = mContext.getString(R.string.section_title_spring).toUpperCase(l);
                icon = getIcon(R.mipmap.spring);
                break;
            case 1:
                title = mContext.getString(R.string.section_title_summer).toUpperCase(l);
                icon = getIcon(R.mipmap.summer);
                break;
            case 2:
                title = mContext.getString(R.string.section_title_autumn).toUpperCase(l);
                icon = getIcon(R.mipmap.autumn);
                break;
            case 3:
                title = mContext.getString(R.string.section_title_winter).toUpperCase(l);
                icon = getIcon(R.mipmap.winter);
                break;
        }

        SpannableString sb = new SpannableString(" " + title);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(icon, ImageSpan.ALIGN_BASELINE);
        sb.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }

    private Drawable getIcon(int drawable){
        return  ResourcesCompat.getDrawable(mContext.getResources(),drawable,null);
    }

    @Override
    public int getCount() { return 5; }
}