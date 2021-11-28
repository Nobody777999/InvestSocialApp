package com.hfad.investsocialapp.screen.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.hfad.investsocialapp.data.Comment

@Composable
fun CommentsView(viewModel: CommentsViewModel, navController: NavController) {
    val comments = viewModel.comments

    LazyColumn {

        comments.forEach {
            item {
                CommentCard(comment = it)
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                
            }

        }

        item {
            Button(onClick = { /*TODO*/ }) {

                Text(text = "Добавить", Modifier.fillMaxWidth(), textAlign = TextAlign.End)
            }
            
            Spacer(modifier = Modifier.padding(36.dp))

        }
    }


}


@Composable
fun CommentCard(modifier: Modifier = Modifier, comment: Comment) {
    val progress = CircularProgressDrawable(LocalContext.current)
    progress.start()
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = 5.dp,
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
                    rememberImagePainter(data = comment.photo) {
                        transformations(RoundedCornersTransformation(40f))
                        placeholder(progress)
                    },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )


            }




            Text(
                text = comment.text,
                style = MaterialTheme.typography.body2
            )


        }
    }

}