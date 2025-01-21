package e_commerce.presentation.product_home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.presentation.LightOrange
import e_commerce.domain.enums.TabType
import e_commerce.domain.factory.TabItemFactory
import e_commerce.presentation.product_home.ProductHomeAction
import e_commerce.presentation.product_home.ProductHomeState


@Composable
fun HomeTabComponent(
    state: ProductHomeState,
    onAction: (ProductHomeAction) -> Unit,
    productsTabContent: @Composable () -> Unit,
    mapsTabContent: @Composable () -> Unit,
) {
    val tabItem = TabItemFactory.generateTabs()

    val pagerState = rememberPagerState {
        tabItem.size
    }

    LaunchedEffect(key1 = state.selectedTabIndex) {
        pagerState.animateScrollToPage(state.selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            onAction(ProductHomeAction.OnTabSelected(pagerState.currentPage))
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        TabRow(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                .background(LightOrange),
            containerColor = Color.Transparent,
            selectedTabIndex = state.selectedTabIndex,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[state.selectedTabIndex])
                        .padding(horizontal = 50.dp)
                        .height(2.dp)
                        .background(color = Color.Black)
                )
            }
        ) {
            tabItem.forEachIndexed { index, tabItem ->
                Tab(
                    selected = index == state.selectedTabIndex,
                    onClick = {
                        onAction(ProductHomeAction.OnTabSelected(index))
                        //selectedTabIndex = index
                    },
                    text = { Text(text = tabItem.title, color = Color.Black) },
                    icon = {
                        Icon(
                            tint = Color.Black,
                            imageVector = if (index == state.selectedTabIndex) {
                                tabItem.selectedIcon
                            } else {
                                tabItem.unSelectedItem
                            },
                            contentDescription = tabItem.title
                        )
                    }

                )
            }
        }

        HorizontalPager(
            userScrollEnabled = tabItem[state.selectedTabIndex].type != TabType.MAPS,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            when (tabItem[index].type) {
                TabType.PRODUCTS_LIST -> {
                    productsTabContent()
//                    Column(
//                        modifier = Modifier.fillMaxSize(),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        Text("AOOOOWBA tela de produtos!")
//                    }
                }

                TabType.MAPS          -> {
//                    mapsTabContent()
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Tela de Maps aqui, chu chu chu!")
                    }

//                    ProductMapsScreen(
//                        modifier = Modifier
//                            .fillMaxSize(),
//                        navigator = navigator
//                    )
                }
            }
        }
    }
}