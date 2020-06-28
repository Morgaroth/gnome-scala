package io.morgaroth.gnome.scala

import org.gnome.{gdk, gtk}

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

  def onKeyUp(callback: gdk.EventKey => ActionHandled): T = {
    underlying.connect(new gtk.Widget.KeyReleaseEvent {
      override def onKeyReleaseEvent(widget: gtk.Widget, eventKey: gdk.EventKey) = {
        callback(eventKey) == EventWasHandledFully
      }
    })
    underlying
  }

  def onEnter(callback: () => ActionHandled): T = {
    onKeyUp { evt =>
      if (evt.getKeyval == gdk.Keyval.Return || evt.getKeyval.toString == "Keyval.KP_Enter") callback() else AllowFurtherHandling
    }
  }

}

trait RichWidgetOps {
  implicit def toRichWidget[T <: gtk.Widget](raw: T): RichWidget[T] = new RichWidget(raw)
}