package com.hfad.investsocialapp.screen.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.hfad.investsocialapp.data.Post
import com.hfad.investsocialapp.navigation.NavigationItem
import com.hfad.investsocialapp.screen.profile.ProfileViewModel
import kotlin.math.roundToInt


const val ANIMATION_DURATION = 500
const val MIN_DRAG_AMOUNT = 6

@Composable
fun PostCard(
    post: Post,
    viewModel: ProfileViewModel,
    modifier: Modifier = Modifier,
    progress: CircularProgressDrawable,
    navController: NavController
) {
//    val offsetX = remember { mutableStateOf(0f) }
//    val transitionState = remember {
//        MutableTransitionState(isRevealed).apply {
//            targetState = !isRevealed
//        }
//    }
//    val transition = updateTransition(transitionState, "cardTransition")
//
//    val offsetTransition by transition.animateFloat(
//        label = "cardOffsetTransition",
//        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
//        targetValueByState = { if (isRevealed) cardOffset - offsetX.value else -offsetX.value },
//
//        )
//    val cardElevation by transition.animateDp(
//        label = "cardElevation",
//        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
//        targetValueByState = { if (isRevealed) 40.dp else 2.dp }
//    )

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = 3.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            val imageModifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(percent = 6))
            Row(
                modifier = Modifier.padding(top = 8.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    rememberImagePainter(data = post.avatar) {
                        transformations(RoundedCornersTransformation(40f))
                        placeholder(progress)
                    },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )


                Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
                    Text(
                        text = post.name,
                    )
                    Text(
                        text = "Репутация: " + post.honor,
                        style = MaterialTheme.typography.caption
                    )
                }

            }


            Image(
                rememberImagePainter(data = post.image) {
                    placeholder(progress)
                },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )



            Row(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)) {
                for (category in post.categories) {
                    KeyWordView(category)

                }
            }
            Text(
                text = post.title,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = post.text,
                style = MaterialTheme.typography.body2
            )
            Row(modifier = Modifier.padding(top = 12.dp)) {
                Text(
                    text = "Понравилось ${post.likes}",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                        .wrapContentWidth(Alignment.Start)
                )
            }
            Row(
                modifier = Modifier.padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Не понравилось ${post.dislikes}",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                        .wrapContentWidth(Alignment.Start)
                )
                Button(
                    onClick =  {navController.navigate(NavigationItem.Comments.route){
                        launchSingleTop = true
                      }}, modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                        .wrapContentWidth(Alignment.End)
                ) {
                    Text(
                        text = "Комментарии ${post.comments}",
                        style = MaterialTheme.typography.caption,


                        )
                }

            }

        }
    }
}


@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DraggableCard(
    cardHeight: Dp,
    isRevealed: Boolean,
    cardOffset: Float,
    onExpand: () -> Unit,
    onCollapse: () -> Unit,
    post: Post,
    viewModel: ProfileViewModel,
    modifier: Modifier = Modifier,
    progress: CircularProgressDrawable,
    navController: NavController
) {
    val offsetX = remember { mutableStateOf(0f) }
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }
    val transition = updateTransition(transitionState, "cardTransition")

    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) cardOffset - offsetX.value else -offsetX.value },

        )
    val cardElevation by transition.animateDp(
        label = "cardElevation",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) 40.dp else 2.dp }
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(cardHeight)
            .offset { IntOffset((offsetX.value + offsetTransition).roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    val original = Offset(offsetX.value, 0f)
                    val summed = original + Offset(x = dragAmount, y = 0f)
                    val newValue = Offset(x = summed.x.coerceIn(0f, cardOffset), y = 0f)
                    if (newValue.x >= 10) {
                        onExpand()
                        return@detectHorizontalDragGestures
                    } else if (newValue.x <= 0) {
                        onCollapse()
                        return@detectHorizontalDragGestures
                    }
                    change.consumePositionChange()
                    offsetX.value = newValue.x
                }
            },
        shape = RoundedCornerShape(0.dp),
        elevation = cardElevation,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                val imageModifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(percent = 6))
                Row(
                    modifier = Modifier.padding(top = 8.dp, bottom = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        rememberImagePainter(data = post.avatar) {
                            transformations(RoundedCornersTransformation(40f))
                            placeholder(progress)
                        },
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                    )


                    Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
                        Text(
                            text = post.name,
                        )
                        Text(
                            text = "Репутация: " + post.honor,
                            style = MaterialTheme.typography.caption
                        )
                    }

                }


                Image(
                    rememberImagePainter(data = post.image) {
                        placeholder(progress)
                    },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )



                Row(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)) {
                    for (category in post.categories) {
                        KeyWordView(category)

                    }
                }
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = post.text,
                    style = MaterialTheme.typography.body2
                )
                Row(modifier = Modifier.padding(top = 12.dp)) {
                    Text(
                        text = "Понравилось ${post.likes}",
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                            .wrapContentWidth(Alignment.Start)
                    )
                }
                Row(
                    modifier = Modifier.padding(top = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Не понравилось ${post.dislikes}",
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                            .wrapContentWidth(Alignment.Start)
                    )
                    Button(
                        onClick = {navController.navigate(NavigationItem.Comments.route){
                            launchSingleTop = true
                        } }, modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                            .wrapContentWidth(Alignment.End)
                    ) {
                        Text(
                            text = "Комментарии ${post.comments}",
                            style = MaterialTheme.typography.caption,


                            )
                    }

                }

            }


        }
    )
}