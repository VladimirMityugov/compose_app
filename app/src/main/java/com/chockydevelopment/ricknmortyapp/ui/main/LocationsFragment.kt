package com.chockydevelopment.ricknmortyapp.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.chockydevelopment.ricknmortyapp.domain.models_remote.loctions_model.ResultM
import com.chockydevelopment.ricknmortyapp.presentation.view_models.LocationsViewModel
import com.chockydevelopment.ricknmortyapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LocationsFragment : Fragment() {

    private val viewModel: LocationsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext())

        composeView.setContent {
            AppTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.background) {
                    LocationsList(viewModel = viewModel)
                }

            }
        }

        return composeView
    }

    @Composable
    fun LocationItem(location: ResultM) {
        Card(
            elevation = 4.dp,
            backgroundColor = MaterialTheme.colors.onSurface
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                ) {
                    Text(
                        text = "ID",
                        style = MaterialTheme.typography.h6,
                        color = Color.Yellow
                    )
                    Text(
                        text = "${location.id}",
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Column(
                    modifier = Modifier
                        .width(90.dp)
                ) {
                    Text(
                        text = "NAME",
                        style = MaterialTheme.typography.h6,
                        color = Color.Yellow
                    )
                    Text(
                        text = location.name,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Column(
                    modifier = Modifier
                        .width(90.dp)
                ) {
                    Text(
                        text = "TYPE",
                        style = MaterialTheme.typography.h6,
                        color = Color.Yellow
                    )
                    Text(
                        text = location.type,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Column(
                    modifier = Modifier
                        .width(120.dp)
                ) {
                    Text(
                        text = "DIMENSION",
                        style = MaterialTheme.typography.h6,
                        color = Color.Yellow
                    )
                    Text(
                        text = location.dimension,
                        color = Color.White
                    )
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun LocationPreview() {
        LocationItem(
            location = ResultM(
                created = "1",
                dimension = "Dimension C-137",
                id = 1,
                name = "Earth (C-137)",
                residents = emptyList(),
                type = "Planet",
                url = ""
            )
        )
    }

    @Composable
    fun LocationsList(viewModel: LocationsViewModel) {
        val locations: LazyPagingItems<ResultM> = viewModel.locations.collectAsLazyPagingItems()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            this.items(
                count = locations.itemCount,
                key = locations.itemKey(),
                contentType = locations.itemContentType(
                )
            ) { index ->
                val item = locations[index]
                item?.let {
                    LocationItem(location = item)
                }
            }
            locations.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillParentMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillParentMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = locations.loadState.refresh as LoadState.Error
                        item {
                            ErrorItem(
                                message = e.error.localizedMessage!!,
                                modifier = Modifier.fillParentMaxSize(),
                                onClickRetry = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ErrorItem(message: String, modifier: Modifier, onClickRetry: () -> Unit) {
        Box(
            modifier = modifier
                .padding(25.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = message)
                Button(onClick = onClickRetry) {
                    Text(text = "RETRY")
                }
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = LocationsFragment()
    }

}

    



