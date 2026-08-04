package org.example.project.features.addCoffee.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coffee.shared.generated.resources.Res
import coffee.shared.generated.resources.ic_ai_24
import coffee.shared.generated.resources.ic_edit_24
import io.github.ismoy.imagepickerkmp.domain.config.GalleryConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.config.ImagePickerKMPConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.ui.rememberImagePickerKMP
import org.example.project.core.domain.model.Coffee
import org.example.project.core.ui.components.AppButton
import org.example.project.core.ui.components.HeaderAppText
import org.example.project.core.ui.components.RegularAppText
import org.example.project.core.ui.theme.UiDefaults
import org.example.project.core.ui.theme.blueGrayText
import org.example.project.core.ui.theme.getComfortaBold
import org.example.project.core.ui.theme.gray
import org.example.project.core.ui.theme.white
import org.example.project.features.coffeeDetails.ui.composables.ButtonRow
import org.example.project.features.addCoffee.ui.states.AddCoffeeScreenIntents
import org.example.project.features.addCoffee.ui.states.AddCoffeeScreenUiEvents
import org.example.project.features.addCoffee.ui.states.AddCoffeeScreenUiState
import org.example.project.features.addCoffee.ui.states.AddCoffeeScreenUiStatus
import org.example.project.features.addCoffee.ui.vm.AddCoffeeScreenModel
import org.jetbrains.compose.resources.painterResource

@Composable
fun AddCoffeeScreen(
    modifier: Modifier = Modifier,
    screenModel: AddCoffeeScreenModel,
    onBack: () -> Unit,
) {
    val state = screenModel.state.collectAsStateWithLifecycle()

    val photoPicker = rememberImagePickerKMP(
        config = ImagePickerKMPConfig(
            galleryConfig = GalleryConfig(
                selectionLimit = 1
            ),
        ),
    )
    LaunchedEffect(photoPicker.result) {
        val result = photoPicker.result
        screenModel.onIntent(AddCoffeeScreenIntents.ImagePicked(result))
    }

    LaunchedEffect(Unit) {
        screenModel.uiEvents.collect { event ->
            when (event) {
                AddCoffeeScreenUiEvents.PickPhoto -> {
                    photoPicker.launchGallery()
                }

                is AddCoffeeScreenUiEvents.ShowAiError -> {
                    println(event.message)
                }

                AddCoffeeScreenUiEvents.NavigateToCoffeeScreen -> {
                    onBack()
                }
            }
        }
    }

    AddCoffeeScreenContent(
        modifier = modifier,
        state = state.value,
        onPickImageClick = {
            screenModel.onIntent(
                AddCoffeeScreenIntents.PickImage
            )
        },
        onLoadBtnClick = {
            screenModel.onIntent(
                AddCoffeeScreenIntents.LoadImage
            )
        },
        onAddBtnClick = {
            screenModel.onIntent(AddCoffeeScreenIntents.AddCoffee)
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
    val imagePath = state.imageDirectory

    Column(modifier = modifier) {
        HeaderAppText(
            text = "Новый кофе"
        )
        Spacer(Modifier.padding(top = 20.dp))
        if (imagePath != null) {
            CoffeeImage(
                modifier = Modifier
                    .aspectRatio(UiDefaults.IMAGE_ASPECT_RATIO)
                    .fillMaxWidth(),
                model = imagePath
            )
        } else {
            RegularAppText(
                modifier = Modifier
                    .align(Alignment.End),
                text = "Тыкни, чтобы загрузить",
                color = blueGrayText,
                fontSize = 16.sp
            )
            CoffeeImagePlaceholder(
                modifier = Modifier
                    .aspectRatio(UiDefaults.IMAGE_ASPECT_RATIO)
                    .fillMaxWidth()
            ) {
                onPickImageClick()
            }
        }
        when (val status = state.status) {
            is AddCoffeeScreenUiStatus.Content -> {
                CoffeeInfoContent(
                    coffee = status.coffee,
                    onAddBtnClick = { onAddBtnClick() },
                    onEditBtnClick = {}
                )
            }

            is AddCoffeeScreenUiStatus.Error -> Unit
            AddCoffeeScreenUiStatus.Idle -> Unit
            AddCoffeeScreenUiStatus.Loading -> {
                CoffeeInfoLoading(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            AddCoffeeScreenUiStatus.PhotoLoaded -> {
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
                            text = "Загрузить",
                            color = white
                        )
                    },
                    onClick = {
                        onLoadBtnClick()
                    }
                )
            }
        }
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
            color = blueGrayText,
            fontSize = 18.sp,
        )
        Spacer(Modifier.padding(top = 10.dp))
        RegularAppText(
            text = coffee.title,
            fontSize = 28.sp,
            fontFamily = getComfortaBold()
        )
        Spacer(Modifier.padding(top = 20.dp))
        RegularAppText(
            modifier = Modifier
                .align(Alignment.End),
            text = coffee.tasteDescription,
            color = gray,
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
                            text = "Добавить",
                            color = white
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