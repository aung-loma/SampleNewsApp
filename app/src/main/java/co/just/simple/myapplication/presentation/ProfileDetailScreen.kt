package co.just.simple.myapplication.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import co.just.simple.myapplication.R
import co.just.simple.myapplication.components.button.FillButton
import co.just.simple.myapplication.components.image.NetworkImage
import co.just.simple.myapplication.components.toolbar.ToolBarWithTitleAndBack
import co.just.simple.myapplication.model.ProfileTabItems
import co.just.simple.myapplication.model.UserPost
import co.just.simple.myapplication.ui.theme.IconResource
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileDetailScreen (
    navHostController: NavHostController
) {
    val pagerState = rememberPagerState(pageCount = {ProfileTabItems.entries.size})
    val selectedIndex = remember{ derivedStateOf { pagerState.currentPage }}
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ToolBarWithTitleAndBack(
            title = "@bobo",
            onBackClick = { navHostController.popBackStack() },
            onEditClick = {

            }
        )
        LazyColumn (
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                ConstraintLayout (modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
                    val (profile,name,joinDate,followStatus) = createRefs()
                    Image(
                        painter = painterResource(id = IconResource.Account),
                        contentDescription = "Account", modifier = Modifier
                            .constrainAs(profile) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }
                            .size(56.dp)
                            .clip(CircleShape))

                    Column (
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.constrainAs(name) {
                            top.linkTo(profile.top)
                            bottom.linkTo(profile.bottom)
                            start.linkTo(profile.end, margin = 4.dp)
                            end.linkTo(followStatus.start)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                    ) {
                        Text(text = "Aung Bo Phyoe", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 4.dp))
                        Text(text = "Joined July 2023", style = MaterialTheme.typography.labelSmall)
                    }

                    Row (modifier = Modifier.constrainAs(followStatus){
                        top.linkTo(profile.top)
                        bottom.linkTo(profile.bottom)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    }, verticalAlignment = Alignment.CenterVertically) {
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Followers", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(bottom = 4.dp))
                            Text(text = "1000", style = MaterialTheme.typography.titleSmall)
                        }
                        Spacer(modifier = Modifier.width(24.dp))
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Following", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(bottom = 4.dp))
                            Text(text = "20", style = MaterialTheme.typography.titleSmall)
                        }
                    }

                }
                Text(text = stringResource(id = R.string.long_text), style = MaterialTheme.typography.bodyMedium , modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))

                ConstraintLayout (modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)) {
                    val (followBtn,actionStatus) = createRefs()
                    FillButton (
                        name = "Follow",
                        onClick = {
                            /**/
                        },
                        modifier = Modifier
                            .constrainAs(followBtn) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                            }
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .width(120.dp)
                            .height(28.dp)
                    )

                    Row(modifier = Modifier.constrainAs(actionStatus){
                        end.linkTo(parent.end)
                        top.linkTo(followBtn.top)
                        bottom.linkTo(followBtn.bottom)
                        height = Dimension.fillToConstraints
                    }, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(id = IconResource.Heart), contentDescription = "Like", modifier = Modifier
                            .size(24.dp)
                            .padding(end = 4.dp))
                        Text(text = "100", style = MaterialTheme.typography.titleSmall, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.width(12.dp))
                        Image(painter = painterResource(id = IconResource.Comment), contentDescription = "Comment", modifier = Modifier
                            .size(24.dp)
                            .padding(end = 4.dp))
                        Text(text = "40", style = MaterialTheme.typography.titleSmall, textAlign = TextAlign.Center)
                    }
                }

                FillButton (
                    name = "Become a Publisher",
                    onClick = {
                        /**/
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            stickyHeader {
                Box (modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()) {
                    TabRow(modifier = Modifier.fillMaxWidth(),
                        selectedTabIndex = selectedIndex.value,
                        divider = {}) {
                        ProfileTabItems.entries.forEachIndexed { index, tab ->
                            ProfileCustomTabs(
                                selected = index == selectedIndex.value,
                                text = tab.title,
                                count = tab.count,
                                onClick =  {
                                    scope.launch {
                                        pagerState.animateScrollToPage(tab.ordinal)
                                    }
                                }
                            )
                        }
                    }
                }
            }
            item {
                HorizontalPager(
                    state = pagerState
                ) {pageIndex ->
                    when (pageIndex) {
                        ProfileTabItems.PUBLISHED.ordinal -> PostList()

                        ProfileTabItems.PENDING.ordinal ->  EmptyScreen()

                        ProfileTabItems.DECLINED.ordinal ->  PostList()

                        ProfileTabItems.DRAFT.ordinal ->  EmptyScreen()
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun EmptyScreen() {
    Box (modifier = Modifier
        .fillMaxWidth()
        .height(200.dp), contentAlignment = Alignment.Center) {
        Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Empty Post!", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
            Image(painter = painterResource(id = R.drawable.ic_broken_image), contentDescription = "empty", modifier = Modifier.size(56.dp))
        }
    }
}

@Composable
fun PostList(){
    val postList = listOf(
        UserPost(1,"title 1","https://picsum.photos/200/300"),
        UserPost(2,"title 2","https://picsum.photos/200/300"),
        UserPost(3,"title 3","https://picsum.photos/200/300"),
        UserPost(4,"title 4","https://picsum.photos/200/300"),
        UserPost(5,"title 5","https://picsum.photos/200/300"),
        UserPost(6,"title 6","https://picsum.photos/200/300"),
        UserPost(7,"title 7","https://picsum.photos/200/300"),
        UserPost(8,"title 8","https://picsum.photos/200/300"),)
    Column {
        postList.forEachIndexed { index, userPost ->
            PostItem(item = userPost)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileTabs() {
    val pagerState = rememberPagerState(pageCount = {ProfileTabItems.entries.size})
    val selectedIndex = remember{ derivedStateOf { pagerState.currentPage }}
    val scope = rememberCoroutineScope()
    Column (modifier = Modifier.fillMaxWidth()) {
        Box (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
            TabRow(modifier = Modifier.fillMaxWidth(),
                selectedTabIndex = selectedIndex.value,
                divider = {}) {
                ProfileTabItems.entries.forEachIndexed { index, tab ->
                    ProfileCustomTabs(
                        selected = index == selectedIndex.value,
                        text = tab.title,
                        count = tab.count,
                        onClick =  {
                            scope.launch {
                                pagerState.animateScrollToPage(tab.ordinal)
                            }
                        }
                    )
                }
            }
        }
        HorizontalPager(
            state = pagerState
        ) {pageIndex ->
            when (pageIndex) {
                ProfileTabItems.PUBLISHED.ordinal -> PostList()

                ProfileTabItems.PENDING.ordinal ->  EmptyScreen()

                ProfileTabItems.DECLINED.ordinal ->  PostList()

                ProfileTabItems.DRAFT.ordinal ->  EmptyScreen()
            }
        }
    }
}

@Composable
fun ProfileCustomTabs(
    selected: Boolean,
    text: String,
    count : Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(56.dp)
            .fillMaxSize()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(
                color = if (selected) MaterialTheme.colorScheme.primary else Color.Gray,
                lineHeight = 14.sp,
                textAlign = TextAlign.Center
            ),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$count",
            style = MaterialTheme.typography.labelSmall.copy(
                color = if (selected) MaterialTheme.colorScheme.primary else Color.Gray,
                lineHeight = 14.sp,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            ),
        )
    }
}


@Composable
fun PostItem(item : UserPost) {
    Column (modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.5f),
                        Color.Transparent,
                    )
                )
            )
        )
        NetworkImage(imageUrl = item.photoUrl, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp))
        Text(text = "${item.id}"+stringResource(id = R.string.long_text), style = MaterialTheme.typography.bodyMedium , modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp))
    }
}