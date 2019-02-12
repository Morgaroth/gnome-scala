package io.morgaroth.gnome.scala

import org.gnome.gtk

trait RichLayoutsOps {

  def HorizontalLayout(elements: gtk.Widget*): gtk.HBox = {
    val r = new gtk.HBox(false, 1)
    elements.foreach(r.add)
    r
  }

  def VerticalLayout(elements: gtk.Widget*): gtk.VBox = {
    val r = new gtk.VBox(false, 1)
    elements.foreach(r.add)
    r
  }
}