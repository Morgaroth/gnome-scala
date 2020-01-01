package io.morgaroth.gnome.scala

import org.gnome.gtk

import scala.language.implicitConversions


class RichWidget[T <: org.gnome.gtk.Widget](underlying: T) {
  def enable(condition: Boolean): T = {
    underlying.setSensitive(condition)
    underlying
  }

  def enabled: T = enable(true)

  def disabled: T = enable(false)

  def withSizeRequest(width: Int, height: Int): T = {
    underlying.setSizeRequest(width, height)
    underlying
  }
}

trait RichWidgetOps {
  implicit def toRichWidget[T <: gtk.Widget](raw: T): RichWidget[T] = new RichWidget(raw)
}