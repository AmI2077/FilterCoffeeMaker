package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.add_description
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.components.AppBackButton
import org.example.project.core.ui.components.AppOutlinedButton
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.backgroundColor
import org.example.project.core.ui.theme.getMontserratExtraBold
import org.example.project.core.ui.theme.getMontserratRegular
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.features.addCoffee.ui.composables.CoffeeBalance
import org.example.project.features.addCoffee.ui.composables.CoffeeImage
import org.example.project.features.coffeeDetails.store.CoffeeDetailsAction
import org.example.project.features.coffeeDetails.store.CoffeeDetailsScreenModel
import org.example.project.features.coffeeDetails.ui.utils.CoffeeDetailsScreenCallbacks
import org.example.project.features.editCoffee.store.EditCoffeeScreenModel
import org.example.project.features.editCoffee.ui.composables.EditBottomSheet
import org.jetbrains.compose.resources.stringResource

@Composable
fun CoffeeDetailsScreen(
    modifier: Modifier = Modifier,
    coffeeId: String,
    detailsScreenModel: CoffeeDetailsScreenModel,
    editScreenModel: EditCoffeeScreenModel,
    onRecipeBtnClick: (coffeeId: String) -> Unit,
    onBackBtnClick: () -> Unit
) {
    val state by detailsScreenModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        detailsScreenModel.loadCoffeeDetails(coffeeId)
    }

    LaunchedEffect(Unit) {
        detailsScreenModel.uiActions.collect { action ->
            when (action) {
                CoffeeDetailsAction.ClickOnRecipeBtn -> onRecipeBtnClick(coffeeId)
            }
        }
    }

    if (state.showEditBottomSheet) {
        EditBottomSheet(
            screenModel = editScreenModel,
            coffeeId = coffeeId,
            onDismissRequest = {
                detailsScreenModel.dismissEditBottomSheet()
                detailsScreenModel.loadCoffeeDetails(coffeeId)
            }
        )
    }

    state.content?.let { coffee ->
        Column(
            modifier = modifier
                .background(backgroundColor)
                .fillMaxHeight()
        ) {
            CoffeeImageView(
                coffee = coffee,
                onBackBtnClick = onBackBtnClick
            )
            CoffeeDetailsScreenInfo(
                modifier = Modifier
                    .offset(y = (-20).dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    ),
                coffee = coffee,
                showEditDescriptionField = state.showEditDescriptionField,
                callback = detailsScreenModel
            )
        }
    }
}

@Composable
fun CoffeeDetailsScreenInfo(
    modifier: Modifier = Modifier,
    coffee: Coffee,
    showEditDescriptionField: Boolean = false,
    callback: CoffeeDetailsScreenCallbacks
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .background(backgroundColor )
            .padding(
                top = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp,
                start = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp,
                end = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp
            )
            .verticalScroll(scrollState)
    ) {
        RoastingAndProcessingRow(
            roasting = coffee.roasting,
            processingMethod = coffee.processingMethod
        )
        Spacer(Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RegularAppText(
                modifier = Modifier.weight(0.6f),
                text = coffee.title,
                fontSize = 32.sp,
                lineHeight = 36.sp,
                fontFamily = getMontserratExtraBold(),
                maxLines = 2
            )
            Spacer(Modifier.width(50.dp))
            EditButton(
                onClick = {
                    callback.onEditBtnClick()
                }
            )
        }
        Spacer(Modifier.height(20.dp))
        RegularAppText(
            text = coffee.tasteDescription,
            maxLines = Int.MAX_VALUE,
            lineHeight = 25.sp,
            fontSize = 18.sp,
            fontFamily = getMontserratRegular(),
            color = textSecondaryColor
        )
        Spacer(Modifier.height(20.dp))
        CoffeeBalance(
            modifier = Modifier.fillMaxWidth(),
            density = coffee.density,
            acidity = coffee.acidity
        )
        Spacer(Modifier.height(25.dp))
        if (showEditDescriptionField) {
            EditDescriptionView(
                onSaveBtnClick = {
                    callback.onSaveDescription(it)
                },
                onCancellationClick = {
                    callback.onCancellationClick()
                },
            )
        } else {
            DescriptionView(
                description = coffee.userDescription,
                onAddDescriptionBtnClick = {
                    callback.onAddDescriptionBtnClick()
                }
            )
        }
        Spacer(Modifier.height(20.dp))
        RecipeButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                callback.onRecipeBtnClick()
            }
        )
    }
}

@Composable
private fun CoffeeImageView(
    modifier: Modifier = Modifier,
    coffee: Coffee,
    onBackBtnClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        CoffeeImage(
            modifier = Modifier
                .aspectRatio(1 / 1f)
                .fillMaxWidth(),
            shape = RoundedCornerShape(0),
            model = coffee.imagePath,
        )
        coffee.qGrade?.let {
            QGradeBox(
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp,
                        vertical = 40.dp
                    )
                    .align(Alignment.TopEnd),
                qGrade = coffee.qGrade
            )
        }
        AppBackButton(
            modifier = Modifier
                .padding(
                    horizontal = 20.dp,
                    vertical = 40.dp
                ),
            onClick = onBackBtnClick
        )
    }
}

@Composable
private fun DescriptionView(
    description: String?,
    onAddDescriptionBtnClick: () -> Unit,
) {
    if (description.isNullOrBlank()) {
        AppOutlinedButton(
            text = stringResource(Res.string.add_description),
            onClick = { onAddDescriptionBtnClick() }
        )
        Spacer(Modifier.height(40.dp))
    } else {
        UserDescription(
            description = description
        )
    }
}