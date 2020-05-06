package io.morgaroth.gnome.scala

import org.gnome.gtk

trait RichComboBoxTextOps {
  def ComboBoxText(elements: Iterable[String]): gtk.ComboBoxText = {
    val result = new org.gnome.gtk.ComboBoxText()
    elements.foreach(result.appendText)
    result
  }
}
