Smart Inköpslista
Android-app: inköpslista med butiksstöd. Kotlin + Jetpack Compose, MVVM, Room.
Arkitektur

* Mönster: MVVM — View (Compose) → ViewModel (StateFlow) → Repository → Room DAO
* UI: Jetpack Compose, Material Design 3 (inget XML-UI, inga Fragments)
* Persistens: Room, en enda databas (`AppDatabase`), inga andra databaslösningar
* Async: Kotlin Coroutines + Flow. All databasläsning ska vara Flow-baserad för reaktivt UI
* DI: Ingen Hilt/Dagger — manuell dependency injection via `ViewModelProvider.Factory`. Lägg inte till Hilt utan att fråga först.

Datamodell

* `ShoppingItem`: id, name, quantity, category, isCompleted, estimatedPrice
* `GroceryStore`: id, name, address, latitude, longitude, isActive, radiusMeters
* Kategorier är fasta strängar (se `ShoppingCategories`): Mejeri, Frukt & Grönt, Kött, Skafferi, Bröd, Dryck, Allmänt — lägg inte till fritextkategorier utan att fråga.

Tema

* Namn: "Forest Green"
* Primärfärg ljust läge: `#1E4D2B` (mörk skogsgrön)
* Generös, luftig padding — använd tokens i `ui/theme/Dimens.kt`, hårdkoda inte dp-värden
* Både ljust och mörkt läge ska stödjas

Byggkommandon

* Bygg: `./gradlew assembleDebug`
* Tester: `./gradlew test`
* Lint: `./gradlew lint`
* Kör alltid build + test efter en ändring innan du säger att en uppgift är klar

Edge-to-edge

* `enableEdgeToEdge()` ska vara aktiverat i `MainActivity`
* All insets-hantering sker explicit i Compose (`Modifier.windowInsetsPadding(WindowInsets.systemBars)`) — förlita dig inte på att systemet hanterar det automatiskt

Kodstil

* Svenska kommentarer i domänlogik är okej, men klass-/funktionsnamn ska vara på engelska
* En ViewModel per skärm, inte en gemensam "God ViewModel"
* Repository-lagret ska aldrig exponera Room-entiteter direkt till UI om UI behöver ett annat format — men för denna app är `ShoppingItem`/`GroceryStore` enkla nog att användas direkt

Gör inte

* Lägg inte till nätverksberoenden (Retrofit, Ktor etc.) utan att fråga — appen är offline-first
* Byt inte ut Room mot annan persistens
* Ändra inte primärfärgen utan att fråga

Efter varje uppgift

* Kör build + test
* Skriv ett tydligt commit-meddelande
* Sammanfatta kort vad som ändrades och varför

## CI
- GitHub Actions kör build + test automatiskt vid varje push (.github/workflows/android-build.yml)
- Kolla alltid Actions-fliken efter en push innan du litar på att koden fungerar

## Arbetsflöde
- Committa aldrig direkt till main — jobba alltid i en gren och öppna en pull request
