package com.bhavya.foodorder.screens.profileScreen

import androidx.compose.runtime.Composable

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.bhavya.foodorder.R
import com.bhavya.foodorder.ViewModel.ProfileViewModel
import com.bhavya.foodorder.dataclass.Profile
import com.bhavya.getfitapp.components.InputFields


@Composable
fun profileEdit(

    navController: NavController,
    onValueChange: (String)-> Unit={},
    profileViewModel: ProfileViewModel
){
    val value= remember {
        mutableStateOf("")
    }


    val mobileNum= remember {
        mutableStateOf("")
    }
    val Nickname= remember {
        mutableStateOf("")
    }
    val Email= remember {
        mutableStateOf("")
    }
    val scrollState=rememberScrollState()
    var validState= remember(value.value) {
        value.value.trim().isNotEmpty()

    }
    val validState_Email= remember(Email.value) {
        value.value.trim().isNotEmpty()

    }
    val validState_Nick= remember(Nickname.value) {
        value.value.trim().isNotEmpty()

    }
    val validState_mobile= remember(mobileNum.value) {
        value.value.trim().isNotEmpty()

    }
    val Address= remember {
        mutableStateOf("")
    }

    val keyboardController= LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri // Save the selected image
    }
    Surface(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        color = Color.LightGray
    ) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            , verticalArrangement = Arrangement.Top
            , horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(50.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp,0.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(imageVector = Icons.Default.PlayArrow, contentDescription = "", modifier = Modifier.clickable {
                    navController.popBackStack()                }
                    .rotate(180f),
                    colorFilter = ColorFilter.tint(Color.Black))
                Spacer(modifier = Modifier.width(10.dp))
                Text(text="Back", color = Color.Black, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start, modifier =Modifier.fillMaxWidth(), fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(text="Fill Your Profile", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(40.dp))

            Surface(modifier = Modifier.fillMaxWidth()
                .height(150.dp),
                color = Color.LightGray) {
                Column(modifier = Modifier.width(300.dp).fillMaxHeight().
                padding(0.dp,20.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//Image(painter = painterResource(id=R.drawable.def), contentDescription = "", modifier = Modifier.size(200.dp).clip(CircleShape))
//                    Text(text="Forgot password?", modifier = Modifier.fillMaxWidth().clickable {  }, textAlign = TextAlign.End,fontSize = 18.sp)
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .clickable { launcher.launch("image/*") } // Launch gallery on click
                    ) {
                        if (selectedImageUri != null) {
                            // Show the selected image
                            Image(
                                painter = rememberAsyncImagePainter(selectedImageUri),
                                contentDescription = "Selected Profile Picture",
                                modifier = Modifier
                                    .fillMaxSize()


                            )
                        } else {
                            // Default Image if no image selected
                            Image(
                                painter = painterResource(id = R.drawable.img),
                                contentDescription = "Default Profile Picture",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }

                    }

                }

            }
            Text(text="click to edit your profile picture",fontSize = 15.sp,  color = Color.Black)
            Column(modifier = Modifier.fillMaxSize().padding(20.dp,10.dp)) {
                Spacer(modifier = Modifier.height(13.dp))
                Text(text="Full Name",fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Spacer(modifier = Modifier.height(13.dp))
                InputFields(
                    valueState =value,
                    labelId =  "Enter bill" ,
                    enabled =true ,
                    modifier = Modifier.
                    padding(20.dp)



                    ,
                    isSingleLine =true ,
                    onAction = KeyboardActions{
                        if(!validState)return@KeyboardActions
                        onValueChange(value.value.trim())
                        keyboardController?.hide()

                    })

                Spacer(modifier = Modifier.height(13.dp))
                Text(text="Email",fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Spacer(modifier = Modifier.height(13.dp))

                InputFields(
                    valueState =Email,
                    labelId =  "" ,
                    enabled =true ,
                    modifier = Modifier.
                    padding(20.dp)
                    ,
                    isSingleLine =true ,
                    onAction = KeyboardActions{
                        if(!validState)return@KeyboardActions
                        onValueChange(Email.value.trim())
                        keyboardController?.hide()

                    })
                Spacer(modifier = Modifier.height(13.dp))

                Text(text="Mobile Number",fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Spacer(modifier = Modifier.height(13.dp))
                InputFields(
                    valueState =mobileNum,
                    labelId =  "Enter bill" ,
                    enabled =true ,
                    modifier = Modifier.
                    padding(20.dp)



                    ,
                    isSingleLine =true ,
                    onAction = KeyboardActions{
                        if(!validState)return@KeyboardActions
                        onValueChange(mobileNum.value.trim())
                        keyboardController?.hide()

                    })
                Spacer(modifier = Modifier.height(13.dp))
                Text(text="Address",fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Spacer(modifier = Modifier.height(13.dp))

                InputFields(
                    valueState =Address,
                    labelId =  "" ,
                    enabled =true ,
                    modifier = Modifier.
                    padding(20.dp)
                    ,
                    isSingleLine =true ,
                    onAction = KeyboardActions{
                        if(!validState)return@KeyboardActions
                        onValueChange(Email.value.trim())
                        keyboardController?.hide()

                    })
                Spacer(modifier = Modifier.height(13.dp))
            }
            Button(onClick = {
     profileViewModel.profile.value= Profile(
         name = value.value,
         Adrees = Address.value,
         email = Email.value,
         MobileNO = mobileNum.value
     )
                val name ="Delivery"
                if (name==profileViewModel.name.toString())navController.navigate(name)
                else navController.navigate("profile")
            }, modifier = Modifier.width(150.dp)
                .height(50.dp)

                .clip(RoundedCornerShape(15.dp)),
                shape = CircleShape,
                colors =ButtonDefaults.buttonColors(Color.Red),
            ) {
                Text(text="Save", fontSize = 20.sp,color=Color.White)
            }

        }
    }
}

