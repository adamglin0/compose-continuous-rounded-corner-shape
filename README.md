<div align="center">
<h1>
<a href="https://github.com/adamglin0/compose-continuous-rounded-corner-shape" >
  <img alt="The icon of zithian" src="docs/images/logo.png" width="520" title="compose-continuous-rounded-corner-shape"/>
</a>
</h1>
<img alt="Maven Central Version" src="https://img.shields.io/maven-central/v/com.adamglin/compose-continuous-rounded-corner-shape">

# Compose ContinuousRoundedCornerShape

Provide the ability in **Compose Multiplatform** to create **ContinuousRoundedCornerShape**, which is a smooth rounded corner.

</div>

## Versions

| `compose-continuous-rounded-shape` | CMP   | Kotlin |
|------------------------------------|-------|--------|
| 1.0.6                              | 1.9.1 | 2.2.21 |
| 1.0.5                              | 1.8.2 | 2.2.10 |
| 1.0.4                              | 1.8.2 | 2.2.0  |
| 1.0.3                              | 1.8.2 | 2.2.0  |
| 1.0.2                              | 1.8.2 | 2.2.0  |
| 1.0.1                              | 1.8.1 | 2.1.21 |

## Installation

```kts
implementation("com.adamglin:compose-continuous-rounded-corner-shape:$version")
```

## Platform support

`compose-continuous-rounded-corner-shape` supports these platforms:

1. [x] Android
2. [x] iOS
3. [x] Desktop (JVM)
4. [x] JS/Wasm (Support from 1.0.4)

wasm sample https://adamglin0.github.io/compose-continuous-rounded-corner-shape/sample/


> [!IMPORTANT]  
> Before v1.0.4, you can still use ContinuousRoundedCornerShape in commonMain, but the smooth parameter on the JS/Wasm side will be ignored.

## Use

The RoundedCornerShape has consistent parameters, with an additional smooth parameter, which supports values from 0f to 1f, with a default value of 0.6f, which is the default value in Apple's design system.

```kotlin
ContinuousRoundedCornerShape(50.dp, smooth = 1f)
```

smooth support from 0f to 1f, the design of ios is 0.6f.

e.g.

```kotlin
Box(
    modifier = Modifier
        .padding(20.dp)
        .size(300.dp)
        .clip(ContinuousRoundedCornerShape(50.dp, smooth = 1f))
        .background(Color.Red)
)
```

![img.png](docs/images/readme.png)
