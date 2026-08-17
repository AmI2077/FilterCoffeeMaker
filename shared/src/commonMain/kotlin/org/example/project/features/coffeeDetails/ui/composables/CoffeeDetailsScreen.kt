package org.example.project.features.coffeeDetails.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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
import coffee.shared.generated.resources.ic_edit_24
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.components.AppBackButton
import org.example.project.core.ui.components.AppOutlinedButton
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.backgroundColor
import org.example.project.core.ui.theme.getMontserratBold
import org.example.project.core.ui.theme.getMontserratRegular
import org.example.project.core.ui.theme.lightGray
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.features.addCoffee.ui.composables.CoffeeBalance
import org.example.project.features.addCoffee.ui.composables.CoffeeImage
import org.example.project.features.coffeeDetails.store.CoffeeDetailsAction
import org.example.project.features.coffeeDetails.store.CoffeeDetailsScreenModel
import org.example.project.features.editCoffee.store.EditCoffeeScreenModel
import org.example.project.features.editCoffee.ui.composables.EditBottomSheet
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun CoffeeDetailsScreen(
    modifier: Modifier = Modifier,
    verticalPaddings: PaddingValues = PaddingValues(0.dp),
    horizontalPaddings: PaddingValues = PaddingValues(
        horizontal = UiDefaults.HORIZONTAL_SCREEN_PADDING.dp
    ),
    coffeeId: String,
    detailsScreenModel: CoffeeDetailsScreenModel,
    editScreenModel: EditCoffeeScreenModel,
    onRecipeBtnClick: (coffeeId: String) -> Unit,
    onBackBtnClick: () -> Unit
) {
    val scrollState = rememberScrollState()

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

    val state by detailsScreenModel.state.collectAsStateWithLifecycle()

    state.content?.let { coffee ->
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
        Box(
            modifier = Modifier
                .padding(verticalPaddings)
                .fillMaxHeight()
        ) {
            CoffeeImageView(
                modifier = Modifier
                    .aspectRatio(1 / 1f)
                    .fillMaxWidth(),
                coffee = coffee,
                onBackBtnClick = onBackBtnClick
            )

            CoffeeDetailsScreenInfo(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    )
                    .padding(horizontalPaddings)
                    .padding(top = 20.dp)
                    .verticalScroll(scrollState),
                coffee = coffee,
                onRecipeBtnClick = { onRecipeBtnClick(coffeeId) },
                onEditBtnClick = { detailsScreenModel.onEditButton() },
                onAddDescriptionBtnClick = { detailsScreenModel.onAddDescription() },
                showEditDescriptionField = state.showEditDescriptionField,
                onSaveDescription = { desc ->
                    detailsScreenModel.saveDescription(desc)
                },
                onCancellationClick = {
                    detailsScreenModel.onCancelDescription()
                },
            )
        }
    }
}

@Composable
fun CoffeeDetailsScreenInfo(
    modifier: Modifier = Modifier,
    coffee: Coffee,
    showEditDescriptionField: Boolean = false,
    onEditBtnClick: () -> Unit = {},
    onRecipeBtnClick: () -> Unit = {},
    onAddDescriptionBtnClick: () -> Unit = {},
    onSaveDescription: (String) -> Unit = {},
    onCancellationClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
    ) {
        RoastingAndProcessingRow(
            roasting = coffee.roasting,
            processingMethod = coffee.processingMethod
        )
        Spacer(Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RegularAppText(
                modifier = Modifier.weight(0.6f),
                text = coffee.title,
                fontSize = 30.sp,
                lineHeight = 36.sp,
                fontFamily = getMontserratBold(),
                maxLines = 2
            )
            Box(
                Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        color = textSecondaryColor.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable(onClick = onEditBtnClick)
                    .size(60.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp),
                    painter = painterResource(Res.drawable.ic_edit_24),
                    contentDescription = null,
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        RegularAppText(
            text = coffee.tasteDescription,
            maxLines = Int.MAX_VALUE,
            lineHeight = 25.sp,
            fontSize = 16.sp,
            fontFamily = getMontserratRegular(),
            color = textSecondaryColor
        )
        Spacer(Modifier.height(20.dp))
        CoffeeBalance(
            modifier = Modifier.fillMaxWidth(),
            density = coffee.density,
            acidity = coffee.acidity
        )
        Spacer(Modifier.height(20.dp))
        if (showEditDescriptionField) {
            EditDescriptionView(
                onSaveBtnClick = { onSaveDescription(it) },
                onCancellationClick = onCancellationClick,
            )
        } else {
            DescriptionView(
                description = coffee.userDescription,
                onAddDescriptionBtnClick = onAddDescriptionBtnClick
            )
        }
        Spacer(Modifier.height(20.dp))
        RecipeButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onRecipeBtnClick
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
            shape = RoundedCornerShape(0),
            model = coffee.imagePath,
        )
        coffee.qGrade?.let {
            QGradeBox(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.TopEnd),
                qGrade = coffee.qGrade
            )
        }
        AppBackButton(
            modifier = Modifier.padding(20.dp),
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