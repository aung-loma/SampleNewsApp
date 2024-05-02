package co.just.simple.myapplication.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import co.just.simple.myapplication.R
import co.just.simple.myapplication.components.image.NetworkImage
import co.just.simple.myapplication.model.NavigationItem
import co.just.simple.myapplication.ui.theme.IconResource
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsScreen (
    navHostController: NavHostController
) {
    val list = arrayListOf("1","2","3","4","5", "6", "7", "8", "9", "10")
    val pagerState = rememberPagerState( pageCount = { list.size } )
    Box (modifier = Modifier.fillMaxSize()) {
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ){
            ListItem(list[it], onSwipeLeft = {navHostController.navigate(NavigationItem.ProfileDetail.route)})
        }
    }
}

@Composable
fun ListItem(value : String,  onSwipeLeft: () -> Unit = {}) {
    val archive = SwipeAction(
        icon = {},
        background = Color.Black.copy(alpha = 0.4f),
        onSwipe = {
            onSwipeLeft()
        },
        isUndo = true
    )
    SwipeableActionsBox(
        startActions = listOf(archive)
    ) {
        ConstraintLayout (modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {
            val (image, text, bookmark) = createRefs()
            NetworkImage(imageUrl = "https://picsum.photos/200/300", modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
                .height(200.dp))
            Text( text = stringResource(id = R.string.more_text) , style = MaterialTheme.typography.bodyMedium , modifier = Modifier.constrainAs(text){
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                top.linkTo(image.bottom, margin = 24.dp)
                width = Dimension.fillToConstraints
            })
            Image(painter = painterResource(id = IconResource.Heart), contentDescription = null, modifier = Modifier
                .size(24.dp)
                .constrainAs(bookmark) {
                    top.linkTo(image.top, margin = 14.dp)
                    end.linkTo(image.end, margin = 14.dp)
                }
                .clickable {
                    onSwipeLeft()
                })
        }
    }
}