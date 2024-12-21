# Compose SuperRoundedCornerShape

SuperRoundedShape for Compose Multiplatform.

![Maven Central Version](https://img.shields.io/maven-central/v/com.adamglin/compose-super-rounded-corner-shape)

---

## Versions

| `compose-super-rounded-shape` | CMP   | Kotlin |
|-------------------------------|-------|--------|
| 0.0.1-beta09                  | 1.7.1 | 2.1.0  |


## Installation

```kts
implementation("com.adamglin:compose-super-rounded-corner-shape:$version")
```

## Platform support

`compose-super-rounded-corner-shape` supports these platforms:

1. [x] Android
2. [x] iOS
3. [x] Desktop (JVM)
4. [x] JS/Wasm (now using RoundedCornerShape compatibility, waiting androidx-graphics-shapes support web target. )

## Use
```kotlin
SuperRoundedCornerShape(50.dp, smooth = 1f)
```

smooth support from 0f to 1f, the design of ios is 0.6f.

```kotlin
Box {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp)
            .clip(RoundedCornerShape(50.dp)) // .clip(RoundedCornerShape(50.dp,50.dp,50.dp,50.dp,0.6f))
            .background(Color.Black)
    )
    Box(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp)
            .clip(SuperRoundedCornerShape(50.dp, smooth = 1f))
            .background(Color.Red)
    )
}
```

![img.png](docs/images/readme.png)