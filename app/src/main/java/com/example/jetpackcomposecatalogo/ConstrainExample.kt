package com.example.jetpackcomposecatalogo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ConstraintExample1() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (CyanBox, BlueBox, MagentaBox, YellowBox, RedBox) = createRefs()

        Box(
            Modifier
                .size(125.dp)
                .background(Color.Cyan)
                .constrainAs(CyanBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)

                })
        Box(
            Modifier
                .size(125.dp)
                .background(Color.Blue)
                .constrainAs(BlueBox) {
                    bottom.linkTo(CyanBox.top)
                    end.linkTo(CyanBox.start)
                })
        Box(
            Modifier
                .size(125.dp)
                .background(Color.Magenta)
                .constrainAs(MagentaBox) {
                    bottom.linkTo(CyanBox.top)
                    start.linkTo(CyanBox.end)
                })
        Box(
            Modifier
                .size(125.dp)
                .background(Color.Yellow)
                .constrainAs(YellowBox) {
                    top.linkTo(CyanBox.bottom)
                    start.linkTo(CyanBox.end)
                })
        Box(
            Modifier
                .size(125.dp)
                .background(Color.Red)
                .constrainAs(RedBox) {
                    top.linkTo(CyanBox.bottom)
                    end.linkTo(CyanBox.start)
                })
    }
}

@Composable
fun ConstraintExampleGuide() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val boxRed = createRef()
        val topGuide = createGuidelineFromTop(0.1f)
        val startGuide = createGuidelineFromStart(0.25f)

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(boxRed) {
                top.linkTo(topGuide)
                start.linkTo(startGuide)
            })
    }
}

@Composable
fun ConstraintBarrier() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (boxRed, boxBlue, boxYellow) = createRefs()
        val barrier = createEndBarrier(boxRed, boxBlue)

        Box(
            Modifier
                .size(125.dp)
                .background(Color.Red)
                .constrainAs(boxRed) {
                    start.linkTo(parent.start, margin = 16.dp)
                })
        Box(
            Modifier
                .size(235.dp)
                .background(Color.Blue)
                .constrainAs(boxBlue) {
                    top.linkTo(boxRed.bottom)
                    start.linkTo(parent.start, margin = 32.dp)
                })
        Box(
            Modifier
                .size(50.dp)
                .background(Color.Yellow)
                .constrainAs(boxYellow) {
                    start.linkTo(barrier)
                }
        )

    }
}

@Preview
@Composable
fun ConstraintChainExample(){
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (boxRed, boxBlue, boxYellow) = createRefs()

        Box(
            Modifier
                .size(75.dp)
                .background(Color.Red)
                .constrainAs(boxRed) {
                    start.linkTo(parent.start)
                    end.linkTo(boxBlue.start)
                })
        Box(
            Modifier
                .size(75.dp)
                .background(Color.Blue)
                .constrainAs(boxBlue) {
                    start.linkTo(boxRed.end)
                    end.linkTo(boxYellow.start)
                })
        Box(
            Modifier
                .size(75.dp)
                .background(Color.Yellow)
                .constrainAs(boxYellow) {
                    start.linkTo(boxBlue.end)
                    end.linkTo(parent.end)
                }
        )

        createHorizontalChain(boxRed,boxBlue,boxYellow, chainStyle = ChainStyle.SpreadInside)

    }
}