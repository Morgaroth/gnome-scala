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
}

trait RichWidgetOps {
  implicit def toRichWidget[T <: gtk.Widget](raw: T): RichWidget[T] = new RichWidget(raw)
}