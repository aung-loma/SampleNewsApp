package co.just.simple.myapplication.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import co.just.simple.myapplication.R
import co.just.simple.myapplication.components.button.FillButton
import co.just.simple.myapplication.components.editText.SimpleOutlinedTextFieldSample
import co.just.simple.myapplication.components.toolbar.ToolBarWithTitleAndBack
import co.just.simple.myapplication.ui.theme.IconResource


@Composable
fun ProfileDetailScreen (
    navHostController: NavHostController
) {
    var showContent by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ToolBarWithTitleAndBack(
            title = "@bobo",
            onBackClick = { navHostController.popBackStack() },
            onEditClick = {

            }
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            ConstraintLayout (modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)) {
                val (profile,name,joinDate,followStatus) = createRefs()
                val horizontalGuideline = createGuidelineFromTop(0.5f)
                Image(
                    painter = painterResource(id = IconResource.Account),
                    contentDescription = "Account", modifier = Modifier
                        .constrainAs(profile) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                        .size(56.dp)
                        .clip(CircleShape))

                Text(text = "Aung Bo Phyoe", style = MaterialTheme.typography.titleSmall, modifier = Modifier.constrainAs(name) {
                    start.linkTo(profile.end, margin = 8.dp)
                    bottom.linkTo(horizontalGuideline, margin = 4.dp)
                })

                Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.constrainAs(joinDate){
                    start.linkTo(name.start)
                    top.linkTo(horizontalGuideline)
                }) {
                    Image( painterResource(id = IconResource.Calendar), contentDescription = null, modifier = Modifier.size(18.dp).padding(end = 4.dp))
                    Text(text = "Joined July 2023", style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
                }
                Row (modifier = Modifier.constrainAs(followStatus){
                    top.linkTo(profile.top)
                    bottom.linkTo(profile.bottom)
                    end.linkTo(parent.end)
                }, verticalAlignment = Alignment.Top) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(text = "Followers", style = MaterialTheme.typography.labelLarge)
                        Text(text = "1000", style = MaterialTheme.typography.titleSmall)
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(text = "Following", style = MaterialTheme.typography.labelLarge)
                        Text(text = "20", style = MaterialTheme.typography.titleSmall)
                    }
                }

            }
            FillButton (
                name = "Become a Publisher",
                onClick = {
                    navHostController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}