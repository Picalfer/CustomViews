package com.landfathich.customviews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.landfathich.customviews.databinding.PartButtonsBinding

enum class BottomButtonAction {
    POSITIVE, NEGATIVE
}

typealias OnBottomButtonsActionListener = (BottomButtonAction) -> Unit

class BottomButtonsView(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int,
) : ConstraintLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val binding: PartButtonsBinding

    private var listener: OnBottomButtonsActionListener? = null

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attributeSet,
        defStyleAttr,
        0
    )

    constructor(context: Context, attributeSet: AttributeSet?) : this(
        context,
        attributeSet,
        0
    ) // when we include our component in xml

    constructor(context: Context) : this(context, null) // when we inflate a view in code

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_buttons, this, true)
        binding = PartButtonsBinding.bind(this)
        initializeAttributes(attributeSet, defStyleAttr, defStyleRes)
        initListeners()
    }

    private fun initializeAttributes(
        attributeSet: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
    ) {
        if (attributeSet == null) return
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.BottomButtonsView,
            defStyleAttr,
            defStyleRes
        )

        with(binding) {
            val positiveButtonText =
                typedArray.getString(R.styleable.BottomButtonsView_bottomPositiveButtonText)
            positiveButton.text = positiveButtonText ?: "Ok"

            val negativeButtonText =
                typedArray.getString(R.styleable.BottomButtonsView_bottomNegativeButtonText)
            negativeButton.text = negativeButtonText ?: "Cancel"

            val positiveButtonBgColor = typedArray.getColor(
                R.styleable.BottomButtonsView_bottomPositiveBackgroundColor,
                Color.BLACK
            )
            positiveButton.backgroundTintList = ColorStateList.valueOf(positiveButtonBgColor)

            val negativeButtonBgColor = typedArray.getColor(
                R.styleable.BottomButtonsView_bottomNegativeBackgroundColor,
                Color.WHITE
            )
            negativeButton.backgroundTintList = ColorStateList.valueOf(negativeButtonBgColor)

            val isProgressMode =
                typedArray.getBoolean(R.styleable.BottomButtonsView_bottomProgressMode, false)
            if (isProgressMode) {
                positiveButton.visibility = INVISIBLE
                negativeButton.visibility = INVISIBLE
                progress.visibility = VISIBLE
            } else {
                positiveButton.visibility = VISIBLE
                negativeButton.visibility = VISIBLE
                progress.visibility = INVISIBLE
            }

            val positiveButtonTextColor = typedArray.getColor(
                R.styleable.BottomButtonsView_bottomPositiveTextColor,
                Color.WHITE
            )
            positiveButton.setTextColor(positiveButtonTextColor)

            val negativeButtonTextColor = typedArray.getColor(
                R.styleable.BottomButtonsView_bottomNegativeTextColor,
                Color.BLACK
            )
            negativeButton.setTextColor(negativeButtonTextColor)
        }

        typedArray.recycle()
    }

    private fun initListeners() {
        binding.positiveButton.setOnClickListener {
            this.listener?.invoke(BottomButtonAction.POSITIVE)
        }
        binding.negativeButton.setOnClickListener {
            this.listener?.invoke(BottomButtonAction.NEGATIVE)
        }
    }

    fun setListener(listener: OnBottomButtonsActionListener?) {
        this.listener = listener
    }
}