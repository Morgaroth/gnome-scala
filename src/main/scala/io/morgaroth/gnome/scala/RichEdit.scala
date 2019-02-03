package io.morgaroth.gnome.scala

import org.gnome.gtk

import scala.language.implicitConversions

class RichEdit[T <: gtk.Entry](underlying: T) {

  def enable(condition: Boolean): T = {
    underlying.setEditable(condition)
    underlying.setSensitive(condition)
    underlying
  }

  def enabled: T = enable(true)

  def disabled: T = enable(false)
}

trait RichEditOps {
  implicit def toRichEdit[T <: gtk.Entry](raw: T) = new RichEdit(raw)

  def Edit() = new gtk.Entry()

  def Edit(text: String) = new gtk.Entry(text)
}