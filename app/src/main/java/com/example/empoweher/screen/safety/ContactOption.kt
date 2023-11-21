package com.example.empoweher.screen.safety

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empoweher.R
import com.example.empoweher.model.Screen

@Composable
fun ContactOption(navigateToNextScreen: (route: String)->Unit){
    
    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.cream)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Image(
            painter = painterResource(id = R.drawable.add_contact_option),
            contentDescription = "addContact",
            modifier=Modifier
                .padding(bottom=20.dp)
                .clip(RoundedCornerShape(30.dp))
                .size(250.dp)
                .clickable {
                    navigateToNextScreen(Screen.AddContact.route)
                },
            contentScale = ContentScale.FillBounds

        )
        Text(
            text = "Add Contact",
            fontSize = 35.sp,
            fontFamily = FontFamily(Font(R.font.font1)),
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.purple_200),
            modifier = Modifier.padding(bottom=20.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.view_contact),
            contentDescription = "viewContacts",
            modifier=Modifier
                .padding(bottom=20.dp)
                .clip(RoundedCornerShape(30.dp))
                .size(250.dp)
                .clickable {
                    navigateToNextScreen(Screen.ContactsList.route)
                },
            contentScale = ContentScale.FillBounds,

        )
        Text(
            text = "View Contacts",
            fontSize = 35.sp,
            fontFamily = FontFamily(Font(R.font.font1)),
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.purple_200),
            modifier = Modifier.padding(bottom=20.dp)
        )

        
    }

}