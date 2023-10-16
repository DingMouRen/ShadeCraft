package com.dingmouren.shadecraft.ui.page.widget

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.Convexity
import com.dingmouren.shadecraft.core.widgets.ShadeSearchBar
import com.dingmouren.shadecraft.ext.toast
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeSearchBarPage(){
    TitleSurface(title = "ShadeSearchBar") {
        Box(modifier = Modifier.fillMaxSize()){
            Column(Modifier.padding(horizontal = 20.dp).align(Alignment.Center)) {
                Spacer(modifier = Modifier.height(30.dp))
                ShadeSearchBar(
                    value = "",
                    onValueChange = {
                        Log.e("ShadeSearchBarPage","value:$it")
                    },
                    onSearchClick = {
                        App.context().toast("搜索:$it")
                    },
                    onRightIconClick = {
                        App.context().toast("右侧图标点击")
                    },
                    placeholderText = "Search Google or type a URL",
                    leftIcon = R.drawable.google,
                    rightIcon = R.drawable.google_audio
                )
                Spacer(modifier = Modifier.height(30.dp))
                ShadeSearchBar(
                    value = "",
                    onValueChange = {
                        Log.e("ShadeSearchBarPage","value:$it")
                    },
                    cornerRadius = 25.dp,
                    onSearchClick = {
                        App.context().toast("搜索:$it")
                    },
                    onRightIconClick = {
                        App.context().toast("右侧图标点击")
                    },
                    placeholderText = "Search Google or type a URL",
                    leftIcon = R.drawable.google,
                    rightIcon = R.drawable.google_audio
                )
                Spacer(modifier = Modifier.height(30.dp))
                ShadeSearchBar(
                    value = "",
                    onValueChange = {
                        Log.e("ShadeSearchBarPage","value:$it")
                    },
                    cornerRadius = 25.dp,
                    onSearchClick = {
                        App.context().toast("搜索:$it")
                    },
                    onRightIconClick = {
                        App.context().toast("删除")
                    },
                    convexity = Convexity.CONCAVE,
                    leftIcon = R.drawable.search,
                    rightIcon = R.drawable.delete,
                    imgSize = 20.dp,
                    placeholderText = "Search Google or type a URL"
                )


            }

        }

    }
}