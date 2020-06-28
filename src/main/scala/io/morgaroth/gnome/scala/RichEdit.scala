package io.morgaroth.gnome.scala

import org.gnome.{gdk, gtk}

import scala.language.implicitConversions

sealed trait ActionHandled extends Product with Serializable

case object EventWasHandledFully extends ActionHandled

case object AllowFurtherHandling extends ActionHandled


class RichEdit[T <: gtk.Entry](underlying: T) {

  def enable(condition: Boolean): T = {
    underlying.setEditable(condition)
    underlying.setSensitive(condition)
    underlying
  }

  def enabled: T = enable(true)

  def disabled: T = enable(false)

  def withText(text: String): T = {
    underlying.setText(text)
    underlying
  }

  def withText(newValue: Option[String]): T = withText(newValue.getOrElse(""))
}

trait RichEditOps {
  implicit def toRichEdit[T <: gtk.Entry](raw: T): RichEdit[T] = new RichEdit(raw)

  def Edit(): gtk.Entry = new gtk.Entry()

  def Edit(text: String): gtk.Entry = new gtk.Entry(text)
}
