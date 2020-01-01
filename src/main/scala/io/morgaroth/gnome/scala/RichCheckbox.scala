package io.morgaroth.gnome.scala

import org.gnome.gtk

import scala.language.implicitConversions

class RichCheckbox[T <: gtk.CheckButton](underlying: T) {
  def onToggle(callback: (Boolean, T) => Unit): T = {
    underlying.connect(new gtk.ToggleButton.Toggled {
      override def onToggled(toggleButton: gtk.ToggleButton): Unit = {
        callback(toggleButton.getActive, toggleButton.asInstanceOf[T])
      }
    })
    underlying
  }

  def select(condition: Boolean): T = {
    underlying.setActive(condition)
    underlying
  }

  def selected: T = select(true)

  def unselected: T = select(false)

}

trait RichCheckboxOps {
  implicit def toRichCheckbox[T <: gtk.CheckButton](raw: T): RichCheckbox[T] = new RichCheckbox(raw)

  def Checkbox(title: String): gtk.CheckButton = new org.gnome.gtk.CheckButton(title)

  def Checkbox(title: String, onChange: (Boolean, gtk.CheckButton) => Unit): gtk.CheckButton = Checkbox(title).onToggle(onChange)
}