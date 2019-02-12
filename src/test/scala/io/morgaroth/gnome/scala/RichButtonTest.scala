package io.morgaroth.gnome.scala

import org.scalatest.{FlatSpec, Matchers}

class RichButtonTest extends FlatSpec with Matchers {

  initializeGtk()

  "RichButton" should "convert from button" in {
    """new org.gnome.gtk.Button("text").onClick(_ => {
      println("")
    })""" should compile
  }

  it should "wrap a subclass of button" in {
    """new org.gnome.gtk.CheckButton().onClick(_ => { println(1) })""" should compile
  }

  it should "convert to raw button" in {
    """Btn("das").getAlignmentX""" should compile
  }

  it should "wrap subclass return original type" in {
    //                   subclass      // RichButton method     // subclass-only method
    """new org.gnome.gtk.CheckButton().onClick(_ => { println(1) }).getActive""" should compile
  }

  it should "set text using option" in {
    """new org.gnome.gtk.Button().setText(Some("ala ma kota"))""" should compile
  }

  it should "work when joined richbutton method and widgetbutton" in {
    """new org.gnome.gtk.Button().setText(Some("ala ma kota")).disabled""" should compile
  }
}
