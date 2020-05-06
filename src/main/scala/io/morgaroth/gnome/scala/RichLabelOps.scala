package io.morgaroth.gnome.scala

import org.gnome.gtk

import scala.language.implicitConversions

class RichLabel[T <: gtk.Label](underlying: T) {
  def withText(text: String): T = {
    underlying.setLabel(text)
    underlying
  }

  def withText(text: Option[String]): T = withText(text.getOrElse(""))
}

trait RichLabelOps {
  implicit def toRichLabel[T <: gtk.Label](raw: T): RichLabel[T] = new RichLabel(raw)

  def Label(text: String): gtk.Label = new gtk.Label(text)

  def L(text: String): gtk.Label = Label(text)
}
