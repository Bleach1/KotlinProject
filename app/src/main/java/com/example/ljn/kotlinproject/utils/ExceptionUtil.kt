package com.example.ljn.kotlinproject.utils

class ExceptionUtil(message: String?) : RuntimeException(message) {
    companion object {
        private val ERROR_TOKEN = 10
        private val ERROR_NETWORK = ERROR_TOKEN + 1
        private val ERROR_SERVER = ERROR_NETWORK + 1
    }

    private fun getErrorMsg(code: Int): String {

        return when (code) {
            ERROR_TOKEN -> "token过期，请重新登陆！"
            ERROR_NETWORK -> "网络错误，请检查网络连接设置！"
            ERROR_SERVER -> "服务器内部错误，请稍后再试！"
            else -> "未知错误！"
        }

    }

}