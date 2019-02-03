package io.morgaroth.gnome.scala

import org.gnome.gtk

trait RichLabelOps {
  def Label(text: String) = new gtk.Label(text)

  def L(text: String) = Label(text)
}
