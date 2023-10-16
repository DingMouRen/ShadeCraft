package com.dingmouren.shadecraft.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.widgets.ShadeCard
import com.dingmouren.shadecraft.route.LocalNavController
import com.dingmouren.shadecraft.route.RoutePath
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun MainPage(){
    val samplesDesc = "New neumorphic style, showcasing control usage. Extraordinary visual impact and smooth operational experience. Embark on a new era of controls!"
    val widgetsDesc = "Neumorphic UI, diverse components, seamless experience. Efficient, stylish, stunning interfaces!"
    val navController = LocalNavController.current
    TitleSurface(title = "ShadeCraft") {
        Box(modifier = Modifier.fillMaxSize()){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(50.dp)
                    .wrapContentHeight(),
                horizontalAlignment  = Alignment.CenterHorizontally,
            ) {

                ShadeCard(
                    onClick = { navController.navigate(RoutePath.SamplesPage)},
                    cornerRadius = 20.dp,
                    offset = 20f,
                    blurRadius = 20f
                ) {
                    ImageWithTextLayout(
                        "Samples",
                        samplesDesc
                    )
                }

                Spacer(modifier = Modifier.height(90.dp))

                ShadeCard(onClick = { navController.navigate(RoutePath.WidgetsPage) },
                    cornerRadius = 20.dp,
                    offset = 20f,
                    blurRadius = 20f
                ) {
                    ImageWithTextLayout(
                        "Widgets",
                        widgetsDesc
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

//            ShadeCard(onClick = { navController.navigate(RoutePath.TestPage) }) {
//                ImageWithTextLayout(
//                    painterResource(id = R.drawable.widgets),
//                    "Test",
//                    widgetsDesc
//                )
//            }

            }
        }
        
    }
}

@Composable
fun ImageWithTextLayout( title: String, description: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)) {

        Column() {
            Text(text = title, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description, style = TextStyle(fontSize = 14.sp))
        }
    }
}



@Composable
fun MainList() {
    val navController = LocalNavController.current
    val mainList = listOf<String>(
        RoutePath.SamplesPage,
        RoutePath.WidgetsPage
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(
            modifier = Modifier.wrapContentSize()
        ){
            items(mainList){item ->
                Button(
                    onClick = { navController.navigate(item) }) {
                    Text(text = item)
                }
            }
        }
    }

}

