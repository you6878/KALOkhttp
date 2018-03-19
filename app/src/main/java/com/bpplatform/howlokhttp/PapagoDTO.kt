package com.bpplatform.howlokhttp

/**
 * Created by myeongsic on 2018. 3. 19..
 */
data class PapagoDTO(var message : Message? = null){
    data class Message(var result : Result? = null){
        data class Result(var translatedText : String? = null)
    }
}