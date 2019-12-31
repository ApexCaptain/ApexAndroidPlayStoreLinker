package com.example.apexandroidplaystorelinker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gmail.ayteneve93.playstorelinker.ApplicationInfoData
import com.gmail.ayteneve93.playstorelinker.PlayStoreLinkerApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
    }

    @Suppress("all")
    fun test() {

        PlayStoreLinkerApiService.rxFetchApplicationInfo(ApplicationInfoData.ApplicationInfoRequestBody("com.nayuntech.goodsper"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("ayteneve93_test_rx", it.body.toString())
            }, {
                Log.d("ayteneve93_test_rx", it.toString())
            })


        CoroutineScope(Dispatchers.IO).launch {
            val response = PlayStoreLinkerApiService.axFetchApplicationInfo(ApplicationInfoData.ApplicationInfoRequestBody("com.nayuntech.goodsper"))
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        Log.d("ayteneve93_test_ax", response.body().toString())
                    } else {
                        Log.d("ayteneve93_test_ax", response.toString())
                    }
                } catch (e: Exception) {
                    Log.d("ayteneve93_test_ax", "Exception ${e.message}")
                }
            }
        }


    }

}
