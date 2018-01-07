package bp.view

import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.event.EventTarget
import tornadofx.*
import tornadofx.control.DateTimePicker
import java.time.LocalDateTime

class MainView : View() {

    override val root = hbox {
        add<TableView>()
    }
}


fun DateTimePicker.bind(property: ObservableValue<LocalDateTime>, readonly: Boolean = false) {
    ViewModel.register(valueProperty(), property)
    if (readonly || (property !is Property<*>)) dateTimeValueProperty().bind(property) else dateTimeValueProperty().bindBidirectional(property as Property<LocalDateTime>)
}

fun EventTarget.datetimepicker(op: DateTimePicker.() -> Unit = {}) = opcr(this, DateTimePicker(), op)
fun EventTarget.datetimepicker(property: Property<LocalDateTime>, op: DateTimePicker.() -> Unit = {}) = datetimepicker().apply {
    bind(property)
    op(this)
}
