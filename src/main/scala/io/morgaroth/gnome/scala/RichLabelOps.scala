package io.morgaroth.gnome.scala

import org.gnome.gtk

trait RichLabelOps {
  def Label(text: String): gtk.Label = new gtk.Label(text)

  def L(text: String): gtk.Label = Label(text)
}
