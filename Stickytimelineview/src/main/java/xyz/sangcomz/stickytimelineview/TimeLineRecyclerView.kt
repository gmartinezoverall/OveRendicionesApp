package xyz.sangcomz.stickytimelineview

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.GridLayoutAnimationController
import xyz.sangcomz.stickytimelineview.model.RecyclerViewAttr


/*
 * Copyright 2018 SeokWon Jeong.
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

class TimeLineRecyclerView(context: Context?, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    private var recyclerViewAttr: RecyclerViewAttr? = null

    init {
        attrs?.let {
            val a = context?.theme?.obtainStyledAttributes(
                    attrs,
                    R.styleable.TimeLineRecyclerView,
                    0, 0)

            a?.let {
                recyclerViewAttr =
                        RecyclerViewAttr(it.getColor(R.styleable.TimeLineRecyclerView_sectionBackgroundColor,
                                ContextCompat.getColor(context, R.color.colorDefaultBackground)),
                                it.getColor(R.styleable.TimeLineRecyclerView_sectionTitleTextColor,
                                        ContextCompat.getColor(context, R.color.colorDefaultTitle)),
                                it.getColor(R.styleable.TimeLineRecyclerView_sectionSubTitleTextColor,
                                        ContextCompat.getColor(context, R.color.colorDefaultSubTitle)),
                                it.getColor(R.styleable.TimeLineRecyclerView_timeLineColor,
                                        ContextCompat.getColor(context, R.color.colorDefaultTitle)),
                                it.getColor(R.styleable.TimeLineRecyclerView_timeLineCircleColor,
                                        ContextCompat.getColor(context, R.color.colorDefaultTitle)),
                                it.getColor(R.styleable.TimeLineRecyclerView_timeLineCircleStrokeColor,
                                        ContextCompat.getColor(context, R.color.colorDefaultStroke)),
                                it.getDimension(R.styleable.TimeLineRecyclerView_sectionTitleTextSize,
                                        context.resources.getDimension(R.dimen.title_text_size)),
                                it.getDimension(R.styleable.TimeLineRecyclerView_sectionSubTitleTextSize,
                                        context.resources.getDimension(R.dimen.sub_title_text_size)),
                                it.getDimension(R.styleable.TimeLineRecyclerView_timeLineWidth,
                                        context.resources.getDimension(R.dimen.line_width)),
                                it.getBoolean(R.styleable.TimeLineRecyclerView_isSticky, true))
            }

        }
    }

    /**
     * Add RecyclerSectionItemDecoration for Sticky TimeLineView
     *
     * @param callback SectionCallback
     */
    fun addItemDecoration(callback: RecyclerSectionItemDecoration.SectionCallback) {
        recyclerViewAttr?.let {
            this.addItemDecoration(RecyclerSectionItemDecoration(context,
                    callback,
                    it))
        }
    }


    override fun attachLayoutAnimationParameters(child: View, params: ViewGroup.LayoutParams,
                                                 index: Int, count: Int) {
        val layoutManager = layoutManager
        if (adapter != null && layoutManager is GridLayoutManager) {

            var animationParams: GridLayoutAnimationController.AnimationParameters? = params.layoutAnimationParameters as GridLayoutAnimationController.AnimationParameters

            if (animationParams == null) {
                animationParams = GridLayoutAnimationController.AnimationParameters()
                params.layoutAnimationParameters = animationParams
            }

            val columns = layoutManager.spanCount

            animationParams.count = count
            animationParams.index = index
            animationParams.columnsCount = columns
            animationParams.rowsCount = count / columns

            val invertedIndex = count - 1 - index
            animationParams.column = columns - 1 - invertedIndex % columns
            animationParams.row = animationParams.rowsCount - 1 - invertedIndex / columns

        } else {
            super.attachLayoutAnimationParameters(child, params, index, count)
        }
    }
}