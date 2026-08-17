package org.example.project.features.addCoffee.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.add_button
import coffee.shared.generated.resources.already_exist_dialog
import coffee.shared.generated.resources.ic_ai_24
import coffee.shared.generated.resources.ic_edit_24
import coffee.shared.generated.resources.load_button
import coffee.shared.generated.resources.new_coffee_title
import coffee.shared.generated.resources.tap_to_upload
import org.example.project.core.domain.impl.rememberImagePicker
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.AppDialog
import org.example.project.core.ui.components.HeaderAppText
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.getMontserratBold
import org.example.project.core.ui.theme.textPrimaryColorLight
import org.example.project.core.ui.theme.textSecondaryColor
import org.example.project.features.addCoffee.store.AddCoffeeActions
import org.example.project.features.addCoffee.store.AddCoffeeScreenUiState
import org.example.project.features.coffeeDetails.ui.composables.ButtonRow
import org.example.project.features.addCoffee.ui.vm.AddCoffeeScreenModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

sealed interface AlreadyExistDialogResult {
    data object Confirm: AlreadyExistDialogResult
    data object Dismiss: AlreadyExistDialogResult
}

@Composable
fun AddCoffeeScreen(
    modifier: Modifier = Modifier,
    screenModel: AddCoffeeScreenModel,
    onAddBtnClick: () -> Unit
) {
    val state by screenModel.state.collectAsStateWithLifecycle()

    val imagePicker = rememberImagePicker { bytes ->
        screenModel.loadImage(bytes)
    }

    LaunchedEffect(Unit) {
        screenModel.uiActions.collect { action ->
            when (action) {
                AddCoffeeActions.OpenGallery -> imagePicker.launchGallery()
                is AddCoffeeActions.AddCoffeeBtnClicked -> {
                    onAddBtnClick()
                }
            }
        }
    }

    if (state.showAlreadyExistDialog) {
        AppDialog(
            message = stringResource(Res.string.already_exist_dialog),
            onDismissClick = { screenModel.onDialogResult(AlreadyExistDialogResult.Dismiss) },
            onConfirmClick = { screenModel.onDialogResult(AlreadyExistDialogResult.Confirm) }
        )
    }

    AddCoffeeScreenContent(
        modifier = modifier,
        state = state,
        onPickImageClick = {
            screenModel.pickImage()
        },
        onLoadBtnClick = {
            screenModel.loadCoffeeInfo()
        },
        onAddBtnClick = {
            screenModel.addCoffee()
        }
    )
}

@Composable
fun AddCoffeeScreenContent(
    modifier: Modifier = Modifier,
    state: AddCoffeeScreenUiState,
    onPickImageClick: () -> Unit,
    onLoadBtnClick: () -> Unit,
    onAddBtnClick: () -> Unit,
) {
    val imageDirectory = state.imageDirectory

    Column(modifier = modifier) {
        HeaderAppText(
            text = stringResource(Res.string.new_coffee_title)
        )
        Spacer(Modifier.padding(top = 20.dp))
        CoffeeImageView(
            modifier = Modifier
                .aspectRatio(UiDefaults.IMAGE_ASPECT_RATIO)
                .fillMaxWidth(),
            imageDirectory = imageDirectory,
            onClick = { onPickImageClick() }
        )
        if (state.coffeeInfo != null) {
            CoffeeInfoContent(
                coffee = state.coffeeInfo,
                onAddBtnClick = { onAddBtnClick() },
                onEditBtnClick = {}
            )
        }
        if (state.isLoading) {
            CoffeeInfoLoading(
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        if (state.error != null) {
            Spacer(Modifier.height(100.dp))
            CoffeeError(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                message = state.error
            )
        }
        Spacer(Modifier.weight(1f))
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_ai_24),
                    contentDescription = null
                )
            },
            text = {
                RegularAppText(
                    modifier = Modifier.padding(vertical = 20.dp),
                    text = stringResource(Res.string.load_button),
                    color = textPrimaryColorLight
                )
            },
            isEnabled = imageDirectory != null,
            onClick = {
                onLoadBtnClick()
            }
        )
    }
}

@Composable
fun CoffeeInfoContent(
    modifier: Modifier = Modifier,
    coffee: Coffee,
    onAddBtnClick: () -> Unit,
    onEditBtnClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        RegularAppText(
            text = coffee.roasting,
            color = textSecondaryColor,
            fontSize = 18.sp,
        )
        Spacer(Modifier.padding(top = 10.dp))
        RegularAppText(
            text = coffee.title,
            fontSize = 28.sp,
            fontFamily = getMontserratBold()
        )
        Spacer(Modifier.padding(top = 20.dp))
        RegularAppText(
            modifier = Modifier
                .align(Alignment.End),
            text = coffee.tasteDescription,
            color = textSecondaryColor,
            fontSize = 18.sp,
        )
        Spacer(Modifier.padding(top = 30.dp))
        CoffeeBalance(
            density = coffee.density,
            acidity = coffee.acidity
        )
        Spacer(Modifier.weight(1f))

        ButtonRow(
            modifier = Modifier.fillMaxWidth(),
            firstBtn = {
                AppButton(
                    modifier = Modifier.weight(0.8f),
                    isEnabled = true,
                    text = {
                        RegularAppText(
                            modifier = Modifier
                                .padding(vertical = 20.dp),
                            text = stringResource(Res.string.add_button),
                            color = textPrimaryColorLight
                        )
                    },
                    icon = null,
                    onClick = {
                        onAddBtnClick()
                    }
                )
            },
            secondBtn = {
                AppButton(
                    modifier = Modifier
                        .weight(0.2f),
                    text = null,
                    icon = {
                        Icon(
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .size(24.dp),
                            painter = painterResource(Res.drawable.ic_edit_24),
                            contentDescription = null,
                        )
                    }
                ) {
                    onEditBtnClick()
                }
            }
        )
    }
}

@Composable
private fun ColumnScope.CoffeeImageView(
    modifier: Modifier = Modifier,
    imageDirectory: String?,
    onClick: () -> Unit,
) {
    if (imageDirectory != null) {
        CoffeeImage(
            modifier = modifier,
            model = imageDirectory
        )
    } else {
        RegularAppText(
            modifier = Modifier
                .align(Alignment.End),
            text = stringResource(Res.string.tap_to_upload),
            color = textSecondaryColor,
            fontSize = 16.sp
        )
        CoffeeImagePlaceholder(
            modifier = modifier,
            onClick = { onClick() }
        )
    }
}