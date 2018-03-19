package com.bpplatform.howlokhttp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val JSON = MediaType.parse("application/json; charset=utf-8")

        val client = OkHttpClient()
        var url = "https://openapi.naver.com/v1/papago/n2mt"
        var json = JSONObject()
        json.put("source","ko")
        json.put("target","en")
        json.put("text","만나서 반갑습니다.")
        val body = RequestBody.create(JSON, json.toString())
        val request = Request.Builder()
                .header("X-Naver-Client-Id","e2q77p_f58GcFUT4mgKu")
                .addHeader("X-Naver-Client-Secret","wf8W67IfYl")
                .url(url)
                .post(body)
                .build()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
                var str = response!!.body()!!.string()
                println(str)
                var papagoDTO = Gson().fromJson<PapagoDTO>(str,PapagoDTO::class.java)
                println(papagoDTO.message!!.result!!.translatedText)
            }

        })
    }
}
