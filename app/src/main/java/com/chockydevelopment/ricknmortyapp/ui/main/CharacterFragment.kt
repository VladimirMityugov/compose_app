package com.chockydevelopment.ricknmortyapp.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.chockydevelopment.ricknmortyapp.domain.models_remote.characters_model.ResultM
import com.chockydevelopment.ricknmortyapp.presentation.view_models.CharactersViewModel
import com.chockydevelopment.ricknmortyapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private val viewModel: CharactersViewModel by activityViewModels()

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext())

        composeView.setContent {
            AppTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.onSurface) {
                    CharacterList(viewModel = viewModel)
                }
            }
        }

        return composeView
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun CharacterItem(
        character: ResultM,
        episodes: LazyPagingItems<com.chockydevelopment.ricknmortyapp.domain.models_remote.episode_model.ResultM>,
        firstEpisodeId: MutableList<Int>
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Card(
                elevation = 15.dp,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = MaterialTheme.colors.background
            ) {
                LoadPicture(url = character.image, name = character.name)
            }
            Column {
                Text(
                    text = character.name,
                    modifier = Modifier.padding(
                        vertical = 15.dp,
                        horizontal = 25.dp
                    ),
                    color = MaterialTheme.colors.primaryVariant,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "Live status:",
                    modifier = Modifier
                        .padding(horizontal = 25.dp),
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
                ) {

                    CharacterStatus(status = character.status)

                    Text(
                        text = character.status.replaceFirstChar { it.uppercase() },
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = MaterialTheme.colors.primaryVariant,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Species and gender:",
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .padding(horizontal = 25.dp),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light
                )

                Text(
                    text = "${character.species}(${character.gender})",
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier
                        .padding(horizontal = 25.dp),
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Last known location:",
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .padding(horizontal = 25.dp),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light
                )

                Text(
                    text = character.locationM.name,
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier
                        .padding(horizontal = 25.dp),
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "First seen in:",
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .padding(horizontal = 25.dp),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light
                )

                val firstEpisode = viewModel.getAllEpisodes(firstEpisodeId).collectAsLazyPagingItems()

              LazyColumn(
                  modifier = Modifier
                      .height(25.dp)
              ){
                  this.items(
                      count = firstEpisode.itemCount,
                      key = firstEpisode.itemKey(),
                      contentType = firstEpisode.itemContentType(
                      )
                  ) {index ->
                      val item = firstEpisode[index]
                      item?.let {
                          Text(
                              text = it.name,
                              color = MaterialTheme.colors.primaryVariant,
                              modifier = Modifier
                                  .padding(horizontal = 25.dp),
                              fontSize = 18.sp,
                              fontFamily = FontFamily.SansSerif,
                              fontWeight = FontWeight.Normal
                          )
                      }
                  }
              }



                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "Episodes:",
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .padding(horizontal = 25.dp),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )

                LazyColumn(
                    modifier = Modifier
                        .height(350.dp)
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    this.items(
                        count = episodes.itemCount,
                        key = episodes.itemKey(),
                        contentType = episodes.itemContentType(
                        )
                    ) { index ->
                        val item = episodes[index]
                        item?.let {
                            EpisodeItem(episode = it)
                        }
                    }

                }


            }

        }
    }

    @Composable
    private fun EpisodeItem(episode: com.chockydevelopment.ricknmortyapp.domain.models_remote.episode_model.ResultM) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            shape = RoundedCornerShape(9.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 5.dp
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 15.dp,
                    vertical = 5.dp
                ),
                Arrangement.SpaceBetween
            ) {
                Column {

                    Text(
                        text = buildAnnotatedString {
                            if (episode.name.length > 30) {
                                append(episode.name.take(30))
                                append("...")
                            } else {
                                append(episode.name)
                            }
                        },
                        color = MaterialTheme.colors.primaryVariant,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = episode.air_date,
                        color = MaterialTheme.colors.primaryVariant,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal
                    )
                }

                Text(
                    text = episode.episode,
                    color = MaterialTheme.colors.primaryVariant,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal
                )


            }

        }
    }

    @Composable
    fun CharacterStatus(status: String) {
        Card(
            modifier = Modifier
                .height(9.dp)
                .width(9.dp),
            shape = RoundedCornerShape(9.dp),
            backgroundColor = when (status) {
                ALIVE -> Color.Green
                DEAD -> Color.Red
                UNKNOWN -> Color.Gray
                else -> Color.Transparent
            }
        ) {}
    }


    @Preview(showBackground = true)
    @Composable
    fun EpisodePreview() {
        EpisodeItem(
            episode = com.chockydevelopment.ricknmortyapp.domain.models_remote.episode_model.ResultM(
                air_date = "2.3.4.",
                characters = emptyList(),
                created = "1.2.3.",
                episode = "EPISODE",
                id = 1,
                name = "EPISODE",
                url = ""
            )
        )
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun CharacterList(viewModel: CharactersViewModel) {
        val characterId = viewModel.characterId.value
        if (characterId != null) {
            val characterFiltered = viewModel.getAllCharacters(characterId)
            val characterItem = characterFiltered.collectAsLazyPagingItems()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                this.items(
                    count = characterItem.itemCount,
                    key = characterItem.itemKey(),
                    contentType = characterItem.itemContentType(
                    )
                ) { index ->
                    val item = characterItem[index]
                    item?.let {
                        val allEpisodeIds = mutableListOf<Int>()
                        val firstEpisodeId = mutableListOf<Int>()
                        it.episode.forEach { episode ->
                            val episodeId = episode.substringAfterLast("/")
                            allEpisodeIds.add(episodeId.toInt())
                        }
                        firstEpisodeId.add(allEpisodeIds.min())
                        val episodes =
                            viewModel.getAllEpisodes(allEpisodeIds).collectAsLazyPagingItems()
                        CharacterItem(
                            character = item,
                            episodes = episodes,
                            firstEpisodeId = firstEpisodeId
                        )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @SuppressLint("UnrememberedMutableState")
    @Composable
    private fun LoadPicture(
        url: String,
        name: String
    ) {
        GlideImage(
            model = url,
            contentDescription = name,
            modifier = Modifier.border(
                1.dp,
                MaterialTheme.colors.primary,
                RoundedCornerShape(10.dp)
            ),
            contentScale = ContentScale.FillWidth
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharacterFragment()
        private const val ALIVE = "Alive"
        private const val DEAD = "Dead"
        private const val UNKNOWN = "unknown"
    }

}

    



