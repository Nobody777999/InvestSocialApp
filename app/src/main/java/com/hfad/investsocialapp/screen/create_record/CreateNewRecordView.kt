package com.hfad.investsocialapp.screen.create_record

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.hfad.investsocialapp.R
import com.hfad.investsocialapp.navigation.NavigationItem
import java.time.LocalDate
import java.util.*

@ExperimentalComposeUiApi
@Composable
// функция создания новой записи на стену
fun CreateNewRecordView(
    navController: NavController,
    createNewRecordViewModel: CreateNewRecordViewModel,
    fabIcon: MutableState<ImageVector>,
    fabAction: MutableState<() -> Unit>
) {
    val createRecord = createNewRecordViewModel.createRecord.observeAsState()
    val context = LocalContext.current
    val showDialog = remember {
        mutableStateOf(false)
    }

    fabIcon.value = Icons.Default.Create


    val imageUri = remember {
        mutableStateOf<Uri?>(null)
    }

    if (showDialog.value) {
        ShowDialog2(show = showDialog)
    }


    val title = remember {
        mutableStateOf("")
    }
    val text = remember {
        mutableStateOf("")
    }
    val categories = createNewRecordViewModel.categories

    fabAction.value = {
        if (title.value.isNotEmpty() && text.value.isNotEmpty()) {
            navController.popBackStack()
            createNewRecordViewModel.createRecord.value = true
        } else {
            showDialog.value = true
        }
    }


    Column(
        modifier = Modifier
            .padding(top = 12.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ) {

        Text(
            "Создать новый пост",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title.value, onValueChange = { title.value = it },
            label = {
                Text(text = "Заголовок", fontSize = 12.sp, textAlign = TextAlign.Center)
            },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text.value, onValueChange = { text.value = it },
            label = {
                Text(text = "Основной текст", fontSize = 12.sp, textAlign = TextAlign.Center)
            },
        )



        Spacer(modifier = Modifier.padding(8.dp))

        RequestContentPermission(imageUri)
    }

    DisposableEffect(key1 = fabIcon.value) {

        onDispose {
            fabIcon.value = Icons.Default.Add
            fabAction.value = {
                navController.navigate(NavigationItem.CreateRecord.route) {
                    launchSingleTop = true
                }
            }
        }
    }

    LaunchedEffect(key1 = createRecord.value) {
        if (createRecord.value!! && imageUri.value != null) {
            createNewRecordViewModel.createNewRecord(imageUri.value!!, context)
            imageUri.value = null
            createNewRecordViewModel.createRecord.value = false
        }
    }


}
// Прикрепить изображение при создании записи
@Composable
fun RequestContentPermission(imageUri: MutableState<Uri?>) {

    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri.value = uri
    }
    Column() {
        Button(onClick = {
            launcher.launch("image/*")
        }) {
            Text(text = "Добавить изображение")
        }

        Spacer(modifier = Modifier.height(4.dp))

        imageUri.value?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)

            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
            }
        }

    }
}
// алерт, сообщение об ошибке ныне не используется
@Composable
fun ShowDialog(show: MutableState<Boolean>) {
    val page = remember {
        mutableStateOf(1)
    }
    AlertDialog(onDismissRequest = { show.value = false },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(modifier = Modifier, onClick = { show.value = false }) {
                    Text(text = "Ок")
                }
                Spacer(modifier = Modifier.weight(1f))

                if (page.value != 1) {
                    Button(modifier = Modifier, onClick = {
                        if (page.value == 3){
                            page.value = 2
                        } else if (page.value == 2){
                            page.value = 1
                        }
                    }) {
                        Text(text = "Назад")
                    }
                }
                if (page.value != 3) {
                    Spacer(modifier = Modifier.padding(4.dp))

                    Button(modifier = Modifier, onClick = {
                        if (page.value == 1){
                            page.value = 2
                        } else if (page.value == 2){
                            page.value = 3
                        }
                    }) {
                        Text(text = "Дальше")
                    }
                }

            }

        },
        title = { Text(text = "Обучение") },
        text = {
            LazyColumn() {
                item {
                    when (page.value) {
                        1 -> {
                            Text(
                                text = stringResource(R.string.dialog)
                            )
                        }
                        2 -> {
                            Text(
                                text = stringResource(R.string.dialog2)
                            )
                            Spacer(modifier = Modifier.padding(bottom = 10.dp))
                        }
                        3 -> {
                            Text(
                                text = stringResource(R.string.dialog3)
                            )
                            Spacer(modifier = Modifier.padding(bottom = 10.dp))
                        }
                    }
                }




//                item {

//                }
//                item {

//                }
            }
        })

}
@Composable
fun ShowDialog2(show: MutableState<Boolean>) {
    AlertDialog(onDismissRequest = { show.value = false },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(modifier = Modifier.fillMaxWidth(), onClick = { show.value = false }) {
                    Text(text = "Ок")
                }
            }

        },
        title = { Text(text = "Ошибка") },
        text = { Text(text = "Заполните все поля") }
    )


}
