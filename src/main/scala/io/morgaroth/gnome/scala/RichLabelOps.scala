package io.morgaroth.gnome.scala

import org.gnome.gtk
import org.gnome.gtk.Label

trait RichLabelOps {
  def Label(text: String): Label = new gtk.Label(text)

  def L(text: String): Label = Label(text)
}
