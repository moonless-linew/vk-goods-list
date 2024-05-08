package ru.linew.goods_single_info.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.linew.data.GoodsRepository
import ru.linew.goods_single_info.impl.ui.model.GoodsSingleInfoScreenStatus
import ru.linew.goods_single_info.impl.ui.model.GoodsSingleInfoUiState
import kotlin.math.roundToInt

class GoodsSingleInfoViewModel(
    private val repository: GoodsRepository,
    private val id: Int
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<GoodsSingleInfoScreenStatus>(GoodsSingleInfoScreenStatus.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                return@runCatching repository.getGoodsItem(id)
            }.onSuccess { result ->
                val hasDiscount = result.discountPercentage != 0.0
                _uiState.value = GoodsSingleInfoScreenStatus.Success(
                    state = GoodsSingleInfoUiState(
                        brand = result.brand,
                        category = result.category,
                        description = result.description,
                        id = result.id,
                        /*
                         отображение thumbnail обязательно, при этом в большинстве случаев
                        thumbnail есть в images -> могут быть повторения
                        */
                        images = result.images.toMutableList().apply { add(result.thumbnail) },
                        price = "${result.price}$",
                        originalPrice = if (hasDiscount) "${(result.price * (1 + result.discountPercentage / 100.0)).roundToInt()}$" else null,
                        rating = "${result.rating}/5.00",
                        stock = result.stock,
                        thumbnail = result.thumbnail,
                        title = result.title
                    )
                )
            }.onFailure {
                _uiState.value = GoodsSingleInfoScreenStatus.Failure
            }
        }
    }
}
