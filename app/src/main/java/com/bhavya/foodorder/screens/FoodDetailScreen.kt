import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun FoodDetailScreen(
    name: String,
    price: Double,
    imageUrl: String = "https://images.unsplash.com/photo-1600891964599-f61ba0e24092", // optional default
    onAddToCart: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(top = 50.dp)
                .size(500.dp)
                .clip(RoundedCornerShape(1000.dp))
        )

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = name,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "₦${price.toInt()}",
            fontSize = 20.sp,
            color = Color(0xFF4CAF50),
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = onAddToCart, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        , modifier = Modifier.width(200.dp).height(70.dp)) {
            Text("Add to Cart", fontSize = 19.sp)
        }
    }
}
