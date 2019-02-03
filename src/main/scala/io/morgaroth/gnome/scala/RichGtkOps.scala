package io.morgaroth.gnome.scala

import org.gnome.gtk

trait RichGtkOps {
  def initializeGtk() = {
    if (!gtk.Gtk.isInitialized) gtk.Gtk.init(Array.empty)
  }
}
