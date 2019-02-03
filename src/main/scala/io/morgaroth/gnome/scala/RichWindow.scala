package io.morgaroth.gnome.scala

import org.gnome.gdk.Event
import org.gnome.gtk

import scala.language.implicitConversions

class RichWindow(window: gtk.Window) {
  def closeOnDeleteEvent(): Unit = {
    window.connect(new gtk.Window.DeleteEvent() {
      def onDeleteEvent(source: gtk.Widget, event: Event) = {
        gtk.Gtk.mainQuit()
        false
      }
    })
  }
}

trait RichWindowOps {
  implicit def toRichWindow(raw: gtk.Window) = new RichWindow(raw)
}