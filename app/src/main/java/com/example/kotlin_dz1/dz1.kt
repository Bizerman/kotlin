package com.example.kotlin_dz1

import android.content.res.Configuration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel


@Preview(showSystemUi = true)
@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    myViewModel: ListofNumbers = viewModel(),
){
    val data = myViewModel.data
    val configuration = LocalConfiguration.current
    Column {
        CompositionLocalProvider(
            GridWidth provides if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 3
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(GridWidth.current) ,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top=60.dp, start = 30.dp, end = 30.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
            ){
                items(data){
                    CompositionLocalProvider(
                        ItemBoxColor provides if (it % 2 == 0) Color.Red else Color.Blue
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .width(100.dp)
                                .height(100.dp)
                                .background(color = ItemBoxColor.current)
                                .clickable { myViewModel.deleteItem(it) }
                            ,
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                modifier = Modifier,
                                fontSize = 32.sp,
                                text = "$it",
                                color = Color.White
                            )
                        }

                    }
                }
            }
        }
        Button(
            onClick = {
                myViewModel.addItem()
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
            ,
        ) {
            Text(
                text = "+",
                fontSize = 32.sp,
            )
        }
    }
}
