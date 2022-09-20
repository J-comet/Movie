package hs.project.movie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import hs.project.movie.R
import hs.project.movie.databinding.ActivityDetailSearchBinding

class DetailSearchActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailSearchBinding.inflate(layoutInflater)
    }

    private var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initWebViewSetting()

        url = intent.getStringExtra("detail_link").toString()

        if (url.isNotEmpty()) {
            binding.webView.loadUrl(url)
        }
    }

    private fun initWebViewSetting() {
        binding.webView.webViewClient = WebViewClient() // 클릭시 새창 안뜨게

        val settings: WebSettings = binding.webView.settings //세부 세팅 등록

        settings.javaScriptEnabled = true // 웹페이지 자바스클비트 허용 여부
//        settings.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
        settings.javaScriptCanOpenWindowsAutomatically = true // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        settings.loadWithOverviewMode = true // 메타태그 허용 여부
        settings.useWideViewPort = false // 화면 사이즈 맞추기 허용 여부
        settings.setSupportZoom(false) // 화면 줌 허용 여부
        settings.builtInZoomControls = false // 화면 확대 축소 허용 여부
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL // 컨텐츠 사이즈 맞추기
        settings.cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
        settings.domStorageEnabled = true // 로컬저장소 허용 여부
        settings.textZoom = 100

    }
}