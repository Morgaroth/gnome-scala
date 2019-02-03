package io.morgaroth.gnome.scala

import org.gnome.gtk
import org.gnome.gtk.{HBox, VBox}

trait RichLayoutsOps {

  def HorizontalLayout(elements: gtk.Widget*): HBox = {
    val r = new gtk.HBox(false, 1)
    elements.foreach(r.add)
    r
  }

  def VerticalLayout(elements: gtk.Widget*): VBox = {
    val r = new gtk.VBox(false, 1)
    elements.foreach(r.add)
    r
  }
}