package com.landfathich.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class BottomButtonsView(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int,
) : ConstraintLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attributeSet,
        defStyleAttr,
        0
    )
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0) // when we include our component in xml
    constructor(context: Context) : this(context, null) // when we inflate a view in code
}