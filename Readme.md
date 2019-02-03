# Installation
```
resolvers += Resolver.bintrayRepo("morgaroth", "maven")
libraryDependencies += "io.morgaroth" %% "gnome-scala" % "1.0.2"
```

# Usage

### Server

```tut
import io.morgaroth.gnome.scala._

// required
initializeGtk()

// create button
val btn1 = Btn("Some name")
val btn2 = Button("Another name")

// set text using option
btn2.setText(Some("Save"))
```