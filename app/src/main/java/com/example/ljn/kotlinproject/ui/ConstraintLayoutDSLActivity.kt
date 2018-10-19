package com.example.ljn.kotlinproject.ui

import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintSet.MATCH_CONSTRAINT_WRAP
import android.support.constraint.ConstraintSet.PARENT_ID
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.example.ljn.kotlinproject.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout

class ConstraintLayoutDSLActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(constraintLayout {

            val sessionStart = textView {
                id = R.id.session_start
                textSize = 18f
                textColor = R.attr.colorAccent
                text = "开始"
            }

            val sessionTitle = textView {
                id = R.id.session_title
                textSize = 18f
                textColor = Color.BLACK
                text = "开始"
            }.lparams(0, wrapContent)

            textView {
                id = R.id.session_details
                textSize = 16f
                text = "开始"
            }.lparams(0, wrapContent)

            applyConstraintSet {
                // Connect without block
                // You may use view id or view itself to define connections
                connect(
                        Side.START of R.id.session_start to Side.START of PARENT_ID margin dip(10),
                        Side.TOP of sessionStart to Side.TOP of PARENT_ID margin dip(10)
                )

                // constraint configuration on view
                sessionTitle {
                    connect(
                            Side.START to Side.START of PARENT_ID margin dip(20),
                            Side.TOP to Side.TOP of PARENT_ID margin dip(10),
                            Side.END to Side.END of PARENT_ID margin dip(10),
                            Side.BOTTOM to Side.TOP of R.id.session_details
                    )

                    horizontalBias = 0.0f
                    defaultWidth = MATCH_CONSTRAINT_WRAP
                }

                // constraint configuration on view Id
                R.id.session_details {
                    connect(
                            Side.START to Side.START of PARENT_ID margin dip(20),
                            Side.TOP to Side.BOTTOM of sessionTitle margin dip(2),
                            Side.END to Side.END of PARENT_ID margin dip(10),
                            Side.BOTTOM to Side.BOTTOM of PARENT_ID margin dip(2)
                    )

                    horizontalBias = 0.0f
                    defaultWidth = MATCH_CONSTRAINT_WRAP
                }
            }


        })
    }
}