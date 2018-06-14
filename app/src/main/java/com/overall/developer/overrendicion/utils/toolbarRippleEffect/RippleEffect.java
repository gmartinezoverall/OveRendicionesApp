package com.overall.developer.overrendicion.utils.toolbarRippleEffect;


import android.animation.Animator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toolbar;

import com.github.florent37.awesomebar.AwesomeBar;
import com.mancj.materialsearchbar.MaterialSearchBar;


public class RippleEffect
{
    public static void ShowSearchBar(AwesomeBar mTool_bar, MaterialSearchBar mSearchBar,  View searchAppBarLayout)
    {

        int cx = mTool_bar.getWidth() - (int) ((48)* (0.5f + 2));
        // start y-index for circular animation
        int cy = (mTool_bar.getTop() + mTool_bar.getBottom()) / 2;

        // calculate max radius
        int dx = Math.max(cx, mTool_bar.getWidth() - cx);
        int dy = Math.max(cy, mTool_bar.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Circular animation declaration begin
        final Animator animator;
        animator = io.codetail.animation.ViewAnimationUtils
                .createCircularReveal(searchAppBarLayout, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(400);
        mSearchBar.enableSearch();
        searchAppBarLayout.setVisibility(View.VISIBLE);
        mTool_bar.setVisibility(View.GONE);

        animator.start();
    }

    public static void hideSearchBar(AwesomeBar mTool_bar, MaterialSearchBar mSearchBar,  View searchAppBarLayout)
    {


        // start x-index for circular animation
        int cx = mTool_bar.getWidth() - (int) ((48) * (0.5f + 2));
        // start  y-index for circular animation
        int cy = (mTool_bar.getTop() + mTool_bar.getBottom()) / 2;

        // calculate max radius
        int dx = Math.max(cx, mTool_bar.getWidth() - cx);
        int dy = Math.max(cy, mTool_bar.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Circular animation declaration begin
        Animator animator;
        animator = io.codetail.animation.ViewAnimationUtils
                .createCircularReveal(searchAppBarLayout, cx, cy, finalRadius, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(400);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator)
            {

            }

            @Override
            public void onAnimationEnd(Animator animator)
            {
                mTool_bar.setVisibility(View.VISIBLE);
                searchAppBarLayout.setVisibility(View.GONE);


            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();

    }

}
