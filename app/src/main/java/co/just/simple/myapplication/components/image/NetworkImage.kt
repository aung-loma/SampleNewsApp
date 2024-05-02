package co.just.simple.myapplication.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.just.simple.myapplication.R
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size
import kotlinx.coroutines.Dispatchers


@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentScale: ContentScale = ContentScale.FillBounds,
    contentDescription: String = "NetworkImage",
    errorPlaceHolder: Int = R.drawable.ic_broken_image,
    imagePadding: PaddingValues = PaddingValues(0.dp),
    placeHolderPadding: PaddingValues = PaddingValues(0.dp),
    loaderPadding: PaddingValues = PaddingValues(0.dp),
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .setHeader("Cache-Control", "max-age=31536000")
                .size(Size.ORIGINAL)
                .dispatcher(Dispatchers.IO)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .memoryCacheKey(imageUrl)
                .diskCacheKey(imageUrl)
                .crossfade(enable = true)
                .crossfade(durationMillis = 1500)
                .build(),
            contentScale = contentScale,
            contentDescription = contentDescription,
            modifier = modifier
                .padding(imagePadding),
            loading = {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.dp,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(loaderPadding)
                        .wrapContentSize(align = Alignment.Center, unbounded = false)
                )
            },
            error = {
                Image(
                    painter = painterResource(id = errorPlaceHolder),
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .padding(placeHolderPadding)
                        .wrapContentSize(align = Alignment.Center, unbounded = false)
                )
            }
        )
    }
}