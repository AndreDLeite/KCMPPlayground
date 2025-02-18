package home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import core.presentation.desing_system.DSDefaultText
import home.domain.ProjectType
import home.presentation.components.ProjectHubTopBar

@Composable
fun ProjectsHubHomeScreenRoot(
    viewModel: HomePageViewModel,
    onProjectClick: (ProjectType) -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProjectsHubHomeScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is HomeScreenAction.OnProjectClick -> onProjectClick(action.project)
                else                               -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun ProjectsHubHomeScreen(
    state: HomeScreenState,
    modifier: Modifier = Modifier,
    onAction: (HomeScreenAction) -> Unit,
) {
    Scaffold(
        topBar = {
            ProjectHubTopBar(
                isSearching = state.isSearching
            ) {
                onAction(HomeScreenAction.OnSearchClick)
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding() + 16.dp,
                )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(state.projects) { item ->
                    Card(
                        onClick = {
                            onAction(HomeScreenAction.OnProjectClick(item.projectType))
                        },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                        ),
                        modifier = modifier
                            .fillParentMaxWidth()
                            .height(120.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            DSDefaultText(item.name)
                            Spacer(modifier.height(8.dp))
                            DSDefaultText(item.lastUpdated)
                        }

                    }
                }
            }
        }
    }
}