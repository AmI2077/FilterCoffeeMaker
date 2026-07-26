package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_coffee_image_placeholder
import coffee.shared.generated.resources.ic_edit_24
import coffee.shared.generated.resources.recipe
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.blueGrayText
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.ui.theme.getComfortaRegular
import org.example.project.core.ui.theme.regularTextStyle
import org.example.project.core.ui.theme.white
import org.example.project.features.coffeeDetails.ui.vm.CoffeeDetailsScreenModel
import org.example.project.features.coffeeDetails.ui.vm.CoffeeDetailsScreenUiState
import org.example.project.features.newCoffee.ui.composables.CoffeeBalance
import org.example.project.features.newCoffee.ui.composables.CoffeeImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CoffeeDetailsScreen(
    modifier: Modifier = Modifier,
    coffeeId: Int,
    screenModel: CoffeeDetailsScreenModel,
    onRecipeBtnClick: (coffeeId: Int) -> Unit
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        screenModel.getCoffeeDetails(coffeeId)
    }

    val state by screenModel.state.collectAsStateWithLifecycle()

    CoffeeDetailsContainer(
        modifier = modifier,
        onRecipeBtnClick = { onRecipeBtnClick(coffeeId) },
        onEditBtnClick = {},
    ) {
        when (state) {
            is CoffeeDetailsScreenUiState.Content -> {
                CoffeeDetailsScreenContent(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .fillMaxSize(),
                    coffee = (state as CoffeeDetailsScreenUiState.Content).coffee
                )
            }

            CoffeeDetailsScreenUiState.Loading -> {

            }
        }
    }
}

@Composable
fun CoffeeDetailsContainer(
    modifier: Modifier = Modifier,
    onRecipeBtnClick: () -> Unit,
    onEditBtnClick: () -> Unit,
    content: @Composable (BoxScope.() -> Unit)
) {
    Box(modifier = modifier) {
        content()
        ButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            firstBtn = {
                AppButton(
                    modifier = Modifier.weight(0.8f),
                    text = {
                        Text(
                            modifier = Modifier.padding(vertical = 15.dp),
                            text = stringResource(Res.string.recipe),
                            style = regularTextStyle.copy(
                                fontFamily = getComfortaRegular(),
                                color = white,
                                fontSize = 20.sp,
                            )
                        )
                    },
                    icon = null
                ) {
                    onRecipeBtnClick()
                }
            },
            secondBtn = {
                AppButton(
                    contentPadding = PaddingValues(vertical = 15.dp),
                    text = null,
                    icon = {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            painter = painterResource(Res.drawable.ic_edit_24),
                            contentDescription = null,
                        )
                    }
                ) {
                    onEditBtnClick()
                }
            },
        )
    }
}

@Composable
fun CoffeeDetailsScreenContent(
    modifier: Modifier,
    coffee: Coffee,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize(),
        ) {
            CoffeeImage(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = blueGrayText,
                        shape = RoundedCornerShape(UiDefaults.IMAGE_CORNERS_RADIUS.dp)
                    )
                    .aspectRatio(UiDefaults.IMAGE_ASPECT_RATIO)
                    .fillMaxWidth(),
                placeholder = painterResource(Res.drawable.ic_coffee_image_placeholder),
                model = coffee.imagePath
            )
            coffee.qGrade?.let {
                QGradeBox(
                    modifier = Modifier
                        .padding(15.dp),
                    qGrade = coffee.qGrade
                )
            }
        }
        Row {
            RegularAppText(
                text = "${coffee.roasting}, ",
                fontSize = 20.sp,
                color = blueGrayText,
                fontFamily = getComfortaRegular()
            )
            RegularAppText(
                text = coffee.processingMethod,
                fontSize = 20.sp,
                color = blueGrayText,
                fontFamily = getComfortaRegular()
            )
        }
        Spacer(Modifier.padding(top = 5.dp))
        RegularAppText(
            text = coffee.title,
            fontSize = 30.sp,
            fontFamily = getComfortaBold()
        )
        Spacer(Modifier.padding(top = 5.dp))
        RegularAppText(
            text = coffee.tasteDescription,
            maxLines = Int.MAX_VALUE,
            fontSize = 20.sp,
            fontFamily = getComfortaRegular()
        )
        Spacer(Modifier.padding(top = 20.dp))
        CoffeeBalance(
            density = coffee.density,
            acidity = coffee.acidity
        )
        Spacer(Modifier.padding(top = 10.dp))
        coffee.userDescription?.let {
            UserDescription(
                description = coffee.userDescription
            )
        }
    }
}