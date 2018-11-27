package jp.ozelot.othloeventsample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    /*
        TODO:
            課題１
                - [X] 画面にボタンを追加してみよう
                - [X] ボタンをクリックしたら、文字が変わるようにしてみよう
            ぷらすあるふぁ
                - [X] 文字の色を変えてみよう
                - [ ] 文字の大きさを変えてみよう
                - [ ] 文字の位置を変えてみよう
            課題２
                - [X] テキストボックスで自由に文字を書けるようにしよう
                - [X] ボタンを押すと入力した文字にTextViewを書き換えよう
            ぷらすあるふぁ
                - [ ] レイアウトが縦に伸びて入力しにくいので変更してみよう
                - [X] EditTextに文字を入力したらすぐにTextViewに反映してみよう
            課題３
                - [X] ボタンを押したときに入力した文字を保存しよう
                - [X] アプリを終了し、再度立ち上がったときに保存した文字を表示しよう
            ぷらすあるふぁ
                - [X] Activityを閉じたときにテキストを保存してみよう
                - [ ] Activityを開いたときと閉じたときの時間を記録し、次開いたときに表示してみよう
            課題４
                - [X] MainActivityに２つ目のActivityを呼び出すボタンを追加しよう
                - [X] ボタンを押して２つ目のActivityを呼び出そう
                - [X] MainActivityから２つ目のActivityにデータを渡そう
            ぷらすあるふぁ
                - [ ] Extraを使っていろいろな値を渡してみよう
                - [ ] ヤフーのURLをブラウザアプリで開いてみよう
            課題５
                - [ ] XMLでRecyclerViewの中に出すレイアウトを作ろう
                - [ ] ２つ目のActivityでRecyclerViewを表示しよう
     */

    companion object {
        const val PREF_NAME = "settings"
    }

    private lateinit var settings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("listener", "onCreate")
        setContentView(R.layout.activity_main)

        settings = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        val textView = findViewById<TextView>(R.id.textView)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val secondButton = findViewById<Button>(R.id.secondButton)
        val inputText = editText.text.toString()

        textView.text = settings.getString("text", "DEFAULT")

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textView.text = inputText
                textView.setTextColor(Color.RED)
                // textView.textSize = textView.textSize
            }
        })

        button.setOnClickListener { _ ->
            settings.edit()
                .putString("text", inputText)
                .apply()
        }

        secondButton.setOnClickListener { _ ->
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("name", inputText)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("listener", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("listener", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("listener", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("listener", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("listener", "onDestroy")

        val editText = findViewById<EditText>(R.id.editText)

        settings.edit()
            .putString("text", editText.text.toString())
            .apply()
    }
}
