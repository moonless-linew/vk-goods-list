package ru.linew.vkscrollapp

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.linew.goods.impl.data.repository.GoodsRepository
import ru.linew.goods.impl.data.service.DummyJsonService

class VkScrollApp: Application() {

    lateinit var goodsRepository: GoodsRepository

    override fun onCreate() {
        super.onCreate()

        /*Да, это должно быть в компоненте(в терминах Dagger 2),
        но использовать Dagger в таком маленьком проекте - не имеет смысла */

        val service = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DummyJsonService::class.java)
        goodsRepository = GoodsRepository(service)
    }
}