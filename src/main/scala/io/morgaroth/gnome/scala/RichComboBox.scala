package io.morgaroth.gnome.scala

import org.gnome.gtk
import org.gnome.gtk.ComboBox

import scala.language.implicitConversions

class RichComboBox[T <: gtk.ComboBox](underlying: T) {

  def setActiveRow(idx: Int): T = {
    underlying.setActive(idx)
    underlying
  }

  def onChange(callback: T => Unit): T = {
    underlying.connect(new ComboBox.Changed {
      override def onChanged(comboBox: ComboBox) = {
        callback(comboBox.asInstanceOf[T])
      }
    })
    underlying
  }
}

trait RichComboBoxOps {
  implicit def toRichComboBox[T <: gtk.ComboBox](raw: T): RichComboBox[T] = new RichComboBox(raw)
}