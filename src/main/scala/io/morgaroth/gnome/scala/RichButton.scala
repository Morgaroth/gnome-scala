package io.morgaroth.gnome.scala

import org.gnome.gtk

import scala.language.implicitConversions

class RichButton[T <: gtk.Button](val underlying: T) {
  type Out = T

  def onClick(callback: T => Unit): RichButton[T] = {
    underlying.connect(new gtk.Button.Clicked {
      override def onClicked(button: gtk.Button) = callback(button.asInstanceOf[T])
    })
    this
  }

  def setText(newValue: String): RichButton[T] = {
    underlying.setLabel(newValue)
    this
  }

  def setText(newValue: Option[String]): RichButton[T] = setText(newValue.getOrElse(""))
}

trait RichButtonOps {
  implicit def toRichButton[T <: gtk.Button](raw: T) = new RichButton(raw)

  implicit def toNormalButton[T <: gtk.Button](raw: RichButton[T]) = raw.underlying

  def Button(title: String, onClick: gtk.Button => Unit = _ => ()): RichButton[gtk.Button] = new RichButton(new gtk.Button(title).onClick(onClick))

  def Button(title: String): RichButton[gtk.Button] = new RichButton(new gtk.Button(title))

  def Btn(title: String): RichButton[gtk.Button] = new RichButton(Button(title))
}