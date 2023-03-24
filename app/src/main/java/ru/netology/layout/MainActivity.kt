package ru.netology.layout


import android.icu.text.CompactDecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.layout.databinding.ActivityMainBinding
import ru.netology.layout.dto.Post
import java.util.*


class MainActivity : AppCompatActivity() {

    fun convertNumber(number: Int, locale: Locale): String? {
        val compactDecimalFormat: CompactDecimalFormat =
            CompactDecimalFormat.getInstance(locale, CompactDecimalFormat.CompactStyle.SHORT)
        return compactDecimalFormat.format(number.toLong())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            1,
            "Нетология. Университет интернет-профессий будущего",
            "21 мая в 18:36",
            "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",

            )
        with(binding) {
            author.text = post.author
            publisher.text = post.published
            content.text = post.content

            if (post.LikeByMt) {
                like?.setImageResource(R.drawable.baseline_favorite_border_24)
            }
            countlike.text = convertNumber(post.likes, Locale.US)
            like?.setOnClickListener {
                post.LikeByMt = !post.LikeByMt
                post.likes = if (post.LikeByMt) post.likes - 1 else {
                    post.likes + 1
                }

                countlike.text = convertNumber(post.likes, Locale.US)
                like.setImageResource(
                    if (post.LikeByMt) R.drawable.baseline_favorite_border_24
                    else
                        R.drawable.baseline_favorite_red_24
                )

            }


            counsher.text = convertNumber(post.sheres, Locale.US)

            share?.setOnClickListener {
                post.sheres = post.sheres + 1
                counsher.text = convertNumber(post.sheres, Locale.US)
            }

            countview.text = convertNumber(post.views, Locale.US)
            view?.setOnClickListener {
                post.views = post.views + 100
                countview.text = convertNumber(post.views, Locale.US)
            }

        }

    }


}




