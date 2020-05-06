package io.morgaroth.gnome

import cats.Monoid

package object scala
  extends RichButtonOps
    with RichWidgetOps
    with RichCheckboxOps
    with RichEditOps
    with RichWindowOps
    with RichLabelOps
    with RichLayoutsOps
    with RichComboBoxOps
    with RichComboBoxTextOps
    with RichGtkOps {

  implicit def strM: Monoid[String] = new Monoid[String] {
    override def empty = ""

    override def combine(x: String, y: String) = x + y
  }
}
