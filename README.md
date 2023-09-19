### Android HomeWorks
*****
<details close><summary> HomeWorkAnd (Block 1) </summary>
    <br>

#### Домашнее задание к занятию «1.1. Android Studio, SDK, эмулятор и первое приложение»
<details close><summary> Задача Code Like a Pro</summary>
    <br>

✔️ При выполнении задачи используется **GitHub Actions** для сборки приложения в ***apk-файл*** (и последующего тестирования) при каждом пуше.
Проект выводит на экран текстовую надпись **NMedia!** вместо **Hello, World**
> При создании проекта использовались следующие настройки:
>
>    applicationId: ru.netology.nmedia
> versionName: 1.0
> minSdk (минимальная версия Android): 23 (Android 6.0)
</details>  
#### Домашнее задание к занятию «1.2. Ресурсы, View и ViewGroup»
<details close><summary> Задача Launcher Icon</summary>
  <br>
Заменена стандартная иконка приложения Android на кастомную
- [логотип Нетологии](https://github.com/netology-code/and2-homeworks/blob/master/02_resources/assets/netology.svg)
![](https://raw.githubusercontent.com/netology-code/and2-homeworks/4c90eaafc1bb9566cabaa487c1442d8b647ea85e/02_resources/assets/netology.svg)
Для создания иконок используется Image Asset Studio, который входит в состав Android Studio и
позволяет выбрать изображение и сам разместит необходимые файлы в каталогах res/mipmap.
➡️ Начиная с Android 8.0, применяется подход адаптивных иконок запуска, которые разделяют подложку
иконки - **background** и непосредственно **foreground** - часть (чаще всего логотип), позволяя в
зависимости от устройства менять форму подложки.
<details close>
<summary> ❓ Если интересно - 💡 можно прочесть</summary>
<br>
Иконка указывается в манифесте: (атрибуты android:icon и android:roundIcon)
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
package="ru.netology.nmedia">
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
Эти значения ведут на файлы mipmap/ic_launcher и (mipmap/ic_launcher_round) соответственно. В
зависимости от версии платформы это будут либо сгенерированные изображения в формате png, либо xml,
в которых стоят ссылки на **foreground** и **background** ресурсы.
</details>
  </details>
<details close><summary> Задача Translations</summary>
  <br>

Добавление перевода на русский язык (для поддержания мультиязычности).
Переводиться должны:
* Название приложения (пусть на русском будет **"НМедиа"**)
* Текст (пусть на русском будет ***"НМедиа!"***)
</details>
#### Домашнее задание к занятию «1.3. Constraint Layout»
<details close><summary> Задача Layout</summary>
    <br>

Вёрстка для получения приложения следующего вида :arrow_heading_down:
![](https://github.com/netology-code/and2-homeworks/blob/master/03_constraint_layout/pic/layout.png?raw=true)
Реализована разметка в соответствии с заданием (при увеличении чисел изменяется величина строки).
Все иконки взяты из стандартного набора.
</details>
#### Домашнее задание к занятию «2.1. Обработка событий в Android»
<details close><summary> Like, Share</summary>
    <br>
Добавлен следующий функционал приложения:
* При клике на like меняется не только картинка, но и число рядом с ней: like - увеличивается на +1,
  dislike - уменьшается на -1
* При клике на share увеличиваться число рядом (10 раз нажали на share - +10)
* Добавлена логика с тысячами и миллионами: если количество лайков, share или просмотров перевалило
  за 999, то должно отображается 1K и т.д., а не 1000
:heavy_exclamation_mark::heavy_exclamation_mark::heavy_exclamation_mark: **Attention** :
heavy_exclamation_mark::heavy_exclamation_mark::heavy_exclamation_mark:
    1.1К отображается по достижении 1100
    После 10К сотни перестают отображаться
    После 1M сотни тысяч отображаются в формате 1.3M
    Логика по расчёту и преобразованию вынесена как отдельный объект
</details>
#### Домашнее задание к занятию «2.2. Архитектура: MVVM»
<details close><summary> Задача MVVM </summary>
    <br>
Проект переделан согласно архитектуре **MVVM**.
~~That's all~~ :hammer_and_wrench: ~~, but it's not easy~~ :trollface:
</details>
#### Домашнее задание к занятию «2.3. Отображение списков: RecyclerView»
<details close><summary> Задача RecyclerView </summary>
    <br>
В проект добавлена реализацию отображения списков на базе RecyclerView и ListAdapter.
По аналогии с лекцией к *OnLikeListener*, добавлен *OnShareListener*.
</details>
#### Домашнее задание к занятию «2.4. CRUD: списки, добавление, удаление, изменение»
<details close><summary> Задача Задача CRUD и отмена редактирования </summary>
    <br>
- В проект приложения добавлена реализация **CRUD**.
- Реализована отмена редактирования (по аналогии с *Telegram*)
![](https://github.com/netology-code/and2-homeworks/blob/master/07_crud/pic/cancel.png?raw=true)
Для этого с помощью ConstraintLayout сформирована соответствующую структура над полем ввода поста.
View объединены
в [виртуальную группу](https://developer.android.com/reference/androidx/constraintlayout/widget/Group)
.
Во ViewModel выставляются нужные значения для сокрытия и отображения панели:
    group.visibility = View.GONE        // сокрытие
    group.visibility = View.VISIBLE     // отображение
</details>
#### Домашнее задание к занятию «3.1. Material Design»
<details close><summary> Задача Кнопки </summary>
    <br>
Стилизованы кнопки **Like**, **Share**, **Menu**, а также **View** в виде *Button*, согласно
документации на компоненты  :
open_book:  [Buttons](https://material.io/develop/android/components/buttons).
Текст задан через атрибуты кнопки (кол-во лайков, шаринга, просмотров).
Создан и назначен кнопкам отдельный стиль ***styles.xml***.
</details>
#### Домашнее задание к занятию «3.2 Организация навигации (перемещение между Activity)»
<details close><summary> Задача Editing </summary>
    <br>
Реализованы создание поста и функция редактирования поста в отдельных *Activity*.
</details>
<details close><summary> Задача YouTube Video </summary>
    <br>
На **Intent'ах** в Android строится большая часть взаимодействия между приложениями, в частности, задействуются другие приложения для отображения нужного контента/выполнения действий и т.д. (Самые распространённые [Intent'ы](https://developer.android.com/guide/components/intents-common))

В разметку поста добавлен отдельный блок, который отображается при наличии ссылки на видео, при нажатии на который запускается неявный Intent со ссылкой. Далее сиситема его обрабатывает и отображает пользователю видео в браузере или в приложении YouTube.
<details close>

<summary> :pushpin: Реализация </summary>
    <br>
    Вместо обложки видео установлена картинка-заглушка и кнопка Play.
    Для запуска Intent'а можно кликать и на кнопке, и на обложке (т.е. пользователю не обязательно попадать в саму кнопку).
    Для открытия внешнего приложения:
        - используется URL'а вида: "https://www.youtube.com/watch?v=WhWc3b3KhnY";
        - передаётся этот URL в Uri.parse: Intent(Intent.ACTION_VIEW, Uri.parse('url'));
        - стартуется Activity с созданным Intent'ом.
</details>
    </details>

#### Домашнее задание к занятию «3.3 Хранение данных»
<details close><summary> Задача Хранение данных </summary>
    <br>
Сделана альтернативная реализация репозитория, которая работает с JSON-файлом в качестве постоянного
хранилища вместо In Memory.
</details>
#### Домашнее задание к занятию «3.4 Fragments, FragmentManager»
<details close><summary> Задача Details </summary>
    <br>
Приведение проекта к фрагментам.
Добавлен следующий функционал:
    - при нажатии на элемент списка - открывается фрагмент с конкретным постом;
    - работу с кнопками like, share и menu (редактировать, удалить) также можно проводить и во фрагменте с выбранным постом.
С этого выбранного фрагмента можно попасть:
    Если нажать на кнопку изменить, то на фрагмент редактирования.
    Если нажать на кнопку назад (системную), то на фрагмент со списком всех постов.
    Если нажать на кнопку удалить, то на фрагмент со списком всех постов.
</details>
#### Домашнее задание к занятию «4.1 SQL и SQLite»
<details close><summary> Задача SQL </summary>
    <br>
Произведена миграция проекта на ***SQLite***, с сохранением работоспособности приложения.
</details>
#### Домашнее задание к занятию «4.2 Библиотека Room»
<details close><summary> Задача Room </summary>
    <br>
Произведена миграция проекта на библиотеку ***ROOM***, с сохранением работоспособности приложения.
</details>
#### Домашнее задание к занятию «4.3 Notifications & Pushes»
<details close><summary> Задача Exceptions </summary>
    <br>
Добавлен обработчик ситуации, если в приложение придёт Notification, у которого поле action не соответствует ни одному значению из Enum'а Action.
</details>
<details close><summary> Задача New Post </summary>
    <br>
Реализовано получение уведомления о новом посте.
Уведомления о новых постах отображаются в формате:
    <имя пользователя> опубликовал новый пост:
    Текст поста... (на несколько строк)
</details>
    </details>
*****
<details close><summary> HomeWorkAndIn (Block 2) </summary>
    <br>

#### Домашнее задание к занятию «1.2. Сетевые запросы: Main Thread & Background»
<details close><summary> Задача Likes </summary>
    <br>

Предоставлены описания API для реализации:
1. Добавление лайка:
   `POST /api/posts/{id}/likes`
2. Удаление лайка:
   `DELETE /api/posts/{id}/likes`
   Где **{id}** - это идентификатор поста.
   В ответ на оба запроса сервер присылает JSON обновленного поста, который можно использовать для отображения измененного поста в ленте.
   В проекте реализвана функциональность простановки/снятия лайка. Для этого используется [код сервера с лекции](https://github.com/netology-code/andin-code/tree/master/02_threads/server).
> После выполнения запроса список постов обновляется, для отображения пользователю актуального количества лайков.
</details>
<details close><summary> Задача Swipe to Refresh* </summary>
    <br>

Реализована функциональность `Swipe To Refresh` в списках:
- Пользователь "тянет" сверху вниз список (или любое другое View)
- Появляется иконка обновления
- Список обновляется
>Для этого:
>
>1. Добавлена необходимая [зависимость в build.gradle](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout)
>2. ***RecyclerView*** завёрнут `в androidx.swiperefreshlayout.widget.SwipeRefreshLayout`
>3. `OnRefreshListener` заново запрашивает все посты с сервера

</details>
#### Домашнее задание к занятию «2.2 Современные подходы работы с многопоточностью»
<details close><summary> Задача OkHttp enqueue </summary>
    <br>

Перевод функциональности проекта с использования функции thread на enqueue из OkHttp. После выполнения запроса список постов обновляется, для отображения пользователю актуального количества лайков.
</details>
#### Домашнее задание к занятию «2.3 Многопоточность в Android»
<details close><summary> Задача Glide </summary>
    <br>

Реализовано отображение аватарок в приложении с использованием [проекта сервера](https://github.com/netology-code/andin-code/tree/master/06_android).
В качестве библиотеки для загрузки изображений использована библиотека ***Glide***.
</details>
<details close><summary> Задача Rounded </summary>
    <br>

Для реализации круглых аватарок, среди [методов трансформации](https://bumptech.github.io/glide/doc/transformations.html) был выбран наиболее подходящий класс *CircleCrop*.
</details>
<details close><summary> Задача Attachments* </summary>
    <br>

Для тех постов, у которых есть вложения (типа - *IMAGE*) ***attachment*** на сервере, реализовано отображение соответствующей картинки в посте :
![](https://github.com/netology-code/andin-homeworks/blob/master/06_android/pic/attachment.png?raw=true)
</details>
#### Домашнее задание к занятию «2.4 Retrofit (CRUD)»
<details close><summary> Задача Buggy Server </summary>
    <br>

Рассмотрен альтернативный (но очень частый) сценарий - [сервер](https://github.com/netology-code/andin-homeworks/blob/master/07_crud/server) периодически (а именно в 50% случаев) присылает не 2xx коды ответа. С его использованием реализована обработка подобного рода ошибок методом вывода на экран пользователя **Snackbar'а**
</details>
#### Домашнее задание к занятию «3.3 Coroutines в Android»
<details close><summary> Задача remove & likes </summary>
    <br>

Реализована функциональность удаления и проставления лайков.
Логика работы:
1. Сначала была модифицирована запись в локальной БД
2. Затем отправляется соответствующий запрос в API (HTTP)
   Также произведена обработка ошибок и кнопка `Retry`, в случае, если запрос в API завершился с ошибкой (в том числе в случае отсутствия сетевого соединения*).
   Примечание*: для этого не обязательно перезапускать сервер, достаточно отключить сеть в шторке телефона/эмулятора.
</details>
#### Домашнее задание к занятию «3.4 Flow»
<details close><summary> Задача New Posts </summary>
    <br>

В проекте реализован следующий функционал:
1. Посты, загружаемые в фоне (через `getNewer`), не отображаются сразу в `RecyclerView`, вместо этого выводится "плашка" как в [Vk](https://github.com/netology-code/andin-homeworks/blob/master/11_flow/pic/vk.png):
   ![](pic/vk.png)
   ![](https://github.com/netology-code/andin-homeworks/blob/master/11_flow/pic/vk.png)
2. При нажатии на "плашку" производится плавный скролл `RecyclerView` к самому верху и отображаются загруженные посты (сама "плашка" после этого удаляется).
</details>
#### Домашнее задание к занятию «4.1. Загрузка и отображение изображений»
<details close><summary> Задача Photo </summary>
    <br>

На примере загрузки изображений, реализовано их отображение. В качестве аналога взято приложение ***ВКонтакте***.
Если в посте есть картинка, то она отображается внутри этого поста. Если кликнуть на картинку, она открывается на весь экран:
![](https://github.com/netology-code/andin-homeworks/raw/master/12_images/pic/02.png)
Задача реализована через фрагменты, т.е. при клике на картинку открывается новый фрагмент, на котором изображение выводится на весь экран.
</details>
#### Домашнее задание к занятию «4.2. Регистрация, аутентификация и авторизация»
<details close><summary> Задача Аутентификация </summary>
    <br>

При нажатии на пункт меню **«Sign in»** реализована следующая последовательность действий:
1\. Открывается фрагмент с полями для ввода логина и пароля и кнопкой «Войти». Для этого фрагмента создана собственная `ViewModel`.
2\. Происходит отправка запроса пары логин / пароль с получаемым ответом в виде JSON.
3\. Далее сохраняется в `AppAuth`.
</details>
<details close><summary> Sign In to ... и Are you sure?* </summary>
    <br>

Реализован следующий функционал:
1\. Когда на экране находится лента постов в `PostViewModel`, проходит проверка, аутентифицирован ли пользователь. Проверка проходит при:
* добавлении поста (нажатие на `addPost` (+);
* лайке поста.

Если пользователь не аутентифицирован, появляется диалоговое окно с предложением пройти аутентификацию. Пользователь перенаправляется на фрагмент аутентификации.
2\. При создании поста возникает диалоговое окно с подтверждением выхода, если пользователь в `ActionBar` выбрал пункт меню `Sign Out`.
Если пользователь подтвердил выход, то перенаправляется на предыдущий фрагмент.
</details>
<details close><summary> Регистрация* </summary>
    <br>

При нажатии на пункт меню **«Sign Up»** реализована следующая последовательность действий:
1\. Открывается фрагмент с 4 полями для ввода имени, логина, пароля и подтверждения пароля и кнопкой «Зарегистрироваться». Для этого фрагмента создана собственная `ViewModel`.
2\. Отправляется запрос и в ответ получен JSON.
3\. Происходит сохранение в `AppAuth`.

</details>
#### Домашнее задание к занятию «4.3. Рассылка и приём push-уведомлений»
<details close><summary> Задача RecipientId </summary>
    <br>

Реализована проверка `recipientId` при получении push-уведомления с сервера.
Для тестирования при помощи ***Postman*** отправлялся запрос вида:
```http request
POST http://localhost:9999/api/pushes?token=<--- Used TOKEN is set here --->
Content-Type: application/json
{
  "recipientId": null,
  "content": "Hello !!!"
}
```
</details>
    </details>
*****
<details close><summary> HomeWorkAndAd (Block 3) </summary>
    <br>

#### Домашнее задание к занятию «1.1. Dependency Injection»
<details close><summary> Задача Dagger Hilt</summary>
    <br>

Призведена миграция проекта на **Dagger Hilt**.
</details>  
<details close><summary> Задача Singletons</summary>
    <br>
В приложении с лекции в `MainActivity` используются следующие конструкции:
```kotlin
FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
    ...
}
with(GoogleApiAvailability.getInstance()) {
    ...
}
```
Заменены вызовы `getInstance` на ***Dependency Injection***.

</details> 

#### Домашнее задание к занятию «1.2. Architecture Components. Часть 1»
#### Домашнее задание к занятию «1.3. Architecture Components. Часть 2»

<details close><summary> Задача Refresh on Login/Logout</summary>
    <br>

Внесены изменения для реализации запросов с сервера заново при произведении `login/logout`.

</details>  


#### Домашнее задание к занятию «1.2. Architecture Components. Часть 1»

<details close><summary> Задача Refresh to Prepend </summary>
    <br>

Произведены следующие изменения:

- Автоматический PREPEND отключен, т. е. при scroll к первому сверху элементу данные автоматически не подгружаются.
- REFRESH не затирает предыдущий кеш, а добавляет данные сверху, учитывая ID последнего поста сверху.
- APPEND работает в обычном режиме.

</details>

#### Домашнее задание к занятию «1.4. RecyclerView — продвинутое использование»

<details close><summary> Задача Paging Refresh, Prepend & Append </summary>
    <br>

Использовав примеры с лекции и [Codelab](https://developer.android.com/codelabs/android-paging), посвящённую Paging, в код проекта добавлена, поддержка PREPEND, APPEND и REFRESH, следующего поведения:

- Refreshing SwipeRefreshLayout отображается только при REFRESH.
- При PREPEND первый элемент в RecyclerView - элемент с загрузкой.
- При APPEND последний элемент в RecyclerView - элемент с загрузкой.

</details>
    </details>