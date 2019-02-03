package io.morgaroth.gnome.scala

import org.gnome.gtk

import scala.language.implicitConversions

class RichButton[T <: gtk.Button](underlying: T) {
  def onClick(callback: T => Unit): T = {
    underlying.connect(new gtk.Button.Clicked {
      override def onClicked(button: gtk.Button) = callback(button.asInstanceOf[T])
    })
    underlying
  }
}

trait RichButtonOps {
  implicit def toRichButton[T <: gtk.Button](raw: T) = new RichButton(raw)

  def Button(title: String, onClick: gtk.Button => Unit = _ => ()): gtk.Button = new gtk.Button(title).onClick(onClick)

  def Button(title: String): gtk.Button = new gtk.Button(title)

  def Btn(title: String): gtk.Button = Button(title)
}