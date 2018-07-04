package com.overall.developer.overrendicion.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;


import com.overall.developer.overrendicion.R;

import cn.refactor.lib.colordialog.ColorDialog;
import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by terry on 3/13/2018.
 */

public class CustomDialog
{
    public static ColorDialog dialogGifAndEditText(View view, GifDrawable gifFromResource, String tittle, final String message)
    {
        ColorDialog dialog = new ColorDialog(view.getContext());
        dialog.setTitle(tittle);
        dialog.setAnimationEnable(true);
        dialog.setContentImage(gifFromResource);
    /*    dialog.setPositiveListener(R.string.send, dialog1 ->
        {
            dialog1.dismiss();
            return message;

        }).setNegativeListener(R.string.cancel, dialog12 -> dialog12.dismiss()).show();*/
        return dialog;
    }



}
