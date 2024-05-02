package co.just.simple.myapplication.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import co.just.simple.myapplication.R
import co.just.simple.myapplication.components.SwipeCard
import co.just.simple.myapplication.model.NavigationItem
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsScreen (
    navHostController: NavHostController
) {
    val list = arrayListOf("1","2","3","4","5")
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
    SwipeCard(
        onSwipeRight = {
            onSwipeLeft()
        }
    ) {
        ConstraintLayout (modifier = Modifier.fillMaxSize()) {
            val (image, text, bookmark) = createRefs()

            AsyncImage(
                model = "https://fastly.picsum.photos/id/16/2500/1667.jpg?hmac=uAkZwYc5phCRNFTrV_prJ_0rP0EdwJaZ4ctje2bY7aE",
                null ,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
                .height(200.dp))
            Text("Hello $value \n dfdlasf sadfs; afsd fjsdf; sdjf;sd ajffsa; dfj;sd jsd;f sf ds;fsdf sdf sdf f; sajdf" , style = MaterialTheme.typography.bodyMedium , modifier = Modifier.constrainAs(text){
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                top.linkTo(image.bottom, margin = 24.dp)
                width = Dimension.fillToConstraints
            })
            Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null, modifier = Modifier.height(56.dp).width(24.dp).constrainAs(bookmark){
                top.linkTo(image.top, margin = 14.dp)
                end.linkTo(image.end, margin = 14.dp)
            }.background(color = Color.Gray))
        }
    }
}