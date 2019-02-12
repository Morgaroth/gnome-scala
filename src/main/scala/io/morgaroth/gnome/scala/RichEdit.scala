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

  def text_=(newValue: String): T = {
    underlying.setText(newValue)
    underlying
  }

  def text_=(newValue: Option[String]): T = {
    underlying.setText(newValue.getOrElse(""))
    underlying
  }
}

trait RichEditOps {
  implicit def toRichEdit[T <: gtk.Entry](raw: T): RichEdit[T] = new RichEdit(raw)

  def Edit(): gtk.Entry = new gtk.Entry()

  def Edit(text: String): gtk.Entry = new gtk.Entry(text)
}