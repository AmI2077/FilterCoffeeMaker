<div align="center">

# ☕ Coffee

**Kotlin Multiplatform-приложение для любителей фильтр-кофе:**
личная коллекция кофе, рецепты заваривания, сгенерированные нейросетью, и пошаговый таймер.

Android 🤖 · iOS 🍎 · 100% общего UI на Compose Multiplatform

</div>

---

## ✨ Возможности

- **Коллекция кофе** — библиотека кофе с фотографией, степенью обжарки, оценкой Q-градера, вкусовыми нотами, плотностью и кислотностью. Добавление из галереи, редактирование и удаление с подтверждением.
- **AI-генерация рецептов** — рецепт заваривания (V60) собирается нейросетью под выбранный кофе и объём воды. Генерация через **Yandex Cloud AI** (YandexGPT / Qwen) с последующей валидацией и парсингом ответа.
- **Пошаговый таймер** — таймер ведёт по этапам рецепта: подсказки о вливании воды, объём воды на каждом шаге, прогресс заваривания в реальном времени.
- **Недавние рецепты** — сетка сохранённых рецептов на главном экране.
- **Анимация загрузки** — Lottie-анимация и ротация фактов о кофе, пока нейросеть готовит рецепт.

> Проект — учебно-портфолио: интерфейс на русском языке.

## 🛠 Стек технологий

| Категория | Технологии |
| --- | --- |
| Язык | Kotlin, Swift (только точка входа) |
| UI | Compose Multiplatform, Material 3 |
| Архитектура | MVVM + MVI (StateFlow), Clean-ish структура по фичам |
| Навигация | Voyager (Navigator + TabNavigator) |
| Инъекция зависимостей | Koin |
| Локальная БД | Room + SQLite (androidx.sqlite bundled), KSP |
| Сеть | Ktor Client (OkHttp / Darwin), kotlinx.serialization |
| Image loading | Coil 3 |
| Анимации | Compottie (Lottie) |
| AI | OpenAI-совместимый API Yandex Cloud (YandexGPT 5.1, Qwen 3.6) |
| Платформы | Android (minSdk 24), iOS (Xcode) |

## 🏗 Архитектура и структура

Проект — стандартный Kotlin Multiplatform: весь код и UI живут в общем модуле, нативные обёртки минимальны.

```
Coffee/
├── androidApp/                       # Точка входа Android (MainActivity, AndroidApp)
├── iosApp/                           # Точка входа iOS (SwiftUI обёртка)
└── shared/                           # Общий код (business logic + UI)
    └── src/
        ├── commonMain/kotlin/
        │   └── org/example/project/
        │       ├── App.kt            # Корень приложения (Navigator + MainScreen)
        │       ├── core/             # Сеть, БД, DI, навигация, тема, MVI-фреймворк
        │       └── features/         # Фичи приложения
        ├── androidMain/kotlin/       # Платформенные реализации (OkHttp, ImageSaver и т.д.)
        └── iosMain/kotlin/           # Платформенные реализации (Darwin, ImageSaver и т.д.)
```

**Фичи** (`features/`):

- `recipesList` — главный экран «Недавние рецепты» (сетка, карточка рецепта);
- `recipeDetails` — экран рецепта: параметры, шаги заваривания, запуск таймера;
- `coffeeDetails` / `savedCoffee` — экран кофе и коллекция «Мой кофе» (MVI store);
- `addCoffee` — добавление кофе (фото, характеристики);
- `timer` — пошаговый таймер заваривания.

**Паттерны**: данные проходят слой `domain → data`, экраны управляются `ScreenModel`'ами (Voyager) , сложные экраны («Мой кофе») построены на собственном компактном MVI-`Store` в `core/ui/store`.

## 🌐 AI-интеграция

`YandexAiClient` отправляет запрос в OpenAI-совместимый endpoint Yandex Cloud. `RecipeResponseSerializer` разбирает сгенерированную нейросетью строку в структурированную модель `Recipe` с шагами заваривания `BrewStep` (временные интервалы, объём воды, подсказки к каждому шагу).

> ⚠️ Для работы AI-функций необходимо указать свои ключи API в `shared/src/commonMain/kotlin/org/example/project/core/data/AiConfig.kt`.

## 📸 Скриншоты

| Главный экран | Коллекция кофе | Рецепт | Таймер |
| --- | --- | --- | --- |
| _добавьте скриншот_ | _добавьте скриншот_ | _добавьте скриншот_ | _добавьте скриншот_ |

## 🚀 Запуск

### Требования

- JDK 11+
- Android Studio (последняя версия) с Android SDK
- Для iOS: macOS с Xcode

### Android

```bash
./gradlew :androidApp:assembleDebug
```

Либо откройте проект в Android Studio и запустите конфигурацию `androidApp`.

### iOS

Откройте папку [`iosApp`](./iosApp) в Xcode и запустите приложение из него.

### Тесты

```bash
./gradlew :shared:testAndroidHostTest     # тесты общих модулей на JVM-хост
./gradlew :shared:iosSimulatorArm64Test   # тесты iOS-таргета
```

## 📄 Лицензия

Проект создан в учебных целях. Лицензия — [MIT](./LICENSE).